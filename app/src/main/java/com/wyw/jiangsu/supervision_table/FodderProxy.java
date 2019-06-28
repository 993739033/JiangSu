package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.wyw.jiangsu.activity.model.PhotoViewModel;
import com.wyw.jiangsu.bean.local.CheckContentEntity;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.BitmapUtils;
import com.wyw.jiangsu.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUANG on 2017/7/24.
 */
public class FodderProxy extends BaseProxy {
    private final UploadCommonSupervisionBean bean;
    private List<String> paths=new ArrayList<>();

    public FodderProxy(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
        super(context,cachecanvas,cachebitmap);
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
    public String draw_1(){
        File file=new File(FileUtil.getInstance().getDirPrint(),"siliao_1.jpg");
        Bitmap bt1 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap bt1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.siliao_1);
        merge(bt1);
        bt1.recycle();
        if(bean.getFName()!=null)
            writeText(bean.getFName(),8.0f,5.5f,6.0f,1.0f);
        if(bean.getGlid()!=null)
            writeText(bean.getGlid(),23.0f,5.5f,6.0f,1.0f);
        if(bean.getFCityAdd()!=null)
            writeText(bean.getFCityAdd(),8.0f,7.2f,6.0f,1.0f);

        //writeText("联系人",8.0f,9.0f,2.0f,1.0f);
        if(bean.getFPhone()!=null)
            writeText(bean.getFPhone(),22.0f,9.0f,2.0f,1.0f);
        if(bean.getFLegalPerson()!=null)
            writeText(bean.getFLegalPerson(),8.0f,10.9f,4.0f,1.0f);
        List<CheckContentEntity> checkItems = bean.getCheckItems();

        if (checkItems != null) {
            for (int i = 0; i < 20; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item6_1:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,14.5f,1.0f,1.0f);
                        } else {
                            writeText("√",23.6f,14.5f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",26.8f,14.5f,1.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),26.8f,14.5f,3.3f,1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_2:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,17.6f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 17.6f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 17.6f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 17.6f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_3:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,20.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 20.5f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 20.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 20.5f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_4:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,22.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 22.5f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 22.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 22.5f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_5:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,24.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 24.5f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 24.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 24.5f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_6:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,26.6f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 26.6f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 26.6f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 26.6f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_7:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,28.6f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 28.6f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 28.6f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 28.6f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_8:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,30.6f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 30.6f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 30.6f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 30.6f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_9:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,32.99f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 32.99f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 32.99f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 32.99f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_10:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,35.48f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 35.48f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 35.48f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 35.48f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_11:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,37.6f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 37.6f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 37.6f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 37.6f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_12:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,39.78f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 39.78f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 39.78f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 39.78f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_13:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,41.56f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 41.56f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 41.56f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 41.56f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_14:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.6f,43.79f,1.0f,1.0f);
                        } else {
                            writeText("√", 23.6f, 43.79f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 43.79f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 43.79f, 3.3f, 1.0f);
                            }
                        }
                        break;
                }
            }
        }
        File f1 = new File(FileUtil.getInstance().getCacheDirPath(), "sl_1.jpg");
        if (f1.exists()) f1.delete();
        String s1 = FileUtil.getInstance().save2Local2(cacheBitmap, f1.getAbsolutePath());
        return  s1;

    }
    public String draw_2(){

        //第二张
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"siliao_2.jpg");
        Bitmap b2 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.siliao_2);
        merge(b2);
        if(!b2.isRecycled())b2.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 0; i < 20; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item6_15:
                        if (checkItems.get(i).getJL().equals("是")) {

                            writeText("√",21.7f,3.0f,1.0f,1.0f);

                        } else {
                            writeText("√", 23.7f, 3.0f, 2.3f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 3.0f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 3.0f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_16:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.7f,5.99f,1.0f,1.0f);

                        } else {
                            writeText("√", 23.7f, 5.99f, 2.3f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 5.99f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 5.99f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_17:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.7f,12.60f,1.0f,1.0f);

                        } else {
                            writeText("√", 23.7f, 12.60f, 2.3f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 12.60f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 12.60f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_18:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.7f,18.42f,1.0f,1.0f);

                        } else {
                            writeText("√", 23.7f, 18.42f, 2.3f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 18.42f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 18.42f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item6_19:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",21.7f,21.50f,1.0f,1.0f);

                        } else {
                            writeText("√", 23.7f, 21.50f, 2.3f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 26.8f, 21.50f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 26.8f, 21.50f, 3.3f, 1.0f);
                            }
                        }
                        break;
                }
            }
        }





      /*  writeText("√",21.7f,3.0f,1.0f,1.0f);
        writeText("√", 23.7f, 3.0f, 2.3f, 1.0f);

        writeText("√",21.7f,5.99f,1.0f,1.0f);
        writeText("√", 23.7f, 5.99f, 2.3f, 1.0f);

        writeText("√",21.7f,12.60f,1.0f,1.0f);
        writeText("√", 23.7f, 12.60f, 2.3f, 1.0f);

        writeText("√",21.7f,18.42f,1.0f,1.0f);
        writeText("√", 23.7f, 18.42f, 2.3f, 1.0f);

        writeText("√",21.7f,21.50f,1.0f,1.0f);
        writeText("√", 23.7f, 21.50f, 2.3f, 1.0f);*/
        String name = checkItems.get(19).getName();
        if (name!=null){
            writeText(name,7.7f,24.50f,16.0f,1.0f);
        }
        if(bean.getJcclyj()!=null){
            writeText(bean.getJcclyj(),8.7f,32.50f,8.0f,1.0f);
        }
        if(bean.getJcryxmyi()!=null){
            writeText(bean.getJcryxmyi(),8.7f,39.00f,4.0f,2.0f);
        }
        if(bean.getJcryxmer()!=null){
            writeText(bean.getJcryxmyi(),8.7f,41.00f,4.0f,2.0f);
        }
        if(bean.getZfzhyi()!=null)
            writeText(bean.getZfzhyi(),20.7f,39.00f,4.0f,1.0f);
        if(bean.getZfzher()!=null)
            writeText(bean.getZfzher(),20.7f,41.00f,4.0f,1.0f);

        if(bean.getJcdate()!=null){
            writeText(bean.getJcdate(),22.7f,42.50f,4.0f,1.0f);
        }
        File commonSupervisionDir = PhotoViewModel.getCommonSupervisionDir();
        if(commonSupervisionDir.exists()) {
            List<UploadCommonSupervisionBean.SignPicNames> signPicNames = bean.getSignPicNames();
            //负责人
            UploadCommonSupervisionBean.SignPicNames signPicNames1 = signPicNames.get(3);
            if (signPicNames1 != null) {
                String qz = signPicNames1.getQz();
                File ff1 = new File(commonSupervisionDir, qz);
                if (ff1.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(8.7f), (int) JsPxValue(42.50f), (int) JsPxValue(11.7f), (int) JsPxValue(43.50f)), paint);
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
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(8.7f), (int) JsPxValue(44.40f), (int) JsPxValue(11.7f), (int) JsPxValue(45.40f)), paint);
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
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(22.7f), (int) JsPxValue(44.40f), (int) JsPxValue(25.7f), (int) JsPxValue(45.40f)), paint);
                    bitmap.recycle();
                }

            }
        }

/*

            writeText("负责人",8.7f,42.50f,4.0f,1.0f);
        writeText("检查1",8.7f,44.40f,4.0f,1.0f);
        writeText("加的三",22.7f,44.40f,4.0f,1.0f);*/

        File f2 = new File(FileUtil.getInstance().getCacheDirPath(), "sl_2.jpg");
        if (f2.exists()) f2.delete();
        String s2 = FileUtil.getInstance().save2Local2(cacheBitmap, f2.getAbsolutePath());
        return s2;
    }
    @Override
    public List<String> draws() {
        paths.add(draw_2());
        paths.add(draw_1());
        if(!cacheBitmap.isRecycled()){
            cacheBitmap.recycle();
            System.gc();
        }
        return paths;
    }
}
