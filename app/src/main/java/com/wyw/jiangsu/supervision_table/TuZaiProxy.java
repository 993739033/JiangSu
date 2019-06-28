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
 * Created by HUANG on 2017/7/20.
 */
public class TuZaiProxy extends BaseProxy {
    private final UploadCommonSupervisionBean bean;
    private List<String> paths=new ArrayList<>();

    public TuZaiProxy(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
        super(context,cachecanvas,cachebitmap);
        this.mContext = context;
        this.mCacheCanvas = cachecanvas;
        this.bean = t;
        this.cacheBitmap = cachebitmap;
    }

    @Override
    public Bitmap draw(int whichPrinter) {
      /*  //第一张开始绘制
        Bitmap bt1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tz11);
        BitmapUtils.mergeBitmap(cacheBitmap,bt1);
        bt1.recycle();
        writeText("名称"+bean.getFLegalPerson(),13.0f,3.0f,2.0f,1.0f);
        writeText("负责人"+bean.getFLegalPerson(),20.0f,3.0f,2.0f,1.0f);
        writeText("地址",13.0f,3.0f,2.0f,1.0f);
        writeText("电话",20.0f,3.0f,2.0f,1.0f);*/
        return null;
    }
    private void merge(Bitmap frontBitmap) {
        cacheBitmap = BitmapUtils.mergeBitmap(cacheBitmap, frontBitmap);
        if (cacheBitmap != null) mCacheCanvas = new Canvas(cacheBitmap);
    }
    private String draw_1(){
        File file=new File(FileUtil.getInstance().getDirPrint(),"tz_1.jpg");
        Bitmap bt1 = BitmapFactory.decodeFile(file.getAbsolutePath());
       //Bitmap bt1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tz11);
        merge(bt1);
        bt1.recycle();
        if(bean.getFName()!=null)
            writeText(bean.getFName(),13.0f,7.2f,7.0f,1.0f);
        if (bean.getFLegalPerson()!=null)
            writeText(bean.getFLegalPerson(),25.0f,7.2f,7.0f,1.0f);
        if (bean.getFCityAdd()!=null)
            writeText(bean.getFCityAdd(),9.0f,9.0f,9.0f,1.0f);
        if (bean.getFPhone()!=null)
            writeText(bean.getFPhone(),22.0f,9.0f,9.0f,1.0f);

        List<CheckContentEntity> checkItems = bean.getCheckItems();

        if (checkItems != null) {
            for (int i = 0; i < 20; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item2_1:
                        if (checkItems.get(i).getJL().equals("能")) {
                            writeText("√",25.0f,12.3f,1.0f,1.0f);
                        } else {
                            writeText("√",27.1f,12.3f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("备注",28.2f,12.3f,1.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),28.2f,12.3f,3.3f,1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_2:
                        if (checkItems.get(i).getJL().equals("能")) {
                            writeText("√",25.0f,13.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 13.5f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 13.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 13.5f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_3:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,14.8f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 14.8f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 14.8f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 14.8f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_4:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,16.1f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 16.1f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 16.1f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 16.1f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_5:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,17.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 17.5f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 17.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 17.5f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_6:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,18.8f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 18.8f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 18.8f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 18.8f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_7:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,20.2f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 20.2f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 20.2f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 20.2f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_8:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,21.6f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 21.6f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 21.6f, 3.3f, 0.7f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 21.6f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_9:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,23.3f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 23.3f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 23.3f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 23.3f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_10:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,24.9f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 24.9f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 24.9f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 24.9f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_11:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,26.4f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 26.4f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 26.4f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 26.4f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_12:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,28.1f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 28.1f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 28.1f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 28.1f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_13:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,29.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 29.5f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 29.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 29.5f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_14:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,31.1f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 31.1f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 31.1f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 31.1f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_15:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,32.9f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 32.9f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 32.9f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 32.9f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_16:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,34.7f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 34.7f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 34.7f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 34.7f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_17:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,36.3f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 36.3f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 36.3f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f,36.3f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_18:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,37.8f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 37.8f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 37.8f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 37.8f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_19:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,39.7f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 39.7f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 39.7f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 39.7f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_20:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,41.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 41.5f, 2.3f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 41.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 41.5f, 3.3f, 1.0f);
                            }
                        }
                        break;



                }
            }
        }



       /*  writeText("√",25.0f,12.3f,1.0f,1.0f);
        writeText("√",25.0f,13.5f,1.0f,1.0f);
       writeText("√",25.0f,14.8f,1.0f,1.0f);
        writeText("√",25.0f,16.1f,1.0f,1.0f);
        writeText("√",25.0f,17.5f,1.0f,1.0f);
         writeText("√",25.0f,18.8f,1.0f,1.0f);
        writeText("√",25.0f,20.2f,1.0f,1.0f);
        writeText("√",25.0f,21.6f,1.0f,1.0f);
       writeText("√",25.0f,23.3f,1.0f,1.0f);
        writeText("√",25.0f,24.9f,1.0f,1.0f);
        writeText("√",25.0f,26.4f,1.0f,1.0f);
        writeText("√",25.0f,28.2f,1.0f,1.0f);
        writeText("√",25.0f,29.5f,1.0f,1.0f);
         writeText("√",25.0f,31.1f,1.0f,1.0f);
       writeText("√",25.0f,32.9f,1.0f,1.0f);
        writeText("√",25.0f,34.7f,1.0f,1.0f);
        writeText("√",25.0f,36.3f,1.0f,1.0f);
         writeText("√",25.0f,37.8f,1.0f,1.0f);

       writeText("√",25.0f,39.7f,1.0f,1.0f);
        writeText("√",25.0f,41.5f,1.0f,1.0f);
*/

       /* writeText("√",27.1f,12.3f,1.0f,1.0f);
        writeText("√",27.1f,13.5f,1.0f,1.0f);
        writeText("√",27.1f,14.8f,1.0f,1.0f);
        writeText("√",27.1f,16.1f,1.0f,1.0f);
        writeText("√",27.1f,17.5f,1.0f,1.0f);
        writeText("√",27.1f,18.8f,1.0f,1.0f);
        writeText("√",27.1f,20.2f,1.0f,1.0f);
        writeText("√",27.1f,21.6f,1.0f,1.0f);
        writeText("√",27.1f,23.3f,1.0f,1.0f);
        writeText("√",27.1f,24.9f,1.0f,1.0f);
        writeText("√",27.1f,26.4f,1.0f,1.0f);
        writeText("√",27.1f,28.2f,1.0f,1.0f);
        writeText("√",27.1f,29.5f,1.0f,1.0f);
        writeText("√",27.1f,31.1f,1.0f,1.0f);
        writeText("√",27.1f,32.9f,1.0f,1.0f);
        writeText("√",27.1f,34.7f,1.0f,1.0f);
        writeText("√",27.1f,36.3f,1.0f,1.0f);
        writeText("√",27.1f,37.8f,1.0f,1.0f);
        writeText("√",27.1f,39.7f,1.0f,1.0f);
        writeText("√",27.1f,41.5f,1.0f,1.0f);

        writeText("备注",28.2f,12.3f,4.0f,1.5f);
        writeText("备注",28.2f,13.5f,4.0f,1.5f);
        writeText("备注",28.2f,14.8f,4.0f,1.5f);
        writeText("备注",28.2f,16.1f,4.0f,1.5f);
        writeText("备注",28.2f,17.5f,4.0f,1.5f);
        writeText("备注",28.2f,18.8f,4.0f,1.5f);
        writeText("备注",28.2f,20.2f,4.0f,1.5f);
        writeText("备注",28.2f,21.6f,4.0f,1.5f);
        writeText("备注",28.2f,23.3f,4.0f,1.5f);
        writeText("备注",28.2f,24.9f,4.0f,1.5f);
        writeText("备注",28.2f,26.4f,4.0f,1.5f);
        writeText("备注",28.2f,28.2f,4.0f,1.5f);
        writeText("备注",28.2f,29.5f,4.0f,1.5f);
        writeText("备注",28.2f,31.1f,4.0f,1.5f);
        writeText("备注",28.2f,32.9f,4.0f,1.5f);
        writeText("备注",28.2f,34.7f,4.0f,1.5f);
        writeText("备注",28.2f,36.3f,4.0f,1.5f);
        writeText("备注",28.2f,37.8f,4.0f,1.5f);
        writeText("备注",28.2f,39.7f,4.0f,1.5f);
        writeText("备注",28.2f,41.5f,4.0f,1.5f);
*/
        File f1 = new File(FileUtil.getInstance().getCacheDirPath(), "tz_1.jpg");
        if (f1.exists()) f1.delete();
        String s1 = FileUtil.getInstance().save2Local2(cacheBitmap, f1.getAbsolutePath());
        return s1;

    }
    private String draw_2(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"tz_2.jpg");
        Bitmap b2 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tz22);
        merge(b2);
        if(!b2.isRecycled())b2.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 19; i < checkItems.size(); i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item2_21:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,4.1f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 4.1f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 4.1f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 4.1f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_22:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,6.6f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 6.6f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 6.6f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 6.6f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_23:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,9.4f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 9.4f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 9.4f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 9.4f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_24:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,11.7f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 11.7f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 11.7f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 11.7f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_25:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,13.5f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 13.5f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 13.5f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 13.5f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_26:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,15.4f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 15.4f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 15.4f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 15.4f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_27:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,17.1f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 17.1f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 17.1f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 17.1f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_28:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,18.96f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 18.96f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 18.96f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 18.96f, 3.3f, 1.0f);
                            }
                        }
                        break;
                    case Constance.Item2_29:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",25.0f,21.2f,1.0f,1.0f);
                        } else {
                            writeText("√", 27.1f, 21.2f, 1.0f, 0.7f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 28.2f, 21.2f, 3.3f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 28.2f, 21.2f, 3.3f, 1.0f);
                            }
                        }
                        break;

                }
            }
        }
        if(checkItems.get(29).getName()!=null)
            writeText(checkItems.get(29).getName(), 8.2f, 25.2f, 3.3f, 1.0f);
        if(bean.getJcryxmyi()!=null)
            writeText(bean.getJcryxmyi(), 14.0f, 35.5f, 3.3f, 1.0f);
        if(bean.getJcdate()!=null){
            String jcdate = bean.getJcdate();
            String[] split = jcdate.split("-");

            writeText(split[0], 23.0f, 37.8f, 3.0f, 2.0f);
            writeText(split[1], 25.5f, 37.8f, 3.0f, 2.0f);
            writeText(split[2], 27.5f, 37.8f, 3.0f, 2.0f);

            writeText(split[0], 23.0f, 35.4f, 3.0f, 2.0f);
            writeText(split[1], 25.5f, 35.4f, 3.0f, 2.0f);
            writeText(split[2], 27.5f, 35.4f, 3.0f, 2.0f);
        }



        drawQianZi();




        File f2 = new File(FileUtil.getInstance().getCacheDirPath(), "tz_2.jpg");
        if (f2.exists()) f2.delete();
        String s2 = FileUtil.getInstance().save2Local2(cacheBitmap, f2.getAbsolutePath());
        return s2;
    }
    @Override
    public List<String> draws() {
        draw_1();
        draw_2();
        if (!cacheBitmap.isRecycled()) {
            cacheBitmap.recycle();
            System.gc();
        }
        return FileUtil.getInstance().getPrintList("tz");
    }

    private void drawQianZi() {
        File commonSupervisionDir = PhotoViewModel.getCommonSupervisionDir();
        if(commonSupervisionDir.exists()) {
            List<UploadCommonSupervisionBean.SignPicNames> signPicNames = bean.getSignPicNames();
            //负责人
            UploadCommonSupervisionBean.SignPicNames signPicNames1 = signPicNames.get(3);
            if(signPicNames1!=null){
                String qz = signPicNames1.getQz();
                File ff1=new File(commonSupervisionDir,qz);
                if(ff1.exists()){
                    Bitmap bitmap = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bitmap,null,new Rect((int)JsPxValue(14.0f),(int)JsPxValue(38.0f),(int)JsPxValue(17.0f),(int)JsPxValue(39.0f)),paint);
                    bitmap.recycle();
                }

            }
            //检察人员1
            UploadCommonSupervisionBean.SignPicNames signPicNames2 = signPicNames.get(1);
            if(signPicNames1!=null){
                String qz = signPicNames2.getQz();
                File ff1=new File(commonSupervisionDir,qz);
                if(ff1.exists()){
                    Bitmap bitmap = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bitmap,null,new Rect((int)JsPxValue(12.0f),(int)JsPxValue(40.0f),(int)JsPxValue(15.0f),(int)JsPxValue(42.0f)),paint);
                    bitmap.recycle();
                }

            }
            //检察人员2
            UploadCommonSupervisionBean.SignPicNames signPicNames3 = signPicNames.get(2);
            if(signPicNames1!=null){
                String qz = signPicNames3.getQz();
                File ff1=new File(commonSupervisionDir,qz);
                if(ff1.exists()){
                    Bitmap bitmap = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bitmap,null,new Rect((int)JsPxValue(25.0f),(int)JsPxValue(40.0f),(int)JsPxValue(28.0f),(int)JsPxValue(42.0f)),paint);
                    bitmap.recycle();
                }

            }


        }
    }



}
