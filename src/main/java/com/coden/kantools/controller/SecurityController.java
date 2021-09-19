package com.coden.kantools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/security")
public class SecurityController {

//    @RequestMapping(value = "/login", method = RequestMethod.POST)

//    public void login(@RequestBody String username, @RequestBody String password, @RequestBody String kaptcha, @RequestBody String isKeepingLogin) {
//        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//
//        if (session.getAttribute("onlineUser") != null) {
//            //如果用户已经登录了，直接跳转主页面
//        }
//
//        if (username == null || password == null || kaptcha == null) {
//            // 如果账户密码验证码任意为空,则提示参数异常
//        }
//
//        if (currentUser.isAuthenticated() == false) {
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//            try {
//                currentUser.login(token);
//                System.out.println(currentUser.getPrincipals().getRealmNames());
//                session.setAttribute("onlineUser", currentUser.getPrincipals().getPrimaryPrincipal());
//
//
//                return;
//            } catch (AuthenticationException e) {
//
//                return;
//            }
//
//        }
//
//        return;
//    }

//    public static void main(String[] args) {
//        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
////        stringStringHashMap.put("mark","aaaaa");
////        stringStringHashMap.put("content","aaaaa");
////        stringStringHashMap.put("username","aaaaa");
////        stringStringHashMap.put("title","aaaaa");
////        stringStringHashMap.put("mailType","aaaaa");
////        stringStringHashMap.put("logoPath","aaaaa");
////        stringStringHashMap.put("system","aaaaa");
////        stringStringHashMap.put("email","aaaaa");
//        ArrayList s = new ArrayList<String>();
//        s.add("1131429439@qq.com");
//
//        new SendEmailUtil(s, stringStringHashMap).send();
//    }
}
