package com.coden.kantools.controller;

import com.coden.kantools.service.ToolsService;
import com.coden.kantools.service.jmx.MyDefaultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
@ResponseBody
@RequestMapping(value = "/api/tools", produces = "application/json;charset=UTF-8")
public class ToolsController {

    @Autowired
    ToolsService toolsService;


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


    @PostMapping("/outputJMX")
    public void generateJMX(String path, HttpServletResponse response) throws Exception {
        // 读到流中
        InputStream inputStream = null;// 文件的存放路径
        MyDefaultGenerator myDefaultGenerator = new MyDefaultGenerator();

        System.out.println(path);
        // 读到流中
        inputStream = myDefaultGenerator.generate(path, "D:\\jmeter-script\\");
        System.out.println(inputStream);
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

    @PostMapping("/generateJMX")
    public void generateJMXToSystem(String path) {

    }


}
