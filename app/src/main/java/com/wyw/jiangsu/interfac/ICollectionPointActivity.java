package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.V_APP_SJDRYXXQueryBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface ICollectionPointActivity extends IMVPList {

//    void addListData(List<V_APP_SJDRYXXQueryBean.DataListBean> dataListEntity);
//
//    List<V_APP_SJDRYXXQueryBean.DataListBean> setData(List<V_APP_SJDRYXXQueryBean.DataListBean> dataListEntity);

//    List<WuHaiHuaCXbean.DataListBean> getData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void addListData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void setData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);
}
