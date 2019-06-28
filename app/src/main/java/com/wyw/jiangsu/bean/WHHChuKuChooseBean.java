package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class WHHChuKuChooseBean extends BaseArrayObjectEntity<WHHChuKuChooseBean.DataListBean> implements Serializable {

    public static class DataListBean implements Serializable {
        /**
         * FSenterpriseId : 6
         * Fbsdwzl : 猪
         * Fsws : 47
         * ZSL : 0.000
         */

        private String FSenterpriseId;
        private String Fbsdwzl;
        private String Fsws;
        private String ZSL;
        private boolean Cheak;

        public String getFSenterpriseId() {
            return FSenterpriseId;
        }

        public void setFSenterpriseId(String FSenterpriseId) {
            this.FSenterpriseId = FSenterpriseId;
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

        public String getZSL() {
            return ZSL;
        }

        public void setZSL(String ZSL) {
            this.ZSL = ZSL;
        }

        public boolean isCheak() {
            return Cheak;
        }

        public void setCheak(boolean cheak) {
            Cheak = cheak;
        }
    }
}
