package com.luyichao.staticserver.server;

import com.luyichao.staticserver.config.Config;
import io.netty.bootstrap.ServerBootstrap;

public class StaticServer {

    public static StaticServer getInstance() {
        return InternalClass.getInstance();
    }

    private static class InternalClass {
        private static StaticServer staticServer = new StaticServer();

        public static StaticServer getInstance() {
            return staticServer;
        }
    }

    private Config config;

    private ServerBootstrap bootstrap;

    private StaticServer() {
        config = Config.getInstance();
        bootstrap = new ServerBootstrap();
    }

    public void start() {

    }
}
