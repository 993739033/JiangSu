package com.wyw.jiangsu.bean;

/**
 * Created by zhyzh on 2017/4/17.
 */

public class SlaughterAnimTypeBean extends BaseArrayObjectEntity<SlaughterAnimTypeBean.DataListEntity> {

    /**
     * errorCode : 0
     * errorMsg : msg
     * dataList : [{"QCProduct":"猪"}]
     */

    public static class DataListEntity {
        /**
         * QCProduct : 猪
         */

        private String QCProduct;

        public void setQCProduct(String QCProduct) {
            this.QCProduct = QCProduct;
        }

        public String getQCProduct() {
            return QCProduct;
        }
    }
}
