package com.wyw.jiangsu.bean;

import com.wyw.jiangsu.view.recyclerview.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Windows on 2017/7/4.
 */

public class JiZhongChuLiDetilBean extends BaseObjectEntity<JiZhongChuLiDetilBean.DataBean> {

    public static class DataBean {
        /**
         * FStId : 1
         * Glid : 36
         * pictures1 : {"A6":"f979676f-a0bd-4605-9772-875f1c7ac306A6.jpg","A7":"6078a482-5607-46c5-b707-ff3edefb9e95_A7.jpg","A8":"1","A9":"6078a482-5607-46c5-b707-ff3edefb9e95_A9.jpg"}
         * dataList1 : [{"dwzl":"猪","sws":"75","zls":"3260.000"}]
         * FGlid :
         * HJ : 3260.0
         * WHHCLZX : 响水县双誉畜禽无害化处理有限公司
         * CLRQ : 2017-06-24
         * CLR : 王梅花
         * CLFSS : 高温
         * WD :
         * YL :
         * SJ : 2017-06-24
         * YZ : 45.000
         * CZ : 600.000
         * YTCW : 0.000
         * TGlid : 0
         */

        private String FStId;
        private String Glid;
        private Pictures1Bean pictures1;
        private String FGlid;
        private String HJ;
        private String WHHCLZX;
        private String CLRQ;
        private String CLR;
        private String CLFSS;
        private String WD;
        private String YL;
        private String SJ;
        private String YZ;
        private String CZ;
        private String YTCW;
        private String TGlid;
        private List<DataList1Bean> dataList1;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getGlid() {
            return Glid;
        }

        public void setGlid(String Glid) {
            this.Glid = Glid;
        }

        public Pictures1Bean getPictures1() {
            return pictures1;
        }

        public void setPictures1(Pictures1Bean pictures1) {
            this.pictures1 = pictures1;
        }

        public String getFGlid() {
            return FGlid;
        }

        public void setFGlid(String FGlid) {
            this.FGlid = FGlid;
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

        public String getWD() {
            return WD;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getYL() {
            return YL;
        }

        public void setYL(String YL) {
            this.YL = YL;
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

        public String getTGlid() {
            return TGlid;
        }

        public void setTGlid(String TGlid) {
            this.TGlid = TGlid;
        }

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public static class Pictures1Bean {
            /**
             * A6 : f979676f-a0bd-4605-9772-875f1c7ac306A6.jpg
             * A7 : 6078a482-5607-46c5-b707-ff3edefb9e95_A7.jpg
             * A8 : 1
             * A9 : 6078a482-5607-46c5-b707-ff3edefb9e95_A9.jpg
             */

            private String A6;
            private String A7;
            private String A8;
            private String A9;

            public String getA6() {
                return A6;
            }

            public void setA6(String A6) {
                this.A6 = A6;
            }

            public String getA7() {
                return A7;
            }

            public void setA7(String A7) {
                this.A7 = A7;
            }

            public String getA8() {
                return A8;
            }

            public void setA8(String A8) {
                this.A8 = A8;
            }

            public String getA9() {
                return A9;
            }

            public void setA9(String A9) {
                this.A9 = A9;
            }
        }

        public static class DataList1Bean extends MultiItemEntity {
            /**
             * dwzl : 猪
             * sws : 75
             * zls : 3260.000
             */

            private String dwzl;
            private String sws;
            private String zls;

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

            public String getZls() {
                return zls;
            }

            public void setZls(String zls) {
                this.zls = zls;
            }
        }
    }
}
