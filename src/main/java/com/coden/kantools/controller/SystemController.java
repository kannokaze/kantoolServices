package com.coden.kantools.controller;

import com.coden.kantools.bean.MailContextRequest;
import com.coden.kantools.model.kurumi_mail_log;
import com.coden.kantools.service.SystemService;
import com.coden.kantools.util.DataOperater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/api/system", produces = "application/json;charset=UTF-8")
public class SystemController {

    @Autowired
    SystemService systemService;

    @PostMapping(value = "/sendSysMail")
    public boolean sendSystemMail(@RequestBody MailContextRequest mailContextRequest) {
        return systemService.sendSystemMail(mailContextRequest.getMailList(),
                DataOperater.bean2HashMap(mailContextRequest.getMailContext()));
    }


    @PostMapping(value = "/getSysMailList")
    public List<HashMap> getSysMailList(@RequestBody kurumi_mail_log kurumiMailLog) {
        return systemService.getSysMailList(kurumiMailLog);
    }


    @RequestMapping(value = "/setSystemMailAccount", method = RequestMethod.POST)
    public boolean SystemMailAccount(@RequestBody MailContextRequest mailContextRequest) {

        return systemService.sendSystemMail(mailContextRequest.getMailList(),
                DataOperater.bean2HashMap(mailContextRequest.getMailContext()));
    }


}
