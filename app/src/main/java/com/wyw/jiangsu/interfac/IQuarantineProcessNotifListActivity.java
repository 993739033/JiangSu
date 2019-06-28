package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.QuarantineProcessNotifListBean;

import java.util.List;

/**
 * Created by wyw on 2017/2/10.
 */
public interface IQuarantineProcessNotifListActivity extends IMVPList {

    void refresh(List<QuarantineProcessNotifListBean.DataListBean> dataListBean);

    void loadMore(List<QuarantineProcessNotifListBean.DataListBean> dataListBean);

    void uploadsuccess();
}
