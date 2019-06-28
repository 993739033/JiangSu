package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class WHHChuKuUploadBean {

    private int FZcdGlid;       //收集点关联ID

    private String FZcdName;    //收集点名称

    private String FckdNumber;    //出库单流水号

    private String FckDate;    //出库日期

    private String FWhhGlid;    //无害化处理中心关联id

    private String FWhhName;    //无害化处理中心

    private String FZcddz;    //收集点地址

    private List<WHHChuKuUploadBean.DataList1Bean> dataList;

    public int getFZcdGlid() {
        return FZcdGlid;
    }

    public void setFZcdGlid(int FZcdGlid) {
        this.FZcdGlid = FZcdGlid;
    }

    public String getFZcdName() {
        return FZcdName;
    }

    public void setFZcdName(String FZcdName) {
        this.FZcdName = FZcdName;
    }

    public String getFckdNumber() {
        return FckdNumber;
    }

    public void setFckdNumber(String fckdNumber) {
        FckdNumber = fckdNumber;
    }

    public String getFckDate() {
        return FckDate;
    }

    public void setFckDate(String fckDate) {
        FckDate = fckDate;
    }

    public String getFWhhGlid() {
        return FWhhGlid;
    }

    public void setFWhhGlid(String FWhhGlid) {
        this.FWhhGlid = FWhhGlid;
    }

    public String getFWhhName() {
        return FWhhName;
    }

    public void setFWhhName(String FWhhName) {
        this.FWhhName = FWhhName;
    }

    public String getFZcddz() {
        return FZcddz;
    }

    public void setFZcddz(String FZcddz) {
        this.FZcddz = FZcddz;
    }

    public List<WHHChuKuUploadBean.DataList1Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<WHHChuKuUploadBean.DataList1Bean> dataList) {
        this.dataList = dataList;
    }

    public static class DataList1Bean {

        private String FBsdwType;       //病死动物种类
        private String FBsdwNum;        //病死动物数量
        private String FBsdwWeight;     //病死动物重量

        public String getFBsdwType() {
            return FBsdwType;
        }

        public void setFBsdwType(String FBsdwType) {
            this.FBsdwType = FBsdwType;
        }

        public String getFBsdwNum() {
            return FBsdwNum;
        }

        public void setFBsdwNum(String FBsdwNum) {
            this.FBsdwNum = FBsdwNum;
        }

        public String getFBsdwWeight() {
            return FBsdwWeight;
        }

        public void setFBsdwWeight(String FBsdwWeight) {
            this.FBsdwWeight = FBsdwWeight;
        }
    }

}
