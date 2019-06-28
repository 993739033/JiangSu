package com.wyw.jiangsu.presenter;



import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.interfac.IAnimApplylistActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;


/**
 * Created by wyw on 2016/12/20.
 */
public class AnimApplylistActivityPresenter extends BasePresenter<IAnimApplylistActivity>{
    public AnimApplylistActivityPresenter(IAnimApplylistActivity view) {
        super(view);
    }

    private String fstId = "-1";
    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getGuarantineDeclareListDetil(getUserId(), "V_Qua_QuarantineDeclarationCD", fstId)
                .map(guarantineDeclareListDetilBean -> {
                    List<GuarantineDeclareListDetilBean.DataListBean> dataList = new StatusException(guarantineDeclareListDetilBean).getObjectRefreshDataList(GuarantineDeclareListDetilBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(DataListBean -> getView().refresh(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }

    public void loadmore() {
        addSubscribe(NetClient.getGuarantineDeclareListDetil(getUserId(), "V_Qua_QuarantineDeclarationCD", fstId)
                .map(guarantineDeclareListDetilBean -> {
                    List<GuarantineDeclareListDetilBean.DataListBean> dataList = new StatusException(guarantineDeclareListDetilBean).getObjectDataList(GuarantineDeclareListDetilBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().loadMore(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
