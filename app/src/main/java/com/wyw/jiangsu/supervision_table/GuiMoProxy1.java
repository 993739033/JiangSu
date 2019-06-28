package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;

import com.wyw.jiangsu.activity.model.PhotoViewModel;
import com.wyw.jiangsu.bean.local.CheckContentEntity;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.BitmapUtils;
import com.wyw.jiangsu.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GuiMoProxy1 extends BaseProxy {
    private final UploadCommonSupervisionBean bean;
    private List<String> paths = new ArrayList<>();


    private void merge(Bitmap frontBitmap) {
        cacheBitmap = BitmapUtils.mergeBitmap(cacheBitmap, frontBitmap);
        if (cacheBitmap != null) mCacheCanvas = new Canvas(cacheBitmap);
    }

    public List<String> draws() {
        //分析数据
        analysisdata();
        //附录图片绘制

        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = checkItems.size()-1; i >=0; i--) {
                if (checkItems.get(i) != null) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            paths.add(getFLPath(pf1.getAbsolutePath()));
                        }
                    }
                }
            }
        }
        //第四张
        paths.add(draw_4());
        //第三张
        paths.add(draw_3());
        //第二张
        paths.add(draw_2());
        //第一张基础信息

        paths.add(draw_1());


        //回收cacheBitmap
        if (!cacheBitmap.isRecycled()) {
            cacheBitmap.recycle();
            System.gc();
        }
        return FileUtil.getInstance().getPrintList("yz");
    }

    //附录图片下标集合
    private Map<String, String> fl1 = new HashMap<>();
    private Map<String, String> fl2 = new HashMap<>();
    private Map<String, String> fl3 = new HashMap<>();
    private Map<String, String> fl4 = new HashMap<>();
    //图片索引
    private int flindex1;
    private int flindex2;
    private int flindex3;
    private int flindex4;

    /**
     * 分析数据，附录图片数量与说明
     */
    private void analysisdata() {
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        for (int i = 0; i < checkItems.size(); i++) {
            if (i < 4) {
                if (!checkItems.get(i).getJL().equals("是")) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl1.put("" + i, "图1-" + (flindex1 + 1));
                            flindex1++;
                        }
                    }
                }

            } else if (i < 9) {
                if (!checkItems.get(i).getJL().equals("是")) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl2.put("" + i, "图2-" + (flindex2 + 1));
                            flindex2++;
                        }
                    }
                }

            } else if (i < 21) {
                if (!checkItems.get(i).getJL().equals("是")) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl3.put("" + i, "图3-" + (flindex3 + 1));
                            flindex3++;
                        }
                    }
                }

            } else if (i < 26) {
                if (!checkItems.get(i).getJL().equals("是")) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl4.put("" + i, "图4-" + (flindex4 + 1));
                            flindex4++;
                        }
                    }

                }

            }
        }
    }

    public GuiMoProxy1(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
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

    /**
     * 绘制第一张
     */
    public String draw_1() {
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"yz_img1.jpg");
        Bitmap frontBitmap1 = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(frontBitmap1);


        if (bean.getFName() != null) {
            writeText(bean.getFName(), 8.2f, 7.1f, 7.8f, 0.7f);
        }
        if (bean.getFLegalPerson() != null) {
            writeText(bean.getFLegalPerson(), 8.2f, 8.7f, 7.8f, 0.7f);
        }
        if (bean.getFCategory() != null) {
            writeText(bean.getFCategory(), 8.2f, 10.75f, 7.8f, 0.7f);
        }
        if (bean.getFCityAdd() != null) {
            writeText(bean.getFCityAdd(), 24.5f, 7.1f, 4.7f, 0.7f);
        }
        if (bean.getFPhone() != null) {
            writeText(bean.getFPhone(), 24.5f, 8.7f, 4.7f, 0.7f);
        }
        if (bean.getClsl() != null) {
            writeText(bean.getClsl(), 24.5f, 10.75f, 4.7f, 0.7f);
        }
        //第一张条目
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 0; i < 4; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item1_1:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.77f, 13.8f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.77f, 14.8f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 14.3f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 14.3f, 3.3f, 0.7f);
                            }
                            String s = fl1.get("" + i);
                            writeText(s, 27.1f, 14.3f, 4.0f, 1.0f);
                            //writeText("图-P1", 29.1f, 18.3f,  0.5f, 1.0f);
                        }
                        break;
                    case Constance.Item1_2:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.77f, 16.03f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.77f, 17.02f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 20.1f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 16.72f, 3.3f, 0.7f);
                            }
                            String s = fl1.get("" + i);
                            writeText(s, 27.1f, 16.8f, 4.0f, 1.0f);
                            //writeText("图-P2", 29.1f, 20.1f,  0.5f, 1.0f);
                        }
                        break;
                    case Constance.Item1_3:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.77f, 18.3f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.77f, 19.3f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 21.4f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 18.8f, 3.3f, 0.7f);
                            }
                            String s = fl1.get("" + i);
                            writeText(s, 27.1f, 18.8f, 4.0f, 1.0f);
                            // writeText("图-P3", 29.1f, 21.4f,  0.5f, 1.0f);
                        }
                        break;
                    case Constance.Item1_4:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.77f, 30.8f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.77f, 31.8f, 2.3f, 0.7f);
                            for (int n = 0; n < checkItems.get(i).getList().size(); n++) {
                                boolean cheek = checkItems.get(i).getList().get(n).isCheek();

                                if (cheek) {
                                    switch (n) {
                                        case 0:
                                            writeText("√", 23.72f, 20.21f, 2.3f, 0.7f);
                                            break;
                                        case 1:
                                            writeText("√", 23.72f, 23.98f, 2.3f, 0.7f);
                                            break;
                                        case 2:
                                            writeText("√", 23.72f, 27.50f, 2.3f, 0.7f);
                                            break;
                                        case 3:
                                            writeText("√", 23.72f, 32.20f, 2.3f, 0.7f);
                                            break;
                                        case 4:
                                            writeText("√", 23.72f, 35.96f, 2.3f, 0.7f);
                                            break;
                                        case 5:
                                            writeText("√", 23.72f, 38.80f, 2.3f, 0.7f);
                                            break;

                                    }
                                }

                            }
                            String s = fl1.get("" + i);
                            writeText(s, 27.1f, 31.3f, 4.0f, 1.0f);
                            // writeText("图-P4", 29.1f, 32.2f,  0.5f, 1.0f);
                        }
                        break;
                }
            }
        }

        File f1 = new File(FileUtil.getInstance().getCacheDirPath(), "yz_1.jpg");
        if (f1.exists()) f1.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, f1.getAbsolutePath());


    }

    /**
     * 绘制第二张
     */
    public String draw_2() {
        //第二张开始
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"yz_img2.jpg");
        Bitmap frontBitmap2 = BitmapFactory.decodeFile(file.getAbsolutePath());
     //   Bitmap frontBitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yz_img1);
        merge(frontBitmap2);
        frontBitmap2.recycle();

        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 3; i < 10; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item1_4:
                        if (checkItems.get(i).getJL().equals("是")) {
                            //writeText("√", 20.76f, 3.8f, 2.3f, 0.7f);
                        } else {
                            //writeText("√", 20.76f, 4.8f, 2.3f, 0.7f);
                            for (int n = 0; n < checkItems.get(i).getList().size(); n++) {
                                boolean cheek = checkItems.get(i).getList().get(n).isCheek();

                                if (cheek) {
                                    switch (n) {
                                        case 6:
                                            writeText("√", 23.72f, 2.8f, 2.3f, 0.7f);
                                            break;
                                        case 7:
                                            writeText("√", 23.72f, 8.25f, 2.3f, 0.7f);
                                            break;
                                        case 8:
                                            writeText("√", 23.72f, 14.6f, 2.3f, 0.7f);
                                            break;


                                    }
                                }

                            }

                            //writeText("图-P1", 29.1f, 18.3f,  0.5f, 1.0f);
                        }
                        break;

                    case Constance.Item1_5:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.76f, 19.55f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.76f, 20.55f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 20.05f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 20.05f, 3.3f, 0.7f);
                            }
                            String s = fl2.get("" + i);
                            writeText(s, 28.1f, 20.05f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_6:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.76f, 22.8f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.76f, 23.6f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 23.2f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 23.2f, 3.3f, 0.7f);
                            }
                            String s = fl2.get("" + i);
                            writeText(s, 28.1f, 23.2f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_7:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.76f, 26.0f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.76f, 26.95f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 18.1f, 26.47f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 26.47f, 3.3f, 0.7f);
                            }
                            String s = fl2.get("" + i);
                            writeText(s, 28.1f, 26.47f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_8:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.76f, 28.3f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.76f, 29.25f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 28.80f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 28.80f, 3.3f, 0.7f);
                            }
                            String s = fl2.get("" + i);
                            writeText(s, 28.1f, 28.80f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_9:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.76f, 35.80f, 2.3f, 0.7f);
                        } else {
                            writeText("√", 20.76f, 36.75f, 2.3f, 0.7f);
                            String s = fl2.get("" + i);
                            writeText(s, 28.1f, 36.30f, 4.0f, 1.0f);
                            for (int n = 0; n < checkItems.get(i).getList().size(); n++) {
                                boolean cheek = checkItems.get(i).getList().get(n).isCheek();

                                if (cheek) {
                                    switch (n) {
                                        case 0:
                                            writeText("√", 23.72f, 30.3f, 2.3f, 0.7f);
                                            break;
                                        case 1:
                                            writeText("√", 23.72f, 32.1f, 2.3f, 0.7f);
                                            break;
                                        case 2:
                                            writeText("√", 23.72f, 33.9f, 2.3f, 0.7f);
                                            break;
                                        case 3:
                                            writeText("√", 23.72f, 36.7f, 2.3f, 0.7f);
                                            break;
                                        case 4:
                                            writeText("√", 23.72f, 39.5f, 2.3f, 0.7f);
                                            break;
                                        case 5:
                                            writeText("√", 23.72f, 41.3f, 2.3f, 0.7f);
                                            break;

                                    }
                                }
                            }

                        }
                        break;
                }
            }
        }

        File f2 = new File(FileUtil.getInstance().getCacheDirPath(), "yz_2.jpg");
        if (f2.exists()) f2.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, f2.getAbsolutePath());

    }

    /**
     * 第三张
     */
    public String draw_3() {
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"yz_img3.jpg");
        Bitmap frontBitmap3 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap frontBitmap3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yz_img2);
        merge(frontBitmap3);
        frontBitmap3.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 8; i < 22; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item1_9:

                        boolean cheek = checkItems.get(i).getList().get(6).isCheek();
                        if (cheek) {
                            writeText("√", 23.72f, 3.7f, 2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item1_10:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 6.7f, 0.7f, 0.7f);

                        } else {
                            writeText("√", 20.8f, 7.6f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 7.15f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 7.15f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 7.15f, 4.0f, 1.0f);

                        }
                        break;
                    case Constance.Item1_11:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 9.1f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 10.0f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 9.55f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 9.55f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 9.55f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_12:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 11.5f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 12.4f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 11.95f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 11.95f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 11.95f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_13:
                        if (checkItems.get(i).getJL().equals("是")) {


                            writeText("√", 20.8f, 13.9f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 14.9f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 14.35f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 14.35f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 14.35f, 4.0f, 1.7f);
                        }
                        break;
                    case Constance.Item1_14:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 16.3f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 17.2f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 16.75f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 16.75f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 16.75f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_15:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 18.8f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 19.7f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 19.25f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 19.25f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 19.25f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_16:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 21.1f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 22.0f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 21.55f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 21.55f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 21.55f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_17:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 23.5f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 24.4f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 23.95f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 23.95f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 23.95f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_18:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 27.7f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 28.6f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 28.2f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 28.2f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 28.2f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_19:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 32.0f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 32.9f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 32.65f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 32.65f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 32.65f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_20:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 35.45f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 36.4f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 36.9f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 36.9f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 36.9f, 4.0f, 1.0f);
                        }
                        break;
                    case Constance.Item1_21:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 40.3f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 41.2f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 40.75f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 40.75f, 2.0f, 1.0f);
                            }
                            String s = fl3.get("" + i);
                            writeText(s, 27.5f, 40.75f, 4.0f, 1.0f);
                        }
                        break;
                }
            }
        }

        File f3 = new File(FileUtil.getInstance().getCacheDirPath(), "yz_3.jpg");
        if (f3.exists()) f3.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, f3.getAbsolutePath());

    }

    /**
     * 第四张
     */
    public String draw_4() {
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"yz_img4.jpg");
        Bitmap frontBitmap4 = BitmapFactory.decodeFile(file.getAbsolutePath());
       // Bitmap frontBitmap4 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yz_img3);
        merge(frontBitmap4);
        frontBitmap4.recycle();

        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 21; i < 26; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item1_22:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 3.6f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 4.5f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 4.05f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 4.05f, 2.0f, 1.0f);
                            }
                            String s = fl4.get("" + i);
                            writeText(s, 27.5f, 4.05f, 1.5f, 1.0f);
                        }
                        break;
                    case Constance.Item1_23:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 20.8f, 6.47f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 7.37f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 6.92f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 6.92f, 2.0f, 1.0f);
                            }
                            String s = fl4.get("" + i);
                            writeText(s, 27.5f, 6.92f, 1.5f, 1.0f);
                        }
                        break;
                    case Constance.Item1_24:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 9.55f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 10.45f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 10.0f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 10.0f, 2.0f, 1.0f);
                            }
                            String s = fl4.get("" + i);
                            writeText(s, 27.5f, 10.0f, 1.5f, 1.0f);
                        }
                        break;
                    case Constance.Item1_25:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 13.12f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 14.02f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 13.57f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 13.57f, 2.0f, 1.0f);
                            }
                            String s = fl4.get("" + i);
                            writeText(s, 27.5f, 13.57f, 1.5f, 1.0f);
                        }
                        break;
                    case Constance.Item1_26:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√", 20.8f, 15.9f, 0.7f, 0.7f);
                        } else {
                            writeText("√", 20.8f, 16.8f, 0.7f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.72f, 16.35f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.72f, 16.35f, 2.0f, 1.0f);
                            }
                            String s = fl4.get("" + i);
                            writeText(s, 27.5f, 16.35f, 1.5f, 1.0f);
                        }
                        break;
                }
            }
        }
        if(checkItems.get(26).getName()!=null){
            writeText(checkItems.get(26).getName(), 8.8f, 18.13f, 3.0f, 1.0f);
        }
        if (bean.getJcryxmyi() != null)
            writeText(bean.getJcryxmyi(), 8.8f, 31.0f, 5.0f, 1.0f);
        if (bean.getZfzhyi() != null)
            writeText(bean.getZfzhyi(), 20.8f, 31.0f, 5.0f, 1.0f);

        if (bean.getJcryxmer() != null)
            writeText(bean.getJcryxmer(), 8.8f, 33.5f, 5.0f, 1.0f);
        if (bean.getZfzher() != null)
            writeText(bean.getZfzher(), 20.8f, 33.5f, 5.0f, 1.0f);
        if (bean.getJcdate() != null)
            writeText(bean.getJcdate(), 8.8f, 36.0f, 3.0f, 1.0f);

        File commonSupervisionDir = PhotoViewModel.getCommonSupervisionDir();
        if (commonSupervisionDir.exists()) {
            List<UploadCommonSupervisionBean.SignPicNames> signPicNames = bean.getSignPicNames();
            //负责人
            UploadCommonSupervisionBean.SignPicNames signPicNames1 = signPicNames.get(3);
            if (signPicNames1 != null) {
                String qz = signPicNames1.getQz();
                File ff1 = new File(commonSupervisionDir, qz);
                if (ff1.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(23.0f), (int) JsPxValue(36.0f), (int) JsPxValue(25.7f), (int) JsPxValue(37.0f)), paint);
                    bitmap.recycle();
                }

            }
            //检察人员1
            UploadCommonSupervisionBean.SignPicNames signPicNames2 = signPicNames.get(1);
            if (signPicNames1 != null) {
                String qz = signPicNames2.getQz();
                File ff1 = new File(commonSupervisionDir, qz);
                if (ff1.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(9.0f), (int) JsPxValue(38.0f), (int) JsPxValue(12.0f), (int) JsPxValue(39.0f)), paint);
                    bitmap.recycle();
                }

            }
            //检察人员2
            UploadCommonSupervisionBean.SignPicNames signPicNames3 = signPicNames.get(2);
            if (signPicNames1 != null) {
                String qz = signPicNames3.getQz();
                File ff1 = new File(commonSupervisionDir, qz);
                if (ff1.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(23.0f), (int) JsPxValue(38.0f), (int) JsPxValue(26.0f), (int) JsPxValue(39.0f)), paint);
                    bitmap.recycle();
                }

            }


        }
        File f4 = new File(FileUtil.getInstance().getCacheDirPath(), "yz_4.jpg");
        if (f4.exists()) f4.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, f4.getAbsolutePath());


    }


    /**
     * 绘制附录图片
     *
     * @param path
     * @return 存储路径
     */
    public String getFLPath(String path) {
        mCacheCanvas.drawColor(Color.WHITE);
        Bitmap bitmap = BitmapFactory.decodeFile(path);

        int oldwidth = bitmap.getWidth();
        int oldheight = bitmap.getHeight();

        int canWidth = mCacheCanvas.getWidth();
        int canHeight = mCacheCanvas.getHeight();


        Rect src = new Rect();
        src.left = 0;
        src.top = 0;
        src.right = oldwidth;
        src.bottom = oldheight;

        RectF des = new RectF();
        des.left = 80;
        des.right = mCacheCanvas.getWidth() - 80;
        des.top = 80;
        des.bottom = canHeight - 80;

        mCacheCanvas.drawBitmap(bitmap, src, des, paint);
        bitmap.recycle();

        switch (path.split("_")[1]) {
            case "P1.jpg":
                writeText(fl1.get("0"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P2.jpg":
                writeText(fl1.get("1"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P3.jpg":
                writeText(fl1.get("2"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P4.jpg":
                writeText(fl1.get("3"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P5.jpg":
                writeText(fl2.get("4"), 16.5f, 1.0f, 4.0f, 2.0f);
                break;
            case "P6.jpg":
                writeText(fl2.get("5"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P7.jpg":
                writeText(fl2.get("6"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P8.jpg":
                writeText(fl2.get("7"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P9.jpg":
                writeText(fl2.get("8"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P10.jpg":
                writeText(fl3.get("9"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P11.jpg":
                writeText(fl3.get("10"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P12.jpg":
                writeText(fl3.get("11"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P13.jpg":
                writeText(fl3.get("12"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P14.jpg":
                writeText(fl3.get("13"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P15.jpg":
                writeText(fl3.get("14"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P16.jpg":
                writeText(fl3.get("15"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P17.jpg":
                writeText(fl3.get("16"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P18.jpg":
                writeText(fl3.get("17"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P19.jpg":
                writeText(fl3.get("18"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P20.jpg":
                writeText(fl3.get("19"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P21.jpg":
                writeText(fl3.get("20"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P22.jpg":
                writeText(fl4.get("21"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P23.jpg":
                writeText(fl4.get("22"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P24.jpg":
                writeText(fl4.get("23"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P25.jpg":
                writeText(fl4.get("24"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P26.jpg":
                writeText(fl4.get("25"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
        }

        File ff = new File(FileUtil.getInstance().getCacheDirPath(),"yz_"+numName+".jpg");
        if (ff.exists()) ff.delete();
        String pt = FileUtil.getInstance().save2Local2(cacheBitmap, ff.getAbsolutePath());
        numName++;
        return pt;
    }
    private int numName=5;
}
