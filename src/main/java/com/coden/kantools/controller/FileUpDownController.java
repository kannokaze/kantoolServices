package com.coden.kantools.controller;

import com.coden.kantools.util.FTPOperater;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping(value = "/api/fileUpDown", produces = "application/json;charset=UTF-8")
public class FileUpDownController {

    //    @Autowired
//    FTPOperater ftpOperater;
    private FTPOperater ftpOperater = new FTPOperater("127.0.0.1", 21, "root", "123456");

    @PostMapping("/upload2")
    public String upload2(MultipartFile[] files) throws IOException {
        if (files.length == 0) {
            return "请选择要上传的文件";
        }

        Map<String, InputStream> fileList = new HashMap<String, InputStream>();
        File file = null;


        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                return "文件上传失败";
            }

            //生成文件名
            String originalFilename = multipartFile.getOriginalFilename();
            String fileName = UUID.randomUUID() + StringUtils.substring(originalFilename, StringUtils.lastIndexOf(originalFilename, "."), originalFilename.length());

            fileList.put(new String((fileName).getBytes("UTF-8"), "iso-8859-1"), multipartFile.getInputStream());
        }
        System.out.println(fileList);
        ftpOperater.uploadFile("/b", fileList);
        return "文件上传完毕";
    }
}
