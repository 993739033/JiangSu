package com.wyw.jiangsu.bean.upload;

import com.wyw.jiangsu.bean.local.CheckContentEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyw on 2017/6/21.
 * 日常监管上传
 */

public class UploadCommonSupervisionBean implements Serializable{
    //企业名称
    private String FName;
    //企业名称ID
    private String Glid;
    //地址
    private String FCityAdd;
    //法定代表人/负责人
    private String FLegalPerson;
    //电话
    private String FPhone;
    //养殖动物种类
    private String FCategory;
    //存栏动物数量
    private String clsl;
    //检查处理意见
    private String jcclyj;
    //监督检查人员姓名一
    private String jcryxmyi;
    //所在单位一
    private String zfzhyi;
    //监督检查人员姓名二
    private String jcryxmer;
    //所在单位二
    private String zfzher;
    //检查日期
    private String jcdate;
    //检查内容
    private List<CheckContentEntity> checkItems;
    private List<SignPicNames> signPicNames;
    public static class SignPicNames implements  Serializable{
        //签字图片名字
        private String Qz;

        public String getQz() {
            return Qz;
        }

        public void setQz(String qz) {
            Qz = qz;
        }
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getGlid() {
        return Glid;
    }

    public void setGlid(String glid) {
        Glid = glid;
    }

    public String getFCityAdd() {
        return FCityAdd;
    }

    public void setFCityAdd(String FCityAdd) {
        this.FCityAdd = FCityAdd;
    }

    public String getFLegalPerson() {
        return FLegalPerson;
    }

    public void setFLegalPerson(String FLegalPerson) {
        this.FLegalPerson = FLegalPerson;
    }

    public String getFPhone() {
        return FPhone;
    }

    public void setFPhone(String FPhone) {
        this.FPhone = FPhone;
    }

    public String getFCategory() {
        return FCategory;
    }

    public void setFCategory(String FCategory) {
        this.FCategory = FCategory;
    }

    public String getClsl() {
        return clsl;
    }

    public void setClsl(String clsl) {
        this.clsl = clsl;
    }

    public String getJcclyj() {
        return jcclyj;
    }

    public void setJcclyj(String jcclyj) {
        this.jcclyj = jcclyj;
    }

    public String getJcryxmyi() {
        return jcryxmyi;
    }

    public void setJcryxmyi(String jcryxmyi) {
        this.jcryxmyi = jcryxmyi;
    }

    public String getZfzhyi() {
        return zfzhyi;
    }

    public void setZfzhyi(String zfzhyi) {
        this.zfzhyi = zfzhyi;
    }

    public String getJcryxmer() {
        return jcryxmer;
    }

    public void setJcryxmer(String jcryxmer) {
        this.jcryxmer = jcryxmer;
    }

    public String getZfzher() {
        return zfzher;
    }

    public void setZfzher(String zfzher) {
        this.zfzher = zfzher;
    }

    public String getJcdate() {
        return jcdate;
    }

    public void setJcdate(String jcdate) {
        this.jcdate = jcdate;
    }

    public List<CheckContentEntity> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<CheckContentEntity> checkItems) {
        this.checkItems = checkItems;
    }

    public List<SignPicNames> getSignPicNames() {
        return signPicNames;
    }

    public void setSignPicNames(List<SignPicNames> signPicNames) {
        this.signPicNames = signPicNames;
    }

    @Override
    public String toString() {
        return "UploadCommonSupervisionBean{" +
                "FName='" + FName + '\'' +
                ", Glid='" + Glid + '\'' +
                ", FCityAdd='" + FCityAdd + '\'' +
                ", FLegalPerson='" + FLegalPerson + '\'' +
                ", FPhone='" + FPhone + '\'' +
                ", FCategory='" + FCategory + '\'' +
                ", clsl='" + clsl + '\'' +
                ", jcclyj='" + jcclyj + '\'' +
                ", jcryxmyi='" + jcryxmyi + '\'' +
                ", zfzhyi='" + zfzhyi + '\'' +
                ", jcryxmer='" + jcryxmer + '\'' +
                ", zfzher='" + zfzher + '\'' +
                ", jcdate='" + jcdate + '\'' +
                ", checkItems=" + checkItems +
                ", signPicNames=" + signPicNames +
                '}';
    }
}
