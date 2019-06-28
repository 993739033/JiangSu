package com.wyw.jiangsu.activity.print;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jq.printer.JQPrinter;
import com.jq.printer.jpl.Page;
import com.qs.helper.printer.PrintService;
import com.qs.helper.printer.PrinterClass;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.BaseActivity;
import com.wyw.jiangsu.activity.PrintsSettingActivity;
import com.wyw.jiangsu.bean.AnimBQueryListBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.ChangeLock;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;


public class PrintsActivity extends BaseActivity implements OnClickListener {

    private AnimBlistBean.DataListBean bean2;
    private int index;
    private AnimBQueryListBean.DataListEntity bean3;
    private Canvas canvas;
    private Button print, btn_print2, btn_print3;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_items);
        setTitle("打印");
        init();

        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index", 0);
        if (index == 0) {
            bean2 = (AnimBlistBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
        } else if (index == 1) {
            bean3 = (AnimBQueryListBean.DataListEntity) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
        }

        int position = bundle.getInt("position", 0);
        if (PrintService.pl != null && (position == 0 || position == 1) && PrintService.pl.getState() != PrinterClass.STATE_CONNECTED) {
            Intent intent = new Intent();
            intent.setClass(PrintsActivity.this, PrintSettingActivity.class);
            startActivityForResult(intent, 0);
        }

    }


    private void init() {
        print = (Button) findViewById(R.id.btn_print);
        btn_print2 = (Button) findViewById(R.id.btn_print2);
        btn_print3 = (Button) findViewById(R.id.btn_print3);
        print.setOnClickListener(this);
        btn_print2.setOnClickListener(this);
        btn_print3.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                if (PrintService.pl.getState() != PrinterClass.STATE_CONNECTED) {
                    PrintsActivity.this.finish();
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        PrintService.pl.disconnect();
        super.onDestroy();
    }

    private ChangeLock changeLock = new ChangeLock();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_print:
                int printer = getSharedPreferences("print", MODE_PRIVATE).getInt("printer", 0);
                if(printer==2) {
                    if (index == 0) {
                        PrintImage();
                        PrintImage2();
                    }
                    if (index == 1) {
                        PrintImage3();
                        PrintImage4();
                    }
                }else if (printer==3){
//                    if (index == 0) {
////                        PrintImage_2();
//                        // TODO: 2018/4/11
//                        PrintImage_1();
//                        PrintImage2_1();
//                    }
//                    if (index == 1) {
////                        PrintImage_2();
//                        // TODO: 2018/4/11
//                        PrintImage3_1();
////                        PrintImage4_1();
//                    }
                }
                break;
            case R.id.btn_print2:
                if (index == 1) {
                    Intent intent3 = new Intent(this, PrintsSettingActivity.class);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("index", 1);
                    bundle3.putSerializable(Constance.START_ACTIVITY_DATA, bean3);
                    intent3.putExtras(bundle3);
                    startActivity(intent3);
                }

                if (index == 0) {
                    Intent intent3 = new Intent(this, PrintsSettingActivity.class);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("index", 0);
                    bundle3.putSerializable(Constance.START_ACTIVITY_DATA, bean2);
                    intent3.putExtras(bundle3);
                    startActivity(intent3);

                }
                break;

            case R.id.btn_print3:
                if (index == 1) {
                    print(bean3);
                }

                if (index == 0) {
                    print2(bean2);
                }

                break;

        }
    }

    private void print2(AnimBlistBean.DataListBean bean2) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(32F);

        Bitmap bitmap = Bitmap.createBitmap(700, 2000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean2.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean2.getAQCargoOwner(), 6.9F, 12.5F, 10.2F, 2F);//货主
        writeText(bean2.getAQPhone(), 6.9F, 15.8F, 10.2F, 2F);//电话
        writeText(bean2.getAQXuZhong(), 6.9F, 19.3F, 10.2F, 2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean2.getAQQuantity())) + bean2.getAQDanWei(), 6.9F, 22.8F, 10.2F, 2F);//数量及单位
        writeText(bean2.getAQYongTu(), 6.9F, 26F, 10.3F, 2F);//用途
        writeText(bean2.getAQShiQy() + bean2.getAQXianQy() + bean2.getAQXiangQy() + bean2.getAQCunQy(), 6.9F, 29F, 10.2F, 2.5F);//起运地点
        writeText(bean2.getAQShiDd() + bean2.getAQXianDd() + bean2.getAQXiangDd() + bean2.getAQCunDd(), 6.9F, 32.5F, 10.2F, 2.5F);//到达地点
        writeText(bean2.getAQEarTag(), 6.9F, 35.8F, 10.2F, 3F);//耳标号
        writeText(bean2.getAQVeterinary(), 8.5F, 42.5F, 6F, 2F); // 官方签字
        String[] nians = bean2.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 44F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 44F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 44F, 3F, 1.0F); //日

        saveImage(bitmap);
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);

    }

    private void print(AnimBQueryListBean.DataListEntity bean3) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(32F);

        Bitmap bitmap = Bitmap.createBitmap(700, 2000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean3.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean3.getAQCargoOwner(), 6.9F, 12.5F, 10.2F, 2F);//货主
        writeText(bean3.getAQPhone(), 6.9F, 15.8F, 10.2F, 2F);//电话
        writeText(bean3.getAQXuZhong(), 6.9F, 19.3F, 10.2F, 2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei(), 6.9F, 22.8F, 10.2F, 2F);//数量及单位
        writeText(bean3.getAQYongTu(), 6.9F, 26F, 10.3F, 2F);//用途
        writeText(bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy(), 6.9F, 29F, 10.2F, 2.5F);//起运地点
        writeText(bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd(), 6.9F, 32.5F, 10.2F, 2.5F);//到达地点
        writeText(bean3.getAQEarTag(), 6.9F, 35.8F, 10.2F, 3F);//耳标号
        writeText(bean3.getAQVeterinary(), 8.5F, 42.5F, 6F, 2F); // 官方签字
        String[] nians = bean3.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 44F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 44F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 44F, 3F, 1.0F); //日

        saveImage(bitmap);
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);

    }


    private void PrintImage4() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);

        Bitmap bitmap = Bitmap.createBitmap(700, 1807, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean3.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean3.getAQCargoOwner(), 6.5F, 11F, 5.3F, 1.2F);//货主
        writeText(bean3.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean3.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean3.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean3.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean3.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean3.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
    }

    private void PrintImage3() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);

        Bitmap bitmap = Bitmap.createBitmap(700, 1808, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean3.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean3.getAQCargoOwner(), 6.5F, 11F, 5.3F, 1.2F);//货主
        writeText(bean3.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean3.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean3.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean3.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean3.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean3.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
    }


    private void PrintImage() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);

        Bitmap bitmap = Bitmap.createBitmap(700, 1808, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean2.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean2.getAQCargoOwner(), 6.5F, 11F, 5.3F, 1.2F);//货主
        writeText(bean2.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean2.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean2.getAQQuantity())) + bean2.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean2.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean2.getAQShiQy() + bean2.getAQXianQy() + bean2.getAQXiangQy() + bean2.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean2.getAQShiDd() + bean2.getAQXianDd() + bean2.getAQXiangDd() + bean2.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean2.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean2.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean2.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
    }

    private void PrintImage2() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);
        Bitmap bitmap = Bitmap.createBitmap(700, 1807, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean2.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean2.getAQCargoOwner(), 6.5F, 11F, 5.3F, 1.2F);//货主
        writeText(bean2.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean2.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean2.getAQQuantity())) + bean2.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean2.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean2.getAQShiQy() + bean2.getAQXianQy() + bean2.getAQXiangQy() + bean2.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean2.getAQShiDd() + bean2.getAQXianDd() + bean2.getAQXiangDd() + bean2.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean2.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean2.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean2.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
    }

    private void PrintImage_1() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);

        Bitmap bitmap = Bitmap.createBitmap(700, 2500, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean2.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean2.getAQCargoOwner(), 6.5F, 11F, 5.3F, 1.2F);//货主
        writeText(bean2.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean2.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean2.getAQQuantity())) + bean2.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean2.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean2.getAQShiQy() + bean2.getAQXianQy() + bean2.getAQXiangQy() + bean2.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean2.getAQShiDd() + bean2.getAQXianDd() + bean2.getAQXiangDd() + bean2.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean2.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean2.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean2.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
    }

    private void PrintImage2_1() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);
        Bitmap bitmap = Bitmap.createBitmap(700, 2650, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean2.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean2.getAQCargoOwner(), 6.5F, 11F, 5.3F, 1.2F);//货主
        writeText(bean2.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean2.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean2.getAQQuantity())) + bean2.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean2.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean2.getAQShiQy() + bean2.getAQXianQy() + bean2.getAQXiangQy() + bean2.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean2.getAQShiDd() + bean2.getAQXianDd() + bean2.getAQXiangDd() + bean2.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean2.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean2.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean2.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
    }

    private void PrintImage4_1() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);

        Bitmap bitmap = Bitmap.createBitmap(700, 2650, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean3.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(5), paint);
        writeText(bean3.getAQCargoOwner(), 6.5F, 11F, 5.3F, 1.2F);//货主
        writeText(bean3.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean3.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean3.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean3.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean3.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean3.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
    }

    private void PrintImage3_1() {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);

        JQPrinter printer = new JQPrinter();
        printer.jpl.page.start(0,0,700,1650, Page.PAGE_ROTATE.x0);
//        printer.jpl.page.start(0,0,700,1650, Page.PAGE_ROTATE.x0);
        Bitmap bitmap = Bitmap.createBitmap(700, 1650, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean3.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.8), JsPxValue(5.6), paint);

        writeText(bean3.getAQCargoOwner(), 9F, 16.4F, 30F, 4.4F);//货主
        writeText(bean3.getAQPhone(), 6.5F, 14.0F, 5.3F, 1.2F);//电话
        writeText(bean3.getAQXuZhong(), 6.5F, 17.2F, 5.3F, 1.2F);//动物种类
        writeText(changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei(), 6.5F, 20.2F, 5.3F, 1.2F);//数量及单位
        writeText(bean3.getAQYongTu(), 6.5F, 23.2F, 5.3F, 1.2F);//用途
        writeText(bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy(), 6.5F, 25.8F, 11F, 3F);//起运地点
        writeText(bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd(), 6.5F, 29F, 11F, 3F);//到达地点
        writeText(bean3.getAQEarTag(), 6.5F, 32.5F, 11F, 2.2F);//耳标号
        writeText(bean3.getAQVeterinary(), 8.5F, 37.8F, 5.3F, 1F); // 官方签字

        String[] nians = bean3.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText(Years, 7F, 39.5F, 3F, 1.0F);//年
        writeText(Months, 10.5F, 39.5F, 3F, 1.0F); //月
        writeText(Dates, 14.0F, 39.5F, 3F, 1.0F); //日
        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PhotoUtils.saveBitmap(Environment.getExternalStorageDirectory()+File.separator+"anim1.jpg",bitmap);
//        PrintService.pl.printImage(bitmap);
//        printer.jpl.image.drawOut(0,0,this.getResources(), bitmap, Image.IMAGE_ROTATE.ANGLE_0);
        printer.esc.image.drawOut(0,0, bitmap);

    }


    private boolean isSize(String str) {
        if (str.length() > 60) {
            return true;
        } else {
            return false;
        }
    }


    public static File saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "aaaa");
        if (appDir.exists()) {
            appDir.delete();
        }
        appDir.mkdir();
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appDir;
    }

    public Bitmap Create2DCode(String str) {

        BitMatrix matrix = null;
        Hashtable hints = new Hashtable();
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        try {
            //编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,
                    300, 300, hints);
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
                Bitmap.Config.ARGB_8888);
        // 将上面的二维码颜色数组传入，生成图片颜色
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private void writeText(String value, float x, float y, float width, float height) {

        TextPaint textPaint = new TextPaint();

        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(32F);
        int w = (int) JsPxValue(width);

        // TODO: 2018/4/12
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLUE);
//        RectF rectF = new RectF(JsPxValue(x), JsPxValue(y), JsPxValue(x + width), JsPxValue(y + height));
//        canvas.drawRect(rectF, paint);

        if (value != null) {
            StaticLayout layout = new StaticLayout(value, textPaint, w, Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, true);
            float h = JsPxValue(height);
            while (true) {
                if (layout.getHeight() <= h) {
                    break;
                } else {
                    textPaint.setTextSize(textPaint.getTextSize() - 1);
                    layout = new StaticLayout(value, textPaint, w,
                            Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, true);
                }
            }
            canvas.translate(JsPxValue(x), JsPxValue(y));
            layout.draw(canvas);
            canvas.translate(-JsPxValue(x), -JsPxValue(y));
        }
    }


    private float JsPxValue(double cm) {
        double rv = cm * (96 / 2.54);
        return Float.parseFloat(Double.toString(rv));
    }


    @Override
    public void bindData() {

    }

    @Override
    public void bindListener() {

    }
}
