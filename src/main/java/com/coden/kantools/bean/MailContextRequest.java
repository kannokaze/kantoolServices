package com.coden.kantools.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class MailContextRequest implements Serializable {
    private ArrayList mailList;
    private MailContext mailContext;

    @Override
    public String toString() {
        return "MailContextRequest{" +
                "mailList=" + mailList +
                ", mailContext=" + mailContext +
                '}';
    }

    public ArrayList getMailList() {
        return mailList;
    }

    public void setMailList(ArrayList mailList) {
        this.mailList = mailList;
    }

    public MailContext getMailContext() {
        return mailContext;
    }

    public void setMailContext(MailContext mailContext) {
        this.mailContext = mailContext;
    }


}
