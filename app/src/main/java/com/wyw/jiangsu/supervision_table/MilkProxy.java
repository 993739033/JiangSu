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
public class MilkProxy extends BaseProxy {
    private final UploadCommonSupervisionBean bean;
    private List<String> paths=new ArrayList<>();

    public MilkProxy(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
        super(context,cachecanvas,cachebitmap);
        this.mContext = context;
        this.mCacheCanvas = cachecanvas;
        this.bean = t;
        this.cacheBitmap = cachebitmap;
    }
    private int numName=3;
    @Override
    public Bitmap draw(int whichPrinter) {
        return null;
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
        paths.add(draw_2());
        paths.add(draw_1());
        if (!cacheBitmap.isRecycled()){
            cacheBitmap.recycle();
            System.gc();
        }
        return FileUtil.getInstance().getPrintList("sxr");
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
        if (!bitmap.isRecycled())
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
                writeText(fl1.get("8"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P10.jpg":
                writeText(fl1.get("9"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P11.jpg":
                writeText(fl1.get("10"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P12.jpg":
                writeText(fl1.get("11"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P13.jpg":
                writeText(fl1.get("12"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P14.jpg":
                writeText(fl1.get("13"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P15.jpg":
                writeText(fl1.get("14"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P16.jpg":
                writeText(fl1.get("15"),16.5f,0.5f,4.0f,2.0f);
            case "P17.jpg":
                writeText(fl1.get("16"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P18.jpg":
                writeText(fl2.get("17"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P19.jpg":
                writeText(fl2.get("18"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P20.jpg":
                writeText(fl2.get("19"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P21.jpg":
                writeText(fl2.get("20"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P22.jpg":
                writeText(fl2.get("21"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P23.jpg":
                writeText(fl2.get("22"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P24.jpg":
                writeText(fl2.get("23"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P25.jpg":
                writeText(fl2.get("24"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;
            case "P26.jpg":
                writeText(fl2.get("25"), 16.5f, 0.5f, 4.0f, 2.0f);
                break;

        }
        File ff=new File(FileUtil.getInstance().getCacheDirPath(),"sxr_"+numName+".jpg");
        //File ff=new File(FileUtil.getInstance().getCacheDirPath(),path.split("_")[1]);
        if(ff.exists())ff.delete();
        String pt = FileUtil.getInstance().save2Local2(cacheBitmap, ff.getAbsolutePath());
        numName++;
        return pt;
    }
    public String draw_1(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"sxr_1.jpg");
        Bitmap b1 = BitmapFactory.decodeFile(file.getAbsolutePath());
       //Bitmap b1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sxr_1);
        merge(b1);
        if(!b1.isRecycled())b1.recycle();
        if (bean.getFName()!=null)
        writeText(bean.getFName(),10.0f,7.0f,5.0f,2.0f);
        if (bean.getFCityAdd()!=null)
        writeText(bean.getFCityAdd(),23.0f,7.0f,5.0f,2.0f);
        if (bean.getFLegalPerson()!=null)
        writeText(bean.getFLegalPerson(),10.0f,9.0f,5.0f,2.0f);
        if (bean.getFPhone()!=null)
        writeText(bean.getFPhone(),23.0f,9.0f,5.0f,2.0f);
        if (bean.getFCategory()!=null)
        writeText(bean.getFCategory(),10.0f,11.0f,5.0f,2.0f);
        if (bean.getClsl()!=null)
        writeText(bean.getClsl(),23.0f,11.0f,5.0f,2.0f);

        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems!=null){
            for (int i = 0; i < 17; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item9_1:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,15.4f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,15.4f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 15.4f,2.3f, 0.7f);
                        }

                       /* writeText("√",24.0f,15.4f,1.0f,1.0f);
                        writeText("√",26.0f,15.4f,5.0f,2.0f);
                        writeText("√",28.0f,15.4f,5.0f,2.0f);*/
                        break;
                    case Constance.Item9_2:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,18.4f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,18.4f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 18.4f,2.3f, 0.7f);
                        }

                        //writeText("√",24.0f,18.4f,1.0f,1.0f);
                        break;
                    case Constance.Item9_3:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,21.1f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,21.1f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 21.1f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,21.1f,1.0f,1.0f);
                        break;
                    case Constance.Item9_4:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,22.9f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,22.9f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 22.9f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,22.9f,1.0f,1.0f);
                        break;
                    case Constance.Item9_5:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,24.4f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,24.4f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 24.4f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,24.4f,1.0f,1.0f);
                        break;
                    case Constance.Item9_6:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,26.0f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,26.0f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 26.1f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,26.1f,1.0f,1.0f);
                        break;
                    case Constance.Item9_7:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,27.9f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,27.9f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 27.9f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,27.9f,1.0f,1.0f);
                        break;
                    case Constance.Item9_8:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,29.9f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,29.9f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 29.9f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,29.9f,1.0f,1.0f);
                        break;
                    case Constance.Item9_9:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,31.2f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,31.2f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 31.2f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,31.2f,1.0f,1.0f);
                        break;
                    case Constance.Item9_10:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,32.5f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,32.5f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 32.5f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,32.5f,1.0f,1.0f);
                        break;
                    case Constance.Item9_11:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,33.89f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,33.89f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 33.89f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,33.89f,1.0f,1.0f);
                        break;
                    case Constance.Item9_12:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,35.4f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,35.4f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 35.4f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,35.4f,1.0f,1.0f);
                        break;
                    case Constance.Item9_13:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,36.89f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,36.89f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 36.89f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,36.89f,1.0f,1.0f);
                        break;
                    case Constance.Item9_14:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,38.4f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,38.4f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 38.4f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,38.4f,1.0f,1.0f);
                        break;
                    case Constance.Item9_15:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,39.96f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,39.96f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 39.96f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,39.96f,1.0f,1.0f);
                        break;
                    case Constance.Item9_16:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,41.5f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,41.5f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 41.5f,2.3f, 0.7f);
                        }
                      //  writeText("√",24.0f,41.5f,1.0f,1.0f);
                        break;
                    case Constance.Item9_17:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,43.0f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,43.0f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i), 28.0f, 43.0f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,43.0f,1.0f,1.0f);
                        break;

                }
            }
        }


        File f1=new File(FileUtil.getInstance().getCacheDirPath(),"sxr_1.jpg");
        if (f1.exists())f1.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap,f1.getAbsolutePath());
    }
    public String draw_2(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"sxr_2.jpg");
        Bitmap b2 = BitmapFactory.decodeFile(file.getAbsolutePath());
       // Bitmap b2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sxr_2);
        merge(b2);
        if(!b2.isRecycled())b2.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems!=null) {
            for (int i = 17; i < 26; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item9_18:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,3.1f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,3.1f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 3.1f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,3.1f,1.0f,1.0f);
                        break;
                    case Constance.Item9_19:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,4.6f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,4.6f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 4.6f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,4.6f,1.0f,1.0f);
                        break;
                    case Constance.Item9_20:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,6.1f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,6.1f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 6.1f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,6.1f,1.0f,1.0f);
                        break;
                    case Constance.Item9_21:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,7.6f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,7.6f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 7.6f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,7.6f,1.0f,1.0f);
                        break;
                    case Constance.Item9_22:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,9.1f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,9.1f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 9.1f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,9.1f,1.0f,1.0f);
                        break;
                    case Constance.Item9_23:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,10.6f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,10.6f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 10.6f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,10.6f,1.0f,1.0f);
                        break;
                    case Constance.Item9_24:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,12.1f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,12.1f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 12.1f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,12.1f,1.0f,1.0f);
                        break;
                    case Constance.Item9_25:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,14.1f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,14.1f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 14.1f,2.3f, 0.7f);
                        }
                       // writeText("√",24.0f,14.1f,1.0f,1.0f);
                        break;
                    case Constance.Item9_26:
                        if (checkItems.get(i).getJL()!=null&&checkItems.get(i).getJL().equals("是")){
                            writeText("√",24.0f,16.1f,1.0f,1.0f);
                        }else{
                            writeText("√",26.0f,16.1f,1.0f,1.0f);
                            /*if(checkItems.get(i).getQK()!=null)
                                writeText(checkItems.get(i).getQK(),23.5f,13.9f,2.5f,1.0f);*/
                            if(fl2.get(""+i)!=null)
                                writeText(fl2.get(""+i), 28.0f, 16.1f,2.3f, 0.7f);
                        }
                        //writeText("√",24.0f,16.1f,1.0f,1.0f);
                        break;

                }
            }
        }

        //writeText("√",24.0f,3.0f,1.0f,1.0f);
        if (bean.getJcclyj()!=null)
        writeText(bean.getJcclyj(),10.0f,19.5f,9.0f,2.0f);
        if (bean.getJcryxmyi()!=null)
        writeText(bean.getJcryxmyi(),10.0f,27.5f,9.0f,2.0f);
        if (bean.getZfzhyi()!=null)
        writeText(bean.getZfzhyi(),23.0f,27.5f,9.0f,2.0f);
        if (bean.getJcryxmer()!=null)
        writeText(bean.getJcryxmer(),10.0f,29.5f,9.0f,2.0f);
        if (bean.getZfzher()!=null)
        writeText(bean.getZfzher(),23.0f,29.5f,9.0f,2.0f);
        if (bean.getJcdate()!=null)
        writeText(bean.getJcdate(),11.0f,31.5f,5.0f,2.0f);

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
                    mCacheCanvas.drawBitmap(bitmap,null,new Rect((int)JsPxValue(25.0f),(int)JsPxValue(31.5f),(int)JsPxValue(28.0f),(int)JsPxValue(32.5f)),paint);
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
                    mCacheCanvas.drawBitmap(bitmap,null,new Rect((int)JsPxValue(11.0f),(int)JsPxValue(33.0f),(int)JsPxValue(14.0f),(int)JsPxValue(34.0f)),paint);
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
                    mCacheCanvas.drawBitmap(bitmap,null,new Rect((int)JsPxValue(25.0f),(int)JsPxValue(33.0f),(int)JsPxValue(28.0f),(int)JsPxValue(34.0f)),paint);
                    bitmap.recycle();
                }

            }


        }
        File f2=new File(FileUtil.getInstance().getCacheDirPath(),"sxr_2.jpg");
        if (f2.exists())f2.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap,f2.getAbsolutePath());
    }




    private void merge(Bitmap frontBitmap) {
        cacheBitmap = BitmapUtils.mergeBitmap(cacheBitmap, frontBitmap);
        if (cacheBitmap != null) mCacheCanvas = new Canvas(cacheBitmap);
    }

    //附录图片下标集合
    private Map<String, String> fl1 = new HashMap<>();
    private Map<String, String> fl2 = new HashMap<>();

    //图片索引
    private int flindex1;
    private int flindex2;

    private int maxnum;
    /**
     * 分析数据，附录图片数量与说明
     */
    private void analysisdata() {
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        for (int i = 0; i < checkItems.size(); i++) {
            if (i < 17) {
                if (!checkItems.get(i).getJL().equals("是")) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl1.put("" + i, "图1-" + (flindex1 + 1));
                            flindex1++;
                        }
                    }
                }

            } else if (i < 26) {
                if (!checkItems.get(i).getJL().equals("是")) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl2.put("" + i, "图2-" + (flindex2 + 1));
                            flindex2++;
                        }
                    }
                }

            }


        }
        maxnum=fl1.size()+fl2.size()+2;



    }


}
