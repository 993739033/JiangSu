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
 * Created by HUANG on 2017/7/24.
 */
public class VDrugProductionProxy extends BaseProxy {

    private final UploadCommonSupervisionBean bean;
    private List<String> paths=new ArrayList<>();

    public VDrugProductionProxy(Context context, Canvas cachecanvas, UploadCommonSupervisionBean t, Bitmap cachebitmap) {
        super(context,cachecanvas,cachebitmap);
        this.mContext = context;
        this.mCacheCanvas = cachecanvas;
        this.bean = t;
        this.cacheBitmap = cachebitmap;
    }
    private void merge(Bitmap frontBitmap) {
        cacheBitmap = BitmapUtils.mergeBitmap(cacheBitmap, frontBitmap);
        if (cacheBitmap != null) mCacheCanvas = new Canvas(cacheBitmap);
    }

    @Override
    public Bitmap draw(int whichPrinter) {
        return null;
    }
    private  String draw_2(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"sysc_2.jpg");
        Bitmap b2 = BitmapFactory.decodeFile(file.getAbsolutePath());
       // Bitmap b2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sysc_2);
        merge(b2);
        if(!b2.isRecycled())b2.recycle();
        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 9; i < checkItems.size(); i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item7_10:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,2.9f,1.0f,1.0f);

                        } else {
                            writeText("√", 21.75f, 2.9f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.75f, 2.9f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.75f, 2.9f, 2.0f, 1.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 25.75f, 2.9f, 2.0f, 1.0f);
                        }
                        break;
                    case Constance.Item7_11:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,4.9f,1.0f,1.0f);

                        } else {
                            writeText("√", 21.75f, 4.9f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.75f, 4.9f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.75f, 4.9f, 2.0f, 1.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 25.75f, 4.9f, 2.0f, 1.0f);
                        }
                        break;
                    case Constance.Item7_12:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,7.23f,1.0f,1.0f);

                        } else {
                            writeText("√", 21.75f, 7.23f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.75f, 7.23f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.75f, 7.23f, 2.0f, 1.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 25.75f, 7.23f, 2.0f, 1.0f);
                        }
                        break;
                    case Constance.Item7_13:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,9.6f,1.0f,1.0f);

                        } else {
                            writeText("√", 21.75f, 9.6f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.75f, 9.6f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.75f, 9.6f, 2.0f, 1.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 25.75f, 9.6f, 2.0f, 1.0f);
                        }
                        break;
                    case Constance.Item7_14:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,12.2f,1.0f,1.0f);

                        } else {
                            writeText("√", 21.75f, 12.2f, 1.0f, 1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("", 23.75f, 12.2f, 2.0f, 1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(), 23.75f, 12.2f, 2.0f, 1.0f);
                            }
                            if (fl2.get("" + i) != null)
                                writeText(fl2.get("" + i), 25.75f, 12.2f, 2.0f, 1.0f);
                        }
                        break;

                }
            }
        }
      /*  writeText("√",19.75f,2.9f,1.0f,1.0f);
        writeText("√",19.75f,4.9f,1.0f,1.0f);
        writeText("√",19.75f,7.23f,1.0f,1.0f);
        writeText("√",19.75f,9.6f,1.0f,1.0f);
        writeText("√",19.75f,12.2f,1.0f,1.0f);*/
        if(checkItems.get(14).getName()!=null)
            writeText(checkItems.get(14).getName(),9.75f,14.88f,12.0f,2.0f);
        if(bean.getJcclyj()!=null)
            writeText(bean.getJcclyj(),9.75f,18.88f,12.0f,2.0f);
        if (bean.getJcryxmyi()!=null)
            writeText(bean.getJcryxmyi(),9.75f,32.88f,5.0f,2.0f);
        if (bean.getJcryxmer()!=null)
            writeText(bean.getJcryxmer(),9.75f,34.88f,5.0f,2.0f);
        if(bean.getZfzhyi()!=null)
            writeText(bean.getZfzhyi(),19.75f,32.88f,6.0f,2.0f);
        if(bean.getZfzher()!=null)
            writeText(bean.getZfzher(),19.75f,34.88f,6.0f,2.0f);
        if(bean.getJcdate()!=null)
            writeText(bean.getJcdate(),23.0f,36.88f,5.0f,2.0f);






       /* writeText("其他",9.75f,14.88f,1.0f,1.0f);

        writeText("意见",9.75f,18.88f,1.0f,1.0f);

        writeText("姓名1",9.75f,32.88f,5.0f,2.0f);
        writeText("单位1",19.75f,32.88f,5.0f,2.0f);
        writeText("姓名2",9.75f,34.88f,5.0f,2.0f);
        writeText("单位2",19.75f,34.88f,5.0f,2.0f);
        writeText("负责人",9.75f,36.88f,5.0f,2.0f);
        writeText("2017-07-24",23.0f,36.88f,5.0f,1.0f);
        writeText("检查1",9.75f,38.88f,2.0f,1.0f);*/



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
                    mCacheCanvas.drawBitmap(bf1,null,new Rect((int)JsPxValue(9.75f),(int)JsPxValue(36.88f),(int)JsPxValue(12.75f),(int)JsPxValue(37.88f)),paint);
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
                    mCacheCanvas.drawBitmap(bf2,null,new Rect((int)JsPxValue(9.75f),(int)JsPxValue(38.88f),(int)JsPxValue(12.75f),(int)JsPxValue(39.88f)),paint);
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
                    mCacheCanvas.drawBitmap(bf3,null,new Rect((int)JsPxValue(23.0f),(int)JsPxValue(38.88f),(int)JsPxValue(26.0f),(int)JsPxValue(39.88f)),paint);
                    bf3.recycle();
                }

            }


        }




        File f2 = new File(FileUtil.getInstance().getCacheDirPath(), "sysc_2.jpg");
        if (f2.exists()) f2.delete();
       return FileUtil.getInstance().save2Local2(cacheBitmap, f2.getAbsolutePath());

    }
    private  String draw_1(){
        mCacheCanvas.drawColor(Color.WHITE);
        File file=new File(FileUtil.getInstance().getDirPrint(),"sysc_1.jpg");
        Bitmap b1 = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Bitmap b1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sysc_1);
        merge(b1);
        if(!b1.isRecycled())b1.recycle();

        if(bean.getFName()!=null)
            writeText(bean.getFName(),9.0f,7.3f,6.0f,1.0f);
        if(bean.getFCityAdd()!=null)
            writeText(bean.getFCityAdd(),9.0f,9.0f,6.0f,1.0f);
        if(bean.getFPhone()!=null)
            writeText(bean.getFLegalPerson(),9.0f,10.9f,4.0f,1.0f);
        if(bean.getFLegalPerson()!=null)
            writeText(bean.getFLegalPerson(),24.0f,10.9f,4.0f,1.0f);

        List<CheckContentEntity> checkItems = bean.getCheckItems();
        if (checkItems != null) {
            for (int i = 0; i < 9; i++) {
                switch (checkItems.get(i).getName()) {
                    case Constance.Item7_1:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,19.0f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,19.0f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,19.0f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,19.0f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,19.0f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_2:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,22.5f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,22.5f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,22.5f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,22.5f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,22.5f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_3:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,26.5f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,26.5f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,26.5f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,26.5f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,26.5f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_4:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,30.5f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,30.5f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,30.5f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,30.5f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,30.5f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_5:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,32.55f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,32.55f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,32.55f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,32.55f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,32.55f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_6:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,34.60f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,34.60f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,34.60f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,34.60f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,34.60f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_7:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,36.50f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,36.50f,2.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,36.50f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,36.50f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,36.50f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_8:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,40.0f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,40.0f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,40.0f,1.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,40.0f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,40.0f,2.0f,1.0f);
                        }
                        break;
                    case Constance.Item7_9:
                        if (checkItems.get(i).getJL().equals("是")) {
                            writeText("√",19.75f,43.5f,1.0f,1.0f);

                        } else {
                            writeText("√",21.75f,43.5f,1.0f,1.0f);
                            if (checkItems.get(i).getQK() == null) {
                                writeText("",23.75f,43.5f,2.0f,1.0f);
                            } else {
                                writeText(checkItems.get(i).getQK(),23.75f,43.5f,2.0f,1.0f);
                            }
                            if(fl1.get(""+i)!=null)
                                writeText(fl1.get(""+i),25.75f,43.5f,2.0f,1.0f);
                        }
                        break;

                }
            }
        }

        /*writeText("√",19.75f,19.0f,1.0f,1.0f);
        writeText("√",21.75f,19.0f,1.0f,1.0f);
        writeText("备注",23.75f,19.0f,1.0f,1.0f);
        writeText("资料",25.75f,19.0f,1.0f,1.0f);

        writeText("√",19.75f,22.5f,1.0f,1.0f);

        writeText("√",19.75f,26.5f,1.0f,1.0f);

        writeText("√",19.75f,30.5f,1.0f,1.0f);

        writeText("√",19.75f,32.70f,1.0f,1.0f);

        writeText("√",19.75f,34.62f,1.0f,1.0f);

        writeText("√",19.75f,36.50f,1.0f,1.0f);

        writeText("√",19.75f,40.0f,1.0f,1.0f);

        writeText("√",19.75f,43.5f,1.0f,1.0f);*/



        File f1 = new File(FileUtil.getInstance().getCacheDirPath(), "sysc_1.jpg");
        if (f1.exists()) f1.delete();
      return FileUtil.getInstance().save2Local2(cacheBitmap, f1.getAbsolutePath());


    }
    @Override
    public List<String> draws() {
        analysisdata();

        //第二张


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
        if(!cacheBitmap.isRecycled()){
            cacheBitmap.recycle();
            System.gc();
        }
        return FileUtil.getInstance().getPrintList("sysc");
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
            if (i < 9) {
                if (!checkItems.get(i).getJL().equals("是")) {
                    if (checkItems.get(i).getTp() != null && !checkItems.get(i).getTp().equals("")) {
                        File pf1 = new File(PhotoViewModel.getCommonSupervisionDir(), checkItems.get(i).getTp());
                        if (pf1.exists()) {
                            fl1.put("" + i, "图1-" + (flindex1 + 1));
                            flindex1++;
                        }
                    }
                }

            } else if (i < 14) {
                if (checkItems.get(i).getJL() != null && !checkItems.get(i).getJL().equals("是")) {
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
                writeText(fl1.get("8"),16.5f,0.5f,4.0f,2.0f);
                break;
            case "P10.jpg":
                writeText(fl1.get("9"),16.5f,0.5f,4.0f,2.0f);
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

        }

        File ff=new File(FileUtil.getInstance().getCacheDirPath(),"sysc_"+numName+".jpg");
        if(ff.exists())ff.delete();
        String pt = FileUtil.getInstance().save2Local2(cacheBitmap, ff.getAbsolutePath());
        numName++;
        return pt;
    }
    private int numName=3;

}
