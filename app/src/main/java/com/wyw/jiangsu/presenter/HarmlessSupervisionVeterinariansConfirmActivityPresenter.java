package com.wyw.jiangsu.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.upload.UploadReplaceApplyBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessSupervisionVeterinariansConfirmActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by wyw on 2016/12/23.
 */
public class HarmlessSupervisionVeterinariansConfirmActivityPresenter extends BasePresenter<IHarmlessSupervisionVeterinariansConfirmActivity> {
    private String result;

    public HarmlessSupervisionVeterinariansConfirmActivityPresenter(IHarmlessSupervisionVeterinariansConfirmActivity view) {
        super(view);
    }

    public void getPageDetil(String fstId,boolean ifNewAnim) {
        addSubscribe(NetClient.getHarmlessListDetil(getUserId(), "CK2", fstId)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilBean -> new StatusException(userDetilBean).getObjectData(HarmlessListDetilBean.DataBean.class))
                .subscribe(dataBean ->
                                getView().getPageDetil(dataBean,ifNewAnim),
                        new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    /**
     * 移送收集点
     */
    public void getCollectionLocationList(String fstid) {
        addSubscribe(NetClient.getCollectionLocationList(getUserId(), fstid)
                .map(collectionListBean -> new StatusException(collectionListBean).getObjectDataList(CollectionListBean.DataListBean.class))
                .subscribe(dataListBeen -> {
                    getView().getCollectionLocationList(dataListBeen);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    /**
     * 移送收集点
     */
    public void loadPicture(String picName, String uuid) {
//        addSubscribe(NetClient.loadPic(picName)
//                .map(collectionListBean -> new StatusException(collectionListBean).getObjectDataList(CollectionListBean.DataListBean.class))
//                .subscribe(dataListBeen -> {
//                    getView().getCollectionLocationList(dataListBeen);
//                }, new ExceptionImp(getView()), new CompleteImp(getView())));
        addSubscribe(NetClient.loadPic(Constance.IMAGE_PATH + picName)
                .map(responseBody -> {
                    Bitmap bitmap = null;
                    if (saveLocal(responseBody, uuid + "_" + picName.split("_")[1])) {
                        bitmap = BitmapFactory.decodeFile(PhotoUtils.getWuHaiHuaDir() + File.separator + uuid + "_" + picName.split("_")[1]);
                    }

                    return bitmap;
                }).subscribe(
                        bitmap -> {
                            if (bitmap != null) {
                                getView().loadPicture(bitmap);
                            }
                        }, throwable -> {
                        }));
    }

    private boolean saveLocal(ResponseBody responseBody, String picName) {
        File file = new File(PhotoUtils.getWuHaiHuaDir(), picName);
        byte[] buf = new byte[1024];
        int length;
        InputStream inputStream = responseBody.byteStream();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            while ((length = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 上传
     */
    public void upload(UploadReplaceApplyBean bean, String uuid) {
//        new Gson().toJson(bean), getUserId(), "HT_JGSYDSB",null
        if (getLock().isLock()){
            return ;
        }
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
                        }, new ExceptionImp(getView(),getLock()),
                        () -> {
                            Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                            getView().uploadsuccess();
                        }));
    }
}
