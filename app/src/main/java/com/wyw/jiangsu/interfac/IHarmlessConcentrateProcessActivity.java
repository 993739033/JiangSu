package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.HarmlessListDetilBean;

/**
 * Created by wyw on 2016/12/27.
 */
public interface IHarmlessConcentrateProcessActivity extends IMVP{
    void getPageDetil(HarmlessListDetilBean.DataBean dataBean);
    void uploadSuccessful();
}
