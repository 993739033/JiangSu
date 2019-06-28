package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;

import java.util.List;

/**
 * Created by mnkj004 on 2017/8/2.
 */

public interface ICommonSupervisionListActivity extends IMVP{

   /*
    void setDownDialogProgress(long bytesRead, long contentLength, boolean done);
    void showApkDialog() ;
    void onDownloadDone(int type) ;

    void showDbDialog(String version);
    void recordSuccessful();
    */
//   void refreshComplete(List<HomeBean.DataList> dataLists);
   void showToast(String msg);
   void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists);
   void onActivityResult(RefreshBus bean);


}
