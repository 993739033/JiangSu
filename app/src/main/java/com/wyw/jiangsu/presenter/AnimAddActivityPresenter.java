package com.wyw.jiangsu.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AdressBean;
import com.wyw.jiangsu.bean.Qua_QuarantineDeclarationCD;
import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.interfac.IAnimAddActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by wyw on 2017/2/10.
 */
public class AnimAddActivityPresenter extends BasePresenter<IAnimAddActivity> {
    private String result;
    private int picCount, resultCount;
    private boolean haveDefeat = false;

    public AnimAddActivityPresenter(IAnimAddActivity view) {
        super(view);
    }

    public void uploadNew(Qua_QuarantineDeclarationCD bean) {
        MultipartBody.Builder multyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("type", "Qua_QuarantineDeclarationCD")
                .addFormDataPart("uid", getUserId());
        StringBuilder fileBuilder = new StringBuilder();
//        File lastFiel = null;
        for (File file : PhotoUtils.getQuarantineDir().listFiles()) {
            fileBuilder.append(file.getName() + ",");
            multyBuilder.addFormDataPart(file.getName(), file.getName(), RequestBody.create(okhttp3.MediaType.parse("image/*"), file));
//            lastFiel = file;
        }
//        multyBuilder.addFormDataPart("A3.jpg", "A3.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), lastFiel));
        if (fileBuilder.length() > 0) fileBuilder.deleteCharAt(fileBuilder.length() - 1);
        bean.setLen(fileBuilder.toString());
        multyBuilder.addFormDataPart("json", new Gson().toJson(bean));

//                .addFormDataPart("type", "A1.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file));
        if (getLock().isLock()) {
            return;
        }
        getLock().lock();
        addSubscribe(NetClient.uploadFile(multyBuilder.build())
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getErrorCode();
                }).subscribe(errorCode -> {
                            Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
                        }, new ExceptionImp(getView(), getLock()),
                        () -> {
                            Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                            Log.e("HarmlessApplyActivityPr", "complete");
                            getView().uploadsuccess();
                        }));
    }

    public void getBianhao() {

        addSubscribe(NetClient.getShenbaoJiluBianhaoBean(getUserId(), "sb")
                .map(guarantineDeclareListDetilBean -> {
                    ShenbaoJiluBianhaoBean.DataBean dataList = new StatusException(guarantineDeclareListDetilBean)
                            .getObjectData(ShenbaoJiluBianhaoBean.DataBean.class);
                    return dataList;
                })
                .subscribe(DataListBean -> getView().bianhao(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    private ResultHandler handler = new ResultHandler();

    public void getAdress() {
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
                    return new RegisteAddressBean(dataList, cities, countries, streets);

                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .subscribe(bean -> {
                            getView().setAddress(bean);
                        }
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public class ResultHandler extends Handler {
        public static final int SEND = 0;
        public static final int DELAY = 1;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND:
                    //网络请求
                    break;
                case DELAY:
                    sendMessageDelayed(msg, 800);
                    break;
            }
        }
    }
}
