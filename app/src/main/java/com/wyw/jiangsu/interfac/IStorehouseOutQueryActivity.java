package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.StoreChukuBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by mnkj004 on 2017/8/28.
 */

public interface IStorehouseOutQueryActivity extends IMVPList{
    //    void addListData(List<DeclareNewstListBean.DataListBean> dataListEntity);
//
//    List<DeclareNewstListBean.DataListBean> setData(List<DeclareNewstListBean.DataListBean> dataListEntity);

//    List<WuHaiHuaCXbean.DataListBean> getData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void addListData(List<StoreChukuBean.DataListBean> dataListEntity);

    void setData(List<StoreChukuBean.DataListBean> dataListEntity);
}
