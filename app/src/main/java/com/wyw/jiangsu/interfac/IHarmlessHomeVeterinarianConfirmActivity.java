package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.HarmlessListDetilBean;

/**
 * Created by wyw on 2016/12/23.
 */
public interface IHarmlessHomeVeterinarianConfirmActivity extends IMVP{
    void getPageDetil(HarmlessListDetilBean.DataBean dataBean);
    void uploadSuccessful();
}
