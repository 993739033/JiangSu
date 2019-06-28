package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.RegisteAddressBean;

/**
 * Created by Administrator on 2017/5/16.
 */
public interface IPhoneRegisteActivity extends IMVP {

    void setAddress(RegisteAddressBean bean);

//    void uploadsuccess();

    void uploadsuccess(String result2);
}
