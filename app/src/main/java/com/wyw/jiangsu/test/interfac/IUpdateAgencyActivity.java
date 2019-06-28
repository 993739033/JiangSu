package com.wyw.jiangsu.test.interfac;

import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.interfac.IMVP;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
public interface IUpdateAgencyActivity extends IMVP {
    void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBeen);
    void getAnimType(AnimTypeListBean.DataBean list);

    void uploadSuccessful();

    void setUserMsg(UserDetilBean.Data data);
}
