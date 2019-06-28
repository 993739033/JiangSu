package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.QroducingAreaProcessListBean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimProducingAreaRecordActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.AnimProducingAreaRecordActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.R.id.rb_clinic_check_yes;
import static com.wyw.jiangsu.R.id.rb_quarantine_rule_yes;

/**
 * 动物产地检疫工作记录单详情
 */
public class AnimProducingAreaRecordActivity extends MVPActivity<AnimProducingAreaRecordActivityPresenter> implements IAnimProducingAreaRecordActivity, ITime {
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
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
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
    @BindView(R.id.rb_epidemic_area_yes)
    RadioButton rbEpidemicAreaYes;
    @BindView(R.id.rb_epidemic_area_no)
    RadioButton rbEpidemicAreaNo;
    @BindView(R.id.rb_keep_file_yes)
    RadioButton rbKeepFileYes;
    @BindView(R.id.rb_keep_file_no)
    RadioButton rbKeepFileNo;
    @BindView(R.id.rb_epidemic_status_yes)
    RadioButton rbEpidemicStatusYes;
    @BindView(R.id.rb_epidemic_staus_no)
    RadioButton rbEpidemicStausNo;
    @BindView(R.id.rb_epidemic_condition_yes)
    RadioButton rbEpidemicConditionYes;
    @BindView(R.id.rb_epidemic_condition_no)
    RadioButton rbEpidemicConditionNo;
    @BindView(R.id.rb_force_yes)
    RadioButton rbForceYes;
    @BindView(R.id.rb_force_no)
    RadioButton rbForceNo;
    @BindView(R.id.et_other_check)
    EditText etOtherCheck;
    @BindView(R.id.rb_clinic_check_yes)
    RadioButton rbClinicCheckYes;
    @BindView(R.id.rb_clinic_check_no)
    RadioButton rbClinicCheckNo;
    @BindView(R.id.rg_clinic_check_yes)
    RadioGroup rgClinicCheckYes;
    @BindView(R.id.rb_check_lab_yes)
    RadioButton rbCheckLabYes;
    @BindView(R.id.rb_check_lab_no)
    RadioButton rbCheckLabNo;
    @BindView(R.id.rb_check_status_yes)
    RadioButton rbCheckStatusYes;
    @BindView(R.id.rb_check_status_no)
    RadioButton rbCheckStatusNo;
    @BindView(R.id.rb_quarantine_rule_yes)
    RadioButton rbQuarantineRuleYes;
    @BindView(R.id.rb_quarantine_rule_no)
    RadioButton rbQuarantineRuleNo;
    @BindView(R.id.rg_quarantine_rule_yes)
    RadioGroup rgQuarantineRuleYes;
    @BindView(R.id.et_quarantine_prove_number)
    EditText etQuarantineProveNumber;
    @BindView(R.id.lin_quarantine_rule_yes)
    RelativeLayout linQuarantineRuleYes;
    @BindView(R.id.et_object_number)
    EditText etObjectNumber;
    @BindView(R.id.et_general_process)
    EditText etGeneralProcess;
    @BindView(R.id.et_harmless_process)
    EditText etHarmlessProcess;
    @BindView(R.id.et_quarantine_notification_number)
    EditText etQuarantineNotificationNumber;
    @BindView(R.id.lin_quarantine_rule_no)
    LinearLayout linQuarantineRuleNo;
    @BindView(R.id.et_signagure_check)
    EditText etSignagureCheck;
    @BindView(R.id.et_signagure_checked)
    EditText etSignagureChecked;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.ll_check_qingkuang)
    RelativeLayout llCheckQingkuang;
    @BindView(R.id.rg_shiyanshi_check)
    RadioGroup rgShiyanshiCheck;
    @BindView(R.id.cb_jiance)
    CheckBox cbJiance;
    @BindView(R.id.cb_geli)
    CheckBox cbGeli;
    @BindView(R.id.cb_zhiliao)
    CheckBox cbZhiliao;
    @BindView(R.id.cb_qita)
    CheckBox cbQita;
    @BindView(R.id.cb_fenshao)
    CheckBox cbFenshao;
    @BindView(R.id.cb_huazhi)
    CheckBox cbHuazhi;
    @BindView(R.id.cb_yanmai)
    CheckBox cbYanmai;
    @BindView(R.id.cb_fajiao)
    CheckBox cbFajiao;
    @BindView(R.id.cb_wuhaiqita)
    CheckBox cbWuhaiqita;
    @BindView(R.id.scrollV)
    ScrollView scrollV;
    @BindView(R.id.et_address_type)
    EditText etAddressType;
    @BindView(R.id.et_address_type2)
    EditText etAddressType2;
    @BindView(R.id.tv_jy_time)
    TextView tv_jy_time;
    @BindView(R.id.tv_bj_time)
    TextView tv_bj_time;
    @BindView(R.id.tv_bj_time_1)
    TextView tv_bj_time_1;
    @BindView(R.id.tv_object)
    TextView tv_object;

    String[] jy_time = new String[4];
    String[] bj_time = new String[4];
    String[] bj_time1 = new String[4];
    private TimeSelectDialog jyDialog;//检疫时间选择
    private TimeSelectDialog bjDialog;//报检时间选择
    private TimeSelectDialog bjDialog1;//报检时间1选择
    private DataSelectDialog objectDialog;//对象选择

    private AH_AnimalOrigin.DataListBean uploadBean;//上传的类
    private TimePresenter timePresenter;
    private String time1;
    private String time2;

    private List<String> endDayList;
    QroducingAreaProcessListBean.DataListBean transformeredd;
    AH_AnimalOrigin.DataListBean dataListBean1;
    private Intent intent1;


    private List<String> anniu;
    private List<String> anniu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_producing_area_record);
        ButterKnife.bind(this);
        scrollV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected AnimProducingAreaRecordActivityPresenter createPresenter() {
        return new AnimProducingAreaRecordActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("动物产地工作记录单");
        getPresenter().getBianhao();
        getPresenter().getJiludanhao();
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        setTime();
        //默认选项
        cbJiance.setChecked(true);
        cbFenshao.setChecked(true);

        intent1 = getIntent();
        transformeredd = (QroducingAreaProcessListBean.DataListBean) intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        //转换
        uploadBean = transformer(transformeredd);
//        etBianhaoId.setText(uploadBean.getId());          //编号
//        etBianhaoRecordNo.setText(uploadBean.getRecordNo());//记录单编号
        tvInspectionTime.setText(uploadBean.getInspectiontime() + " " + uploadBean.getDr() + "时");//报检时间   两个字段 分开
        etUserName.setText(uploadBean.getShippername());      // 货主姓名
        etUserPhone.setText(uploadBean.getTeltphone());       // 联系电话
        etUserUnit.setText(uploadBean.getSupervisename());    // 监督所名称
        //   toolid运载号  自己写  数量animalnum   显示数量和单位   上交数量
        etQuarantineaddress.setText(uploadBean.getQuarantineaddress()); //检疫地点
        etDomesticatedid.setText(uploadBean.getDomesticatedid());     //繁殖许可证号
        etCatchid.setText(uploadBean.getCatchid());                   //捕捉许可证号
        etIdentityNumber.setText(uploadBean.getIdcardnum());          //身份证号
        etAnimType.setText(uploadBean.getAnimalsort());       // 动物种类
        etPurpose.setText(uploadBean.getMyuse());             //用途
        etSource.setText(uploadBean.getAnimalsources());      //动物来源
        etFarmName.setText(uploadBean.getFarmsnme());         // 养殖场、村、 原驻地或捕获地   养殖场名字
        etAnimCount.setText(uploadBean.getAnimalnum() + "");  //数量和单位
        tvDanwei.setText(uploadBean.getQDWDanWei());
        etStartPlace.setText(uploadBean.getRqydzxx());        //起运地名称
        etAddressType.setText(uploadBean.getStartaddress5());
        etEndPlace.setText(uploadBean.getRdddzxxs());         //到达地
        etAddressType2.setText(uploadBean.getRdddzxx5());


        //检疫人员
        etSignagureCheck.setText(uploadBean.getVetname());
        //货主签字
        etSignagureChecked.setText(uploadBean.getShippername());
        objectDialog = new DataSelectDialog(this);
        objectDialog.setTitle("对象选择");
        objectDialog.setDatas(getResources().getStringArray(R.array.quarantine_objects));
        objectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                tv_object.setText(data);
            }

            @Override
            public void cancel() {

            }
        });


    }

    private AH_AnimalOrigin.DataListBean transformer(QroducingAreaProcessListBean.DataListBean transformered) {
        AH_AnimalOrigin.DataListBean uploadBean = new AH_AnimalOrigin.DataListBean();
        uploadBean.setId(transformered.getFStId());
//        uploadBean.setId();//编号
        uploadBean.setQDWNumber(transformered.getQDWNumber());//申报单编号
        uploadBean.setInspectiontime(transformered.getDateQF());//报检时间   两个字段 分开
        uploadBean.setDr(transformered.getDr());
//        uploadBean.setQuarantinetime(transformered.getDateNpy());//检疫时间     两个字段 分开
        uploadBean.setShippername(transformered.getQDWCargoOwner());// 货主姓名
        uploadBean.setTeltphone(transformered.getQDWPhone());// 联系电话
        uploadBean.setSupervisename(getPresenter().getUser().getFUSEENAME());// 监督所名称
        uploadBean.setQuarantineaddress(transformered.getQDWAddress());//检疫地点
        uploadBean.setDomesticatedid(transformered.getXKZH());//繁殖许可证号
        uploadBean.setCatchid(transformered.getXKZH1()); //捕捉许可证号
//        uploadBean.setIdcardnum(transformered.get()); //身份证号
        uploadBean.setAnimalsort(transformered.getQDWXuZhong());// 动物种类
        uploadBean.setMyuse(transformered.getQDWYongTu()); //用途
        uploadBean.setAnimalsources(transformered.getQDWLaiYuan());  //动物来源
        uploadBean.setFarmsnme(transformered.getYzcmc());// 养殖场、村、 原驻地或捕获地   养殖场名字
        uploadBean.setAnimalnum(Integer.valueOf(transformered.getQDWQuantity()));//数量
        uploadBean.setQDWDanWei(transformered.getQDWDanWei());//单位
        uploadBean.setRqydzxx(transformered.getQDAddQy());//起运地名称
        uploadBean.setRdddzxxs(transformered.getQDWAddDd()); //到达地
        uploadBean.setVetname(getPresenter().getUser().getFUNAME());//检疫人员
        //起运地
        uploadBean.setStartaddress(transformered.getQDWShengQy());
        uploadBean.setStartaddress1(transformered.getQDWShiQy());
        uploadBean.setStartaddress2(transformered.getQDWXianQy());
        uploadBean.setStartaddress3(transformered.getQDWXiangQy());
        uploadBean.setStartaddress4(transformered.getQDWCunQy());
        uploadBean.setStartaddress5(transformered.getQDWTypeQy());
        uploadBean.setRdddzxx(transformered.getQDWShengDd());
        uploadBean.setRdddzxx1(transformered.getQDWShiDd());
        uploadBean.setRdddzxx2(transformered.getQDWXianDd());
        uploadBean.setRdddzxx3(transformered.getQDWXiangDd());
        uploadBean.setRdddzxx4(transformered.getQDWCunDd());
        uploadBean.setRdddzxx5(transformered.getQDWTypeDd());
        return uploadBean;
    }

    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        jyDialog = new TimeSelectDialog(this,"选择检疫时间");
        jyDialog.initTime();
        jyDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                jy_time[0] = year;
                jy_time[1] = month;
                jy_time[2] = day;
                jy_time[3] = hour;
                tv_jy_time.setText(year+"-"+month+"-"+day+" "+hour+"时");
            }
        });
        jy_time[0] = jyDialog.getmCurrentYear()+"";
        jy_time[1] = jyDialog.getmCurrentMonth()+"";
        jy_time[2] = jyDialog.getmCurrentDay()+"";
        jy_time[3] = jyDialog.getmCurrentHour()+"";
        tv_jy_time.setText(jy_time[0]+"-"+jy_time[1]+"-"+jy_time[2]+" "+jy_time[3]+"时");




        bjDialog = new TimeSelectDialog(this,"请选择报检时间");
        bjDialog.initTime();
        bjDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                bj_time[0] = year;
                bj_time[1] = month;
                bj_time[2] = day;
                bj_time[3] = hour;
                tv_bj_time.setText(year+"-"+month+"-"+day+" "+hour+"时");
            }
        });

        bj_time[0] = bjDialog.getmCurrentYear()+"";
        bj_time[1] = bjDialog.getmCurrentMonth()+"";
        bj_time[2] = bjDialog.getmCurrentDay()+"";
        bj_time[3] = bjDialog.getmCurrentHour()+"";
        tv_bj_time.setText(bj_time[0]+"-"+bj_time[1]+"-"+bj_time[2]+" "+bj_time[3]+"时");

        bjDialog1 = new TimeSelectDialog(this,"请选择报检时间");
        bjDialog1.initTime();
        bjDialog1.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                bj_time1[0] = year;
                bj_time1[1] = month;
                bj_time1[2] = day;
                bj_time1[3] = hour;
                tv_bj_time_1.setText(year+"-"+month+"-"+day+" "+hour+"时");
            }
        });

        bj_time1[0] = bjDialog1.getmCurrentYear()+"";
        bj_time1[1] = bjDialog1.getmCurrentMonth()+"";
        bj_time1[2] = bjDialog1.getmCurrentDay()+"";
        bj_time1[3] = bjDialog1.getmCurrentHour()+"";
        tv_bj_time_1.setText(bj_time1[0]+"-"+bj_time1[1]+"-"+bj_time1[2]+" "+bj_time1[3]+"时");
    }



    boolean lock = false;

    @Override
    public void bindListener() {


        //实验室检查
        rgShiyanshiCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_check_lab_yes) {
                    llCheckQingkuang.setVisibility(View.VISIBLE);
                } else {
                    llCheckQingkuang.setVisibility(View.GONE);
                }
            }
        });

        tv_jy_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jyDialog.show();
            }
        });

        tv_bj_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bjDialog.show();
            }
        });

        tv_bj_time_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bjDialog1.show();
            }
        });

        tv_object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectDialog.show();
            }
        });

        rgClinicCheckYes.setOnCheckedChangeListener((group, checkedId) -> {
            if (lock) return;
            lock = true;
            if (checkedId == rb_clinic_check_yes) {
                linQuarantineRuleYes.setVisibility(View.VISIBLE);
                linQuarantineRuleNo.setVisibility(View.GONE);
                rgQuarantineRuleYes.check(R.id.rb_quarantine_rule_yes);
            } else {
                linQuarantineRuleYes.setVisibility(View.GONE);
                linQuarantineRuleNo.setVisibility(View.VISIBLE);
                rgQuarantineRuleYes.check(R.id.rb_quarantine_rule_no);
            }
            lock = false;
        });
        rgQuarantineRuleYes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (lock) return;
                lock = true;
                if (checkedId == rb_quarantine_rule_yes) {
                    linQuarantineRuleYes.setVisibility(View.VISIBLE);
                    linQuarantineRuleNo.setVisibility(View.GONE);
                    rgClinicCheckYes.check(R.id.rb_clinic_check_yes);
                } else {
                    linQuarantineRuleYes.setVisibility(View.GONE);
                    linQuarantineRuleNo.setVisibility(View.VISIBLE);
                    rgClinicCheckYes.check(R.id.rb_clinic_check_no);
                }
                lock = false;
            }
        });
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传保存的文件
                if (check()) {
                    showNormalDialog();
                }
            }
        });
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AnimProducingAreaRecordActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upLoad(getData());
                        dialog.dismiss();
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    /**
     * 要上传的东西
     *
     * @return
     */
    private AH_AnimalOrigin.DataListBean getData() {
        dataListBean1 = new AH_AnimalOrigin.DataListBean();
        //dataListBean1.setId(etBianhaoId.getText().toString());           //编号
        dataListBean1.setRecordNo(etBianhaoRecordNo.getText().toString());//记录单编号
        uploadBean.setRecordNo(dataListBean1.getRecordNo());
        dataListBean1.setShippername(etUserName.getText().toString());//货主名字
        uploadBean.setShippername(dataListBean1.getShippername());
        dataListBean1.setTeltphone(etUserPhone.getText().toString()); //联系电话
        uploadBean.setTeltphone(dataListBean1.getTeltphone());
        dataListBean1.setIdcardnum(etIdentityNumber.getText().toString()); //身份证号
        uploadBean.setIdcardnum(dataListBean1.getIdcardnum());
        dataListBean1.setFarmsnme(etFarmName.getText().toString()); //养殖场名字
        uploadBean.setFarmsnme(dataListBean1.getFarmsnme());
        dataListBean1.setQuarantineaddress(etQuarantineaddress.getText().toString()); //检疫地点
        uploadBean.setQuarantineaddress(dataListBean1.getQuarantineaddress());
        dataListBean1.setAnimalsort(etAnimType.getText().toString()); //动物种类
        uploadBean.setAnimalsort(dataListBean1.getAnimalsort());
        dataListBean1.setMyuse(etPurpose.getText().toString()); //用途
        uploadBean.setMyuse(dataListBean1.getMyuse());
        dataListBean1.setAnimalsources(etSource.getText().toString()); //动物来源
        uploadBean.setAnimalsources(dataListBean1.getAnimalsources());
        dataListBean1.setDomesticatedid(etDomesticatedid.getText().toString()); //繁殖许可证号
        uploadBean.setDomesticatedid(dataListBean1.getDomesticatedid());
        dataListBean1.setCatchid(etCatchid.getText().toString()); //捕捉许可证号
        uploadBean.setCatchid(dataListBean1.getCatchid());
        dataListBean1.setAnimalnum(uploadBean.getAnimalnum()); //数量
        uploadBean.setAnimalnum(dataListBean1.getAnimalnum());
        dataListBean1.setQDWDanWei(uploadBean.getQDWDanWei()); //单位
        uploadBean.setQDWDanWei(dataListBean1.getQDWDanWei());
        //起运地
        dataListBean1.setStartaddress(uploadBean.getStartaddress());
        uploadBean.setStartaddress(dataListBean1.getStartaddress());
        dataListBean1.setStartaddress1(uploadBean.getStartaddress1());
        dataListBean1.setStartaddress2(uploadBean.getStartaddress2());
        dataListBean1.setStartaddress3(uploadBean.getStartaddress3());
        dataListBean1.setStartaddress4(uploadBean.getStartaddress4());
        dataListBean1.setStartaddress5(uploadBean.getStartaddress5());
        dataListBean1.setRqydzxx(etStartPlace.getText().toString());
        uploadBean.setRqydzxx(dataListBean1.getRqydzxx());
        //到达地点
        dataListBean1.setRdddzxx(uploadBean.getRdddzxx());
        dataListBean1.setRdddzxx1(uploadBean.getRdddzxx1());
        dataListBean1.setRdddzxx2(uploadBean.getRdddzxx2());
        dataListBean1.setRdddzxx3(uploadBean.getRdddzxx3());
        dataListBean1.setRdddzxx4(uploadBean.getRdddzxx4());
        dataListBean1.setRdddzxx5(uploadBean.getRdddzxx5());
        dataListBean1.setRdddzxxs(etEndPlace.getText().toString());
        uploadBean.setRdddzxxs(dataListBean1.getRdddzxxs());
        dataListBean1.setToolid(etCarrierUtilNumber.getText().toString()); //运载工具牌号
        uploadBean.setToolid(dataListBean1.getToolid());
        dataListBean1.setSupervisename(etUserUnit.getText().toString()); //监督所名称
        uploadBean.setSupervisename(dataListBean1.getSupervisename());
        dataListBean1.setQzmy(rbForceYes.isChecked() ? "是" : "否");//是否强制免疫
        uploadBean.setQzmy(dataListBean1.getQzmy());
        dataListBean1.setRecordrule(rbKeepFileYes.isChecked() ? "符合" : "不符合");//养殖档案是否归档
        uploadBean.setRecordrule(dataListBean1.getRecordrule());
        dataListBean1.setLogorule(rbEpidemicConditionYes.isChecked() ? "符合" : "不符合");//// 畜禽标识是否符合规定   动物防疫条件合格证
        uploadBean.setLogorule(dataListBean1.getLogorule());
        dataListBean1.setOutbreak(rbEpidemicStatusYes.isChecked() ? "有" : "无");//养殖场疫情
        uploadBean.setOutbreak(dataListBean1.getOutbreak());
        dataListBean1.setEridemicarea(rbEpidemicAreaYes.isChecked() ? "是" : "否");//是否疫区
        uploadBean.setEridemicarea(dataListBean1.getEridemicarea());
        dataListBean1.setOther(etOtherCheck.getText().toString());  //其他
        uploadBean.setOther(dataListBean1.getOther());
        dataListBean1.setLaboratory(rbCheckLabYes.isChecked() ? "需要" : "不需要");//实验室检查
        uploadBean.setLaboratory(dataListBean1.getLaboratory());
        if (rbCheckLabYes.isChecked()) {
            if (rbCheckStatusYes.isChecked()) {
                dataListBean1.setSituation("合格");//检测情况
            } else if (rbCheckStatusNo.isChecked()) {
                dataListBean1.setSituation("不合格");//检测情况
            } else {
                dataListBean1.setSituation("");//检测情况
            }
        } else {
            dataListBean1.setSituation("");//检测情况
        }

        anniu = new ArrayList<>();
        if (cbJiance.isChecked()) {
            anniu.add(cbJiance.getText().toString());
        }

        if (cbGeli.isChecked()) {
            anniu.add(cbGeli.getText().toString());
        }

        if (cbZhiliao.isChecked()) {
            anniu.add(cbZhiliao.getText().toString());
        }

        if (cbQita.isChecked()) {
            anniu.add(cbQita.getText().toString());
        }
        StringBuffer sb1 = new StringBuffer();
        for (int i = 0; i < anniu.size(); i++) {
            if (i != anniu.size() - 1) {
                sb1.append(anniu.get(i).toString()).append(",");
            } else {
                sb1.append(anniu.get(i).toString());
            }
        }

        dataListBean1.setGeneral(sb1.toString());

        anniu2 = new ArrayList<>();
        if (cbFenshao.isChecked()) {
            anniu2.add(cbFenshao.getText().toString());
        }
        if (cbHuazhi.isChecked()) {
            anniu2.add(cbHuazhi.getText().toString());
        }
        if (cbYanmai.isChecked()) {
            anniu2.add(cbYanmai.getText().toString());
        }
        if (cbFajiao.isChecked()) {
            anniu2.add(cbFajiao.getText().toString());
        }
        if (cbWuhaiqita.isChecked()) {
            anniu2.add(cbWuhaiqita.getText().toString());
        }

        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < anniu2.size(); i++) {
            if (i != anniu2.size() - 1) {
                sb2.append(anniu2.get(i).toString()).append(",");
            } else {
                sb2.append(anniu2.get(i).toString());
            }
        }

        dataListBean1.setHarmless(sb2.toString());//无害化处理对象

        uploadBean.setSituation(dataListBean1.getSituation());


        dataListBean1.setVetname(uploadBean.getVetname()); //检疫人员签字
        uploadBean.setVetname(dataListBean1.getVetname());
        dataListBean1.setRhzqz(etSignagureChecked.getText().toString());//货主签字

        if (!rbClinicCheckYes.isChecked()) {
            dataListBean1.setCode("");
            dataListBean1.setJyclcard(etQuarantineNotificationNumber.getText().toString());//检疫处理通知单编号
            uploadBean.setJyclcard(dataListBean1.getJyclcard());
        } else {
            dataListBean1.setJyclcard("");
            dataListBean1.setCode(etQuarantineProveNumber.getText().toString());//《动物检疫合格证明》编号
            uploadBean.setCode(dataListBean1.getCode());
        }


        uploadBean.setRhzqz(dataListBean1.getRhzqz());
        dataListBean1.setClinical(rbClinicCheckYes.isChecked() ? "合格" : "不合格");//临床检查 合格不显示  不合格显示对象  一般处理 无害化处理   和数量
        uploadBean.setClinical(dataListBean1.getClinical());
        dataListBean1.setQuarantinerule(rbQuarantineRuleYes.isChecked() ? "是" : "否"); //是否符合检疫规定  在这里做判断
        uploadBean.setQuarantinerule(dataListBean1.getQuarantinerule());

        if (tv_object.toString().contains("其它对象")) {
            dataListBean1.setLegal("0");
            dataListBean1.setOtherObj("1");
        } else {
            dataListBean1.setOtherObj("0");
            dataListBean1.setLegal("1");
        }
        dataListBean1.setRunit(tv_object.getText().toString());//对象
//        dataListBean1.setRunit(spinnerObject.getSelectedItem().toString());//对象
        dataListBean1.setLegalnum(Integer.parseInt(etObjectNumber.getText().toString()));          //对象数量
        uploadBean.setLegalnum(dataListBean1.getLegalnum());

//        dataListBean1.setGeneral(spinnerGeneralProcess.getSelectedItem().toString());//一般处理对象
//        uploadBean.setGeneral(dataListBean1.getGeneral());
        dataListBean1.setHarmlesnums(Integer.parseInt(etGeneralProcess.getText().toString()));         //一般处理数量
        uploadBean.setHarmlesnums(dataListBean1.getHarmlesnums());
//        dataListBean1.setHarmless(spinnerHarmlessProcess.getSelectedItem().toString());//无害化处理对象
//        uploadBean.setHarmless(dataListBean1.getHarmless());
        dataListBean1.setHarmlesnum(Integer.parseInt(etHarmlessProcess.getText().toString()));           //无害化处理数量
        uploadBean.setHarmlesnum(dataListBean1.getHarmlesnum());

        //报检时间inspectiontime   dr 检疫时间quarantinetime dr1   检疫人员签字时间Gftime  dr2   货主签字时间rhzdate dr3
        dataListBean1.setInspectiontime(uploadBean.getInspectiontime());
        dataListBean1.setDr(uploadBean.getDr());
        dataListBean1.setQuarantinetime(jy_time[0] + "/" + jy_time[1] + "/" + jy_time[2]);
        dataListBean1.setDr1(jy_time[3]);
        dataListBean1.setGftime(bj_time[0] + "/" + bj_time[1] + "/" + bj_time[2]);
        dataListBean1.setDr2(bj_time[3]);
        dataListBean1.setRhzdate(bj_time1[0] + "/" + bj_time1[1] + "/" + bj_time1[2]);
        dataListBean1.setDr3(bj_time1[3]);
        //设置不符合检疫规定的
        if (!rbQuarantineRuleYes.isChecked()) {
//            if (spinnerObject.getSelectedItemPosition() == 0) {
//                uploadBean.setLegal("1");
//                uploadBean.setOtherObj("2");
//            } else {
//                uploadBean.setOtherObj("1");
//                uploadBean.setLegal("2");
//            }
            uploadBean.setLegalnum(Integer.valueOf(isEmpty(etObjectNumber.getText().toString()) ? "0" : etObjectNumber.getText().toString()));

            uploadBean.setGeneral(dataListBean1.getGeneral());
            uploadBean.setHarmless(dataListBean1.getHarmless());
//            uploadBean.setGeneral(spinnerGeneralProcess.getSelectedItem().toString());

//            uploadBean.setHarmless(spinnerHarmlessProcess.getSelectedItem().toString());
            uploadBean.setHarmlesnum(Integer.valueOf(isEmpty(etHarmlessProcess.getText().toString()) ? "0" : etHarmlessProcess.getText().toString()));
        } else {
            uploadBean.setGeneral("");
            uploadBean.setHarmless("");
        }
        dataListBean1.setId(transformeredd.getFStId());//关联id
        dataListBean1.setFSm1("已保存");
        return dataListBean1;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean check() {

        if (rbQuarantineRuleYes.isChecked() && etQuarantineProveNumber.getText().length() != 10) {
            Toast.makeText(this, "动物检疫合格证明编号必须是10位", Toast.LENGTH_SHORT).show();
            etQuarantineProveNumber.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etQuarantineProveNumber.requestFocus();
            return false;
        } else if (!rbQuarantineRuleYes.isChecked() && etQuarantineNotificationNumber.getText().length() != 12) {
            Toast.makeText(this, "动物处理通知单编号必须是12位", Toast.LENGTH_SHORT).show();
            etQuarantineNotificationNumber.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etQuarantineNotificationNumber.requestFocus();
            return false;
        } else if (tv_jy_time.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择检疫时间", Toast.LENGTH_SHORT).show();
            return false;
        }else if (tv_bj_time.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择检疫人员下的报检时间", Toast.LENGTH_SHORT).show();
            return false;
        }else if (tv_bj_time_1.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择货主签名下报检时间", Toast.LENGTH_SHORT).show();
            return false;
        }else if (tv_object.getText().toString().equals("请选择")&&!rbQuarantineRuleYes.isChecked()) {
            Toast.makeText(this, "请在检疫处理结果中选择对象", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void setTime(String time, long longTime) {
        time1 = time.substring(0, 11);  //年月日
        time2 = time.substring(11, 13);  //小时
//        tvTimeSignatureCheck.setText(time1 + " " + time2 + "时");
//        tvTimeSignatureChecked.setText(time1 + " " + time2 + "时");
//        tvShipmentTime.setText(time1 + " " + time2 + "时");
    }

    @Override
    public void upLoadSucceed() {
//        finish();
        openPrintDialog();
    }

    @Override
    public void openPrintDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View viewAddEmployee = layoutInflater.inflate(
                R.layout.tishi_activity, null);
        new AlertDialog.Builder(this)
                .setTitle("是否打印")
                .setCancelable(false)
                .setView(viewAddEmployee)
                .setPositiveButton("是", (dialog, which) -> {
                    Intent intent = new Intent(this, PrintAcitivty.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_AREA);
                    bundle.putString(Constance.START_ACTIVITY_PRINTID, printId);
                    bundle.putSerializable(Constance.START_ACTIVITY_DATA, dataListBean1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    setResult(RESULT_OK, intent1);
                    finish();

                })
                .setNegativeButton("否", (dialog, which) -> {
                    setResult(RESULT_OK, intent1);
                    finish();
                }).show();
    }

    @Override
    public void bianhao(ShenbaoJiluBianhaoBean.DataBean dataListBean) {
        String result = dataListBean.getResult();
        etBianhaoRecordNo.setText(result);//记录单编号
    }

    @Override
    public void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean) {
        String result = dataListBean.getResult();     //检疫处理通知单编号
        if (etQuarantineNotificationNumber.getText().toString().isEmpty()) {
            etQuarantineNotificationNumber.setText(result);
        }
    }

    @Override
    public void getToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private String printId;

    @Override
    public void getPrintId(String id) {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        this.printId = id + "";
    }

}
