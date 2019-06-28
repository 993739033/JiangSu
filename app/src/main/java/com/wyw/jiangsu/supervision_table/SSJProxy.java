package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.wyw.jiangsu.bean.DoubleRandomCenterBean;
import com.wyw.jiangsu.bean.upload.UploadDoubleRandomBean;
import com.wyw.jiangsu.utils.BitmapUtils;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.NumFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 双随机
 */
public class SSJProxy extends BaseProxy {
    private final UploadDoubleRandomBean bean;
    private List<String> paths = new ArrayList<>();
    private float dis = 0.9f;

    public SSJProxy(Context context, Canvas cachecanvas, UploadDoubleRandomBean t, Bitmap cachebitmap) {
        super(context, cachecanvas, cachebitmap);
        this.mContext = context;
        this.mCacheCanvas = cachecanvas;
        this.bean = t;
        this.cacheBitmap = cachebitmap;
    }

    @Override
    public Bitmap draw(int whichPrinter) {
        return null;
    }

    private void merge(Bitmap frontBitmap) {
        cacheBitmap = BitmapUtils.mergeBitmap(cacheBitmap, frontBitmap);
        if (cacheBitmap != null) mCacheCanvas = new Canvas(cacheBitmap);
    }

    public String draw_1() {
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_1.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        if (bean.getTopData().getFFarmName() != null)
            writeText(bean.getTopData().getFFarmName(), 10f, 8.4f, 20f, 3f, 38f);
        if (bean.getTopData().getFLegalPerson() != null)
            writeText(bean.getTopData().getFLegalPerson(), 35.4f, 8.4f, 10f, 3f, 38f);
        if (bean.getTopData().getFCityAdd() != null)
            writeText(bean.getTopData().getFCityAdd(), 8.3f, 10.3f, 20f, 3f, 38f);
        if (bean.getTopData().getFPhone() != null)
            writeText(bean.getTopData().getFPhone(), 35.4f, 10.3f, 10f, 3f, 38f);
        int sum = 0;
        //检查结果
      a:for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        if (sum > 6) break a;
                        switch (sum) {
                            case 1:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 36f, 14.4f, 2f, 2f);
                                } else {
                                    writeText("√", 36f, 14.4f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 38f, 13.8f, 6f, 3);
                                break;
                            case 2:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.7f, 17f, 2f, 2f);
                                } else {
                                    writeText("√", 35.7f, 17f + dis, 2f, 2);
                                }
                                writeText(bean4.getCheckRemark(), 38f, 16.8f, 6f, 2.2f);
                                break;
                            case 3:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.7f, 19.2f, 2f, 2f);
                                } else {
                                    writeText("√", 35.7f, 19.2f + dis, 2f, 2);
                                }
                                writeText(bean4.getCheckRemark(), 38f, 19f, 6f, 2.2f);
                                break;
                            case 4:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.7f, 21.5f, 2f, 2f);
                                } else {
                                    writeText("√", 35.7f, 21.5f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 38f, 21.3f, 6f, 2.2f);
                                break;
                            case 5:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.7f, 23.7f, 2f, 2f);
                                } else {
                                    writeText("√", 35.7f, 23.7f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 38f, 23.5f, 6f, 2.2f);
                                break;
                            case 6:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.7f, 25.9f, 2f, 2f);
                                } else {
                                    writeText("√", 35.7f, 25.9f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 38f, 25.7f, 6f, 2.2f);
                                break;
                        }
                    }
                }
            }
        }


        File f1 = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_1.jpg");
        if (f1.exists()) f1.delete();
        String s1 = FileUtil.getInstance().save2Local2(cacheBitmap, f1.getAbsolutePath());
        return s1;
    }

    public String draw_2() {
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_2.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        int sum = 6;
        //检查结果
        a:for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        if (sum > 14) break a;
                        switch (sum) {
                            case 7:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 6.8f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 6.8f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 6.5f, 6f, 2.8f);
                                break;
                            case 8:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 9.3f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 9.3f + dis, 2f, 1.6f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 9.4f, 6f, 1.6f);
                                break;
                            case 9:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 11.7f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 11.7f + dis, 2f, 2);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 11.3f, 6f, 2.6f);
                                break;
                            case 10:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 14.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 14.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 14.2f, 6f, 1.6f);
                                break;


                            case 11:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 16.6f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 16.6f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 16.4f, 6f, 2.6f);
                                break;
                            case 12:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 19f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 19f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 19.1f, 6f, 1.6f);
                                break;
                            case 13:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 21.6f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 21.6f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 21.2f, 6f, 2.8f);
                                break;
                            case 14:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 25.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 25.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 24.4f, 6f, 2.8f);
                                break;

                        }
                    }
                }
            }
        }


        File f = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_2.jpg");
        if (f.exists()) f.delete();
        String s = FileUtil.getInstance().save2Local2(cacheBitmap, f.getAbsolutePath());
        return s;
    }

    public String draw_3() {
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_3.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        int sum = 14;
        //检查结果
        a:for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        if (sum > 24) break a;
                        switch (sum) {
                            case 15:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 6.4f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 6.4f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 6.4f, 6f, 1.8f);
                                break;
                            case 16:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 8.9f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 8.9f + dis, 2f, 1.6f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 8.6f, 6f, 2.4f);
                                break;
                            case 17:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 11.3f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 11.2f + dis, 2f, 2);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 11.3f, 6f, 1.8f);
                                break;
                            case 18:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 13.4f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 13.4f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 13.4f, 6f, 2);
                                break;


                            case 19:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 15.5f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 15.5f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 15.4f, 6f, 2);
                                break;
                            case 20:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 18f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 18f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 17.8f, 6f, 2);
                                break;
                            case 21:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 20.6f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 20.6f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 20.4f, 6f, 2);
                                break;
                            case 22:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 23.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 23.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 22.8f, 6f, 2);
                                break;
                            case 23:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 26.4f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 26.4f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.8f, 25.6f, 6f, 2.8f);
                                break;

                        }
                    }
                }
            }
        }


        File f = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_3.jpg");
        if (f.exists()) f.delete();
        String s = FileUtil.getInstance().save2Local2(cacheBitmap, f.getAbsolutePath());
        return s;
    }

    public String draw_4() {
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_4.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        int sum = 24;
        //检查结果
        a:for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        if (sum > 31) break a;
                        switch (sum) {
                            case 25:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 6.8f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 6.8f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 6.9f, 5.8f, 1.6f);
                                break;
                            case 26:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 9.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 9.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 8.7f, 6f, 2.7f);
                                break;
                            case 27:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 11.8f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 11.8f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 11.5f, 6f, 2.6f);
                                break;
                            case 28:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 14.6f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 14.6f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 14.3f, 6f, 2.5f);
                                break;


                            case 29:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 17.3f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 17.3f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 17.1f, 6f, 2.7f);
                                break;
                            case 30:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 20.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 20.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 19.9f, 6f, 2.4f);
                                break;
                            case 31:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 23.7f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 23.7f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 22.4f, 6f, 3.4f);
                                break;
                        }
                    }
                }
            }
        }


        File f = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_4.jpg");
        if (f.exists()) f.delete();
        String s = FileUtil.getInstance().save2Local2(cacheBitmap, f.getAbsolutePath());
        return s;
    }

    public String draw_5() {
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_5.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        int sum = 31;
        //检查结果
        a:for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        if (sum > 36) break a;
                        switch (sum) {
                            case 32:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 7.4f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 7.4f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 6.8f, 5.8f, 3.4f);
                                break;
                            case 33:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 10.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 10.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 10.2f,  6f, 1.6f);
                                break;
                            case 34:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 15.6f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 15.6f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 12.2f, 6f, 3.4f);
                                break;
                            case 35:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 21.7f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 21.7f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 21.3f, 6f, 2.8f);
                                break;
                            case 36:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 24.7f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 24.7f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 24.4f, 6f, 2.7f);
                                break;
                        }
                    }
                }
            }
        }


        File f = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_5.jpg");
        if (f.exists()) f.delete();
        String s = FileUtil.getInstance().save2Local2(cacheBitmap, f.getAbsolutePath());
        return s;
    }

    public String draw_6() {
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_6.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        int sum = 36;
        //检查结果
        a:for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        if (sum > 40) break a;
                        switch (sum) {
                            case 37:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 8.2f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 8.2f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 6f, 5.8f, 4);
                                break;
                            case 38:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 14f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 14f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 12.6f,  6f, 3f);
                                break;
                            case 39:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 19.2f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 19.2f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 17.7f, 6f, 3.2f);
                                break;
                            case 40:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 23.5f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 23.5f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 23f, 6f, 2.8f);
                                break;
                        }
                    }
                }
            }
        }


        File f = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_6.jpg");
        if (f.exists()) f.delete();
        String s = FileUtil.getInstance().save2Local2(cacheBitmap, f.getAbsolutePath());
        return s;
    }

    public String draw_7() {
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_7.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        int sum = 40;
        //检查结果
        a:for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        if (sum > 46) break a;
                        switch (sum) {
                            case 41:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 6.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 6.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 5.7f, 5.8f, 2.6f);
                                break;
                            case 42:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 10.2f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 10.2f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 9f,  6f, 3.6f);
                                break;
                            case 43:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 14.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 14.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 13.9f, 6f, 2.6f);
                                break;
                            case 44:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 17.1f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 17.1f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 16.7f, 6f, 2.8f);
                                break;
                            case 45:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 20.6f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 20.6f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 19.9f, 6f, 3f);
                                break;
                            case 46:
                                if (bean4.getCheckResult().equals("是")) {
                                    writeText("√", 35.6f, 25f, 2f, 2f);
                                } else {
                                    writeText("√", 35.6f, 25f + dis, 2f, 2f);
                                }
                                writeText(bean4.getCheckRemark(), 37.9f, 23.8f, 6f, 3.4f);
                                break;
                        }
                    }
                }
            }
        }


        File f = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_7.jpg");
        if (f.exists()) f.delete();
        String s = FileUtil.getInstance().save2Local2(cacheBitmap, f.getAbsolutePath());
        return s;
    }

    public String draw_8() {
        cacheBitmap = Bitmap.createBitmap(1280, 1810, Bitmap.Config.ARGB_8888);
        File file = new File(FileUtil.getInstance().getDirPrint(), "ssj_8.jpg");
        Bitmap bt = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(bt);
        bt.recycle();
        writeText(bean.getTopData().getFFarmName(), 11f, 9.6f, 15f, 1.8f, 38f);
        writeText(bean.getCheckTime(), 11f, 11.8f, 15f, 3f, 38f);
        writeText(NumFormat.toChineseCharI(bean.getNoSum()), 13.1f, 17.1f, 20f, 3f, 38f);
        String[] times = bean.getZgTime().split("-");
        writeText(times[0], 7f, 19.2f, 3f, 3f, 38f);
        writeText(times[1], 11.8f, 19.2f, 3f, 3f, 38f);
        writeText(times[2], 15.5f, 19.2f, 3f, 3f, 38f);
        File f = new File(FileUtil.getInstance().getCacheDirPath(), "ssj_8.jpg");
        if (f.exists()) f.delete();
        String s = FileUtil.getInstance().save2Local2(cacheBitmap, f.getAbsolutePath());
        return s;
    }

    @Override
    public List<String> draws() {
        paths.add(draw_1());
        paths.add(draw_2());
        paths.add(draw_3());
        paths.add(draw_4());
        paths.add(draw_5());
        paths.add(draw_6());
        paths.add(draw_7());
        paths.add(draw_8());


        if (!cacheBitmap.isRecycled()) {
            cacheBitmap.recycle();
            System.gc();
        }
        return paths;
    }
}
