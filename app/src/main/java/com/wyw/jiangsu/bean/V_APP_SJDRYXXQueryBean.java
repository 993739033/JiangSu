package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * 无害化收集点bean
 * Created by Administrator on 2017/3/30.
 */

public class V_APP_SJDRYXXQueryBean extends BaseArrayObjectEntity<V_APP_SJDRYXXQueryBean.DataListBean> {

    public static class DataListBean implements Serializable {
        /**
         * FStId : 41
         * Fxzxm : 江贤稳
         * Fxxdz : 江苏省南京市玄武区玄武区街道东坝镇沛桥村
         * Fsbrq : 2017-03-28
         * ZLSL : 猪:0
         * Fyssj : 2017-03-31
         * FsjdID : 3
         * Fsjd : 玄武收集点
         */

        private String FStId;
        private String Fxzxm;   //养殖场户姓名
        private String Fxxdz;   //地址
        private String Fsbrq;   //申报日期（不传）
        private String ZLSL;    //病死动物及数量
        private String Fyssj;   //移送时间  (第二次访问的时候，放在第一位)
        private String FsjdID;
        private String Fsjd;


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
    }
}
