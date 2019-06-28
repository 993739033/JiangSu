package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.ChuKuDeatilBean;
import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;
import com.wyw.jiangsu.interfac.IStorehouseOutDetailActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by mnkj004 on 2017/8/28.
 */

public class StorehouseOutDetailPresenter extends BasePresenter<IStorehouseOutDetailActivity> {
    public StorehouseOutDetailPresenter(IStorehouseOutDetailActivity view) {
        super(view);
    }

    public void getFarmDeclare(String tableName,int fstId){
        addSubscribe(NetClient.getChuKuDetil(tableName,fstId)
                .map(yangzhihuDetailActivityBean -> {
                    if (yangzhihuDetailActivityBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(yangzhihuDetailActivityBean.getErrorMsg());
                    }
                    ChuKuDeatilBean.DataBean dataBean = new StatusException(yangzhihuDetailActivityBean).getObjectData(ChuKuDeatilBean.DataBean.class);
                    return dataBean;
                }).subscribe(DataListEntity ->
                        getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
//    public void deteleData(String fstid,String guid){
//        addSubscribe(NetClient.deleteYangzhiChangSB("HT_BSDWSWSB",fstid,guid)
//                .map(baseMsgBean -> {
//                    if(baseMsgBean.getErrorCode()!=0){
//                        throw  new IllegalArgumentException(baseMsgBean.getErrorMsg());
//                    }
//                    return baseMsgBean;
//                }).subscribe(baseMsgBean1 ->getView().deletesucess(baseMsgBean1), new ExceptionImp(getView()), new CompleteImp(getView()))
//        );


//    }
}
