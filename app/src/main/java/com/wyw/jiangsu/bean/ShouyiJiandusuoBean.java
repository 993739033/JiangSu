package com.wyw.jiangsu.bean;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ShouyiJiandusuoBean extends BaseObjectEntity<ShouyiJiandusuoBean.DataBean> {

    public static class DataBean {
        /**
         * result : 18772501365   监督所电话
         * result1 :              官方兽医签字
         */

        private String result;
        private String result1;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getResult1() {
            return result1;
        }

        public void setResult1(String result1) {
            this.result1 = result1;
        }
    }
}
