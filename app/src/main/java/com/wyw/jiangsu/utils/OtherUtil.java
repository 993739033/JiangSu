package com.wyw.jiangsu.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wyw.jiangsu.MyApplication;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wyw on 2016/11/7.
 * 一些单独针对本项目使用的util
 */

public class OtherUtil {
    private static Context context;


    public static Context getApplicationContent() {
        return context;
    }

    /**
     * 获取文本
     *
     * @param view textview | edittext
     * @return
     */
    public static String toString(View view) {
        if (view instanceof EditText) {
            return ((EditText) view).getText().toString();
        }
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        }
        return view.toString();
    }

    /**
     * startTime 大于等于 endTime 返回 true 说明有问题
     *
     * @param startTime yyyy-MM-dd 小时间
     * @param endTime   大时间
     * @return
     */
    public static boolean compareTime(String startTime, String endTime) {
        //分割 startTime
        String[] splitStart = startTime.split("-");
        String[] splitEnd = endTime.split("-");
        //2015 < 2016
        if (Integer.valueOf(splitStart[0]) < Integer.valueOf(splitEnd[0]) ||
                Integer.valueOf(splitStart[1]) < Integer.valueOf(splitEnd[1]) ||
                Integer.valueOf(splitStart[2]) < Integer.valueOf(splitEnd[2])) {
            return false;
        }
        if (Integer.valueOf(splitStart[0]).intValue() == Integer.valueOf(splitEnd[0]).intValue() &&
                Integer.valueOf(splitStart[1]).intValue() == Integer.valueOf(splitEnd[1]).intValue() &&
                Integer.valueOf(splitStart[2]).intValue() == Integer.valueOf(splitEnd[2]).intValue())
            return false;
        return true;
    }

    /**
     * 是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str) {
        Pattern pattern = Pattern.compile("(^0\\d{2,3}-\\d{5,9}|\\d{11}$)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 从当前时间 前取5年 后取5年
     *
     * @return
     */
    public static List<String> createYears() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ArrayList<String> years = new ArrayList<>();
        for (int i = year - 4; i < year + 6; i++) {
            years.add(i + "");
        }
        return years;
    }

    public static List<String> createMonths() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add((i < 10 ? "0" : "") + i);
        }
        return list;
    }

    public static List<Integer> createWeeks() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            list.add(i);
        }
        return list;
    }

    public static List<String> createDays(String yearString, String monthString) {
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(yearString), Integer.parseInt(monthString) - 1, 1);
        c.roll(Calendar.DATE, false);
        return d(1, c.get(Calendar.DATE));
    }

    public static List<String> createHours() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            list.add(i + "");
        }
        return list;
    }

    public static List<String> createMinutes() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= 59; i++) {
            list.add(i + "");
        }
        return list;
    }

    /**
     * 获取的是下标
     */
    public static int getCurrentMonthPosition() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getCurrentDayPosition() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1;
    }

    public static int getCurrentHourPosition() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentMinutePosition() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 打开文件
     *
     * @param file
     */
    public static void openFile(File file) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addCategory("android.intent.category.DEFAULT");
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        Uri contentUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            contentUri = FileProvider.getUriForFile(MyApplication.getContext(), "com.wyw.jiangsu.fileprovider", file);

        } else {
            contentUri = Uri.fromFile(file);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//新增  增加读写权限
        intent.setDataAndType(contentUri, type);
        //跳转
        try {
            MyApplication.getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数字传化为集合，并且补充0
     *
     * @param startNum 数字起点
     * @param count    数字个数
     * @return
     */
    private static List<String> d(int startNum, int count) {
        String[] values = new String[count];
        for (int i = startNum; i < startNum + count; i++) {
            String tempValue = (i < 10 ? "0" : "") + i;
            values[i - startNum] = tempValue;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i]);
        }
        return list;
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    private static String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end.equals("")) return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    public static String getPhoneIP() {
        ConnectivityManager connMgr = (ConnectivityManager) MyApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        if (isMobileConn) {
            return getLocalIpAddress();
        } else if (isWifiConn) {
            return getIp();
        } else {
            return null;
        }


    }

    public static String getLocalIpAddress() {
        try {
            String allIP = "";
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    allIP += inetAddress.getHostAddress() + "\n";
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
//                        return inetAddress.getHostAddress();
                    }
                }
            }
            return allIP;
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return "获取失败";
    }

    public static String getIp() {
        @SuppressLint("WifiManagerLeak") WifiManager wm = (WifiManager) MyApplication.getContext().getSystemService(Context.WIFI_SERVICE);

//        @SuppressLint("WifiManagerLeak")
//        WifiManager wm = (WifiManager) getApplicationContent().getSystemService(Context.WIFI_SERVICE);
        //检查Wifi状态
        if (!wm.isWifiEnabled())
            return "wifi未开启";
        WifiInfo wi = wm.getConnectionInfo();
        //获取32位整型IP地址
        int ipAdd = wi.getIpAddress();
        //把整型地址转换成“*.*.*.*”地址
        String ip = intToIp(ipAdd);
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());

        return format.format(curDate);

        /* SimpleDateFormat formatter=new SimpleDateFormat("");
        Date curDate =new Date(System.currentTimeMillis());*/

    }

    //验证密码格式
    public static boolean isPwdType(String pwd) {
        //^[A-Za-z0-9@]{6,16}
        String str = "^[A-Za-z0-9@]{6,14}";
        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(pwd);

        return m.matches();
    }

    //\d{15}|\d{18}
    public static boolean isidCard(String idcard) {
        String str = "\\d{15}|\\d{18}";
        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(idcard);

        return m.matches();
    }

    public static boolean isTyCode(String code) {
        String str = "^([A-Z0-9]{15})$|^([A-Z0-9]{18})$|^([A-Z0-9]{20})$";
        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(code);

        return m.matches();

    }

    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }

    public static boolean isEmail(String email) {

        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(email);

        return m.matches();

    }

    private static final String[][] MIME_MapTable = {
            //{后缀名， MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };
}
