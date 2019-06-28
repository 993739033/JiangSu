package com.wyw.jiangsu.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by song on 2017/6/9.
 */

public class CollectionTransportDealActivityBean extends BaseObjectEntity<CollectionTransportDealActivityBean.DataBean> {

    public static class DataBean {
        /**
         * FStId : 1
         * Glid : 1
         * pictures1 : {"A1":"e00eab85-18d5-47d4-b865-344fe057d575A1.jpg","A2":"f14b952e-2bc4-4e58-9b04-bb4a54b111b2_A2.jpg","A3":"1"}
         * dataList1 : [{"dwzl":"猪","sws":"1","zls":"1.000"},{"dwzl":"猪","sws":"4","zls":"0.000"}]
         * FGlid : 1,2
         * HSQFH :
         * JSQFH : 1
         * QDSL : 1
         * Fxzxm : test1
         * Fxxdz : 江苏省南京市玄武区玄武区街道111
         */

        private String FStId;
        private String Glid;
        private Pictures1Bean pictures1;
        private String FGlid;
        private String HSQFH;
        private String JSQFH;
        private String QDSL;
        private String Fxzxm;
        private String Fxxdz;
        private String FScTime;
        private List<DataList1Bean> dataList1;


        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

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

        public String getQDSL() {
            return QDSL;
        }

        public void setQDSL(String QDSL) {
            this.QDSL = QDSL;
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

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public static class Pictures1Bean {
            /**
             * A1 : e00eab85-18d5-47d4-b865-344fe057d575A1.jpg
             * A2 : f14b952e-2bc4-4e58-9b04-bb4a54b111b2_A2.jpg
             * A3 : 1
             */

            private String A1;
            private String A2;
            private String A3;

            public String getA1() {
                return A1;
            }

            public void setA1(String A1) {
                this.A1 = A1;
            }

            public String getA2() {
                return A2;
            }

            public void setA2(String A2) {
                this.A2 = A2;
            }

            public String getA3() {
                return A3;
            }

            public void setA3(String A3) {
                this.A3 = A3;
            }
        }

        public static class DataList1Bean {
            /**
             * dwzl : 猪
             * sws : 1
             * zls : 1.000
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
