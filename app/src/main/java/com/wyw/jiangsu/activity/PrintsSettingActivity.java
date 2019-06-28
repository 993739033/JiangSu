package com.wyw.jiangsu.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.qs.helper.printer.PrintService;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimBQueryListBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.ChangeLock;
import com.wyw.jiangsu.utils.EditInputFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrintsSettingActivity extends AppCompatActivity {


    @BindView(R.id.oneLie)
    EditText oneLie;
    @BindView(R.id.twoLie)
    EditText twoLie;
    @BindView(R.id.oneHang)
    EditText oneHang;
    @BindView(R.id.twoHang)
    EditText twoHang;
    @BindView(R.id.threeHang)
    EditText threeHang;
    @BindView(R.id.fourHang)
    EditText fourHang;
    @BindView(R.id.fiveHang)
    EditText fiveHang;
    @BindView(R.id.sixHang)
    EditText sixHang;
    @BindView(R.id.sevenHang)
    EditText sevenHang;
    @BindView(R.id.eightHang)
    EditText eightHang;
    @BindView(R.id.liezongXiangSu)
    EditText liezongXiangSu;
    @BindView(R.id.hangzongXiangSu)
    EditText hangzongXiangSu;
    @BindView(R.id.btn_print)
    Button btnPrint;
    private AnimBlistBean.DataListBean bean2;
    private int index;
    private AnimBQueryListBean.DataListEntity bean3;
    private float lieZongShu = 950;
    private float hangZongShu = 1600;
    private float vs = 0;
    private float v1 = 0;
    private float v2 = 0;
    private float v3 = 0;
    private float v4 = 0;
    private float v5 = 0;
    private float v6 = 0;
    private float v7 = 0;
    private float v8 = 0;
    private float lie = 0;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prints_setting);
        ButterKnife.bind(this);


        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index", 0);
        if (index == 0) {
            bean2 = (AnimBlistBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
        } else if (index == 1) {
            bean3 = (AnimBQueryListBean.DataListEntity) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
        }

        total();

        twoLie.setText(String.valueOf(lieZongShu - lie));
        liezongXiangSu.setText(String.valueOf(lieZongShu));
        hangzongXiangSu.setText(String.valueOf(vs));

        InputFilter[] filters = {new EditInputFilter()};
        oneLie.setFilters(filters);
        oneLie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = oneLie.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    float v = Float.parseFloat(s);
                    twoLie.setText(String.valueOf(lieZongShu - v));
                } else {
                    twoLie.setText("");
                }

            }
        });


        oneHang.setFilters(filters);
        twoHang.setFilters(filters);
        threeHang.setFilters(filters);
        fourHang.setFilters(filters);
        fiveHang.setFilters(filters);
        sixHang.setFilters(filters);
        sevenHang.setFilters(filters);
        eightHang.setFilters(filters);

        oneHang.addTextChangedListener(new DoubleTextWatcher());
        twoHang.addTextChangedListener(new DoubleTextWatcher());
        threeHang.addTextChangedListener(new DoubleTextWatcher());
        fourHang.addTextChangedListener(new DoubleTextWatcher());
        fiveHang.addTextChangedListener(new DoubleTextWatcher());
        sixHang.addTextChangedListener(new DoubleTextWatcher());
        sevenHang.addTextChangedListener(new DoubleTextWatcher());
        eightHang.addTextChangedListener(new DoubleTextWatcher());


        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vs > hangZongShu) {
                    Toast.makeText(PrintsSettingActivity.this, "行高总像素不能超过"+hangZongShu+"像素", Toast.LENGTH_SHORT).show();
                } else {
                    total();
                    if (index == 0) {
                        y = 450;
                        print(bean2);
                    }
                    if (index == 1) {
                        y = 450;
                        print2(bean3);
                    }

                }

            }
        });

    }

    private float y = 450;
    private float x = 0;
    private float y1x = 0;

    private void oneLine(float x1, float y1) {
        y = y + y1;
        y1x = y - y1;
        canvas.drawLine(0, y, 950, y, paint);//横线
        canvas.drawLine(2, y1x, 2, y, paint);//竖线1
        canvas.drawLine(x1, y1x, x1, y, paint);//竖线2
        canvas.drawLine(950, y1x, 950, y, paint);//竖线3
    }


    private ChangeLock changeLock = new ChangeLock();

    private void print(AnimBlistBean.DataListBean bean2) {
        x = lie;
        paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(42);
        Bitmap bitmap = Bitmap.createBitmap(1000, 2800, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean2.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(4), paint);

        canvas.drawLine(0, y, 950, y, paint);//横线

        float one = 170;
        oneLine(x, one);
        writeText2("货主", cm(0), cm(450 + one / 3), cm(x), cm(one / 2));//货主
        writeText2(bean2.getAQCargoOwner(), cm(x), cm(450 + one / 3), cm(950 - x), cm(one / 2));//货主
        float value = 450 + one;

        float two = 170;
        oneLine(x, two);
        writeText2("电话", cm(0), cm(value + two / 3), cm(x), cm(two / 2));//
        writeText2(bean2.getAQPhone(), cm(x), cm(value + two / 3), cm(950 - x), cm(two / 2));//电话
        float valuePhone = value + two;

        float three = 170;
        oneLine(x, three);
        writeText2("动物种类", cm(0), cm(valuePhone + three / 3), cm(x), cm(three / 2));//
        writeText2(bean2.getAQXuZhong(), cm(x), cm(valuePhone + three / 3), cm(950 - x), cm(three / 2));//电话
        float valueType = valuePhone + three;

        float four = 170;
        oneLine(x, four);
        writeText2("数量及单位", cm(0), cm(valueType + four / 3), cm(x), cm(four / 2));
        writeText2(changeLock.changeToQuantity_unit(String.valueOf(bean2.getAQQuantity())) + bean2.getAQDanWei(), cm(x), cm(valueType + four / 3), cm(950 - x), cm(four / 2));//数量及单位
        float valueSum = valueType + four;


        float five = 170;
        oneLine(x, five);
        writeText2("用途", cm(0), cm(valueSum + five / 3), cm(x), cm(five / 2));
        writeText2(bean2.getAQYongTu(), cm(x), cm(valueSum + five / 3), cm(950 - x), cm(five / 2));//用途
        float valueYongTu = valueSum + five;

        float six = 170;
        oneLine(x, six);
        writeText2("起运地点", cm(0), cm(valueYongTu + six / 3), cm(x), cm(six / 2));
        writeText2(bean2.getAQShiQy() + bean2.getAQXianQy() + bean2.getAQXiangQy() + bean2.getAQCunQy(), cm(x), cm(valueYongTu + six / 6), cm(950 - x), cm(six / 1.5));//起运地点
        float valueQiYun = valueYongTu + six;

        float seven = 170;
        oneLine(x, seven);
        writeText2("到达地点", cm(0), cm(valueQiYun + seven / 3), cm(x), cm(seven / 2));
        writeText2(bean2.getAQShiDd() + bean2.getAQXianDd() + bean2.getAQXiangDd() + bean2.getAQCunDd(), cm(x), cm(valueQiYun + seven / 6), cm(950 - x), cm(seven / 1.5));//到达地点
        float valueDaoDa = valueQiYun + seven;

        float eight = 270;
        oneLine(x, eight);
        writeText2("耳标号", cm(0), cm(valueDaoDa + eight / 3), cm(x), cm(eight / 2));
        writeText2(bean2.getAQEarTag(), cm(x), isSize(bean2.getAQEarTag()) ? cm(valueDaoDa) : cm(valueDaoDa + eight / 6), cm(950 - x), isSize(bean2.getAQEarTag()) ? cm(eight) : cm(eight / 1.5));//耳标号
        float valueEr = valueDaoDa + eight;


        writeText2(bean2.getAQVeterinary(), 11F, cm(2270), 5.3F, 2F); // 官方签字

        String[] nians = bean2.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText2(Years, 10.5F, cm(2350), 3F, 2.0F);//年
        writeText2(Months, 15F, cm(2350), 3F, 2.0F); //月
        writeText2(Dates, 19.5F, cm(2350), 3F, 2.0F); //日

        saveImage(bitmap);

//        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
//        PrintService.pl.printImage(bitmap);
    }

    private void print2(AnimBQueryListBean.DataListEntity bean3) {
        x = lie;
        paint = new Paint();
        paint.setAntiAlias(true); // //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(Color.BLACK);
        paint.setTextSize(42);
        Bitmap bitmap = Bitmap.createBitmap(1000, 2800, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        Bitmap bitmaps = Create2DCode(Constance.PRINT_URL_ANIB + bean3.getGuid());
        canvas.drawBitmap(bitmaps, JsPxValue(0.5), JsPxValue(4), paint);

        canvas.drawLine(0, y, 950, y, paint);//横线

        float one = v1;
        oneLine(x, one);
        writeText2("货主", cm(0), cm(450 + one / 3), cm(x), cm(one / 2));//货主
        writeText2(bean3.getAQCargoOwner(), cm(x), cm(450 + one / 3), cm(950 - x), cm(one / 2));//货主
        float value = 450 + one;

        float two = v2;
        oneLine(x, two);
        writeText2("电话", cm(0), cm(value + two / 3), cm(x), cm(two / 2));//
        writeText2(bean3.getAQPhone(), cm(x), cm(value + two / 3), cm(950 - x), cm(two / 2));//电话
        float valuePhone = value + two;

        float three = v3;
        oneLine(x, three);
        writeText2("动物种类", cm(0), cm(valuePhone + three / 3), cm(x), cm(three / 2));//
        writeText2(bean3.getAQXuZhong(), cm(x), cm(valuePhone + three / 3), cm(950 - x), cm(three / 2));//电话
        float valueType = valuePhone + three;

        float four = v4;
        oneLine(x, four);
        writeText2("数量及单位", cm(0), cm(valueType + four / 3), cm(x), cm(four / 2));
        writeText2(changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei(), cm(x), cm(valueType + four / 3), cm(950 - x), cm(four / 2));//数量及单位
        float valueSum = valueType + four;


        float five = v5;
        oneLine(x, five);
        writeText2("用途", cm(0), cm(valueSum + five / 3), cm(x), cm(five / 2));
        writeText2(bean3.getAQYongTu(), cm(x), cm(valueSum + five / 3), cm(950 - x), cm(five / 2));//用途
        float valueYongTu = valueSum + five;

        float six = v6;
        oneLine(x, six);
        writeText2("起运地点", cm(0), cm(valueYongTu + six / 3), cm(x), cm(six / 2));
        writeText2(bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy(), cm(x), cm(valueYongTu + six / 6), cm(950 - x), cm(six / 1.5));//起运地点
        float valueQiYun = valueYongTu + six;

        float seven = v7;
        oneLine(x, seven);
        writeText2("到达地点", cm(0), cm(valueQiYun + seven / 3), cm(x), cm(seven / 2));
        writeText2(bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd(), cm(x), cm(valueQiYun + seven / 6), cm(950 - x), cm(seven / 1.5));//到达地点
        float valueDaoDa = valueQiYun + seven;

        float eight = v8;
        oneLine(x, eight);
        writeText2("耳标号", cm(0), cm(valueDaoDa + eight / 3), cm(x), cm(eight / 2));
        writeText2(bean3.getAQEarTag(), cm(x), isSize(bean3.getAQEarTag()) ? cm(valueDaoDa) : cm(valueDaoDa + eight / 6), cm(950 - x), isSize(bean3.getAQEarTag()) ? cm(eight) : cm(eight / 1.5));//耳标号
        float valueEr = valueDaoDa + eight;


        writeText2(bean3.getAQVeterinary(), 11F, cm(2270), 5.3F, 2F); // 官方签字

        String[] nians = bean3.getDateQF().trim().split(" ");
        String[] splits = nians[0].split("-|/");
        String Years = splits[0];
        String Months = splits[1];
        String Dates = splits[2];
        writeText2(Years, 10.5F, cm(2350), 3F, 2.0F);//年
        writeText2(Months, 15F, cm(2350), 3F, 2.0F); //月
        writeText2(Dates, 19.5F, cm(2350), 3F, 2.0F); //日

        saveImage(bitmap);

        PrintService.pl.write(new byte[]{0x1b, 0x61, 0x00});
        PrintService.pl.printImage(bitmap);
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

    private void writeText2(String value, float x, float y, float width, float height) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(42);
        int w = (int) JsPxValue(width);
        // TODO: 2018/4/9
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLUE);
//        RectF rectF = new RectF(JsPxValue(x), JsPxValue(y), JsPxValue(x + width), JsPxValue(y + height));
//        canvas.drawRect(rectF,paint);
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

    private float cm(double x) {
        double v = 96 / 2.54;
        double cm = x / v;
        return Float.parseFloat(Double.toString(cm));
    }

    private float JsPxValue(double cm) {
        double rv = cm * (96 / 2.54);
        return Float.parseFloat(Double.toString(rv));
    }

    private boolean isSize(String str) {
        if (str.length() > 100) {
            return true;
        } else {
            return false;
        }
    }

    public Bitmap Create2DCode(String str) {

        BitMatrix matrix = null;
        Hashtable hints = new Hashtable();
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        try {
            //编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,
                    280, 280, hints);
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


    private class DoubleTextWatcher implements TextWatcher {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        //主要是重置文本改变事件,判断当前输入的内容
        public void afterTextChanged(Editable edt) {
            total();

        }
    }


    private void total() {
        String lie1 = oneLie.getText().toString();
        if (!TextUtils.isEmpty(lie1)) {
            lie = Float.parseFloat(lie1);
        }

        String s1 = oneHang.getText().toString();
        if (!TextUtils.isEmpty(s1)) {
            v1 = Float.parseFloat(s1);
        }

        String s2 = twoHang.getText().toString();
        if (!TextUtils.isEmpty(s2)) {
            v2 = Float.parseFloat(s2);
        }

        String s3 = threeHang.getText().toString();
        if (!TextUtils.isEmpty(s3)) {
            v3 = Float.parseFloat(s3);
        }

        String s4 = fourHang.getText().toString();
        if (!TextUtils.isEmpty(s4)) {
            v4 = Float.parseFloat(s4);
        }


        String s5 = fiveHang.getText().toString();
        if (!TextUtils.isEmpty(s5)) {
            v5 = Float.parseFloat(s5);
        }


        String s6 = sixHang.getText().toString();
        if (!TextUtils.isEmpty(s6)) {
            v6 = Float.parseFloat(s6);
        }

        String s7 = sevenHang.getText().toString();
        if (!TextUtils.isEmpty(s7)) {
            v7 = Float.parseFloat(s7);
        }


        String s8 = eightHang.getText().toString();
        if (!TextUtils.isEmpty(s8)) {
            v8 = Float.parseFloat(s8);
        }

        vs = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
        hangzongXiangSu.setText(String.valueOf(vs));
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        PrintService.pl.disconnect();
        super.onDestroy();
    }

}
