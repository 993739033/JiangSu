package com.wyw.jiangsu.bean.upload;

/**
 * Created by wyw on 2016/12/29.
 * 监管兽医指派
 */

public class UploadAssignBean {
    /// 申报关联ID
    public String Glid;

    /// 指派ID
    public String ZPID;

    /// 指派人
    public String ZPR;

    /// 图片数组
    public String len;

    //姓名
    public String xm;
    //勘验类型
    private String FSm3;
    //移送时间
    private String FSm4;

    public String getFSm3() {
        return FSm3;
    }

    public void setFSm3(String FSm3) {
        this.FSm3 = FSm3;
    }

    public String getFSm4() {
        return FSm4;
    }

    public void setFSm4(String FSm4) {
        this.FSm4 = FSm4;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    public String getGlid() {
        return Glid;
    }

    public void setGlid(String glid) {
        Glid = glid;
    }

    public String getZPID() {
        return ZPID;
    }

    public void setZPID(String ZPID) {
        this.ZPID = ZPID;
    }

    public String getZPR() {
        return ZPR;
    }

    public void setZPR(String ZPR) {
        this.ZPR = ZPR;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }
}
