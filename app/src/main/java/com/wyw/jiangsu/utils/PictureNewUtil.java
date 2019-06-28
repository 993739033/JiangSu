package com.wyw.jiangsu.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by wyw on 2017/9/1.
 */

public class PictureNewUtil {
    public static final String SUFFIX = ".jpg";
    public void bindTakePhotoAndShow(Activity activity, ImageView img, final int requestCode, final String fileName, final File dir, final View.OnClickListener listener) {
        bindTakePhotoAndShow(activity, img, requestCode, fileName, dir, listener);
    }

    public void bindTakePhotoAndShow(Activity activity, final ImageView img, final String fileName, final File dir, final String url, final View.OnClickListener listener) {

    }

    public static void zoomOut() {

    }

    public static String takePicture(Activity activity,final String fileName, final File dir, final int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    requestCode);
            return null;
        } else {
            Intent intentP = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(dir, fileName + SUFFIX);
            Uri contentUri = null;
            if (Build.VERSION.SDK_INT >= 23) {
                contentUri = FileProvider.getUriForFile(activity, "com.wyw.jiangsu.fileprovider", file);
                intentP.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                contentUri = Uri.fromFile(file);
            }
            intentP.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//            intentP.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

//          intentP.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(activity,"com.jph.takephoto.fileprovider",getPictureWuhaihua(requestCode)));
            activity.startActivityForResult(intentP, requestCode);
            return file.getAbsolutePath();
        }
    }
}
