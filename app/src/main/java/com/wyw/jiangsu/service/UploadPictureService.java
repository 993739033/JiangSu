package com.wyw.jiangsu.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.activity.chaxun.AgencyDeclareDetilActivity;
import com.wyw.jiangsu.activity.wuhaihua.YangzhihuDetailActivity;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import okhttp3.OkHttpClient;

/**
 * Created by wyw on 2017/5/11.
 */

public class UploadPictureService extends Service {
    public static final String NEW_LIFEFORM_DETECTED1 = "com.dxz.broadcasttest.NEW_LIFEFORM1";
    public static final String NEW_LIFEFORM_DETECTED2 = "com.dxz.broadcasttest.NEW_LIFEFORM2";
    public static final String ADD_TASK = "com.mingnong.changshaapp.addTask";
    //下载之前遗留的文件
    public static final String CLEAR_DB = "com.mingnong.changshaapp.clear_db";
    private final int ONE_MINUTE = 60 * 1000;
    private PictureDB db;
    private ExecutorService executorService;
    private ArrayList<String> preparedTask = new ArrayList<>();
    private UploadTask currentTask;
    private OkHttpClient client;
    private Handler timmerHandler;
    private int i = 0;
    private UploadPictureListener listener = new UploadPictureListener() {
        @Override
        public void onStart(UploadTask uploadTask) {

        }

        @Override
        public void onError(UploadTask uploadTask, int errorCode) {
//            writeText(uploadTask);
            switch (errorCode) {
                case UPLOAD_ERROR_FILE_NOT_FOUND:
                    db.delete(uploadTask.getId());
                    preparedTask.remove(uploadTask.getId());
                    break;
                case UPLOAD_ERROR_RESPONSE_NOT_SUCCESS:
                case UPLOAD_ERROR_ERRORCODE_NOT_ZERO:
                    uploadTask.setRetry(i++);
                    if (!NetWorkUtils.isOpenNetwork(MyApplication.getContext())) {
                        preparedTask.clear();
                        currentTask = null;
                        return;
                    } else if (uploadTask.getRetry() >= 5) {
                        i = 0;
                        db.delete(uploadTask.getId());
                        //此图确实上传弄不上去 1分钟继续上传
//                        db.delete(uploadTask.getId());
                        preparedTask.remove(uploadTask.getId());
//                        db.update(uploadTask.getId(), System.currentTimeMillis() + ONE_MINUTE, uploadTask.getRetry() + 1);
                        uploadTask.deleteFile();
                        Log.e("UploadPictureService", "retry");
                    }

                    break;
                case UPLOAD_ERROR_ERRORCODE_OTHER:
                    //10s后继续上传
                    preparedTask.remove(uploadTask.getId());
                    db.update(uploadTask.getId(), System.currentTimeMillis() + ONE_MINUTE / 6, uploadTask.getRetry() + 1);
                    break;
            }
            currentTask = null;
            startTask();
        }

        @Override
        public void onCompleted(UploadTask uploadTask) {
            if (preparedTask.size() > 0 && currentTask != null) {
                preparedTask.remove(currentTask.getId());
//                writeText(uploadTask);
                uploadTask.deleteFile();//上传成功删除图片
                db.delete(uploadTask.getId());
            }
            currentTask = null;
            startTask();
            Intent it1 = new Intent(NEW_LIFEFORM_DETECTED1);
            sendBroadcast(it1);
            Intent it2 = new Intent(NEW_LIFEFORM_DETECTED2);
            sendBroadcast(it2);
        }
    };

    /**
     * 追加文件：使用FileWriter
     */
    public static void writeText(UploadTask task) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        FileWriter writer = null;
        File file = new File(Environment.getExternalStorageDirectory(), "test.txt");
        String content = "id:" + task.getId() + "\t localName: " + task.getLocalName() +
                "\tretry: " + task.getRetry() + "\t时间" + format.format(new Date()) + "\r\n";
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(file, true);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//        if (intent == null) {
////            mNotificationManager.cancel(notificationid);
//        }
//        String action = null;
//        try {
//            action = intent.getAction();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        if (action == null) {
//            return ;
//        }
//        switch (action) {
//            case ADD_TASK:
//                String localName = intent.getStringExtra("localName");
//                String uploadName = intent.getStringExtra("uploadName");
//                String address = intent.getStringExtra("address");
//                addTask(localName, uploadName, address);
//                break;
//            case CLEAR_DB:
//                startTask();
//                break;
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter counterActionFilter = new IntentFilter(NEW_LIFEFORM_DETECTED1);
        YangzhihuDetailActivity.InnerReceiver receiver = new YangzhihuDetailActivity.InnerReceiver();
        registerReceiver(receiver, counterActionFilter);

        IntentFilter cof = new IntentFilter(NEW_LIFEFORM_DETECTED2);
        AgencyDeclareDetilActivity.InnerReceiver re = new AgencyDeclareDetilActivity.InnerReceiver();
        registerReceiver(re, cof);

        timmerHandler = new Handler(Looper.myLooper());
        executorService = Executors.newCachedThreadPool();
        db = PictureDB.getInstance(getApplicationContext());
        client = new OkHttpClient().newBuilder()
//                .addInterceptor(chain -> {
//                    Request request = chain.request();
//                    Response response = chain.proceed(request);
//                    String content= response.body().string();
//                    Log.e("tag", content);
//                    return response.newBuilder().build();
//                })
//                .addInterceptor(new HttpLoggingInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    //
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
//            mNotificationManager.cancel(notificationid);
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
                String localName = intent.getStringExtra("localName");
                String address = intent.getStringExtra("address");
                String uuid = intent.getStringExtra("uuid");
                addTask(localName, address, uuid);
                break;
            case CLEAR_DB:
                currentTask = null;
                preparedTask.clear();
                startTask();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private synchronized void addTask(String localName, String address, String uuid) {
        PictureBean bean = new PictureBean(String.valueOf((address + localName).hashCode()),
                localName, address, System.currentTimeMillis(), 0, uuid);
        db.insert(bean, null);
        preparedTask.add(bean.getId());
        if (currentTask == null) {
            startTask();
        }
    }

    private synchronized void startTask() {
        if (currentTask != null) return;
        if (preparedTask.size() > 0) {
            UploadTask task = null;
            PictureBean bean = db.getTask(preparedTask.get(0));
            if (bean != null) {
                task = UploadTask.parse(bean, this);
            }
            if (task == null) {
//                db.delete(preparedTask.get(0));
                preparedTask.remove(0);
                startTask();
                return;
            }
            task.setClient(client);
            task.setListener(listener);
            executorService.submit(task);
            currentTask = task;
            Log.e("UploadPictureService", "submit task");
        } else {
            //从数据库读取数据
            long time = db.getTimmerTime();
            if (time > 0) {
                //设置定时器
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        startTask();
//                    }
//                }, time);
                timmerHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startTask();
                    }
                }, time);
            } else if (time == 0) {
                List<String> taskList = db.getTaskList();
                if (taskList.size() > 0) {
                    preparedTask.addAll(taskList);
                    startTask();
                }
            } else {
                //删除目录下面的的图片 应该放在开启activity的时候 Presenter开线程删除
//                deleteUnlessPic(TakePictureUtil.getStoreDir());
                deleteUnlessPic(PhotoUtils.getWuHaiHuaDir());
            }

        }
    }

    private void deleteUnlessPic(File dir) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteUnlessPic(file);
            }
        } else {
            //数据库查询
            db.deleteFile(dir);
//            dir.delete();
        }
    }

}
