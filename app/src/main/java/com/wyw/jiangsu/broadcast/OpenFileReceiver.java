package com.wyw.jiangsu.broadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wyw.jiangsu.utils.OtherUtil;

import java.io.File;

/**
 * Created by wyw on 2017/5/19.
 */

public class OpenFileReceiver extends BroadcastReceiver {
    public static final String OPEN_FILE_ACTION = "com.wyw.xizangphone.receiver.open";
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            File file = new File(intent.getStringExtra("dir"), intent.getStringExtra("fileName"));
            if (file.exists()) OtherUtil.openFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
