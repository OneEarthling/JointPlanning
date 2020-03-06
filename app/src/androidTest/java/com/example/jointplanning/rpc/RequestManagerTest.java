package com.example.jointplanning.rpc;

import com.example.jointplanning.Constants;

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
    public void rpc() {
        //RequestManager.rpc(Constants.BASE_URL, )
    }
}