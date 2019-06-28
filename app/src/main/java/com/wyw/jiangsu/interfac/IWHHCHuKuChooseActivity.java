package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.WHHChuKuChooseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public interface IWHHCHuKuChooseActivity extends IMVPList {

    void setData(List<WHHChuKuChooseBean.DataListBean> dataListEntities);
}
