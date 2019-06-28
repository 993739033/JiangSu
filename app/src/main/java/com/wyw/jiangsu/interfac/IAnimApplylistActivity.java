package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.bean.upload.AnimListBean;

import java.util.List;

/**
 * Created by wyw on 2016/12/20.
 */
public interface IAnimApplylistActivity extends IMVPList{

    void refresh(List<GuarantineDeclareListDetilBean.DataListBean> dataListBean);
    void loadMore(List<GuarantineDeclareListDetilBean.DataListBean> dataListBean);
}
