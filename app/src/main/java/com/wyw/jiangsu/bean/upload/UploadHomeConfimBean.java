package com.wyw.jiangsu.bean.upload;

import java.io.Serializable;

/**
 * Created by wyw on 2016/12/30.
 * 无害化驻场兽医确认  上传
 */

public class UploadHomeConfimBean implements Serializable{
    /// 图片数组
    private String len;

    //收集ID
    private String id;

    // 确认重量
    private String QRZL;

    /// 动物种类数组
    private String dwzl;

    /// 死亡数数组
    private String sws;

    /// 参保数数组
    private String cbs;

    /// 重量数组
    private String zls;

    // 合计
    private String HJ;


    /// 关联主ID
    private String Glid;

    /// 关联任务ID集合
    private String FGlid;

    /// 是否立即处理
    private String SFCL;

    /// 收集车辆
    private String CPH;

    /// 收集日期
    private String SJRQ;

    /// 收集人
    private String SJR;

    /// 入场日期
    private String RCRQ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQRZL() {
        return QRZL;
    }

    public void setQRZL(String QRZL) {
        this.QRZL = QRZL;
    }

    public String getLen() {
        return len;
    }

    public String getHJ() {
        return HJ;
    }

    public void setHJ(String HJ) {
        this.HJ = HJ;
    }

    public String getGlid() {
        return Glid;
    }

    public void setGlid(String glid) {
        Glid = glid;
    }

    public void setLen(String len) {
        this.len = len;
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

    public String getCbs() {
        return cbs;
    }

    public void setCbs(String cbs) {
        this.cbs = cbs;
    }

    public String getZls() {
        return zls;
    }

    public void setZls(String zls) {
        this.zls = zls;
    }


    public String getFGlid() {
        return FGlid;
    }

    public void setFGlid(String FGlid) {
        this.FGlid = FGlid;
    }

    public String getSFCL() {
        return SFCL;
    }

    public void setSFCL(String SFCL) {
        this.SFCL = SFCL;
    }

    public String getCPH() {
        return CPH;
    }

    public void setCPH(String CPH) {
        this.CPH = CPH;
    }

    public String getSJRQ() {
        return SJRQ;
    }

    public void setSJRQ(String SJRQ) {
        this.SJRQ = SJRQ;
    }

    public String getSJR() {
        return SJR;
    }

    public void setSJR(String SJR) {
        this.SJR = SJR;
    }

    public String getRCRQ() {
        return RCRQ;
    }

    public void setRCRQ(String RCRQ) {
        this.RCRQ = RCRQ;
    }
}
