package com.coden.kantools.controller;

import com.coden.kantools.bean.PhoneNumberRequest;
import com.coden.kantools.service.ToolsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean createPhoneNumber(@RequestBody PhoneNumberRequest phoneNumberRequest) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData("attachment", file);
//
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
//                FileUtils.readFileToByteArray();
//
//        return responseEntity;
    }

}
