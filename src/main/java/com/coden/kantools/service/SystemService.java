package com.coden.kantools.service;

import com.coden.kantools.mapper.kurumi_mail_logMapper;
import com.coden.kantools.model.kurumi_mail_log;
import com.coden.kantools.util.DataOperater;
import com.coden.kantools.util.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SystemService {

    @Autowired
    kurumi_mail_logMapper kurumiMailLogMapper;

    public boolean sendSystemMail(ArrayList mailList, HashMap context) {

        if (new SendEmailUtil(mailList, context).send()) {
            List list = new ArrayList<kurumi_mail_log>();
            kurumi_mail_log kml = null;
            for (Object mail : mailList) {
                context.put("email", mail);
                kml = DataOperater.hashMapToBean(kurumi_mail_log.class, context);
                list.add(kml);
                kurumiMailLogMapper.insert(kml);
            }
            return true;
        }
        return false;
    }


    public List<HashMap> getSysMailList(kurumi_mail_log kml) {

        List<kurumi_mail_log> list = kurumiMailLogMapper.selectByAny(kml);
        if (list != null || !list.isEmpty()) {
            return DataOperater.beanListToHashMapList(list);
        }
        return null;
    }

}
