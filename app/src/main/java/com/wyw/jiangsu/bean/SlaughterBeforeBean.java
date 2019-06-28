package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by zhyzh on 2017/4/12.
 */

public class SlaughterBeforeBean {
    private List<DataList> dataList ;

    public List<DataList> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataList> dataList) {
        this.dataList = dataList;
    }

    public static class DataList {
        private String title;
        private String position;
        private String type;
        private String isSomething;//是否正常
        private String  quanNo;//圈号
        private String number;//数量

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuanNo() {
            return quanNo;
        }

        public void setQuanNo(String quanNo) {
            this.quanNo = quanNo;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getIsSomething() {
            return isSomething;
        }

        public void setIsSomething(String isSomething) {
            this.isSomething = isSomething;
        }
    }
}
