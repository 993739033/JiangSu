package com.wyw.jiangsu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by CZY on 2018/1/23.
 */
public class TScrollLayout extends ScrollView {
    public TScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void invalidate() {
        Log.e("czy","invalidate");
        super.invalidate();
    }

    @Override
    public void requestLayout() {
        Log.e("czy","requestLayout");
        super.requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e("czy","onSizeChanged:w=="+w+",h=="+h+",oldw=="+oldw+",oldh=="+oldh);
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
