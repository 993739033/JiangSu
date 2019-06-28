package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.SlaughterAnimTypeBean;
import com.wyw.jiangsu.bean.SlaughterChooseBean;
import com.wyw.jiangsu.bean.SlaughterSpHouseBean;

import java.util.List;

/**
 * Created by zhyzh on 2017/4/14.
 */

public interface ISlaughterChooseActivity extends IMVPList {
    void setSpSlaughterData(List<SlaughterSpHouseBean.DataListEntity> list);
    void setSpAnimTypeData(List<SlaughterAnimTypeBean.DataListEntity> list);
    void setDataList(List<SlaughterChooseBean.DataListEntity> list);
    void setupSearvhView();
    void loadMore(List<SlaughterChooseBean.DataListEntity> list);
}
