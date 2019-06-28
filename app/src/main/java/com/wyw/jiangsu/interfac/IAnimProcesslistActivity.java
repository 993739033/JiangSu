package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;

import java.util.List;

/**
 * Created by wyw on 2016/12/20.
 */
public interface IAnimProcesslistActivity extends IMVPList {

    void refresh(List<AnimProcessListBean.DataListBean> dataListBean);

    void loadMore(List<AnimProcessListBean.DataListBean> dataListBean);
}
