package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.HarmlessListDetilBean;

/**
 * Created by wyw on 2016/12/23.
 */
public interface IHarmlessMessageConfirmCollectionActivity extends IMVP{
    void getPageDetil(HarmlessListDetilBean.DataBean dataBean);
    void uploadSuccessful();

    void uploadsuccess();
}
