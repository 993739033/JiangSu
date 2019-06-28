package com.wyw.jiangsu.permisson;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by wyw on 2017/1/10.
 *
 * @takephoto
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PermissionManager {
    public static final int PERMISSION_REQUEST = 5555;
    public static final int PERMISSION_REQUEST_TAKE_PHOTO = 5556;

    public enum TPermission {
        STORAGE_READ(Manifest.permission.READ_EXTERNAL_STORAGE),
        STORAGE_WRITE(Manifest.permission.WRITE_EXTERNAL_STORAGE),
        CAMERA(Manifest.permission.CAMERA),
        LOCATION(Manifest.permission.LOCATION_HARDWARE);
        String stringValue;

        TPermission(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }
    }

    public enum TPermissionType {
        GRANTED("已授权"), DENIED("未授权"), WAIT("等待授权"),
        NOT_NEED("无需权限"), ONLY_CAMERA_DENIED("没有拍照权限"),
        ONLY_READ_STORAGE_DENIED("没有读sd卡权限"),
        ONLY_WRITE_STORAGE_DENIED("没有写sd卡权限"),
        ONLY_LOCATION_DENIED("没有定位权限");
        String stringValue;

        TPermissionType(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }
    }

    private final static String[] methods = {
            "applyAllPermission",
            "applyLocationPermission",
            "applyStoragePermission",
            "applyCameraPermission",
    };

    /**
     * 检测当前应用是否被授予权限
     */
    public static TPermissionType checkPermission(TContextWrap contextWrap, @NonNull Method method) {
        String methodName = method.getName();
        //----------------------------可注销------------------------
        boolean contain = false;
        for (int i = 0, j = methods.length; i < j; i++) {
            if (TextUtils.equals(methodName, methods[i])) {
                contain = true;
            }
        }
        //没有调用这些方法 不需要申请权限
        if (!contain) return TPermissionType.NOT_NEED;
        //----------------------------------------------------------
        boolean cameraGranted = true, locationGranted = true;
        boolean writeStorageGranted = ContextCompat.checkSelfPermission(contextWrap.getActivity(), TPermission.STORAGE_WRITE.stringValue) == PackageManager.PERMISSION_GRANTED;
        boolean readStorageGranted = ContextCompat.checkSelfPermission(contextWrap.getActivity(), TPermission.STORAGE_READ.stringValue) == PackageManager.PERMISSION_GRANTED;
        if (TextUtils.equals(methodName, methods[0]) ||
                TextUtils.equals(methodName, methods[1]))
            locationGranted = ContextCompat.checkSelfPermission(contextWrap.getActivity(), TPermission.LOCATION.stringValue) == PackageManager.PERMISSION_GRANTED;
        if (TextUtils.equals(methodName, methods[0]) ||
                TextUtils.equals(methodName, methods[3])) {
            cameraGranted = ContextCompat.checkSelfPermission(contextWrap.getActivity(), TPermission.CAMERA.stringValue) == PackageManager.PERMISSION_GRANTED;
        }
        boolean granted = cameraGranted & writeStorageGranted & readStorageGranted & locationGranted;
        if (!granted) {
            ArrayList<String> permissions = new ArrayList<>();
            if (!cameraGranted) permissions.add(TPermission.CAMERA.stringValue);
            if (!readStorageGranted) permissions.add(TPermission.STORAGE_READ.stringValue);
            if (!writeStorageGranted) permissions.add(TPermission.STORAGE_WRITE.stringValue);
            if (!locationGranted) permissions.add(TPermission.LOCATION.stringValue);
            requestPermission(contextWrap, permissions.toArray(new String[permissions.size()]));
        }
        return granted ? TPermissionType.GRANTED : TPermissionType.WAIT;
    }

    public static void requestPermission(@NonNull TContextWrap contextWrap, @NonNull String[] permissions) {
        if (contextWrap.getFragment() != null) {
            contextWrap.getFragment().requestPermissions(permissions, PERMISSION_REQUEST);
        } else {
            ActivityCompat.requestPermissions(contextWrap.getActivity(), permissions, PERMISSION_REQUEST);
        }
    }

    public static PermissionManager.TPermissionType onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_TAKE_PHOTO) {
            boolean cameraGranted = true, readStorageGranted = true, writeStorageGranted = true, locationStorageGranted = true;
            for (int i = 0, j = permissions.length; i < j; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (TextUtils.equals(TPermission.STORAGE_READ.stringValue, permissions[i])) {
                        readStorageGranted = false;
                    } else if (TextUtils.equals(TPermission.CAMERA.stringValue, permissions[i])) {
                        cameraGranted = false;
                    } else if (TextUtils.equals(TPermission.STORAGE_WRITE.stringValue, permissions[i])) {
                        writeStorageGranted = false;
                    } else if (TextUtils.equals(TPermission.LOCATION.stringValue, permissions[i])) {
                        locationStorageGranted = false;
                    }
                }
            }
            if (cameraGranted && readStorageGranted && writeStorageGranted && locationStorageGranted)
                return TPermissionType.GRANTED;
            if (!cameraGranted && readStorageGranted && writeStorageGranted && locationStorageGranted)
                return TPermissionType.ONLY_CAMERA_DENIED;
            if (cameraGranted && !readStorageGranted && writeStorageGranted && locationStorageGranted)
                return TPermissionType.ONLY_READ_STORAGE_DENIED;
            if (cameraGranted && readStorageGranted && !writeStorageGranted && locationStorageGranted)
                return TPermissionType.ONLY_WRITE_STORAGE_DENIED;
            if (cameraGranted && readStorageGranted && writeStorageGranted && !locationStorageGranted)
                return TPermissionType.ONLY_LOCATION_DENIED;
        }
        return TPermissionType.WAIT;
    }

    public static void handlePermissionsResult(Activity activity,  PermissionManager.TPermissionType type, InvokeParam invokeParam, Permission.TakeResultListener listener){
        String tip=null;
        switch (type){
            case DENIED:
                listener.takeFail(null,tip="没有使用相机和SD卡的权限，请在权限管理中开启");
                break;
            case ONLY_CAMERA_DENIED:
                listener.takeFail(null,"没有使用相机的权限，请在权限管理中开启");
                break;
            case ONLY_READ_STORAGE_DENIED:
                listener.takeFail(null,tip="没有读SD卡的权限，请在权限管理中开启");
                break;
            case ONLY_WRITE_STORAGE_DENIED:
                listener.takeFail(null,tip="没有使用SD卡的权限，请在权限管理中开启");
                break;
            case ONLY_LOCATION_DENIED:
                listener.takeFail(null,tip="没有定位权限，请在权限管理中开启");
                break;
            case GRANTED:
                try {
                    invokeParam.getMethod().invoke(invokeParam.getProxy(),invokeParam.getArgs());
                    listener.takeSuccess(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.takeFail(null,tip="没有使用相机和SD卡的权限，请在权限管理中开启");
                }
                break;
            default:
                break;
        }
        if(tip!=null) Toast.makeText(activity,tip,Toast.LENGTH_LONG).show();

    }
}
