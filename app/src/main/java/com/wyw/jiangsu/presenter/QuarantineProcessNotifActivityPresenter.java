package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.QuarantineProcessNotifListBeanDetil;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bean.upload.UploadQuarantineProcessNotBeanDetil;
import com.wyw.jiangsu.interfac.IQuarantineProcessNotifActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2017/2/10.
 */
public class QuarantineProcessNotifActivityPresenter extends BasePresenter<IQuarantineProcessNotifActivity>{
    public QuarantineProcessNotifActivityPresenter(IQuarantineProcessNotifActivity view) {
        super(view);
    }


    public QuarantineProcessNotifListBeanDetil.DataBean getNetData(String tableName, String sftId) {
        addSubscribe(NetClient.getQuarantineProcessNotifListBeanDetil2( tableName, sftId)
                .map(duarantineProcessNotifListBeanDetil -> {
                   QuarantineProcessNotifListBeanDetil.DataBean dataBean = new StatusException(duarantineProcessNotifListBeanDetil).getObjectData(QuarantineProcessNotifListBeanDetil.DataBean.class);
                    getView().getNetData(dataBean);
                    return dataBean;
                })
                .subscribe(dataBeen -> getView().getNetData(dataBeen)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

        return null;
    }

    public void upload(UploadQuarantineProcessNotBeanDetil data) {
        addSubscribe(NetClient.uplaod(new Gson().toJson(data),"Qua_Notice",getUserId(),null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode()!=0){
                        Toast.makeText(MyApplication.getContext(), "上传失败", Toast.LENGTH_SHORT).show();
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }else {
                        Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                        getView().uploadsuccess();
                    }
                    return baseMsgBean.getData().getResult();
                }).subscribe(DataListBean -> getView().uploadsuccess()
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void getShouyiJiandu() {

        addSubscribe(NetClient.getShouyiJiandusuoBean(getUserId())
                .map(guarantineDeclareListDetilBean -> {
                    ShouyiJiandusuoBean.DataBean dataList = new StatusException(guarantineDeclareListDetilBean).getObjectData(ShouyiJiandusuoBean.DataBean.class);
                    return dataList;
                })
                .subscribe(DataListBean -> getView().shouyijiandu(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }
}
