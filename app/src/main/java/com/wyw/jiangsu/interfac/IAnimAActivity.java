package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimAProcessUserDetilBean;
import com.wyw.jiangsu.bean.AnimaABProductABBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;

/**
 * Created by wyw on 2017/2/10.
 */
public interface IAnimAActivity extends IMVP{
    void uploadSuccess(String guid);

    void showToast(String message);

    void getUserDetil1(AnimAProcessUserDetilBean.DataListBean dataList);
//    void getUserDetil1(AnimaABProductABBean.DataListBean dataList);

    void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean);
    void getPrintId(String id,String guid);
}
