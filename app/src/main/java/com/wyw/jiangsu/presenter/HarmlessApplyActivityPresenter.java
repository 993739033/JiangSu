package com.wyw.jiangsu.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.bean.upload.UploadFarmUploadBean;
import com.wyw.jiangsu.interfac.IHarmlessApplyActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;

/**
 * Created by wyw on 2016/12/22.
 */
public class HarmlessApplyActivityPresenter extends BasePresenter<IHarmlessApplyActivity> {

    public HarmlessApplyActivityPresenter(IHarmlessApplyActivity view) {
        super(view);

    }

    public void getUserDetil() {
        addSubscribe(NetClient.getUserDetil(getUserId(), "CK1")
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetil -> new StatusException(userDetil).getObjectData(UserDetilBean.Data.class))
                .subscribe(data -> {
                    getView().setUserMsg(data);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    /**
     * 获取动物种类
     */
    public void getAnimType() {
        addSubscribe(NetClient.getAnimType()
                .map(userDetil -> new StatusException(userDetil).
                        getObjectData(AnimTypeListBean.DataBean.class))
                .subscribe(data -> {
                    getView().getAnimType(data);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }


//    public void uploadNew(UploadFarmUploadBean bean) {
//
//        MultipartBody.Builder multyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("type", "HT_BSDWSWSB")
//                .addFormDataPart("uid", getUserId());
//        StringBuilder fileBuilder = new StringBuilder();
////        File lastFiel = null;
//        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
//            fileBuilder.append(file.getName() + ",");
//            multyBuilder.addFormDataPart(file.getName(), file.getName(), RequestBody.create(okhttp3.MediaType.parse("image/*"), file));
////            lastFiel = file;
//        }
////        multyBuilder.addFormDataPart("A3.jpg", "A3.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), lastFiel));
//        fileBuilder.deleteCharAt(fileBuilder.length() - 1);
//        bean.setLen(fileBuilder.toString());
//        multyBuilder.addFormDataPart("json", new Gson().toJson(bean));
//
////                .addFormDataPart("type", "A1.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file));
//        addSubscribe(NetClient.uploadNew(multyBuilder.build())
//                .doOnSubscribe(new DoOnSubscriber(getView()))
//                .map(baseMsgBean -> {
//                    if (baseMsgBean.getErrorCode() != 0) {
//                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
//                    }
//                    return baseMsgBean.getErrorCode();
//                }).subscribe(errorCode -> {
//                            Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
//                        }, new ExceptionUploadImp(getView()),
//                        () -> {
//                            Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
//                            Log.e("HarmlessApplyActivityPr", "complete");
//                            getView().uploadsuccess();
//                        }));
//    }

    /**
     * 上传
     */
    public void upload(UploadFarmUploadBean bean,String uuid) {
        Log.e("czy","===onStart===" +getLock().isLock());
        if (getLock().isLock()){
            Log.e("czy","===onReturn==="+getLock().isLock());
            return;
        }
        Log.e("czy","===setNull==="+getLock().isLock());
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(bean), "HT_BSDWSWSB",getUserId() ,uuid)
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    } else {
                        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
                            if (file.getName().contains(uuid)) {
                                //上传
                                Intent intent = new Intent(MyApplication.getContext(), UploadPictureService.class);
                                intent.setAction(UploadPictureService.ADD_TASK);
                                intent.putExtra("localName", file.getName());
                                intent.putExtra("address", PhotoUtils.getWuHaiHuaDir().getAbsolutePath());
                                intent.putExtra("uuid", uuid);
                                MyApplication.getContext().startService(intent);
                            }
                        }
                    }
                    return baseMsgBean.getErrorCode();
                })
                .subscribe(errorCode -> {
                            Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
                        }, new ExceptionImp(getView(),getLock()),
                        () -> {

                            Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                            getView().uploadsuccess();
                        }));
    }

}
