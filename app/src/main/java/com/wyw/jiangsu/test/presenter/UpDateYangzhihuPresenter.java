package com.wyw.jiangsu.test.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.upload.UploadFarmUploadBean;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.test.interfac.IUpdateYangzhiView;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/7/12 0012.
 */
public class UpDateYangzhihuPresenter extends BasePresenter<IUpdateYangzhiView>{
    public UpDateYangzhihuPresenter(IUpdateYangzhiView view) {
        super(view);
    }
    public void getAnimType() {
        addSubscribe(NetClient.getAnimType()
                .map(userDetil -> new StatusException(userDetil).
                        getObjectData(AnimTypeListBean.DataBean.class))
                .subscribe(data -> {
                    getView().getAnimType(data);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }
    /**
     * 上传
     */
    public void upload(UploadFarmUploadBean bean, String uuid) {
        if (getLock().isLock())return;
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
                            Toast.makeText(MyApplication.getContext(), "修改完成", Toast.LENGTH_SHORT).show();
                            getView().uploadsuccess();
                        }));
    }

}
