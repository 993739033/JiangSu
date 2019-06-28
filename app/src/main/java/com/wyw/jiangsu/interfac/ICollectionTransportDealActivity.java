package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;

/**
 * Created by song on 2017/6/9.
 */

public interface ICollectionTransportDealActivity extends IMVP {
    void setData(CollectionTransportDealActivityBean.DataBean dataListEntity);
}
