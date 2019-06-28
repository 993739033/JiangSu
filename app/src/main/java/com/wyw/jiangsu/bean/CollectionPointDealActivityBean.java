package com.wyw.jiangsu.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by song on 2017/6/9.
 */

public class CollectionPointDealActivityBean extends BaseObjectEntity<CollectionPointDealActivityBean.DataBean>{

    /**
     * data : {"FStId":"1","Glid":"2","ZSL":"0.000","HDSL":"1","EBHHS":"","RKSL":"1","Fxzxm":"玄武区养殖场","Fxxdz":"江苏省南京市玄武区玄武区街道东坝镇沛桥村","Fyzclx":"乳用","Fxcll":"100","Fsbrq":"2017-05-10","Fdw1":"头","Fbsdwzl":"骆驼","Fsws":"20","Fcbs":"0","QDWErBiaoHao":"","Fsfzh":"430524199411292239","fykth":"43555555321134","lxdh":""}
     */


    public static class DataBean {
        /**
         * FStId : 1
         * Glid : 2
         * ZSL : 0.000
         * HDSL : 1
         * EBHHS :
         * RKSL : 1
         * Fxzxm : 玄武区养殖场
         * Fxxdz : 江苏省南京市玄武区玄武区街道东坝镇沛桥村
         * Fyzclx : 乳用
         * Fxcll : 100
         * Fsbrq : 2017-05-10
         * Fdw1 : 头
         * Fbsdwzl : 骆驼
         * Fsws : 20
         * Fcbs : 0
         * QDWErBiaoHao :
         * Fsfzh : 430524199411292239
         * fykth : 43555555321134
         * lxdh :
         */

        private String FStId;
        private String Glid;
        private String ZSL;
        private String HDSL;
        private String EBHHS;
        private String RKSL;
        private String Fxzxm;
        private String Fxxdz;
        private String Fyzclx;
        private String Fxcll;
        private String Fsbrq;
        private String Fdw1;
        private String Fbsdwzl;
        private String Fsws;
        private String Fcbs;
        private String QDWErBiaoHao;
        private String Fsfzh;
        private String fykth;
        private String lxdh;
        private String xm;
        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
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

        public String getZSL() {
            return ZSL;
        }

        public void setZSL(String ZSL) {
            this.ZSL = ZSL;
        }

        public String getHDSL() {
            return HDSL;
        }

        public void setHDSL(String HDSL) {
            this.HDSL = HDSL;
        }

        public String getEBHHS() {
            return EBHHS;
        }

        public void setEBHHS(String EBHHS) {
            this.EBHHS = EBHHS;
        }

        public String getRKSL() {
            return RKSL;
        }

        public void setRKSL(String RKSL) {
            this.RKSL = RKSL;
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

        public String getFsbrq() {
            return Fsbrq;
        }

        public void setFsbrq(String Fsbrq) {
            this.Fsbrq = Fsbrq;
        }

        public String getFdw1() {
            return Fdw1;
        }

        public void setFdw1(String Fdw1) {
            this.Fdw1 = Fdw1;
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

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }
        private Pictures1Bean pictures1;
        public Pictures1Bean getPictures1() {
            return pictures1;
        }

        public void setPictures1(Pictures1Bean pictures1) {
            this.pictures1 = pictures1;
        }
        public static class Pictures1Bean{
            /**
             * A1 : cd22d022-8332-4292-b29e-cd1d5cf0d6e5_A16.jpg
             * A2 : cd22d022-8332-4292-b29e-cd1d5cf0d6e5_A17.jpg
             * A3 : cd22d022-8332-4292-b29e-cd1d5cf0d6e5_A15.jpg
             */

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
            private String A18;
            private String A19;
            private String A20;


            public String getA18() {
                return A18;
            }

            public void setA18(String a18) {
                A18 = a18;
            }

            public String getA19() {
                return A19;
            }

            public void setA19(String a19) {
                A19 = a19;
            }

            public String getA20() {
                return A20;
            }

            public void setA20(String a20) {
                A20 = a20;
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
    }
}
