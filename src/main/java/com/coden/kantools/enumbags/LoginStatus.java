package com.coden.kantools.enumbags;

public enum LoginStatus {
    Success(0, "0000"),
    error(-1, "-1");

    public static String KAPTCHE_IS_NULL = "验证码为空";
    public static String All_IS_NULL = "请填写登录信息";


    LoginStatus(int i, String s) {

    }
}
