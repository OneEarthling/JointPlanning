package com.example.jointplanning.rpc;

import android.util.Log;

import com.example.jointplanning.Constants;
import com.example.jointplanning.authorization.BasicCredentials;
import com.example.jointplanning.model.Project;
import com.example.jointplanning.model.Tag;
import com.example.jointplanning.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class RequestManagerTest {
    @Test
    public void exists() throws IOException {
        HashMap<String, String> map = RequestManager.exists(Constants.BASE_URL);
        assertNotNull(map.get(RequestManager.KEY_IP));
        assertNotNull(map.get(RequestManager.KEY_VERSION));
    }

    @Test
    public void rpc() throws IOException {
        BasicCredentials basicCredentials = new BasicCredentials("demo", "000");
        QueryData queryData = new QueryData();
        queryData.limit = 2;
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, basicCredentials.getToken(), "cs_tag", "Query", queryData);
        if(rpcResults.length > 0) {
            List<Tag> tags = new ArrayList<>();
            rpcResults[0].toObjectArray(tags, Tag.class);
            assertTrue(tags.size() > 0);
        }

        assertTrue(rpcResults[0].isSuccess());
    }

    @Test
    public void getTasks() throws IOException {
        Gson gson = new Gson();
        BasicCredentials basicCredentials = new BasicCredentials("demo", "000");
        QueryData queryData = new QueryData();
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, basicCredentials.getToken(), "cd_userstory", "Query", queryData);
        JSONObject[] records = rpcResults[0].result.records;
        Log.e("TAG", String.valueOf(rpcResults[0].result.records.length));
        Log.e("TAG", Arrays.toString(rpcResults[0].result.records));
        Type taskItemType = new TypeToken<ArrayList<Task>>(){}.getType();
        List<Task> list = gson.fromJson(Arrays.toString(records), taskItemType);
        assertTrue(rpcResults[0].isSuccess());
    }

    @Test
    public void getTask() throws IOException {
        Gson gson = new Gson();
        BasicCredentials basicCredentials = new BasicCredentials("demo", "000");
        QueryData queryData = new QueryData();
        FilterItem fi = new FilterItem("id","=", "2" );
        queryData.filter = new Object[]{fi};
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, basicCredentials.getToken(), "cd_userstory", "Query", queryData);
        JSONObject[] records = rpcResults[0].result.records;
        Log.e("TAG", String.valueOf(rpcResults[0].result.records.length));
        Log.e("TAG", Arrays.toString(rpcResults[0].result.records));
        Task task = gson.fromJson(String.valueOf(rpcResults[0].result.records[0]), Task.class);
        assertTrue(rpcResults[0].isSuccess());
    }

    @Test
    public void getTags() throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        BasicCredentials basicCredentials = new BasicCredentials("demo", "000");
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, basicCredentials.getToken(), "cs_tag", "Query", queryData);
        JSONObject[] records = rpcResults[0].result.records;
        Log.e("TAG", String.valueOf(rpcResults[0].result.records.length));
        Log.e("TAG", Arrays.toString(rpcResults[0].result.records));
        List<Tag> tags = gson.fromJson(Arrays.toString(records),  new TypeToken<ArrayList<Tag>>(){}.getType());
        assertTrue(rpcResults[0].isSuccess());
    }

    @Test
    public void getProjects() throws IOException {
        Gson gson = new Gson();
        QueryData queryData = new QueryData();
        BasicCredentials basicCredentials = new BasicCredentials("demo", "000");
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, basicCredentials.getToken(), "cd_project", "Query", queryData);
        JSONObject[] records = rpcResults[0].result.records;
        Log.e("TAG", String.valueOf(rpcResults[0].result.records.length));
        Log.e("TAG", Arrays.toString(rpcResults[0].result.records));
        List<Project> projects = gson.fromJson(Arrays.toString(records),  new TypeToken<ArrayList<Project>>(){}.getType());
        assertTrue(rpcResults[0].isSuccess());
    }

}