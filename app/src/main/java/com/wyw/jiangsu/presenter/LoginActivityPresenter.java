package com.wyw.jiangsu.presenter;


import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.RegistryRecordBean;
import com.wyw.jiangsu.db.LocalGreenDao;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ILoginActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RetrofitUtils;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.utils.SPUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;

/**
 * Created by wyw on 2016/10/31.
 */

public class LoginActivityPresenter extends BasePresenter<ILoginActivity> {
//    private List<UpdateBean.DataList> downlist;

    public LoginActivityPresenter(ILoginActivity view) {
        super(view);
//        updateApk();
    }


    public void login(String userName, String psw) {
        addSubscribe(NetClient.login(userName, psw)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userBean -> new StatusException(userBean).getObjectData(User.class))
                .subscribe(
                        user -> {
                            LocalGreenDao.getInstance().saveUser(user);
                            SPUtils.getInstance().saveObjectData(Constance.USER_OBJECT, user);
                            SPUtils.getInstance().saveData(Constance.USER_NAME, userName);
                            SPUtils.getInstance().saveData(Constance.USER_PSW, psw);
                            getView().loginSuccessful(((int) user.getUSERID()),user.getFUNAME(),user.getFUPHONE());
                        }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void uploadLoginInfo(RegistryRecordBean bean) {
        addSubscribe(NetClient.uploadLoginInfo(getUserId(), new Gson().toJson(bean), "App_UserLogRecord")
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean;
                })
                .subscribe(baseMsgBean1 -> {
                    Log.e(LoginActivityPresenter.class.getSimpleName(), baseMsgBean1.getErrorMsg());
//                    getView().recordSuccessful();
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
//                        getView().recordSuccessful();
                    }
                }, new CompleteImp(getView()))
        );
    }

    public void upload() {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("name", RequestBody.create(okhttp3.MediaType.parse(RetrofitUtils.MULTIPART_FORM_JSON), "wyw"));
        map.put("pic", RequestBody.create(okhttp3.MediaType.parse(RetrofitUtils.MULTIPART_FORM_JSON), "A1.jpg"));
        File file = new File(Environment.getExternalStorageDirectory(), "aa.jpg");
        RequestBody partFromFile = RetrofitUtils.createPartFromFile(file);
        RequestBody body1 =
                RequestBody.create(okhttp3.MediaType.parse("image/*"), "A1.jpg");

        //Body进行请求
        RequestBody body2 = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name", "wyw")
                .addFormDataPart("pic", "A1.jpg")
                .addFormDataPart("picture1", "A1.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture2", "A2.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture3", "A3.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture4", "A4.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture5", "A5.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture6", "A6.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture7", "A7.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture8", "A8.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
//                .addFormDataPart("picture9", "A9.jpg", RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
                .build();
//        NetClient.uploadPic(body2)
        NetClient.uploadPic(map, RequestBody.create(okhttp3.MediaType.parse("image/*"), file))
                .subscribe(
                        body -> {
                            if (body == null) {
                                Log.e("LoginActivityPresenter", "body = null");
                                return;
                            }
                            try {
                                Log.e("LoginActivityPresenter", body.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        },
                        throwable -> {
                            Log.e("LoginActivityPresenter", throwable.getMessage());
                        }, () -> {
                            Log.e("LoginActivityPresenter", "complete");
                        });
    }
}
