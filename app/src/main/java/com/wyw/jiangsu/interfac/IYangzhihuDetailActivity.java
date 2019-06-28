package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.BaseMsg;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;

import java.util.List;

/**
 * Created by Windows on 2017/6/8.
 */

public interface IYangzhihuDetailActivity extends IMVPList{

    void setData(YangzhihuDetailActivityBean.DataBean dataListEntity);
    void deletesucess(BaseMsgBean msg);
}
