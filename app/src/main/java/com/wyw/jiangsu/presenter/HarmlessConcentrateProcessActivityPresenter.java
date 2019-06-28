package com.wyw.jiangsu.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.upload.UploadHarmlessConcentrateProcess;
import com.wyw.jiangsu.interfac.IHarmlessConcentrateProcessActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;

/**
 * Created by wyw on 2016/12/27.
 */
public class HarmlessConcentrateProcessActivityPresenter extends BasePresenter<IHarmlessConcentrateProcessActivity> {
    public HarmlessConcentrateProcessActivityPresenter(IHarmlessConcentrateProcessActivity view) {
        super(view);
    }

    public void getPageDetil(String fstId) {
        addSubscribe(NetClient.getHarmlessListDetil(getUserId(), "CK6", fstId)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilBean -> new StatusException(userDetilBean).getObjectData(HarmlessListDetilBean.DataBean.class))
                .subscribe(dataBean -> getView().getPageDetil(dataBean),
                        new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    /**
     * 上传
     */
    public void upload(UploadHarmlessConcentrateProcess bean, String uuid) {
        if (getLock().isLock()) return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(bean), "APP_WHHCLZX", getUserId(), uuid)
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
                        }, new ExceptionImp(getView(), getLock()),
                        () -> {
                            Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                            getView().uploadSuccessful();
                        }));
    }
}

