package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AdressBean;
import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.interfac.IDwyzFragment;
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
 * Created by HUANG on 2017/9/7.
 */
public class DwyzFragmentPresenter extends BasePresenter<IDwyzFragment>{
    public DwyzFragmentPresenter(IDwyzFragment view) {
        super(view);
    }
    public void getRegisteAddress(){
        addSubscribe(NetClient.getAdress()
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(result -> {
                    List<AdressBean.DataListBean> dataList = new StatusException(result).getObjectDataList(AdressBean.DataListBean.class);
                    //将数据分割 获取市
                    List<String> province=new ArrayList<String>();
                    List<AdressBean.DataListBean> cities = new ArrayList<>();
                    Map<String, List<AdressBean.DataListBean>> countries = new HashMap<>();
                    Map<String, List<AdressBean.DataListBean>> streets = new HashMap<>();
                    //市
                    for (AdressBean.DataListBean bean : dataList) {
                        if ((dataList.get(0).getFStId()).equals(bean.getFuParent())) {
                            cities.add(bean);
                        }
                        if (bean.getFuParent().equals("0")){
                            province.add(bean.getFuName());
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

                    RegisteAddressBean bean = new RegisteAddressBean(cities, countries, streets);
                    bean.setDataList(dataList);
                    if (province!=null)
                        bean.setProvince(province);
                    return bean;

                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .subscribe(bean -> {
                            getView().setAddress(bean);

                        }
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }
    public void UploadFF(String json,int uid,String type,String myguid) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.UploadFF(json, uid, type, myguid)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .subscribe(baseMsgBean -> {getView().upload(baseMsgBean);
                }, new ExceptionImp(getView(),getLock()), new CompleteImp(getView()))
        );

    }
}
