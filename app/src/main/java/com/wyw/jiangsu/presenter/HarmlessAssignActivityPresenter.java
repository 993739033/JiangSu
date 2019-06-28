package com.wyw.jiangsu.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.SupervisionVeterinarianListBean;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
import com.wyw.jiangsu.bean.upload.UploadAssignBean;
import com.wyw.jiangsu.interfac.IHarmlessAssignActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;

/**
 * Created by wyw on 2016/12/23.
 */
public class HarmlessAssignActivityPresenter extends BasePresenter<IHarmlessAssignActivity> {
    private String result;

    public HarmlessAssignActivityPresenter(IHarmlessAssignActivity view) {
        super(view);
    }

    public void getPageDetil(String fstId) {
        addSubscribe(NetClient.getHarmlessListDetil(getUserId(), "CK2", fstId)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilBean -> new StatusException(userDetilBean).getObjectData(HarmlessListDetilBean.DataBean.class))
                .subscribe(dataBean ->
                                getView().getPageDetil(dataBean),
                        new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void getSupervisionVeterinariansList() {
        addSubscribe(NetClient.getSupervisionVeterianList(getUserId())
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilBean -> new StatusException(userDetilBean).
                        getObjectDataList(SupervisionVeterinarianListBean.DataListBean.class))
                .subscribe(dataBean -> getView().getSupervisonVeterinatianList(dataBean),
                        new ExceptionImp(getView()), new CompleteImp(getView())));
    }
    /**
     * 移送收集点
     */
    public void getCollectionLocationList(String fstid) {
        addSubscribe(NetClient.getCollectionLocationList(getUserId(),fstid)
                .map(collectionListBean -> new StatusException(collectionListBean).getObjectDataList(CollectionListBean.DataListBean.class))
                .subscribe(dataListBeen -> {
                    getView().getCollectionLocationList(dataListBeen);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }

//    public void uploadNew(UploadAssignBean bean) {
//        MultipartBody.Builder multyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("type", "APP_JGSYZP")
//                .addFormDataPart("uid", getUserId());
//        StringBuilder fileBuilder = new StringBuilder();
//        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
//            fileBuilder.append(file.getName() + ",");
//            multyBuilder.addFormDataPart(file.getName(), file.getName(), RequestBody.create(okhttp3.MediaType.parse("image/*"), file));
//        }
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
    public void upload(UploadAssignBean bean,String uuid) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(bean), "APP_JGSYZP", getUserId(),uuid)
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
    public void withoutDealMsg(UploadNoDealWithBean bean) {
        addSubscribe(NetClient.withoutDealMsg(Integer.parseInt(getUserId()),new Gson().toJson(bean))
                .map(homeBean -> {
                    if (homeBean.getErrorCode() != 0) {
                        throw new NullPointerException();
                    }
                    return homeBean.getData();
                })
                .subscribe(dataLists -> {
//                    getSpUtils().saveObjectData(Constance.DATA_PERMISSION,dataLists);
                },throwable -> {
                    Toast.makeText(MyApplication.getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                }));
    }
}
