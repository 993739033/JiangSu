package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.SupervisionVeterinarianListBean;

import java.util.List;

/**
 * Created by wyw on 2016/12/23.
 */
public interface IHarmlessAssignActivity extends IMVP{
    void getPageDetil(HarmlessListDetilBean.DataBean data);

    void getSupervisonVeterinatianList(List<SupervisionVeterinarianListBean.DataListBean> dataBean);

    void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBean);
    void uploadSuccessful();

    void uploadsuccess();
}
