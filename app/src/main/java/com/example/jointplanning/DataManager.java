package com.example.jointplanning;

import android.content.Context;
import android.util.Log;

import com.example.jointplanning.authorization.BasicCredentials;
import com.example.jointplanning.model.TaskJson;
import com.example.jointplanning.rpc.QueryData;
import com.example.jointplanning.rpc.RPCResult;
import com.example.jointplanning.rpc.RequestManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    private Context mContext;
    private String mToken;
    private static DataManager mDataManager;

    private DataManager(Context context, String token) {
        mContext = context;
        mToken = token;
    }

    public static DataManager getInstance() {
        return mDataManager;
    }

    public static void initialize(Context context, String token) {
        mDataManager = new DataManager(context, token);
    }
}
