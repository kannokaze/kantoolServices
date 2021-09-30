package com.coden.kantools.util.tools;

import org.springframework.util.DigestUtils;

public class EncodeTools {

    public static String md5Encode(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }


}
