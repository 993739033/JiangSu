package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.JianyiChuliTongzhidanListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface IJianyiTongzhidanQueryActivity extends IMVPList {
//    List<JianyiChuliTongzhidanListBean.DataListBean> getData(List<JianyiChuliTongzhidanListBean.DataListBean> dataListEntity);

    void addListData(List<JianyiChuliTongzhidanListBean.DataListBean> dataListEntity);

    void setData(List<JianyiChuliTongzhidanListBean.DataListBean> dataListEntity);
}
