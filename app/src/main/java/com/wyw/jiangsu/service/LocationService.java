package com.wyw.jiangsu.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wyw.jiangsu.bean.WeizhiBean;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.utils.SPUtils;

/**
 * Created by Windows on 2017/11/28.
 */

public class LocationService extends Service {

    public AMapLocationClient locationClient = null;


    private User user = (User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT);
    private String[] xinxi = {"请联系相关技术人员",
            "请重新尝试",
            "请在手机的设置中开启app的定位权限",
            "建议到相对开阔的露天场所再次尝试",
            "建议手机关闭飞行模式，并打开WIFI开关",
            "建议手机插上sim卡，打开WIFI开关",
            "请检查当前网络是否通畅"};

    @Override
    public void onCreate() {
        super.onCreate();


    }

    /**
     * 定位监听
     */
    protected AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                if (loc.getErrorCode() == 0) {
                    //解析定位结果
                    upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", "");
                } else {
                    switch (loc.getErrorCode()) {
                        case 1:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 15:
                        case 16:
                        case 17:
                            upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", xinxi[0]);
                            break;
                        case 2:
                            upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", xinxi[1]);
                            break;
                        case 12:
                        case 13:
                            upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", xinxi[2]);
                            break;
                        case 14:
                            upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", xinxi[3]);
                            break;
                        case 18:
                            upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", xinxi[4]);
                            break;
                        case 19:
                            upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", xinxi[5]);
                            break;
                        case 3:
                        case 4:
                        case 5:
                            upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", xinxi[6]);
                            break;
                        default:
                            break;
                    }
                }
            } else {
                upload(getUserId(), loc.getLongitude() + "", loc.getLatitude() + "", loc.getErrorInfo());
            }
        }
    };

    public String getUserId() {
        return String.valueOf(user.getUSERID());
    }

    private void upload(String s, String latitude, String longitude, String errormsg) {
        NetClient.upWeizhi(s, latitude, longitude, errormsg)
                .map(bean -> {
                    if (bean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(bean.getErrorMsg());
                    }
                    WeizhiBean.DataBean data = new StatusException(bean).getObjectData(WeizhiBean.DataBean.class);
                    destroyLocation();
                    return null;
                })
                .subscribe();
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        return mOption;
    }


    /**
     * 开始定位
     */
    private void startLocation() {
        // 启动定位
        locationClient.startLocation();
    }


    /**
     * 销毁定位
     */
    private void destroyLocation() {
        locationClient.unRegisterLocationListener(locationListener);
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //初始化client
        locationClient = new AMapLocationClient(this);
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        startLocation();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
