package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.bean.CollectionListBean;

import java.util.List;

/**
 * Created by mnkj004 on 2017/7/27.
 */

public interface IVeterinarianDeclareDetilActivity extends IMVPList{
    void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBeen);
    void setData(AgencyDeclareDetilActivityBean.DataBean dataEntity);
    void uploadSuccessful();
}
