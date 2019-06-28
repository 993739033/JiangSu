package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.CommonSupervisionQyBean;

import java.util.List;

/**
 * Created by wyw on 2018/1/25.
 */

public interface ICommonSupervisionQy extends IMVPList {

    void searchComplete(List<CommonSupervisionQyBean.DataListBean> userDetilBean);

    void loadMore(List<CommonSupervisionQyBean.DataListBean> userDetilBean);
}
