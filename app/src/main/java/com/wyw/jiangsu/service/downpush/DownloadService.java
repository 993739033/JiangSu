package com.wyw.jiangsu.service.downpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.DownloadBean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.wyw.jiangsu.broadcast.OpenFileReceiver.OPEN_FILE_ACTION;
import static com.wyw.jiangsu.service.downpush.DownloadService.DismissNotReceiver.DISSMISS_ACION;

/**
 * Created by wyw on 2017/5/19.
 * 通知栏下载推送
 */

public class DownloadService extends Service {
    public static final String ADD_TASK = "com.wyw.xizangphone.download.addtask";
    private ExecutorService service;
    private List<String> prepareTaskList;
    private DownloadDB db;
    private DownloadTask currentTask, lastCurrentTask;
    private OkHttpClient client;

    private int downTaskCount = 0;
    private int downTaskDownloaded = -1;
    private NotificationManager mNotificationManager;
    private int notificationid = 10;
    private RemoteViews remoteViews;
    private Context mContext;
    private boolean isForeground;

    private BroadcastReceiver receiver;
    private DownloadTaskListener listener = new DownloadTaskListener() {
        @Override
        public void onPrepare(DownloadTask downloadTask) {

        }

        @Override
        public void onStart(DownloadTask downloadTask) {

        }

        @Override
        public void onDownloading(DownloadTask downloadTask) {

        }

        @Override
        public void onPause(DownloadTask downloadTask) {

        }

        @Override
        public void onCancel(DownloadTask downloadTask) {
            if (prepareTaskList.size() > 0) {
                if (currentTask != null)
                    prepareTaskList.remove(currentTask.getId());
            }
            currentTask = null;
            upDateNotification();
            startTask();
        }

        @Override
        public void onCompleted(DownloadTask downloadTask) {
            if (prepareTaskList.size() > 0) {
                if (currentTask != null)
                    prepareTaskList.remove(currentTask.getId());
            }
            lastCurrentTask = currentTask;
            currentTask = null;
            downTaskDownloaded++;
            startTask();
        }

        @Override
        public void onError(DownloadTask downloadTask, int errorCode) {
            if (prepareTaskList.size() > 0) {
                if (currentTask != null)
                    prepareTaskList.remove(currentTask.getId());
            }
            currentTask = null;
            downTaskCount--;
            startTask();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prepareTaskList = new ArrayList<>();
        service = Executors.newSingleThreadExecutor();
        db = DownloadDB.getInstance(getApplicationContext());
        receiver = new DismissNotReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(DISSMISS_ACION);
        registerReceiver(receiver, filter);
        client = new OkHttpClient().newBuilder()
//                .addInterceptor(chain -> {
//                    Request request = chain.request();
//                    Response response = chain.proceed(request);
//                    String content= response.body().string();
//                    Log.e("tag", content);
//                    return response.newBuilder().build();
//                })
                .addInterceptor(new HttpLoggingInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .build();
        mContext = this;
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            mNotificationManager.cancel(notificationid);
        }
        String action = null;
        try {
            action = intent.getAction();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (action == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        switch (action) {
            case ADD_TASK:
                String url = intent.getStringExtra("url");
                String fileName = intent.getStringExtra("fileName");
                addTask(url, fileName);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void addTask(String url, String fileName) {
        //从url中获取文件名字
//        String fileName = url.substring(url.lastIndexOf("/") + 1);
        File downloadDir = new File(Environment.getExternalStorageDirectory(), "江苏Download");
        if (!downloadDir.exists()) downloadDir.mkdirs();
        DownloadBean downloadBean = new DownloadBean(DownloadStatus.DOWNLOAD_STATUS_INIT, 0l, 0l,
                url, fileName, downloadDir.getAbsolutePath(), url.hashCode() + "");
        db.insert(downloadBean);
        prepareTaskList.add(downloadBean.getId());
        downTaskCount++;
        upDateNotification();
        Toast.makeText(mContext, "已加入到下载", Toast.LENGTH_SHORT).show();
        if (currentTask == null) {
            startTask();
        }
    }

    private void startTask() {
        if (currentTask != null) return;
        if (prepareTaskList.size() > 0) {
            DownloadTask downloadTask = null;
            DownloadBean entity = db.getTaskById(prepareTaskList.get(0));
            if (entity != null) {
                downloadTask = DownloadTask.parse(entity, mContext);
            }
            if (downloadTask == null) {
                return;
            }
//            if (downloadTask.getDownStatus() != DownloadStatus.DOWNLOAD_STATUS_COMPLETED) {
            downloadTask.setDownStatus(DownloadStatus.DOWNLOAD_STATUS_PREPARE);
//                downloadTask.setdownFileStore(downFileStore);
            downloadTask.setClient(client);
            downloadTask.setListener(listener);
            service.submit(downloadTask);
            lastCurrentTask = currentTask = downloadTask;
            upDateNotification();
//            }
        } else {
            cancleNotification();
        }
    }

    private void upDateNotification() {
        if (currentTask == null) {
            return;
        }
        if (!isForeground) {
            startForeground(notificationid, getNotification(false));
            isForeground = true;
        } else {
            mNotificationManager.notify(notificationid, getNotification(false));
        }
    }

    private void cancleNotification() {
        stopForeground(true);
        isForeground = false;
        mNotificationManager.notify(notificationid, getNotification(true));
        downTaskCount = 0;
        downTaskDownloaded = -1;

    }

    private Notification getNotification(boolean complete) {
        if (downTaskCount == 0) {
            downTaskCount = prepareTaskList.size();
        }
        if (downTaskDownloaded == -1) {
            downTaskDownloaded = 0;
        }
        remoteViews = new RemoteViews(this.getPackageName(), R.layout.down_notification);

//        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0,
//                new Intent(this.getApplicationContext(), DownActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


        final Intent nowPlayingIntent = new Intent();
        nowPlayingIntent.putExtra("dir", lastCurrentTask.getSaveDir());
        nowPlayingIntent.putExtra("fileName", lastCurrentTask.getFileName());
        nowPlayingIntent.setAction(OPEN_FILE_ACTION);
        PendingIntent clickIntent = PendingIntent.getBroadcast(this, 0, nowPlayingIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        if (complete) {
            remoteViews.setTextViewText(R.id.title, "江苏app");
            remoteViews.setTextViewText(R.id.text, "文件下载完成，点击查看");
            remoteViews.setTextViewText(R.id.time, showDate());

        } else {
            remoteViews.setTextViewText(R.id.title, "下载进度：" + downTaskDownloaded + "/" + downTaskCount);
            remoteViews.setTextViewText(R.id.text, "正在下载：" + currentTask.getFileName());
            remoteViews.setTextViewText(R.id.time, showDate());
        }
        Intent deleteIntent = new Intent();
        deleteIntent.setAction(DISSMISS_ACION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDeleteIntent(PendingIntent.getBroadcast(this, 0, deleteIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT))
                .setContentIntent(clickIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            builder.setShowWhen(false);
        }
        return builder.build();
    }

    public static String showDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("a hh:mm");
        String date = sDateFormat.format(new Date());
        return date;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null)
            unregisterReceiver(receiver);
    }

    public class DismissNotReceiver extends BroadcastReceiver {
        public static final String DISSMISS_ACION = "com.wyw.xizangphone.receiver.dismiss";

        @Override
        public void onReceive(Context context, Intent intent) {
            mNotificationManager.cancel(notificationid);
        }
    }
}
