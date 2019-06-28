package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.WHHZhiPaiBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface IWHHZhiPaiActivity extends IMVP {

    void zhipaiXinxi(List<WHHZhiPaiBean.DataListBean> dataListBean);

    void upLoadSucceed();
}
