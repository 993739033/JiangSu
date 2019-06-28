package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.IVeterinarianDeclareActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by mnkj004 on 2017/7/27.
 */

public class VeterinarianDeclareActivityPresenter extends BasePresenter<IVeterinarianDeclareActivity> {

    public VeterinarianDeclareActivityPresenter(IVeterinarianDeclareActivity view) {
        super(view);
    }

    public void getVeterinarianDeclare() {
        addSubscribe(NetClient.getWuHaiHuaCXbean(getUserId(), "V_HT_JGSYDSBS", "-1", "Fsbrq,Fxzxm,Fxxdz,Fclfs,Fxcll,Fyzclx,QDWErBiaoHao,Fykth,Fsfzh", "", "", "")
                        .map(animAQueryListBean -> {
                            if (animAQueryListBean.getErrorCode() != 0) {
                                throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                            }
                            List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(WuHaiHuaCXbean.DataListBean.class);
                            return dataList;
                        }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadData(String fstid) {

        addSubscribe(NetClient.getWuHaiHuaCXbean(getUser().getUSERID() + "", "V_HT_JGSYDSBS", fstid, "Fsbrq,Fxzxm,Fxxdz,Fclfs,Fxcll,Fyzclx,QDWErBiaoHao,Fykth,Fsfzh", "", "", "")
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(WuHaiHuaCXbean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


}
