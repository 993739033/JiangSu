package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

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

/**
 * Created by HUANG on 2017/7/25.
 */
public class VDManagementProxy extends BaseProxy {
    private final UploadCommonSupervisionBean bean;
    private List<String> paths=new ArrayList<>();

    public VDManagementProxy(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
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

    @Override
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

        //第一张
        paths.add(draw_1());
        //第二张
        paths.add(draw_2());
        //第三张
        paths.add(draw_3());
        //回收cacheBitmap
        if (!cacheBitmap.isRecycled()) {
            cacheBitmap.recycle();
            System.gc();
        }
        return FileUtil.getInstance().getPrintList("syjy");
    }
    private void merge(Bitmap frontBitmap) {
        cacheBitmap = BitmapUtils.mergeBitmap(cacheBitmap, frontBitmap);
        if (cacheBitmap != null) mCacheCanvas = new Canvas(cacheBitmap);
    }
    /**
     * 第一张
     */
    public String draw_1(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"syjy_1.jpg");
        Bitmap b1 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.syjy_1);
        merge(b1);
        if(!b1.isRecycled())b1.recycle();
        if (bean.getFName()!=null)
        writeText(bean.getFName(),9.0f,6.0f,16.0f,2.0f);
        if (bean.getFCityAdd()!=null)
        writeText(bean.getFCityAdd(),9.0f,8.0f,16.0f,2.0f);
        if (bean.getFLegalPerson()!=null)
        writeText(bean.getFLegalPerson(),9.0f,10.0f,8.0f,2.0f);
        if (bean.getFPhone()!=null)
        writeText(bean.getFPhone(),24.0f,10.0f,8.0f,2.0f);
        if (bean.getFCategory()!=null)
        writeText(bean.getFCategory(),9.0f,12.4f,8.0f,2.0f);
        if (bean.getClsl()!=null)
            writeText(bean.getFPhone(),24.0f,12.4f,8.0f,2.0f);

        List<CheckContentEntity> checkItems = bean.getCheckItems();

        if (checkItems!=null)
        for (int i=0;i<8;i++){
            switch (checkItems.get(i).getName()) {
                case Constance.Item8_1:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,19.7f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,19.7f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,19.7f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                        writeText(fl1.get(""+i), 26.5f,19.7f,2.0f,2.0f);
                    }
/*
                    writeText("√",19.5f,19.7f,1.0f,1.0f);
                    writeText("√",21.5f,19.7f,1.0f,1.0f);
                    writeText("备注",23.5f,19.7f,2.0f,2.0f);
                    writeText("图1-1",26.5f,19.7f,2.0f,2.0f);*/
                    break;
                case Constance.Item8_2:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,23.2f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,23.2f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,23.2f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                            writeText(fl1.get(""+i), 26.5f,23.2f,2.0f,2.0f);
                    }
                    //writeText("√",19.5f,23.2f,1.0f,1.0f);
                    break;
                case Constance.Item8_3:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,26.7f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,26.7f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,26.7f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                            writeText(fl1.get(""+i), 26.5f,26.7f,2.0f,2.0f);
                    }
                   // writeText("√",19.5f,26.7f,1.0f,1.0f);
                    break;
                case Constance.Item8_4:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,30.7f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,30.7f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,30.7f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                            writeText(fl1.get(""+i), 26.5f,30.7f,2.0f,2.0f);
                    }
                    //writeText("√",19.5f,30.7f,1.0f,1.0f);
                    break;
                case Constance.Item8_5:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,33.7f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,33.7f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,33.7f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                            writeText(fl1.get(""+i), 26.5f,33.7f,2.0f,2.0f);
                    }
                  //  writeText("√",19.5f,33.7f,1.0f,1.0f);
                    break;
                case Constance.Item8_6:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,36.7f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,36.7f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,36.7f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                            writeText(fl1.get(""+i), 26.5f,36.7f,2.0f,2.0f);
                    }
                    //writeText("√",19.5f,36.7f,1.0f,1.0f);
                    break;
                case Constance.Item8_7:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,40.7f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,40.7f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,40.7f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                            writeText(fl1.get(""+i), 26.5f,40.7f,2.0f,2.0f);
                    }
                    //writeText("√",19.5f,40.7f,1.0f,1.0f);
                    break;
                case Constance.Item8_8:
                    if (checkItems.get(i).getJL().equals("是")) {
                        writeText("√",19.5f,43.7f,1.0f,1.0f);
                    } else {
                        writeText("√",21.5f,43.7f,1.0f,1.0f);
                        if (checkItems.get(i).getQK() != null) {
                            writeText(checkItems.get(i).getQK(), 23.5f,43.7f,2.0f,2.0f);
                        }
                        if (fl1.get(""+i)!=null)
                            writeText(fl1.get(""+i), 26.5f,43.7f,2.0f,2.0f);
                    }
                    //writeText("√",19.5f,43.7f,1.0f,1.0f);
                    break;
            }
        }







        File f1=new File(FileUtil.getInstance().getCacheDirPath(),"syjy_1.jpg");
        if (f1.exists())f1.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap,f1.getAbsolutePath());
    }
    public String draw_2(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"syjy_2.jpg");
        Bitmap b2 = BitmapFactory.decodeFile(file.getAbsolutePath());
       // Bitmap b2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.syjy_2);
        merge(b2);
        if (!b2.isRecycled())b2.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();

        if (checkItems!=null) {
            for (int i = 8; i < 18; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item8_9:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 3.4f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 3.4f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 3.4f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 3.4f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_10:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 5.4f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 5.4f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 5.4f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 5.4f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_11:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 8.0f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 8.0f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 8.0f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 8.0f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_12:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 10.4f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 10.4f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 10.4f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 10.4f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_13:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 13.0f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 13.0f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 13.0f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 13.0f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_14:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 15.47f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 15.47f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 15.47f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 15.47f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_15:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 17.7f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 17.7f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 17.7f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 17.7f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_16:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 19.80f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 19.80f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 19.80f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 19.80f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_17:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 21.79f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 21.79f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 21.79f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 21.79f, 2.0f, 2.0f);
                        }
                        break;
                    case Constance.Item8_18:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√", 19.5f, 24.2f, 1.0f, 1.0f);
                        } else {
                            writeText("√", 21.5f, 24.2f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() != null) {
                                writeText(checkItems.get(i).getQK(), 23.5f, 24.2f, 2.0f, 2.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 26.5f, 24.2f, 2.0f, 2.0f);
                        }
                        break;
                }
            }
        }
        if (checkItems.get(18).getName()!=null){
            writeText(checkItems.get(18).getName(), 9.0f, 26.4f, 1.0f, 1.0f);
        }
        if(bean.getJcclyj()!=null){
            writeText(bean.getJcclyj(), 9.0f, 33.2f, 8.0f, 2.0f);
        }

        File f2=new File(FileUtil.getInstance().getCacheDirPath(),"syjy_2.jpg");
        if (f2.exists())f2.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap,f2.getAbsolutePath());

    }
    public String draw_3(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"syjy_1.jpg");
        Bitmap b3 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.syjy_3);
        merge(b3);
        if (!b3.isRecycled())b3.recycle();
        if (bean.getJcryxmyi()!=null)
        writeText(bean.getJcryxmyi(),4.0f,3.0f,6.0f,2.0f);
        if (bean.getZfzhyi()!=null)
        writeText(bean.getZfzhyi(),9.0f,3.0f,6.0f,2.0f);
        if (bean.getJcryxmer()!=null)
        writeText(bean.getJcryxmer(),4.0f,5.0f,6.0f,2.0f);
        if (bean.getZfzher()!=null)
        writeText(bean.getZfzher(),9.0f,5.0f,6.0f,2.0f);
        if (bean.getJcdate()!=null)
        writeText(bean.getJcdate(),24.0f,7.0f,5.0f,2.0f);

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
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(9.0f), (int) JsPxValue(7.5f), (int) JsPxValue(12.0f), (int) JsPxValue(8.5f)), paint);
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
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(9.0f), (int) JsPxValue(9.5f), (int) JsPxValue(12.0f), (int) JsPxValue(10.5f)), paint);
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
                    mCacheCanvas.drawBitmap(bitmap, null, new Rect((int) JsPxValue(24.0f), (int) JsPxValue(9.5f), (int) JsPxValue(27.0f), (int) JsPxValue(10.5f)), paint);
                    bitmap.recycle();
                }

            }
        }

     /*   writeText("负责人",9.0f,7.0f,3.0f,2.0f);


        writeText("检查1",9.0f,9.0f,3.0f,2.0f);
        writeText("检查2",24.0f,9.0f,3.0f,2.0f);

*/
        File f2=new File(FileUtil.getInstance().getCacheDirPath(),"syjy_3.jpg");
        if (f2.exists())f2.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap,f2.getAbsolutePath());

    }


    //附录图片下标集合
    private Map<String,String> fl1=new HashMap<>();
    private Map<String,String> fl2=new HashMap<>();

    //图片索引
    private int flindex1;
    private int flindex2;


    private void analysisdata() {
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        for (int i = 0; i < checkItems.size(); i++) {
            if(i<8){
                if (!checkItems.get(i).getJL().equals("是")){
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl1.put("" + i, "图1-" + (flindex1 + 1));
                            flindex1++;
                        }
                    }
                }

            }else if(i<18){
                if (checkItems.get(i).getJL()!=null&&!checkItems.get(i).getJL().equals("是")){
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl2.put(""+i,"图2-"+(flindex2+1));
                            flindex2++;
                        }
                    }
                }
            }

        }
    }
    public String getFLPath(String path) {
        mCacheCanvas.drawColor(Color.WHITE);
        Bitmap bitmap = BitmapFactory.decodeFile(path);

        int oldwidth = bitmap.getWidth();
        int oldheight = bitmap.getHeight();

        int canWidth=mCacheCanvas.getWidth();
        int canHeight=mCacheCanvas.getHeight();


        Rect src = new Rect();
        src.left = 0;
        src.top = 0;
        src.right = oldwidth;
        src.bottom = oldheight;

        RectF des = new RectF();
        des.left = 80;
        des.right = mCacheCanvas.getWidth() - 80;
        des.top = 80;
        des.bottom = canHeight-80;

        Log.e("TAG",mCacheCanvas.getWidth()+"=="+mCacheCanvas.getHeight());
        mCacheCanvas.drawBitmap(bitmap,src,des, paint);
        bitmap.recycle();
        Log.e("TAG",path.split("_")[1]);
        switch (path.split("_")[1]){
            case "P1.jpg":
                writeText(fl1.get("0"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P2.jpg":
                writeText(fl1.get("1"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P3.jpg":
                writeText(fl1.get("2"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P4.jpg":
                writeText(fl1.get("3"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P5.jpg":
                writeText(fl1.get("4"),16.5f,1.0f,4.0f,2.0f);
                break;
            case "P6.jpg":
                writeText(fl1.get("5"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P7.jpg":
                writeText(fl1.get("6"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P8.jpg":
                writeText(fl1.get("7"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P9.jpg":
                writeText(fl2.get("8"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P10.jpg":
                writeText(fl2.get("9"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P11.jpg":
                writeText(fl2.get("10"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P12.jpg":
                writeText(fl2.get("11"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P13.jpg":
                writeText(fl2.get("12"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P14.jpg":
                writeText(fl2.get("13"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P15.jpg":
                writeText(fl2.get("14"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P16.jpg":
                writeText(fl2.get("15"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P17.jpg":
                writeText(fl2.get("16"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P18.jpg":
                writeText(fl2.get("17"),16.5f,0.5f,4.0f,2.0f);
                break;
        }

        File ff=new File(FileUtil.getInstance().getCacheDirPath(),"syjy_"+numName+".jpg");
        if(ff.exists())ff.delete();
        String pt = FileUtil.getInstance().save2Local2(cacheBitmap, ff.getAbsolutePath());
        numName++;
        return pt;
    }
    private int numName=4;
}
