package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface IQueryFragment extends IMVP{
    void showToast(String msg);

    void refreshComplete(List<HomeBean.DataList> dataLists);
}
