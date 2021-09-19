package com.coden.kantools.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static Properties getProerties(String path) {
        Properties systemProperties = new Properties();
        File file = new File(path);
        InputStream in = null;

        try {
            in = new FileInputStream(file);
            systemProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return systemProperties;
    }

    public static Properties getProerties(ClassLoader classLoader) {
        Properties systemProperties = new Properties();
        InputStream in = classLoader.getResourceAsStream("system.properties");

        try {
            systemProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
////                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return systemProperties;
    }

    public static Properties getProerties(Class<?> clazz) {
        Properties systemProperties = new Properties();
        InputStream in = clazz.getResourceAsStream("system.properties");

        try {
            systemProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return systemProperties;
    }
}
