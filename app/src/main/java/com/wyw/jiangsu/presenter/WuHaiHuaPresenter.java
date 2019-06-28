package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;
import com.wyw.jiangsu.bean.JiZhongChuLiDetilBean;
import com.wyw.jiangsu.bean.WuHaiHuaBean;
import com.wyw.jiangsu.interfac.IWuHaiHua;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by song on 2017/6/9.
 */

public class WuHaiHuaPresenter extends BasePresenter<IWuHaiHua> {
    public WuHaiHuaPresenter(IWuHaiHua view) {
        super(view);
    }

    public void getDateList(int fstId) {
        addSubscribe(NetClient.getWuHaiHua1("APP_WHHCLZX", fstId)
                .map(listBean -> {
                    if (listBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(listBean.getErrorMsg());
                    }
                    JiZhongChuLiDetilBean.DataBean dataList = new StatusException(listBean).getObjectData(JiZhongChuLiDetilBean.DataBean.class);
                    return dataList;
                })
                .subscribe(dataListEntity ->
                        getView().setData(dataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));

    }
}
