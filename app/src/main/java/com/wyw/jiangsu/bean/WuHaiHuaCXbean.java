package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */

public class WuHaiHuaCXbean extends BaseArrayObjectEntity<WuHaiHuaCXbean.DataListBean> {


    public static class DataListBean implements Serializable {
        /**
         * FStId : 42
         * Fxzxm : 旋卫忠
         * Fxxdz : 江苏省南京市玄武区星光街道
         * Fsbrq : 2017-04-01
         * ZLSL : 猪:0
         * Fyssj : 2017-04-02
         * FsjdID : 3
         * Fsjd : 玄武收集点
         */


        private String jl;

        private String FStId;
        private String Fxzxm;       //收集点名称  养殖场户姓名
        private String Fxxdz;       //地址
        private String Fsbrq;       //申报日期 （不传）
        private String ZLSL;        //病死动物及数量
        private String Fyssj;       //移送时间  (第二次访问的时候，放在第一位)
        private String FsjdID;
        private String Fsjd;

        private String WHHCLZXID;
        private String WHHCLZX;         //无害化处理厂名字
        private String CLRQ;            //处理日期
        private String CLR;             //处理人
        private String sws;             //死亡数
        private String cbs;             //病死动物类型
        private String zls;             //重量
        private String FSenterpriseId;

        private String FScTime;         //收集时间  分派收集日期
        private String CPH;             //收集车辆
        private String SJR;             //收集人
        private String FSunitUstrId;

        private String SWZL;        //病死动物重量
        private String SJRID;

        private String FSguidId;
        private String FSuTime;
        private String FSuserId;
        private String FSuserName;
        private String FSunitId;
        private String FSunitName;
        private String FSenterpriseName;
        private String FSaudit;
        private String FSreviewer;
        private String FSmemo1;
        private String FSmemo2;
        private String FSmemo3;
        private String FSDel;
        private String FSm1;
        private String FSm2;
        private String FSm3;
        private String FSm4;
        private String FSm5;
        private String Fyzclx;
        private String Fxcll;
        private String Fbsdwzl;
        private String Fsws;
        private String Fcbs;
        private String QDWErBiaoHao;
        private String Fysswyy;
        private String Fsiwh;
        private String Fclfs;
        private String Fqm;
        private String Fclfs2;
        private String Fdw1;
        private String Fsfzh;
        private String fykth;
        private String Glid;
        private String ZPID;

        private String swl;

        private String Fgfsyqz;
        private String SFFH;
        private String FGlid;
        private String JD;
        private String WD;
        private String DZ;

        private String QDSL;
        private String HSQFH;
        private String JSQFH;

        private String YZ;
        private String CZ;
        private String CLFSS;
        private String YTCW;
        private String SJ;
        private String SJRQ;
        private String SFCL;
        private String QRZL;
        private String xm;
        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }
        public String getSJRQ() {
            return SJRQ;
        }

        public void setSJRQ(String SJRQ) {
            this.SJRQ = SJRQ;
        }

        public String getSFCL() {
            return SFCL;
        }

        public void setSFCL(String SFCL) {
            this.SFCL = SFCL;
        }

        public String getQRZL() {
            return QRZL;
        }

        public void setQRZL(String QRZL) {
            this.QRZL = QRZL;
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

        public String getCLFSS() {
            return CLFSS;
        }

        public void setCLFSS(String CLFSS) {
            this.CLFSS = CLFSS;
        }

        public String getYTCW() {
            return YTCW;
        }

        public void setYTCW(String YTCW) {
            this.YTCW = YTCW;
        }

        public String getSJ() {
            return SJ;
        }

        public void setSJ(String SJ) {
            this.SJ = SJ;
        }

        public String getQDSL() {
            return QDSL;
        }

        public void setQDSL(String QDSL) {
            this.QDSL = QDSL;
        }

        public String getHSQFH() {
            return HSQFH;
        }

        public void setHSQFH(String HSQFH) {
            this.HSQFH = HSQFH;
        }

        public String getJSQFH() {
            return JSQFH;
        }

        public void setJSQFH(String JSQFH) {
            this.JSQFH = JSQFH;
        }

        public String getJl() {
            return jl;
        }

        public void setJl(String jl) {
            this.jl = jl;
        }

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFxzxm() {
            return Fxzxm;
        }

        public void setFxzxm(String Fxzxm) {
            this.Fxzxm = Fxzxm;
        }

        public String getFxxdz() {
            return Fxxdz;
        }

        public void setFxxdz(String Fxxdz) {
            this.Fxxdz = Fxxdz;
        }

        public String getFsbrq() {
            return Fsbrq;
        }

        public void setFsbrq(String Fsbrq) {
            this.Fsbrq = Fsbrq;
        }

        public String getZLSL() {
            return ZLSL;
        }

        public void setZLSL(String ZLSL) {
            this.ZLSL = ZLSL;
        }

        public String getFyssj() {
            return Fyssj;
        }

        public void setFyssj(String Fyssj) {
            this.Fyssj = Fyssj;
        }

        public String getFsjdID() {
            return FsjdID;
        }

        public void setFsjdID(String FsjdID) {
            this.FsjdID = FsjdID;
        }

        public String getFsjd() {
            return Fsjd;
        }

        public void setFsjd(String Fsjd) {
            this.Fsjd = Fsjd;
        }

        public String getWHHCLZXID() {
            return WHHCLZXID;
        }

        public void setWHHCLZXID(String WHHCLZXID) {
            this.WHHCLZXID = WHHCLZXID;
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

        public String getFSenterpriseId() {
            return FSenterpriseId;
        }

        public void setFSenterpriseId(String FSenterpriseId) {
            this.FSenterpriseId = FSenterpriseId;
        }

        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public String getCPH() {
            return CPH;
        }

        public void setCPH(String CPH) {
            this.CPH = CPH;
        }

        public String getSJR() {
            return SJR;
        }

        public void setSJR(String SJR) {
            this.SJR = SJR;
        }

        public String getFSunitUstrId() {
            return FSunitUstrId;
        }

        public void setFSunitUstrId(String FSunitUstrId) {
            this.FSunitUstrId = FSunitUstrId;
        }


        public String getSWZL() {
            return SWZL;
        }

        public void setSWZL(String SWZL) {
            this.SWZL = SWZL;
        }

        public String getSJRID() {
            return SJRID;
        }

        public void setSJRID(String SJRID) {
            this.SJRID = SJRID;
        }


        public String getFSguidId() {
            return FSguidId;
        }

        public void setFSguidId(String FSguidId) {
            this.FSguidId = FSguidId;
        }

        public String getFSuTime() {
            return FSuTime;
        }

        public void setFSuTime(String FSuTime) {
            this.FSuTime = FSuTime;
        }

        public String getFSuserId() {
            return FSuserId;
        }

        public void setFSuserId(String FSuserId) {
            this.FSuserId = FSuserId;
        }

        public String getFSuserName() {
            return FSuserName;
        }

        public void setFSuserName(String FSuserName) {
            this.FSuserName = FSuserName;
        }

        public String getFSunitId() {
            return FSunitId;
        }

        public void setFSunitId(String FSunitId) {
            this.FSunitId = FSunitId;
        }

        public String getFSunitName() {
            return FSunitName;
        }

        public void setFSunitName(String FSunitName) {
            this.FSunitName = FSunitName;
        }

        public String getFSenterpriseName() {
            return FSenterpriseName;
        }

        public void setFSenterpriseName(String FSenterpriseName) {
            this.FSenterpriseName = FSenterpriseName;
        }

        public String getFSaudit() {
            return FSaudit;
        }

        public void setFSaudit(String FSaudit) {
            this.FSaudit = FSaudit;
        }

        public String getFSreviewer() {
            return FSreviewer;
        }

        public void setFSreviewer(String FSreviewer) {
            this.FSreviewer = FSreviewer;
        }

        public String getFSmemo1() {
            return FSmemo1;
        }

        public void setFSmemo1(String FSmemo1) {
            this.FSmemo1 = FSmemo1;
        }

        public String getFSmemo2() {
            return FSmemo2;
        }

        public void setFSmemo2(String FSmemo2) {
            this.FSmemo2 = FSmemo2;
        }

        public String getFSmemo3() {
            return FSmemo3;
        }

        public void setFSmemo3(String FSmemo3) {
            this.FSmemo3 = FSmemo3;
        }

        public String getFSDel() {
            return FSDel;
        }

        public void setFSDel(String FSDel) {
            this.FSDel = FSDel;
        }

        public String getFSm1() {
            return FSm1;
        }

        public void setFSm1(String FSm1) {
            this.FSm1 = FSm1;
        }

        public String getFSm2() {
            return FSm2;
        }

        public void setFSm2(String FSm2) {
            this.FSm2 = FSm2;
        }

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

        public String getFSm5() {
            return FSm5;
        }

        public void setFSm5(String FSm5) {
            this.FSm5 = FSm5;
        }

        public String getFyzclx() {
            return Fyzclx;
        }

        public void setFyzclx(String Fyzclx) {
            this.Fyzclx = Fyzclx;
        }

        public String getFxcll() {
            return Fxcll;
        }

        public void setFxcll(String Fxcll) {
            this.Fxcll = Fxcll;
        }

        public String getFbsdwzl() {
            return Fbsdwzl;
        }

        public void setFbsdwzl(String Fbsdwzl) {
            this.Fbsdwzl = Fbsdwzl;
        }

        public String getFsws() {
            return Fsws;
        }

        public void setFsws(String Fsws) {
            this.Fsws = Fsws;
        }

        public String getFcbs() {
            return Fcbs;
        }

        public void setFcbs(String Fcbs) {
            this.Fcbs = Fcbs;
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

        public void setFysswyy(String Fysswyy) {
            this.Fysswyy = Fysswyy;
        }

        public String getFsiwh() {
            return Fsiwh;
        }

        public void setFsiwh(String Fsiwh) {
            this.Fsiwh = Fsiwh;
        }

        public String getFclfs() {
            return Fclfs;
        }

        public void setFclfs(String Fclfs) {
            this.Fclfs = Fclfs;
        }

        public String getFqm() {
            return Fqm;
        }

        public void setFqm(String Fqm) {
            this.Fqm = Fqm;
        }

        public String getFclfs2() {
            return Fclfs2;
        }

        public void setFclfs2(String Fclfs2) {
            this.Fclfs2 = Fclfs2;
        }

        public String getFdw1() {
            return Fdw1;
        }

        public void setFdw1(String Fdw1) {
            this.Fdw1 = Fdw1;
        }

        public String getFsfzh() {
            return Fsfzh;
        }

        public void setFsfzh(String Fsfzh) {
            this.Fsfzh = Fsfzh;
        }

        public String getFykth() {
            return fykth;
        }

        public void setFykth(String fykth) {
            this.fykth = fykth;
        }

        public String getGlid() {
            return Glid;
        }

        public void setGlid(String Glid) {
            this.Glid = Glid;
        }

        public String getZPID() {
            return ZPID;
        }

        public void setZPID(String ZPID) {
            this.ZPID = ZPID;
        }

        public String getSwl() {
            return swl;
        }

        public void setSwl(String swl) {
            this.swl = swl;
        }

        public String getFgfsyqz() {
            return Fgfsyqz;
        }

        public void setFgfsyqz(String Fgfsyqz) {
            this.Fgfsyqz = Fgfsyqz;
        }

        public String getSFFH() {
            return SFFH;
        }

        public void setSFFH(String SFFH) {
            this.SFFH = SFFH;
        }

        public String getFGlid() {
            return FGlid;
        }

        public void setFGlid(String FGlid) {
            this.FGlid = FGlid;
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
    }
}
