package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.HarmlessShujiDetilBean;

/**
 * Created by wyw on 2016/12/23.
 */
public interface IHarmlessPlantCollectionConfirmActivity extends IMVP {
//    void getPageDetil(HarmlessListDetilBean.DataBean dataBean);

    void uploadSuccessful();

    void getPageDetil(HarmlessShujiDetilBean.DataBean dataBean);
}
