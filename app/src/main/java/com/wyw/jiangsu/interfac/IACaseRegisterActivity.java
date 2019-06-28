package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.bean.CaseRegisterBean;

import java.util.List;
import java.util.Objects;

/**
 * Created by mnkj004 on 2017/8/11.
 */

public interface IACaseRegisterActivity extends IMVPList {

    void addListData(List<?> dataListEntity);

    void setData(List<?> dataList);
}
