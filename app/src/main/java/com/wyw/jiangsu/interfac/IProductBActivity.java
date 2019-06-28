package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;

/**
 * Created by wyw on 2017/2/15.
 */
public interface IProductBActivity extends IMVP {

    void upLoadSucceed(String guid);
    void getPrint(String id);

    void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean);
}
