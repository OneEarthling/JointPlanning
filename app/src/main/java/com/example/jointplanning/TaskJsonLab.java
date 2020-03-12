package com.example.jointplanning;

import com.example.jointplanning.authorization.BasicCredentials;
import com.example.jointplanning.model.Task;
import com.example.jointplanning.model.TaskJson;
import com.example.jointplanning.rpc.QueryData;
import com.example.jointplanning.rpc.RPCResult;
import com.example.jointplanning.rpc.RequestManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TaskJsonLab {
    private static TaskJsonLab sTaskJsonLab;

    private List<TaskJson> mTasks;

    public static TaskJsonLab get() throws IOException {
        if (sTaskJsonLab == null) {
            sTaskJsonLab = new TaskJsonLab();
        }
        return sTaskJsonLab;
    }

    private TaskJsonLab() throws IOException {
        Gson gson = new Gson();
        BasicCredentials basicCredentials = new BasicCredentials("demo", "000");
        QueryData queryData = new QueryData();
        RPCResult[] rpcResults = RequestManager.rpc(Constants.BASE_URL, basicCredentials.getToken(), "cd_userstory", "Query", queryData);
        JSONObject[] records = rpcResults[0].result.records;

       mTasks = gson.fromJson(Arrays.toString(records),  new TypeToken<ArrayList<TaskJson>>(){}.getType());
    }

    public List<TaskJson> getTasks() {
        return mTasks;
    }

    public TaskJson getTask(long id) {
        for (TaskJson task : mTasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }
}
