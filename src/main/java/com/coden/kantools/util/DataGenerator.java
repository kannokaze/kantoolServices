package com.coden.kantools.util;

import java.util.UUID;

public class DataGenerator {


    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
