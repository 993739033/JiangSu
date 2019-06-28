package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public interface IFarmDeclareActivity extends IMVPList {

//    void addListData(List<FaramDeclareListBean.DataListBean> dataListEntity);
//
//    List<FaramDeclareListBean.DataListBean> setData(List<FaramDeclareListBean.DataListBean> dataListEntity);

//    List<WuHaiHuaCXbean.DataListBean> getData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void addListData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void setData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);
}
