package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.bean.VerficationListBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface IVerificationLocaActivity extends IMVPList {

    void addListData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);

    void setData(List<WuHaiHuaCXbean.DataListBean> dataListEntity);
}
