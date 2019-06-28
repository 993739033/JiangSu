package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AH_AnimalOrigin;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface IAnimalOriginActivity extends IMVPList {


    void addListData(List<AH_AnimalOrigin.DataListBean> dataListEntity);

//    List<AH_AnimalOrigin.DataListBean> getData(List<AH_AnimalOrigin.DataListBean> dataListEntity);

    void setData(List<AH_AnimalOrigin.DataListBean> dataListEntity);
}
