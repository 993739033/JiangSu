package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;

/**
 * Created by HUANG on 2017/8/1.
 */
public interface ICommonSupervisionDetil extends IMVP{

    void setCompanyDetil(CommonSupervisionBean.Data data);

    void uploadSuccess(BaseMsgBean.Data data);

    void downImgSuccess(int num);

    void downImgFail();
}

