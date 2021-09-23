package com.coden.kantools.bean;

public class PhoneNumberRequest {

    private String fileType;
    private String isDuplicate;
    private String lineSize;
    private String DataFormat;
    private int num;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getIsDuplicate() {
        return isDuplicate;
    }

    public void setIsDuplicate(String isDuplicate) {
        this.isDuplicate = isDuplicate;
    }

    public String getLineSize() {
        return lineSize;
    }

    public void setLineSize(String lineSize) {
        this.lineSize = lineSize;
    }

    public String getDataFormat() {
        return DataFormat;
    }

    public void setDataFormat(String dataFormat) {
        DataFormat = dataFormat;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
