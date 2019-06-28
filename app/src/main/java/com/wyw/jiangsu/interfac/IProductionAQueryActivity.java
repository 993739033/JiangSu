package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.ProductionAListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public interface IProductionAQueryActivity extends IMVPList{
//    List<ProductionAListBean.DataListEntity> getData(List<ProductionAListBean.DataListEntity> list);
    void addListData(List<ProductionAListBean.DataListEntity> list);

    void setData(List<ProductionAListBean.DataListEntity> dataListEntity);
}
