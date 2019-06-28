package com.wyw.jiangsu.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

import java.io.Serializable;

/**
 * Created by wyw on 2016/12/22.
 */
@Entity
public class User implements Serializable {
//public class User extends RealmObject implements Serializable{
    private static final long serialVersionUID = 12423423;

    /**
     * USERID : 1
     * FSGUIDID : cea3bdc4-be3c-4d22-8b77-4233eb858acd
     * FSCTIME : /Date(1392022309333)/
     * FSUTIME : /Date(1392022309333)/
     * FSUSERID : 1
     * FSUSERNAME : 超级管理员
     * FSUNITID : 1
     * FSUNITUSTRID : 01
     * FSUNITNAME : 湖南省
     * FSENTERPRISEID : 1
     * FSENTERPRISENAME : 超级管理员单位
     * FSAUDIT : 0
     * FSREVIEWER : 0
     * FSMEMO1 : 0
     * FSMEMO2 : 0
     * FSMEMO3 : 0
     * FUQUANXIAN : admin//类型
     * FUACCOUNT : admin
     * FUPASSWORD : mnkj2016
     * FRID : 1
     * FRNAME : 超级管理员//角色
     * FUSEEID : 1
     * FUSEENAME : 超级管理员单位       11111111111111111
     * FUNAME : 超级管理员             1111111111
     * FUSEX : false
     * FUPOSITION : 通讯地址
     * FUPHONE : 电话号码             11111111
     * FUEMAIL : 906975387@qq.com
     * FUQQ : QQ号码
     * FUCODE : 430000
     * FUNUMBER : 身份证号码
     * FUPOSTCODE : 邮编
     * FUREMARK : 0
     * FUDWTYPE : xzdw
     * FSDEL : 0
     * FULOGOFF : 0
     * FSM1 : 0
     * FSM2 : 1
     * FSM3 : 0
     * FSM4 : 0
     * FSM5 : 0
     * SFRID : 0
     * SFRNAME : 0
     * CODE : 430000
     * ZT :  0   打印类型
     */
//    @PrimaryKey
    @Id
    private long USERID;
    private String FSGUIDID;
    private String FSCTIME;
    private String FSUTIME;
    private int FSUSERID;
    private String FSUSERNAME;
    private int FSUNITID;
    private String FSUNITUSTRID;
    private String FSUNITNAME;
    private int FSENTERPRISEID;
    private String FSENTERPRISENAME;
    private String FSAUDIT;
    private String FSREVIEWER;
    private String FSMEMO1;
    private String FSMEMO2;
    private String FSMEMO3;
    private String FUQUANXIAN;
    private String FUACCOUNT;
    private String FUPASSWORD;
    private String FRID;
    private String FRNAME;
    private int FUSEEID;
    private String FUSEENAME;
    private String FUNAME;
    private boolean FUSEX;
    private String FUPOSITION;
    private String FUPHONE;
    private String FUEMAIL;
    private String FUQQ;
    private int FUCODE;
    private String FUNUMBER;
    private String FUPOSTCODE;
    private String FUREMARK;
    private String FUDWTYPE;
    private String FSDEL;
    private String FULOGOFF;
    private String FSM1;
    private String FSM2;
    private String FSM3;
    private String FSM4;
    private String FSM5;
    private String SFRID;
    private String SFRNAME;
    private String CODE;
    private int ZT;

    @Keep
    public User(long USERID, String FSGUIDID, String FSCTIME, String FSUTIME, int FSUSERID, String FSUSERNAME, int FSUNITID, String FSUNITUSTRID, String FSUNITNAME, int FSENTERPRISEID, String FSENTERPRISENAME, String FSAUDIT, String FSREVIEWER, String FSMEMO1, String FSMEMO2, String FSMEMO3, String FUQUANXIAN, String FUACCOUNT, String FUPASSWORD, String FRID, String FRNAME, int FUSEEID, String FUSEENAME, String FUNAME, boolean FUSEX, String FUPOSITION, String FUPHONE, String FUEMAIL, String FUQQ, int FUCODE, String FUNUMBER, String FUPOSTCODE, String FUREMARK, String FUDWTYPE, String FSDEL, String FULOGOFF, String FSM1, String FSM2, String FSM3, String FSM4, String FSM5, String SFRID, String SFRNAME, String CODE, int ZT) {
        this.USERID = USERID;
        this.FSGUIDID = FSGUIDID;
        this.FSCTIME = FSCTIME;
        this.FSUTIME = FSUTIME;
        this.FSUSERID = FSUSERID;
        this.FSUSERNAME = FSUSERNAME;
        this.FSUNITID = FSUNITID;
        this.FSUNITUSTRID = FSUNITUSTRID;
        this.FSUNITNAME = FSUNITNAME;
        this.FSENTERPRISEID = FSENTERPRISEID;
        this.FSENTERPRISENAME = FSENTERPRISENAME;
        this.FSAUDIT = FSAUDIT;
        this.FSREVIEWER = FSREVIEWER;
        this.FSMEMO1 = FSMEMO1;
        this.FSMEMO2 = FSMEMO2;
        this.FSMEMO3 = FSMEMO3;
        this.FUQUANXIAN = FUQUANXIAN;
        this.FUACCOUNT = FUACCOUNT;
        this.FUPASSWORD = FUPASSWORD;
        this.FRID = FRID;
        this.FRNAME = FRNAME;
        this.FUSEEID = FUSEEID;
        this.FUSEENAME = FUSEENAME;
        this.FUNAME = FUNAME;
        this.FUSEX = FUSEX;
        this.FUPOSITION = FUPOSITION;
        this.FUPHONE = FUPHONE;
        this.FUEMAIL = FUEMAIL;
        this.FUQQ = FUQQ;
        this.FUCODE = FUCODE;
        this.FUNUMBER = FUNUMBER;
        this.FUPOSTCODE = FUPOSTCODE;
        this.FUREMARK = FUREMARK;
        this.FUDWTYPE = FUDWTYPE;
        this.FSDEL = FSDEL;
        this.FULOGOFF = FULOGOFF;
        this.FSM1 = FSM1;
        this.FSM2 = FSM2;
        this.FSM3 = FSM3;
        this.FSM4 = FSM4;
        this.FSM5 = FSM5;
        this.SFRID = SFRID;
        this.SFRNAME = SFRNAME;
        this.CODE = CODE;
        this.ZT = ZT;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUSERID() {
        return USERID;
    }

    public void setUSERID(long USERID) {
        this.USERID = USERID;
    }

    public String getFSGUIDID() {
        return FSGUIDID;
    }

    public void setFSGUIDID(String FSGUIDID) {
        this.FSGUIDID = FSGUIDID;
    }

    public String getFSCTIME() {
        return FSCTIME;
    }

    public void setFSCTIME(String FSCTIME) {
        this.FSCTIME = FSCTIME;
    }

    public String getFSUTIME() {
        return FSUTIME;
    }

    public void setFSUTIME(String FSUTIME) {
        this.FSUTIME = FSUTIME;
    }

    public int getFSUSERID() {
        return FSUSERID;
    }

    public void setFSUSERID(int FSUSERID) {
        this.FSUSERID = FSUSERID;
    }

    public String getFSUSERNAME() {
        return FSUSERNAME;
    }

    public void setFSUSERNAME(String FSUSERNAME) {
        this.FSUSERNAME = FSUSERNAME;
    }

    public int getFSUNITID() {
        return FSUNITID;
    }

    public void setFSUNITID(int FSUNITID) {
        this.FSUNITID = FSUNITID;
    }

    public String getFSUNITUSTRID() {
        return FSUNITUSTRID;
    }

    public void setFSUNITUSTRID(String FSUNITUSTRID) {
        this.FSUNITUSTRID = FSUNITUSTRID;
    }

    public String getFSUNITNAME() {
        return FSUNITNAME;
    }

    public void setFSUNITNAME(String FSUNITNAME) {
        this.FSUNITNAME = FSUNITNAME;
    }

    public int getFSENTERPRISEID() {
        return FSENTERPRISEID;
    }

    public void setFSENTERPRISEID(int FSENTERPRISEID) {
        this.FSENTERPRISEID = FSENTERPRISEID;
    }

    public String getFSENTERPRISENAME() {
        return FSENTERPRISENAME;
    }

    public void setFSENTERPRISENAME(String FSENTERPRISENAME) {
        this.FSENTERPRISENAME = FSENTERPRISENAME;
    }

    public String getFSAUDIT() {
        return FSAUDIT;
    }

    public void setFSAUDIT(String FSAUDIT) {
        this.FSAUDIT = FSAUDIT;
    }

    public String getFSREVIEWER() {
        return FSREVIEWER;
    }

    public void setFSREVIEWER(String FSREVIEWER) {
        this.FSREVIEWER = FSREVIEWER;
    }

    public String getFSMEMO1() {
        return FSMEMO1;
    }

    public void setFSMEMO1(String FSMEMO1) {
        this.FSMEMO1 = FSMEMO1;
    }

    public String getFSMEMO2() {
        return FSMEMO2;
    }

    public void setFSMEMO2(String FSMEMO2) {
        this.FSMEMO2 = FSMEMO2;
    }

    public String getFSMEMO3() {
        return FSMEMO3;
    }

    public void setFSMEMO3(String FSMEMO3) {
        this.FSMEMO3 = FSMEMO3;
    }

    public String getFUQUANXIAN() {
        return FUQUANXIAN;
    }

    public void setFUQUANXIAN(String FUQUANXIAN) {
        this.FUQUANXIAN = FUQUANXIAN;
    }

    public String getFUACCOUNT() {
        return FUACCOUNT;
    }

    public void setFUACCOUNT(String FUACCOUNT) {
        this.FUACCOUNT = FUACCOUNT;
    }

    public String getFUPASSWORD() {
        return FUPASSWORD;
    }

    public void setFUPASSWORD(String FUPASSWORD) {
        this.FUPASSWORD = FUPASSWORD;
    }

    public String getFRID() {
        return FRID;
    }

    public void setFRID(String FRID) {
        this.FRID = FRID;
    }

    public String getFRNAME() {
        return FRNAME;
    }

    public void setFRNAME(String FRNAME) {
        this.FRNAME = FRNAME;
    }

    public int getFUSEEID() {
        return FUSEEID;
    }

    public void setFUSEEID(int FUSEEID) {
        this.FUSEEID = FUSEEID;
    }

    public String getFUSEENAME() {
        return FUSEENAME;
    }

    public void setFUSEENAME(String FUSEENAME) {
        this.FUSEENAME = FUSEENAME;
    }

    public String getFUNAME() {
        return FUNAME;
    }

    public void setFUNAME(String FUNAME) {
        this.FUNAME = FUNAME;
    }

    public boolean isFUSEX() {
        return FUSEX;
    }

    public void setFUSEX(boolean FUSEX) {
        this.FUSEX = FUSEX;
    }

    public String getFUPOSITION() {
        return FUPOSITION;
    }

    public void setFUPOSITION(String FUPOSITION) {
        this.FUPOSITION = FUPOSITION;
    }

    public String getFUPHONE() {
        return FUPHONE;
    }

    public void setFUPHONE(String FUPHONE) {
        this.FUPHONE = FUPHONE;
    }

    public String getFUEMAIL() {
        return FUEMAIL;
    }

    public void setFUEMAIL(String FUEMAIL) {
        this.FUEMAIL = FUEMAIL;
    }

    public String getFUQQ() {
        return FUQQ;
    }

    public void setFUQQ(String FUQQ) {
        this.FUQQ = FUQQ;
    }

    public int getFUCODE() {
        return FUCODE;
    }

    public void setFUCODE(int FUCODE) {
        this.FUCODE = FUCODE;
    }

    public String getFUNUMBER() {
        return FUNUMBER;
    }

    public void setFUNUMBER(String FUNUMBER) {
        this.FUNUMBER = FUNUMBER;
    }

    public String getFUPOSTCODE() {
        return FUPOSTCODE;
    }

    public void setFUPOSTCODE(String FUPOSTCODE) {
        this.FUPOSTCODE = FUPOSTCODE;
    }

    public String getFUREMARK() {
        return FUREMARK;
    }

    public void setFUREMARK(String FUREMARK) {
        this.FUREMARK = FUREMARK;
    }

    public String getFUDWTYPE() {
        return FUDWTYPE;
    }

    public void setFUDWTYPE(String FUDWTYPE) {
        this.FUDWTYPE = FUDWTYPE;
    }

    public String getFSDEL() {
        return FSDEL;
    }

    public void setFSDEL(String FSDEL) {
        this.FSDEL = FSDEL;
    }

    public String getFULOGOFF() {
        return FULOGOFF;
    }

    public void setFULOGOFF(String FULOGOFF) {
        this.FULOGOFF = FULOGOFF;
    }

    public String getFSM1() {
        return FSM1;
    }

    public void setFSM1(String FSM1) {
        this.FSM1 = FSM1;
    }

    public String getFSM2() {
        return FSM2;
    }

    public void setFSM2(String FSM2) {
        this.FSM2 = FSM2;
    }

    public String getFSM3() {
        return FSM3;
    }

    public void setFSM3(String FSM3) {
        this.FSM3 = FSM3;
    }

    public String getFSM4() {
        return FSM4;
    }

    public void setFSM4(String FSM4) {
        this.FSM4 = FSM4;
    }

    public String getFSM5() {
        return FSM5;
    }

    public void setFSM5(String FSM5) {
        this.FSM5 = FSM5;
    }

    public String getSFRID() {
        return SFRID;
    }

    public void setSFRID(String SFRID) {
        this.SFRID = SFRID;
    }

    public String getSFRNAME() {
        return SFRNAME;
    }

    public void setSFRNAME(String SFRNAME) {
        this.SFRNAME = SFRNAME;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public int getZt() {
        return ZT;
    }

    public void setZt(int zt) {
        this.ZT = zt;
    }

    public boolean getFUSEX() {
        return this.FUSEX;
    }
    public int getZT() {
        return this.ZT;
    }
    public void setZT(int ZT) {
        this.ZT = ZT;
    }
}
