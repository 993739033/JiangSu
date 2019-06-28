package com.wyw.jiangsu.bean;

/**
 * 申报单，记录单编号
 * Created by Administrator on 2017/4/10.
 */

public class ShenbaoJiluBianhaoBean extends BaseObjectEntity<ShenbaoJiluBianhaoBean.DataBean>{

    public static class DataBean {
        /**
         * result : 苏(320000)1704101318424390
         */

        private String result;
        private String shi;
        private String xian;
        private String xiang;

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getXian() {
            return xian;
        }

        public void setXian(String xian) {
            this.xian = xian;
        }

        public String getXiang() {
            return xiang;
        }

        public void setXiang(String xiang) {
            this.xiang = xiang;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
