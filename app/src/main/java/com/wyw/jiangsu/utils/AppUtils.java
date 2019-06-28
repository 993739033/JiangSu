package com.wyw.jiangsu.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.wyw.jiangsu.MyApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyw on 2016/7/13.
 */

public class AppUtils {
    /**
     * 获取app名字
     */
    public static String getAppVersionName(Context context, String packageName) {
        String appVersion = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            appVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }

    public static String getAppVersionName() {
        return getAppVersionName(MyApplication.getContext(), MyApplication.getContext().getPackageName());
    }

    /**
     * 获取手机的硬件信息
     * @return
     */
    public static String getMobileInfo() {
        StringBuffer sb = new StringBuffer();
        //通过反射获取系统的硬件信息
        try {

            java.lang.reflect.Field[] fields = Build.class.getDeclaredFields();
            for(java.lang.reflect.Field field: fields){
                //暴力反射 ,获取私有的信息
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(null).toString();
                sb.append(name+"="+value);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 获取app版本code
     * @param context
     * @param packageName
     * @return
     */
    public static int getAppVersionCode(Context context, String packageName) {
        int appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            appVersionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersionCode;
    }

    /**
     * 安装apk
     * @param context
     * @param filePath
     * @return
     */
    public static boolean installApk(Context context, String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile() || file.length() <= 0) {
            return false;
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context,"com.wyw.jiangsu.fileprovider",file);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.parse("file://" + filePath);
        }
//        uri = Uri.parse("file://" + filePath);
        i.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(i);
        return true;
    }
    /**
     * 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName){
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
