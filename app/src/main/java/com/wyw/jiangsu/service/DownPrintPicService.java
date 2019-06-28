package com.wyw.jiangsu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wyw.jiangsu.bean.DownPrintImgBean;
import com.wyw.jiangsu.interfac.IDownPrintImgListener;
import com.wyw.jiangsu.runnable.DownPrintImg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by HUANG on 2017/7/28.
 */
public class DownPrintPicService extends Service {
    public static final String ADD_TASK = "com.mingnong.jianggsu.downprint.addTask";
    private OkHttpClient client;
    private final int ONE_MINUTE = 60 * 1000;
    private int retry;
    private String imgAddress;
    private String url;
    private String filename;
    private List<String> preparedTask=new ArrayList<>();
    private DownPrintImg currentTask;
    private IDownPrintImgListener listener=new IDownPrintImgListener() {
        @Override
        public void onStart(DownPrintImg downPrintImg) {

        }

        @Override
        public void onError(DownPrintImg downPrintImg, int code) {
                switch (code){
                    case IDownPrintImgListener.UPLOAD_ERROR_FILE_EXIST:
                        db.delete(downPrintImg.getId());
                        preparedTask.remove(downPrintImg.getId());
                        break;
                    case IDownPrintImgListener.UPLOAD_ERROR_RESPONSE_NOT_SUCCESS:
                        /*if (!NetWorkUtils.isOpenNetwork(getApplicationContext())){
                            Log.e(DownPrintPicService.class.getSimpleName(),"沒有網絡");
                            preparedTask.clear();
                        }else {
                            currentTask = null;
                            startTask();
                        }*/
                        currentTask = null;
                        startTask();
                        break;
                }
        }

        @Override
        public void onCompleted(DownPrintImg downPrintImg) {
            Log.e(DownPrintPicService.class.getSimpleName(),"下載成功");
            if (preparedTask.size() > 0 && currentTask != null) {
                preparedTask.remove(currentTask.getId());
                db.delete(downPrintImg.getId());
            }
            currentTask = null;
            startTask();
        }
    };
    private ExecutorService executorService;
    private DownPrintPicDB db;
    private Handler timmerHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(DownPrintPicService.class.getSimpleName(),"onCreate");
        executorService = Executors.newCachedThreadPool();
        db = DownPrintPicDB.getInstance(getApplicationContext());
        timmerHandler = new Handler(Looper.myLooper());
        client = new OkHttpClient().newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(DownPrintPicService.class.getSimpleName(),"onStartCommand");
        String action = null;
        try {
            action = intent.getAction();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (action == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        switch (action){
            case ADD_TASK:
                String imgAddress=intent.getStringExtra("imgAddress");
                String filename=intent.getStringExtra("filename");
                String url=intent.getStringExtra("imgurl");
                addTask(imgAddress,filename,url);
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }
    private synchronized void addTask(String imgAddress,String localName,String uuid){
        Log.e(DownPrintPicService.class.getSimpleName(),"addTask");
        DownPrintImgBean bean = new DownPrintImgBean(String.valueOf((imgAddress + localName).hashCode()),
                localName, imgAddress, System.currentTimeMillis(), 0, uuid);
        db.insert(bean, null);
        preparedTask.add(bean.getId());
        preparedTask.add(bean.getId());
        if (currentTask==null)
            startTask();
    }
    private synchronized void startTask(){
        Log.e(DownPrintPicService.class.getSimpleName(),"startTask"+currentTask+"==size=="+preparedTask.size());
            if (currentTask!=null)return;
            if (preparedTask.size()>0){
                Log.e(DownPrintPicService.class.getSimpleName(),"preparedTask.size()=="+preparedTask.size());
                DownPrintImg task = null;
                DownPrintImgBean bean = db.getTask(preparedTask.get(0));
                if (bean != null) {
                    task =new DownPrintImg(bean);
                }
                if (task == null) {
                    preparedTask.remove(0);
                    startTask();
                    return;
                }
                task.setClient(client);
                task.setListener(listener);
                executorService.submit(task);
                currentTask = task;

            }else{
                long time = db.getTimmerTime();
                if (time > 0) {
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
                }
            }
    }
   /* class NetWorkStateReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){

                NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if (wifi.isConnected()&&mobile.isConnected()){
                    startTask();
                }else if(wifi.isConnected()&&!mobile.isConnected()){
                    startTask();
                }else if (!wifi.isConnected()&&mobile.isConnected()){
                    startTask();
                }else{
                    Toast.makeText(getApplicationContext(),"没有网络",0).show();
                }

            }
        }
    }*/
}
