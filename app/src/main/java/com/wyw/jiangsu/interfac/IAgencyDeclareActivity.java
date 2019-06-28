package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AgencyDeclareListBean;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public interface IAgencyDeclareActivity extends IMVPList {

//    void addListData(List<AgencyDeclareListBean.DataListBean> dataListEntity);
//
//    List<AgencyDeclareListBean.DataListBean> setData(List<AgencyDeclareListBean.DataListBean> dataListEntity);

//    List<WuHaiHuaCXbean.DataListBean> getData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void addListData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void setData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);
}
