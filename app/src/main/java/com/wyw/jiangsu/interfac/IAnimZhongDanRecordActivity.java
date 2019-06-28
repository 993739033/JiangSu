package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;

/**
 * Created by wyw on 2017/2/15.
 */
public interface IAnimZhongDanRecordActivity extends IMVP{

    void uploadsuccess();

    void openPrintDialog();

    void bianhao(ShenbaoJiluBianhaoBean.DataBean dataListBean);

    void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean);
    void getPrindId(String id);
}
