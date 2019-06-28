package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.QuarantineDealListQueryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface IQuarantineProcessNotifQuaryActivity extends IMVPList{
    void refresh(List<QuarantineDealListQueryBean.DataListBean> dataListBean);
    void loadMore(List<QuarantineDealListQueryBean.DataListBean> dataListBean);
}
