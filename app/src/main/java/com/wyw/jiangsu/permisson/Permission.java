package com.wyw.jiangsu.permisson;


/**
 * Created by wyw on 2017/1/10.
 */

public interface Permission {
    void permissionNotify(PermissionManager.TPermissionType type);

    void applyAllPermission();

    void applyLocationPermission();

    void applyStoragePermission();

    void applyCameraPermission();

    interface TakeResultListener {
        void takeSuccess(TResult result);

        void takeFail(TResult result,String msg);

        void takeCancel();
    }
}
