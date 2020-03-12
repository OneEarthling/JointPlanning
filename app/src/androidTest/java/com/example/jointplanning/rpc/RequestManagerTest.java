package com.example.jointplanning.rpc;

import com.example.jointplanning.Constants;
import com.example.jointplanning.authorization.Authorization;
import com.example.jointplanning.authorization.BasicCredentials;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
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

    private class Tag {

        /**
         * Идентификатор
         */
        public Long id;

        /**
         * Код
         */
        public long n_code;

        /**
         * Наименование
         */
        public String c_name;

        /**
         * Краткое наименование
         */
        public String c_short_name;

        /**
         * Константа
         */
        public String c_const;

        /**
         * отключено
         */
        public boolean b_disabled;

        /**
         * сортировка
         */
        public int n_order;

        /**
         * по умолчанию
         */
        public boolean b_default;
    }

}