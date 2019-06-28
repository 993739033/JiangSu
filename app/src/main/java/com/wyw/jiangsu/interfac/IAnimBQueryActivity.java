package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimBQueryListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public interface IAnimBQueryActivity extends IMVPList {
    //    List<AnimBQueryListBean.DataListEntity> getData(List<AnimBQueryListBean.DataListEntity> list);
    void addListData(List<AnimBQueryListBean.DataListEntity> list);

    void setData(List<AnimBQueryListBean.DataListEntity> dataListEntity);
}
