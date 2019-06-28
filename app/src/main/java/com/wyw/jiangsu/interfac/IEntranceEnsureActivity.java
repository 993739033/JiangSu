package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.V_APP_ZCSYXXQueryBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface IEntranceEnsureActivity extends IMVPList {

//    void addListData(List<V_APP_ZCSYXXQueryBean.DataListBean> dataListEntity);
//
//    List<V_APP_ZCSYXXQueryBean.DataListBean> setData(List<V_APP_ZCSYXXQueryBean.DataListBean> dataListEntity);

//    List<WuHaiHuaCXbean.DataListBean> getData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void addListData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void setData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);
}
