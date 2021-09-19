package com.coden.kantools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class hello {

    @RequestMapping("/hello")
    String hellos() {
        return "hello word!";
    }
}
