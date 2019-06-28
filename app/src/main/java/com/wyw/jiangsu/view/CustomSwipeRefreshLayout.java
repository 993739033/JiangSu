package com.wyw.jiangsu.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by wyw on 2016/11/2.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    public CustomSwipeRefreshLayout(Context context) {
        this(context,null);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }
}
