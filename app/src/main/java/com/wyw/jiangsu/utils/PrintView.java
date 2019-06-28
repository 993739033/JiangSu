package com.wyw.jiangsu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

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

import java.util.List;

/**
 * Created by wyw on 2016/11/10.
 */

public class PrintView<T> extends View {

    Canvas cachecanvas; // 赋值过程中的画板
    Paint paint;
    Bitmap cachebitmap; // 最终生成的图片
    Paint paint1;
    Paint paintDate;
    Paint paintG;
    String type;
    T t;
    int position;
    int page;
    private List<String> paths;
    private int whichPrinter;

    public PrintView(Context context, T t) {
        super(context);
        this.t = t;

        initCanvas();
    }
    public PrintView(int whichPrinter,Context context, T t) {
        super(context);
        this.t = t;
        this.whichPrinter = whichPrinter;
        initCanvas();
    }

    public PrintView(Context context, T t, int position) {
        super(context);
        this.t = t;
        this.position = position;


        initCanvas();
    }

    public Bitmap getcacheBitmap() {
        return cachebitmap;
    }

    /***
     * 初始化绘图类
     */
    public void initCanvas() {
        /*paint = new Paint();
        paint.setAntiAlias(true); //是否抗锯齿
        paint.setColor(Color.BLACK);
        paint.setTextSize(10); // 设置画笔字体的大小

        paintG = new Paint();
        paintG.setAntiAlias(true); // //是否抗锯齿
        paintG.setColor(Color.BLACK);
        paintG.setTextSize(20); // 设置画笔字体的大小

        cachebitmap = Bitmap.createBitmap(1050, 1950, Bitmap.Config.ARGB_8888);// 生成的图片

        cachecanvas = new Canvas(cachebitmap);

        cachecanvas.drawColor(Color.WHITE);

        // 打印字体的笔
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.BLACK);
        paint1.setTextSize(50);

        // 打印日期的笔
        paintDate = new Paint();
        paintDate.setAntiAlias(true);
        paintDate.setColor(Color.BLACK);
        paintDate.setTextSize(40);
*/

        //修改打印内容的尺寸
        DrawUtil drawUtil = new DrawUtil(getContext());// 传的参数是上下文对象
        if (t instanceof AnimAlistBean.DataListBean) {
            cachebitmap = drawUtil.Animal_A((AnimAlistBean.DataListBean) t,whichPrinter);
        } else if (t instanceof AnimBlistBean.DataListBean) {
            cachebitmap = drawUtil.Animal_B((AnimBlistBean.DataListBean) t, Constance.TYPE_PRINT_ANIM_B, false,whichPrinter);
        } else if (t instanceof Qua_AnimalProductsAListBean.DataListBean) {
            cachebitmap = drawUtil.Product_A((Qua_AnimalProductsAListBean.DataListBean) t, Constance.TYPE_PRINT_PRODUCT_A);
        } else if (t instanceof Qua_AnimalProductsBListBean.DataListBean) {
            cachebitmap = drawUtil.Product_B((Qua_AnimalProductsBListBean.DataListBean) t, Constance.TYPE_PRINT_PRODUCT_B, false);
        } else if (t instanceof UploadQuarantineProcessNotBeanDetil) {
            //检疫处理通知单
            cachebitmap = new DrawUtil<UploadQuarantineProcessNotBeanDetil>(getContext()).getBitmao(t,whichPrinter);
        } else if (t instanceof AnimProcessListBean.DataListBean) {
            //动物产地检疫申报单或产地检疫申报单,繁殖材料产地检疫申报单
            cachebitmap = new DrawUtil<AnimProcessListBean.DataListBean>(getContext()).getBitmao(t,whichPrinter);
//            paths=drawUtil.getbitmap_cd(t,position);
        } else if (t instanceof QuarantineDealListQueryBean.DataListBean) {
            //检疫处理通知单查询
            cachebitmap = new DrawUtil<QuarantineDealListQueryBean.DataListBean>(getContext()).getBitmao(t,whichPrinter);
        } else if (t instanceof JianyiChuliTongzhidanListBean.DataListBean) {
            //检疫处理通知单查询接口
            cachebitmap = new DrawUtil<JianyiChuliTongzhidanListBean.DataListBean>(getContext()).getBitmao(t,whichPrinter);
        } else if (t instanceof AH_AnimalOrigin.DataListBean) {
            //动物产地工作记录单
            cachebitmap = drawUtil.getBitmap(t);
        } else if (t instanceof AnimRuZhongYongRecordBean.DataListBean) {
            cachebitmap = drawUtil.getBitmap(t);
        } else if (t instanceof AnimZhongDanRedordBean.DataListBean) {
            cachebitmap = drawUtil.getBitmap(t);
        } else if (t instanceof ProductionAListBean.DataListEntity) {
            cachebitmap = drawUtil.Product_QueryA((ProductionAListBean.DataListEntity) t, Constance.TYPE_PRINT_PRODUCT_A);
        } else if (t instanceof ProductBListBean.DataListEntity) {
            cachebitmap = drawUtil.Product_QueryB((ProductBListBean.DataListEntity) t, Constance.TYPE_PRINT_PRODUCT_B, false);
        } else if (t instanceof AnimAQueryListBean.DataListEntity) {
            cachebitmap = drawUtil.Animal_QueryA((AnimAQueryListBean.DataListEntity) t);
        } else if (t instanceof AnimBQueryListBean.DataListEntity) {
            cachebitmap = drawUtil.Animal_QueryB((AnimBQueryListBean.DataListEntity) t, Constance.TYPE_PRINT_ANIM_A, false);
        }else if (t instanceof Qua_QuarantineDeclarationCDQuery){
            paths=drawUtil.getbitmap_cd((Qua_QuarantineDeclarationCDQuery)t,9);
        }
        else if (t instanceof UploadDoubleRandomBean){
            paths=drawUtil.DoubleRandom_OnePublic((UploadDoubleRandomBean)t);
        }
        else if(t instanceof UploadCommonSupervisionBean){
            paths=drawUtil.getbitmap_rc(t,position);
        }
    }

    public List<String> getPaths() {
        return paths;
    }

    // 清除功能
    public void clear() {
        if (cachecanvas != null) {

            paint.setColor(Color.WHITE);
            cachecanvas.drawPaint(paint);
            paint.setColor(Color.BLACK);
            cachecanvas.drawColor(Color.WHITE); // 设置画图的背景颜色
            invalidate();
        }
    }

//    /***
//     * 生成二维码图片返回
//     *
//     * @param str
//     * @return Bitmap
//     */
//    public Bitmap Create2DCode(String str) {
//        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
//        BitMatrix matrix = null;
//        Log.i("ffss", str);
//        try {
//            matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 150, 150);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        // 二维矩阵转为一维像素数组,也就是一直横着排了
//        int[] pixels = new int[width * height];
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (matrix.get(x, y)) {
//                    pixels[y * width + x] = 0xff000000;
//                }
//            }
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height,
//                Bitmap.Config.ARGB_8888);
//        // 通过像素数组生成bitmap,具体参考api
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//        return bitmap;
//    }

}
