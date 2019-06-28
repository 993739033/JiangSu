package com.wyw.jiangsu.bean.upload;

/**
 * Created by wyw on 2016/12/30.
 * 病死动物入库
 */

public class UploadSickDeadAnimBean {
    /// 病死动物入库
    /// 图片数组
    private String len;

    /// 关联ID
    private String Glid;

    /// 重量
    private double ZSL;

    //官方兽医核定数量
    private int HDSL;

    //耳标号回收确认
    private String EBHHS;

    //入库数量
    private int RKSL;

   //姓名
    private String xm;

    //uuid
    private String uuid;

    //
    private String lens;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLens() {
        return lens;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public int getHDSL() {
        return HDSL;
    }

    public void setHDSL(int HDSL) {
        this.HDSL = HDSL;
    }

    public String getEBHHS() {
        return EBHHS;
    }

    public void setEBHHS(String EBHHS) {
        this.EBHHS = EBHHS;
    }

    public int getRKSL() {
        return RKSL;
    }

    public void setRKSL(int RKSL) {
        this.RKSL = RKSL;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getGlid() {
        return Glid;
    }

    public void setGlid(String glid) {
        Glid = glid;
    }

    public double getZSL() {
        return ZSL;
    }

    public void setZSL(double ZSL) {
        this.ZSL = ZSL;
    }

    @Override
    public String toString() {
        return "UploadSickDeadAnimBean{" +
                "len='" + len + '\'' +
                ", Glid='" + Glid + '\'' +
                ", ZSL=" + ZSL +
                ", HDSL=" + HDSL +
                ", EBHHS='" + EBHHS + '\'' +
                ", RKSL=" + RKSL +
                ", xm='" + xm + '\'' +
                '}';
    }
}
