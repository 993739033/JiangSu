package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface IAnimZhongDanRecordFragment extends IMVPList {

    void refresh(List<AnimZhongDanRedordBeangai.DataListBean> dataListBeen);

    void loadMore(List<AnimZhongDanRedordBeangai.DataListBean> dataListBeen);
}
