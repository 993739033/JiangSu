package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.bean.RegisteMsgBean;

/**
 * Created by HUANG on 2017/9/6.
 */
public interface ISyjyRegiste extends IMVP{
    void setAddress(RegisteAddressBean bean);
    void upload(RegisteMsgBean baseMsgBean);
}
