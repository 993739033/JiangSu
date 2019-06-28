package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.ProductA_BBeanListGai;
import com.wyw.jiangsu.interfac.IProductBListActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class ProductBListActivityPresenter extends BasePresenter<IProductBListActivity>{
    public ProductBListActivityPresenter(IProductBListActivity view) {
        super(view);
    }


    private String fstId = "-1";
    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getProductA_BBeanListGaiList(getUserId(),"V_AH_AEmbryoQuarantineB",fstId)
                //map里面的是实体类对象
                .map(productA_bBeanListGai -> {
                    List<ProductA_BBeanListGai.DataListBean> dataList = new StatusException(productA_bBeanListGai).getObjectRefreshDataList(ProductA_BBeanListGai.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(DataListBean -> getView().refresh(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }
    public void loadmore() {
        addSubscribe(NetClient.getProductA_BBeanListGaiList(getUserId(),"V_AH_AEmbryoQuarantineB",fstId)
                .map(productA_bBeanListGai -> {
                    List<ProductA_BBeanListGai.DataListBean> dataList = new StatusException(productA_bBeanListGai).getObjectDataList(ProductA_BBeanListGai.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().loadMore(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
