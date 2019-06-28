package com.wyw.jiangsu.bean.upload;

import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.bean.DoubleRandomCenterBean;

import java.io.Serializable;

/**
 * Created by mnkj on 2018/1/31.
 */

public class UploadDoubleRandomBean implements Serializable {
    private CommonSupervisionBean.Data topData;//企业信息

    private DoubleRandomCenterBean bean;//检查bean

    private String checkTime;//企业名称下检查时间
    private String noSum;//不符合数量
    private String zgTime;//整改时间

    private String jczySign;//检查组员签名
    private String jczySignTime;//检查组员签名时间

    private String sgcySign;//市观察员签名
    private String sgcySignTime;//市观察员签名时间

    private String qyfzrSign;//企业负责人签名
    private String qyfzrSignTime;//企业负责人签名时间

    public CommonSupervisionBean.Data getTopData() {
        return topData;
    }

    public void setTopData(CommonSupervisionBean.Data topData) {
        this.topData = topData;
    }

    public DoubleRandomCenterBean getBean() {
        return bean;
    }

    public void setBean(DoubleRandomCenterBean bean) {
        this.bean = bean;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getNoSum() {
        return noSum;
    }

    public void setNoSum(String noSum) {
        this.noSum = noSum;
    }

    public String getZgTime() {
        return zgTime;
    }

    public void setZgTime(String zgTime) {
        this.zgTime = zgTime;
    }

    public String getJczySign() {
        return jczySign;
    }

    public void setJczySign(String jczySign) {
        this.jczySign = jczySign;
    }

    public String getJczySignTime() {
        return jczySignTime;
    }

    public void setJczySignTime(String jczySignTime) {
        this.jczySignTime = jczySignTime;
    }

    public String getSgcySign() {
        return sgcySign;
    }

    public void setSgcySign(String sgcySign) {
        this.sgcySign = sgcySign;
    }

    public String getSgcySignTime() {
        return sgcySignTime;
    }

    public void setSgcySignTime(String sgcySignTime) {
        this.sgcySignTime = sgcySignTime;
    }

    public String getQyfzrSign() {
        return qyfzrSign;
    }

    public void setQyfzrSign(String qyfzrSign) {
        this.qyfzrSign = qyfzrSign;
    }

    public String getQyfzrSignTime() {
        return qyfzrSignTime;
    }

    public void setQyfzrSignTime(String qyfzrSignTime) {
        this.qyfzrSignTime = qyfzrSignTime;
    }
}
