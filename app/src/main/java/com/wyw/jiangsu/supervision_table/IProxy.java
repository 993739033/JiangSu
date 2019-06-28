package com.wyw.jiangsu.supervision_table;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by wyw on 2017/2/24.
 * 代理画笔的基类
 */

public interface IProxy {
    Bitmap draw( int whichPrinter);
    List<String> draws();
}
