package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.local.Lock;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IMVP;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.utils.SPUtils;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by wyw on 2016/10/31.
 */

public class BasePresenter<G extends IMVP> implements Presenter<G> {
    //activity
//    private WeakReference<T> mActivity;
    private Lock lock;
    /**
     * 这个是所有请求
     */
    private CompositeSubscription subscription;
    /**
     * 当前界面进行上传的请求
     */
    private HashMap<Object, Subscription> maps;
    //接口
    private G mView;


    private NetClient.RequestService request;

    private User user;

    public BasePresenter(G view) {
        attachActivity(view);
        lock = new Lock();
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public void attachActivity(G view) {
//        this.mActivity = new WeakReference<>(activity);
        this.mView = view;
        request = NetClient.getRequest();
    }

    @Override
    public void detachActivity() {
//        if (mActivity != null) mActivity = null;
        if (mView != null) mView = null;
        onUnSubscribe();
    }

    @Override
    public void addSubscribe(Object tag, Subscription subscription) {
        if (maps == null) {
            maps = new HashMap<>();
        }
        if (maps.containsKey(tag)) {
            Subscription subscription1 = maps.get(tag);
            if (subscription1 != null && subscription1.isUnsubscribed()) {
                subscription1.unsubscribe();
            }
        }
        maps.put(tag, subscription);
    }

    @Override
    public void addSubscribe(Subscription subscription) {
        if (this.subscription == null) {
            this.subscription = new CompositeSubscription();
        }
        this.subscription.add(subscription);
    }

    @Override
    public void addSubscribe(Observable observable, Subscriber subscriber) {
        if (this.subscription == null) {
            this.subscription = new CompositeSubscription();
        }

        this.subscription.add(observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    @Override
    public void onUnSubscribe() {
        if (subscription != null && subscription.hasSubscriptions()) {
            subscription.unsubscribe();
        }
        if (maps != null) {
            for (Object key : maps.keySet()) {
                if (maps.get(key) != null && maps.get(key).isUnsubscribed()) {
                    maps.get(key).unsubscribe();
                }
            }
        }
    }

//    public T getmActivity() {
//        return mActivity.get();
//    }

    public G getView() {
        return mView;
    }

    public SPUtils getSpUtils() {
        return SPUtils.getInstance();
    }

    public NetClient.RequestService getRequest() {
        return request;
    }

    //
    public User getUser() {
        if (user == null) {
            user = (User) getSpUtils().getObjectData(Constance.USER_OBJECT);
//            user = new User();
        }
        return user;
    }

    public String getUserId() {
        //Log.e("-----------","------------------------------getUserId()-------------------------------");
        return String.valueOf(getUser().getUSERID());
    }

    public String getFRID(){
        return String.valueOf(getUser().getFRID());
    }

    public String getFuName() {
        return String.valueOf(getUser().getFUNAME());
    }

}
