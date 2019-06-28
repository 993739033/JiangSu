package com.wyw.jiangsu.bean.upload;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wyw on 2016/12/28.
 * 代理申报 官方兽医确认
 */

public class UploadReplaceApplyBean implements Parcelable {
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
    private String Fsfzh;
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
    private String FSm2;

    //姓名
    private String xm;
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }


    public String getFSm2() {
        return FSm2;
    }

    public void setFSm2(String FSm2) {
        this.FSm2 = FSm2;
    }

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
        return "UploadReplaceApplyBean{" +
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
                ", Fclfs='" + Fclfs + '\'' +
                ", Fqm='" + Fqm + '\'' +
                ", Fxxdz='" + Fxxdz + '\'' +
                ", Fdw1='" + Fdw1 + '\'' +
                ", SFFH='" + SFFH + '\'' +
                ", len='" + len + '\'' +
                ", FStId='" + FStId + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Glid);
        dest.writeString(this.Fxzxm);
        dest.writeString(this.lxdh);
        dest.writeString(this.Fyzclx);
        dest.writeString(this.Fxcll);
        dest.writeString(this.Fsbrq);
        dest.writeString(this.Fsfzh);
        dest.writeString(this.fykth);
        dest.writeString(this.Fbsdwzl);
        dest.writeString(this.Fsws);
        dest.writeString(this.Fcbs);
        dest.writeString(this.QDWErBiaoHao);
        dest.writeString(this.Fysswyy);
        dest.writeString(this.Fsiwh);
        dest.writeString(this.Fyssj);
        dest.writeString(this.Fsjd);
        dest.writeString(this.FsjdID);
        dest.writeString(this.FGlid);
        dest.writeString(this.JD);
        dest.writeString(this.WD);
        dest.writeString(this.DZ);
        dest.writeString(this.Fclfs2);
        dest.writeString(this.Fgfsyqz);
        dest.writeString(this.Fclfs);
        dest.writeString(this.Fqm);
        dest.writeString(this.Fxxdz);
        dest.writeString(this.Fdw1);
        dest.writeString(this.SFFH);
        dest.writeString(this.len);
        dest.writeString(this.FSm2);
        dest.writeString(this.xm);
        dest.writeString(this.FStId);
    }

    public UploadReplaceApplyBean() {
    }

    protected UploadReplaceApplyBean(Parcel in) {
        this.Glid = in.readString();
        this.Fxzxm = in.readString();
        this.lxdh = in.readString();
        this.Fyzclx = in.readString();
        this.Fxcll = in.readString();
        this.Fsbrq = in.readString();
        this.Fsfzh = in.readString();
        this.fykth = in.readString();
        this.Fbsdwzl = in.readString();
        this.Fsws = in.readString();
        this.Fcbs = in.readString();
        this.QDWErBiaoHao = in.readString();
        this.Fysswyy = in.readString();
        this.Fsiwh = in.readString();
        this.Fyssj = in.readString();
        this.Fsjd = in.readString();
        this.FsjdID = in.readString();
        this.FGlid = in.readString();
        this.JD = in.readString();
        this.WD = in.readString();
        this.DZ = in.readString();
        this.Fclfs2 = in.readString();
        this.Fgfsyqz = in.readString();
        this.Fclfs = in.readString();
        this.Fqm = in.readString();
        this.Fxxdz = in.readString();
        this.Fdw1 = in.readString();
        this.SFFH = in.readString();
        this.len = in.readString();
        this.FSm2 = in.readString();
        this.xm = in.readString();
        this.FStId = in.readString();
    }

    public static final Parcelable.Creator<UploadReplaceApplyBean> CREATOR = new Parcelable.Creator<UploadReplaceApplyBean>() {
        @Override
        public UploadReplaceApplyBean createFromParcel(Parcel source) {
            return new UploadReplaceApplyBean(source);
        }

        @Override
        public UploadReplaceApplyBean[] newArray(int size) {
            return new UploadReplaceApplyBean[size];
        }
    };
}
