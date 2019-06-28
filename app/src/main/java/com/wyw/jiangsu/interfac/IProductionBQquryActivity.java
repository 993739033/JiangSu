package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.ProductBListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public interface IProductionBQquryActivity extends IMVPList {
    //    List<ProductBListBean.DataListEntity> getData(List<ProductBListBean.DataListEntity> list);
    void addListData(List<ProductBListBean.DataListEntity> list);

    void setData(List<ProductBListBean.DataListEntity> dataListEntity);
}
