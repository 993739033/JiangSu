package com.wyw.jiangsu.presenter;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.AdressBean;
import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.bean.RegisteUploadBean;
import com.wyw.jiangsu.interfac.IPhoneRegisteActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/5/16.
 */
public class PhoneRegisteActivityPresenter extends BasePresenter<IPhoneRegisteActivity> {

    public PhoneRegisteActivityPresenter(IPhoneRegisteActivity view) {
        super(view);
    }

    public void getAddress() {
        addSubscribe(NetClient.getAdress()
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(result -> {
                    List<AdressBean.DataListBean> dataList = new StatusException(result).getObjectDataList(AdressBean.DataListBean.class);
                    //将数据分割 获取市
                    List<AdressBean.DataListBean> cities = new ArrayList<>();
                    Map<String, List<AdressBean.DataListBean>> countries = new HashMap<>();
                    Map<String, List<AdressBean.DataListBean>> streets = new HashMap<>();
                    //市
                    for (AdressBean.DataListBean bean : dataList) {
                        if ((dataList.get(0).getFStId()).equals(bean.getFuParent())) {
                            cities.add(bean);
                        }
                    }
                    //县
                    for (AdressBean.DataListBean city : cities) {
                        List<AdressBean.DataListBean> xian = new ArrayList<>();
                        for (AdressBean.DataListBean bean : dataList) {
                            if (bean.getFuParent().equals(city.getFStId())) {
                                xian.add(bean);
                            }
                        }
                        countries.put(city.getFuName(), xian);
                    }

                    //街道
                    for (AdressBean.DataListBean city2 : cities) {
                        List<AdressBean.DataListBean> xian = new ArrayList<>();
                        for (AdressBean.DataListBean bean : dataList) {
                            if (bean.getFuParent().equals(city2.getFStId())) {
                                xian.add(bean);
                            }
                        }

                        for (AdressBean.DataListBean county : xian) {
                            List<AdressBean.DataListBean> street = new ArrayList<>();
                            for (AdressBean.DataListBean bean : dataList) {
                                if (bean.getFuParent().equals(county.getFStId())) {
                                    street.add(bean);
                                }
                            }
                            streets.put(county.getFuName(), street);
                        }
                    }
                    return new RegisteAddressBean(cities, countries, streets);

                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .subscribe(bean -> {
                            getView().setAddress(bean);
                        }
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void upLoad(RegisteUploadBean data) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaodZhuce(new Gson().toJson(data), "0", "zhuce")
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getData().getResult2();
                }).subscribe(result2 -> getView().uploadsuccess(result2)
                        , new ExceptionImp(getView(),getLock()), new CompleteImp(getView())));
    }
}
