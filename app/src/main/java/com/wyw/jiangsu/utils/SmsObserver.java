package com.wyw.jiangsu.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wyw on 2017/3/1.
 */

public class SmsObserver extends ContentObserver {
    public static String SMS = "content://sms/";
    //收件箱
    public static String SMS_INBOX = "content://sms/inbox";
    //已发送
    public static String SMS_SENT = "content://sms/sent";
    //草稿
    public static String SMS_DRAFT = "content://sms/draft";
    //发件箱
    public static String SMS_OUTBOX = "content://sms/outbox";
    //发送失败
    public static String SMS_SENT_DEFEAT = "content://sms/failed";
    //待发送列表
    public static String SMS_QUEUED = "content://sms/queued";

    private WeakReference<Activity> mActivity;
    private String mContent;
    private SmsListener listener;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsObserver(Handler handler, Activity activity, SmsListener listener) {
        super(handler);
        this.listener = listener;
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        ContentResolver resolver = mActivity.get().getContentResolver();
        Uri uri = null;
//        if (Build.VERSION.SDK_INT >= 24) {
//        } else {
        uri = Uri.parse(SMS_INBOX);
//        }
        Cursor cursor = resolver.query(uri, new String[]{"_id", "address", "body", "read"},
                "body like ? and read = ?", new String[]{"%验证码%", "0"}, "date desc");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String smsBody = cursor.getString(cursor.getColumnIndex("body"));
                String sendPerson = cursor.getString(cursor.getColumnIndex("address"));
                String regEx = "\\d{4}";
                Pattern p = Pattern.compile(regEx);
                Matcher matcher = p.matcher(smsBody);
                if (matcher.find()) {
                    mContent = matcher.group();
                }
//            [^0-9]    mContent = matcher.replaceAll("").trim().toString();
                if (!TextUtils.isEmpty(mContent) && listener != null) {
                    listener.onResult(mContent,sendPerson);
                }
            }
        }
        if (cursor != null)  cursor.close();

    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
    }

    /*
    * 短信回调接口
    */
    public interface SmsListener {
        /**
         * 接受sms状态
         *
         * @Title: onResult
         */
        void onResult(String smsContent,String sendPerson);
    }
}
