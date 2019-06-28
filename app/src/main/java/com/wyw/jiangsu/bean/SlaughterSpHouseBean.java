package com.wyw.jiangsu.bean;

/**
 * Created by zhyzh on 2017/4/17.
 */

public class SlaughterSpHouseBean extends BaseArrayObjectEntity<SlaughterSpHouseBean.DataListEntity> {
    public static class DataListEntity {
        /**
         * FSenterpriseName : 超级管理员单位
         * FSenterpriseId : 1
         */

        private String FSenterpriseName;
        private String FSenterpriseId;

        public void setFSenterpriseName(String FSenterpriseName) {
            this.FSenterpriseName = FSenterpriseName;
        }

        public void setFSenterpriseId(String FSenterpriseId) {
            this.FSenterpriseId = FSenterpriseId;
        }

        public String getFSenterpriseName() {
            return FSenterpriseName;
        }

        public String getFSenterpriseId() {
            return FSenterpriseId;
        }
    }
}
