package com.wyw.jiangsu.bean;

import com.wyw.jiangsu.view.recyclerview.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by wyw on 2016/12/29.
 * 获取监管兽医指派的内容
 */
public class HarmlessListDetilBean extends BaseObjectEntity<HarmlessListDetilBean.DataBean> {

    public static class DataBean {

        /**
         * 图片
         */
        private Pictures pictures;

        /**
         * FStId : 2
         * Glid : 2
         * Fxzxm : ret
         * Fxxdz : 江苏省南京市玄武区二胎
         * Fyzclx : 种用
         * Fxcll : 10
         * Fsbrq : 2016-12-29
         * Fdw1 : 头
         * Fbsdwzl : 仔猪
         * Fsws : 10
         * Fcbs : 15
         * SWZL : 重量
         * QDWErBiaoHao :
         * Fysswyy : 正常死亡
         * Fclfs : 集中处理
         * Fyssj : 2016-12-29
         * Fsjd : 测试
         * Fclfs2 : 深埋
         * Fqm :
         * Fgfsyqz :
         * Fsfzh :
         * fykth : 1564
         * Fsiwh : 疑似疫情
         * FsjdID : 1
         * SFFH :
         */

        private String Fyssj;
        private String Fsjd;
        private String Fgfsyqz;
        private String FsjdID;
        private String SFFH;
        private String FStId;
        private String Fxzxm;
        private String lxdh;
        private String Fyzclx;
        private String Fxcll;
        private String Fsbrq;
        private String Fbsdwzl;
        private String Fsws;
        private String Fcbs;
        private String QDWErBiaoHao;
        private String Fysswyy;
        private String Fsiwh;
        private String Fclfs;
        private String Fqm;
        private String Fxxdz;
        private String Fclfs2;
        private String Fdw1;
        private String Fsfzh;
        private String fykth;
        private String Glid;
        private String FScTime;//分派时间
        private String SWZL;//重量
        private String CPH;//车牌
        private String SJR;//收集人

        //无害化处理集中处理
        private String WHHCLZXID;//无害化处理厂id
        private String WHHCLZX;//无害化处理厂名字
        private String CLRQ;//处理日期
        private String CLR;//处理人
        //meiyong
        private String sws;
        private String cbs;
        private String zls;

       private String xm;
        private String FSm4;

        public String getFSm4() {
            return FSm4;
        }

        public void setFSm4(String fsm4) {
            FSm4 = fsm4;
        }
        //驻场兽医 收集点 地址 动物种类
        /**
         * FStId : 1
         * Fxzxm : 测试
         * Fxxdz : 测试
         * Fbsdwzl : 猪
         * Fsws : 10
         * SWZL : 10.000
         */

        private List<DataListBean0> dataList0;
        private List<DataListBean0> dataList1;
        //驻场兽医 合计

        private List<DataListBean2> dataList2;

        public List<DataListBean0> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataListBean0> dataList1) {
            this.dataList1 = dataList1;
        }
        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
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

        public String getSWZL() {
            return SWZL;
        }

        public void setSWZL(String SWZL) {
            this.SWZL = SWZL;
        }

        public Pictures getPictures() {
            return pictures;
        }

        public void setPictures(Pictures pictures) {
            this.pictures = pictures;
        }

        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public String getFStId() {
            return FStId;
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

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public void setZls(String zls) {
            this.zls = zls;
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

        public String getFyzclx() {
            return Fyzclx;
        }

        public void setFyzclx(String Fyzclx) {
            this.Fyzclx = Fyzclx;
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

        public String getFxcll() {
            return Fxcll;
        }

        public void setFxcll(String Fxcll) {
            this.Fxcll = Fxcll;
        }

        public String getFsbrq() {
            return Fsbrq;
        }

        public void setFsbrq(String Fsbrq) {
            this.Fsbrq = Fsbrq;
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

        public String getFgfsyqz() {
            return Fgfsyqz;
        }

        public void setFgfsyqz(String fgfsyqz) {
            Fgfsyqz = fgfsyqz;
        }

        public String getFsjdID() {
            return FsjdID;
        }

        public void setFsjdID(String fsjdID) {
            FsjdID = fsjdID;
        }

        public String getSFFH() {
            return SFFH;
        }

        public void setSFFH(String SFFH) {
            this.SFFH = SFFH;
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

        public String getFxxdz() {
            return Fxxdz;
        }

        public void setFxxdz(String Fxxdz) {
            this.Fxxdz = Fxxdz;
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

        public List<DataListBean0> getDataList0() {
            return dataList0;
        }

        public void setDataList0(List<DataListBean0> dataList0) {
            this.dataList0 = dataList0;
        }

        public List<DataListBean2> getDataList2() {
            return dataList2;
        }

        public void setDataList2(List<DataListBean2> dataList2) {
            this.dataList2 = dataList2;
        }

    }
    public static class Pictures {
        private String A1;
        private String A2;
        private String A3;
        private String A4;
        private String A5;
        private String A6;
        private String A7;
        private String A8;
        private String A9;
        private String A10;
        private String A11;
        private String A12;
        private String A13;
        private String A14;
        private String A15;
        private String A16;
        private String A17;

        public String getA11() {
            return A11;
        }

        public void setA11(String a11) {
            A11 = a11;
        }

        public String getA12() {
            return A12;
        }

        public void setA12(String a12) {
            A12 = a12;
        }

        public String getA13() {
            return A13;
        }

        public void setA13(String a13) {
            A13 = a13;
        }

        public String getA14() {
            return A14;
        }

        public void setA14(String a14) {
            A14 = a14;
        }

        public String getA15() {
            return A15;
        }

        public void setA15(String a15) {
            A15 = a15;
        }

        public String getA16() {
            return A16;
        }

        public void setA16(String a16) {
            A16 = a16;
        }

        public String getA17() {
            return A17;
        }

        public void setA17(String a17) {
            A17 = a17;
        }

        public String getA1() {
            return A1;
        }

        public void setA1(String a1) {
            A1 = a1;
        }

        public String getA2() {
            return A2;
        }

        public void setA2(String a2) {
            A2 = a2;
        }

        public String getA3() {
            return A3;
        }

        public void setA3(String a3) {
            A3 = a3;
        }

        public String getA4() {
            return A4;
        }

        public void setA4(String a4) {
            A4 = a4;
        }

        public String getA5() {
            return A5;
        }

        public void setA5(String a5) {
            A5 = a5;
        }

        public String getA6() {
            return A6;
        }

        public void setA6(String a6) {
            A6 = a6;
        }

        public String getA7() {
            return A7;
        }

        public void setA7(String a7) {
            A7 = a7;
        }

        public String getA8() {
            return A8;
        }

        public void setA8(String a8) {
            A8 = a8;
        }

        public String getA9() {
            return A9;
        }

        public void setA9(String a9) {
            A9 = a9;
        }

        public String getA10() {
            return A10;
        }

        public void setA10(String a10) {
            A10 = a10;
        }
    }

    public static class DataListBean2 extends MultiItemEntity {

        /**
         * Fbsdwzl : 猪
         * Fsws : 10
         * CBSS :
         * SWZL : 10.000
         */

        private String Fbsdwzl;
        private String Fsws;
        private String CBSS;
        private String SWZL;//如果Fbsdwzl,重量或合计


        private String dwzl;//种类
        private String sws;//死亡数
        private String cbs;//参保数
        private String zls;//重量

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

        public String getCBSS() {
            return CBSS;
        }

        public void setCBSS(String CBSS) {
            this.CBSS = CBSS;
        }

        public String getSWZL() {
            return SWZL;
        }

        public void setSWZL(String SWZL) {
            this.SWZL = SWZL;
        }
    }

    public static class DataListBean0 {
        private String FStId;
        private String Fxzxm;
        private String Fxxdz;
        private String Fbsdwzl;
        private String Fsws;
        private String SWZL;
        private String dwzl;
        private String sws;
        private String cbs;
        private String zls;
        private String id;

        private String CPH;
        private String SJR;
        private String FScTime;
        private String FSunitUstrId;
        private List<DataListBean1> dataList1;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public String getFSunitUstrId() {
            return FSunitUstrId;
        }

        public void setFSunitUstrId(String FSunitUstrId) {
            this.FSunitUstrId = FSunitUstrId;
        }

        public List<DataListBean1> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataListBean1> dataList1) {
            this.dataList1 = dataList1;
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

        public String getSWZL() {
            return SWZL;
        }

        public void setSWZL(String SWZL) {
            this.SWZL = SWZL;
        }

        public static class DataListBean1 {
            private String Fbsdwzl;
            private String Fsws;
            private String SWZL;
            private String id;

            //新加 FGlid
            private String FGlid;

            public String getFGlid() {
                return FGlid;
            }

            public void setFGlid(String FGlid) {
                this.FGlid = FGlid;
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

            public String getSWZL() {
                return SWZL;
            }

            public void setSWZL(String SWZL) {
                this.SWZL = SWZL;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

    }

}
