package com.wyw.jiangsu.bean;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NoDealWithBean extends BaseArrayObjectEntity<NoDealWithBean.DataListBean> {

    public static class DataListBean {
        /**
         * qxid : 28
         * sum : 22
         */

        private String qxid;
        private String sum;

        public String getQxid() {
            return qxid;
        }

        public void setQxid(String qxid) {
            this.qxid = qxid;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }
    }
}
