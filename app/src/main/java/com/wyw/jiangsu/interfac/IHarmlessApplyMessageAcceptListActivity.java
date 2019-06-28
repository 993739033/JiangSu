package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.HarmlessListBean;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 */
public interface IHarmlessApplyMessageAcceptListActivity extends IMVPList{

    void refresh(List<HarmlessListBean.DataEntity> entity);

    void loadMore(List<HarmlessListBean.DataEntity> entity);
}
