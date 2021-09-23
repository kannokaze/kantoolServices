package com.coden.kantools.service;

import com.coden.kantools.bean.PhoneNumberRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ToolsService {


    public ResponseEntity<byte[]> createPhoneNumber(@RequestBody PhoneNumberRequest phoneNumberRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", file);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(null);
        return responseEntity;
    }
}
