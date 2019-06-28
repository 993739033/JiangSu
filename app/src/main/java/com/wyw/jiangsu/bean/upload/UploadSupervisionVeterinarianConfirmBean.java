package com.wyw.jiangsu.bean.upload;

/**
 * Created by wyw on 2016/12/29.
 * 监管兽医确认
 */
@Deprecated
public class UploadSupervisionVeterinarianConfirmBean {
    //自行处理 特殊处理
    // 图片数组
    private String len; 

    /// 申报关联ID
    private String Glid;

    /// 死亡数
    private String Fsws;

    /// 参保数
    private String Fcbs;

    /// 疑似病因
    private String Fysswyy; 

    /// 以上是否符合
    private String SFFH; 

    /// 处理方式选择
    private String CLFSXZ;

    // 非正常死亡
    private String Fsiwh;

    //集中处理
    /// 移送时间
    private String YSSJ;
    
    /// 收集点ID
    private int SJID;

    /// 收集点
    private String SJD;

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

    public String getFysswyy() {
        return Fysswyy;
    }

    public void setFysswyy(String fysswyy) {
        Fysswyy = fysswyy;
    }

    public String getSFFH() {
        return SFFH;
    }

    public void setSFFH(String SFFH) {
        this.SFFH = SFFH;
    }

    public String getCLFSXZ() {
        return CLFSXZ;
    }

    public void setCLFSXZ(String CLFSXZ) {
        this.CLFSXZ = CLFSXZ;
    }

    public String getYSSJ() {
        return YSSJ;
    }

    public void setYSSJ(String YSSJ) {
        this.YSSJ = YSSJ;
    }

    public int getSJID() {
        return SJID;
    }

    public void setSJID(int SJID) {
        this.SJID = SJID;
    }

    public String getSJD() {
        return SJD;
    }

    public String getFsiwh() {
        return Fsiwh;
    }

    public void setFsiwh(String fsiwh) {
        Fsiwh = fsiwh;
    }

    public void setSJD(String SJD) {
        this.SJD = SJD;
    }
}
