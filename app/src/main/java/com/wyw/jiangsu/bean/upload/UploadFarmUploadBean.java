package com.wyw.jiangsu.bean.upload;

import java.io.Serializable;

/**
 * Created by wyw on 2016/12/27.
 * 养殖场申报的实体类
 */

public class UploadFarmUploadBean implements Serializable{
    // 畜主姓名ID
    private String Glid;
    //联系电话
    private String lxdh;
    // 养殖场类型
    private String Fyzclx;
    // 畜主姓名
    private String Fxzxm;
    // 现存栏量
    private String Fxcll;
    // 申报日期
    private String Fsbrq;
    // 身份证号
    private String Fsfzh ;
    //一卡通号
    private String fykth;
    // 病死动物种类
    private String Fbsdwzl;
    // 死亡数
    private String Fsws;
    // 参保数
    private String Fcbs;
    //// 耳标号
    private String QDWErBiaoHao;
    //// 疑似死亡原因
    private String Fysswyy;
    // 非正常死亡
    private String Fsiwh;
    // 处理方式
    private String Fclfs;
    // 签名
    private String Fqm;
    //地址
    private String Fxxdz;
    //// 单位
    private String Fdw1;

    public String getFStId() {
        return FStId;
    }

    public void setFStId(String FStId) {
        this.FStId = FStId;
    }

    ///
    private String FStId;
    //传图片名字
    private String len;
    //姓名
    private String xm;
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getGlid() {
        return Glid;
    }

    public void setGlid(String glid) {
        Glid = glid;
    }

    public String getFxzxm() {
        return Fxzxm;
    }

    public void setFxzxm(String fxzxm) {
        Fxzxm = fxzxm;
    }

    public String getFyzclx() {
        return Fyzclx;
    }

    public void setFyzclx(String fyzclx) {
        Fyzclx = fyzclx;
    }

    public String getFxcll() {
        return Fxcll;
    }

    public void setFxcll(String fxcll) {
        Fxcll = fxcll;
    }

    public String getFsbrq() {
        return Fsbrq;
    }

    public void setFsbrq(String fsbrq) {
        Fsbrq = fsbrq;
    }

    public String getFbsdwzl() {
        return Fbsdwzl;
    }

    public void setFbsdwzl(String fbsdwzl) {
        Fbsdwzl = fbsdwzl;
    }

    public String getFsws() {
        return Fsws;
    }

    public void setFsws(String fsws) {
        Fsws = fsws;
    }

    public String getFcbs() {
        return Fcbs;
    }

    public void setFcbs(String fcbs) {
        Fcbs = fcbs;
    }

    public String getQDWErBiaoHao() {
        return QDWErBiaoHao;
    }

    public void setQDWErBiaoHao(String QDWErBiaoHao) {
        this.QDWErBiaoHao = QDWErBiaoHao;
    }

    public String getFysswyy() {
        return Fysswyy;
    }

    public void setFysswyy(String fysswyy) {
        Fysswyy = fysswyy;
    }

    public String getFsiwh() {
        return Fsiwh;
    }

    public void setFsiwh(String fsiwh) {
        Fsiwh = fsiwh;
    }

    public String getFclfs() {
        return Fclfs;
    }

    public void setFclfs(String fclfs) {
        Fclfs = fclfs;
    }

    public String getFqm() {
        return Fqm;
    }

    public void setFqm(String fqm) {
        Fqm = fqm;
    }

    public String getFxxdz() {
        return Fxxdz;
    }

    public void setFxxdz(String fxxdz) {
        Fxxdz = fxxdz;
    }

    public String getFdw1() {
        return Fdw1;
    }

    public void setFdw1(String fdw1) {
        Fdw1 = fdw1;
    }

    public String getFsfzh() {
        return Fsfzh;
    }

    public void setFsfzh(String fsfzh) {
        Fsfzh = fsfzh;
    }

    public String getFykth() {
        return fykth;
    }

    public void setFykth(String fykth) {
        this.fykth = fykth;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return "UploadFarmUploadBean{" +
                "Glid='" + Glid + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", Fyzclx='" + Fyzclx + '\'' +
                ", Fxzxm='" + Fxzxm + '\'' +
                ", Fxcll='" + Fxcll + '\'' +
                ", Fsbrq='" + Fsbrq + '\'' +
                ", Fsfzh='" + Fsfzh + '\'' +
                ", fykth='" + fykth + '\'' +
                ", Fbsdwzl='" + Fbsdwzl + '\'' +
                ", Fsws='" + Fsws + '\'' +
                ", Fcbs='" + Fcbs + '\'' +
                ", QDWErBiaoHao='" + QDWErBiaoHao + '\'' +
                ", Fysswyy='" + Fysswyy + '\'' +
                ", Fsiwh='" + Fsiwh + '\'' +
                ", Fclfs='" + Fclfs + '\'' +
                ", Fqm='" + Fqm + '\'' +
                ", Fxxdz='" + Fxxdz + '\'' +
                ", Fdw1='" + Fdw1 + '\'' +
                ", FStId='" + FStId + '\'' +
                ", len='" + len + '\'' +
                ", xm='" + xm + '\'' +
                '}';
    }
}
