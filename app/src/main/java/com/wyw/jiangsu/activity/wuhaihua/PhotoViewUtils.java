package com.wyw.jiangsu.activity.wuhaihua;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.view.photoview.Info;
import com.wyw.jiangsu.view.photoview.PhotoView;

/**
 * Created by wyw on 2016/12/26.
 * 使用说明  首先构造函数 其次 initPhotoView 初始化每个photoview 最后onbackpress
 */

public class PhotoViewUtils {
    private AlphaAnimation in;
    private AlphaAnimation out;

    private View mParent;
    private View mBg;
    private Info mInfo;
    private PhotoView parentPhotoView;//frame里面的photoview

    public PhotoViewUtils(View mParent, View mBg, PhotoView parentPhotoView) {
        this.mParent = mParent;
        this.mBg = mBg;
        this.parentPhotoView = parentPhotoView;
        in = new AlphaAnimation(0, 1);
        out = new AlphaAnimation(1, 0);
        in.setDuration(300);
        out.setDuration(300);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //设置背景的photoview
        parentPhotoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        parentPhotoView.enable();
        parentPhotoView.setOnClickListener(v -> zoomOut());
    }

    public void initPhotoView(PhotoView mPhotoView) {
        mPhotoView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // 把PhotoView当普通的控件把触摸功能关掉
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mPhotoView.disenable();
        mPhotoView.setOnClickListener(v -> zoomIn((PhotoView) v));
    }

    public void initPhotoView(PhotoView mPhotoView, OnPhotoViewClickListener listener) {
        mPhotoView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // 把PhotoView当普通的控件把触摸功能关掉
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mPhotoView.disenable();
        mPhotoView.setOnClickListener(v -> zoomIn((PhotoView) v, listener));
    }

    /**
     * 放大
     */
    public void zoomIn(PhotoView mPhotoView, OnPhotoViewClickListener listener) {
        if (mPhotoView.getDrawable() == null) {
            if (listener != null) {
                listener.onNonePic();
            } else {
                Toast.makeText(mPhotoView.getContext(), "没有图片", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        mInfo = mPhotoView.getInfo();
        parentPhotoView.setImageDrawable(mPhotoView.getDrawable());
        mBg.startAnimation(in);
        mBg.setVisibility(View.VISIBLE);
        mParent.setVisibility(View.VISIBLE);
        parentPhotoView.animaFrom(mInfo);

    }

    /**
     * 放大
     */
    public void zoomIn(PhotoView mPhotoView) {
        if (mPhotoView.getDrawable() == null) {
            Toast.makeText(mPhotoView.getContext(), "没有图片", Toast.LENGTH_SHORT).show();
            return;
        }
        mInfo = mPhotoView.getInfo();
        parentPhotoView.setImageDrawable(mPhotoView.getDrawable());
        mBg.startAnimation(in);
        mBg.setVisibility(View.VISIBLE);
        mParent.setVisibility(View.VISIBLE);
        parentPhotoView.animaFrom(mInfo);

    }

    /**
     * 缩小
     */
    public void zoomOut() {
//        mBg.startAnimation(out);
//        parentPhotoView.animaTo(mInfo, new Runnable() {
//            @Override
//            public void run() {
//                mParent.setVisibility(View.GONE);
//            }
//        });
        mBg.startAnimation(out);
        parentPhotoView.animaTo(mInfo, new Runnable() {
            @Override
            public void run() {
                mParent.setVisibility(View.GONE);
//                mBg.setVisibility(View.GONE);
            }
        });
        if (mParent.getVisibility() != View.GONE && parentPhotoView.getDrawable() == null) {
            mParent.setVisibility(View.GONE);
        }
    }

    public boolean onBackPress() {
        if (mParent.getVisibility() == View.VISIBLE) {
            zoomOut();
            return false;
        }
        return true;

    }

    /**
     * 初始化photoView
     *
     * @param url
     * @param mPhotoView
     */
    public void loadPic(String url, PhotoView mPhotoView) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(mParent.getContext()).load(R.drawable.no_pict_empty).into(mPhotoView);
        } else {
            Glide.with(mParent.getContext()).load(url).into(mPhotoView);
        }
    }

    /**
     * 加载默认图片 提示请拍照
     *
     * @param mPhotoView
     */
    public void loadPic(PhotoView mPhotoView) {

    }

    public interface OnPhotoViewClickListener {
        void onNonePic();
    }
}
