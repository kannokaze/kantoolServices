package com.coden.kantools.util;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static String propertiesPath;

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

    public static Properties getProerties(ClassLoader classLoader, String propertiesName) {
        Properties systemProperties = new Properties();
        InputStream in = classLoader.getResourceAsStream(propertiesName);

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

    public static Properties getProerties(Class<?> clazz, String propertiesName) {
        Properties systemProperties = new Properties();
        InputStream in = clazz.getResourceAsStream(propertiesName);

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

    public static Properties getProertiesByResourcePath(String propertiesPath) {
        Properties systemProperties = new Properties();
        InputStream in = null;

        try {
            in = new ClassPathResource(propertiesPath).getInputStream();
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


    public static String getSystemProperty(String propertiesName) {
        return PropertiesReader.getProertiesByResourcePath("system.properties").getProperty(propertiesName);
    }
}
