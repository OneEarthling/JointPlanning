package com.example.jointplanning;

import android.content.Context;

import com.example.jointplanning.authorization.BasicCredentials;
import com.example.jointplanning.model.Project;
import com.example.jointplanning.model.Tag;
import com.example.jointplanning.model.Task;
import com.example.jointplanning.rpc.FilterItem;
import com.example.jointplanning.rpc.QueryData;
import com.example.jointplanning.rpc.RPCResult;
import com.example.jointplanning.rpc.RequestManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<Task> getTasks() throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, mToken, "cd_userstory", "Query", queryData);
        if(rpcResults[0].isSuccess()) {
            JSONObject[] records = rpcResults[0].result.records;
            return gson.fromJson(Arrays.toString(records), new TypeToken<ArrayList<Task>>() {}.getType());
        }
        return null;
    }

    public Task getTask(int id) throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        FilterItem fi = new FilterItem("id","=", String.valueOf(id));
        queryData.filter = new Object[]{fi};
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, mToken, "cd_userstory", "Query", queryData);
        if(rpcResults[0].isSuccess() && rpcResults[0].result.records.length !=0) {
            return gson.fromJson(String.valueOf(rpcResults[0].result.records[0]), Task.class);
        }
        return null;
    }

    public List<Tag> getTags() throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, mToken, "cs_tag", "Query", queryData);
        if(rpcResults[0].isSuccess()) {
            JSONObject[] records = rpcResults[0].result.records;
            return gson.fromJson(Arrays.toString(records), new TypeToken<ArrayList<Tag>>() {}.getType());
        }
        return null;
    }

    public Tag getTag(int id) throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        FilterItem fi = new FilterItem("id","=", String.valueOf(id));
        queryData.filter = new Object[]{fi};
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, mToken, "cs_tag", "Query", queryData);
        if(rpcResults[0].isSuccess() && rpcResults[0].result.records.length !=0) {
            return gson.fromJson(String.valueOf(rpcResults[0].result.records[0]), Tag.class);
        }
        return null;
    }

    public List<Project> getProjects() throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, mToken, "cd_project", "Query", queryData);
        if(rpcResults[0].isSuccess()) {
            JSONObject[] records = rpcResults[0].result.records;
            return gson.fromJson(Arrays.toString(records), new TypeToken<ArrayList<Project>>() {}.getType());
        }
        return null;
    }

    public Project getProject(int id) throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        FilterItem fi = new FilterItem("id","=", String.valueOf(id));
        queryData.filter = new Object[]{fi};
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, mToken, "cd_project", "Query", queryData);
        if(rpcResults[0].isSuccess() && rpcResults[0].result.records.length !=0) {
            return gson.fromJson(String.valueOf(rpcResults[0].result.records[0]), Project.class);
        }
        return null;
    }
}
