package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mnkj004 on 2017/8/10.
 */

public class BreedingDetailshowBean extends BaseObjectEntity<BreedingDetailshowBean.DataListBean> {
    public static class DataListBean implements Serializable {


        private List<DataList1Bean> dataList1;
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

        public static class DataList1Bean implements Serializable{
            /**
             * Name :  1.有有效的防疫条件合格证及年度报告记录。
             * JL : 是
             * QK :
             * Tp :
             */

            private String Name;
            private String JL;
            private String QK;
            private String Tp;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getJL() {
                return JL;
            }

            public void setJL(String JL) {
                this.JL = JL;
            }

            public String getQK() {
                return QK;
            }

            public void setQK(String QK) {
                this.QK = QK;
            }

            public String getTp() {
                return Tp;
            }

            public void setTp(String Tp) {
                this.Tp = Tp;
            }
        }

        public static class DataList2Bean  implements Serializable{
            /**
             * Qz : ca904716-56e1-487d-9c8f-66ec822d3a70A2.jpg
             */

            private String Qz;

            public String getQz() {
                return Qz;
            }

            public void setQz(String Qz) {
                this.Qz = Qz;
            }
        }
    }
}
