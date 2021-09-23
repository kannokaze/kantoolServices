package com.coden.kantools.controller;

import com.coden.kantools.bean.PhoneNumberRequest;
import com.coden.kantools.service.ToolsService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value = "/api/tools", produces = "application/json;charset=UTF-8")
public class ToolsController {

    @Autowired
    ToolsService toolsService;


    @PostMapping(value = "/createPhoneNumber")
    public ResponseEntity<byte[]> createPhoneNumber(@RequestBody PhoneNumberRequest phoneNumberRequest) {
        return toolsService.createPhoneNumber();
    }

}
