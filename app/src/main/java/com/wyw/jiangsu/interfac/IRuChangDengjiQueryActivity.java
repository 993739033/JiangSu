package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public interface IRuChangDengjiQueryActivity extends IMVPList {
//    List<RuChangChaYanQueryBean.DataListBean> getData(List<RuChangChaYanQueryBean.DataListBean> dataListEntity);

    void addListData(List<RuChangChaYanQueryBean.DataListBean> dataListEntity);

    void setData(List<RuChangChaYanQueryBean.DataListBean> dataListEntity);
}
