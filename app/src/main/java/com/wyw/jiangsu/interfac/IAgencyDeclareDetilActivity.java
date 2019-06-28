package com.wyw.jiangsu.interfac;


import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.bean.BaseMsgBean;

/**
 * Created by Windows on 2017/6/9.
 */

public interface IAgencyDeclareDetilActivity extends IMVPList{
    void setData(AgencyDeclareDetilActivityBean.DataBean dataEntity);
    void deletesucess(BaseMsgBean msg);
}
