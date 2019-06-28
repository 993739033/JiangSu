package com.wyw.jiangsu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.AnimAQueryListBean;
import com.wyw.jiangsu.bean.AnimAlistBean;
import com.wyw.jiangsu.bean.AnimBQueryListBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.JianyiChuliTongzhidanListBean;
import com.wyw.jiangsu.bean.ProductBListBean;
import com.wyw.jiangsu.bean.ProductionAListBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsAListBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsBListBean;
import com.wyw.jiangsu.bean.Qua_QuarantineDeclarationCDQuery;
import com.wyw.jiangsu.bean.QuarantineDealListQueryBean;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.bean.upload.UploadDoubleRandomBean;
import com.wyw.jiangsu.bean.upload.UploadQuarantineProcessNotBeanDetil;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.originworkrecord_table.AnimOriginProxy;
import com.wyw.jiangsu.originworkrecord_table.DanQuarantineWorkProxy;
import com.wyw.jiangsu.originworkrecord_table.MilkAnimWorkProxy;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.supervision_table.AnimApplyDetil;
import com.wyw.jiangsu.supervision_table.ApplyDetil;
import com.wyw.jiangsu.supervision_table.ChanDiJianYiQueryProxy;
import com.wyw.jiangsu.supervision_table.FodderProxy;
import com.wyw.jiangsu.supervision_table.GeLiChangProxy;
import com.wyw.jiangsu.supervision_table.GuiMoProxy1;
import com.wyw.jiangsu.supervision_table.IProxy;
import com.wyw.jiangsu.supervision_table.JianyichuliQuery;
import com.wyw.jiangsu.supervision_table.JianyichuliQueryPrint;
import com.wyw.jiangsu.supervision_table.Jianyitonngzhi;
import com.wyw.jiangsu.supervision_table.MilkProxy;
import com.wyw.jiangsu.supervision_table.SSJProxy;
import com.wyw.jiangsu.supervision_table.TuZaiProxy;
import com.wyw.jiangsu.supervision_table.VDManagementProxy;
import com.wyw.jiangsu.supervision_table.VDrugProductionProxy;
import com.wyw.jiangsu.supervision_table.WuHaiHuaProxy;
import com.wyw.jiangsu.supervision_table.ZhenLiaoProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;


public class DrawUtil<T> implements ITime {
    private ChangeLock changeLock = new ChangeLock();
    ;
    private Context context;
    private SharedPreferences pref;
    private Bitmap cachebitmap;
    private Canvas cachecanvas;
    private Paint paint;
    private Paint paint1;
    private String dir = MyApplication.getContext().getExternalFilesDir("").getAbsolutePath();
    TimePresenter timePresenter;
    String mTime;

    private float JsPxValue(double cm) {
        double rv = cm * (96 / 2.54);
        return Float.parseFloat(Double.toString(rv));
    }

    public DrawUtil(Context context) {
        super();
        this.context = context;
        init();
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
    }

    public void init() {
        //cachebitmap = Bitmap.createBitmap(720, 1080, Config.ARGB_8888);
        cachebitmap = Bitmap.createBitmap(1280, 1810, Config.ARGB_8888);
//        cachebitmap = Bitmap.createBitmap(794, 1123, Config.ARGB_8888);
        cachecanvas = new Canvas(cachebitmap);
        cachecanvas.drawColor(Color.WHITE);

        paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10); // 设置绘制文字的字号大小

        // Paint即画笔，在绘图过程中起到了极其重要的作用，画笔主要保存了颜色，
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.BLACK);
        paint1.setTextSize(16);

    }

    /**
     * 打印日常监管
     */
    public List<String> getbitmap_rc(T t, int position) {
        Bitmap frontBitmap;
        IProxy proxy = null;
        if (t instanceof UploadCommonSupervisionBean) {
            switch (position) {
                case 0:
                    proxy = new GuiMoProxy1(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 1:
                    proxy = new TuZaiProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 2:
                    proxy = new ZhenLiaoProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 3:
                    proxy = new WuHaiHuaProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 4:
                    proxy = new GeLiChangProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 5:
                    proxy = new FodderProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 6:
                    proxy = new VDrugProductionProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 7:
                    proxy = new VDManagementProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                case 8:
                    proxy = new MilkProxy(context, cachecanvas, (UploadCommonSupervisionBean) t, cachebitmap);
                    break;
                default:
                    break;
            }
            return proxy.draws();
        }
        return null;
    }

    /**
     * 打印产地检疫
     **/
    public List<String> getbitmap_cd(T t, int position) {
        Bitmap frontBitmap;
        IProxy proxy = null;
        if (t instanceof Qua_QuarantineDeclarationCDQuery) {
            switch (position) {
                case 9:
                    proxy = new ChanDiJianYiQueryProxy(context, cachecanvas, (Qua_QuarantineDeclarationCDQuery) t, cachebitmap);
                    break;
            }
            return proxy.draws();
        }
        return null;
    }

    /**
     * 动物A
     *
     * @param animal_A
     * @param whichPrinter
     * @return
     */
    public Bitmap Animal_A(AnimAlistBean.DataListBean animal_A, int whichPrinter) { // Animal_A animal_A
        // Bitmap cachebitmap1 = Bitmap.createBitmap(794,
        // 1123,Config.ARGB_8888);
        // Canvas cachecanvas1 = newd Canvas(cachebitmap1);
        // cachecanvas1.drawColor(Color.WHITE);
        writeText(animal_A.getGuid() + "", 3.3F, 4.6F, 6.2F, 1.2F);// 二维码

        writeText(animal_A.getACargoOwner(), 11.0F, 6.4F, 6.9F, 1.2F);    //货主
        //联系电话
        writeText(animal_A.getAPhone(), 24.1F, 6.4F, 5.4F, 1.2F);        //货主电话

//        String request = animal_A.getAnimal_species2().trim();
//        if (request.equals("")) {
//            //动物种类
//            writeText(animal_A.getAnimal_species(), 4.3F, 4.86F, 6.9F, 1.2F);
//        } else {
//            //动物种类
//            writeText(animal_A.getAnimal_species1() + "," + request, 4.3F, 4.86F, 6.9F, 1.2F);
//        }
        writeText(animal_A.getAXuZhong(), 11.5F, 8.14F, 6.9F, 1.2F);//动物种类

        //数量及单位
        writeText(changeLock.changeToQuantity_unit(animal_A.getAQuantity() + "")
                + animal_A.getADanWei(), 25.2F, 8.14F, 5.4F, 1.2F);            //数量及单位
        //启运省
        writeText(animal_A.getAShengQy(), 8.0F, 10.25F, 2.6F, 0.9F);            //启运省
        //启运市
        writeText(animal_A.getAShiQy(), 12.55F, 10.25F, 2.5F, 0.9F);            //启运市
        //启运县
        writeText(animal_A.getAXianQy(), 18.35F, 10.25F, 2.5F, 0.9F);        //启运县
        //启运乡
        writeText(animal_A.getAXiangQy(), 26.35F, 10.25F, 2.3F, 0.9F);        // 启运镇
        //村
        writeText(animal_A.getACunQy(), 17.85F, 11.32F, 6F, 0.9F);        //启运村
//        String fdfs = animal_A.getAYongTu();
//        if (fdfs.equals("养殖场")) {        //养殖场
//            writeText("√", 16F, 6.7F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(16), JsPxValue(7.1),
//            // paint1);
//        } else if (fdfs.equals("屠宰场")) {        //屠宰场
//            writeText("√", 18.1F, 6.7F, 1F, 1F);
//        }

        //到达省
        writeText(animal_A.getAShengDd(), 8.0F, 13.15F, 2.6F, 0.9F);
        //到达市
        writeText(animal_A.getAShiDd(), 12.55F, 13.15F, 2.5F, 0.9F);
        //到达县
        writeText(animal_A.getAXianDd(), 18.35F, 13.15F, 2.5F, 0.9F);
        //到达镇
        writeText(animal_A.getAXiangDd(), 26.35F, 13.15F, 2.3F, 0.9F);

        //到达村
        writeText(animal_A.getACunDd(), 14.85F, 14.5F, 6F, 0.9F);
//        String sdf = animal_A.getInput_market1();
//        if (sdf.equals("养殖场")) {
//            writeText("√", 14.3F, 8.5F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(14.3), JsPxValue(9),
//            // paint1);
//        } else if (sdf.equals("屠宰场")) {
//            writeText("√", 16.1F, 8.5F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(16.1), JsPxValue(9),
//            // paint1);
//        } else if (sdf.equals("交易市场")) {
//            writeText("√", 18.2F, 8.5F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(18.2), JsPxValue(9),
//            // paint1);
//        }
        //用途
        writeText(animal_A.getAYongTu(), 7.68F, 15.9F, 2.1F, 1.1F);
        //承运人
        writeText(animal_A.getACarrier(), 16.17F, 15.9F, 5.7F, 1.1F);
        //承运人电话
        writeText(animal_A.getAPhoneCyr(), 26.26F, 15.9F, 3.3F, 1.1F);

        // cachecanvas.drawText(animal_A.getCarrier_phone(), JsPxValue(16.3),
        // JsPxValue(9.9), paint1);

        String fashi = animal_A.getAYunZai().trim();
        String[] fsa = fashi.split(",");
        for (int i = 0; i < fsa.length; i++) {

            if (fsa[i].trim().equals("公路")) {
                writeText("√", 7.3F, 17.85F, 1.1F, 1.1F);
                // cachecanvas1.drawText("鈭�", JsPxValue(4.52),JsPxValue(11.2),
                // paint1);
//                writeText("√", 10.55F, 17.85F, 1.1F, 1.1F);
//                writeText("√", 13.90F, 17.85F, 1.1F, 1.1F);
//                writeText("√", 17.25F, 17.85F, 1.1F, 1.1F);
            }
            if (fsa[i].trim().equals("铁路")) {
                writeText("√", 10.55F, 17.85F, 1.1F, 1.1F);
                // cachecanvas1.drawText("鈭�", JsPxValue(6.58), JsPxValue(11.2),
                // paint1);
            }
            if (fsa[i].trim().equals("水路")) {
                writeText("√", 13.90F, 17.85F, 1.1F, 1.1F);
                // cachecanvas1.drawText("鈭�", JsPxValue(8.71),JsPxValue(11.2),
                // paint1);
            }
            if (fsa[i].trim().equals("航空")) {
                writeText("√", 17.25F, 17.85F, 1.1F, 1.1F);

            }
        }
        // 运载工具牌号
        writeText(animal_A.getATrademark(), 26.26F, 17.85F, 6.3F, 1.1F);
        // 消毒
        writeText(animal_A.getADisinfection(), 20.56F, 19.9F, 7.6F, 1.1F);
        //有效到达日
        writeText(changeLock.numToUpperETA1(animal_A.getAYouXiaoRi() + ""), 12.3F, 22.1F, 5F, 1.1F);

        //官方兽医签字
        writeText(animal_A.getAVeterinary(), 22F, 24.2F, 5F, 1.1F);

        //2016/11/1 10:12:16
        String[] nian = animal_A.getDateQF().trim().split(" ");
        Log.e("----------", animal_A.getDateQF().trim() + "-----------------");
        String[] split = nian[0].split("-|/");
        String year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];

        writeText(changeLock.Change(year), 19.8F, 26.45F, 5F, 1.1F);//年
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 23.4F, 26.45F, 5F, 1.1F);
        writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.1F, 26.45F, 5F, 1.1F);
        //时分秒
        // writeText(shijian, 18F, 16.65F, 1.8F, 0.4F);

        // 耳标号
        if (whichPrinter == 1 && animal_A.getAEarTag().getBytes().length > 146) {
            writeText("耳标号详情见背面", 7.2F, 29.65F, 15F, 1.1F);
        } else {
            writeText(animal_A.getAEarTag(), 7.2F, 29.65F, 15F, 1.1F);
        }
        // 备注
        writeText(animal_A.getAMemo1(), 7.2F, 39.7F, 15F, 1.1F);
        // 动物监督电话
        writeText(animal_A.getADwPhone(), 11F, 43.4F, 15F, 1.1F);

//        Bitmap bitmap1 = Create2DCode(animal_A.getGuid() + "");
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(6), JsPxValue(23), paint);
//        bitmap1.recycle();

        Bitmap bitmap1 = Create2DCode(Constance.PRINT_URL_ANIA + animal_A.getGuid());
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(0.74), paint);
        bitmap1.recycle();
        return cachebitmap;
    }

    /**
     * 动物产地检疫申报单
     */
    public Bitmap Animal_Location_Apply(AnimProcessListBean.DataListBean Location_Apply) {
        //报检时间FScTime
        if (!TextUtils.isEmpty(Location_Apply.getFScTime())) {
            String dateQy = Location_Apply.getFScTime();
            String[] split = dateQy.split("-");
            writeText(split[0], 19.4f, 5.57f, 6f, 1.1f);
            writeText(split[1], 21.8f, 5.57f, 6f, 1.1f);
            writeText(split[2], 23.5f, 5.57f, 6f, 1.1f);
        }
        //申报单编号
        writeText(Location_Apply.getQDWNumber(), 24.2f, 1.3f, 10f, 1.8f);
        //申报人姓名
        writeText(Location_Apply.getFSuserName(), 12f, 7.3f, 6f, 1.2f);
        //联系电话
        writeText(Location_Apply.getQDWPhone(), 25.1f, 7.3f, 6f, 1.2f);
        //养殖户地址
        writeText(Location_Apply.getQDAddQy(), 12f, 8.9f, 18f, 1.6f);
        //动物种类
        writeText(Location_Apply.getQDWXuZhongZ() + Location_Apply.getQDWDanWei(), 12f, 10.4f, 4f, 1.1f);
        //动物数量
        writeText(Location_Apply.getQDWQuantity() + "", 25.1f, 10.4f, 4f, 1.1f);
        //动物来源
        if (Location_Apply.getQDWLaiYuan().equals("家畜家禽")) {
            writeText("√", 15.1F, 11.9F, 1F, 1F);
        } else if (Location_Apply.getQDWLaiYuan().equals("人工饲养")) {
            writeText("√", 20.7F, 11.9F, 1F, 1F);
        } else if (Location_Apply.getQDWLaiYuan().equals("合法捕捞")) {
            writeText("√", 26.2f, 11.9F, 1F, 1F);
        }
        //用途
        if (Location_Apply.getQDWYongTu().equals("饲养")) {
            writeText("√", 12.8F, 13.6F, 1F, 1F);
        } else if (Location_Apply.getQDWYongTu().equals("屠宰")) {
            writeText("√", 15.3F, 13.6F, 1F, 1F);
        } else if (Location_Apply.getQDWYongTu().equals("种用、乳用")) {
            writeText("√", 17.8F, 13.6F, 1F, 1F);
        } else if (Location_Apply.getQDWYongTu().equals("展览")) {
            writeText("√", 22.3F, 13.6F, 1F, 1F);
        } else if (Location_Apply.getQDWYongTu().equals("演出")) {
            writeText("√", 24.7F, 13.6F, 1F, 1F);
        } else if (Location_Apply.getQDWYongTu().equals("比赛")) {
            writeText("√", 27.1F, 13.6F, 1F, 1F);
        } else if (Location_Apply.getQDWYongTu().equals("其他")) {
            writeText("√", 29.6F, 13.6F, 1F, 1F);
        }
        //畜生耳标号 QDWErBiaoHao
        if (Location_Apply.getQDWErBiaoHao().getBytes().length <= 63) {
            writeText(Location_Apply.getQDWErBiaoHao() + "", 12f, 15.75f, 6f, 1.1f);
        } else if (Location_Apply.getQDWErBiaoHao().getBytes().length > 63) {
            writeText(Location_Apply.getQDWErBiaoHao().substring(0, 62) + "", 12f, 15.3f, 40f, 1.1f);
            writeText(Location_Apply.getQDWErBiaoHao().substring(62) + "", 12f, 16.1f, 40f, 1.1f);
        }
        // 野生动物驯养繁殖许可证号
        writeText(Location_Apply.getXKZH(), 12f, 17.84f, 6f, 1.1f);
        //有效...审批表
        if (Location_Apply.getYx().equals("有")) {
            writeText("√", 18.4f, 19.46f, 1f, 1.1f);
        } else if (Location_Apply.getYx().equals("无")) {
            writeText("√", 26.4f, 19.46f, 1f, 1.1f);
        }
        //到达地址
        writeText(Location_Apply.getQDWAddDd(), 12f, 21.1f, 18f, 1.2f);
//        启动时间DateQy
        if (!TextUtils.isEmpty(Location_Apply.getDateQy())) {
            String dateQy = Location_Apply.getDateQy();
            String[] split = dateQy.split("-");
            writeText(split[0], 19.15f, 22.7f, 6f, 1.1f);
            writeText(split[1], 21.55f, 22.7f, 6f, 1.1f);
            writeText(split[2], 23.25f, 22.7f, 6f, 1.1f);
        }
        //受理还是不受理QDWAccepted
        if (Location_Apply.getQDWAccepted().equals("受理")) {
            if (!TextUtils.isEmpty(Location_Apply.getDateNpy())) {
                String dateQy = Location_Apply.getDateNpy();
                String[] split = dateQy.split("-");
                writeText("√", 7.1f, 29.1f, 1f, 1.1f);
                writeText(split[0], 14.7f, 29.1f, 6f, 1.1f);
                writeText(split[1], 17f, 29.1f, 6f, 1.1f);
                writeText(split[2], 18.8f, 29.1f, 6f, 1.1f);
                writeText(Location_Apply.getQDWCunDd(), 21.1f, 29.1f, 6f, 1.1f);
                //处理意见
                writeText(Location_Apply.getQDWCunDd(), 17.15f, 39f, 6f, 1.1f);
                writeText("√", 3.6f, 38.9f, 1f, 1.1f);
                writeText(split[0], 9.2f, 39f, 6f, 1.1f);
                writeText(split[1], 11.6f, 39f, 6f, 1.1f);
                writeText(split[2], 13.3f, 39f, 6f, 1.1f);
            }
        } else if (Location_Apply.getQDWAccepted().equals("不受理")) {
            writeText("√", 6.7f, 30.85f, 1f, 1.1f);
            writeText("√", 3.6f, 41.3f, 1f, 1.1f);
            //理由：Location_Apply.getQDWLiYou()
            if (Location_Apply.getQDWLiYou().length() <= 35) {
                writeText(Location_Apply.getQDWLiYou(), 13.5f, 30.85f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou(), 9f, 41.3f, 40f, 1.1f);
            } else if (Location_Apply.getQDWLiYou().length() <= 40 && Location_Apply.getQDWLiYou().length() > 35) {
                writeText(Location_Apply.getQDWLiYou(), 9f, 41.3f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(0, 35), 13.5f, 30.45f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(35), 13.5f, 31.25f, 40f, 1.1f);
            } else if (Location_Apply.getQDWLiYou().length() > 40) {
                writeText(Location_Apply.getQDWLiYou().substring(0, 40), 9f, 41.3f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(40), 9f, 42.2f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(0, 35), 13.5f, 30.45f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(35), 13.5f, 31.25f, 40f, 1.1f);
            }
        }
        //经办人及时间
        writeText(Location_Apply.getQDWAttn() + "", 9.44f, 32.5f, 6f, 1.1f);
        if (!TextUtils.isEmpty(Location_Apply.getCLRQ())) {
            String dateQy = Location_Apply.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 13.5f, 32.5f, 6f, 1.1f);
            writeText(split[1], 15.8f, 32.5f, 6f, 1.1f);
            writeText(split[2], 17.4f, 32.5f, 6f, 1.1f);
        }

        //经办人及联系电话
        writeText(Location_Apply.getQDWAttn() + "", 6.2f, 43.05f, 6f, 1.1f);
        writeText(Location_Apply.getQDWJBRDianHua() + "", 14.3f, 43.05f, 6f, 1.1f);

        if (!TextUtils.isEmpty(Location_Apply.getCLRQ())) {
            String dateQy = Location_Apply.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 22f, 45.3f, 6f, 1.1f);
            writeText(split[1], 24.3f, 45.3f, 6f, 1.1f);
            writeText(split[2], 26f, 45.3f, 6f, 1.1f);
        }
        return cachebitmap;
    }


    /**
     * 繁殖材料产地检疫申报单
     */
    @Nullable
    private Bitmap Material_Location_Apply(AnimProcessListBean.DataListBean Location_Apply) {
        //报检时间FScTime
        if (!TextUtils.isEmpty(Location_Apply.getFScTime())) {
            String dateQy = Location_Apply.getFScTime();
            String[] split = dateQy.split("-");
            writeText(split[0], 19.3f, 5.57f, 6f, 1.1f);
            writeText(split[1], 21.7f, 5.57f, 6f, 1.1f);
            writeText(split[2], 23.4f, 5.57f, 6f, 1.1f);
        }
        //申报单编号
        writeText(Location_Apply.getQDWNumber(), 24.2f, 1.3f, 10f, 1.8f);
        //申报人姓名
        writeText(Location_Apply.getFSuserName(), 12f, 7.3f, 6f, 1.2f);
        //联系电话
        writeText(Location_Apply.getQDWPhone(), 25.1f, 7.3f, 6f, 1.2f);

        //养殖户地址
        writeText(Location_Apply.getQDAddQy(), 12f, 8.9f, 18f, 1.6f);
        //动物种类
        writeText(Location_Apply.getQDWXuZhongZ(), 12f, 10.6f, 4f, 1.1f);

        //畜生耳标号 QDWErBiaoHao
        if (Location_Apply.getQDWErBiaoHao().getBytes().length <= 63) {
            writeText(Location_Apply.getQDWErBiaoHao() + "", 12f, 15.75f, 6f, 1.1f);
        } else if (Location_Apply.getQDWErBiaoHao().getBytes().length > 63) {
            writeText(Location_Apply.getQDWErBiaoHao().substring(0, 62) + "", 12f, 12.6f, 40f, 1.1f);
            writeText(Location_Apply.getQDWErBiaoHao().substring(62) + "", 12f, 13.5f, 40f, 1.1f);
        }
        //动物产品种类
        writeText(Location_Apply.getFqProduct() + "", 12f, 15.75f, 4f, 1.1f);
//        动物产品数量
        writeText(Location_Apply.getQDWCunDd() + "", 25.3f, 15.75f, 4f, 1.1f);
        //有效
        if (Location_Apply.getFqZxqscjyxkz().equals("有")) {
            writeText("√", 13.2f, 18f, 1f, 1.1f);
        } else if (Location_Apply.getYx().equals("无")) {
            writeText("√", 16.6f, 18f, 1f, 1.1f);
        }
        if (Location_Apply.getYx().equals("有")) {
            writeText("√", 27.2f, 18f, 1f, 1.1f);
        } else if (Location_Apply.getYx().equals("无")) {
            writeText("√", 30.1f, 18f, 1f, 1.1f);
        }

        //到达地址
        writeText(Location_Apply.getQDWAddDd(), 12f, 20.4f, 40f, 1.2f);

//        启动时间DateQy
        if (!TextUtils.isEmpty(Location_Apply.getDateQy())) {
            String dateQy = Location_Apply.getDateQy();
            String[] split = dateQy.split("-");
            writeText(split[0], 19.2f, 22f, 6f, 1.1f);
            writeText(split[1], 21.6f, 22f, 6f, 1.1f);
            writeText(split[2], 23.3f, 22f, 6f, 1.1f);
        }
        ////受理还是不受理QDWAccepted
        if (Location_Apply.getQDWAccepted().equals("受理")) {
            if (!TextUtils.isEmpty(Location_Apply.getDateNpy())) {
                String dateQy = Location_Apply.getDateNpy();
                String[] split = dateQy.split("-");

                writeText("√", 6.7f, 28.9f, 1f, 1.1f);
                writeText(split[0], 14.2f, 28.9f, 6f, 1.1f);
                writeText(split[1], 16.7f, 28.9f, 6f, 1.1f);
                writeText(split[2], 18.4f, 28.9f, 6f, 1.1f);
                writeText(Location_Apply.getQDWCunDd(), 20.8f, 28.9f, 6f, 1.1f);

                //处理意见
                writeText(Location_Apply.getQDWCunDd(), 17f, 39.3f, 6f, 1.1f);
                writeText("√", 3.2f, 39.2f, 1f, 1.1f);
                writeText(split[0], 9f, 39.2f, 6f, 1.1f);
                writeText(split[1], 11.3f, 39.2f, 6f, 1.1f);
                writeText(split[2], 12.9f, 39.2f, 6f, 1.1f);
            }

        } else if (Location_Apply.getQDWAccepted().equals("不受理")) {
            writeText("√", 3.2f, 40.1f, 1f, 1.1f);
            writeText("√", 6.4f, 31f, 1f, 1.1f);
            //理由：Location_Apply.getQDWLiYou()
            if (Location_Apply.getQDWLiYou().length() <= 35) {
                writeText(Location_Apply.getQDWLiYou(), 13f, 31f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou(), 9.2f, 41.5f, 40f, 1.1f);
            } else if (Location_Apply.getQDWLiYou().length() <= 40 && Location_Apply.getQDWLiYou().length() > 35) {
                writeText(Location_Apply.getQDWLiYou(), 9.2f, 41.5f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(0, 35), 13f, 30.6f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(35), 13f, 31.5f, 40f, 1.1f);
            } else if (Location_Apply.getQDWLiYou().length() > 40) {
                writeText(Location_Apply.getQDWLiYou().substring(0, 40), 9f, 41.5f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(40), 9f, 42.4f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(0, 35), 13f, 30.6f, 40f, 1.1f);
                writeText(Location_Apply.getQDWLiYou().substring(35), 13f, 31.5f, 40f, 1.1f);
            }
        }

        //经办人及时间
        writeText(Location_Apply.getQDWAttn() + "", 9.54f, 32.8f, 6f, 1.1f);
        if (!TextUtils.isEmpty(Location_Apply.getCLRQ())) {
            String dateQy = Location_Apply.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 13.4f, 32.8f, 6f, 1.1f);
            writeText(split[1], 15.8f, 32.8f, 6f, 1.1f);
            writeText(split[2], 17.4f, 32.8f, 6f, 1.1f);
        }

        //经办人及联系电话
        writeText(Location_Apply.getQDWAttn() + "", 6.2f, 43.5f, 6f, 1.1f);
        writeText(Location_Apply.getQDWJBRDianHua() + "", 14.3f, 43.5f, 6f, 1.1f);


        if (!TextUtils.isEmpty(Location_Apply.getCLRQ())) {
            String dateQy = Location_Apply.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 21.8f, 45.7f, 6f, 1.1f);
            writeText(split[1], 24.2f, 45.7f, 6f, 1.1f);
            writeText(split[2], 25.9f, 45.7f, 6f, 1.1f);
        }
        return cachebitmap;
    }


    /**
     * 动物A查询
     *
     * @param animal_A
     * @return
     */
    public Bitmap Animal_QueryA(AnimAQueryListBean.DataListEntity animal_A) { // Animal_A animal_A
        // Bitmap cachebitmap1 = Bitmap.createBitmap(794,
        // 1123,Config.ARGB_8888);
        // Canvas cachecanvas1 = newd Canvas(cachebitmap1);
        // cachecanvas1.drawColor(Color.WHITE);

        writeText(animal_A.getFSguidId() + "", 3.3F, 4.6F, 6.2F, 1.2F);// 二维码

        writeText(animal_A.getACargoOwner(), 11.0F, 6.4F, 6.9F, 1.2F);    //货主
        //联系电话
        writeText(animal_A.getAPhone(), 24.1F, 6.4F, 5.4F, 1.2F);        //货主电话

//        String request = animal_A.getAnimal_species2().trim();
//        if (request.equals("")) {
//            //动物种类
//            writeText(animal_A.getAnimal_species(), 4.3F, 4.86F, 6.9F, 1.2F);
//        } else {
//            //动物种类
//            writeText(animal_A.getAnimal_species1() + "," + request, 4.3F, 4.86F, 6.9F, 1.2F);
//        }
        writeText(animal_A.getAXuZhong(), 11.5F, 8.14F, 6.9F, 1.2F);//动物种类

        //数量及单位
        writeText(changeLock.changeToQuantity_unit(animal_A.getAQuantity() + "")
                + animal_A.getADanWei(), 25.2F, 8.14F, 5.4F, 1.2F);            //数量及单位
        //启运省
        writeText(animal_A.getAShengQy(), 8.0F, 10.25F, 2.6F, 0.9F);            //启运省
        //启运市
        writeText(animal_A.getAShiQy(), 12.55F, 10.25F, 2.5F, 0.9F);            //启运市
        //启运县
        writeText(animal_A.getAXianQy(), 18.35F, 10.25F, 2.5F, 0.9F);        //启运县
        //启运乡
        writeText(animal_A.getAXiangQy(), 26.35F, 10.25F, 2.3F, 0.9F);        // 启运镇
        //村
        writeText(animal_A.getACunQy(), 17.85F, 11.32F, 6F, 0.9F);        //启运村
//        String fdfs = animal_A.getAYongTu();
//        if (fdfs.equals("养殖场")) {        //养殖场
//            writeText("√", 16F, 6.7F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(16), JsPxValue(7.1),
//            // paint1);
//        } else if (fdfs.equals("屠宰场")) {        //屠宰场
//            writeText("√", 18.1F, 6.7F, 1F, 1F);
//        }

        //到达省
        writeText(animal_A.getAShengDd(), 8.0F, 13.15F, 2.6F, 0.9F);
        //到达市
        writeText(animal_A.getAShiDd(), 12.55F, 13.15F, 2.5F, 0.9F);
        //到达县
        writeText(animal_A.getAXianDd(), 18.35F, 13.15F, 2.5F, 0.9F);
        //到达镇
        writeText(animal_A.getAXianDd(), 26.35F, 13.15F, 2.3F, 0.9F);

        //到达村
        writeText(animal_A.getACunDd(), 14.85F, 14.5F, 6F, 0.9F);
//        String sdf = animal_A.getInput_market1();
//        if (sdf.equals("养殖场")) {
//            writeText("√", 14.3F, 8.5F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(14.3), JsPxValue(9),
//            // paint1);
//        } else if (sdf.equals("屠宰场")) {
//            writeText("√", 16.1F, 8.5F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(16.1), JsPxValue(9),
//            // paint1);
//        } else if (sdf.equals("交易市场")) {
//            writeText("√", 18.2F, 8.5F, 1F, 1F);
//            // cachecanvas1.drawText("鈭�", JsPxValue(18.2), JsPxValue(9),
//            // paint1);
//        }
        //用途
        writeText(animal_A.getAYongTu(), 7.68F, 15.9F, 2.1F, 1.1F);
        //承运人
        writeText(animal_A.getACarrier(), 16.17F, 15.9F, 5.7F, 1.1F);
        //承运人电话
        writeText(animal_A.getAPhoneCyr(), 26.26F, 15.9F, 3.3F, 1.1F);

        // cachecanvas.drawText(animal_A.getCarrier_phone(), JsPxValue(16.3),
        // JsPxValue(9.9), paint1);

        String fashi = animal_A.getAYunZai().trim();
        String[] fsa = fashi.split(",");
        for (int i = 0; i < fsa.length; i++) {

            if (fsa[i].trim().equals("公路")) {
                writeText("√", 7.3F, 17.85F, 1.1F, 1.1F);
                // cachecanvas1.drawText("鈭�", JsPxValue(4.52),JsPxValue(11.2),
                // paint1);
//                writeText("√", 10.55F, 17.85F, 1.1F, 1.1F);
//                writeText("√", 13.90F, 17.85F, 1.1F, 1.1F);
//                writeText("√", 17.25F, 17.85F, 1.1F, 1.1F);
            }
            if (fsa[i].trim().equals("铁路")) {
                writeText("√", 10.55F, 17.85F, 1.1F, 1.1F);
                // cachecanvas1.drawText("鈭�", JsPxValue(6.58), JsPxValue(11.2),
                // paint1);
            }
            if (fsa[i].trim().equals("水路")) {
                writeText("√", 13.90F, 17.85F, 1.1F, 1.1F);
                // cachecanvas1.drawText("鈭�", JsPxValue(8.71),JsPxValue(11.2),
                // paint1);
            }
            if (fsa[i].trim().equals("航空")) {
                writeText("√", 17.25F, 17.85F, 1.1F, 1.1F);

            }
        }
        // 运载工具牌号
        writeText(animal_A.getATrademark(), 26.26F, 17.85F, 6.3F, 1.1F);
        // 消毒
        writeText(animal_A.getADisinfection(), 20.56F, 19.9F, 7.6F, 1.1F);
        //有效到达日
        writeText(changeLock.numToUpperETA1(animal_A.getAYouXiaoRi() + ""), 12.3F, 22.1F, 5F, 1.1F);

        //官方兽医签字
        writeText(animal_A.getAVeterinary(), 22F, 24.2F, 5F, 1.1F);

        //2016/11/1 10:12:16
        String[] nian = animal_A.getDateQF().trim().split(" ");
        Log.e("----------", animal_A.getDateQF().trim() + "-----------------");
        String[] split = nian[0].split("-|/");
        String year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];

        writeText(changeLock.Change(year), 19.8F, 26.45F, 5F, 1.1F);//年
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 23.4F, 26.45F, 5F, 1.1F);
        writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.1F, 26.45F, 5F, 1.1F);
        //时分秒
        // writeText(shijian, 18F, 16.65F, 1.8F, 0.4F);

        // 耳标号
        writeText(animal_A.getAEarTag(), 7.2F, 29.65F, 15F, 1.1F);
        // 备注
        writeText(animal_A.getAMemo1(), 7.2F, 39.7F, 15F, 1.1F);
        // 动物监督电话
        writeText(animal_A.getADwPhone(), 11F, 43.4F, 15F, 1.1F);

//        Bitmap bitmap1 = Create2DCode(animal_A.getFSguidId() + "");
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(6), JsPxValue(23), paint);
//        bitmap1.recycle();

        Bitmap bitmap1 = Create2DCode(Constance.PRINT_URL_ANIA + animal_A.getFSguidId());
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(0.74), paint);
        bitmap1.recycle();

        return cachebitmap;
    }


    public Bitmap drawEbInBack(String erTag) {
        writeTextNoScale(erTag, 7F, 14.0F, 23F);
        writeTextNoScale(erTag, 7F, 37.47F, 23F);
        return cachebitmap;
    }

    public Bitmap drawEbInBackSecond(String erTag) {
        writeTextNoScale(erTag, 7F, 14.0F, 23F);
        return cachebitmap;
    }


    private void writeText(String value, float x, float y, float width, float height) {

        TextPaint textPaint = new TextPaint();

        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(22.5F);
        int w = (int) JsPxValue(width);
        if (value != null) {
            StaticLayout layout = new StaticLayout(value, textPaint, w, Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            float h = JsPxValue(height);
            while (true) {
                if (layout.getHeight() <= h) {
                    break;
                } else {
                    textPaint.setTextSize(textPaint.getTextSize() - 1);
                    layout = new StaticLayout(value, textPaint, w,
                            Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                }
            }
            cachecanvas.translate(JsPxValue(x), JsPxValue(y));
            layout.draw(cachecanvas);
            cachecanvas.translate(-JsPxValue(x), -JsPxValue(y));
        }
    }

    private Bitmap writeTextNoScale(String value, float x, float y, float width) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(22.5F);
        int w = (int) JsPxValue(width);
        if (value != null) {
            StaticLayout layout = new StaticLayout(value, textPaint, w, Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            cachecanvas.translate(JsPxValue(x), JsPxValue(y));
            layout.draw(cachecanvas);
            cachecanvas.translate(-JsPxValue(x), -JsPxValue(y));
        }
        return cachebitmap;
    }

    /**
     * 动物B
     *
     * @param animal_B
     * @param isSign
     * @return
     */
    public Bitmap Animal_B(AnimBlistBean.DataListBean animal_B, String TableName, boolean isSign, int whichPrinter) {

            writeText(animal_B.getAQCargoOwner(), 10.5F, 6.0F, 6.9F, 1.2F);//货主
            writeText(animal_B.getAQPhone(), 24.1F, 6.0F, 5.4F, 1.2F);// 联系电话
//        String requset = animal_B.getAQXuZhong();
//        if (requset.equals("")) {
//            writeText(animal_B.getAQXuZhong(), 6.8F, 7.7F, 1.85F, 1.0F);//动物种类
//            //第二个
//            writeText(animal_B.getAQXuZhong(), 6.8F, 31.4F, 1.85F, 1.0F);            //动物种类
//        } else {
//
//        }
            writeText(animal_B.getAQXuZhong(), 6.8F, 7.7F, 1.85F, 1.0F);//动物种类
            //第二个
            writeText(animal_B.getAQXuZhong(), 6.8F, 31.4F, 1.85F, 1.0F);            //动物种类

            String ss = String.valueOf(animal_B.getAQQuantity());
            writeText(changeLock.changeToQuantity_unit(ss) + animal_B.getAQDanWei(), 16.0F, 7.7F, 5.4F, 1.0F); //数量及单位
            writeText(animal_B.getAQYongTu(), 27F, 7.7F, 3.55F, 1.0F);                //用途
            writeText(animal_B.getAQShiQy(), 8.5F, 9.2F, 5.2F, 1.0F);    //启运市
            writeText(animal_B.getAQXianQy(), 15.1F, 9.2F, 5.2F, 1.0F);    //启运县
            writeText(animal_B.getAQXiangQy(), 25.1F, 9.2F, 5.2F, 1.0F);//乡
            writeText(animal_B.getAQCunQy(), 18.6F, 10.4F, 5.2F, 1.0F);        //启运村
            String dff = animal_B.getAQTypeQy();
//        if (dff.equals("养殖场")) {
//            writeText("√", 16.28F, 6.03F, 1F, 1F);
//            writeText("√", 16.28F, 20.59F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.9), JsPxValue(6.3),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.9), JsPxValue(20.8),
//            // paint1);
//        } else if (dff.equals("交易市场")) {
//            writeText("√", 18F, 6.03F, 1F, 1F);
//            writeText("√", 18F, 20.59F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(17.7), JsPxValue(6.3),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(17.7), JsPxValue(20.8),
//            // paint1);
//        }
            writeText(animal_B.getAQShiDd(), 8.5F, 11.55F, 5.2F, 1.0F);            //到达市
            writeText(animal_B.getAQXianDd(), 15.1F, 11.55F, 5.2F, 1.0F);        //到达县
            writeText(animal_B.getAQXiangDd(), 25.1F, 11.55F, 5.2F, 1.0F);        //到达镇
            writeText(animal_B.getAQCunDd(), 15.6F, 12.65F, 5.2F, 1.0F);        //到达村
            String kl = animal_B.getAQTypeDd();
//        if (kl.equals("养殖场")) {
//            writeText("√", 14.28F, 7.55F, 1F, 1F);
//            writeText("√", 14.28F, 22F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(14.1), JsPxValue(7.7),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(14.1), JsPxValue(22.3),
//            // paint1);
//        } else if (kl.equals("屠宰场")) {
//            writeText("√", 16.28F, 7.55F, 1F, 1F);
//            writeText("√", 16.28F, 22F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.8), JsPxValue(7.7),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.8), JsPxValue(22.3),
//            // paint1);
//        } else if (kl.equals("交易市场")) {
//            writeText("√", 18F, 7.55F, 1F, 1F);
//            writeText("√", 18F, 22F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(17.9),JsPxValue(7.7),
//            // paint1);
//            // cachecanvas.drawText("鈭�",JsPxValue(17.9), JsPxValue(22.3),
//            // paint1);
//        }
            if (whichPrinter == 1 && animal_B.getAQEarTag() != null && animal_B.getAQEarTag().getBytes().length > 146) {
                writeText("耳标号详情请见背面", 7F, 14.0F, 15.3F, 1F);               //上联耳标号
                writeText("耳标号详情请见背面", 7F, 37.47F, 15.3F, 1F);                //下联耳标号
            } else {
                writeText(animal_B.getAQEarTag(), 7F, 14.0F, 15.3F, 1F);                //上联耳标号
                writeText(animal_B.getAQEarTag(), 7F, 37.47F, 15.3F, 1F);                //下联耳标号
            }
            String[] nian = animal_B.getDateQF().trim().split(" ");
            String[] split = nian[0].split("-|/");
            String Year = split[0];
            String Month = split[1];
            String Date = split[2];
            String shijian = nian[0];
            writeText(animal_B.getAQVeterinary(), 22.8F, 17.1F, 14.3F, 1F); // 官方签字
            writeText(changeLock.Change(Year), 20.15F, 18.4F, 3F, 1.0F);        //年
            writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 24.0F, 18.4F, 3F, 1.0F);                        //月
            writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.2F, 18.4F, 3F, 1.0F);                            //日
            // writeText(shijian, 18F, 11.3F, 1.2F, 0.5F);
            writeText("本证24小时有效", 5.0F, 17.5F, 14.3F, 1F);//有效1
            // cachecanvas.drawText("瀹樻柟鍏藉尰绛惧瓧1", 810, 780, paint1);


            writeText(animal_B.getAQCargoOwner(), 11.0F, 29.4F, 6.9F, 1.2F);//货主
            writeText(animal_B.getAQPhone(), 24.1F, 29.46F, 5.4F, 1.2F);// 联系电话
            writeText(changeLock.changeToQuantity_unit(ss) + animal_B.getAQDanWei(), 16.0F, 31.3F, 5.4F, 1.0F); //数量及单位
            writeText(animal_B.getAQYongTu(), 26F, 31.35F, 5.2F, 1.0F);                //用途
            writeText(animal_B.getAQShiQy(), 8.5F, 32.85F, 5.2F, 1.0F);    //启运市
            writeText(animal_B.getAQXianQy(), 15.1F, 32.85F, 5.2F, 1.0F);    //启运县
            writeText(animal_B.getAQXiangQy(), 25.1F, 32.85F, 35.2F, 1.0F);//乡
            writeText(animal_B.getAQCunQy(), 18.6F, 34.0F, 5.2F, 1.0F);

            writeText(animal_B.getAQShiDd(), 8.5F, 35.0F, 5.2F, 1.0F);            //到达市
            writeText(animal_B.getAQXianDd(), 15.1F, 35.0F, 5.2F, 1.0F);        //到达县
            writeText(animal_B.getAQXiangDd(), 25.1F, 35.0F, 5.2F, 1.0F);        //到达镇
            writeText(animal_B.getAQCunDd(), 15.6F, 36.5F, 5.2F, 1.0F);        //到达村
            writeText(animal_B.getAQVeterinary(), 22.8F, 40.6F, 14.3F, 1F); // 官方签字
            writeText(changeLock.Change(Year), 20.15F, 42.0F, 3F, 1.0F);        //年
            writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 24.0F, 42.0F, 3F, 1.0F);                        //月
            writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.2F, 42.0F, 3F, 1.0F);                            //日
            // writeText(shijian, 18F, 11.3F, 1.2F, 0.5F);
            writeText("本证24小时有效", 5.0F, 41.0F, 14.3F, 1F);//有效2

            writeText(animal_B.getGuid(), 4F, 4.5F, 6.9F, 1.2F);        //二维码
            writeText(animal_B.getGuid(), 4F, 28F, 6.9F, 1.2F);        //二维码
            Bitmap bitmap11 = Create2DCode(Constance.PRINT_URL_ANIB + animal_B.getGuid());
            cachecanvas.drawBitmap(bitmap11, JsPxValue(4.5), JsPxValue(0.85), paint);
            Bitmap bitmap12 = Create2DCode(Constance.PRINT_URL_ANIB + animal_B.getGuid());
            cachecanvas.drawBitmap(bitmap12, JsPxValue(4.5), JsPxValue(24.3), paint);

//        Bitmap bitmap11 = Create2DCode(animal_B.getGuid() + "");
//        cachecanvas.drawBitmap(bitmap11, JsPxValue(10.0), JsPxValue(16.2), paint);
//        Bitmap bitmap12 = Create2DCode(animal_B.getGuid() + "");
//        cachecanvas.drawBitmap(bitmap12, JsPxValue(10.0), JsPxValue(39.55), paint);

            if (isSign) {
                Bitmap bmpProductB = GetNativeSignImage(10, TableName);
                if (bmpProductB != null) {
                    cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
                            JsPxValue(9.2), paint);
                    cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
                            JsPxValue(23.8), paint);
                    bmpProductB.recycle();
                }

            }

        return cachebitmap;
    }

    /**
     * 动物B查询
     *
     * @param
     * @param isSign
     * @return
     */
    public Bitmap Animal_QueryB(AnimBQueryListBean.DataListEntity animal_B, String TableName, boolean isSign) {
        writeText(animal_B.getAQCargoOwner(), 10.5F, 6.0F, 6.9F, 1.2F);//货主
        writeText(animal_B.getAQPhone(), 24.1F, 6.0F, 5.4F, 1.2F);// 联系电话
//        String requset = animal_B.getAQXuZhong();
//        if (requset.equals("")) {
//
//        } else {
//            writeText(animal_B.getAQXuZhong() + "," + requset, 6.8F, 7.7F, 1.85F, 1.0F);//动物种类
//            //第二个
//            writeText(animal_B.getAQXuZhong() + "," + requset, 6.8F, 31.4F, 1.85F, 1.0F);            //动物种类
//        }
        writeText(animal_B.getAQXuZhong(), 6.8F, 7.7F, 1.85F, 1.0F);//动物种类
        //第二个
        writeText(animal_B.getAQXuZhong(), 6.8F, 31.4F, 1.85F, 1.0F);            //动物种类

        String ss = String.valueOf(animal_B.getAQQuantity());
        writeText(changeLock.changeToQuantity_unit(ss) + animal_B.getAQDanWei(), 16.0F, 7.7F, 5.4F, 1.0F); //数量及单位
        writeText(animal_B.getAQYongTu(), 27F, 7.7F, 3.55F, 1.0F);                //用途
        writeText(animal_B.getAQShiQy(), 8.5F, 9.2F, 5.2F, 1.0F);    //启运市
        writeText(animal_B.getAQXianQy(), 15.1F, 9.2F, 5.2F, 1.0F);    //启运县
        writeText(animal_B.getAQXiangQy(), 25.1F, 9.2F, 5.2F, 1.0F);//乡
        writeText(animal_B.getAQCunQy(), 18.6F, 10.4F, 5.2F, 1.0F);        //启运村
        String dff = animal_B.getAQTypeQy();
//        if (dff.equals("养殖场")) {
//            writeText("√", 16.28F, 6.03F, 1F, 1F);
//            writeText("√", 16.28F, 20.59F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.9), JsPxValue(6.3),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.9), JsPxValue(20.8),
//            // paint1);
//        } else if (dff.equals("交易市场")) {
//            writeText("√", 18F, 6.03F, 1F, 1F);
//            writeText("√", 18F, 20.59F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(17.7), JsPxValue(6.3),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(17.7), JsPxValue(20.8),
//            // paint1);
//        }
        writeText(animal_B.getAQShiDd(), 8.5F, 11.55F, 5.2F, 1.0F);            //到达市
        writeText(animal_B.getAQXianDd(), 15.1F, 11.55F, 5.2F, 1.0F);        //到达县
        writeText(animal_B.getAQXiangDd(), 25.1F, 11.55F, 5.2F, 1.0F);        //到达镇
        writeText(animal_B.getAQCunDd(), 15.6F, 12.65F, 5.2F, 1.0F);        //到达村
        String kl = animal_B.getAQTypeDd();
//        if (kl.equals("养殖场")) {
//            writeText("√", 14.28F, 7.55F, 1F, 1F);
//            writeText("√", 14.28F, 22F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(14.1), JsPxValue(7.7),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(14.1), JsPxValue(22.3),
//            // paint1);
//        } else if (kl.equals("屠宰场")) {
//            writeText("√", 16.28F, 7.55F, 1F, 1F);
//            writeText("√", 16.28F, 22F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.8), JsPxValue(7.7),
//            // paint1);
//            // cachecanvas.drawText("鈭�", JsPxValue(15.8), JsPxValue(22.3),
//            // paint1);
//        } else if (kl.equals("交易市场")) {
//            writeText("√", 18F, 7.55F, 1F, 1F);
//            writeText("√", 18F, 22F, 1F, 1F);
//            // cachecanvas.drawText("鈭�", JsPxValue(17.9),JsPxValue(7.7),
//            // paint1);
//            // cachecanvas.drawText("鈭�",JsPxValue(17.9), JsPxValue(22.3),
//            // paint1);
//        }
        writeText(animal_B.getAQEarTag(), 7F, 14.0F, 15.3F, 1F);                //耳标号
        String[] nian = animal_B.getDateQF().trim().split(" ");
        String[] split = nian[0].split("-|/");
        String Year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];
        writeText(animal_B.getAQVeterinary(), 22.8F, 17.1F, 14.3F, 1F); // 官方签字
        writeText(changeLock.Change(Year), 20.15F, 18.4F, 3F, 1.0F);        //年
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 24.0F, 18.4F, 3F, 1.0F);                        //月
        writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.2F, 18.4F, 3F, 1.0F);                            //日
        // writeText(shijian, 18F, 11.3F, 1.2F, 0.5F);
        writeText("本证24小时有效", 5.0F, 17.5F, 14.3F, 1F);//有效1
        // cachecanvas.drawText("瀹樻柟鍏藉尰绛惧瓧1", 810, 780, paint1);


        writeText(animal_B.getAQCargoOwner(), 11.0F, 29.4F, 6.9F, 1.2F);//货主
        writeText(animal_B.getAQPhone(), 24.1F, 29.46F, 5.4F, 1.2F);// 联系电话
        writeText(changeLock.changeToQuantity_unit(ss) + animal_B.getAQDanWei(), 16.0F, 31.3F, 5.4F, 1.0F); //数量及单位
        writeText(animal_B.getAQYongTu(), 26F, 31.35F, 5.2F, 1.0F);                //用途
        writeText(animal_B.getAQShiQy(), 8.5F, 32.85F, 5.2F, 1.0F);    //启运市
        writeText(animal_B.getAQXianQy(), 15.1F, 32.85F, 5.2F, 1.0F);    //启运县
        writeText(animal_B.getAQXiangQy(), 25.1F, 32.85F, 35.2F, 1.0F);//乡
        writeText(animal_B.getAQCunQy(), 18.6F, 34.0F, 5.2F, 1.0F);

        writeText(animal_B.getAQShiDd(), 8.5F, 35.0F, 5.2F, 1.0F);            //到达市
        writeText(animal_B.getAQXianDd(), 15.1F, 35.0F, 5.2F, 1.0F);        //到达县
        writeText(animal_B.getAQXiangDd(), 25.1F, 35.0F, 5.2F, 1.0F);        //到达镇
        writeText(animal_B.getAQCunDd(), 15.6F, 36.5F, 5.2F, 1.0F);        //到达村
        writeText(animal_B.getAQEarTag(), 7F, 37.47F, 15.3F, 1F);                //耳标号
        writeText(animal_B.getAQVeterinary(), 22.8F, 40.6F, 14.3F, 1F); // 官方签字
        writeText(changeLock.Change(Year), 20.15F, 42.0F, 3F, 1.0F);        //年
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 24.0F, 42.0F, 3F, 1.0F);                        //月
        writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.2F, 42.0F, 3F, 1.0F);                            //日
        // writeText(shijian, 18F, 11.3F, 1.2F, 0.5F);
        writeText("本证24小时有效", 5.0F, 41.0F, 14.3F, 1F);//有效2


        writeText(animal_B.getGuid(), 4F, 4.5F, 6.9F, 1.2F);        //二维码
        writeText(animal_B.getGuid(), 4F, 28F, 6.9F, 1.2F);        //二维码
        Bitmap bitmap11 = Create2DCode(Constance.PRINT_URL_ANIB + animal_B.getGuid());
        cachecanvas.drawBitmap(bitmap11, JsPxValue(4.5), JsPxValue(0.85), paint);
        Bitmap bitmap12 = Create2DCode(Constance.PRINT_URL_ANIB + animal_B.getGuid());
        cachecanvas.drawBitmap(bitmap12, JsPxValue(4.5), JsPxValue(24.3), paint);

//        Bitmap bitmap11 = Create2DCode(animal_B.getGuid() + "");
//        cachecanvas.drawBitmap(bitmap11, JsPxValue(10.0), JsPxValue(16.2), paint);
//        Bitmap bitmap12 = Create2DCode(animal_B.getGuid() + "");
//        cachecanvas.drawBitmap(bitmap12, JsPxValue(10.0), JsPxValue(39.55), paint);

        if (isSign) {
            Bitmap bmpProductB = GetNativeSignImage(10, TableName);
            if (bmpProductB != null) {
                cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
                        JsPxValue(9.2), paint);
                cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
                        JsPxValue(23.8), paint);
                bmpProductB.recycle();
            }

        }
        return cachebitmap;
    }


    /**
     * 产品A
     *
     * @param aListBean
     * @param typePrintProductA
     * @return
     */
    public Bitmap Product_A(Qua_AnimalProductsAListBean.DataListBean aListBean, String typePrintProductA) { // Animal_A animal_A

        String pCargoOwner = aListBean.getPCargoOwner().trim();
        String pPhone = aListBean.getPPhone().trim();

        writeText(aListBean.getGuid() + "", 3.6F, 4.5F, 6.2F, 1.2F);// 二维码


        writeText(pCargoOwner, 12.5F, 6.2F, 6.2F, 1.2F);// 货主
        writeText(pPhone, 23.5F, 6.2F, 6.3F, 1.2F);// 电话
        writeText(aListBean.getPName(), 12.5F, 8.35F, 6.2F, 1F); // 产品名称
        String pQuantity = String.valueOf(aListBean.getPQuantity());//数量
        writeText(changeLock.changeToQuantity_unit1(pQuantity)
                + aListBean.getPDanWei(), 24F, 8.35F, 5.6F, 1.2F);//数量及单位
        writeText(aListBean.getPUnitName().trim() + aListBean.getPProductionunit().trim()
                , 9.0F, 10.5F, 14.3F, 1.3F);        //生产单位名称地址
        writeText(aListBean.getPSheng(), 9.8F, 12.5F, 2.6F, 1.2F);    //达到省
        writeText(aListBean.getPShi(), 14.0F, 12.55F, 2.6F, 1.2F);    // 达到市
        writeText(aListBean.getPXian(), 20.2F, 12.6F, 2.6F, 1.2F);    //达到县
        writeText(aListBean.getPCarrier(), 11.5F, 14.8F, 6.2F, 1.2F);    // 承运人
        writeText(aListBean.getPPhoneCyr(), 23.5F, 14.8F, 6.2F, 1.2F);        //承运人电话
        String[] ss = aListBean.getPYunZai().split(",");
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].trim().equals("公路")) {
                writeText("√", 8.2F, 16.9F, 1F, 1F);

                writeText("√", 12.5F, 16.9F, 1F, 1F);
                writeText("√", 16.7F, 16.9F, 1F, 1F);
                writeText("√", 21.3F, 16.9F, 1F, 1F);
            }
            if (ss[i].trim().equals("铁路")) {
                writeText("√", 7.70F, 10.37F, 1F, 1F);
            }
            if (ss[i].trim().equals("水路")) {
                writeText("√", 10.35F, 10.37F, 1F, 1F);
            }
            if (ss[i].trim().equals("航空")) {
                writeText("√", 13.2F, 10.37F, 1F, 1F);
            }
        }
        writeText(aListBean.getPTrademark(), 9.5F, 19.0F, 5.1F, 1.2F);    //运载工具牌号
        writeText(aListBean.getPDisinfection(), 22.0F, 19.0F, 5.2F, 1F);        //装运消毒
        writeText(changeLock.numToUpperETA1(aListBean.getPYouXiaoRi() + ""), 13.5F, 22.0F, 1.2F, 0.8F);        // 有效到达日

        //官方兽医签字
        writeText(aListBean.getPVeterinary(), 23.0F, 24.8F, 3.0F, 1.0F);

        String[] nian = aListBean.getDateQF().trim().split(" ");
        String[] split = nian[0].split("-|/");
        String Year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];
        writeText(changeLock.Change(Year), 19.8F, 26.7F, 3.0F, 1.0F);// 年
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 24.0F, 26.7F, 3.0F, 1.0F);// 月
        writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.2F, 26.7F, 3.0F, 1.0F);// 日
        writeText(aListBean.getPMemo1(), 8.0F, 39.7F, 14.3F, 4.6F);        //		备注
        writeText(aListBean.getPDwPhone(), 13F, 43.3F, 14.3F, 2.1F);        //动物卫生电话

        //二维码
//        Bitmap bitmap1 = Create2DCode(aListBean.getGuid() + "");
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(10), JsPxValue(24), paint);
//        bitmap1.recycle();

        Bitmap bitmap1 = Create2DCode(Constance.PRINT_URL_PROA + aListBean.getGuid());
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(0.85), paint);
        bitmap1.recycle();

        return cachebitmap;
    }

    /**
     * 产品A查询
     *
     * @param aListBean
     * @param typePrintProductA
     * @return
     */
    public Bitmap Product_QueryA(ProductionAListBean.DataListEntity aListBean, String typePrintProductA) { // Animal_A animal_A
        String pCargoOwner = aListBean.getPCargoOwner().trim();
        String pPhone = aListBean.getPPhone().trim();

        writeText(aListBean.getFSguidId() + "", 3.6F, 4.5F, 6.2F, 1.2F);// 二维码

        writeText(pCargoOwner, 12.5F, 6.2F, 6.2F, 1.2F);// 货主
        writeText(pPhone, 23.5F, 6.2F, 6.3F, 1.2F);// 电话
        writeText(aListBean.getPName(), 12.5F, 8.35F, 6.2F, 1F); // 产品名称
        String pQuantity = String.valueOf(aListBean.getPQuantity());//数量
        writeText(changeLock.changeToQuantity_unit1(pQuantity)
                + aListBean.getPDanWei(), 24F, 8.35F, 5.6F, 1.2F);//数量及单位
        writeText(aListBean.getPUnitName().trim() + aListBean.getPProductionunit().trim()
                , 9.0F, 10.5F, 14.3F, 1.3F);        //生产单位名称地址
        writeText(aListBean.getPSheng(), 9.8F, 12.5F, 2.6F, 1.2F);    //达到省
        writeText(aListBean.getPShi(), 14.0F, 12.55F, 2.6F, 1.2F);    // 达到市
        writeText(aListBean.getPXian(), 20.2F, 12.6F, 2.6F, 1.2F);    //达到县
        writeText(aListBean.getPCarrier(), 11.5F, 14.8F, 6.2F, 1.2F);    // 承运人
        writeText(aListBean.getPPhoneCyr(), 23.5F, 14.8F, 6.2F, 1.2F);        //承运人电话
        String[] ss = aListBean.getPYunZai().split(",");
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].trim().equals("公路")) {
                writeText("√", 8.2F, 16.9F, 1F, 1F);

                writeText("√", 12.5F, 16.9F, 1F, 1F);
                writeText("√", 16.7F, 16.9F, 1F, 1F);
                writeText("√", 21.3F, 16.9F, 1F, 1F);
            }
            if (ss[i].trim().equals("铁路")) {
                writeText("√", 7.70F, 10.37F, 1F, 1F);
            }
            if (ss[i].trim().equals("水路")) {
                writeText("√", 10.35F, 10.37F, 1F, 1F);
            }
            if (ss[i].trim().equals("航空")) {
                writeText("√", 13.2F, 10.37F, 1F, 1F);
            }
        }
        writeText(aListBean.getPTrademark(), 9.5F, 19.0F, 5.1F, 1.2F);    //运载工具牌号
        writeText(aListBean.getPDisinfection(), 22.0F, 19.0F, 5.2F, 1F);        //装运消毒
        writeText(changeLock.numToUpperETA1(aListBean.getPYouXiaoRi() + ""), 13.5F, 22.0F, 1.2F, 0.8F);        // 有效到达日

        //官方兽医签字
        writeText(aListBean.getPVeterinary(), 23.0F, 24.8F, 3.0F, 1.0F);

        String[] nian = aListBean.getDateQF().trim().split(" ");
        String[] split = nian[0].split("-|/");
        String Year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];
        writeText(changeLock.Change(Year), 19.8F, 26.7F, 3.0F, 1.0F);// 年
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 24.0F, 26.7F, 3.0F, 1.0F);// 月
        writeText(changeLock.numToUpperday(Integer.parseInt(Date)), 26.2F, 26.7F, 3.0F, 1.0F);// 日
        writeText(aListBean.getPMemo1(), 8.0F, 39.7F, 14.3F, 4.6F);        //		备注
        writeText(aListBean.getPDwPhone(), 13F, 43.3F, 14.3F, 2.1F);        //动物卫生电话

        //二维码
//        Bitmap bitmap1 = Create2DCode(aListBean.getFSguidId() + "");
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(10), JsPxValue(24), paint);
//        bitmap1.recycle();

        Bitmap bitmap1 = Create2DCode(Constance.PRINT_URL_PROA + aListBean.getFSguidId());
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(0.85), paint);
        bitmap1.recycle();

        writeText("打印测试！！！", 12.5f, 55.0f, 10.0f, 1.5f);


        return cachebitmap;
    }


    /**
     * 产品B
     *
     * @param product_B
     * @param isSign
     * @return
     */
    public Bitmap Product_B(Qua_AnimalProductsBListBean.DataListBean product_B, String TableName, boolean isSign) {


        writeText(product_B.getPBCargoOwner(), 12.0F, 5.7F, 5.0F, 1.0F);// 货主
        writeText(product_B.getPBName(), 25.0F, 5.9F, 3F, 1.0F);//产品名称
        String ss = String.valueOf(product_B.getPBQuantity());
        writeText(changeLock.changeToQuantity_unit(ss)            //数量及单位
                + product_B.getPBDanWei(), 9.0F, 7.5F, 10.0F, 1.0F);
        writeText(product_B.getPBOrigin(), 24.0F, 7.4F, 10F, 1.0F);                //产地
        writeText(product_B.getPBPumAdd(), 9.0F, 9.2F, 10.0F, 1.0F);//名称+地址
        writeText(product_B.getPBDestinations(), 9.0F, 11.0F, 10.0F, 1F);            //目的地
        writeText(product_B.getPBSign(), 9.0F, 12.6F, 15.3F, 1F);                //检疫标志号
        String[] nian = product_B.getDateQF().trim().split(" ");
        String[] split = nian[0].split("-|/");
        String Year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];
        writeText(product_B.getPBRemarks(), 9.0F, 13.8F, 14.3F, 1F); // 备注
        writeText(product_B.getPBVeterinary(), 25.0F, 17.0F, 14.3F, 1F); // 官方兽医签字
//        writeText("管兽医", 25.0F, 17.0F, 14.3F, 1F); // 官方兽医签字
        writeText(changeLock.Change(Year), 21.7F, 17.9F, 3F,        //年
                1.0F);
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 25.5F, 17.9F, 2F, 1.0F);                        //月
        writeText(
                changeLock.numToUpperday(Integer.parseInt(Date)),
                28F, 17.9F, 2F, 1.0F);                            //日
        writeText("本证24小时有效", 5.0F, 17.5F, 14.3F, 1F);//有效1


        writeText(product_B.getPBCargoOwner(), 12.0F, 29.4F, 5.0F, 1.0F);// 货主
        writeText(product_B.getPBName(), 25.0F, 29.4F, 1.85F, 1.0F);//产品名称
        String ss1 = String.valueOf(product_B.getPBQuantity());
        writeText(changeLock.changeToQuantity_unit(ss1)            //数量及单位
                + product_B.getPBDanWei(), 9.0F, 31.0F, 10.0F, 1.0F);
        writeText(product_B.getPBOrigin(), 24.0F, 31.0F, 10F, 1.0F);                //产地
//        writeText(product_B.getPBUnitName(), 4F, 5.53F, 3.2F, 0.6F);    //生产单位名称
//        writeText(product_B.getPBProductionunit(), 8.38F, 5.53F, 3.4F, 0.6F);    //生产单位地址
        writeText(product_B.getPBPumAdd(), 9.0F, 32.7F, 10.0F, 1.0F);//名称+地址
        writeText(product_B.getPBDestinations(), 9.0F, 34.6F, 10.0F, 1F);            //目的地
        writeText(product_B.getPBSign(), 9.0F, 36.1F, 15.3F, 1F);                //检疫标志号
        String[] nian1 = product_B.getDateQF().trim().split(" ");
        String[] split1 = nian1[0].split("-|/");
        String Year1 = split1[0];
        String Month1 = split1[1];
        String Date1 = split1[2];
        //String shijian1 = nian1[0];
        writeText(product_B.getPBRemarks(), 9.0F, 37.8F, 5F, 1.0F); // 备注
        writeText(product_B.getPBVeterinary(), 25.0F, 17.0F, 14.3F, 1F); // 官方兽医签字
//        writeText("管兽医", 25.0F, 40.2F, 5F, 1.0F); // 官方兽医签字
        writeText(changeLock.Change(Year1), 21.7F, 41.5F, 3F,        //年
                1F);
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month1)), 25.5F, 41.5F, 3F, 1.0F);                        //月
        writeText(
                changeLock.numToUpperday(Integer.parseInt(Date1)),
                28F, 41.5F, 3F, 1.0F);                            //日
        //writeText(shijian1, 16.7F, 11.4F, 1.2F, 0.5F);
        writeText("本证24小时有效", 4.5F, 41.0F, 14.3F, 1F);//有效2
//        if (isSign) {
//            Bitmap bmpProductB = GetNativeSignImage(10, TableName);
//            if (bmpProductB != null) {
//                cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
//                        JsPxValue(9.2), paint);
//                cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
//                        JsPxValue(23.8), paint);
//                bmpProductB.recycle();
//            }
//        }


        //二维码
//        Bitmap bitmap1 = Create2DCode(product_B.getGuid() + "");
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(11.0), JsPxValue(16.0), paint);
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(11.0), JsPxValue(39.5), paint);
//        bitmap1.recycle();

        writeText(product_B.getGuid(), 3.8F, 4.4F, 5.0F, 1.0F);    // 二维码
        writeText(product_B.getGuid(), 3.8F, 27.8F, 5.0F, 1.0F);    // 二维码

        Bitmap bitmap1 = Create2DCode(Constance.PRINT_URL_PROB + product_B.getGuid());
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(0.75), paint);
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(24.1), paint);
        bitmap1.recycle();

        return cachebitmap;
    }


    /**
     * 产品B查询
     *
     * @param product_B
     * @param isSign
     * @return
     */
    public Bitmap Product_QueryB(ProductBListBean.DataListEntity product_B, String TableName, boolean isSign) {
        writeText(product_B.getPBCargoOwner(), 12.0F, 5.7F, 5.0F, 1.0F);// 货主
        writeText(product_B.getPBName(), 25.0F, 5.9F, 3F, 1.0F);//产品名称
        String ss = String.valueOf(product_B.getPBQuantity());
        writeText(changeLock.changeToQuantity_unit(ss)            //数量及单位
                + product_B.getPBDanWei(), 9.0F, 7.5F, 10.0F, 1.0F);
        writeText(product_B.getPBOrigin(), 24.0F, 7.4F, 10F, 1.0F);                //产地
        writeText(product_B.getPBPumAdd(), 9.0F, 9.2F, 10.0F, 1.0F);//名称+地址
        writeText(product_B.getPBDestinations(), 9.0F, 11.0F, 10.0F, 1F);            //目的地
        writeText(product_B.getPBSign(), 9.0F, 12.6F, 15.3F, 1F);                //检疫标志号
        String[] nian = product_B.getDateQF().trim().split(" ");
        String[] split = nian[0].split("-|/");
        String Year = split[0];
        String Month = split[1];
        String Date = split[2];
        String shijian = nian[0];
        writeText(product_B.getPBRemarks(), 9.0F, 13.8F, 14.3F, 1F); // 备注
        writeText(product_B.getPBVeterinary(), 25.0F, 17.0F, 14.3F, 1F); // 官方兽医签字
//        writeText("管兽医", 25.0F, 17.0F, 14.3F, 1F); // 官方兽医签字
        writeText(changeLock.Change(Year), 21.7F, 17.9F, 3F,        //年
                1.0F);
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month)), 25.5F, 17.9F, 2F, 1.0F);                        //月
        writeText(
                changeLock.numToUpperday(Integer.parseInt(Date)),
                28F, 17.9F, 2F, 1.0F);                            //日
        writeText("本证24小时有效", 5.0F, 17.5F, 14.3F, 1F);//有效1


        writeText(product_B.getPBCargoOwner(), 12.0F, 29.4F, 5.0F, 1.0F);// 货主
        writeText(product_B.getPBName(), 25.0F, 29.4F, 1.85F, 1.0F);//产品名称
        String ss1 = String.valueOf(product_B.getPBQuantity());
        writeText(changeLock.changeToQuantity_unit(ss1)            //数量及单位
                + product_B.getPBDanWei(), 9.0F, 31.0F, 10.0F, 1.0F);
        writeText(product_B.getPBOrigin(), 24.0F, 31.0F, 10F, 1.0F);                //产地
//        writeText(product_B.getPBUnitName(), 4F, 5.53F, 3.2F, 0.6F);    //生产单位名称
//        writeText(product_B.getPBProductionunit(), 8.38F, 5.53F, 3.4F, 0.6F);    //生产单位地址
        writeText(product_B.getPBPumAdd(), 9.0F, 32.7F, 10.0F, 1.0F);//名称+地址
        writeText(product_B.getPBDestinations(), 9.0F, 34.6F, 10.0F, 1F);            //目的地
        writeText(product_B.getPBSign(), 9.0F, 36.1F, 15.3F, 1F);                //检疫标志号
        String[] nian1 = product_B.getDateQF().trim().split(" ");
        String[] split1 = nian1[0].split("-|/");
        String Year1 = split1[0];
        String Month1 = split1[1];
        String Date1 = split1[2];
        //String shijian1 = nian1[0];
        writeText(product_B.getPBRemarks(), 9.0F, 37.8F, 5F, 1.0F); // 备注
        writeText(product_B.getPBVeterinary(), 25.0F, 17.0F, 14.3F, 1F); // 官方兽医签字
//        writeText("管兽医", 25.0F, 40.2F, 5F, 1.0F); // 官方兽医签字
        writeText(changeLock.Change(Year1), 21.7F, 41.5F, 3F,        //年
                1F);
        writeText(changeLock.numToUpperModer(Integer.parseInt(Month1)), 25.5F, 41.5F, 3F, 1.0F);                        //月
        writeText(
                changeLock.numToUpperday(Integer.parseInt(Date1)),
                28F, 41.5F, 3F, 1.0F);                            //日
        //writeText(shijian1, 16.7F, 11.4F, 1.2F, 0.5F);
        writeText("本证24小时有效", 4.5F, 41.0F, 14.3F, 1F);//有效2
//        if (isSign) {
//            Bitmap bmpProductB = GetNativeSignImage(10, TableName);
//            if (bmpProductB != null) {
//                cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
//                        JsPxValue(9.2), paint);
//                cachecanvas.drawBitmap(bmpProductB, JsPxValue(15.2),
//                        JsPxValue(23.8), paint);
//                bmpProductB.recycle();
//            }
//        }


        //二维码
//        Bitmap bitmap1 = Create2DCode(product_B.getFSguidId() + "");
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(11.0), JsPxValue(16.0), paint);
//        cachecanvas.drawBitmap(bitmap1, JsPxValue(11.0), JsPxValue(39.5), paint);
//        bitmap1.recycle();

        writeText(product_B.getFSguidId(), 3.8F, 4.4F, 5.0F, 1.0F);    // 二维码
        writeText(product_B.getFSguidId(), 3.8F, 27.8F, 5.0F, 1.0F);    // 二维码

        Bitmap bitmap1 = Create2DCode(Constance.PRINT_URL_PROB + product_B.getFSguidId());
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(0.75), paint);
        cachecanvas.drawBitmap(bitmap1, JsPxValue(3.8), JsPxValue(24.1), paint);
        bitmap1.recycle();

        if (isSign) {
            Bitmap bmpProductB = GetNativeSignImage(10, TableName);
            if (bmpProductB != null) {
                cachecanvas.drawBitmap(bmpProductB, JsPxValue(11.0),
                        JsPxValue(16.0), paint);
                cachecanvas.drawBitmap(bmpProductB, JsPxValue(11.0),
                        JsPxValue(39.5), paint);
                bmpProductB.recycle();
            }
        }
        return cachebitmap;
    }

    /**
     * 屠宰“双随机”
     */
    public List<String> DoubleRandom_OnePublic(UploadDoubleRandomBean bean) {
        cachebitmap = Bitmap.createBitmap(1810, 1280, Config.ARGB_8888);
        cachecanvas = new Canvas(cachebitmap);
        cachecanvas.drawColor(Color.WHITE);
        IProxy proxy = new SSJProxy(context, cachecanvas, bean, cachebitmap);
        return proxy.draws();
    }


    /***
     * 目前无用
     *
     * @return
     */
    @SuppressWarnings("static-access")
    private Bitmap GetNativeSignImage(int multiple, String TableName) {
        pref = context.getSharedPreferences("data", context.MODE_PRIVATE);
        String Time = pref.getString("UserAccount", "");

        Bitmap map = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = (int) multiple; /* 鍥剧墖闀垮鏂瑰悜缂╁皬鍊嶆暟 */
        options.inJustDecodeBounds = false;
        try {
            File file = new File(dir + File.separator + "image" + File.separator + TableName + "Sign_"
                    + Time + ".jpg");
            Uri uri = Uri.fromFile(file);
            InputStream input = context.getContentResolver().openInputStream(uri);
            map = BitmapFactory.decodeStream(input, null, options);
            input.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();

            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /***
     * @param str
     * @return Bitmap
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Bitmap Create2DCode(String str) {

        BitMatrix matrix = null;
        Hashtable hints = new Hashtable();
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        try {
            //编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,
                    170, 170, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        int width = matrix.getWidth();
        int height = matrix.getHeight();
        //像素
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }
        // 创建一张bitmap图片，采用最高的图片效果ARGB_8888
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Config.ARGB_8888);
        // 将上面的二维码颜色数组传入，生成图片颜色
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }


    public <T> Bitmap getBitmao(T t, int whichPrinter) {
        if (t instanceof UploadQuarantineProcessNotBeanDetil) {
            Bitmap fronBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.jianyichulitngzhi);
            merge(fronBitmap);
            return new Jianyitonngzhi(context, cachecanvas, (UploadQuarantineProcessNotBeanDetil) t, cachebitmap, mTime).draw(whichPrinter);
        } else if (t instanceof QuarantineDealListQueryBean.DataListBean) {
            Bitmap fronBitmapQuery = BitmapFactory.decodeResource(context.getResources(), R.drawable.jianyichulitngzhi);
            merge(fronBitmapQuery);
            return new JianyichuliQuery(context, cachecanvas, (QuarantineDealListQueryBean.DataListBean) t, cachebitmap).draw(whichPrinter);
        } else if (t instanceof JianyiChuliTongzhidanListBean.DataListBean) {
            Bitmap fronBitmapQuery = BitmapFactory.decodeResource(context.getResources(), R.drawable.jianyichulitngzhi);
            merge(fronBitmapQuery);
            return new JianyichuliQueryPrint(context, cachecanvas, (JianyiChuliTongzhidanListBean.DataListBean) t, cachebitmap).draw(whichPrinter);
        } else if (t instanceof AnimProcessListBean.DataListBean) {
            if (((AnimProcessListBean.DataListBean) t).getFqSbType().contains("动物")) {
                Bitmap animBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.animapplydetilgai);
                merge(animBitmap);
                return new AnimApplyDetil(context, cachecanvas, (AnimProcessListBean.DataListBean) t, cachebitmap).draw(whichPrinter);
            } else if (((AnimProcessListBean.DataListBean) t).getFqSbType().contains("繁殖材料")) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.applydetilgai);
                merge(bitmap);
                return new ApplyDetil(context, cachecanvas, (AnimProcessListBean.DataListBean) t, cachebitmap).draw(whichPrinter);
            }
        }
        return null;
    }


    private void merge(Bitmap frontBitmap) {
        cachebitmap = BitmapUtils.mergeBitmap(cachebitmap, frontBitmap);
        if (cachebitmap != null) cachecanvas = new Canvas(cachebitmap);
    }

    public <T> Bitmap getBitmap(T t) {
        if (t instanceof AH_AnimalOrigin.DataListBean) { //动物产地工作记录单
            Bitmap frontBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.animchandijianyi);
            merge(frontBitmap);
            return new AnimOriginProxy(context, cachecanvas, (AH_AnimalOrigin.DataListBean) t, cachebitmap).draw();
        } else if (t instanceof AnimRuZhongYongRecordBean.DataListBean) {
            Bitmap frontBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ruzhongjiludan);
            merge(frontBitmap);
            return new MilkAnimWorkProxy(context, cachecanvas, (AnimRuZhongYongRecordBean.DataListBean) t, cachebitmap).draw();
        } else if (t instanceof AnimZhongDanRedordBean.DataListBean) {
            Bitmap frontBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.zhongdanpeitai);
            merge(frontBitmap);
            return new DanQuarantineWorkProxy(context, cachecanvas, (AnimZhongDanRedordBean.DataListBean) t, cachebitmap).draw();
        }
        return cachebitmap;
    }

    @Override
    public void setTime(String time, long longTime) {
        this.mTime = time;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


}
