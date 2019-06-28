package com.wyw.jiangsu.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wyw on 2016/7/18.
 */
public class ApkBroadCastReceiver extends BroadcastReceiver {
    public static final String APK_INSTALL = Intent.ACTION_PACKAGE_INSTALL;
    public static final String APK_ADDED= Intent.ACTION_PACKAGE_ADDED;
    public static final String APK_REMOVED = Intent.ACTION_PACKAGE_REMOVED;
    public static final String APK_CHANGED = Intent.ACTION_PACKAGE_CHANGED;
    public static final String APK_REPLACED = Intent.ACTION_PACKAGE_REPLACED;
    public static final String APK_RESTARTED = Intent.ACTION_PACKAGE_RESTARTED;
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case APK_INSTALL:
                Log.e("ApkBroadCastReceiver", "receiver 安装 install");
                break;
            case APK_ADDED:
                Log.e("ApkBroadCastReceiver", "receiver 安装 added");
                break;
            case APK_REMOVED:
                Log.e("ApkBroadCastReceiver", "receiver 删除 removed");
                break;
            case APK_CHANGED:
                Log.e("ApkBroadCastReceiver", "receiver 改变 changed");
                break;
            case APK_REPLACED:
                Log.e("ApkBroadCastReceiver", "receiver 替换 replaced");
                break;
            case APK_RESTARTED:
                Log.e("ApkBroadCastReceiver", "receiver 重启 restarted");
                break;
        }
    }
}
