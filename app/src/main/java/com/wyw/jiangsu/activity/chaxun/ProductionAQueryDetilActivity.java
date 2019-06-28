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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.ProductionAListBean;
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
 * 产品A查询列表详情
 * Created by Administrator on 2017/3/27.
 */
public class ProductionAQueryDetilActivity extends MVPActivity {

    @BindView(R.id.et_quarantine_no)
    EditText etQuarantineNo;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.et_product_name)
    EditText etProductName;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
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
    @BindView(R.id.bt_print)
    Button btPrint;
    @BindView(R.id.SV)
    ScrollView SV;
    private ProductionAListBean.DataListEntity entity;
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
                    Toast.makeText(ProductionAQueryDetilActivity.this, "未检测到指定的printshare软件，请先下载", Toast.LENGTH_SHORT).show();
                    break;
                case 108:
                    Toast.makeText(ProductionAQueryDetilActivity.this,"请选择图片,找到"+Constance.dirName+"文件夹打印对应的文件",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productiona_query_detil_activity);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("产品A查询详情");
        entity = (ProductionAListBean.DataListEntity) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        etQuarantineNo.setText(entity.getPNumber());
        etUserName.setText(entity.getPCargoOwner());
        etUserPhone.setText(entity.getPPhone());
        etProductName.setText(entity.getPName());
        etAnimCount.setText(entity.getPQuantity() + entity.getPDanWei());
        etStartPlace.setText(entity.getPProductionunit());
        etEndPlace.setText(entity.getPMemo2());

        etCarrierName.setText(entity.getPCarrier());
        etCarrierPhone.setText(entity.getPPhoneCyr());
        etCarrierUtilNumber.setText(entity.getPTrademark());
        etSteriallierMethod.setText(entity.getPDisinfection());

        etTransportation.setText(entity.getPYunZai());
        etHegeri.setText(entity.getPYouXiaoRi());
        etRemark.setText(entity.getPMemo1());
        etSignatureChecked.setText(entity.getPVeterinary());
        etCheckPhone.setText(entity.getPDwPhone());
        tvProveTime.setText(entity.getDateQF());

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
        File file3 = new File(FileUtil.getInstance().getCacheDirPath(), "product1.jpg");
        if (file3.exists()) file3.delete();
        PrintView<ProductionAListBean.DataListEntity> printView3 = new PrintView<>(this, entity);
        String path3 = FileUtil.getInstance().save2Local(printView3.getcacheBitmap(), file3.getAbsolutePath());
        if (((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt() == 1) {
            saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "product1.jpg");
            return;
        }
        phandler.sendEmptyMessage(104);
        while (true){
            BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null,phandler);
            int code = bPrint.print(path3, 2);
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
                if(AppUtils.isAvilible(ProductionAQueryDetilActivity.this,"com.dynamixsoftware.printershare")){
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
