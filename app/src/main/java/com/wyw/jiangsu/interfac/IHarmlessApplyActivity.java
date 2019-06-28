package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.UserDetilBean;

/**
 * Created by wyw on 2016/12/22.
 */
public interface IHarmlessApplyActivity extends IMVP{
    void setUserMsg(UserDetilBean.Data data);

    void getAnimType(AnimTypeListBean.DataBean list);

    void uploadsuccess();
}
