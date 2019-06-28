package com.wyw.jiangsu.presenter;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by wyw on 2016/10/31.
 */

public interface Presenter<G> {
    void attachActivity(G view);

    void detachActivity();
     void addSubscribe(Object tag,Subscription subscription);
    void addSubscribe(Subscription subscription);
    void addSubscribe(Observable observable, Subscriber subscriber);

    void onUnSubscribe();
}
