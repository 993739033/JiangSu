package com.wyw.jiangsu.activity.chaxun;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.PrintAcitivty;
import com.wyw.jiangsu.bean.AnimAQueryListBean;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.utils.AppUtils;
import com.wyw.jiangsu.utils.BorisPrint;
import com.wyw.jiangsu.utils.DrawUtil;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.PrintView;
import com.wyw.jiangsu.utils.SPUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动物A查询列表详情
 * Created by Administrator on 2017/3/27.
 */
public class AnimalAQueryDetilActivity extends MVPActivity {

    @BindView(R.id.et_quarantine_no)
    EditText etQuarantineNo;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.et_purpose)
    EditText etPurpose;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_start_address)
    EditText et_start_address;
    //    @BindView(R.id.et_start_village)
//    EditText etStartVillage;
//    @BindView(R.id.et_start_other)
//    EditText etStartOther;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.et_end_address)
    EditText et_end_address;
    //    @BindView(R.id.et_end_village)
//    EditText etEndVillage;
//    @BindView(R.id.et_end_other)
//    EditText etEndOther;
    @BindView(R.id.et_carrier_name)
    EditText etCarrierName;
    @BindView(R.id.et_carrier_phone)
    EditText etCarrierPhone;
    @BindView(R.id.et_carrier_util_number)
    EditText etCarrierUtilNumber;
    @BindView(R.id.et_steriallier_method)
    EditText etSteriallierMethod;
    @BindView(R.id.et_transportation)
    EditText etTransportation;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.et_hegeri)
    EditText etHegeri;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.et_signature_checked)
    EditText etSignatureChecked;
    @BindView(R.id.et_check_phone)
    EditText etCheckPhone;
    @BindView(R.id.et_prove_time)
    TextView tvProveTime;
    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.SV)
    ScrollView SV;
    private AnimAQueryListBean.DataListEntity entity;
    private Handler phandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101://打印完成
                    progressDialog.dismiss();
                    break;
                case 102://打印机查找失败
                    progressDialog.dismiss();
                    break;
                case 103://打印机查找成功
                    progressDialog.dismiss();
                    progressDialog.setMessage("打印中。。。");
                    progressDialog.show();
                    break;
                case 104://绘制完成，查找打印机
                    progressDialog.dismiss();
                    progressDialog.setMessage("查找打印机。。。");
                    progressDialog.show();
                    break;
                case 107:
                    Toast.makeText(AnimalAQueryDetilActivity.this, "未检测到指定的printshare软件，请先下载", Toast.LENGTH_SHORT).show();
                    break;
                case 108:
                    Toast.makeText(AnimalAQueryDetilActivity.this,"请选择图片,找到"+Constance.dirName+"文件夹打印对应的文件",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private ProgressDialog progressDialog;
    private boolean hasErTag;
    private String earTag;
    private int whichPrinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animala_query_detil_activity);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void bindData() {
        setTitle("动物A查询详情");
        entity = (AnimAQueryListBean.DataListEntity) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        etQuarantineNo.setText(entity.getANumber());
        etUserName.setText(entity.getACargoOwner());
        etUserPhone.setText(entity.getAPhone());
        etAnimType.setText(entity.getAXuZhong());
        etAnimCount.setText(entity.getAQuantity() + entity.getADanWei());
        etPurpose.setText(entity.getAYongTu());
//        etStartPlace.setText(entity.getADiQuQy());
        etStartPlace.setText(entity.getAShengQy() + entity.getAShiQy() + entity.getAXianQy());
        et_start_address.setText(entity.getAXiangQy()+" "+entity.getACunQy());
//        etStartVillage.setText(entity.getAXiangQy());
//        etStartOther.setText(entity.getACunQy());
        etEndPlace.setText(entity.getAShengDd() + entity.getAShiDd() + entity.getAXianDd());
        et_end_address.setText(entity.getAXiangDd()+" "+entity.getACunDd());
//        etEndVillage.setText(entity.getAXiangDd());
//        etEndOther.setText(entity.getACunDd());
        etCarrierName.setText(entity.getACarrier());
        etCarrierPhone.setText(entity.getAPhoneCyr());
        etCarrierUtilNumber.setText(entity.getATrademark());
        etSteriallierMethod.setText(entity.getADisinfection());
        tvErbiao.setText(entity.getAEarTag());
        etTransportation.setText(entity.getAYunZai());
        etHegeri.setText(entity.getAYouXiaoRi());
        etRemark.setText(entity.getAMemo1());
        etSignatureChecked.setText(entity.getAVeterinary());
        etCheckPhone.setText(entity.getADwPhone());
        tvProveTime.setText(entity.getDateQF());
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    @Override
    public void bindListener() {
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("绘制视图。。。");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();

                        init();

                        Looper.loop();
                    }
                }).start();
                //init();
            }
        });
    }

    private void init() {

        File file1 = new File(FileUtil.getInstance().getCacheDirPath(), "anim1.jpg");
        if (file1.exists()) file1.delete();
        PrintView<AnimAQueryListBean.DataListEntity> printView1 = new PrintView<>(this, entity);
        String path1 = FileUtil.getInstance().save2Local(printView1.getcacheBitmap(), file1.getAbsolutePath());
        whichPrinter = ((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt();
        if (whichPrinter == 1) {
            if (entity.getAEarTag()!=null&&entity.getAEarTag().getBytes().length>146){
                hasErTag =true;
                earTag = entity.getAEarTag();
            }
            saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "anim1.jpg");
            return;
        }
        phandler.sendEmptyMessage(104);
        while (true){
            BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null,phandler);
            int code = bPrint.print(path1, 2);
            if (code==0){
                break;
            }
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
        phandler.sendEmptyMessage(104);
        long time1 = System.currentTimeMillis();
        phandler.sendEmptyMessage(108);
        long time2 = 0;
        do {
            time2 = System.currentTimeMillis();
        }while(time2-time1<5000);
        phandler.sendEmptyMessage(105);
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = initDir();
                Bitmap bitmap = BitmapFactory.decodeFile(filePath + "/" + fileName);
                FileUtil.getInstance().save2Local(bitmap, file.getAbsolutePath() + "/" + fileName);
                if (hasErTag){
                    DrawUtil drawUtil = new DrawUtil(AnimalAQueryDetilActivity.this);
                    Bitmap bitmapEb=drawUtil.drawEbInBackSecond(earTag);
                    FileUtil.getInstance().save2Local(bitmapEb,
                            Environment.getExternalStorageDirectory() + Constance.dirName+"/ErBiao.jpg");
                }
                if(AppUtils.isAvilible(AnimalAQueryDetilActivity.this,"com.dynamixsoftware.printershare")){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    ComponentName cn = new ComponentName("com.dynamixsoftware.printershare", "com.dynamixsoftware.printershare.ActivityMain");
                    intent.setComponent(cn);
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("startprintshare", e.getMessage());
                    }
                    phandler.sendEmptyMessage(108);
                }else {
                    phandler.sendEmptyMessage(107);
                }

            }
        }).start();
    }


}
