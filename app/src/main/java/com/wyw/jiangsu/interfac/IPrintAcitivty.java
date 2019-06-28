package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.dialog.DownloadDialog;

/**
 * Created by Administrator on 2017/3/16.
 */
public interface IPrintAcitivty extends IMVP{
    void Return();
    void Toath();
    void setDownDialogProgress(long bytesRead, long contentLength, boolean done);
    void onDownloadDone(int type);
}
