package com.coden.kantools.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class SendEmailUtil {
    private ArrayList<String> emailList;
    private String email;
    private String system;
    private String logoPath;
    private String mailType;
    private String username;
    private String content;
    private String title;
    private String mark;
    private String time;


    public SendEmailUtil(ArrayList<String> emailList, HashMap<String, String> mailText) {
        this.emailList = emailList;
        this.system = mailText.get("system");
        this.logoPath = mailText.get("logoPath");
        this.mailType = mailText.get("mailType");
        this.title = mailText.get("title");
        this.username = mailText.get("username");
        this.content = mailText.get("content");
        this.mark = mailText.get("mark");
        this.time = getNowDate(new Date());
    }

    public SendEmailUtil(String email, HashMap<String, String> mailText) {
        this.email = email;
        this.system = mailText.get("system");
        this.logoPath = mailText.get("logoPath");
        this.mailType = mailText.get("mailType");
        this.title = mailText.get("title");
        this.username = mailText.get("username");
        this.content = mailText.get("content");
        this.mark = mailText.get("mark");
        this.time = getNowDate(new Date());
    }


    public static String getNowDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    private String buildContent() {
        String fileName = "C:\\Users\\admin\\Desktop\\新建文件夹\\kantools\\src\\main\\java\\com\\coden\\kantools\\util\\post.html";
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
//            inputStream = SendEmailUtil.class.getClassLoader().getResourceAsStream(fileName);
            inputStream = new FileInputStream(new File(fileName));
            fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String htmlText = MessageFormat.format(buffer.toString(), system, logoPath, mailType, title, username, content, mark,
                time, PropertiesReader.getProerties("C:\\Users\\admin\\Desktop\\新建文件夹\\kantools\\src\\main\\java\\system.properties").getProperty("system.protocol") + PropertiesReader.getProerties("C:\\Users\\admin\\Desktop\\新建文件夹\\kantools\\src\\main\\java\\system.properties").getProperty("system.host"));
        return htmlText;
    }


    public boolean send() {

        String from = "1131429439@qq.com";
        String host = "smtp.qq.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");

        boolean flat = true;
        try {

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("1131429439@qq.com", "oalwdgceseyobaea");
                }
            });

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from, system));
            message.setSubject(title);
            message.setHeader("Content-Type", "text/html; charset=utf-8");
            message.setContent(buildContent(), "text/html;charset=utf-8");
            if (email == null) {
                for (String email : emailList) {
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                    System.out.println(email);
                    Transport.send(message);
                }
            } else {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                Transport.send(message);
            }

        } catch (Exception e) {
            flat = false;
            e.printStackTrace();
        } finally {

        }
        return flat;
    }

}