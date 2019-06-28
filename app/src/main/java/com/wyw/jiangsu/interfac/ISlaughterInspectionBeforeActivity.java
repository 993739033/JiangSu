package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.SlaghterNumberBean;
import com.wyw.jiangsu.bean.SlaughterBeforeBean;

import java.util.List;

/**
 * Created by zhyzh on 2017/4/12.
 */

public interface ISlaughterInspectionBeforeActivity extends IMVP {
    List<SlaughterBeforeBean.DataList> getDataList(String type);

    void setSlaughterNumber(List<SlaghterNumberBean.DataEntity> list);

    void uploadsuccess();

    void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean);
}
