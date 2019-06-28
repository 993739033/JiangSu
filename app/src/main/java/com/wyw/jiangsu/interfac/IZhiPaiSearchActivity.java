package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.WHHZhiPaiChooseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface IZhiPaiSearchActivity extends IMVPList {
    void searchComplete(List<WHHZhiPaiChooseBean.DataListBean> userDetilBean);

    void loadMore(List<WHHZhiPaiChooseBean.DataListBean> userDetilBean);
}
