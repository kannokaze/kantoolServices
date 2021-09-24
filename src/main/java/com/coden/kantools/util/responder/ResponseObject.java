package com.coden.kantools.util.responder;

import java.util.Date;

public class ResponseObject {
    private static String code;
    private static String msg;
    private Long time = new Date().getTime();
    private Object data = new data();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    class data {
        private int pageIndex;
        private int pageSize;
        private int dataSize;
        private int dataList;

        public int getDataList() {
            return dataList;
        }

        public void setDataList(int dataList) {
            this.dataList = dataList;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataSize() {
            return dataSize;
        }

        public void setDataSize(int dataSize) {
            this.dataSize = dataSize;
        }

    }
}