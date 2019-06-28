package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Windows on 2017/6/8.
 */

public class YangzhihuDetailActivityBean extends BaseObjectEntity<YangzhihuDetailActivityBean.DataBean> implements Serializable {


    public static class DataBean implements Serializable {
        /**
         * FStId : 1
         * Fxzxm : 玄武区养殖场
         * pictures1 : {"A1":"1e111379-5665-4615-99da-5826d82ec105_A1.jpg","A2":"1e111379-5665-4615-99da-5826d82ec105_A2.jpg"}
         * Fyzclx : 乳用
         * Fxcll : 100
         * Fsbrq : 2017-05-10
         * Fbsdwzl : 鹿
         * Fsws : 10
         * Fcbs : 1
         * QDWErBiaoHao :
         * Fysswyy : 正常死亡
         * Fsiwh :
         * Fclfs : 集中处理
         * Fqm :
         * Fxxdz : 江苏省南京市玄武区玄武区街道东坝镇沛桥村
         * Fclfs2 :
         * Fdw1 : 头
         * Fsfzh : 430524199411292239
         * fykth : 43555555321134
         * Glid : 9
         * lxdh :
         */

        private String FStId;
        private String Fxzxm;
        private Pictures1Bean pictures1;
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
        private String lxdh;
        private String ZPR;
        private String xm;
        //勘验方式
        private String KYFS;
        //移送时间
        private String YSSJ;

        public String getKYFS() {
            return KYFS;
        }

        public void setKYFS(String KYFS) {
            this.KYFS = KYFS;
        }

        public String getYSSJ() {
            return YSSJ;
        }

        public void setYSSJ(String YSSJ) {
            this.YSSJ = YSSJ;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }


        public String getZPR() {
            return ZPR;
        }

        public void setZPR(String ZPR) {
            this.ZPR = ZPR;
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

        public Pictures1Bean getPictures1() {
            return pictures1;
        }

        public void setPictures1(Pictures1Bean pictures1) {
            this.pictures1 = pictures1;
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

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public static class Pictures1Bean implements Serializable {
            /**
             * A1 : 1e111379-5665-4615-99da-5826d82ec105_A1.jpg
             * A2 : 1e111379-5665-4615-99da-5826d82ec105_A2.jpg
             */

            private String A1;
            private String A2;

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
        }
    }
}
