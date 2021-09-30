package com.coden.kantools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
public class helloController {

    @RequestMapping("/hello")
    String hellos() {
        return "hello word!";
    }

    @PostMapping("/upload1")
    public String upload1(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/Users/itinypocket/workspace/temp/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }


    @PostMapping("/upload2")
    //@RequestMapping(value="/upload2.do", method = RequestMethod.POST)
    //上传的文件会转换成MultipartFile对象，file名字对应html中上传控件的name
    public String upload2(MultipartFile[] files) throws IOException {
        if (files.length == 0) {
            return "请选择要上传的文件";
        }
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                return "文件上传失败";
            }

            byte[] fileBytes = multipartFile.getBytes();
            String filePath = "C:\\Users\\Administrator\\Desktop\\images\\";
            //取得当前上传文件的文件名称
            String originalFilename = multipartFile.getOriginalFilename();
            //生成文件名
            String fileName = UUID.randomUUID() + "&" + originalFilename;
//            FileUtils.uploadFile(fileBytes, filePath, fileName);
        }

        return "文件上传完毕";
    }


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


}
