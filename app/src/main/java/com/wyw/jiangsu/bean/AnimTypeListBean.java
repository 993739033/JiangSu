package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by wyw on 2016/12/29.
 * 无害化动物种类
 */
public class AnimTypeListBean extends BaseObjectEntity<AnimTypeListBean.DataBean>{
    public static class DataBean {
        /**
         * ZL : 猪
         */

        private List<DataList1Bean> dataList1;
        /**
         * ZL : 猪
         */

        private List<DataList2Bean> dataList2;

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public List<DataList2Bean> getDataList2() {
            return dataList2;
        }

        public void setDataList2(List<DataList2Bean> dataList2) {
            this.dataList2 = dataList2;
        }

        public static class DataList1Bean {
            private String ZL;

            public String getZL() {
                return ZL;
            }

            public void setZL(String ZL) {
                this.ZL = ZL;
            }
        }

        public static class DataList2Bean {
            private String ZL;

            public String getZL() {
                return ZL;
            }

            public void setZL(String ZL) {
                this.ZL = ZL;
            }
        }
    }
}
