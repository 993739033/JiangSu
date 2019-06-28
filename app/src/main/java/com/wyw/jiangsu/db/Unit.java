package com.wyw.jiangsu.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wyw on 2016/12/22.
 */

@Entity
public class Unit {
//public class Unit extends RealmObject {

//    @PrimaryKey
    @Id
    private long uId;//主键
    private String uName;//省名字
    private String uCode;//行政id
    private String uParent;//父id
    private String tId;//同主键id
    private String uStrid;
    private String uOrder;


    @Generated(hash = 808630990)
    public Unit(long uId, String uName, String uCode, String uParent, String tId,
            String uStrid, String uOrder) {
        this.uId = uId;
        this.uName = uName;
        this.uCode = uCode;
        this.uParent = uParent;
        this.tId = tId;
        this.uStrid = uStrid;
        this.uOrder = uOrder;
    }

    @Generated(hash = 1236212320)
    public Unit() {
    }


    public long getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuCode() {
        return uCode;
    }

    public void setuCode(String uCode) {
        this.uCode = uCode;
    }

    public String getuParent() {
        return uParent;
    }

    public void setuParent(String uParent) {
        this.uParent = uParent;
    }


    public String getuStrid() {
        return uStrid;
    }

    public void setuStrid(String uStrid) {
        this.uStrid = uStrid;
    }

    public String getuOrder() {
        return uOrder;
    }

    public void setuOrder(String uOrder) {
        this.uOrder = uOrder;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uCode='" + uCode + '\'' +
                ", uParent='" + uParent + '\'' +
                ", tId='" + tId + '\'' +
                ", uStrid='" + uStrid + '\'' +
                ", uOrder='" + uOrder + '\'' +
                '}';
    }

    public long getUId() {
        return this.uId;
    }

    public void setUId(long uId) {
        this.uId = uId;
    }

    public String getUName() {
        return this.uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUCode() {
        return this.uCode;
    }

    public void setUCode(String uCode) {
        this.uCode = uCode;
    }

    public String getUParent() {
        return this.uParent;
    }

    public void setUParent(String uParent) {
        this.uParent = uParent;
    }

    public String getTId() {
        return this.tId;
    }

    public void setTId(String tId) {
        this.tId = tId;
    }

    public String getUStrid() {
        return this.uStrid;
    }

    public void setUStrid(String uStrid) {
        this.uStrid = uStrid;
    }

    public String getUOrder() {
        return this.uOrder;
    }

    public void setUOrder(String uOrder) {
        this.uOrder = uOrder;
    }
}
