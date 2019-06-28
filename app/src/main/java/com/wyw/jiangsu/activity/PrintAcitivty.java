package com.wyw.jiangsu.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.AnimAlistBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsAListBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsBListBean;
import com.wyw.jiangsu.bean.Qua_QuarantineDeclarationCDQuery;
import com.wyw.jiangsu.bean.QuarantineDealListQueryBean;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.bean.upload.UploadDoubleRandomBean;
import com.wyw.jiangsu.bean.upload.UploadQuarantineProcessNotBeanDetil;
import com.wyw.jiangsu.dialog.DownloadDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IPrintAcitivty;
import com.wyw.jiangsu.presenter.PrintAcitivtyPresenter;
import com.wyw.jiangsu.service.downapk.DownloadReceiver;
import com.wyw.jiangsu.utils.AppUtils;
import com.wyw.jiangsu.utils.BorisPrint;
import com.wyw.jiangsu.utils.DrawUtil;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.OtherUtil;
import com.wyw.jiangsu.utils.PrintView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 打印页面
 */

/**
 *
 */
public class PrintAcitivty extends MVPActivity<PrintAcitivtyPresenter> implements IPrintAcitivty {

    @BindView(R.id.bt_print)
    Button btPrint;
    private int whichPrinter = 0;//默认为0,  0-爱普生打印机  1-惠普打印机


    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    dialog.dismiss();
                    Toast.makeText(PrintAcitivty.this, "打印开始", Toast.LENGTH_SHORT).show();
                    dialog.setMessage("查找打印机。。。");
                    dialog.show();
                    break;
                case 101:
                    dialog.dismiss();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 102://打印机未找到
                    dialog.dismiss();
                    dialog.cancel();
                    Toast.makeText(PrintAcitivty.this, "打印机未找到", Toast.LENGTH_SHORT).show();
                    break;
                case 103://打印机找到
                    dialog.dismiss();
                    Toast.makeText(PrintAcitivty.this, "打印开始", Toast.LENGTH_SHORT).show();
                    dialog.setMessage("打印进行中。。。");
                    dialog.show();
                    break;
                case 104://寻找打印软件
                    dialog.dismiss();
                    dialog = new ProgressDialog(PrintAcitivty.this).show(PrintAcitivty.this, "", "寻找打印软件中...");
                    dialog.show();
                    break;
                case 105://关闭对话框
                    dialog.dismiss();
                    break;
                case 106://
                    switch (msg.arg1){
                        case 0:
                            getPresenter().uploadPrint("Qua_AnimalQuarantineA", printId, ((AnimAlistBean.DataListBean)msg.obj).getFSm5());
                            break;
                        case 1:
                            getPresenter().uploadPrint("Qua_AnimalQuarantineB", printId, ((AnimBlistBean.DataListBean)msg.obj).getFSm5());
                            break;
                        case 2:
                            getPresenter().uploadPrint("Qua_AnimalQuarantineA", printId, ((Qua_AnimalProductsAListBean.DataListBean)msg.obj).getFSm5());
                            break;
                        case 3:
                            getPresenter().uploadPrint("AH_AnimalOrigin", printId, "");
                            break;
                        case 4:
                            getPresenter().uploadPrint("AH_AEmbryoQuarantine", printId, "");
                            break;
                        case 5:
                            getPresenter().uploadPrint("AH_AiryEmbryoQuarantine", printId, "");
                            break;
                        case 6:
                            getPresenter().uploadPrint("Qua_QuarantineDeclarationCD", ((AnimProcessListBean.DataListBean)msg.obj).getFStId(), "");
                            break;
                        case 7:
                            getPresenter().uploadPrint("Qua_QuarantineDeclarationCD",  ((AnimProcessListBean.DataListBean)msg.obj).getFStId(), "");
                            break;
                    }
                    break;
                case 107:
                    new AlertDialog.Builder(PrintAcitivty.this).setMessage("未检测到指定的printshare软件，请先下载")
                            .setTitle("下载").setNegativeButton("取消", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            downloadDialog1 = new DownloadDialog.Builder(PrintAcitivty.this)
                                    .OnClickListener(new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setStyle(ProgressDialog.STYLE_HORIZONTAL)
                                    .setCanCancel(false)
                                    .setTitle("下载")
                                    .create();
                            downloadDialog1.show();
                            getPresenter().startDownPrintShareApk();
                        }
                    }).show();
                    break;
                case 108:
                    Toast.makeText(PrintAcitivty.this,"请选择图片,找到"+Constance.dirName+"文件夹打印对应的文件",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private ProgressDialog dialog;
    private String printId;
    private DownloadDialog downloadDialog1;
    private DownloadReceiver downloadReceiver;
    private IntentFilter intentFilter;
    private boolean hasErTag;
    private String earTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_acitivty);
        ButterKnife.bind(this);
    }


    @Override
    protected PrintAcitivtyPresenter createPresenter() {
        return new PrintAcitivtyPresenter(this);
    }

    @Override
    public void bindData() {
        whichPrinter = getPresenter().getUser().getZt();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        switch (bundle.getString(Constance.START_ACTIVITY_TYPE, "")) {
            case Constance.TYPE_PRINT_ANIM_A:
                setTitle("动物A证打印");
                break;
            case Constance.TYPE_PRINT_ANIM_B:
                setTitle("动物B证打印");
                break;
            case Constance.TYPE_PRINT_PRODUCT_A:
                setTitle("产品A证打印");
                break;
            case Constance.TYPE_PRINT_PRODUCT_B:
                setTitle("产品B证打印");
                break;
            case Constance.TYPE_PRINT_JIANYI_CHULI:
                setTitle("检疫处理通知单打印");
                break;
            case Constance.TYPE_PRINT_ANIM_APPLY_DETIL:
                setTitle("动物产地检疫申报单打印");
                break;
            case Constance.TYPE_PRINT_APPLY_DETIL:
                setTitle("产地检疫申报单打印");
                break;
            case Constance.TYPE_PRINT_QUERY_APPLY_DETIL:
                setTitle("产地检疫申报单查询打印");
                break;
            case Constance.TYPE_PRINT_AREA:
                setTitle("动物产地工作记录单打印");
                break;
            case Constance.TYPE_PRINT_ZHONGDAN:
                setTitle("种蛋、胚胎、精液检疫工作记录单打印");
                break;
            case Constance.TYPE_PRINT_RUZHONG:
                setTitle("乳用、种用动物工作记录单打印");
                break;
            case Constance.TYPE_PRINT_QUERY_JIANI_CHULI:
                setTitle("检疫处理通知单查询打印");
                break;
            case Constance.TYPE_PRINT_RZ_ONE:
                setTitle("规模养殖场日常监管检查记录打印");
                break;
            case Constance.TYPE_PRINT_RZ_TWO:
                setTitle("屠宰厂（场）日常监督检查记录打印");
                break;
            case Constance.TYPE_PRINT_RZ_THREE:
                setTitle("动物诊疗场所监督检查记录打印");
                break;
            case Constance.TYPE_PRINT_RZ_FOUR:
                setTitle("无害化处理场所检查记录打印");
                break;
            case Constance.TYPE_PRINT_RZ_FIVE:
                setTitle("隔离场所监督检查记录打印");
                break;
            case Constance.TYPE_PRINT_RZ_SIX:
                setTitle("饲料生产企业日常检查打印");
                break;
            case Constance.TYPE_PRINT_RZ_SEVEN:
                setTitle("兽药生产企业日常打印");
                break;
            case Constance.TYPE_PRINT_RZ_EIGHT:
                setTitle("兽药经营企业日常打印");
                break;
            case Constance.TYPE_PRINT_RZ_NINE:
                setTitle("生鲜乳收购站检查表打印");
                break;
            case Constance.TYPE_PRINT_RZ_TEN:
                setTitle("屠宰“双随机”检查表打印");
                break;
            default:
                Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
                break;
        }
        dialog = new ProgressDialog(this);

        dialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void bindListener() {
        getBack().setOnClickListener(v -> {
            setResult(RESULT_OK);
//            finish();
            //不想回到原先界面
            startActivity(new Intent(PrintAcitivty.this,MainActivity.class));
        });
        btPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存
//                getPresenter().saveMachineNumber(OtherUtil.toString(printer));
                //上传
//                getPresenter().upload();
                //打印
                if (whichPrinter == 0) {
                    dialog.setMessage("准备打印中。。。");
                    dialog.show();
                } else if (whichPrinter == 1) {
                    dialog.dismiss();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        printer();
                        Looper.loop();
                    }
                }).start();

//                if (!TextUtils.isEmpty(getPresenter().getMachineNumber()) &&
//                        !TextUtils.isEmpty(getPresenter().getSerialNumber())) {
//                    //直接打印
//                    printer();
//                } else {
//                    //打开对话框
//                    createDialog().show();
//                }
            }
        });
    }

    /**
     * 打开输入打印机编号的对话框
     */
    public AlertDialog createDialog() {
        // 使用自己的布局文件
        LayoutInflater factory = LayoutInflater.from(this);
        // 关联布局
        final View textEntryView = factory.inflate(R.layout.printer, null);
        EditText printer = (EditText) textEntryView.findViewById(R.id.printerNo);
        return new AlertDialog.Builder(this).setTitle("输入打印机的出厂编号")
                .setView(textEntryView)
                .setMessage("不要*符号，从英文字母开始输入!")
                .setPositiveButton("确定", (dialog, which) -> {
                    if (TextUtils.isEmpty(OtherUtil.toString(printer))
                            || OtherUtil.toString(printer).length() < 8) {
                        getPresenter().saveMachineNumber("");
                        Toast.makeText(this, "请正确的打印机编号,在机器的下方", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }).create();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
    }

    /**
     *
     */
    private void printer() {
        /*BorisPrint bPrint = new BorisPrint(this, null, mhandler);*/
        // showProgress();
        // BorisParintUtil borisParintUtil=new BorisParintUtil(this,null);
//        BorisPrint bPrint = new BorisPrint(this, new BorisPrint.OnFindPrinterListener() {
//            @Override
//            public boolean doSomething(String serialNumber) {
////                serialNumber -> {
//                getPresenter().saveSerialNumber(serialNumber);
//                if (TextUtils.isEmpty(getPresenter().getMachineNumber()))
//                    return false;
//                if (TextUtils.isEmpty(getPresenter().getSerialNumber())
//                        || !getPresenter().getSerialNumber().equals(serialNumber)) {
//                    //弹出对话框重新输入
//                    getPresenter().saveMachineNumber("");
//                    createDialog().show();
//                    return false;
//                }
//                return true;
////                }
//            }
//        });
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        switch (bundle.getString(Constance.START_ACTIVITY_TYPE, "")) {
            case Constance.TYPE_PRINT_ANIM_A:
                AnimAlistBean.DataListBean bean1 = (AnimAlistBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,0,0,bean1).sendToTarget();
                File file1 = new File(FileUtil.getInstance().getCacheDirPath(), "anim1.jpg");
                if (file1.exists()) file1.delete();
                PrintView<AnimAlistBean.DataListBean> printView1 = new PrintView<>(whichPrinter,this, bean1);
                String path1 = FileUtil.getInstance().save2Local(printView1.getcacheBitmap(), file1.getAbsolutePath());
                if (whichPrinter == 1) {
                    if (bean1.getAEarTag()!=null&&bean1.getAEarTag().getBytes().length>146){
                        hasErTag =true;
                        earTag = bean1.getAEarTag();
                    }
                    saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "anim1.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(path1, 2);
                    if (code == 0) {
                        break;
                    }
                }

                break;
            case Constance.TYPE_PRINT_ANIM_B:
                AnimBlistBean.DataListBean bean2 = (AnimBlistBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,1,0,bean2).sendToTarget();
                File file2 = new File(FileUtil.getInstance().getCacheDirPath(), "anim2.jpg");
                if (file2.exists()) file2.delete();
                PrintView<AnimBlistBean.DataListBean> printView2 = new PrintView<>(whichPrinter,this, bean2);
                String path2 = FileUtil.getInstance().save2Local(printView2.getcacheBitmap(), file2.getAbsolutePath());
                if (whichPrinter == 1) {
                    if (bean2.getAQEarTag()!=null&&bean2.getAQEarTag().getBytes().length>146){
                        hasErTag =true;
                        earTag = bean2.getAQEarTag();
                    }
                    saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "anim2.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(path2, 1);
                    if (code == 0) {
                        break;
                    }
                }
                break;
            case Constance.TYPE_PRINT_PRODUCT_A:
                File file3 = new File(FileUtil.getInstance().getCacheDirPath(), "product1.jpg");
                if (file3.exists()) file3.delete();
                Qua_AnimalProductsAListBean.DataListBean bean3 = (Qua_AnimalProductsAListBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,2,0,bean3);
                PrintView<Qua_AnimalProductsAListBean.DataListBean> printView3 = new PrintView<>(this, bean3);
                String path3 = FileUtil.getInstance().save2Local(printView3.getcacheBitmap(), file3.getAbsolutePath());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "product1.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(path3, 2);
                    if (code == 0) {
                        break;
                    }
                }

                break;
            case Constance.TYPE_PRINT_PRODUCT_B:
                File file4 = new File(FileUtil.getInstance().getCacheDirPath(), "product2.jpg");
                if (file4.exists()) file4.delete();
                Qua_AnimalProductsBListBean.DataListBean bean4 = (Qua_AnimalProductsBListBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<Qua_AnimalProductsBListBean.DataListBean> printView4 = new PrintView<>(this, bean4);
                String path4 = FileUtil.getInstance().save2Local(printView4.getcacheBitmap(), file4.getAbsolutePath());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "product2.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(path4, 1);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.print(path4, 1);
                break;
            case Constance.TYPE_PRINT_AREA:
                AH_AnimalOrigin.DataListBean bean5 = (AH_AnimalOrigin.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,3,0,bean5).sendToTarget();
                File file5 = new File(FileUtil.getInstance().getCacheDirPath(), "animchandi.jpg");
                if (file5.exists()) file5.delete();
                PrintView<AH_AnimalOrigin.DataListBean> printView5 = new PrintView<>(this, bean5);
                String path5 = FileUtil.getInstance().save2Local(printView5.getcacheBitmap(), file5.getAbsolutePath());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "animchandi.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(path5, 1);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.print(path5, 1);
                break;
            case Constance.TYPE_PRINT_ZHONGDAN:
                AnimZhongDanRedordBean.DataListBean bean6 = (AnimZhongDanRedordBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,4,0,bean6).sendToTarget();

                File file6 = new File(FileUtil.getInstance().getCacheDirPath(), "zhongdan.jpg");
                if (file6.exists()) file6.delete();
                PrintView<AnimZhongDanRedordBean.DataListBean> printView6 = new PrintView<>(this, bean6);
                String path6 = FileUtil.getInstance().save2Local(printView6.getcacheBitmap(), file6.getAbsolutePath());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "zhongdan.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(path6, 1);
                    if (code == 0) {
                        break;
                    }
                }

                // bPrint.print(path6, 1);
                break;
            case Constance.TYPE_PRINT_RUZHONG:
                AnimRuZhongYongRecordBean.DataListBean bean7 = (AnimRuZhongYongRecordBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,5,0,bean7).sendToTarget();

                File file7 = new File(FileUtil.getInstance().getCacheDirPath(), "ruzhong.jpg");
                if (file7.exists()) file7.delete();
                PrintView<AnimRuZhongYongRecordBean.DataListBean> printView7 = new PrintView<>(this, bean7);
                String path7 = FileUtil.getInstance().save2Local(printView7.getcacheBitmap(), file7.getAbsolutePath());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "ruzhong.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(path7, 1);
                    if (code == 0) {
                        break;
                    }
                }
                // bPrint.print(path7, 1);
                break;
            case Constance.TYPE_PRINT_JIANYI_CHULI:
                UploadQuarantineProcessNotBeanDetil bean = (UploadQuarantineProcessNotBeanDetil) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadQuarantineProcessNotBeanDetil> printView = new PrintView<>(this, bean);
                Bitmap bitmap = printView.getcacheBitmap();
                String s = FileUtil.getInstance().save2Local(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "jianyichuli.jpg");
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(Environment.getExternalStorageDirectory().getAbsolutePath(), "jianyichuli.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(s, 1);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.print(s, 1);
                break;
            case Constance.TYPE_PRINT_QUERY_JIANI_CHULI:
                QuarantineDealListQueryBean.DataListBean bean10 = (QuarantineDealListQueryBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<QuarantineDealListQueryBean.DataListBean> printView10 = new PrintView<>(this, bean10);
                Bitmap Mbitmap = printView10.getcacheBitmap();
                String s10 = FileUtil.getInstance().save2Local(Mbitmap, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "queryjianyichuli.jpg");
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(Environment.getExternalStorageDirectory().getAbsolutePath(), "queryjianyichuli.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(s10, 1);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.print(s10, 1);
                break;
            //动物检疫申报单处理
            case Constance.TYPE_PRINT_ANIM_APPLY_DETIL:
                AnimProcessListBean.DataListBean bean8 = (AnimProcessListBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,6,0,bean8).sendToTarget();

                PrintView<AnimProcessListBean.DataListBean> printView8 = new PrintView(whichPrinter,this, bean8);
                List<String> paths = printView8.getPaths();
                Bitmap bitmap1 = printView8.getcacheBitmap();
                String s1 = FileUtil.getInstance().save2Local(bitmap1, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "animapplydetil.jpg");
                if (whichPrinter == 1) {
                    if (bean8.getQDWErBiaoHao()!=null&&bean8.getQDWErBiaoHao().getBytes().length>146){
                        hasErTag =true;
                        earTag = bean8.getQDWErBiaoHao();
                    }
                    saveAndStartPrintShare(Environment.getExternalStorageDirectory().getAbsolutePath(), "animapplydetil.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(s1, 1);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.print(s1, 1);
                break;
            case Constance.TYPE_PRINT_APPLY_DETIL:
                AnimProcessListBean.DataListBean bean9 = (AnimProcessListBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                printId = bundle.getString(Constance.START_ACTIVITY_PRINTID);
                mhandler.obtainMessage(106,7,0,bean9).sendToTarget();

                PrintView<AnimProcessListBean.DataListBean> printView9 = new PrintView<>(this, bean9);
                Bitmap bitmap2 = printView9.getcacheBitmap();
                String s2 = FileUtil.getInstance().save2Local(bitmap2, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "appludetil.jpg");
                if (whichPrinter == 1) {
                    if (bean9.getQDWErBiaoHao()!=null&&bean9.getQDWErBiaoHao().getBytes().length>146){
                        hasErTag =true;
                        earTag = bean9.getQDWErBiaoHao();
                    }

                    saveAndStartPrintShare(Environment.getExternalStorageDirectory().getAbsolutePath(), "appludetil.jpg");
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.print(s2, 1);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.print(s2, 1);
                break;
            case Constance.TYPE_PRINT_RZ_ONE:

                UploadCommonSupervisionBean bea10 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView11 = new PrintView<UploadCommonSupervisionBean>(PrintAcitivty.this, bea10, 0);

                Log.e("czy", printView11.getPaths().toString());
                //FileUtil.getInstance().getPrintList("yz");

                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView11.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("yz"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                // bPrint.prints( FileUtil.getInstance().getPrintList("yz"),2);

                break;
            case Constance.TYPE_PRINT_RZ_TWO:

                UploadCommonSupervisionBean bea11 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView12 = new PrintView<UploadCommonSupervisionBean>(this, bea11, 1);
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView12.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("tz"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                //  bPrint.prints( FileUtil.getInstance().getPrintList("tz"),2);
                // Log.e("czy",printView12.getPaths().toString());
                break;

            case Constance.TYPE_PRINT_RZ_THREE:

                UploadCommonSupervisionBean bea12 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView13 = new PrintView<UploadCommonSupervisionBean>(this, bea12, 2);
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView13.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("zl"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                // Log.e("czy",printView13.getPaths().toString());
                //bPrint.prints( FileUtil.getInstance().getPrintList("zl"),2);
                break;
            case Constance.TYPE_PRINT_RZ_FOUR:

                UploadCommonSupervisionBean bea13 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView14 = new PrintView<UploadCommonSupervisionBean>(this, bea13, 3);
                Log.e("czy", printView14.getPaths().toString());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView14.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("whh"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.prints( FileUtil.getInstance().getPrintList("whh"),2);
                break;
            case Constance.TYPE_PRINT_RZ_FIVE:
                UploadCommonSupervisionBean bea14 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView15 = new PrintView<UploadCommonSupervisionBean>(this, bea14, 4);
                Log.e("czy", printView15.getPaths().toString());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView15.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("geli"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.prints( FileUtil.getInstance().getPrintList("geli"),2);
                break;
            case Constance.TYPE_PRINT_RZ_SIX:
                UploadCommonSupervisionBean bea15 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView16 = new PrintView<UploadCommonSupervisionBean>(this, bea15, 5);
                Log.e("czy", printView16.getPaths().toString());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView16.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("sl"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                // bPrint.prints( FileUtil.getInstance().getPrintList("sl"),2);
                break;
            case Constance.TYPE_PRINT_RZ_SEVEN:
                UploadCommonSupervisionBean bea16 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView17 = new PrintView<UploadCommonSupervisionBean>(this, bea16, 6);
                Log.e("czy", printView17.getPaths().toString());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView17.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("sysc"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                // bPrint.prints( FileUtil.getInstance().getPrintList("sysc"),2);
                break;
            case Constance.TYPE_PRINT_RZ_EIGHT:
                UploadCommonSupervisionBean bea17 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView18 = new PrintView<UploadCommonSupervisionBean>(this, bea17, 7);
                Log.e("czy", printView18.getPaths().toString());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView18.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("syjy"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                //bPrint.prints( FileUtil.getInstance().getPrintList("syjy"),2);
                break;
            case Constance.TYPE_PRINT_RZ_NINE:
                UploadCommonSupervisionBean bea18 = (UploadCommonSupervisionBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadCommonSupervisionBean> printView19 = new PrintView<UploadCommonSupervisionBean>(this, bea18, 8);
                Log.e("czy", printView19.getPaths().toString());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView19.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("sxr"), 2);
                    if (code == 0) {
                        break;
                    }
                }
                // bPrint.prints( FileUtil.getInstance().getPrintList("sxr"),2);
                break;
            case Constance.TYPE_PRINT_RZ_TEN:
                UploadDoubleRandomBean doubleRandomBean = (UploadDoubleRandomBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<UploadDoubleRandomBean> pv1 = new PrintView<UploadDoubleRandomBean>(this, doubleRandomBean, 8);
                Log.e("ssj", pv1.getPaths().toString());
//                if (whichPrinter == 1) {
//                    saveAndStartPrintShare(pv1.getPaths());
//                    return;
//                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(FileUtil.getInstance().getPrintList("ssj"), 1);
                    if (code == 0) {
                        break;
                    }
                }
                // bPrint.prints( FileUtil.getInstance().getPrintList("sxr"),2);
                break;

            //查询->产地检疫申报单套打
            case Constance.TYPE_PRINT_QUERY_APPLY_DETIL:
                Qua_QuarantineDeclarationCDQuery bea19 = (Qua_QuarantineDeclarationCDQuery) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
                PrintView<Qua_QuarantineDeclarationCDQuery> printView20 = new PrintView<Qua_QuarantineDeclarationCDQuery>(this, bea19, 9);
//                Log.e("cdjy",printView20.getPaths().toString());
                if (whichPrinter == 1) {
                    saveAndStartPrintShare(printView20.getPaths());
                    return;
                }
                mhandler.sendEmptyMessage(100);
                while (true) {
                    BorisPrint bPrint = new BorisPrint(this, null, mhandler);
                    int code = bPrint.prints(printView20.getPaths(), 2);
                    if (code == 0) {
                        break;
                    }
                }
//                bPrint.prints( FileUtil.getInstance().getPrintList("cdjy"),1);
                break;

            default:
                Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @NonNull
    private File initDir() {
        File file = new File(Environment.getExternalStorageDirectory() + Constance.dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
        return file;
    }

    private void saveAndStartPrintShare(String filePath, String fileName) {
        mhandler.sendEmptyMessage(104);
        long time1 = System.currentTimeMillis();
        long time2 = 0;
        do {
            time2 = System.currentTimeMillis();
        }while(time2-time1<5000);
        mhandler.sendEmptyMessage(105);
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = initDir();
                Bitmap bitmap = BitmapFactory.decodeFile(filePath + "/" + fileName);
                FileUtil.getInstance().save2Local(bitmap, file.getAbsolutePath() + "/" + fileName);
                if (hasErTag){
                    DrawUtil drawUtil = new DrawUtil(PrintAcitivty.this);
                    Bitmap bitmapEb=null;
                    if (fileName.equals("anim2.jpg")){
                        bitmapEb = drawUtil.drawEbInBack(earTag);
                    }else {
                        bitmapEb=drawUtil.drawEbInBackSecond(earTag);
                    }
                    FileUtil.getInstance().save2Local(bitmapEb,
                            Environment.getExternalStorageDirectory() + Constance.dirName+"/ErBiao.jpg");
                }
                if(AppUtils.isAvilible(PrintAcitivty.this,"com.dynamixsoftware.printershare")){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    ComponentName cn = new ComponentName("com.dynamixsoftware.printershare", "com.dynamixsoftware.printershare.ActivityMain");
                    intent.setComponent(cn);
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("startprintshare", e.getMessage());
                    }
                    mhandler.sendEmptyMessage(108);
                }else {
                    mhandler.sendEmptyMessage(107);
                }

            }
        }).start();
    }

    private void saveAndStartPrintShare(List<String> filePaths) {
        mhandler.sendEmptyMessage(104);
        long time1 = System.currentTimeMillis();
        mhandler.sendEmptyMessage(108);
        long time2 = 0;
        do {
            time2 = System.currentTimeMillis();
        }while(time2-time1<5000);
        mhandler.sendEmptyMessage(105);
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = initDir();
                for (int i = 0; i < filePaths.size(); i++) {
                    String path = filePaths.get(i);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    FileUtil.getInstance().save2Local(bitmap, file.getAbsolutePath() + "/picture" + i + ".jpg");
                    bitmap.recycle();
                }
                if(AppUtils.isAvilible(PrintAcitivty.this,"com.dynamixsoftware.printershare")){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    ComponentName cn = new ComponentName("com.dynamixsoftware.printershare", "com.dynamixsoftware.printershare.ActivityMain");
                    intent.setComponent(cn);
                    startActivity(intent);
                    mhandler.sendEmptyMessage(108);

                }else {
                    mhandler.sendEmptyMessage(107);
                }
            }
        }).start();
    }



    @Override
    public void setDownDialogProgress(long bytesRead, long contentLength, boolean done) {
        downloadDialog1.setProgress((int) (bytesRead / 1024));
        downloadDialog1.setMax((int) (contentLength / 1024));
        if (done) {
            downloadDialog1.dismiss();
        }
    }

    @Override
    public void onDownloadDone(int type) {
        Intent intent = new Intent();
        intent.setAction(DownloadReceiver.Action);
        switch (type) {
            case Constance.TYPE_APK_DONE:
                intent.putExtra(DownloadReceiver.DOWNLOAD, DownloadReceiver.DOWNLAOD_APK);
                intent.putExtra(DownloadReceiver.PATH, new File(Environment.getExternalStorageDirectory(), "PrintShare.apk").getAbsolutePath());
                break;
        }
        sendBroadcast(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        downloadReceiver = new DownloadReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadReceiver.Action);
        registerReceiver(downloadReceiver, intentFilter);

    }

    @Override
    public void Return() {
        return;
    }

    @Override
    public void Toath() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(downloadReceiver);
        hideProgress();
    }
}
