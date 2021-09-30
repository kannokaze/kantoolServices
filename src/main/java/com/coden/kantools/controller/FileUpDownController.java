package com.coden.kantools.controller;

import com.coden.kantools.service.FileUpDownService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Controller
@ResponseBody
@RequestMapping(value = "/api/fileUpDown", produces = "application/json;charset=UTF-8")
public class FileUpDownController {

    private static final Logger logger = LoggerFactory.getLogger(FileUpDownController.class);


    @Autowired
    FileUpDownService fileUpDownService;


    @PostMapping("/uploadSingleToFtp")
    public String uploadSingleToFtp(MultipartFile file) throws IOException {
        return fileUpDownService.uploadSingleToFtp(file);
    }

    @PostMapping("/uploadMultiToFtp")
    public String uploadMultiToFtp(MultipartFile[] files) throws IOException {
        return fileUpDownService.uploadMultiToFtp(files);
    }

    @PostMapping("/download")
    public void download(@RequestBody String path, HttpServletResponse response) throws IOException {

        // 读到流中
        InputStream inputStream = fileUpDownService.downloadSingle("/", "/a1bc70ab-478b-4b51-bd42-3f9fe5e6938f.txt");
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("", "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();

    }


    @PostMapping("/download2")
    private ResponseEntity<byte[]> outInternshipTable(@RequestBody String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode("", "UTF-8"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        InputStream inputStream = fileUpDownService.downloadSingle("/", "/f88854ee-f45d-4aa3-bda9-7ecf83ef05b4.apk");
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), headers, HttpStatus.CREATED);
    }
}
