package com.example.jointplanning.rpc;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Результат RPC вызова
 */
public class RPCResult {

    public static final int NOT_AUTHORIZATION_RESULT = 401;
    public static final int BAD_REQUEST = 400;
    public static final int SERVER_ERROR = 500;
    public static final int SUCCESS = 200;
    public static final int APPLICATION_ERROR = 600;

    /**
     * код результата
     */
    int code;

    /**
     * метаописание результат запроса
     */
    public RPCResultMeta meta;

    public String action;
    public String method;
    public int tid;
    public String type;
    public String host;

    public RPCRecords result;

    /**
     * обработка JSONObject
     * @param obj объект для обработки данных
     * @return Возвращается объект
     */
    private static RPCResult processingJSONObject(JSONObject obj){
        RPCResult result;
        try {
            result = new RPCResult();
            result.code = obj.getInt("code");

            RPCResultMeta meta = new RPCResultMeta();
            JSONObject metaJSONObject = obj.getJSONObject("meta");
            meta.success = metaJSONObject.getBoolean("success");
            try {
                meta.msg = metaJSONObject.getString("msg");
            }catch (Exception ignored){

            }
            result.meta = meta;
            switch (result.code) {
                case NOT_AUTHORIZATION_RESULT:

                    break;

                case SERVER_ERROR:
                case BAD_REQUEST:
                case SUCCESS:
                    result.action = obj.getString("action");
                    result.method = obj.getString("method");
                    result.tid = obj.getInt("tid");
                    result.type = obj.getString("type");
                    try {
                        result.host = obj.getString("host");
                    } catch (Exception exc) {
                        result.host = "undefined";
                    }

                    RPCRecords records = new RPCRecords();
                    JSONObject resultJSONObject = obj.getJSONObject("result");
                    try {
                        records.total = resultJSONObject.getInt("total");
                    }catch (Exception e){
                        records.total = 0;
                    }

                    try {
                        JSONArray array = resultJSONObject.getJSONArray("records");
                        if (array.length() > 0) {
                            records.total = array.length();
                            records.records = new JSONObject[array.length()];
                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    records.records[i] = array.getJSONObject(i);
                                } catch (JSONException e) {
                                    try {
                                        JSONObject innerObj = new JSONObject();
                                        innerObj.put("value", array.get(i));
                                        records.records[i] = innerObj;
                                    } catch (Exception inner) {
                                        inner.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            records.records = new JSONObject[0];
                        }
                    }catch (Exception e){
                        String errorMessage = resultJSONObject.getString("records");
                        records.records = new JSONObject[0];
                    }
                    result.result = records;

                    break;
            }

            return result;
        }catch (Exception e){
            result = new RPCResult();
            result.code = APPLICATION_ERROR;
            result.meta = new RPCResultMeta();
            result.meta.success = false;
            result.meta.msg = e.toString();
            return result;
        }
    }

    /**
     * создание экземпляра
     * @param requestResult результат запроса
     * @return обработанный объект
     */
    public static RPCResult[] createInstance(String requestResult) {
        try {
            RPCResult[] result;
            if(requestResult.indexOf("[") ==0){
                JSONArray array = new JSONArray(requestResult);
                result = new RPCResult[array.length()];
                for(int i =0;i < array.length(); i++){
                    result[i] = processingJSONObject(array.getJSONObject(i));
                }
            }else {
                result = new RPCResult[1];
                result[0] = processingJSONObject(new JSONObject(requestResult));
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Результат обработки пакета
     * @return true - пакет обработан без ошибок
     */
    public boolean isSuccess() {
        return meta.success;
    }

    public <T> void toObjectArray(List<T> input, Class<T> classOfT) {
        if(isSuccess()) {
            JSONObject[] jsonObject = result.records;

            Gson gson = new Gson();
            for(JSONObject object : jsonObject) {
                String json = object.toString();
                input.add(gson.fromJson(json, classOfT));
            }
        }
    }
}
