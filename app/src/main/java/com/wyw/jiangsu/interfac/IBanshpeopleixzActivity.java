package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.BanshpeopleleixzActivityBean;

import java.util.List;

/**
 * Created by Windows on 2017/6/20.
 */

public interface IBanshpeopleixzActivity extends IMVPList {

    void setData(List<BanshpeopleleixzActivityBean.DataListBean> dataListEntity);
}
