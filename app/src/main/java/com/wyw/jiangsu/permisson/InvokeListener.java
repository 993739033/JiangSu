package com.wyw.jiangsu.permisson;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by wyw on 2017/1/10.
 * 权限的回调
 */

public interface InvokeListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    PermissionManager.TPermissionType invoke(InvokeParam param);
}
