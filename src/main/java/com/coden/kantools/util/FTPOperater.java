package com.coden.kantools.util;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FTPOperater {

    private String ftpIp;
    private Integer ftpPort;
    private String ftpUser;
    private String ftpPass;
    private FTPClient ftpClient;

    private boolean connectServer(String ip, int port, String user, String pwd) {
        ftpClient = new FTPClient();
        Boolean isSuccess = false;
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean upload = true;
        FileInputStream fileInputStream = null;
        //connect to ftpServer
        if (connectServer(ftpIp, ftpPort, ftpUser, ftpPass)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem : fileList
                ) {
                    fileInputStream = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fileInputStream);
                }
            } catch (IOException e) {
                upload = false;
            } finally {
                fileInputStream.close();
                ftpClient.disconnect();
            }
        }
        return upload;
    }

    public boolean uploadToFtp(String remotePath, String fileName, File file) throws IOException {
        boolean upload = true;
        FileInputStream fileInputStream = null;
        //connect to ftpServer
        if (connectServer(ftpIp, ftpPort, ftpUser, ftpPass)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                //上传文件 参数：上传后的文件名，输入流
                fileInputStream = new FileInputStream(file);
                upload = ftpClient.storeFile(fileName, fileInputStream);
            } catch (IOException e) {
                upload = false;
            } finally {
                fileInputStream.close();
                ftpClient.disconnect();
            }
        }
        return upload;
    }
}
