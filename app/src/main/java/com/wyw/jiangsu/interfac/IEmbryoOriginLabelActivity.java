package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface IEmbryoOriginLabelActivity extends IMVPList {

    void addListData(List<AnimZhongDanRedordBean.DataListBean> dataListEntity);

//    List<AnimZhongDanRedordBean.DataListBean> getData(List<AnimZhongDanRedordBean.DataListBean> dataListEntity);

    void setData(List<AnimZhongDanRedordBean.DataListBean> dataListEntity);
}
