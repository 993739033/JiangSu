package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface IAnimalQuarantineActivity extends IMVPList {

    //    List<AnimalQuarantineActivityBean.DataListBean> getData(List<AnimalQuarantineActivityBean.DataListBean> dataListEntity);
//    getData(List<AnimalQuarantineActivityBean.DataListBean> dataListEntity);

    void addListData(List<AnimalQuarantineActivityBean.DataListBean> dataListEntity);

    void setData(List<AnimalQuarantineActivityBean.DataListBean> dataList);
}
