package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimAQueryListBean;
import com.wyw.jiangsu.interfac.IAnimAQueryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class AnimAQueryActivityPresenter extends BasePresenter<IAnimAQueryActivity>{
    public AnimAQueryActivityPresenter(IAnimAQueryActivity view) {
        super(view);
    }

    public void getAnimAListData(String value,String sdate,String jdate){
        addSubscribe(NetClient.getAnimAListData(getUser().getUSERID()+"","Qua_AnimalQuarantineA","-1","DateQF,IsPrant,APhoneCyr,ACargoOwner,AXuZhong,AQuantity,ADanWei",value,sdate,jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimAQueryListBean.DataListEntity> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(AnimAQueryListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity-> getView().setData(DataListEntity),new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadData(String value,String sdate,String jdate,String fstid){

        addSubscribe(NetClient.getAnimAListData(getUser().getUSERID()+"","Qua_AnimalQuarantineA",fstid,"DateQF,IsPrant,APhoneCyr,ACargoOwner,AXuZhong,AQuantity,ADanWei",value,sdate,jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimAQueryListBean.DataListEntity> dataList = new StatusException(animAQueryListBean).getObjectDataList(AnimAQueryListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity-> getView().addListData(DataListEntity),new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
