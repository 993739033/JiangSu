package com.wyw.jiangsu.bean;

/**
 * Created by Windows on 2017/6/21.
 */

public class AddAnjiandengjiActivityBean {


    /**
     * FSunitUstrId : 01
     * Fdjrq : 2017/6/20 16:31:33
     * Fly : 案件来源
     * Fany : 案由
     * Fajlx : 案件类型
     * uid : 2,3
     */

    private String FSunitUstrId;
    private String Fdjrq;
    private String Fly;
    private String Fany;
    private String Fajlx;
    private String uid;
    private String FSuserId;                //用户Id

    public String getFSuserId() {
        return FSuserId;
    }

    public void setFSuserId(String FSuserId) {
        this.FSuserId = FSuserId;
    }

    public String getFSunitUstrId() {
        return FSunitUstrId;
    }

    public void setFSunitUstrId(String FSunitUstrId) {
        this.FSunitUstrId = FSunitUstrId;
    }

    public String getFdjrq() {
        return Fdjrq;
    }

    public void setFdjrq(String Fdjrq) {
        this.Fdjrq = Fdjrq;
    }

    public String getFly() {
        return Fly;
    }

    public void setFly(String Fly) {
        this.Fly = Fly;
    }

    public String getFany() {
        return Fany;
    }

    public void setFany(String Fany) {
        this.Fany = Fany;
    }

    public String getFajlx() {
        return Fajlx;
    }

    public void setFajlx(String Fajlx) {
        this.Fajlx = Fajlx;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
