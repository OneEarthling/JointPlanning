package com.example.jointplanning.rpc;

import com.example.jointplanning.Constants;
import com.example.jointplanning.authorization.Authorization;
import com.example.jointplanning.authorization.BasicCredentials;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

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
        assertTrue(rpcResults[0].isSuccess());
    }
}