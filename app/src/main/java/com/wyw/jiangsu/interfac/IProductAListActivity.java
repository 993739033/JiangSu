package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.ProductA_BBeanListGai;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface IProductAListActivity extends IMVPList{

    void refresh(List<ProductA_BBeanListGai.DataListBean> dataListBean);

    void loadMore(List<ProductA_BBeanListGai.DataListBean> dataListBean);

}
