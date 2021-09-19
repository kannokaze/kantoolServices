package com.coden.kantools.bean;

import java.io.Serializable;

public class MailContext implements Serializable {
    private String system;
    private String logoPath;
    private String mailType;
    private String username;
    private String content;
    private String title;
    private String mark;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "MailContext{" +
                "system='" + system + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", mailType='" + mailType + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
