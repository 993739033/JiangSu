package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimAProcessUserDetilBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;

/**
 * Created by wyw on 2017/2/10.
 */
public interface IAnimBActivity extends IMVP{
    void uploadSucceed();

    void getUserDetil1(AnimAProcessUserDetilBean.DataListBean dataListBean);

    void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean);

    void getPrintId(String id,String guid);
}
