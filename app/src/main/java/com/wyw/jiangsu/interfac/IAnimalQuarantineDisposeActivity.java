package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface IAnimalQuarantineDisposeActivity extends IMVPList {


    void addListData(List<AnimProcessListBean.DataListBean> dataListEntity);

//    List<AnimProcessListBean.DataListBean> getData(List<AnimProcessListBean.DataListBean> dataListEntity);

    void setData(List<AnimProcessListBean.DataListBean> dataListEntity);
}
