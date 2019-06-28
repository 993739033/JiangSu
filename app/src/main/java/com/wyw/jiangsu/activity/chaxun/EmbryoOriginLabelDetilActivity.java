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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
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
 * 种蛋、胚胎、精液检疫工作记录单查询详情
 * Created by Administrator on 2017/3/27.
 */
public class EmbryoOriginLabelDetilActivity extends MVPActivity {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.et_user_unit)
    EditText etUserUnit;
    @BindView(R.id.et_record_no)
    EditText etRecordNo;
    @BindView(R.id.et_carrier_util_number)
    EditText etCarrierUtilNumber;
    @BindView(R.id.et_identity_number)
    EditText etIdentityNumber;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.et_apply_type)
    EditText etApplyType;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.et_farm_name)
    EditText etFarmName;
    @BindView(R.id.et_quarantine_place)
    EditText etQuarantinePlace;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    //    @BindView(R.id.et_start_village)
//    EditText etStartVillage;
//    @BindView(R.id.et_start_other)
//    EditText etStartOther;
    @BindView(R.id.et_qiyundi_xiangqing)
    EditText et_qiyundi_xiangqing;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    //    @BindView(R.id.et_end_village)
//    EditText etEndVillage;
//    @BindView(R.id.et_end_other)
//    EditText etEndOther;
    @BindView(R.id.et_daodadi_xiangqing)
    EditText et_daodadi_xiangqing;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(R.id.tv_shipment_time)
    TextView tvShipmentTime;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.et_centification_unit)
    EditText etCentificationUnit;
    @BindView(R.id.et_centification_unit_no)
    EditText etCentificationUnitNo;
    @BindView(R.id.et_approval_unit)
    EditText etApprovalUnit;
    @BindView(R.id.et_approval_table_no)
    EditText etApprovalTableNo;
    @BindView(R.id.et_epidemic_area)
    EditText etEpidemicArea;
    @BindView(R.id.et_keep_file)
    EditText etKeepFile;
    @BindView(R.id.et_epidemic_status)
    EditText etEpidemicStatus;
    @BindView(R.id.et_condition)
    EditText etCondition;
    @BindView(R.id.et_zxqsc)
    EditText etZxqsc;
    @BindView(R.id.et_flag)
    EditText etFlag;
    @BindView(R.id.et_force)
    EditText etForce;
    @BindView(R.id.et_other_check)
    EditText etOtherCheck;
    @BindView(R.id.et_clinic_check)
    EditText etClinicCheck;
    @BindView(R.id.et_check_status)
    EditText etCheckStatus;
    @BindView(R.id.et_quarantine_rule)
    EditText etQuarantineRule;
    @BindView(R.id.et_quarantine_prove_number)
    EditText etQuarantineProveNumber;
    @BindView(R.id.lin_quarantine_rule_yes)
    LinearLayout linQuarantineRuleYes;
    @BindView(R.id.et_object)
    EditText etObject;
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
    //    @BindView(R.id.et_shiper_name)
//    EditText etShiperName;
//    @BindView(R.id.spinner_year_process)
//    Spinner spinnerYearProcess;
//    @BindView(R.id.spinner_month_process)
//    Spinner spinnerMonthProcess;
//    @BindView(R.id.spinner_day_process)
//    Spinner spinnerDayProcess;
    @BindView(R.id.et_signagure_check)
    EditText etSignagureCheck;
    @BindView(R.id.tv_time_signature_check)
    TextView tvTimeSignatureCheck;
    @BindView(R.id.et_signagure_checked)
    EditText etSignagureChecked;
    @BindView(R.id.tv_time_signature_checked)
    TextView tvTimeSignatureChecked;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.SV)
    ScrollView SV;
    private AnimZhongDanRedordBean.DataListBean dataListBean;
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
                    Toast.makeText(EmbryoOriginLabelDetilActivity.this, "未检测到指定的printshare软件，请先下载", Toast.LENGTH_SHORT).show();
                    break;
                case 108:
                    Toast.makeText(EmbryoOriginLabelDetilActivity.this,"请选择图片,找到"+Constance.dirName+"文件夹打印对应的文件",Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.embryo_origin_label_detil_activity);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("种蛋、胚胎、精液检疫工作记录单详情");
        Intent intent = getIntent();
        dataListBean = (AnimZhongDanRedordBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);

        etUserName.setText(dataListBean.getShippername());//货主姓名
        etUserUnit.setText(dataListBean.getSupervisename());//动物卫生监督所名称
        etUserPhone.setText(dataListBean.getTeltphone());//联系电话
        etRecordNo.setText(dataListBean.getRecordNo());//记录单编号
        etCarrierUtilNumber.setText(dataListBean.getToolid());//运载工具号
        etIdentityNumber.setText(dataListBean.getIdcardnum());//身份证号

        etAnimType.setText(dataListBean.getOnimalsort());//动物产品种类

        etApplyType.setText(dataListBean.getAnimalsort());//供体动物种类
        etAnimCount.setText(dataListBean.getAnimalnum() + dataListBean.getQCPDanWei());//数量及单位
        etFarmName.setText(dataListBean.getFarmsnme());//养殖场名称
        etQuarantinePlace.setText(dataListBean.getQuarantineaddress());//检疫地点

        etStartPlace.setText(dataListBean.getStartaddress() + dataListBean.getStartaddress1());//起运地省市
//        etStartVillage.setText(dataListBean.getStartaddress2());//起运地县
//        etStartOther.setText(dataListBean.getStartaddress3());//起运地乡
        et_qiyundi_xiangqing.setText(dataListBean.getStartaddress2()+" "+dataListBean.getStartaddress3());
        etEndPlace.setText(dataListBean.getRdddzxx() + dataListBean.getRdddzxx1());//到达地省市
//        etEndVillage.setText(dataListBean.getRdddzxx2());//到达地县
//        etEndOther.setText(dataListBean.getRdddzxx3());//到达地乡

        et_daodadi_xiangqing.setText(dataListBean.getRdddzxx2()+" "+dataListBean.getRdddzxx3());


        tvInspectionTime.setText(dataListBean.getInspectiontime() + " " + dataListBean.getDr() + "时");//报检时间
        tvShipmentTime.setText(dataListBean.getQuarantinetime() + " " + dataListBean.getDr1() + "时");//检疫时间

        etCentificationUnit.setText(dataListBean.getFzdw()); //发证单位
        etCentificationUnitNo.setText(dataListBean.getBhid());//编号
        etApprovalUnit.setText(dataListBean.getSpdw());//《跨省引进乳用种用动物检疫审批表》审批单位
        etApprovalTableNo.setText(dataListBean.getSpbhb());//审批表编号
        etEpidemicArea.setText(dataListBean.getEridemicarea());//是否是疫区
        etKeepFile.setText(dataListBean.getRecordrule());//养殖档案是否归档
        etEpidemicStatus.setText(dataListBean.getOutbreak());//养殖场疫情(6个月内)
        etCondition.setText(dataListBean.getDwtjhgz());//《动物防疫条件合格证》
        etFlag.setText(dataListBean.getLogorule());//耳标佩戴是否符合规定
        etZxqsc.setText(dataListBean.getZxqsc());//《种畜禽生产经营许可证》
        etForce.setText(dataListBean.getQzmy());//是否经强制免疫并在有效期内

        etOtherCheck.setText(dataListBean.getOther());//其他项目检查
        etClinicCheck.setText(dataListBean.getClinical());//临床检查
        etCheckStatus.setText(dataListBean.getSituation());//法定检疫对象实验室检测情况
        etQuarantineRule.setText(dataListBean.getQuarantinerule());//是否符合检疫规定
        etQuarantineProveNumber.setText(dataListBean.getCode());//动物检疫合格证明编号
        etSignagureCheck.setText(dataListBean.getVetname());//检疫人员签字
        tvTimeSignatureCheck.setText(dataListBean.getGftime());//检疫人员签字时间
        etSignagureChecked.setText(dataListBean.getRhzqz());//货主签字
        tvTimeSignatureChecked.setText(dataListBean.getRhzdate());//货主签字时间

        tvErbiao.setText(dataListBean.getOnimalsortName());//耳标号;

        if (dataListBean.getQuarantinerule().contains("是") && dataListBean.getClinical().contains("合格")) {
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

    @Override
    public void bindListener() {
        btUpload.setOnClickListener(new View.OnClickListener() {
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
        File file6 = new File(FileUtil.getInstance().getCacheDirPath(), "zhongdan.jpg");
        if (file6.exists()) file6.delete();
        PrintView<AnimZhongDanRedordBean.DataListBean> printView6 = new PrintView<>(this, dataListBean);
        String path6 = FileUtil.getInstance().save2Local(printView6.getcacheBitmap(), file6.getAbsolutePath());
        if (((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt() == 1) {
            saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "zhongdan.jpg");
            return;
        }
        phandler.sendEmptyMessage(104);
        while (true){
            BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null,phandler);
            int code = bPrint.print(path6, 2);
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
                if(AppUtils.isAvilible(EmbryoOriginLabelDetilActivity.this,"com.dynamixsoftware.printershare")){
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
