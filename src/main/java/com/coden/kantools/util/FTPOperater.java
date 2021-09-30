package com.coden.kantools.util;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Map;

//@Repository
public class FTPOperater {

    private String ftpIp;
    private Integer ftpPort;
    private String ftpUser;
    private String ftpPass;
    private FTPClient ftpClient;

    public FTPOperater(String ftpIp, Integer ftpPort, String ftpUser, String ftpPass) {
        this.ftpIp = ftpIp;
        this.ftpPort = ftpPort;
        this.ftpUser = ftpUser;
        this.ftpPass = ftpPass;
    }

    public boolean connectServer(String ip, int port, String user, String pwd) {
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

    public boolean uploadFileList(String remotePath, Map<String, InputStream> fileList) throws IOException {
        boolean upload = true;
        //connect to ftpServer
        if (connectServer(ftpIp, ftpPort, ftpUser, ftpPass)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024 * 1024 * 1024 * 1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (String fileItem : fileList.keySet()) {
                    System.out.println(ftpClient.storeFile(fileItem, fileList.get(fileItem)));
                    fileList.get(fileItem).close();
                }
            } catch (IOException e) {
                upload = false;
            } finally {
                ftpClient.disconnect();
            }
        } else {
            upload = false;
        }
        return upload;
    }

    public boolean uploadFile(String remotePath, String fileName, InputStream inputStream) throws IOException {
        boolean upload = true;

        //connect to ftpServer
        if (connectServer(ftpIp, ftpPort, ftpUser, ftpPass)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                //上传文件 参数：上传后的文件名，输入流
                upload = ftpClient.storeFile(fileName, inputStream);

            } catch (IOException e) {
                upload = false;
            } finally {
                inputStream.close();
                ftpClient.disconnect();
            }
        } else {
            upload = false;
        }
        return upload;
    }


    public InputStream downloadSingle(String fileName) {
        InputStream is = null;
        if (connectServer(ftpIp, ftpPort, ftpUser, ftpPass)) {

            try {
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                is = ftpClient.retrieveFileStream(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return is;
    }

}
