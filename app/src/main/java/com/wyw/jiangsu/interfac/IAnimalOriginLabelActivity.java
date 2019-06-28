package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface IAnimalOriginLabelActivity extends IMVPList {


    void addListData(List<AnimRuZhongYongRecordBean.DataListBean> dataListEntity);

//    List<AnimRuZhongYongRecordBean.DataListBean> getData(List<AnimRuZhongYongRecordBean.DataListBean> dataListEntity);

    void setData(List<AnimRuZhongYongRecordBean.DataListBean> dataListEntity);
}
