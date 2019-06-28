package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by mnkj004 on 2017/7/27.
 */

public interface IVeterinarianDeclareActivity  extends IMVPList{
    void addListData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void setData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);
}
