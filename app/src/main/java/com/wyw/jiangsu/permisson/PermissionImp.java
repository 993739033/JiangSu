package com.wyw.jiangsu.permisson;

/**
 * Created by wyw on 2017/1/10.
 */

public class PermissionImp implements Permission {
    /**
     * 将来用来拍照,定位,存储扩展
     */
    PermissionManager.TPermissionType type;

    @Override
    public void permissionNotify(PermissionManager.TPermissionType type) {
        this.type = type;
    }

    @Override
    public void applyAllPermission() {

    }

    @Override
    public void applyLocationPermission() {

    }

    @Override
    public void applyStoragePermission() {

    }

    @Override
    public void applyCameraPermission() {

    }
}
