package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by wyw on 2017/2/24.
 */

public abstract class BaseProxy implements IProxy {
    public static final float SCALE_MUTIPLE = 4;
    public static final int TS_TABLE_NAME = 22;//表名的字体大小
    public static final int TS_NORMAL = 16;//正常的字体大小
    public static final float TABLE_HEIGHT = 24;
    public static final float TABLE_WIDTH = 19;
    public Context mContext;
    public Paint paint;
    public Canvas mCacheCanvas;//画布
    public TextPaint mTextPaint;//画文本
    public Bitmap cacheBitmap;

    public float pagerHeight;//纸张的高度
    public float pagerWidth;//纸张的宽度
    public float leftTitleWidth; //抽样情况等宽度
    public float marginLeftAndRight;//表的左右边距
    public float fontHeight; //正常字体的高度
    public float oneTextWidth;//一个字体的宽度

    public BaseProxy(Context mContext, Canvas mCacheCanvas, Bitmap cacheBitmap) {
        this.mContext = mContext;
        this.mCacheCanvas = mCacheCanvas;
        this.cacheBitmap = cacheBitmap;
        init();
    }

    private void init() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        pagerHeight = 29.2f;
        pagerWidth = 21f;
        marginLeftAndRight = 1;
        leftTitleWidth = 3;
        // Paint即画笔，在绘图过程中起到了极其重要的作用，画笔主要保存了颜色，
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setTextSize(TS_NORMAL);
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(TS_NORMAL);
        oneTextWidth = mTextPaint.measureText("你");
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        fontHeight = fontMetrics.bottom - fontMetrics.top;
    }
    public void drawBitmap(Bitmap bitmap,float x,float y) {
        drawBitmap(bitmap,x,y,1f,4f);
    }

    public void drawBitmap(Bitmap bitmap,float x,float y, float height,float width) {
        float scale ,widthScale =1f,heightScale =1f;
        //5 9
        if (unJsPxValue(bitmap.getHeight()) > height) {
            heightScale = height / unJsPxValue(bitmap.getHeight());
        }
        if (unJsPxValue(bitmap.getWidth())>width ) {
            widthScale = width / unJsPxValue(bitmap.getHeight());
        }
        scale = widthScale > heightScale ? heightScale : widthScale;
        drawBitmap(bitmap,x,y,scale);
    }
    /**
     * if (unJsPxValue(bitmap.getWidth())>2 || unJsPxValue(bitmap.getHeight())>2) {
     * if (bitmap.getWidth() > bitmap.getHeight()) {
     * scale = 2f / unJsPxValue(bitmap.getWidth());
     * } else {
     * scale = 2f / unJsPxValue(bitmap.getHeight());
     * }
     * }
     * drawBitmap(bitmap,8f,10.4f,scale);
     */
    public void drawBitmap(Bitmap bitmap, float startX, float startY, float scale) {
        Matrix matrix = new Matrix();
        matrix.preTranslate(JsPxValue(startX), JsPxValue(startY));
        matrix.preScale(scale, scale);
//        matrix.preTranslate(JsPxValue(startX), JsPxValue(startY));
        mCacheCanvas.drawBitmap(bitmap, matrix, paint);
        bitmap.recycle();
    }


    /**
     * @param value    文本内容
     * @param x        文本的起始点 x
     * @param y        文本的起始点 y
     * @param width    文本的所占宽度
     * @param height   文本所占的高度
     * @param textSize 文字大小
     * @param isBold   是否加粗
     */
    public void writeText(String value, float x, float y, float width, float height, float textSize, boolean isBold) {
        if (TextUtils.isEmpty(value)) return;
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(textSize);
        textPaint.setFakeBoldText(isBold);
        int w = (int) JsPxValue(width);
        StaticLayout layout = new StaticLayout(value, textPaint, w, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        float h = JsPxValue(height);
        while (true) {
            if (layout.getHeight() <= h) {
                break;
            } else {
                textPaint.setTextSize(textPaint.getTextSize() - 1);
                layout = new StaticLayout(value, textPaint, w,
                        Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            }
        }
        mCacheCanvas.translate(JsPxValue(x), JsPxValue(y));
        layout.draw(mCacheCanvas);
        mCacheCanvas.translate(-JsPxValue(x), -JsPxValue(y));
    }

    public void writeText(String value, float x, float y, float width, float height) {
        writeText(value, x, y, width, height, 23F, false);
    }
    public void writeText(String value, float x, float y, float width, float height,float textSize) {
        writeText(value, x, y, width, height, textSize, false);
    }

    /**
     * 以磅为单位 像素来计算行高 行高(cm)=行高像素数/(96/2.54)
     * 将 cm  转换成像素
     *
     * @param cm
     * @return
     */
    public float JsPxValue(double cm) {
        double rv = cm * (96 / 2.54);
        return Float.parseFloat(Double.toString(rv));
    }

    public float unJsPxValue(float px) {
        return Float.parseFloat(Float.toString(px / (96 / 2.54f)));
    }

    /**
     * 一个大小为textsize的文字的高度
     *
     * @return
     */
    public float getTextHeight(int textSize) {
        TextPaint mTextPaint = new TextPaint();
        mTextPaint.setTextSize(textSize);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        return fontMetrics.bottom - fontMetrics.top;
    }
}
