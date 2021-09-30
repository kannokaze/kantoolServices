package com.coden.kantools.service;

import com.coden.kantools.util.FTPOperater;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUpDownService {

    private FTPOperater ftpOperater = new FTPOperater("127.0.0.1", 21, "root", "123456");

    public String uploadSingleToFtp(MultipartFile file) {
        if (file.isEmpty()) {
            return "请选择要上传的文件";
        }

        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + StringUtils.substring(originalFilename, StringUtils.lastIndexOf(originalFilename, "."), originalFilename.length());

        try {
            ftpOperater.uploadFile("/b", fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }

        return "文件上传成功";
    }

    public String uploadMultiToFtp(MultipartFile[] files) {
        if (files.length == 0) {
            return "请选择要上传的文件";
        }


        Map<String, InputStream> fileList = new HashMap<String, InputStream>();
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                return "请选择上传文件";
            }

            //生成文件名
            String originalFilename = multipartFile.getOriginalFilename();
            String fileName = UUID.randomUUID() + StringUtils.substring(originalFilename, StringUtils.lastIndexOf(originalFilename, "."), originalFilename.length());

            try {
                fileList.put(new String((fileName).getBytes("UTF-8"), "iso-8859-1"), multipartFile.getInputStream());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        try {
            ftpOperater.uploadFileList("/b", fileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传完毕";
    }

    public InputStream downloadSingle(String path, String fileName) {
        return ftpOperater.downloadSingle(path, fileName);
    }

}
