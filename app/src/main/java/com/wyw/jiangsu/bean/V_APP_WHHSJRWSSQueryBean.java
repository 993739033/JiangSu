package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * 无害化收集运输bean
 * Created by Administrator on 2017/3/30.
 */

public class V_APP_WHHSJRWSSQueryBean extends BaseArrayObjectEntity<V_APP_WHHSJRWSSQueryBean.DataListBean> {


    public static class DataListBean {
        /**
         * FStId : 15
         * Fxzxm : 玄武收集点
         * Fxxdz : 江苏省南京市玄武区玄武区街道
         * Fsbrq : 2017-03-28
         * ZLSL : 猪:0
         * SWZL : 猪:0.000
         * FScTime : 2017-03-29
         * SJRID : 113
         */

        private String FStId;
        private String Fxzxm;       //收集点名称
        private String Fxxdz;       //地址
        private String Fsbrq;       //申报日期
        private String ZLSL;        //病死动物及数量
        private String SWZL;        //病死动物重量
        private String FScTime;     //分派收集日期
        private String SJRID;

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

        public String getSWZL() {
            return SWZL;
        }

        public void setSWZL(String SWZL) {
            this.SWZL = SWZL;
        }

        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public String getSJRID() {
            return SJRID;
        }

        public void setSJRID(String SJRID) {
            this.SJRID = SJRID;
        }
    }
}
