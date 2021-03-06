package com.example.jointplanning.authorization;

import android.content.Context;

import com.example.jointplanning.util.DateUtil;
import com.example.jointplanning.util.StringUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Хранение логина и пароля в памяти устройства.
 * Предназначен для проверки безопасности в offline
 */
public class AuthorizationCache {

    private Context context;
    private final String PART_FILENAME = ".credentials";

    /**
     *
     * @param context контекст приложения
     */
    public AuthorizationCache(Context context){
        this.context = context;
    }

    /**
     * Запись настроек безопасности
     * @param user объект в котором храниться безопасности пользователя
     * @return Возвращается true если все хорошо
     */
    public boolean write(BasicUser user){
        return saveUser(user, "", new Date());
    }

    /**
     * Обновление информации об account
     * @param login логин
     * @param pin пин-код
     * @param time время
     * @return Возвращается true если все хорошо
     */
    public boolean update(String login, String pin, Date time){
        BasicUser user = read(login);
        return saveUser(user, pin, time);
    }

    /**
     * Чтение настроек безопасности
     * @param login логин пользователя
     * @return Данные об авторизации
     */
    public BasicUser read(String login){
        BasicUser user = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(login + PART_FILENAME)));
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = br.readLine()) != null) {
                builder.append(str);
            }
            JSONObject json = new JSONObject(builder.toString());
            user = new BasicUser(BasicCredentials.decode(json.getString("token")), json.getInt("userId"), json.getString("claims"));
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Чтение Пин-кода пользователя
     * @param login логин пользователя
     * @return пин-код
     */
    public String readPin(String login){
        Object result = getData(login, "pin");
        if(result != null){
            return (String)result;
        }
        return "";
    }

    /**
     * Чтение последнего времени входа
     * @param login логин пользователя
     * @return Время последнего входа
     */
    public Date readDate(String login) throws ParseException {
        Object result = getData(login, "time");
        if(result != null){
            return DateUtil.convertStringToDate(String.valueOf(result));
        }
        return null;
    }

    /**
     * Очистка кэшированных данных
     * @param all удалить все файлы безопасности или нет
     */
    public boolean clear(boolean all){
        File dir = new File(context.getFilesDir().getPath());
        if(all){
            File[] files = dir.listFiles();
            for(File file: files){
                if(file.getName().indexOf(PART_FILENAME) > 0){
                    context.deleteFile(file.getName());
                }
            }
            return true;
        } else {
            String login = Authorization.getInstance().getUser().getCredentials().login;
            if(!StringUtil.isEmptyOrNull(login)) {
                File file = new File(context.getFilesDir(), login + PART_FILENAME);
                if (file.exists()) {
                    return context.deleteFile(PART_FILENAME);
                }
            }
        }

        return false;
    }

    /**
     * Получение списка пользователея, которые авторизовывались на устройстве
     * @return Возвращается массив строк с логинали пользователей
     */
    public String[] getNames(){
        File dir = new File(context.getFilesDir().getPath());
        ArrayList<String> users = new ArrayList<>();

        File[] files = dir.listFiles();
        for(File file: files){
            if(file.getName().indexOf(PART_FILENAME) > 0){
                users.add(file.getName().replace(PART_FILENAME, ""));
            }
        }
        return users.toArray(new String[0]);
    }

    /**
     * получение данных
     * @param login логин
     * @param key ключ
     * @return данные
     */
    private Object getData(String login, String key) {
        Object data = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(login + PART_FILENAME)));
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = br.readLine()) != null) {
                builder.append(str);
            }
            br.close();
            JSONObject json = new JSONObject(builder.toString());
            data = json.get(key);
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Сохранение информации о пользователе
     * @param user пользователь
     * @param pin пин-код
     * @param time время записи
     * @return Возвращается true если все хорошо
     */
    private boolean saveUser(BasicUser user, String pin, Date time) {
        boolean result = false;
        BasicCredentials credentials = user.getCredentials();
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(credentials.login + PART_FILENAME, Context.MODE_PRIVATE)));
            JSONObject json = new JSONObject();
            json.put("userId", user.getUserId());
            json.put("claims", user.claims);
            json.put("pin", pin);
            json.put("time", DateUtil.convertDateToString(time));
            json.put("token", user.getCredentials().getToken());
            String t =json.toString();
            bw.write(json.toString());
            bw.close();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
