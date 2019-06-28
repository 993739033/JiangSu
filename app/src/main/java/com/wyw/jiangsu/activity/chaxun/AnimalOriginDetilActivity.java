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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.PrintAcitivty;
import com.wyw.jiangsu.bean.AH_AnimalOrigin;
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
 * 动物产地工作记录单查询详情
 * Created by Administrator on 2017/3/27.
 */
public class AnimalOriginDetilActivity extends MVPActivity {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_bianhao_id)
    EditText etBianhaoId;
    @BindView(R.id.et_bianhao_recordNo)
    EditText etBianhaoRecordNo;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_unit)
    EditText etUserUnit;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.et_farm_name)
    EditText etFarmName;
    @BindView(R.id.et_quarantineaddress)
    EditText etQuarantineaddress;
    @BindView(R.id.et_domesticatedid)
    EditText etDomesticatedid;
    @BindView(R.id.et_catchid)
    EditText etCatchid;
    @BindView(R.id.et_carrier_util_number)
    EditText etCarrierUtilNumber;
    @BindView(R.id.et_identity_number)
    EditText etIdentityNumber;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.et_purpose)
    EditText etPurpose;
    @BindView(R.id.et_source)
    EditText etSource;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(R.id.tv_shipment_time)
    TextView tvShipmentTime;
    @BindView(R.id.et_yiqu)
    EditText etYiqu;
    @BindView(R.id.et_dangan)
    EditText etDangan;
    @BindView(R.id.et_yiqing)
    EditText etYiqing;
    @BindView(R.id.et_chuqin_biaoshi)
    EditText etChuqinBiaoshi;
    @BindView(R.id.et_qiangzhi_mianyi)
    EditText etQiangzhiMianyi;
    @BindView(R.id.et_other_check)
    EditText etOtherCheck;
    @BindView(R.id.et_linchuang_jiancha)
    EditText etLinchuangJiancha;
    @BindView(R.id.et_shiyanshi_jiancha)
    EditText etShiyanshiJiancha;
    @BindView(R.id.et_jiance_qingkuang)
    EditText etJianceQingkuang;
    @BindView(R.id.et_jianyi_guiding)
    EditText etJianyiGuiding;
    @BindView(R.id.et_quarantine_prove_number)
    EditText etQuarantineProveNumber;
    @BindView(R.id.lin_quarantine_rule_yes)
    RelativeLayout linQuarantineRuleYes;
    @BindView(R.id.et_object)
    TextView etObject;
    @BindView(R.id.et_object_number)
    EditText etObjectNumber;
    @BindView(R.id.et_yibanchuli)
    EditText etYibanchuli;
    @BindView(R.id.et_general_process)
    EditText etGeneralProcess;
    @BindView(R.id.et_wuhaihuachuli)
    EditText etWuhaihuachuli;
    @BindView(R.id.et_harmless_process)
    EditText etHarmlessProcess;
    @BindView(R.id.et_quarantine_notification_number)
    EditText etQuarantineNotificationNumber;
    @BindView(R.id.lin_quarantine_rule_no)
    LinearLayout linQuarantineRuleNo;
    @BindView(R.id.et_signagure_check)
    EditText etSignagureCheck;
    @BindView(R.id.tv_time_signature_check)
    TextView tvTimeSignatureCheck;
    @BindView(R.id.et_signagure_checked)
    EditText etSignagureChecked;
    @BindView(R.id.tv_time_signature_checked)
    TextView tvTimeSignatureChecked;
    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.SV)
    ScrollView SV;
    private AH_AnimalOrigin.DataListBean dataListBean;
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
                case 105://关闭对话框
                    progressDialog.dismiss();
                    break;
                case 107://关闭对话框
                    Toast.makeText(AnimalOriginDetilActivity.this, "未检测到指定的printshare软件，请先下载", Toast.LENGTH_SHORT).show();
                    break;
                case 108:
                    Toast.makeText(AnimalOriginDetilActivity.this,"请选择图片,找到"+Constance.dirName+"文件夹打印对应的文件",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_origin_detil_activity);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("动物产地工作记录单详情");
        Intent intent = getIntent();
        dataListBean = (AH_AnimalOrigin.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);

        etBianhaoId.setText(dataListBean.getId());          //编号
        etBianhaoRecordNo.setText(dataListBean.getRecordNo());//记录单编号
        tvInspectionTime.setText(dataListBean.getInspectiontime());//报检时间   两个字段 分开
        tvShipmentTime.setText(dataListBean.getQuarantinetime());//检疫时间     两个字段 分开
        etUserName.setText(dataListBean.getShippername());      // 货主姓名
        etUserPhone.setText(dataListBean.getTeltphone());       // 联系电话
        etUserUnit.setText(dataListBean.getSupervisename());    // 监督所名称
        etQuarantineaddress.setText(dataListBean.getQuarantineaddress()); //检疫地点
        etDomesticatedid.setText(dataListBean.getDomesticatedid());     //繁殖许可证号
        etCatchid.setText(dataListBean.getCatchid());                   //捕捉许可证号
        etCarrierUtilNumber.setText(dataListBean.getToolid());     //运载工具号
        etIdentityNumber.setText(dataListBean.getIdcardnum());          //身份证号
        etAnimType.setText(dataListBean.getAnimalsort());       // 动物种类
        etPurpose.setText(dataListBean.getMyuse());             //用途
        etSource.setText(dataListBean.getAnimalsources());      //动物来源
        etFarmName.setText(dataListBean.getFarmsnme());         // 养殖场、村、 原驻地或捕获地   养殖场名字
        etAnimCount.setText(dataListBean.getAnimalnum() + dataListBean.getQDWDanWei());  //数量和单位
        etStartPlace.setText(dataListBean.getRqydzxx());        //起运地名称
        etEndPlace.setText(dataListBean.getRdddzxxs());         //到达地
        etSignagureCheck.setText(dataListBean.getVetname());   //检疫人员
        tvTimeSignatureCheck.setText(dataListBean.getGftime()); //检疫人员时间
        etSignagureChecked.setText(dataListBean.getRhzqz());   //货主签字
        tvTimeSignatureChecked.setText(dataListBean.getRhzdate()); //货主签字时间
        etYiqu.setText(dataListBean.getEridemicarea());//是否疫区
        etDangan.setText(dataListBean.getRecordrule());//养殖档案是否归档
        etYiqing.setText(dataListBean.getOutbreak());//养殖场疫情
        etChuqinBiaoshi.setText(dataListBean.getLogorule());//畜禽标识是否符合规定
        etQiangzhiMianyi.setText(dataListBean.getQzmy());//是否强制免疫
        etOtherCheck.setText(dataListBean.getOther());////其他

        etLinchuangJiancha.setText(dataListBean.getClinical());//临床检查
        //需要在这里进行判断  如果是，不显示   如果不是，显示另一个界面

        etShiyanshiJiancha.setText(dataListBean.getLaboratory());//实验室检查
        etJianceQingkuang.setText(dataListBean.getSituation());//检测情况
        etJianyiGuiding.setText(dataListBean.getQuarantinerule());////是否符合检疫规定
        //需要在这里进行判断  如果是，不显示   如果不是，显示另一个界面

        etQuarantineProveNumber.setText(dataListBean.getCode());//《动物检疫合格证明》编号

        if (dataListBean.getQuarantinerule().contains("是")) {
            linQuarantineRuleNo.setVisibility(View.GONE);
        } else {
            linQuarantineRuleNo.setVisibility(View.VISIBLE);
            etObject.setText(dataListBean.getRunit());//法定检疫对象
            etObjectNumber.setText(dataListBean.getLegalnum() + "");//法定检疫对象数量
            etYibanchuli.setText(dataListBean.getGeneral());//一般处理对象
            etGeneralProcess.setText(dataListBean.getHarmlesnums() + ""); //一般处理数量
            etWuhaihuachuli.setText(dataListBean.getHarmless());//无害化处理对象
            etHarmlessProcess.setText(dataListBean.getHarmlesnum() + ""); //无害化处理数量
            etQuarantineNotificationNumber.setText(dataListBean.getJyclcard());//检疫处理通知单编号

        }
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    //    private BorisPrint bPrint;
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
            }
        });
    }

    private void init() {
        //BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null);
        File file5 = new File(FileUtil.getInstance().getCacheDirPath(), "area.jpg");
        if (file5.exists()) file5.delete();
        PrintView<AH_AnimalOrigin.DataListBean> printView5 = new PrintView<>(this, dataListBean);
        String path5 = FileUtil.getInstance().save2Local(printView5.getcacheBitmap(), file5.getAbsolutePath());
        if (((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt() == 1) {
            phandler.sendEmptyMessage(105);
            saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "area.jpg");
            return;
        }
        phandler.sendEmptyMessage(104);
        while (true){
            BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null,phandler);
            int code = bPrint.print(path5, 2);
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
                if(AppUtils.isAvilible(AnimalOriginDetilActivity.this,"com.dynamixsoftware.printershare")){
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
