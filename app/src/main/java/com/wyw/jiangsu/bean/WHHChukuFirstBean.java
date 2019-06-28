package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mnkj004 on 2017/8/15.
 */

public class WHHChukuFirstBean extends BaseArrayObjectEntity<WHHChukuFirstBean.DataListBean> {


    public static class DataListBean implements Serializable{
        /**
         * FStId : 6
         * FsjdName : test1
         * pjdiz : 姹熻嫃鐪佸崡浜競鐜勬鍖虹巹姝﹀尯琛楅亾111
         * FSm1 : 22
         * FSm2 : 娴嬭瘯
         */

        private String FStId;
        private String FsjdName;
        private String pjdiz;
        private String FSm1;
        private String FSm2;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFsjdName() {
            return FsjdName;
        }

        public void setFsjdName(String FsjdName) {
            this.FsjdName = FsjdName;
        }

        public String getPjdiz() {
            return pjdiz;
        }

        public void setPjdiz(String pjdiz) {
            this.pjdiz = pjdiz;
        }

        public String getFSm1() {
            return FSm1;
        }

        public void setFSm1(String FSm1) {
            this.FSm1 = FSm1;
        }

        public String getFSm2() {
            return FSm2;
        }

        public void setFSm2(String FSm2) {
            this.FSm2 = FSm2;
        }
    }
}
