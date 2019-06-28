package com.wyw.jiangsu.interfac;

/**
 * Created by wyw on 2016/12/20.
 */
public interface IMainActivity extends IMVP {
    void loadFail(String msg);

    void addTask(int type);

    void loadSuccess();

    void recordSuccessful();


}
