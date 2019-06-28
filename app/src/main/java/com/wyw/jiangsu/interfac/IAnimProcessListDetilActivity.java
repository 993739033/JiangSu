package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnimProcessListBean;

import java.util.List;

/**
 * Created by wyw on 2017/2/9.
 */
public interface IAnimProcessListDetilActivity extends IMVP {
    void uploadsuccess();

    void getToast(String string);

    void getPrintId(String s);

}
