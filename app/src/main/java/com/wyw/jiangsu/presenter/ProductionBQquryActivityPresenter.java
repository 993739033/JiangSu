package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.ProductBListBean;
import com.wyw.jiangsu.interfac.IProductionBQquryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class ProductionBQquryActivityPresenter extends BasePresenter<IProductionBQquryActivity> {
    public ProductionBQquryActivityPresenter(IProductionBQquryActivity view) {
        super(view);
    }

    public void getAnimAListData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getProductBListData(getUser().getUSERID() + "", "Qua_AnimalProductsB", "-1", "DateQF,IsPrant,PBNumber,PBCargoOwner,PBName,PBQuantity,PBDanWei", value, sdate, jdate)
                .map(productBListBean -> {
                    if (productBListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productBListBean.getErrorMsg());
                    }
                    List<ProductBListBean.DataListEntity> dataList = new StatusException(productBListBean).getObjectRefreshDataList(ProductBListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {
        addSubscribe(NetClient.getProductBListData(getUser().getUSERID() + "", "Qua_AnimalProductsB", fstid, "DateQF,IsPrant,PBNumber,PBCargoOwner,PBName,PBQuantity,PBDanWei", value, sdate, jdate)
                .map(productBListBean -> {
                    if (productBListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productBListBean.getErrorMsg());
                    }
                    List<ProductBListBean.DataListEntity> dataList = new StatusException(productBListBean).getObjectDataList(ProductBListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
