package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.ZaiqianCheckQueryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public interface IZaiqianCheckQueryActivity extends IMVPList {
//    List<ZaiqianCheckQueryBean.DataListBean> getData(List<ZaiqianCheckQueryBean.DataListBean> dataListEntity);

    void addListData(List<ZaiqianCheckQueryBean.DataListBean> dataListEntity);

    void setData(List<ZaiqianCheckQueryBean.DataListBean> dataListEntity);
}
