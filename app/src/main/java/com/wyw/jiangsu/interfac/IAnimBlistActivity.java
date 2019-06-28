package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimAProcessListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface IAnimBlistActivity extends IMVPList {

    void refresh(List<AnimAProcessListBean.DataListBean> dataListBean);

    void loadMore(List<AnimAProcessListBean.DataListBean> dataListBean);
}
