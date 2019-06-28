package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.QuarantineProcessNotifListBeanDetil;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;

/**
 * Created by wyw on 2017/2/10.
 */
public interface IQuarantineProcessNotifActivity extends IMVP{
    void uploadsuccess();

    void getNetData(QuarantineProcessNotifListBeanDetil.DataBean dataBean);

    void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean);
}
