package com.wyw.jiangsu.presenter;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.bean.VeterinarianDeclareBean;
import com.wyw.jiangsu.bean.upload.UploadReplaceApplyBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.IVeterinarianDeclareDetilActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;

/**
 * Created by mnkj004 on 2017/7/27.
 */

public class VeterinarianDeclareDetilActivityPresenter extends BasePresenter<IVeterinarianDeclareDetilActivity> {


    public VeterinarianDeclareDetilActivityPresenter(IVeterinarianDeclareDetilActivity view) {
        super(view);
    }

    public void getFarmDeclare(String tableName, int fstId) {

        addSubscribe(NetClient.getdaishenbaoDetil(tableName, fstId)
                .map(agencyDeclareDetilActivityBean -> {
                    if (agencyDeclareDetilActivityBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(agencyDeclareDetilActivityBean.getErrorMsg());
                    }
                    AgencyDeclareDetilActivityBean.DataBean dataBean = new StatusException(agencyDeclareDetilActivityBean).getObjectData(AgencyDeclareDetilActivityBean.DataBean.class);
                    return dataBean;
                }).subscribe(DataEntity ->
                        getView().setData(DataEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void upload(UploadReplaceApplyBean bean, String uuid) {
        if (getLock().isLock()) return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(bean), "HT_JGSYDSB", getUserId(), uuid)
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
