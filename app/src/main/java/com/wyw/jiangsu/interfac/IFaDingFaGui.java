package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.FadingFaguiBean;

import java.util.List;

/**
 * Created by wyw on 2018/1/17.
 */

public interface IFaDingFaGui extends IMVP {

    void loadLaw(List<FadingFaguiBean.DataListBean> dataLists);

}
