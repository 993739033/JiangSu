package com.wyw.jiangsu.bean.upload;

/**
 * Created by wyw on 2017/1/3.
 * 无害化处理厂集中处理
 */

public class UploadHarmlessConcentrateProcess {
    
    /// 图片数组
    private String len;
    /// 关联主ID
    private String Glid;
    //选个是 过来的id
    private String TGlid;
    /// 关联任务ID集合
    private String FGlid;
    /// 动物种类数组
    private String dwzl;
    /// 死亡数数组
    private String sws;
    /// 参保数数组
    private String cbs;
    /// 重量数组
    private String zls;
    /// 合计
    private String HJ;
    /// 无害化处理中心
    private String WHHCLZX;
    /// 开始时间
    private String CLRQ;
    /// 处理人
    private String CLR;
    /// 处理方式
    private String CLFSS;
    /// 结束时间
    private String SJ;
    /// 油脂
    private String YZ;
    /// 残渣
    private String CZ;
    /// 液体产物
    private String YTCW;

    public String getTGlid() {
        return TGlid;
    }

    public void setTGlid(String TGlid) {
        this.TGlid = TGlid;
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

    public String getHJ() {
        return HJ;
    }

    public void setHJ(String HJ) {
        this.HJ = HJ;
    }

    public String getWHHCLZX() {
        return WHHCLZX;
    }

    public void setWHHCLZX(String WHHCLZX) {
        this.WHHCLZX = WHHCLZX;
    }

    public String getCLRQ() {
        return CLRQ;
    }

    public void setCLRQ(String CLRQ) {
        this.CLRQ = CLRQ;
    }

    public String getCLR() {
        return CLR;
    }

    public void setCLR(String CLR) {
        this.CLR = CLR;
    }

    public String getCLFSS() {
        return CLFSS;
    }

    public void setCLFSS(String CLFSS) {
        this.CLFSS = CLFSS;
    }


    public String getSJ() {
        return SJ;
    }

    public void setSJ(String SJ) {
        this.SJ = SJ;
    }

    public String getYZ() {
        return YZ;
    }

    public void setYZ(String YZ) {
        this.YZ = YZ;
    }

    public String getCZ() {
        return CZ;
    }

    public void setCZ(String CZ) {
        this.CZ = CZ;
    }

    public String getYTCW() {
        return YTCW;
    }

    public void setYTCW(String YTCW) {
        this.YTCW = YTCW;
    }

    @Override
    public String toString() {
        return "UploadHarmlessConcentrateProcess{" +
                "len='" + len + '\'' +
                ", Glid='" + Glid + '\'' +
                ", TGlid='" + TGlid + '\'' +
                ", FGlid='" + FGlid + '\'' +
                ", dwzl='" + dwzl + '\'' +
                ", sws='" + sws + '\'' +
                ", cbs='" + cbs + '\'' +
                ", zls='" + zls + '\'' +
                ", HJ='" + HJ + '\'' +
                ", WHHCLZX='" + WHHCLZX + '\'' +
                ", CLRQ='" + CLRQ + '\'' +
                ", CLR='" + CLR + '\'' +
                ", CLFSS='" + CLFSS + '\'' +
                ", SJ='" + SJ + '\'' +
                ", YZ='" + YZ + '\'' +
                ", CZ='" + CZ + '\'' +
                ", YTCW='" + YTCW + '\'' +
                '}';
    }
}
