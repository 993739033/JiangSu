package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public interface IRuchangQuerenSearchActivity extends IMVPList {

    void searchComplete(List<RuChangChaYanQueryBean.DataListBean> userDetilBean);

    void loadMore(List<RuChangChaYanQueryBean.DataListBean> userDetilBean);
}
