package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.interfac.IFaDingFaGui;
import com.wyw.jiangsu.runnable.NetClient;

/**
 * Created by wyw on 2018/1/17.
 */

public class FaDingFaGuiPresenter extends BasePresenter<IFaDingFaGui> {
    public FaDingFaGuiPresenter(IFaDingFaGui view) {
        super(view);
    }

    public void loadLaw(String text) {
        addSubscribe(NetClient.getLaw(text)
                .map(fadingFaguiBean -> {
                    if (fadingFaguiBean.getErrorCode() != 0) {
                        throw new NullPointerException(fadingFaguiBean.getErrorMsg());
                    }
                    return fadingFaguiBean.getData();
                })
                .subscribe(dataLists -> {
                    getView().loadLaw(dataLists);
                }, throwable -> {
                    Toast.makeText(MyApplication.getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                }));
    }
}
