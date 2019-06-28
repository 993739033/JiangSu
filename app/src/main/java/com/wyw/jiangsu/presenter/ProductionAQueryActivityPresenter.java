package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.ProductionAListBean;
import com.wyw.jiangsu.interfac.IProductionAQueryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class ProductionAQueryActivityPresenter extends BasePresenter<IProductionAQueryActivity> {
    public ProductionAQueryActivityPresenter(IProductionAQueryActivity view) {
        super(view);
    }

    public void getAnimAListData(String value, String sdate, String jdate) {   //pname
        addSubscribe(NetClient.getProductAListData(getUser().getUSERID()+"","Qua_AnimalProductsA","-1","DateQF,IsPrant,PNumber,PCargoOwner,PName,PQuantity,PDanWei",value,sdate,jdate)
                .map(productionAListBean -> {
                    if (productionAListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productionAListBean.getErrorMsg());
                    }
                    List<ProductionAListBean.DataListEntity> dataList = new StatusException(productionAListBean).getObjectRefreshDataList(ProductionAListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity-> getView().setData(DataListEntity),new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {
        addSubscribe(NetClient.getProductAListData(getUser().getUSERID()+"","Qua_AnimalProductsA",fstid,"DateQF,IsPrant,PNumber,PCargoOwner,PName,PQuantity,PDanWei",value,sdate,jdate)
                .map(productionAListBean -> {
                    if (productionAListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productionAListBean.getErrorMsg());
                    }
                    List<ProductionAListBean.DataListEntity> dataList = new StatusException(productionAListBean).getObjectDataList(ProductionAListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity-> getView().addListData(DataListEntity),new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
