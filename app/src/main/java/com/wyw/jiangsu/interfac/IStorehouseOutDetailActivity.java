package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.ChuKuDeatilBean;
import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;


public interface IStorehouseOutDetailActivity extends IMVPList{

    void setData(ChuKuDeatilBean.DataBean dataListEntity);
    void deletesucess(BaseMsgBean msg);
}