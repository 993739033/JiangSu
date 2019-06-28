package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;

/**
 * Created by wyw on 2017/6/21.
 */

public interface ICommonSupervisionDetilActivity extends IMVP{
    void setCompanyDetil(CommonSupervisionBean.Data data);

    void uploadSuccess(BaseMsgBean.Data data);

    void downImgSuccess();

    void downImgFail();

}
