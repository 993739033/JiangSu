package com.wyw.jiangsu.bean;


import com.wyw.jiangsu.view.recyclerview.entity.MultiItemEntity;

import java.util.List;

/**
 * 入场确认查询详情Bean
 * Created by song on 2017/6/16.
 */

public class WuhaihuaDateBean extends BaseObjectEntity<WuhaihuaDateBean.DataBean> {

    public static class DataBean extends MultiItemEntity {
        /**
         * FStId : 1
         * Glid : 2
         * pictures1 : {"A1":"a49ba1bb-5a74-4b8d-b376-e47a1c816467A1.jpg","A2":"c72f3831-e65b-4283-aa82-5635669fa3eaA2.jpg","A3":"1","A4":"1","A5":"1"}
         * dataList1 : [{"Fxzxm":"test1","Fxxdz":"江苏省南京市玄武区玄武区街道111","Fbsdwzl":"鹿","Fsws":"9","SWZL":"0.000"},{"Fxzxm":"test1","Fxxdz":"江苏省南京市玄武区玄武区街道111","Fbsdwzl":"猪","Fsws":"10","SWZL":"0.000"}]
         * dataList2 : [{"dwzl":"猪","sws":"4","zls":"0.00"}]
         * FGlid : 1,2
         * SFCL : 否
         * CPH : 1
         * SJRQ : 2017-06-16
         * SJR : 测试无害化处理厂收集人员
         * RCRQ : 2017-06-16 16:10:22
         * HJ : 0.000
         * QRZL : 1.000
         * id : 22
         */

        private String FStId;
        private String Glid;
        private Pictures1Bean pictures1;
        private String FGlid;
        private String SFCL;
        private String CPH;
        private String SJRQ;
        private String SJR;
        private String RCRQ;
        private String HJ;
        private String QRZL;
        private String id;
        private List<DataList1Bean> dataList1;
        private List<DataList2Bean> dataList2;

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

        public String getSFCL() {
            return SFCL;
        }

        public void setSFCL(String SFCL) {
            this.SFCL = SFCL;
        }

        public String getCPH() {
            return CPH;
        }

        public void setCPH(String CPH) {
            this.CPH = CPH;
        }

        public String getSJRQ() {
            return SJRQ;
        }

        public void setSJRQ(String SJRQ) {
            this.SJRQ = SJRQ;
        }

        public String getSJR() {
            return SJR;
        }

        public void setSJR(String SJR) {
            this.SJR = SJR;
        }

        public String getRCRQ() {
            return RCRQ;
        }

        public void setRCRQ(String RCRQ) {
            this.RCRQ = RCRQ;
        }

        public String getHJ() {
            return HJ;
        }

        public void setHJ(String HJ) {
            this.HJ = HJ;
        }

        public String getQRZL() {
            return QRZL;
        }

        public void setQRZL(String QRZL) {
            this.QRZL = QRZL;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public List<DataList2Bean> getDataList2() {
            return dataList2;
        }

        public void setDataList2(List<DataList2Bean> dataList2) {
            this.dataList2 = dataList2;
        }

        public static class Pictures1Bean {
            /**
             * A1 : a49ba1bb-5a74-4b8d-b376-e47a1c816467A1.jpg
             * A2 : c72f3831-e65b-4283-aa82-5635669fa3eaA2.jpg
             * A3 : 1
             * A4 : 1
             * A5 : 1
             */

            private String A1;
            private String A2;
            private String A3;
            private String A4;
            private String A5;

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

            public String getA4() {
                return A4;
            }

            public void setA4(String A4) {
                this.A4 = A4;
            }

            public String getA5() {
                return A5;
            }

            public void setA5(String A5) {
                this.A5 = A5;
            }
        }

        public static class DataList1Bean {
            /**
             * Fxzxm : test1
             * Fxxdz : 江苏省南京市玄武区玄武区街道111
             * Fbsdwzl : 鹿
             * Fsws : 9
             * SWZL : 0.000
             */

            private String Fxzxm;
            private String Fxxdz;
            private String Fbsdwzl;
            private String Fsws;
            private String SWZL;

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
        }

        public static class DataList2Bean extends MultiItemEntity {
            /**
             * dwzl : 猪
             * sws : 4
             * zls : 0.00
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
