package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public interface IHarmlessFlow2Activity extends IMVP{
    void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists);
    void onActivityResult(RefreshBus bean);
}
