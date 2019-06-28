package com.wyw.jiangsu.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/3/23.
 */
@Entity
public class CodeXD {
    //public class CodeXD extends RealmObject {
//    @PrimaryKey\
    @Id
    private long id;
    private String ACode;
    private String cName;


    @Generated(hash = 1979076494)
    public CodeXD(long id, String ACode, String cName) {
        this.id = id;
        this.ACode = ACode;
        this.cName = cName;
    }

    @Generated(hash = 673389644)
    public CodeXD() {
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getACode() {
        return ACode;
    }

    public void setACode(String ACode) {
        this.ACode = ACode;
    }

    @Override
    public String toString() {
        return "CodeXD{" +
                "ACode" + ACode +
                ", cName'" + cName + "\'" +
                ", id'" + id + "\'" +
                '}';
    }

    public String getCName() {
        return this.cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

}
