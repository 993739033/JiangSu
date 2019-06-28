package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;

/**
 * Created by wyw on 2017/2/9.
 */
public interface IAnimRuZhongRecordActivity extends IMVP{
    void uploadsuccess();
    void openPrintDialog();

    void bianhao(ShenbaoJiluBianhaoBean.DataBean dataListBean);

    void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean);
    void getPrintid(String id);
}
