package com.luyichao.staticserver.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static Config getInstance() {
        return InternalClass.getInstance();
    }

    private static class InternalClass {
        public static Properties properties = new Properties();

        static {
            try {
                InputStream inputStream = InternalClass.class.getClassLoader().getResourceAsStream("application.properties");
                properties.load(inputStream);
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        private static Config config = new Config();

        public static Config getInstance() {
            return config;
        }

    }

    private String rootPath;

    private String port;

    private String bossGroupEventLoopNumber;

    private String workerGroupEventLoopNumber;


    private Config() {
        Properties properties = InternalClass.properties;
        rootPath = properties.getProperty("root");
        port = properties.getProperty("port");
        bossGroupEventLoopNumber = properties.getProperty("bossGroupEventLoopNumber");
        workerGroupEventLoopNumber = properties.getProperty("workGroupEventLoopNumber");
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getBossGroupEventLoopNumber() {
        return bossGroupEventLoopNumber;
    }

    public void setBossGroupEventLoopNumber(String bossGroupEventLoopNumber) {
        this.bossGroupEventLoopNumber = bossGroupEventLoopNumber;
    }

    public String getWorkerGroupEventLoopNumber() {
        return workerGroupEventLoopNumber;
    }

    public void setWorkerGroupEventLoopNumber(String workerGroupEventLoopNumber) {
        this.workerGroupEventLoopNumber = workerGroupEventLoopNumber;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
