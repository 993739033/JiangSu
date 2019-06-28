package com.wyw.jiangsu.bean;

/**
 * Created by wyw on 2016/12/28.
 * 获取病死畜禽移送收集点
 */

public class CollectionListBean extends BaseArrayObjectEntity<CollectionListBean.DataListBean>{

    /**
     * FStId : 1
     * FsjdName : 测试
     */

    public static class DataListBean {
        private String FStId;
        private String FsjdName;

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
    }
}
