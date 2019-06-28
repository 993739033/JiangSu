package com.wyw.jiangsu.interfac;


import com.wyw.jiangsu.bean.ChaxunJinduActuvityBean;

import java.util.List;

/**
 * Created by Windows on 2017/6/12.
 */

public interface IChaxunJinduActivity extends IMVPList{
    void addListData(List<ChaxunJinduActuvityBean.DataListBean> dataListEntity);

    void setData(List<ChaxunJinduActuvityBean.DataListBean> dataListEntity);
}
