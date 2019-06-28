package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.AnjiandengjiActivityBean;
import com.wyw.jiangsu.bean.ChaxunJinduActuvityBean;

import java.util.List;

/**
 * Created by Windows on 2017/6/19.
 */

public interface IAnjiandengjiActivity extends IMVPList{

    void setData(List<AnjiandengjiActivityBean.DataListBean> dataListEntity);
}
