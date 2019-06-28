package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.RuChangChaXunBean;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;

/**
 * Created by Administrator on 2017/4/11.
 */
public interface IRuChangQuerenActivity extends IMVP {
    void uploadsuccess();

    void chaxun(RuChangChaXunBean.DataBean bean);

    void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean);
}
