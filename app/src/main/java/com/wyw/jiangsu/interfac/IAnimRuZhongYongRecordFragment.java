package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordListBean;

import java.util.List;

/**
 * Created by wyw on 2017/2/9.
 */
public interface IAnimRuZhongYongRecordFragment extends IMVPList {

    void refresh(List<AnimRuZhongYongRecordListBean.DataListBean> dataListBean);

    void loadMore(List<AnimRuZhongYongRecordListBean.DataListBean> dataListBean);
}
