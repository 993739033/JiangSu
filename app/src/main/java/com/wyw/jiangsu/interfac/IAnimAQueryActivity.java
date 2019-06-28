package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimAQueryListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public interface IAnimAQueryActivity extends IMVPList {
//    List<AnimAQueryListBean.DataListEntity>  getData(List<AnimAQueryListBean.DataListEntity> list);

    void addListData(List<AnimAQueryListBean.DataListEntity> list);

    void setData(List<AnimAQueryListBean.DataListEntity> dataListEntity);
}
