package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class UploadNoDealWithBean {

    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * qxid : 39
         * TableName :
         */

        private int qxid;
        private String TableName;

        public int getQxid() {
            return qxid;
        }

        public void setQxid(int qxid) {
            this.qxid = qxid;
        }

        public String getTableName() {
            return TableName;
        }

        public void setTableName(String TableName) {
            this.TableName = TableName;
        }
    }
}
