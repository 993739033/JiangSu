package com.wyw.jiangsu.presenter;

import android.content.Context;
import android.widget.Toast;

import com.wyw.jiangsu.interfac.IModifyPwd;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HUANG on 2017/9/13.
 */
public class ModifyPwdActivityPresenter extends BasePresenter<IModifyPwd>{

    public ModifyPwdActivityPresenter(IModifyPwd view) {
        super(view);
    }

    public void upload(String json,int uid,String type,String myguid){
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.UploadFF(json, uid, type, myguid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
        .subscribe(data->{
            if (data.getErrorCode()==0){
                getView().uploadSuccess();
            }else if (data.getErrorCode()==1){
                getLock().unLock();
                Toast.makeText(((Context) getView()),data.getErrorMsg(),Toast.LENGTH_SHORT).show();
            }else{
                getLock().unLock();
                Toast.makeText(((Context) getView()),"上传失败，稍后再试",Toast.LENGTH_SHORT).show();
            }
        },new ExceptionImp(getView(),getLock()),new CompleteImp(getView()) {
            @Override
            public void call() {
                super.call();
            }
        })
        );
    }

}
