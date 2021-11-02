package com.coden.kantools.controller;


import com.coden.kantools.service.ToolsService;
import com.coden.kantools.service.jmx.MyDefaultGenerator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping(value = "/api/tools", produces = "application/json;charset=UTF-8")
public class ToolsController {

    @Autowired
    ToolsService toolsService;

    @Autowired
    MyDefaultGenerator myDefaultGenerator;


//    @PostMapping(value = "/createPhoneNumber")
//    public ResponseEntity<byte[]> createPhoneNumber(@RequestBody PhoneNumberRequest phoneNumberRequest,) {
//        return toolsService.createPhoneNumber();
//    }

    @PostMapping("/netDownloadLocal")
    public void downloadNet(String netAddress, String path) throws IOException {
        URL url = new URL(netAddress);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(path);

        int bytesum = 0;
        int byteread;
        byte[] buffer = new byte[1024];
        while ((byteread = inputStream.read(buffer)) != -1) {
            bytesum += byteread;
            System.out.println(bytesum);
            fileOutputStream.write(buffer, 0, byteread);
        }
        fileOutputStream.close();
    }

    @RequestMapping("/netDownLoadNet")
    public void netDownLoadNet(String netAddress, String filename, boolean isOnLine, HttpServletResponse response) throws Exception {

        URL url = new URL(netAddress);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();

        response.reset();
        response.setContentType(conn.getContentType());
        if (isOnLine) {
            // 在线打开方式 文件名应该编码成UTF-8
            response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(filename, "UTF-8"));
        } else {
            //纯下载方式 文件名应该编码成UTF-8
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        }

        byte[] buffer = new byte[1024];
        int len;
        OutputStream outputStream = response.getOutputStream();
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
    }

    @RequestMapping("/downloadLocal")
    public void downloadLocal(String path, HttpServletResponse response) throws IOException {
// 读到流中
        InputStream inputStream = new FileInputStream(path);// 文件的存放路径
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = new File(path).getName();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
//从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }


    @GetMapping("/outputJMX")
    public void generateJMX(String path, HttpServletResponse response) throws Exception {

        System.out.println(path);

        if (path.indexOf("//v2/api-docs") < 0) {
            path = path + "//v2/api-docs";
        }

        // 读到流中
        InputStream inputStream = myDefaultGenerator.generate(path);

        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("", "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();

//        byte[] b = new byte[1024];
//        int len;
//        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
//        while ((len = inputStream.read(b)) > 0) {
//            System.out.println(b);
//            outputStream.write(b, 0, len);
//        }
        IOUtils.copy(inputStream, outputStream);
        inputStream.close();
    }


    @GetMapping("/downloadJMX")
    public ResponseEntity<byte[]> downloadJMX(String path, HttpServletResponse response) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode("", "UTF-8"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        InputStream inputStream = null;
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), headers, HttpStatus.CREATED);
    }

    @GetMapping("/generateJMX")
    public void generateJMXToSystem(String path) {

        try {
            myDefaultGenerator.generate(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/customParameter")
    public void customParameter(String strJson) {

        System.out.println(toolsService.getSwaggerJson1(strJson));
    }


    @GetMapping("/genPhoneNumber")
    private ResponseEntity<byte[]> genPhoneNumber(int dataLength, boolean isUnrepeat, String dataForm) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode("", "UTF-8"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        ArrayList<String> phoneNumberList = toolsService.createPhoneNumber(dataLength, isUnrepeat);
        byte[] result = new byte[phoneNumberList.size()];
        return new ResponseEntity<byte[]>(result, headers, HttpStatus.CREATED);
    }

    @PostMapping("/geFileMD5")
    public ArrayList geFileMD5(MultipartFile[] files) throws Exception {
        ArrayList MD5Result = new ArrayList();
        MessageDigest mMessageDigest = MessageDigest.getInstance("MD5");
        for (MultipartFile multipartFile : files) {
            InputStream fis = multipartFile.getInputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) > 0) {
                mMessageDigest.update(buffer, 0, length);
            }
            MD5Result.add(new BigInteger(1, mMessageDigest.digest()).toString(16));
            fis.close();
        }
        return MD5Result;
    }


}
