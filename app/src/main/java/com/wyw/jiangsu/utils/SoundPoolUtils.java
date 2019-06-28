package com.wyw.jiangsu.utils;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;


/**
 * Created by wyw on 2017/5/3.
 */

public class SoundPoolUtils {
    private static SoundPoolUtils instance;
    private Context mContext;
    private SoundPool soundPool;
    private int soundId;
    public SoundPoolUtils(Context context) {
        mContext = context;
    }

    public static SoundPoolUtils getInstance() {
        if (instance == null) {
            instance = new SoundPoolUtils(MyApplication.getContext());
        }
        return instance;
    }

    public void playSound() {
        //系统声音管理类
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        //震动管理类
        Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        //100等待时间 400震动时间
        //vibrator.vibrator(pattern,-1) 表示不重复只震动一次
        //vibrator.vibrator(pattern,1)从下标为1的开始震动
        //bibrator,bivrator(1000); 震动1s
        long[] pattern = {100,400,100,400};
        //获取系统设置模式
        switch (audioManager.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                //静音
                Log.e("SoundPoolUtils", "静音");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                //震动
                Log.e("SoundPoolUtils", "震动");
                vibrator.vibrate(pattern, -1);
                break;
            case  AudioManager.RINGER_MODE_NORMAL:
                //正常
                Log.e("SoundPoolUtils", "正常");
                //播放铃声
                if (soundPool == null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        AudioAttributes build = new AudioAttributes.Builder()
                                .setLegacyStreamType(AudioManager.STREAM_RING)
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build();
                        soundPool = new SoundPool.Builder()
                                .setMaxStreams(10)
                                .setAudioAttributes(build)
                                .build();
                    } else {
                        soundPool = new SoundPool(10, AudioManager.STREAM_RING, 100);
                    }
                }
                /**
                 *   float maxSound = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大音量
                 *   float currentSound = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//当前音量
                 *   soundPool.play(soundId,currentSound/maxSound,currentSound/maxSound,1,0,1);
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        soundId = soundPool.load(mContext, R.raw.alarm, 0);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        soundPool.play(soundId, 1, 1, 0, 0, 1);
                    }
                }).start();

                break;
            default:
                break;
        }
    }
}
