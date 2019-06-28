package com.wyw.jiangsu.test.interfac;

import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.interfac.IActivity;
import com.wyw.jiangsu.interfac.IMVP;

/**
 * Created by Administrator on 2017/7/12 0012.
 */
public interface IUpdateYangzhiView extends  IMVP {
    void uploadsuccess();
    void getAnimType(AnimTypeListBean.DataBean data);
}
