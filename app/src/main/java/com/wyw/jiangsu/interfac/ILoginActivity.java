package com.wyw.jiangsu.interfac;

/**
 * Created by wyw on 2016/10/31.
 */

public interface ILoginActivity extends IMVP {

    void recordSuccessful();
    void loginSuccessful();
    void loginSuccessful(int uid,String name,String phone);
}

