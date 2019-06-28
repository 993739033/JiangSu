package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class WHHZhiPaiBean extends BaseArrayObjectEntity<WHHZhiPaiBean.DataListBean> {

    public static class DataListBean {
        /**
         * FStId : 344
         * dataList1 : [{"CPH1":"1"}]
         * FuName : 测试无害化处理厂收集人员
         */

        private String FStId;       //收集人id
        private String FuName;      //收集人
        private List<DataList1Bean> dataList1;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFuName() {
            return FuName;
        }

        public void setFuName(String FuName) {
            this.FuName = FuName;
        }

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public static class DataList1Bean {
            /**
             * CPH1 : 1
             */

            private String CPH1;        //车牌号

            public String getCPH1() {
                return CPH1;
            }

            public void setCPH1(String CPH1) {
                this.CPH1 = CPH1;
            }
        }
    }
}
