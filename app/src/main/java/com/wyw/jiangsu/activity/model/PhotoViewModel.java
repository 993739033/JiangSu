package com.wyw.jiangsu.activity.model;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by wyw on 2017/5/23.
 */

public class PhotoViewModel {
    public static final String SUFFIX = ".jpg";
    private WeakReference<Activity> mActivity;
    /** 定义相片的最大尺寸 **/
    private static final int IMAGE_DEFAULT_WIDTH = 768;
    private static final int IMAGE_DEFAULT_HEIGHT = 1024;
    public PhotoViewModel(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public void bindData(final ImageView img, final int requestCode, final String fileName, final File dir, final OnClickListener listener) {
        bindData(img,requestCode,fileName,dir,listener,null);
    }

    public void bindData(final ImageView img, final int requestCode, final String fileName, final File dir, final OnClickListener listener, final String url) {
        if (!TextUtils.isEmpty(url)){
            Picasso.with(mActivity.get()).load(url).into(img);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img.getDrawable() == null) {

                    if (listener != null)
                        listener.onModelTakePhoto(takePicture(mActivity.get(), requestCode, fileName, dir));
                } else {
                    String path;
                    File file = new File(dir, fileName);
                    if (file.exists()) path = file.getAbsolutePath();
                    else path = url;
                    if (Build.VERSION.SDK_INT >= 21) {
                        img.setTransitionName("photoView");
                        Intent intent = new Intent(mActivity.get(), ActivityTransitionToActivity.class);
                        intent.putExtra("path", path);
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(mActivity.get(), img, "photoView");
                        mActivity.get().startActivity(intent, options.toBundle());
                    } else {
                        Intent intent = new Intent(mActivity.get(), ActivityTransitionToActivity.class);
                        intent.putExtra("path", path);
                        mActivity.get().startActivity(intent);
                    }
                }
            }
        });
    }

    public static String takePicture(Activity activity, int requestCode, String name, File dir) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    requestCode);
            return null;
        } else {
            Intent intentP = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(dir, name);
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

    public static String getCommonSupervisionDirPath() {
        return getCommonSupervisionDir().getAbsolutePath();
    }
    public static String getUploadDirPath() {
        return getUploadDir().getAbsolutePath();
    }

    public  static File getCommonSupervisionDir() {
        Log.e("PhotoViewModel", MyApplication.getContext().getPackageName());
        File dir ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dir = MyApplication.getContext().getExternalFilesDir("commonSupervision");
        } else {
            dir = new File(Environment.getExternalStorageDirectory(),"Android"+File.separator+
            "data"+File.separator+MyApplication.getContext().getPackageName()+File.separator+"commonSupervision");
        }
        if (!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }

    public  static File getUploadDir() {
        Log.e("PhotoViewModel", MyApplication.getContext().getPackageName());
        File dir ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dir = MyApplication.getContext().getExternalFilesDir("upload");
        } else {
            dir = new File(Environment.getExternalStorageDirectory(),"Android"+File.separator+
            "data"+File.separator+MyApplication.getContext().getPackageName()+File.separator+"files"+File.separator+"upload");
        }
        if (!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }

    public static void saveBitmap(String path, Bitmap bm) {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Bitmap getBitmap(String imgPath,int width,int height) {
        // Get bitmap through image path
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = false;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        if (width < 0 || height < 0) {
            newOpts.inSampleSize = 4;
        } else {
            newOpts.inSampleSize = getZoomScale(imgPath,width,height);
        }

        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath, newOpts);
    }

    private static int getZoomScale(String imagePath) {
        return getZoomScale(imagePath, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT);
    }
    /**
     * 图片缩放处理
     * @return 缩放的倍数
     */
    private static int getZoomScale(String imagePath,int width,int height) {
        int scale = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        while (options.outWidth / scale >= width
                || options.outHeight / scale >= height) {
            scale *= 2;
        }
        return scale;
    }
    public static byte[] compressBmpToFileWithSave(String picturePath) {
        return compressBmpToFileWithSave(picturePath, -1, -1);
    }

    public static ByteArrayOutputStream compressBmp(String picturePath, int width, int height) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bmp = getBitmap(picturePath,width,height);
        int options = 100;
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 500) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        return baos;
    }

    /**
     *  图片质量压缩 并保存到本地 通过计算来压缩
     * @param picturePath
     * @param width
     * @param height
     * @return
     */
    public static byte[] compressBmpToFileWithSave(String picturePath, int width, int height) {
        ByteArrayOutputStream baos = compressBmp(picturePath, width, height);
        //写出
        File pic = new File(picturePath);
        FileOutputStream fileOutputStream  = null;
        try {
            fileOutputStream = new FileOutputStream(pic);
            fileOutputStream.write(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

    /**
     *  图片质量压缩 不计算
     *  有性能损失
     * @param picturePath
     * @param width
     * @param height
     * @return
     */
    @Deprecated
    public static byte[] compressBmpToFile(String picturePath, int width, int height) {
        //保存图片不采用width,height压缩
        //new Thread(() -> compressBmpToFileWithSave(picturePath,-1,-1)).start();
        return compressBmp(picturePath, width, height).toByteArray();
    }
    public static Bitmap getBitmap2(String imgPath,int sampleSize) {
        // Get bitmap through image path
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = false;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        // Do not compress
        newOpts.inSampleSize = 2;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath, newOpts);
    }
    public static byte[] compressBmpToFile2(String picturePath,int sampleSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bmp = getBitmap2(picturePath,sampleSize);
        int options = 100;
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 500) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        //写出
        File pic = new File(picturePath);
        FileOutputStream fileOutputStream  = null;
        try {
            fileOutputStream = new FileOutputStream(pic);
            fileOutputStream.write(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }
    /**
     * 将文件拷贝到上传目录
     */
    public static  void copyToUpload(File file) {
        if (!file.exists()) return ;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(new File(getUploadDir(),file.getName()));
            byte[] buf = new byte[1024*4];
            int length;
            while((length = fis.read(buf))!=-1){
                fos.write(buf,0,length);
            }
        } catch (Exception e) {
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件夹下面的文件
     */
    public static void deleteFile(File dir) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteFile(file);
            }
        } else {
            dir.delete();
        }
    }

    public interface OnClickListener {
        void onModelTakePhoto(String path);
    }
}
