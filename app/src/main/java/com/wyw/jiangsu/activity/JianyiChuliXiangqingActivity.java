package com.wyw.jiangsu.activity;

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
import com.wyw.jiangsu.bean.JianyiChuliTongzhidanListBean;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.utils.AppUtils;
import com.wyw.jiangsu.utils.BorisPrint;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.PrintView;
import com.wyw.jiangsu.utils.SPUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 检疫处理通知单查询详情
 * Created by Administrator on 2017/4/12.
 */
public class JianyiChuliXiangqingActivity extends MVPActivity {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_quarantine_no)
    EditText etQuarantineNo;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_goods_name)
    EditText etGoodsName;
    @BindView(R.id.et_goods_unit)
    EditText etGoodsUnit;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.et_goods_count)
    EditText etGoodsCount;
    @BindView(R.id.et_check_type)
    EditText etCheckType;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.tv_process_declare1)
    TextView tvProcessDeclare1;
    @BindView(R.id.tv_process_declare2)
    TextView tvProcessDeclare2;
    @BindView(R.id.tv_process_declare3)
    TextView tvProcessDeclare3;
    @BindView(R.id.tv_process_declare4)
    TextView tvProcessDeclare4;
    @BindView(R.id.et_party_name)
    EditText etPartyName;
    @BindView(R.id.et_party_phone)
    EditText etPartyPhone;
    @BindView(R.id.et_veterinarian_name)
    EditText etVeterinarianName;
    @BindView(R.id.et_supervision_phone)
    EditText etSupervisionPhone;
    @BindView(R.id.bt_print)
    Button btPrint;
    @BindView(R.id.SV)
    ScrollView SV;
    private JianyiChuliTongzhidanListBean.DataListBean entity;
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
                    Toast.makeText(JianyiChuliXiangqingActivity.this, "未检测到指定的printshare软件，请先下载", Toast.LENGTH_SHORT).show();
                    break;
                case 108:
                    Toast.makeText(JianyiChuliXiangqingActivity.this,"请选择图片,找到"+Constance.dirName+"文件夹打印对应的文件",Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianyi_detil);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("检疫处理通知单查询详情");
        entity = (JianyiChuliTongzhidanListBean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);

        etQuarantineNo.setText(entity.getNNumber());//检疫证编号
        etUserName.setText(entity.getNDanWei());//被处理的厂家
        etGoodsName.setText(entity.getNName());//物品名称
        etGoodsUnit.setText(entity.getFChuliDanwei());//物品单位
        etAnimType.setText(entity.getFChuliType());//物种类别
        etGoodsCount.setText(entity.getFChuliNum());//物种数量
        etPartyName.setText(entity.getNParties()); //当事人姓名 NParties
        etPartyPhone.setText(entity.getNDsrPhone());  //电话  NDsrPhone
        etVeterinarianName.setText(entity.getNVeterinary()); //官方兽医姓名 NVeterinary
        etSupervisionPhone.setText(entity.getNDwPhone()); //卫生监督所电话  NDwPhone

        tvProcessDeclare1.setText(entity.getNChuLi());          //处理结果1
        tvProcessDeclare2.setText(entity.getNChuLi1());         //处理结果2
        tvProcessDeclare3.setText(entity.getNChuLi2());         //处理结果3
        tvProcessDeclare4.setText(entity.getNChuLi3());         //处理结果4

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    @Override
    public void bindListener() {
        btPrint.setOnClickListener(new View.OnClickListener() {
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
            }
        });
    }

    private void init() {
        //BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null);
        File file1 = new File(FileUtil.getInstance().getCacheDirPath(), "jianyichuli.jpg");
        if (file1.exists()) file1.delete();
        PrintView<JianyiChuliTongzhidanListBean.DataListBean> printView1 = new PrintView<>(this,entity);
        String path1 = FileUtil.getInstance().save2Local(printView1.getcacheBitmap(), file1.getAbsolutePath());
        if (((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt() == 1) {
            saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "jianyichuli.jpg");
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
                if(AppUtils.isAvilible(JianyiChuliXiangqingActivity.this,"com.dynamixsoftware.printershare")){
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
