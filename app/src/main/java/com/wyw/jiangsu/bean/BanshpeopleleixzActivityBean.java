package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Windows on 2017/6/20.
 */

public class BanshpeopleleixzActivityBean extends BaseArrayObjectEntity<BanshpeopleleixzActivityBean.DataListBean> {
    public static class DataListBean implements Serializable {
        /**
         * Fname : 周科
         * FUID : 2
         * Fzfzjh : 00000353
         */

        private String Fname;
        private String FUID;
        private String Fzfzjh;
        private String Fdwei;
        private boolean Cheak;

        public String getFdwei() {
            return Fdwei;
        }

        public void setFdwei(String fdwei) {
            Fdwei = fdwei;
        }

        public String getFname() {
            return Fname;
        }

        public void setFname(String Fname) {
            this.Fname = Fname;
        }

        public String getFUID() {
            return FUID;
        }

        public void setFUID(String FUID) {
            this.FUID = FUID;
        }

        public String getFzfzjh() {
            return Fzfzjh;
        }

        public void setFzfzjh(String Fzfzjh) {
            this.Fzfzjh = Fzfzjh;
        }

        public boolean isCheak() {
            return Cheak;
        }

        public void setCheak(boolean cheak) {
            Cheak = cheak;
        }
    }
}
