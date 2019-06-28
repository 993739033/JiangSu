package com.wyw.jiangsu.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Common.EpsStatus;
import com.epson.isv.eprinterdriver.Ctrl.EPSetting;
import com.epson.isv.eprinterdriver.Ctrl.EPrintManager;
import com.epson.isv.eprinterdriver.Ctrl.IPrintListener;
import com.epson.isv.eprinterdriver.Ctrl.ISearchPrinterListener;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.wyw.jiangsu.activity.model.PhotoViewModel;

import java.io.File;
import java.util.List;



/**
 * 打印
 *
 * @author pc1
 */
public class BorisPrint {

    Context context;
    EPrintManager epManager;
    EPSetting setting;

    String imagePath;
    int a = 1;
    private List<String> mpaths;
    private int num;
    private int page;
    private Handler mhandle;
    private boolean isfind;
    @SuppressLint("Instantiatable")
    public BorisPrint(Context mycontext, OnFindPrinterListener listener) {
        context = mycontext;
//        File file = new File(MyApplication.getContext().getFilesDir(), "/lib/libeprinterdriver.so");
//        if (!file.exists()) {
//            CommonUtils.initAssetsFile();
//        }
        init(listener);
    }
    public BorisPrint(Context mycontext, OnFindPrinterListener listener, Handler handle) {
        context = mycontext;
//        File file = new File(MyApplication.getContext().getFilesDir(), "/lib/libeprinterdriver.so");
//        if (!file.exists()) {
//            CommonUtils.initAssetsFile();
//        }
        if (mhandle==null){
            mhandle=handle;
        }
        init(listener);
    }

    @SuppressLint("SdCardPath")
    public void init(OnFindPrinterListener listener) {
//        context.getExternalFilesDir("");
//        new File(context.getFilesDir().getParentFile(),"lib");
//        context.getCacheDir();
        Log.e("----BorisPrint----", context.getExternalFilesDir("") + "------------");
        Log.e("----BorisPrint----", context.getFilesDir() + "------------");
        Log.e("----BorisPrint----", context.getCacheDir() + "------------");
        String libpath = "/data/data/com.wyw.jiangsu/lib";
        //使用外部存储卡的路径
//        String libpath = context.getFilesDir().getAbsolutePath()+ File.separator+"lib";
        String libname = "libeprinterdriver.so";
        epManager = EPrintManager.instance();
        epManager.initEscprLib(context.getApplicationContext(), libpath,
                libname);
        epManager.addPrintListener(new PrinterListenerImp());
        epManager.addSearchListener(new SearchListenerImp() {
            @Override
            public void onFindPrinter(EpsPrinter printer) {
                if (listener != null && !listener.doSomething(printer.getPrinterID())) return;
                super.onFindPrinter(printer);
            }
        });
        setting = EPSetting.instance();
        PageAttribute epPageAttri = new PageAttribute(PageAttribute.MediaSizeID.EPS_MSID_A4,
                PageAttribute.MediaTypeID.EPS_MTID_PLAIN, PageAttribute.PrintQuality.EPS_MQID_NORMAL);
        setting.setSelPageAttri(epPageAttri);
        setting.setPrintDirection(EPSetting.PRINT_DIR_UNI);
        setting.setColorMode(EPSetting.COLOR_MODE_MONOCHROME);
        setting.setPaperSource(EPSetting.PAPER_SOURCE_NOT_SPEC);
        setting.setBorderless(true);
        setting.setDuplexPrint(false);
        setting.setTotalPages(1);
        setting.setTemporaryImageFilePath(context.getCacheDir().getAbsolutePath() + "/temp.jpg");

    }

    /**
     * 打印多张
     * @param paths
     * @param page
     * @return
     */
    public int prints(List<String> paths,int page){
        epManager.cancelFindPrinter();
        epManager.cancelPrint();
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num=0;
        mpaths=paths;
        this.a=page;
        return epManager.findPrinter(30);
    }
    //打印单张
    public int print(String ppath, int num) {
        epManager.cancelFindPrinter();
        epManager.cancelPrint();
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imagePath = ppath;
        a = num;
       return epManager.findPrinter(30);
    }

    private class PrinterListenerImp implements IPrintListener {

        @Override
        public void onCleaningTime(int arg0) {

        }

        @Override
        public void onPageFinished(int arg0, int arg1) {
            System.gc();
            Toast.makeText(context, "打印下一张", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPrintAutoContinue() {
        }

        @Override
        public void onPrintBegin() {
            System.gc();
        }

        @Override
        public void onPrintFinished(int arg0) {
            System.gc();
            if(imagePath!=null&&mpaths==null) {
                a = a - 1;
                if (a > 0) {
                    Toast.makeText(context, "打印下一份", Toast.LENGTH_SHORT).show();
                    EPrintManager.instance().startPrint(imagePath, EPrintManager.EPRINT_FILETYPE.JPEG);
                } else {
                    mhandle.sendEmptyMessage(101);
                    Toast.makeText(context, "打印完成", Toast.LENGTH_SHORT).show();
                }
            }else if (mpaths!=null&&imagePath==null){
                num++;
                if (num<mpaths.size()) {
                    Toast.makeText(context, "打印下一张", Toast.LENGTH_SHORT).show();
                    File file=new File(mpaths.get(num));
                    if (file.exists()){
                    int code = EPrintManager.instance().startPrint(mpaths.get(num), EPrintManager.EPRINT_FILETYPE.JPEG);
                    }
                }else {
                    mhandle.sendEmptyMessage(101);
                    Toast.makeText(context, "打印完成", Toast.LENGTH_SHORT).show();
                    PhotoViewModel.deleteFile(PhotoViewModel.getCommonSupervisionDir());
                    FileUtil.deleteCacheFile(new File(FileUtil.getInstance().getCacheDirPath()));
                }
            }
        }

        @Override
        public void onPrintPause(int i, int i1, EpsStatus epsStatus) {

        }


        @Override
        public void onPrintResume() {
        }
    }

    private class SearchListenerImp implements ISearchPrinterListener {
        @Override
        public void onFindPrinter(EpsPrinter printer) {
            Toast.makeText(context, "找到打印机", Toast.LENGTH_SHORT).show();
            mhandle.sendEmptyMessage(103);
            isfind=true;
            epManager.cancelFindPrinter();
//            if (EPrintManager.instance().isPrintBusy()) {
//                Toast.makeText(context, "打印机忙．．．", Toast.LENGTH_SHORT).show();
//                return;
//            }
            if (mpaths!=null&&imagePath==null) {
                setting.setTotalPages(a);
                setting.setColorMode(EPSetting.COLOR_MODE_COLOR);
            }
            setting.setSelEpsPrinter(printer);
            Toast.makeText(context, "开始打印内容", Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(imagePath!=null&&mpaths==null) {
                EPrintManager.instance().startPrint(imagePath,
                        EPrintManager.EPRINT_FILETYPE.JPEG);
            }else if(mpaths!=null&&imagePath==null){

                int code = EPrintManager.instance().startPrint(mpaths.get(num),
                        EPrintManager.EPRINT_FILETYPE.JPEG);
                Log.e("czy","code1=="+page+"===="+code);
            }
        }

        @Override
        public void onSearchBegin() {
            Toast.makeText(context, "开始查找打印机", Toast.LENGTH_SHORT).show();
            isfind=false;
        }

        @Override
        public void onSearchFinished(int arg0) {

            if (!isfind){
                Toast.makeText(context, "未找到打印机", Toast.LENGTH_SHORT).show();
                mhandle.sendEmptyMessage(102);
            }
        }
    }

    public interface OnFindPrinterListener {
        /**
         * 如果返回false 那么不打印
         *
         * @param serialNumber
         * @return
         */
        boolean doSomething(String serialNumber);
    }
}
