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
 * Created by HUANG on 2017/7/20.
 */
public class ZhenLiaoProxy extends BaseProxy {
    private final UploadCommonSupervisionBean bean;
    private List<String> paths=new ArrayList<>();

    public ZhenLiaoProxy(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
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


        if (cacheBitmap!=null&&!cacheBitmap.isRecycled()) {
            cacheBitmap.recycle();
            System.gc();
        }
        return FileUtil.getInstance().getPrintList("zl");
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
            if(i<5){
                if (!checkItems.get(i).getJL().equals("是")){
                    if(checkItems.get(i).getTp()!=null&&!checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl1.put("" + i, "图1-" + (flindex1 + 1));
                            flindex1++;
                        }
                    }
                }

            }else if(i<13){
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
    public String draw_3(){
        //第三张
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"dwzlcs_3.jpg");
        Bitmap b3 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.dwzlcs_3);
        merge(b3);
        b3.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if(checkItems.get(13).getName()!=null)
            writeText(checkItems.get(13).getName(),7.5f,4.0f,7.0f,2.0f);
        if(bean.getJcclyj()!=null)
            writeText(bean.getJcclyj(),11.0f,6.5f,5.0f,2.0f);
        if (bean.getJcryxmyi()!=null)
            writeText(bean.getJcryxmyi(),10.5f,13.0f,7.0f,2.0f);
        if (bean.getJcryxmer()!=null)
            writeText(bean.getJcryxmer(),10.5f,15.0f,7.0f,2.0f);
        if(bean.getZfzhyi()!=null)
            writeText(bean.getZfzhyi(),20.5f,13.0f,7.0f,2.0f);
        if(bean.getZfzher()!=null)
            writeText(bean.getZfzher(),20.5f,15.0f,7.0f,2.0f);
        if(bean.getJcdate()!=null)
            writeText(bean.getJcdate(),10.5f,17.5f,4.0f,2.0f);

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
                    mCacheCanvas.drawBitmap(bf1,null,new Rect((int)JsPxValue(24.0f),(int)JsPxValue(17.5f),(int)JsPxValue(26.5f),(int)JsPxValue(18.5f)),paint);
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
                    mCacheCanvas.drawBitmap(bf2,null,new Rect((int)JsPxValue(11.0f),(int)JsPxValue(20.0f),(int)JsPxValue(13.5f),(int)JsPxValue(21.0f)),paint);
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
                    mCacheCanvas.drawBitmap(bf3,null,new Rect((int)JsPxValue(24.0f),(int)JsPxValue(20.0f),(int)JsPxValue(26.5f),(int)JsPxValue(21.0f)),paint);
                    bf3.recycle();
                }

            }


        }














      /*  writeText("负责人",23.0f,17.5f,3.0f,2.0f);
        writeText("检查人员1",10.5f,20.0f,3.0f,2.0f);
        writeText("检查人员2",23.0f,20.0f,3.0f,2.0f);
*/

        File f3=new File(FileUtil.getInstance().getCacheDirPath(),"zl_3.jpg");
        if (f3.exists())f3.delete();
       return FileUtil.getInstance().save2Local2(cacheBitmap, f3.getAbsolutePath());


    }
    public String draw_2(){
        //第二张
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"dwzlcs_2.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
       // Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.dwzlcs_2);
        merge(bitmap);
        bitmap.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if(checkItems!=null){
            for (int i=0;i<checkItems.size();i++){
                switch (checkItems.get(i).getName()){
                    case Constance.Item3_6:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,7.5f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,7.5f,1.0f,1.0f);
                            List<CheckContentEntity.DataEntity> list = checkItems.get(i).getList();

                            for (int n=0;n<list.size();n++){
                                CheckContentEntity.DataEntity dataEntity = list.get(n);
                                switch (n){
                                    case 0:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 3.6f,1.0f, 1.0f);
                                        break;
                                    case 1:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 5.6f,1.0f, 1.0f);
                                        break;
                                    case 2:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 9.4f,1.0f, 1.0f);
                                        break;
                                }

                            }
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 7.5f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_7:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,13.67f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,13.67f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 13.67f,3.5f, 1.5f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 13.67f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_8:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,22.0f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,22.0f,1.0f,1.0f);
                            List<CheckContentEntity.DataEntity> list = checkItems.get(i).getList();

                            for (int n=0;n<list.size();n++){
                                CheckContentEntity.DataEntity dataEntity = list.get(n);
                                switch (n){
                                    case 0:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 16.0f,1.0f, 1.0f);
                                        break;
                                    case 1:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 17.9f,1.0f, 1.0f);
                                        break;
                                    case 2:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 19.5f,1.0f, 1.0f);
                                        break;
                                    case 3:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 21.5f,1.0f, 1.0f);
                                        break;
                                    case 4:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 24.4f,1.0f, 1.0f);
                                        break;
                                }

                            }
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 24.4f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_9:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,30.0f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,30.0f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 30.0f,3.5f, 1.5f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 30.0f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_10:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,32.47f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,32.47f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 32.47f,3.5f, 1.5f);
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 32.47f,2.3f, 1.0f);
                        }
                        break;
                    case Constance.Item3_11:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,34.8f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,34.8f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 34.8f,3.5f, 1.5f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 34.8f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_12:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,39.0f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,39.0f,1.0f,1.0f);

                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 39.0f,3.5f, 1.5f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 39.0f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_13:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",18.7f,43.3f,1.0f,1.0f);

                        }else{
                            writeText("√",20.55f,43.3f,1.0f,1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 43.3f,3.5f, 1.5f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl2.get(""+i), 25.9f, 43.3f,2.3f, 0.7f);
                        }
                        break;
                }
            }
        }






       /* //6
        writeText("√",18.7f,7.5f,1.0f,1.0f);
        writeText("√",20.55f,7.5f,1.0f,1.0f);
        writeText("图1-1", 25.9f, 7.5f,2.3f, 1.0f);

        writeText("√", 22.5f, 3.6f,1.0f, 1.0f);
        writeText("√", 22.5f, 5.6f,1.0f, 1.0f);
        writeText("√", 22.5f, 9.4f,1.0f, 1.0f);
        //7
        writeText("√",18.7f,13.67f,1.0f,1.0f);
        writeText("√",20.55f,13.67f,1.0f,1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 13.67f,3.5f, 1.5f);
        writeText("图1-1", 25.9f, 13.67f,2.3f, 1.0f);
        //8
        writeText("√",18.7f,22.0f,1.0f,1.0f);
        writeText("√",20.55f,22.0f,1.0f,1.0f);
        writeText("图1-1", 25.9f, 22.0f,2.3f, 1.0f);

        writeText("√", 22.5f, 16.0f,1.0f, 1.0f);
        writeText("√", 22.5f, 17.9f,1.0f, 1.0f);
        writeText("√", 22.5f, 19.5f,1.0f, 1.0f);
        writeText("√", 22.5f, 21.5f,1.0f, 1.0f);
        writeText("√", 22.5f, 24.4f,1.0f, 1.0f);

        //9
        writeText("√",18.7f,30.0f,1.0f,1.0f);
        writeText("√",20.55f,30.0f,1.0f,1.0f);
        writeText("图1-1", 25.9f, 30.0f,2.3f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 30.0f,3.5f, 1.5f);
        //10
        writeText("√",18.7f,32.47f,1.0f,1.0f);
        writeText("√",20.55f,32.47f,1.0f,1.0f);
        writeText("图1-1", 25.9f, 32.47f,2.3f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 32.47f,3.5f, 1.5f);
        //11
        writeText("√",18.7f,34.8f,1.0f,1.0f);
        writeText("√",20.55f,34.8f,1.0f,1.0f);
        writeText("图1-1", 25.9f, 34.8f,2.3f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 34.8f,3.5f, 1.5f);
        //12
        writeText("√",18.7f,39.0f,1.0f,1.0f);
        writeText("√",20.55f,39.0f,1.0f,1.0f);
        writeText("图1-1", 25.9f, 39.0f,2.3f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 39.0f,3.5f, 1.5f);
        //13
        writeText("√",18.7f,43.3f,1.0f,1.0f);
        writeText("√",20.55f,43.3f,1.0f,1.0f);
        writeText("图1-1", 25.9f, 43.3f,2.3f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 43.3f,3.5f, 1.5f);
*/

        File f2=new File(FileUtil.getInstance().getCacheDirPath(),"zl_2.jpg");
        if (f2.exists())f2.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, f2.getAbsolutePath());

    }
    public String draw_1(){

        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"dwzlcs_1.jpg");
        Bitmap bt1 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap bt1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.dwzlcs_1);
        merge(bt1);
        bt1.recycle();
        //27*2=54
        if (bean.getFName() == null) {
            writeText("场所名称===", 10.0f, 7.7f, 7.8f, 0.7f);
        } else {
            writeText(bean.getFName(), 10.0f, 7.7f, 7.8f, 0.7f);
        }
        if (bean.getFLegalPerson() == null) {
            writeText("负责人", 10.0f, 9.7f, 7.8f, 0.7f);
        } else {
            writeText(bean.getFLegalPerson(), 10.0f, 9.7f, 7.8f, 0.7f);
        }
        if (bean.getFCategory() == null) {
            writeText("诊疗动物种类", 10.0f, 11.75f, 7.8f, 0.7f);
        } else {
            writeText(bean.getFCategory(), 10.0f, 11.75f, 7.8f, 0.7f);
        }
        if (bean.getFCityAdd() == null) {
            writeText("地址", 25.9f, 7.7f, 4.7f, 0.7f);
        } else {
            writeText(bean.getFCityAdd(), 25.9f, 7.7f, 4.7f, 0.7f);
        }
        if (bean.getFPhone() == null) {
            writeText("电话", 25.9f, 9.7f, 4.7f, 0.7f);
        } else {
            writeText(bean.getFPhone(), 25.9f, 9.7f, 4.7f, 0.7f);
        }
        if (bean.getClsl() == null) {
            writeText("每日诊疗数量", 25.9f, 11.75f, 4.7f, 0.7f);
        } else {
            writeText(bean.getClsl(), 25.9f, 11.75f, 4.7f, 0.7f);
        }

        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if(checkItems!=null){
            for (int i=0;i<5;i++){
                switch (checkItems.get(i).getName()){
                    case Constance.Item3_1:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.7f, 16.3f, 1.0f, 1.0f);
                        }else{
                            writeText("√", 20.55f, 16.3f, 1.0f, 1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 15.5f,2.3f, 1.5f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 25.9f, 16.5f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_2:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.7f, 19.6f, 1.0f, 1.0f);
                        }else{
                            writeText("√", 20.55f, 19.6f, 1.0f, 1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 18.5f,3.5f, 1.5f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 25.9f, 19.5f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_3:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.7f, 23.0f, 1.0f, 1.0f);
                        }else{
                            writeText("√", 20.55f, 23.0f, 1.0f, 1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 22.0f,3.5f, 1.5f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 25.9f, 23.0f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_4:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.7f, 26.2f, 1.0f, 1.0f);
                        }else{
                            writeText("√", 20.55f, 26.2f, 1.0f, 1.0f);
                            if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(), 21.9f, 22.0f,3.5f, 1.5f);
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 25.9f, 26.2f,2.3f, 0.7f);
                        }
                        break;
                    case Constance.Item3_5:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√", 18.7f, 35.5f, 1.0f, 1.0f);
                        }else{
                            writeText("√", 20.55f, 35.5f, 1.0f, 1.0f);

                            List<CheckContentEntity.DataEntity> list = checkItems.get(i).getList();

                            for (int n=0;n<list.size();n++){
                                CheckContentEntity.DataEntity dataEntity = list.get(n);
                                switch (n){
                                    case 0:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 28.7f,1.0f, 1.0f);
                                        break;
                                    case 1:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 31.5f,1.0f, 1.0f);
                                        break;
                                    case 2:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 35.1f,1.0f, 1.0f);
                                        break;
                                    case 3:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 38.0f,1.0f, 1.0f);
                                        break;
                                    case 4:
                                        if (dataEntity.isCheek())
                                            writeText("√", 22.5f, 40.8f,1.0f, 1.0f);
                                        break;

                                }


                            }




                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 25.9f, 35.5f,2.3f, 1.0f);
                        }
                        break;
                }
            }
        }

      /*  //资质1
        writeText("√", 18.7f, 16.3f, 1.0f, 1.0f);
        writeText("√", 20.55f, 16.3f, 1.0f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 15.5f,2.3f, 1.5f);
        writeText("资料", 25.9f, 16.5f,2.3f, 0.7f);

        //资质2
        writeText("√", 18.7f, 19.6f, 1.0f, 1.0f);
        writeText("√", 20.55f, 19.6f, 1.0f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 18.5f,3.5f, 1.5f);
        writeText("图1-1", 25.9f, 19.5f,2.3f, 0.7f);
        //人员1
        writeText("√", 18.7f, 23.0f, 1.0f, 1.0f);
        writeText("√", 20.55f, 23.0f, 1.0f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 22.0f,3.5f, 1.5f);
        writeText("图1-1", 25.9f, 23.0f,2.3f, 0.7f);
        //人员2
        writeText("√", 18.7f, 26.2f, 1.0f, 1.0f);
        writeText("√", 20.55f, 26.2f, 1.0f, 1.0f);
        writeText("存在问题fdfsdfdfsdgdssd", 21.9f, 25.2f,3.5f, 1.5f);
        writeText("图1-1", 25.9f, 26.2f,2.3f, 0.7f);

        //选址与布局
        writeText("√", 18.7f, 35.5f, 1.0f, 1.0f);
        writeText("√", 20.55f, 35.5f, 1.0f, 1.0f);

        writeText("√", 22.5f, 28.7f,1.0f, 1.0f);
        writeText("√", 22.5f, 31.5f,1.0f, 1.0f);
        writeText("√", 22.5f, 35.1f,1.0f, 1.0f);
        writeText("√", 22.5f, 38.0f,1.0f, 1.0f);
        writeText("√", 22.5f, 40.8f,1.0f, 1.0f);

        writeText("图1-1", 25.9f, 35.5f,2.3f, 1.0f);*/


        File f1 = new File(FileUtil.getInstance().getCacheDirPath(), "zl_1.jpg");
        if (f1.exists()) f1.delete();
      return FileUtil.getInstance().save2Local2(cacheBitmap, f1.getAbsolutePath());

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
                writeText(fl2.get("5"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P7.jpg":
                writeText(fl2.get("6"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P8.jpg":
                writeText(fl2.get("7"),16.5f,0.5f,4.0f,2.0f);
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
        }

        File ff=new File(FileUtil.getInstance().getCacheDirPath(),"zl_"+numName+".jpg");
        if(ff.exists())ff.delete();
        String pt = FileUtil.getInstance().save2Local2(cacheBitmap, ff.getAbsolutePath());
        numName++;
        return pt;
    }
    private int numName=4;
}
