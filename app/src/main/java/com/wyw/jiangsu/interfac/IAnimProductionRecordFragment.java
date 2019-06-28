package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.QroducingAreaProcessListBean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;

import java.util.List;

/**
 * Created by wyw on 2017/2/9.
 */
public interface IAnimProductionRecordFragment extends IMVPList {
    void refresh(List<QroducingAreaProcessListBean.DataListBean> dataListBean);

    void loadMore(List<QroducingAreaProcessListBean.DataListBean> dataListBean);

}
