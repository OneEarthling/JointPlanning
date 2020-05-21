package com.example.jointplanning.authorization;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.jointplanning.Constants;
import com.example.jointplanning.DataManager;
import com.example.jointplanning.authorization.Authorization;

public class App extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Authorization.createInstance(this, Constants.BASE_URL);
    }

    /**
     * обработчик авторизации пользователя
     */
    public void onAuthorized() {
        DataManager.initialize(this, Authorization.getInstance().getUser().getCredentials().getToken());
    }

    /**
     * пользователь сбросил авторизацию
     * @param clearUserAuthorization очистка авторизации пользователя
     */
    public void unAuthorized(boolean clearUserAuthorization) {
        if(clearUserAuthorization) {
            Authorization.getInstance().destroy();
        } else {
            Authorization.getInstance().reset();
        }
    }
}
