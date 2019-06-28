package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.UserDetilBean;

import java.util.List;

/**
 * Created by wyw on 2016/12/28.
 */
public interface IUserDetilSearchActivity extends IMVPList{

   void searchComplete(List<UserDetilBean.Data> datas);

   void loadMore(List<UserDetilBean.Data> datas);
}
