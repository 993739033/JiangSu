package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 */
public interface IHarmlessFlowActivity extends IMVP{
    void showToast(String msg);
    void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists);
    void onActivityResult(RefreshBus bean);
}
