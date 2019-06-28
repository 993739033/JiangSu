package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Log;

import com.wyw.jiangsu.bean.upload.UploadQuarantineProcessNotBeanDetil;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.TimePresenter;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class Jianyitonngzhi extends BaseProxy  {
    private UploadQuarantineProcessNotBeanDetil bean;
     String mTime;

    public Jianyitonngzhi(Context mContext, Canvas mCacheCanvas, UploadQuarantineProcessNotBeanDetil bean, Bitmap cacheBitmap,String time) {
        super(mContext, mCacheCanvas, cacheBitmap);
        this.mContext = mContext;
        this.mCacheCanvas = mCacheCanvas;
        this.bean = bean;
        this.cacheBitmap = cacheBitmap;
        this.mTime = time;
    }


    public Bitmap draw(int whichPrinter) {
// 检疫编号
       writeText(bean.getNNumber(), 23.0F, 7.3F, 10.0F, 4.0F);
        //被处理单位
        writeText(bean.getNDanWei(),9.00F, 8.3F, 10.0F, 4.0F);
/// 不合格动物及动物产品名称+数量和单位
        writeText(bean.getFChuliNum()+bean.getFChuliDanwei()+" "+bean.getNName(), 23.0F,12.2F,10.0F,4.0F);
/// 类型
        writeText(bean.getFType(), 9.0F,13.6F, 5.0F, 4.0F);
///// 处理依据
//        writeText(bean.getFType(), 3.5F,12.5F, 12.0F, 4.0F);
  /// 处理意见1
        writeText(bean.getNChuLi1(), 7.0F,23.1F, 12.0F, 4.0F);

        writeText(bean.getNChuLi2(), 7.0F,24.9F, 12.0F, 4.0F);

        writeText(bean.getNChuLi3(), 7.0F,26.65F, 12.0F, 4.0F);

        writeText(bean.getNChuLi4(), 7.0F,28.3F, 12.0F, 4.0F);
/// 兽医签字
        writeText(bean.getNVeterinary(), 14.0F,33.0F, 5.0F, 4.0F);
/// 当事人签字
        writeText(bean.getNParties(), 11.0F,34.6F, 5.0F, 4.0F);
/// 动物卫生监督所电话
        writeText(bean.getNDsrPhone(), 16.4F,39.30F, 5.0F, 4.0F);
 /// 联系电话
        writeText(bean.getNDwPhone(), 14.4F,41.0F, 5.0F, 4.0F);


        String[] nian = bean.getFScTime().trim().split(" ");
        String[] split = nian[0].split("-|/");
        String Year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];
        //writeText(changeLock.Change(Year), 12.20F, 16.57F, 1.6F, 0.6F);// 年
        writeText(Year, 21.2F, 31.4F,3.0F, 1.5F);
//        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 14.65F, 16.57F, 1.6F, 0.6F);// 月
        writeText(Month, 24.0F, 31.4F, 3.0F, 1.5F);
//        writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 16.2F, 16.57F, 1.58F, 0.6F);// 日
        writeText(Date, 26.0F, 31.4F, 3.0F, 1.5F);

        return cacheBitmap;
    }

    @Override
    public List<String> draws() {
        return null;
    }

}
