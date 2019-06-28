package com.wyw.jiangsu.bean.local;

import java.io.Serializable;

import java.util.List;

/**
 * Created by wyw on 2016/9/8.
 * 检查内容listview的bean
 */
public class CheckContentEntity implements Serializable {


    //检查内容
    private String Name = "无";
    //检查结论 能 是 两种文字
    private String JL;
    //不符合情况说明
    private String QK;
    //图片名字
    private String Tp;
    //不符合情况选择说明
    private String Cs;
    private String rbCheckYes;
    private String rbCheckNo;

    private List<DataEntity> list;

    public List<DataEntity> getList() {
        return list;
    }

    public void setList(List<DataEntity> list) {
        this.list = list;
    }

    public static class DataEntity implements Serializable{
        //不符合情况的条件
        private String XZ;
        //是否被点击
        private boolean Cheek = false;

        public boolean isCheek() {
            return Cheek;
        }

        public void setCheek(boolean cheek) {
            Cheek = cheek;
        }


        public String getXZ() {
            return XZ;
        }

        public void setXZ(String XZ) {
            this.XZ = XZ;
        }
    }

    public String getCs() {
        return Cs;
    }

    public void setCs(String cs) {
        Cs = cs;
    }

    public String getJL() {
        return JL;
    }

    public String getRbCheckYes() {
        return rbCheckYes;
    }

    public CheckContentEntity setRbCheckYes(String rbCheckYes) {
        this.rbCheckYes = rbCheckYes;
        return this;
    }

    public String getRbCheckNo() {
        return rbCheckNo;
    }

    public CheckContentEntity setRbCheckNo(String rbCheckNo) {
        this.rbCheckNo = rbCheckNo;
        return this;
    }

    public String getName() {
        return Name;
    }

    public CheckContentEntity setName(String name) {
        this.Name = name;
        return this;
    }

    public CheckContentEntity setJL(String JL) {
        this.JL = JL;
        return this;
    }


    public String getQK() {
        return QK;
    }

    public CheckContentEntity setQK(String QK) {
        this.QK = QK;
        return this;
    }

    public String getTp() {
        return Tp;
    }

    public CheckContentEntity setTp(String tp) {
        this.Tp = tp;
        return this;
    }
}
