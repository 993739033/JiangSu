package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.TongzhiGGBean;

import java.util.List;

/**
 * Created by Windows on 2017/9/25.
 */

public interface IShowSdActivity extends IMVPList {

    void refresh(List<TongzhiGGBean.DataListBean> bean);

    void loadMore(List<TongzhiGGBean.DataListBean> bean);
}
