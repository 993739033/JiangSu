package com.wyw.jiangsu.bean;

/**
 * Created by mnkj004 on 2017/7/28.
 * 兽医人員代理申报
 */

public class VeterinarianDeclareBean {
    // 畜主姓名ID
    private String Glid;
    // 畜主姓名
    private String Fxzxm;
    //联系电话
    private String lxdh;
    // 养殖场类型
    private String Fyzclx;
    // 现存栏量
    private String Fxcll;
    // 申报日期
    private String Fsbrq;
    // 身份证号
    private String Fsfzh ;
    //一卡通号
    private String fykth;
    // 病死动物种类
    private String Fbsdwzl;
    // 死亡数
    private String Fsws;
    // 参保数
    private String Fcbs;
    //// 耳标号
    private String QDWErBiaoHao;
    //// 疑似死亡原因
    private String Fysswyy;
    // 非正常死亡
    private String Fsiwh;
    //移送时间
    private String Fyssj;
    // 病死畜禽移送收集点
    private String Fsjd;
    //病死畜禽移送收集点id
    private String FsjdID;
    /// 关联监管兽医确认ID
    private String FGlid;

    //经度
    private String JD;
    //维度
    private String WD;
    //地址
    private String DZ;

    // 处理方式2
    private String Fclfs2;
    //官方兽医签字
    private String Fgfsyqz;



    //处理方式1
    private String Fclfs;

    // 签名
    private String Fqm;
    //地址
    private String Fxxdz;
    //// 单位
    private String Fdw1;
    //以上内容是否符合要求
    private String SFFH;

    //长度
    private String len;

/*
    public String getFgfsyqz2() {
        return Fgfsyqz2;
    }

    public void setFgfsyqz2(String fgfsyqz2) {
        Fgfsyqz2 = fgfsyqz2;
    }
*/

    public String getFStId() {
        return FStId;
    }

    public void setFStId(String FStId) {
        this.FStId = FStId;
    }

    private String FStId;
    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getJD() {
        return JD;
    }

    public void setJD(String JD) {
        this.JD = JD;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getDZ() {
        return DZ;
    }

    public void setDZ(String DZ) {
        this.DZ = DZ;
    }

    public String getFGlid() {
        return FGlid;
    }

    public void setFGlid(String FGlid) {
        this.FGlid = FGlid;
    }

    public String getGlid() {
        return Glid;
    }

    public void setGlid(String glid) {
        Glid = glid;
    }

    public String getFxzxm() {
        return Fxzxm;
    }

    public void setFxzxm(String fxzxm) {
        Fxzxm = fxzxm;
    }

    public String getSFFH() {
        return SFFH;
    }

    public void setSFFH(String SFFH) {
        this.SFFH = SFFH;
    }

    public String getFyzclx() {
        return Fyzclx;
    }

    public void setFyzclx(String fyzclx) {
        Fyzclx = fyzclx;
    }

    public String getFxcll() {
        return Fxcll;
    }

    public void setFxcll(String fxcll) {
        Fxcll = fxcll;
    }

    public String getFsbrq() {
        return Fsbrq;
    }

    public void setFsbrq(String fsbrq) {
        Fsbrq = fsbrq;
    }

    public String getFsfzh() {
        return Fsfzh;
    }

    public void setFsfzh(String fsfzh) {
        Fsfzh = fsfzh;
    }

    public String getFykth() {
        return fykth;
    }

    public void setFykth(String fykth) {
        this.fykth = fykth;
    }

    public String getFbsdwzl() {
        return Fbsdwzl;
    }

    public void setFbsdwzl(String fbsdwzl) {
        Fbsdwzl = fbsdwzl;
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

    public String getQDWErBiaoHao() {
        return QDWErBiaoHao;
    }

    public void setQDWErBiaoHao(String QDWErBiaoHao) {
        this.QDWErBiaoHao = QDWErBiaoHao;
    }

    public String getFysswyy() {
        return Fysswyy;
    }

    public void setFysswyy(String fysswyy) {
        Fysswyy = fysswyy;
    }

    public String getFsiwh() {
        return Fsiwh;
    }

    public void setFsiwh(String fsiwh) {
        Fsiwh = fsiwh;
    }

    public String getFyssj() {
        return Fyssj;
    }

    public void setFyssj(String fyssj) {
        Fyssj = fyssj;
    }

    public String getFsjd() {
        return Fsjd;
    }

    public void setFsjd(String fsjd) {
        Fsjd = fsjd;
    }

    public String getFclfs2() {
        return Fclfs2;
    }

    public void setFclfs2(String fclfs2) {
        Fclfs2 = fclfs2;
    }

    public String getFgfsyqz() {
        return Fgfsyqz;
    }

    public void setFgfsyqz(String fgfsyqz) {
        Fgfsyqz = fgfsyqz;
    }

    public String getFclfs() {
        return Fclfs;
    }

    public void setFclfs(String fclfs) {
        Fclfs = fclfs;
    }

    public String getFqm() {
        return Fqm;
    }

    public String getFsjdID() {
        return FsjdID;
    }

    public void setFsjdID(String fsjdID) {
        FsjdID = fsjdID;
    }

    public void setFqm(String fqm) {
        Fqm = fqm;
    }

    public String getFxxdz() {
        return Fxxdz;
    }

    public void setFxxdz(String fxxdz) {
        Fxxdz = fxxdz;
    }

    public String getFdw1() {
        return Fdw1;
    }

    public void setFdw1(String fdw1) {
        Fdw1 = fdw1;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return "VeterinarianDeclareBean{" +
                "Glid='" + Glid + '\'' +
                ", Fxzxm='" + Fxzxm + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", Fyzclx='" + Fyzclx + '\'' +
                ", Fxcll='" + Fxcll + '\'' +
                ", Fsbrq='" + Fsbrq + '\'' +
                ", Fsfzh='" + Fsfzh + '\'' +
                ", fykth='" + fykth + '\'' +
                ", Fbsdwzl='" + Fbsdwzl + '\'' +
                ", Fsws='" + Fsws + '\'' +
                ", Fcbs='" + Fcbs + '\'' +
                ", QDWErBiaoHao='" + QDWErBiaoHao + '\'' +
                ", Fysswyy='" + Fysswyy + '\'' +
                ", Fsiwh='" + Fsiwh + '\'' +
                ", Fyssj='" + Fyssj + '\'' +
                ", Fsjd='" + Fsjd + '\'' +
                ", FsjdID='" + FsjdID + '\'' +
                ", FGlid='" + FGlid + '\'' +
                ", JD='" + JD + '\'' +
                ", WD='" + WD + '\'' +
                ", DZ='" + DZ + '\'' +
                ", Fclfs2='" + Fclfs2 + '\'' +
                ", Fgfsyqz='" + Fgfsyqz + '\'' +
//                ", Fgfsyqz2='" + Fgfsyqz2 + '\'' +
                ", Fclfs='" + Fclfs + '\'' +
                ", Fqm='" + Fqm + '\'' +
                ", Fxxdz='" + Fxxdz + '\'' +
                ", Fdw1='" + Fdw1 + '\'' +
                ", SFFH='" + SFFH + '\'' +
                ", len='" + len + '\'' +
                ", FStId='" + FStId + '\'' +
                '}';
    }
}
