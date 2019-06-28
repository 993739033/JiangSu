package com.wyw.jiangsu.view.springview;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DensityUtil {
    private static DensityUtil densityUtil = null;
    private static final String TAG = DensityUtil.class.getSimpleName();

    // 当前屏幕的densityDpi
    private static float dmDensityDpi = 0.0f;

    private static DisplayMetrics dm;

    private static float scale = 0.0f;

    private DensityUtil(Context context) {
        // 获取当前屏幕
        dm = new DisplayMetrics();
        // 返回当前资源对象的DispatchMetrics信息??
        //dm = context.getApplicationContext().getResources().getDisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        } else {
            Logger.getLogger(TAG, "your context must is instance of Activity!");//TODO is it true?
            return;
        }
        // 设置DensityDpi
        setDmDensityDpi(dm.densityDpi);
        // 密度因子
        //scale = getDmDensityDpi() / 160;// 等于 scale=dm.density;
        scale = dm.density;
    }

    public static DensityUtil getInstance(Context context) {
        if (densityUtil == null) {
            densityUtil = new DensityUtil(context);
        }
        return densityUtil;
    }

    /**
     * 屏幕分辨率宽
     *
     * @return
     */
    public int getWindowWidth() {
        if (dm != null) {
            return dm.widthPixels;
        }
        return 0;
    }

    /**
     * 屏幕分辨率高
     *
     * @return
     */
    public int getWindowHeight() {
        if (dm != null) {
            return dm.heightPixels;
        }
        return 0;
    }

    /**
     * 屏幕的ppi
     *
     * @return
     */
    public float getDmDensityDpi() {
        return dmDensityDpi;
    }

    public void setDmDensityDpi(float dmDensityDpi) {
        DensityUtil.dmDensityDpi = dmDensityDpi;
    }

    public int dip2px(float dipValue) {
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dip转像素
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPixels(Context context, int dip) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        float valueDips = dip;
        int valuePixels = (int) (valueDips * SCALE + 0.5f);
        return valuePixels;
    }

    /**
     * 像素转dip
     *
     * @param context
     * @param Pixels
     * @return
     */
    public static float pixelsToDip(Context context, int Pixels) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        float dips = Pixels / SCALE;
        return dips;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
