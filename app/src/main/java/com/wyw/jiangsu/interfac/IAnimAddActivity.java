package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;

import java.util.List;

/**
 * Created by wyw on 2017/2/10.
 */
public interface IAnimAddActivity extends IMVP{
    void uploadsuccess();

    void showToast(String msg);

    void bianhao(ShenbaoJiluBianhaoBean.DataBean dataListBean);

    void setAddress(RegisteAddressBean bean);
}
