package com.wyw.jiangsu;

/**
 * Created by wyw on 2017/9/1.
 */

public interface TakePictureListener {
    String ACTIVITY_RECYCLE = "activity_recycler";
    /**
     * 拍照返回的调用
     * @param requestCode
     * @param resultCode
     */
     void onActivityResult(int requestCode, int resultCode);
}
