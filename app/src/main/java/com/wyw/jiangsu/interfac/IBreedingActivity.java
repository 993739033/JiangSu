
package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.BreedingRecordData;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface IBreedingActivity extends IMVPList {

    //    List<AnimalQuarantineActivityBean.DataListBean> getData(List<AnimalQuarantineActivityBean.DataListBean> dataListEntity);
//    getData(List<AnimalQuarantineActivityBean.DataListBean> dataListEntity);

    void addListData(List<BreedingRecordData.DataListBean> dataListEntity);

    void setData(List<BreedingRecordData.DataListBean> dataList);
}