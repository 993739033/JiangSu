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
 * Created by HUANG on 2017/7/21.
 */
public class WuHaiHuaProxy extends BaseProxy {
    private final UploadCommonSupervisionBean bean;
    private List<String> paths=new ArrayList<>();

    public WuHaiHuaProxy(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
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
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"wuhaihua_1.jpg");
        Bitmap b1 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.wuhaihua_1);
        merge(b1);
        if (!b1.isRecycled())
            b1.recycle();
        if (bean.getFName() == null) {
            writeText("", 9.0f, 7.05f, 7.8f, 0.7f);
        } else {
            writeText(bean.getFName(), 9.0f, 7.05f, 7.8f, 0.7f);
        }
        if (bean.getFLegalPerson() == null) {
            writeText("", 9.0f, 9.05f, 7.5f, 0.7f);
        } else {
            writeText(bean.getFLegalPerson(), 9.0f, 9.05f, 7.8f, 0.7f);
        }
        if (bean.getFCategory() == null) {
            writeText("", 9.0f, 11.70f, 7.8f, 0.7f);
        } else {
            writeText(bean.getFCategory(), 9.0f, 11.70f, 7.8f, 0.7f);
        }
        if (bean.getFCityAdd() == null) {
            writeText("", 26.0f, 7.05f, 4.7f, 0.7f);
        } else {
            writeText(bean.getFCityAdd(), 26.0f, 7.05f, 4.7f, 0.7f);
        }
        if (bean.getFPhone() == null) {
            writeText("", 26.0f, 9.05f, 4.7f, 0.7f);
        } else {
            writeText(bean.getFPhone(), 26.0f, 9.05f, 4.7f, 0.7f);
        }
        if (bean.getClsl() == null) {
            writeText("", 26.0f, 11.70f, 4.7f, 0.7f);
        } else {
            writeText(bean.getClsl(), 26.0f, 11.70f, 4.7f, 0.7f);
        }
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if(checkItems!=null){
            for (int i=0;i<3;i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item4_1:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.15f, 15.7f, 1.0f, 1.0f);
                        }else{
                            writeText("√", 20.0f, 15.7f, 1.0f, 1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,15.7f,2.5f,1.0f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 27.0f, 15.7f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_2:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.15f, 18.7f, 1.0f, 1.0f);

                        }else{
                            writeText("√", 20.0f, 18.7f, 1.0f, 1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,18.7f,2.5f,1.0f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 27.0f, 18.7f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_3:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.15f, 32.2f, 1.0f, 1.0f);

                        }else{
                            writeText("√", 20.0f, 32.2f, 1.0f, 1.0f);
                            List<CheckContentEntity.DataEntity> list = checkItems.get(i).getList();

                            for (int n=0;n<list.size();n++){
                                CheckContentEntity.DataEntity dataEntity = list.get(n);
                                switch (n){
                                    case 0:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,21.3f,1.0f,1.0f);
                                        break;
                                    case 1:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,26.2f,1.0f,1.0f);
                                        break;
                                    case 2:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,31.2f,1.0f,1.0f);
                                        break;
                                    case 3:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,35.2f,1.0f,1.0f);
                                        break;
                                    case 4:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,38.97f,1.0f,1.0f);
                                        break;
                                }

                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),27.0f,32.2f,3.0f,1.0f);
                        }
                        break;
                }
            }
        }
       /* //资质1
        writeText("√", 18.15f, 15.7f, 1.0f, 1.0f);
        writeText("√", 20.0f, 15.7f, 1.0f, 1.0f);
        writeText("存在问题",23.0f,15.7f,2.5f,1.0f);
        writeText("存在问题",27.0f,15.7f,3.0f,1.0f);
        //资质2
        writeText("√", 18.15f, 18.7f, 1.0f, 1.0f);
        writeText("√", 20.0f, 18.7f, 1.0f, 1.0f);
        writeText("存在问题",23.0f,18.7f,2.5f,1.0f);
        writeText("存在问题",27.0f,18.7f,3.0f,1.0f);

        //选址与布局
        writeText("√", 18.15f, 32.2f, 1.0f, 1.0f);
        writeText("√", 20.0f, 32.2f, 1.0f, 1.0f);
        writeText("存在问题",27.0f,32.2f,3.0f,1.0f);
        writeText("√",23.5f,21.3f,1.0f,1.0f);
        writeText("√",23.5f,26.2f,1.0f,1.0f);
        writeText("√",23.5f,31.2f,1.0f,1.0f);
        writeText("√",23.5f,35.2f,1.0f,1.0f);
        writeText("√",23.5f,38.97f,1.0f,1.0f);*/


        File f1 = new File(FileUtil.getInstance().getCacheDirPath(), "whh_1.jpg");
        if (f1.exists()) f1.delete();
       return FileUtil.getInstance().save2Local2(cacheBitmap, f1.getAbsolutePath());

    }
    public String draw_2(){
        //第二张
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"wuhaihua_2.jpg");
        Bitmap b2 = BitmapFactory.decodeFile(file.getAbsolutePath());
       // Bitmap b2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.wuhaihua_2);
        merge(b2);
        if(!b2.isRecycled())b2.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if(checkItems!=null){
            for (int i=2;i<8;i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item4_3:
                        if (checkItems.get(i).getJL()!=null&&!checkItems.get(i).getJL().equals("是")){
                            List<CheckContentEntity.DataEntity> list = checkItems.get(i).getList();
                            for (int n=0;n<list.size();n++){
                                CheckContentEntity.DataEntity dataEntity = list.get(n);
                                switch (n){
                                    case 5:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,3.15f,1.0f,1.0f);
                                        break;
                                    case 6:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,7.8f,1.0f,1.0f);
                                        break;
                                    case 7:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,15.9f,1.0f,1.0f);
                                        break;
                                }

                            }

                        }
                        break;
                    case Constance.Item4_4:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,23.0f,1.0f,1.0f);

                        }else{
                            writeText("√",19.95f,23.0f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,23.0f,2.5f,1.0f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 27.0f, 23.0f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_5:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,27.7f,1.0f,1.0f);

                        }else{
                            writeText("√",19.95f,27.7f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,27.7f,2.5f,1.0f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 27.0f, 27.7f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_6:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,33.5f,1.0f,1.0f);

                        }else{
                            writeText("√",19.95f,33.5f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,33.5f,2.5f,1.0f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 27.0f, 33.5f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_7:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,37.25f,1.0f,1.0f);

                        }else{
                            writeText("√",19.95f,37.25f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,37.25f,2.5f,1.0f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 27.0f, 37.25f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_8:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,41.94f,1.0f,1.0f);

                        }else{
                            writeText("√",19.95f,41.94f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,41.94f,2.5f,1.0f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 27.0f, 41.94f,2.3f, 0.7f);
                        }
                        break;
                }
            }
        }
       /* //3
        writeText("√",23.5f,3.15f,1.0f,1.0f);
        writeText("√",23.5f,7.8f,1.0f,1.0f);
        writeText("√",23.5f,15.9f,1.0f,1.0f);
        //4
        writeText("√",18.10f,23.0f,1.0f,1.0f);
        writeText("√",19.95f,23.0f,1.0f,1.0f);
        //5
        writeText("√",18.10f,27.7f,1.0f,1.0f);
        writeText("√",19.95f,27.7f,1.0f,1.0f);
        //6
        writeText("√",18.10f,33.5f,1.0f,1.0f);
        writeText("√",19.95f,33.5f,1.0f,1.0f);
        //7
        writeText("√",18.10f,37.25f,1.0f,1.0f);
        writeText("√",19.95f,37.25f,1.0f,1.0f);
        //8
        writeText("√",18.10f,41.97f,1.0f,1.0f);
        writeText("√",19.95f,41.97f,1.0f,1.0f);*/


        File f2 = new File(FileUtil.getInstance().getCacheDirPath(), "whh_2.jpg");
        if (f2.exists()) f2.delete();
       return  FileUtil.getInstance().save2Local2(cacheBitmap, f2.getAbsolutePath());

    }
    public String draw_3(){
        //第三张
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"wuhaihua_3.jpg");
        Bitmap b3 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.wuhaihua3);
        merge(b3);
        if(!b3.isRecycled())b3.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if(checkItems!=null) {
            for (int i = 8; i < 14; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item4_9:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,3.6f,1.0f,1.0f);
                        }else{
                            writeText("√",19.95f,3.6f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,3.6f,2.5f,1.0f);
                            if(fl3.get(""+i)!=null)
                                writeText(fl3.get(""+i), 27.0f, 3.6f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_10:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,16.7f,1.0f,1.0f);


                        }else{
                            writeText("√",19.95f,16.7f,1.0f,1.0f);
                            List<CheckContentEntity.DataEntity> list = checkItems.get(i).getList();
                            for (int n=0;n<list.size();n++){
                                CheckContentEntity.DataEntity dataEntity = list.get(n);
                                switch (n){
                                    case 0:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,5.9f,1.0f,1.0f);
                                        break;
                                    case 1:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,11.96f,1.0f,1.0f);
                                        break;
                                    case 2:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,14.9f,1.0f,1.0f);
                                        break;
                                    case 3:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,18.65f,1.0f,1.0f);
                                        break;
                                    case 4:
                                        if (dataEntity.isCheek())
                                            writeText("√",23.5f,24.67f,1.0f,1.0f);
                                        break;
                                }

                            }
                            if(fl3.get(""+i)!=null)
                                writeText(fl3.get(""+i),27.0f,16.7f,3.0f,1.0f);
                        }
                        break;
                    case Constance.Item4_11:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,29.5f,1.0f,1.0f);

                        }else{
                            writeText("√",19.95f,29.5f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,29.5f,2.5f,1.0f);
                            if(fl3.get(""+i)!=null)
                                writeText(fl3.get(""+i), 27.0f, 29.5f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_12:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,33.9f,1.0f,1.0f);

                        }else{
                            writeText("√",19.95f,33.9f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,33.9f,2.5f,1.0f);
                            if(fl3.get(""+i)!=null)
                                writeText(fl3.get(""+i), 27.0f, 33.9f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_13:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,39.3f,1.0f,1.0f);


                        }else{
                            writeText("√",19.95f,39.3f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,39.3f,2.5f,1.0f);
                            if(fl3.get(""+i)!=null)
                                writeText(fl3.get(""+i), 27.0f, 39.3f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item4_14:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.10f,43.97f,1.0f,1.0f);
                        }else{
                            writeText("√",19.95f,43.97f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.0f,43.97f,2.5f,1.0f);
                            if(fl3.get(""+i)!=null)
                                writeText(fl3.get(""+i), 27.0f, 43.97f,2.3f, 0.7f);
                        }
                        break;


                }
            }
        }

/*
        //9
        writeText("√",18.10f,3.6f,1.0f,1.0f);
        writeText("√",19.95f,3.6f,1.0f,1.0f);
        //10
        writeText("√",18.10f,16.7f,1.0f,1.0f);
        writeText("√",19.95f,16.7f,1.0f,1.0f);

        writeText("√",23.5f,5.9f,1.0f,1.0f);
        writeText("√",23.5f,11.96f,1.0f,1.0f);
        writeText("√",23.5f,14.9f,1.0f,1.0f);
        writeText("√",23.5f,18.65f,1.0f,1.0f);
        writeText("√",23.5f,24.67f,1.0f,1.0f);

        //11
        writeText("√",18.10f,29.5f,1.0f,1.0f);
        writeText("√",19.95f,29.5f,1.0f,1.0f);
        //12
        writeText("√",18.10f,33.9f,1.0f,1.0f);
        writeText("√",19.95f,33.9f,1.0f,1.0f);
        //13
        writeText("√",18.10f,39.3f,1.0f,1.0f);
        writeText("√",19.95f,39.3f,1.0f,1.0f);
        //14
        writeText("√",18.10f,43.97f,1.0f,1.0f);
        writeText("√",19.95f,43.97f,1.0f,1.0f);*/

        File f3 = new File(FileUtil.getInstance().getCacheDirPath(), "whh_3.jpg");
        if (f3.exists()) f3.delete();
        return  FileUtil.getInstance().save2Local2(cacheBitmap, f3.getAbsolutePath());

    }
    public String draw_4(){
        //第四张
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"wuhaihua_4.jpg");
        Bitmap b4 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b4 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.wuhaihua4);
        merge(b4);
        if (!b4.isRecycled())b4.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if(checkItems.get(14).getJL().equals("是"))
            writeText("√",18.13f,5.75f,1.0f,1.0f);
        else {
            writeText("√", 19.96f, 5.75f, 1.0f, 1.0f);
            if(checkItems.get(14).getQK()!=null)
                writeText(checkItems.get(14).getQK(),23.0f,5.75f,2.5f,1.0f);
            if(fl4.get(""+14)!=null)
                writeText(fl4.get(""+14), 27.0f, 5.75f,2.3f, 0.7f);
        }

        if(checkItems.get(15).getName()!=null)
            writeText(checkItems.get(15).getName(),9.0f,9.5f,12.0f,2.0f);
        if(bean.getJcclyj()!=null)
            writeText(bean.getJcclyj(),9.0f,13.0f,12.0f,2.0f);
        if (bean.getJcryxmyi()!=null)
            writeText(bean.getJcryxmyi(),9.0f,23.0f,5.0f,2.0f);
        if (bean.getJcryxmer()!=null)
            writeText(bean.getJcryxmer(),9.0f,25.0f,5.0f,2.0f);
        if(bean.getZfzhyi()!=null)
            writeText(bean.getZfzhyi(),19.96f,23.0f,6.0f,2.0f);
        if(bean.getZfzher()!=null)
            writeText(bean.getZfzher(),19.96f,25.0f,6.0f,2.0f);
        if(bean.getJcdate()!=null)
            writeText(bean.getJcdate(),9.0f,27.5f,5.0f,2.0f);

        File commonSupervisionDir = PhotoViewModel.getCommonSupervisionDir();
        if(commonSupervisionDir.exists()) {
            List<UploadCommonSupervisionBean.SignPicNames> signPicNames = bean.getSignPicNames();
            //负责人
            UploadCommonSupervisionBean.SignPicNames signPicNames1 = signPicNames.get(3);
            if(signPicNames1!=null){
                String qz = signPicNames1.getQz();
                File ff1=new File(commonSupervisionDir,qz);
                if(ff1.exists()){
                    Bitmap bf1 = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bf1,null,new Rect((int)JsPxValue(23.0f),(int)JsPxValue(27.0f),(int)JsPxValue(26.5f),(int)JsPxValue(28.0f)),paint);
                    bf1.recycle();
                }

            }
            //检察人员1
            UploadCommonSupervisionBean.SignPicNames signPicNames2 = signPicNames.get(1);
            if(signPicNames1!=null){
                String qz = signPicNames2.getQz();
                File ff1=new File(commonSupervisionDir,qz);
                if(ff1.exists()){
                    Bitmap bf2 = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bf2,null,new Rect((int)JsPxValue(9.0f),(int)JsPxValue(29.5f),(int)JsPxValue(12.5f),(int)JsPxValue(30.5f)),paint);
                    bf2.recycle();
                }

            }
            //检察人员2
            UploadCommonSupervisionBean.SignPicNames signPicNames3 = signPicNames.get(2);
            if(signPicNames1!=null){
                String qz = signPicNames3.getQz();
                File ff1=new File(commonSupervisionDir,qz);
                if(ff1.exists()){
                    Bitmap bf3 = BitmapFactory.decodeFile(ff1.getAbsolutePath());
                    mCacheCanvas.drawBitmap(bf3,null,new Rect((int)JsPxValue(23.0f),(int)JsPxValue(29.5f),(int)JsPxValue(26.0f),(int)JsPxValue(30.5f)),paint);
                    bf3.recycle();
                }

            }


        }



/*

        writeText("其他",9.0f,9.5f,1.0f,1.0f);
        writeText("意见",9.0f,13.0f,1.0f,1.0f);


        writeText("姓名1",9.0f,23.0f,1.0f,1.0f);
        writeText("√",19.96f,23.0f,1.0f,1.0f);
        writeText("姓名2",9.0f,25.0f,1.0f,1.0f);
        writeText("√",19.96f,25.0f,1.0f,1.0f);

        writeText("日期",9.0f,27.5f,1.0f,1.0f);
        writeText("负责人",23.0f,27.5f,1.0f,1.0f);
        writeText("检查1",9.0f,30.0f,1.0f,1.0f);
        writeText("检查2",23.0f,30.0f,1.0f,1.0f);


*/



        File f4 = new File(FileUtil.getInstance().getCacheDirPath(), "whh_4.jpg");
        if (f4.exists()) f4.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, f4.getAbsolutePath());

    }
    @Override
    public List<String> draws() {
        analysisdata();


        List<CheckContentEntity> checkItems = bean.getCheckItems();

        if (checkItems != null) {
            for (int i = checkItems.size()-1; i >=0; i--) {
                if(checkItems.get(i)!=null) {
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            paths.add(getFLPath(pf1.getAbsolutePath()));
                        }
                    }
                }
            }
        }

        paths.add(draw_1());
        paths.add(draw_2());
        paths.add(draw_3());
        paths.add(draw_4());
        if(!cacheBitmap.isRecycled()){
            cacheBitmap.recycle();
            System.gc();
        }


        return FileUtil.getInstance().getPrintList("whh");
    }
    //附录图片下标集合
    private Map<String,String> fl1=new HashMap<>();
    private Map<String,String> fl2=new HashMap<>();
    private Map<String,String> fl3=new HashMap<>();
    private Map<String,String> fl4=new HashMap<>();
    //图片索引
    private int flindex1;
    private int flindex2;
    private int flindex3;
    private int flindex4;

    private void analysisdata() {
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        for (int i = 0; i < checkItems.size(); i++) {
            if(i<3){
                if (!checkItems.get(i).getJL().equals("是")){
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl1.put("" + i, "图1-" + (flindex1 + 1));
                            flindex1++;
                        }
                    }
                }

            }else if(i<8){
                if (checkItems.get(i).getJL()!=null&&!checkItems.get(i).getJL().equals("是")){
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl2.put(""+i,"图2-"+(flindex2+1));
                            flindex2++;
                        }
                    }
                }
            }else if (i<14){
                if (checkItems.get(i).getJL()!=null&&!checkItems.get(i).getJL().equals("是")){
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl3.put(""+i,"图3-"+(flindex3+1));
                            flindex3++;
                        }
                    }
                }
            }else{
                if (checkItems.get(i).getJL()!=null&&!checkItems.get(i).getJL().equals("是")){
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl4.put(""+i,"图4-"+(flindex4+1));
                            flindex4++;
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
                writeText(fl2.get("3"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P5.jpg":
                writeText(fl2.get("4"),16.5f,1.0f,4.0f,2.0f);
                break;
            case "P6.jpg":
                writeText(fl2.get("5"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P7.jpg":
                writeText(fl2.get("6"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P8.jpg":
                writeText(fl2.get("7"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P9.jpg":
                writeText(fl3.get("8"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P10.jpg":
                writeText(fl3.get("9"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P11.jpg":
                writeText(fl3.get("10"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P12.jpg":
                writeText(fl3.get("11"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P13.jpg":
                writeText(fl3.get("12"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P14.jpg":
                writeText(fl3.get("13"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P15.jpg":
                writeText(fl4.get("14"),16.5f,0.5f,4.0f,2.0f);
                break;

        }

        File ff=new File(FileUtil.getInstance().getCacheDirPath(),"whh_"+numName+".jpg");
        if(ff.exists())ff.delete();
        String pt = FileUtil.getInstance().save2Local2(cacheBitmap, ff.getAbsolutePath());
        numName++;
        return pt;
    }
    private int numName=5;
}
