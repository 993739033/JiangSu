package com.wyw.jiangsu.interfac;

import android.graphics.Bitmap;

import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;

import java.util.List;

/**
 * Created by wyw on 2016/12/23.
 */
public interface IHarmlessSupervisionVeterinariansConfirmActivity extends IMVP{
    void getPageDetil(HarmlessListDetilBean.DataBean dataBean,boolean ifNewAnim);
    void uploadSuccessful();
    void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBeen);

    void uploadsuccess();

    void loadPicture(Bitmap bitmap);

}
