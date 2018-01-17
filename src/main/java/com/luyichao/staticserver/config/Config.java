package com.luyichao.staticserver.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static Config getInstance() {
        return InternalClass.getInstance();
    }

    private static class InternalClass {
        private static Config config = new Config();

        public static Config getInstance() {
            return config;
        }

        public static Properties properties = new Properties();

        static {
            try {
                InputStream inputStream = InternalClass.class.getClassLoader().getResourceAsStream("");
                properties.load(inputStream);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private String rootPath;


    private Config() {
        rootPath = InternalClass.properties.getProperty("root");
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }



}
