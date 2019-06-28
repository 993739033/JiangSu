package com.wyw.jiangsu.bean;

/**
 * Created by wyw on 2016/11/1.
 */

public class BaseMsgBean extends BaseObjectEntity<BaseMsgBean.Data> {
    public static class Data {
        private String result;
        private String result2;
        private DATA1 result1;
        private String guid; //额外增加的 打印使用的uuid


        public String getResult2() {
            return result2;
        }

        public void setResult2(String result2) {
            this.result2 = result2;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public DATA1 getResult1() {
            return result1;
        }

        public void setResult1(DATA1 result1) {
            this.result1 = result1;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public static class DATA1 {
        private String TableName;
        private String title;
        private String content;

        public String getTableName() {
            return TableName;
        }

        public void setTableName(String tableName) {
            TableName = tableName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
