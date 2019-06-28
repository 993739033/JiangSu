package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.UserDetilBean;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 */
public interface IHarmlessReplaceApplyActivity extends IMVP {
    void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBeen);
    void getAnimType(AnimTypeListBean.DataBean list);

    void uploadSuccessful();

    void setUserMsg(UserDetilBean.Data data);
}
