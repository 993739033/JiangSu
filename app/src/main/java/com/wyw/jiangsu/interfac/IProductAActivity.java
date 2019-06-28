package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;

/**
 * Created by wyw on 2017/2/15.
 */
public interface IProductAActivity extends IMVP {

    void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean);

    void uploadComplete(String guid);

    void getPrintId(String id,String guid);
}
