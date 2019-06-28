package com.wyw.jiangsu.bean.upload;

/**
 * Created by wyw on 2016/12/30.
 * 无害化收集人员上门收集
 * APP_SJQR
 */

public class UploadMessageConfirmStorageBean {
    /// 图片数组
    private String len;

    /// 关联ID
    private String Glid;

    // 回收签封号
    private String HSQFH;
    //加施签封号
    private String JSQFH;
    //确定数量
    private int QDSL;

    // 无害化任务关联ID
    public String FGlid;
    // 动物种类
    public String dwzl;
    // 死亡数
    public String sws;
    // 重量
    public String zls;

    //车牌号
    public String CPH;

    public String getCPH() {
        return CPH;
    }

    public void setCPH(String CPH) {
        this.CPH = CPH;
    }

    public String getFGlid() {
        return FGlid;
    }

    public void setFGlid(String FGlid) {
        this.FGlid = FGlid;
    }

    public String getDwzl() {
        return dwzl;
    }

    public void setDwzl(String dwzl) {
        this.dwzl = dwzl;
    }

    public String getSws() {
        return sws;
    }

    public void setSws(String sws) {
        this.sws = sws;
    }

    public String getZls() {
        return zls;
    }

    public void setZls(String zls) {
        this.zls = zls;
    }

    public String getHSQFH() {
        return HSQFH;
    }

    public void setHSQFH(String HSQFH) {
        this.HSQFH = HSQFH;
    }

    public String getJSQFH() {
        return JSQFH;
    }

    public void setJSQFH(String JSQFH) {
        this.JSQFH = JSQFH;
    }

    public int getQDSL() {
        return QDSL;
    }

    public void setQDSL(int QDSL) {
        this.QDSL = QDSL;
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
}
