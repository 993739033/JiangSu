package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.WHHChukuBean;
import com.wyw.jiangsu.bean.WHHChukuFirstBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface IWHHChuKuActivity extends IMVP{

    void chukuXinxi(WHHChukuBean.DataBean dataListBean);
    void chukuXinxi1(List<WHHChukuFirstBean.DataListBean> dataListBean);

    void upLoadSucceed();
}
