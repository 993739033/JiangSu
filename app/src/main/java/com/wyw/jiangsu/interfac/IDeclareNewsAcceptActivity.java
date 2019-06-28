package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.DeclareNewstListBean;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface IDeclareNewsAcceptActivity extends IMVPList {

//    void addListData(List<DeclareNewstListBean.DataListBean> dataListEntity);
//
//    List<DeclareNewstListBean.DataListBean> setData(List<DeclareNewstListBean.DataListBean> dataListEntity);

//    List<WuHaiHuaCXbean.DataListBean> getData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void addListData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void setData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);
}
