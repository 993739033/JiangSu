package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.interfac.IAgencyDeclareDetilActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by Windows on 2017/6/9.
 */

public class AgencyDeclareDetilActivityPresenter extends BasePresenter<IAgencyDeclareDetilActivity>{
    public AgencyDeclareDetilActivityPresenter(IAgencyDeclareDetilActivity view) {
        super(view);
    }
    public void getFarmDeclare(String tableName,int fstId) {

        addSubscribe(NetClient.getdaishenbaoDetil(tableName, fstId)
                .map(agencyDeclareDetilActivityBean -> {
                    if (agencyDeclareDetilActivityBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(agencyDeclareDetilActivityBean.getErrorMsg());
                    }
                    AgencyDeclareDetilActivityBean.DataBean dataBean = new StatusException(agencyDeclareDetilActivityBean).getObjectData(AgencyDeclareDetilActivityBean.DataBean.class);
                    return dataBean;
                }).subscribe(DataEntity ->
                        getView().setData(DataEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
    public void deteleData(String fstid,String guid){
        addSubscribe(NetClient.deleteYangzhiChangSB("HT_JGSYDSB",fstid,guid)
                .map(baseMsgBean -> {
                    if(baseMsgBean.getErrorCode()!=0){
                        throw  new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean;
                }).subscribe(baseMsgBean1 ->getView().deletesucess(baseMsgBean1), new ExceptionImp(getView()), new CompleteImp(getView()))
        );


    }
}
