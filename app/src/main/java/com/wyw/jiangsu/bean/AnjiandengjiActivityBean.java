package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Windows on 2017/6/19.
 */

public class AnjiandengjiActivityBean extends BaseArrayObjectEntity<AnjiandengjiActivityBean.DataListBean>{

    public static class DataListBean implements Serializable {
        /**
         * FStId : 5
         * Fly : 案件来源
         * Fdjrq : 2017-06-20
         * Fany : 案由
         * Fajlx : 案件类型
         * Fname : 周科,佴梅
         * Fzfzjh : 00000353,00000307
         */

        private String FStId;
        private String Fly;
        private String Fdjrq;
        private String Fany;
        private String Fajlx;
        private String Fname;
        private String Fzfzjh;
        private String Fanbh;

        public String getFanbh() {
            return Fanbh;
        }

        public void setFanbh(String fanbh) {
            Fanbh = fanbh;
        }

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFly() {
            return Fly;
        }

        public void setFly(String Fly) {
            this.Fly = Fly;
        }

        public String getFdjrq() {
            return Fdjrq;
        }

        public void setFdjrq(String Fdjrq) {
            this.Fdjrq = Fdjrq;
        }

        public String getFany() {
            return Fany;
        }

        public void setFany(String Fany) {
            this.Fany = Fany;
        }

        public String getFajlx() {
            return Fajlx;
        }

        public void setFajlx(String Fajlx) {
            this.Fajlx = Fajlx;
        }

        public String getFname() {
            return Fname;
        }

        public void setFname(String Fname) {
            this.Fname = Fname;
        }

        public String getFzfzjh() {
            return Fzfzjh;
        }

        public void setFzfzjh(String Fzfzjh) {
            this.Fzfzjh = Fzfzjh;
        }
    }

}
