package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.TimeBean;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wyw on 2016/12/27.
 * 获取服务器时间的模块
 */

public class TimePresenter extends BasePresenter<ITime> {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);

    public TimePresenter(ITime view) {
        super(view);
    }

    public void getTime() {
        addSubscribe(NetClient.getDate()
                .map(timeBean ->
                        new StatusException(timeBean).getObjectData(TimeBean.Data.class))
                .subscribe(timeBean -> {
                            long longTime = new Date().getTime();
                            try {
                                longTime = dateFormat.parse(timeBean.getResult()).getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            getView().setTime(timeBean.getResult(), longTime);
                        }
                        , throwable -> {
//                            获取本地时间
                            Date date = new Date();
                            getView().setTime(dateFormat.format(date), date.getTime());
                        }));
    }
}
