package com.wyw.jiangsu.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.AnimProcessListActivity;
import com.wyw.jiangsu.activity.ShowSdActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessApplyMessageAcceptListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessConcentrateProcessListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessHomeVeterinatiansListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessMessageAcceptCollectionListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessMessageAcceptStorageListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessSupervisionVeterinatiansListActivity;
import com.wyw.jiangsu.adapter.Ceshi;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.service.downpush.DownloadService;
import com.wyw.jiangsu.utils.SPUtils;
import com.wyw.jiangsu.utils.SoundPoolUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    private static final String TAG = "GetuiSdkDemo";

    /**
     * 为了传数据变化.观察透
     */
    private static int cnt;

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.d(TAG, "onReceiveServicePid -> " + pid);
    }


    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();

        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));

        Log.d(TAG, "onReceiveMessageData -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nmessageid = " + messageid + "\npkg = " + pkg
                + "\ncid = " + cid);

        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
            Intent intent = new Intent(this, LocationService.class);
            startService(intent);
//            RxBus.getInstance().post(new RefreshBus(RefreshBus.Text));
        } else {
            String data = new String(payload);
            Log.d(TAG, "receiver payload = " + data);
            SoundPoolUtils.getInstance().playSound();
            // 测试消息为了观察数据变化
            cnt++;
            Log.e("GetuiSdkDemo", "receiver payload : " + data);

//            TestUtils.writeFile(context, data);
            //解析json
            try {
                //通知公告
                TongZhiBean bean = new Gson().fromJson(data, TongZhiBean.class);
                String[] fileName = bean.getFileName().split(",|，");
                String[] url = bean.getUrl().split(",|，");
                for (int i = 0; i < fileName.length; i++) {
                    Intent intent = new Intent(context, DownloadService.class);
                    intent.setAction(DownloadService.ADD_TASK);
                    intent.putExtra("url", url[i]);
                    intent.putExtra("fileName", fileName[i]);
                    startService(intent);
                }
            } catch (Exception e) {
            }
            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //乡镇站长确认
                if ("HT_BSDWSWSB".equals(tableName)) {
                    createNotification(context, makeIntentStack(context, HarmlessApplyMessageAcceptListActivity.class),
                            content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }

            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //现场勘验
                if ("APP_JGSYZP".equals(tableName)) {
                    createNotification(context, makeIntentStack(context, HarmlessSupervisionVeterinatiansListActivity.class),
                            content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }

            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //收集点
                if ("HT_JGSYDSB".equals(tableName)) {
                    createNotification(context, makeIntentStack(context, HarmlessMessageAcceptCollectionListActivity.class),
                            content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }

            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //收集运输
                if ("APP_WHHSJRW".equals(tableName)) {
                    createNotification(context, makeIntentStack(context, HarmlessMessageAcceptStorageListActivity.class),
                            content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }

            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //入场确认
                if ("APP_SJQR".equals(tableName)) {
                    createNotification(context, makeIntentStack(context, HarmlessHomeVeterinatiansListActivity.class),
                            content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }

            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //集中处理
                if ("APP_PTWHHCL".equals(tableName)) {
                    createNotification(context, makeIntentStack(context, HarmlessConcentrateProcessListActivity.class),
                            content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }
            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //检查站
                if ("".equals(tableName)) {
//                    createNotification(context, makeIntentStack(context, MainActivity.class), content, title);
                    createNotification(context, content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }

            try {
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //通知公告
                if ("Sys_Inform".equals(tableName)) {
                    createNotification(context, makeIntentStack(context, ShowSdActivity.class),
                            content, title);
                }
            } catch (Exception e) {
                
            }

            try {
//                BaseMsgBean bean = new Gson().fromJson(data, BaseMsgBean.class);
//                String tableName = bean.getData().getResult1().getTableName();
//                String title = bean.getData().getResult1().getTitle();
//                String content = bean.getData().getResult1().getContent();
                Ceshi bean = new Gson().fromJson(data, Ceshi.class);
                String tableName = bean.getTableName();
                String title = bean.getTitle();
                String content = bean.getContent();
                //动物检疫申报单处理
                if ("Qua_QuarantineDeclarationCD".equals(tableName)) {
//                        SPUtils.getInstance().saveData(Constance.SP_PROCESS, "data");
//                        SPUtils.getInstance().saveData(Constance.SP_PROCESS_JIZHONG, "data");
//                        EventBus.getDefault().post(new MessageEvent(MessageEvent.TYPE_PROCESS_JIZHONG));
//                        EventBus.getDefault().post(new MessageEvent(MessageEvent.TYPE_HOME_PROCESS));
                    createNotification(context, makeIntentStack(context, AnimProcessListActivity.class),
                            content, title);
                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                }
            } catch (Exception e) {
            }
            Log.d("PushDemoReceiver", "end");
        }
        Log.e(TAG, "----------------------------------------------------------------------------------------------");
    }

    @Override
    public void onReceiveClientId(final Context context, final String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
//        TestUtils.writeFile(context, "clientid = " + clientid);
        new Thread() {
            @Override
            public void run() {
                long userId = ((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getUSERID();
//                String useId = context.getSharedPreferences("date", Context.MODE_PRIVATE).getString("userid", "");
                String id = "";
                MultipartBody.Builder multyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("UserID", String.valueOf(userId))
                        .addFormDataPart("ClientID", clientid);
                while (TextUtils.isEmpty(id)) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new StethoInterceptor())
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            //设置错误重连
                            .retryOnConnectionFailure(true)
                            .build();
                    try {
                        Response response = client.newCall(new Request.Builder().url(NetClient.URL + "UploadClientID.ashx").post(multyBuilder.build()).build()).execute();
                        id = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("onReceiveClientId", "上传完毕" + id);
//                TestUtils.appendFile(context, "上传完毕 = " + id);
            }
        }.start();
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.d(TAG, "onReceiveOnlineState -> " + (online ? "online" : "offline"));
    }

    //    onReceiveOnlineState cid 离线上线通知 <br>
    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.d(TAG, "onReceiveCommandResult -> " + cmdMessage);
        int action = cmdMessage.getAction();

        if (action == PushConsts.SET_TAG_RESULT) {
            setTagResult((SetTagCmdMessage) cmdMessage);
        } else if ((action == PushConsts.THIRDPART_FEEDBACK)) {
            feedbackResult((FeedbackCmdMessage) cmdMessage);
        }
    }

    private void setTagResult(SetTagCmdMessage setTagCmdMsg) {
        String sn = setTagCmdMsg.getSn();
        String code = setTagCmdMsg.getCode();

        String text = "设置标签失败, 未知异常";
        switch (Integer.valueOf(code)) {
            case PushConsts.SETTAG_SUCCESS:
                text = "设置标签成功";
                break;

            case PushConsts.SETTAG_ERROR_COUNT:
                text = "设置标签失败, tag数量过大, 最大不能超过200个";
                break;

            case PushConsts.SETTAG_ERROR_FREQUENCY:
                text = "设置标签失败, 频率过快, 两次间隔应大于1s且一天只能成功调用一次";
                break;

            case PushConsts.SETTAG_ERROR_REPEAT:
                text = "设置标签失败, 标签重复";
                break;

            case PushConsts.SETTAG_ERROR_UNBIND:
                text = "设置标签失败, 服务未初始化成功";
                break;

            case PushConsts.SETTAG_ERROR_EXCEPTION:
                text = "设置标签失败, 未知异常";
                break;

            case PushConsts.SETTAG_ERROR_NULL:
                text = "设置标签失败, tag 为空";
                break;

            case PushConsts.SETTAG_NOTONLINE:
                text = "还未登陆成功";
                break;

            case PushConsts.SETTAG_IN_BLACKLIST:
                text = "该应用已经在黑名单中,请联系售后支持!";
                break;

            case PushConsts.SETTAG_NUM_EXCEED:
                text = "已存 tag 超过限制";
                break;

            default:
                break;
        }

        Log.d(TAG, "settag result sn = " + sn + ", code = " + code + ", NoDealWithInfoBean = " + text);
    }

    private void feedbackResult(FeedbackCmdMessage feedbackCmdMsg) {
        String appid = feedbackCmdMsg.getAppid();
        String taskid = feedbackCmdMsg.getTaskId();
        String actionid = feedbackCmdMsg.getActionId();
        String result = feedbackCmdMsg.getResult();
        long timestamp = feedbackCmdMsg.getTimeStamp();
        String cid = feedbackCmdMsg.getClientId();

        Log.d(TAG, "onReceiveCommandResult -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nactionid = " + actionid + "\nresult = " + result
                + "\ncid = " + cid + "\ntimestamp = " + timestamp);
    }

    public static void createNotification(Context context, Intent intents, String content, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon_home_nor);
        PendingIntent intent = PendingIntent.getActivity(context, 0, intents, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(intent);
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1, builder.build());
    }

    public static void createNotification(Context context, String content, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon_home_nor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(content)
                    .setBigContentTitle(title));
        }
        builder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(), 0));
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1, builder.build());
    }

    public static Intent makeIntentStack(Context context, Class acitivity) {
        return new Intent(context, acitivity);
    }

    private class TongZhiBean {
        private String fileName;
        private String title;
        private String content;
        private String url;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
