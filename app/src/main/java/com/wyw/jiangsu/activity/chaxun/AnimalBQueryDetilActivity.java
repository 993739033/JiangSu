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
import com.wyw.jiangsu.activity.print.JQPrintActivity;
import com.wyw.jiangsu.activity.print.PrintMainActivity;
import com.wyw.jiangsu.bean.AnimBQueryListBean;
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
 * 动物B查询列表详情
 * Created by Administrator on 2017/3/27.
 */
public class AnimalBQueryDetilActivity extends MVPActivity {

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
    TextView etStartPlace;
    @BindView(R.id.et_start_address)
    EditText et_start_address;
    //    @BindView(R.id.et_start_village)
//    EditText etStartVillage;
//    @BindView(R.id.et_start_other)
//    EditText etStartOther;
    @BindView(R.id.et_end_place)
    TextView etEndPlace;
    @BindView(R.id.et_end_address)
    EditText et_end_address;
    //    @BindView(R.id.et_end_village)
//    EditText etEndVillage;
//    @BindView(R.id.et_end_other)
//    EditText etEndOther;
    @BindView(R.id.tv_erbiao)
    EditText tvErbiao;
    @BindView(R.id.et_signature_checked)
    EditText etSignatureChecked;
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
    private AnimBQueryListBean.DataListEntity entity;
    private Handler phandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
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
                    Toast.makeText(AnimalBQueryDetilActivity.this, "未检测到指定的printshare软件，请先下载", Toast.LENGTH_SHORT).show();
                    break;
                case 108:
                    Toast.makeText(AnimalBQueryDetilActivity.this, "请选择图片,找到" + Constance.dirName + "文件夹打印对应的文件", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private ProgressDialog progressDialog;
    private int whichPrint;
    private boolean hasErTag;
    private String earTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalb_query_detil_activity);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("动物B查询详情");
        entity = (AnimBQueryListBean.DataListEntity) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        etQuarantineNo.setText(entity.getAQNumber());
        etUserName.setText(entity.getAQCargoOwner());
        etUserPhone.setText(entity.getAQPhone());
        etAnimType.setText(entity.getAQXuZhong());
        etAnimCount.setText(entity.getAQQuantity() + entity.getAQDanWei());
        etPurpose.setText(entity.getAQYongTu());
        etStartPlace.setText(entity.getFSunitName() + entity.getAQShiQy() + entity.getAQXianQy());
        et_start_address.setText(entity.getAQXiangQy() + " " + entity.getAQCunQy());
//        etStartVillage.setText(entity.getAQXiangQy());
//        etStartOther.setText(entity.getAQCunQy());
        etEndPlace.setText(entity.getFSunitName() + entity.getAQShiDd() + entity.getAQXianDd());
        et_end_address.setText(entity.getAQXiangDd() + " " + entity.getAQCunDd());
//        etEndVillage.setText(entity.getAQXiangDd());
//        etEndOther.setText(entity.getAQCunDd());
        tvErbiao.setText(entity.getAQEarTag());
        etSignatureChecked.setText(entity.getAQVeterinary());

        tvProveTime.setText(entity.getDateQF());
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    @Override
    public void bindListener() {
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int printer = getSharedPreferences("print", MODE_PRIVATE).getInt("printer", 0);
                //公司热敏打印机
                if (printer == 2) {
                    Intent intent = new Intent(v.getContext(), PrintMainActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("index", 1);
                    bundle2.putSerializable(Constance.START_ACTIVITY_DATA, entity);
                    intent.putExtras(bundle2);
                    startActivity(intent);
                }if (printer==3){
                    Intent intent = new Intent(v.getContext(), JQPrintActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("index", 1);
                    bundle2.putSerializable(Constance.START_ACTIVITY_DATA, entity);
                    intent.putExtras(bundle2);
                    startActivity(intent);
                }
                else {
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

            }
        });
    }

    private void init() {
        //  BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null);
        File file2 = new File(FileUtil.getInstance().getCacheDirPath(), "anim2.jpg");
        if (file2.exists()) file2.delete();
        PrintView<AnimBQueryListBean.DataListEntity> printView2 = new PrintView<>(this, entity);
        String path2 = FileUtil.getInstance().save2Local(printView2.getcacheBitmap(), file2.getAbsolutePath());
        // bPrint.print(path2, 1);
        whichPrint = ((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt();
        if (whichPrint == 1) {
            if (entity.getAQEarTag() != null && entity.getAQEarTag().getBytes().length > 146) {
                hasErTag = true;
                earTag = entity.getAQEarTag();
            }
            saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "anim2.jpg");
            return;
        }
        phandler.sendEmptyMessage(104);
        while (true) {
            BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null, phandler);
            int code = bPrint.print(path2, 2);
            if (code == 0) {
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
        } while (time2 - time1 < 5000);
        phandler.sendEmptyMessage(105);
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = initDir();
                Bitmap bitmap = BitmapFactory.decodeFile(filePath + "/" + fileName);
                FileUtil.getInstance().save2Local(bitmap, file.getAbsolutePath() + "/" + fileName);
                if (hasErTag) {
                    DrawUtil drawUtil = new DrawUtil(AnimalBQueryDetilActivity.this);
                    Bitmap bitmapEb = drawUtil.drawEbInBack(earTag);
                    FileUtil.getInstance().save2Local(bitmapEb,
                            Environment.getExternalStorageDirectory() + Constance.dirName + "/ErBiao.jpg");
                }
                if (AppUtils.isAvilible(AnimalBQueryDetilActivity.this, "com.dynamixsoftware.printershare")) {
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
                } else {
                    phandler.sendEmptyMessage(107);
                }

            }
        }).start();
    }


}
