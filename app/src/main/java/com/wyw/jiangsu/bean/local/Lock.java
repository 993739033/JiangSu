package com.wyw.jiangsu.bean.local;

/**
 * Created by HUANG on 2017/7/27.
 */
public class Lock {
    private boolean isLock = false;

    public boolean isLock() {
        return isLock;
    }

    public void lock() {
        isLock = true;
    }
    public void unLock() {
        isLock = false;
    }
}
