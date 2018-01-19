package com.luyichao.staticserver.main;

import com.luyichao.staticserver.server.StaticServer;

public class Main {
    public static void main(String[] args) {

        StaticServer server = StaticServer.getInstance();
        server.start();
    }
}
