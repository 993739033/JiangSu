package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;
import com.wyw.jiangsu.interfac.IYangzhihuDetailActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by Windows on 2017/6/8.
 */

public class YangzhihuDetailActivityPresenter extends BasePresenter <IYangzhihuDetailActivity>{
    public YangzhihuDetailActivityPresenter(IYangzhihuDetailActivity view) {
        super(view);
    }
    public void getFarmDeclare(String tableName,int fstId){

        addSubscribe(NetClient.getyangzhiDetil(tableName,fstId)
                .map(yangzhihuDetailActivityBean -> {
                    if (yangzhihuDetailActivityBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(yangzhihuDetailActivityBean.getErrorMsg());
                    }
                    YangzhihuDetailActivityBean.DataBean dataBean = new StatusException(yangzhihuDetailActivityBean).getObjectData(YangzhihuDetailActivityBean.DataBean.class);
                    return dataBean;
                }).subscribe(DataListEntity ->
                        getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
    public void deteleData(String fstid,String guid){
        if (getLock().isLock())return;
        getLock().lock();
            addSubscribe(NetClient.deleteYangzhiChangSB("HT_BSDWSWSB",fstid,guid)
            .map(baseMsgBean -> {
                if(baseMsgBean.getErrorCode()!=0){
                    throw  new IllegalArgumentException(baseMsgBean.getErrorMsg());
                }
                return baseMsgBean;
            }).subscribe(baseMsgBean1 ->getView().deletesucess(baseMsgBean1), new ExceptionImp(getView(),getLock()), new CompleteImp(getView()))
            );


    }

}
