package com.wyw.jiangsu.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.utils.SmsObserver;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;
import hugo.weaving.DebugLog;

public class RegisteActivity extends AppCompatActivity implements Handler.Callback{
    //此APPKEY仅供测试使用，且不定期失效，请到mob.com后台申请正式APPKEY
    private static String APPKEY = "1b47042ed3581";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "2c956e011d6d4e2ef442445032c6a09b";
    private EventHandler eventHandler;

    private TimeCount mTimeCount;
    private Button btGetCode;
    private EditText etPhone,etCode;
    private SmsObserver observer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        initSDK();
        mTimeCount = new TimeCount(60000, 1000);
        btGetCode = (Button) findViewById(R.id.bt_get_code);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etCode = (EditText) findViewById(R.id.et_code);
        observer = new SmsObserver(new Handler(),this,(smsContent,sendPerson) -> {
            Log.e("RegisteActivity", smsContent);
            etCode.setText(smsContent);
        });
        getContentResolver().registerContentObserver(Uri.parse(SmsObserver.SMS),true,observer);

        //【掌淘科技】测试的验证码：4103
//        mContent = matcher.replaceAll("").trim().toString();
    }

    private void initSDK() {
        try {
            final Handler handler = new Handler(this);
            eventHandler = new EventHandler() {
                public void afterEvent(int event, int result, Object data) {
                    Message msg = new Message();
                    msg.arg1 = event;
                    msg.arg2 = result;
                    msg.obj = data;
                    handler.sendMessage(msg);
                }
            };

            SMSSDK.registerEventHandler(eventHandler); // 注册短信回调
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        SMSSDK.registerEventHandler(eventHandler); // 注册短信回调
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(eventHandler);
        getContentResolver().unregisterContentObserver(observer);
        super.onDestroy();
    }

    public void sendCode(View view) {
        mTimeCount.start();
        if (!TextUtils.isEmpty(etPhone.getText().toString())) SMSSDK.getVerificationCode("86", etPhone.getText().toString());//获取短信
    }

    public void registe(View view) {
        if (!TextUtils.isEmpty(etPhone.getText().toString()) &&
                !TextUtils.isEmpty(etCode.getText().toString()))
        SMSSDK.submitVerificationCode("86", etPhone.getText().toString(),
                etCode.getText().toString());
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            btGetCode.setClickable(false);
            btGetCode.setText(l/1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            btGetCode.setClickable(true);
            btGetCode.setText("获取验证码");
        }
    }

    private OnSendMessageHandler osmHandler = new OnSendMessageHandler() {
        @Override
        @DebugLog
        public boolean onSendMessage(String s, String s1) {
            runOnUiThread(() -> {
                Log.e("RegisteActivity", Thread.currentThread().getName());
            });
            return true;
        }
    };
    @Override
    public boolean handleMessage(Message msg) {

        int event = msg.arg1;
        int result = msg.arg2;
        Object data = msg.obj;
        if (result == SMSSDK.RESULT_COMPLETE) {
            System.out.println("--------result"+event);
            //回调完成
            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                //验证码成功验证
                Toast.makeText(RegisteActivity.this, "验证码成功验证", Toast.LENGTH_SHORT).show();
            }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                //获取验证码成功
                Toast.makeText(RegisteActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
            }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                //返回支持发送验证码的国家列表
            }

        }else{
            //	((Throwable) data).printStackTrace();
            //	Toast.makeText(MainActivity.this, "验证码错误123", Toast.LENGTH_SHORT).show();

            int status = 0;
            try {
                ((Throwable) data).printStackTrace();
                Throwable throwable = (Throwable) data;

                JSONObject object = new JSONObject(throwable.getMessage());
                String des = object.optString("detail");
                status = object.optInt("status");
                switch (status) {
                    case 400:
                        Toast.makeText(RegisteActivity.this, "无效请求:客户端请求不能被识别", Toast.LENGTH_SHORT).show();
                        break;
                    case 405:
                        Toast.makeText(RegisteActivity.this, "AppKey为空:请求的AppKey为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 406:
                        Toast.makeText(RegisteActivity.this, "AppKey错误:请求的AppKey不存在", Toast.LENGTH_SHORT).show();
                        break;
                    case 407:
                        Toast.makeText(RegisteActivity.this, "缺少数据:请求提交的数据缺少必要的数据", Toast.LENGTH_SHORT).show();
                        break;
                    case 408:
                        Toast.makeText(RegisteActivity.this, "无效的参数:无效的请求参数", Toast.LENGTH_SHORT).show();
                        break;
                    case 418:
                        Toast.makeText(RegisteActivity.this, "内部接口调用失败:内部接口调用失败", Toast.LENGTH_SHORT).show();
                        break;
                    case 450:
                        Toast.makeText(RegisteActivity.this, "权限不足:无权执行该操作", Toast.LENGTH_SHORT).show();
                        break;
                    case 454:
                        Toast.makeText(RegisteActivity.this, "数据格式错误:请求传递的数据格式错误，服务器无法转换为JSON格式的数据", Toast.LENGTH_SHORT).show();
                        break;
                    case 455:
                        Toast.makeText(RegisteActivity.this, "签名无效:签名检验", Toast.LENGTH_SHORT).show();
                        break;
                    case 456:
                        Toast.makeText(RegisteActivity.this, "手机号码为空:提交的手机号码或者区号为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 457:
                        Toast.makeText(RegisteActivity.this, "手机号码格式错误:提交的手机号格式不正确（包括手机的区号）", Toast.LENGTH_SHORT).show();
                        break;
                    case 458:
                        Toast.makeText(RegisteActivity.this, "手机号码在黑名单中:手机号码在发送黑名单中", Toast.LENGTH_SHORT).show();
                        break;
                    case 459:
                        Toast.makeText(RegisteActivity.this, "无appKey的控制数据:", Toast.LENGTH_SHORT).show();
                        break;
                    case 460:
                        Toast.makeText(RegisteActivity.this, "无权限发送短信:没有打开客户端发送短信的开关", Toast.LENGTH_SHORT).show();
                        break;
                    case 461:
                        Toast.makeText(RegisteActivity.this, "不支持该地区发送短信:没有开通给当前地区发送短信的功能", Toast.LENGTH_SHORT).show();
                        break;
                    case 462:
                        Toast.makeText(RegisteActivity.this, "每分钟发送次数超限:每分钟发送短信的数量超过限制", Toast.LENGTH_SHORT).show();
                        break;
                    case 463:
                        Toast.makeText(RegisteActivity.this, "手机号码每天发送次数超限:手机号码在当前APP内每天发送短信的次数超出限制", Toast.LENGTH_SHORT).show();
                        break;
                    case 464:
                        Toast.makeText(RegisteActivity.this, "每台手机每天发送次数超限:每台手机每天发送短信的次数超限", Toast.LENGTH_SHORT).show();
                        break;
                    case 465:
                        Toast.makeText(RegisteActivity.this, "号码在App中每天发送短信的次数超限:手机号码在APP中每天发送短信的数量超限", Toast.LENGTH_SHORT).show();
                        break;
                    case 466:
                        Toast.makeText(RegisteActivity.this, " 校验的验证码为空:提交的校验验证码为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 467:
                        Toast.makeText(RegisteActivity.this, "校验验证码请求频繁:5分钟内校验错误超过3次，验证码失效", Toast.LENGTH_SHORT).show();
                        break;
                    case 468:
                        Toast.makeText(RegisteActivity.this, "需要校验的验证码错误:用户提交校验的验证码错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 469:
                        Toast.makeText(RegisteActivity.this, " 未开启web发送短信:没有打开通过网页端发送短信的开关", Toast.LENGTH_SHORT).show();
                        break;
                    case 470:
                        Toast.makeText(RegisteActivity.this, "账户余额不足:账户的短信余额不足", Toast.LENGTH_SHORT).show();
                        break;
                    case 471:
                        Toast.makeText(RegisteActivity.this, "请求IP错误:通过服务端发送或验证短信的IP错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 472:
                        Toast.makeText(RegisteActivity.this, "客户端请求发送短信验证过于频繁:客户端请求发送短信验证过于频繁", Toast.LENGTH_SHORT).show();
                        break;
                    case 473:
                        Toast.makeText(RegisteActivity.this, "服务端根据duid获取平台错误:服务端根据duid获取平台错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 474:
                        Toast.makeText(RegisteActivity.this, " 没有打开服务端验证开关:没有打开服务端验证开关", Toast.LENGTH_SHORT).show();
                        break;
                    case 475:
                        Toast.makeText(RegisteActivity.this, "appKey的应用信息不存在:appKey的应用信息不存在", Toast.LENGTH_SHORT).show();
                        break;
                    case 476:
                        Toast.makeText(RegisteActivity.this, "当前appkey发送短信的数量超过限额:如果当前appkey对应的包名没有通过审核，每天次appkey+包名最多可以发送20条短信", Toast.LENGTH_SHORT).show();
                        break;
                    case 477:
                        Toast.makeText(RegisteActivity.this, "当前手机号发送短信的数量超过限额:当前手机号码在SMSSDK平台内每天最多可发送短信10条，包括客户端发送和WebApi发送", Toast.LENGTH_SHORT).show();
                        break;
                    case 478:
                        Toast.makeText(RegisteActivity.this, "当前手机号在当前应用内发送超过限额:当前手机号码在当前应用下12小时内最多可发送文本验证码5条", Toast.LENGTH_SHORT).show();
                        break;
                    case 500:
                        Toast.makeText(RegisteActivity.this, "服务器内部错误:服务端程序报错", Toast.LENGTH_SHORT).show();
                        break;
                }
            } catch (Exception e) {
                SMSLog.getInstance().w(e);
            }

        }
        return false;
    }
}
