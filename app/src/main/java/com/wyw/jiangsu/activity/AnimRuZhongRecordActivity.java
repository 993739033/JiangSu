package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordListBean;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimRuZhongRecordActivity;
import com.wyw.jiangsu.presenter.AnimRuZhongRecordActivityPresenter;
import com.wyw.jiangsu.runnable.RxBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.R.id.rb_clinic_check_yes;
import static com.wyw.jiangsu.R.id.rb_quarantine_rule_yes;
import static com.wyw.jiangsu.R.id.tv_bj_time;
import static com.wyw.jiangsu.R.id.tv_bj_time_1;
import static com.wyw.jiangsu.R.id.tv_jy_time;
import static com.wyw.jiangsu.R.id.tv_object;

/**
 * 乳用、种用动物检疫工作记录单
 */
public class AnimRuZhongRecordActivity extends MVPActivity<AnimRuZhongRecordActivityPresenter> implements IAnimRuZhongRecordActivity {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_unit)
    EditText etUserUnit;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.et_record_no)
    EditText etRecordNo;
    @BindView(R.id.et_carrier_util_number)
    EditText etCarrierUtilNumber;
    @BindView(R.id.et_identity_number)
    EditText etIdentityNumber;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.et_farm_name)
    EditText etFarmName;
    @BindView(R.id.et_quarantine_place)
    EditText etQuarantinePlace;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_address_type)
    EditText etAddressType;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.et_address_type2)
    EditText etAddressType2;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(tv_jy_time)
    TextView tvJyTime;
    @BindView(R.id.et_centification_unit)
    EditText etCentificationUnit;
    @BindView(R.id.et_centification_unit_no)
    EditText etCentificationUnitNo;
    @BindView(R.id.et_approval_unit)
    EditText etApprovalUnit;
    @BindView(R.id.et_approval_table_no)
    EditText etApprovalTableNo;
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
    @BindView(R.id.rb_condition_yes)
    RadioButton rbConditionYes;
    @BindView(R.id.rb_condition_no)
    RadioButton rbConditionNo;
    @BindView(R.id.rb_zxqsc_yes)
    RadioButton rbZxqscYes;
    @BindView(R.id.rb_zxqsc_no)
    RadioButton rbZxqscNo;
    @BindView(R.id.rb_flag_yes)
    RadioButton rbFlagYes;
    @BindView(R.id.rb_flag_no)
    RadioButton rbFlagNo;
    @BindView(R.id.rb_force_yes)
    RadioButton rbForceYes;
    @BindView(R.id.rb_force_no)
    RadioButton rbForceNo;
    @BindView(R.id.et_other_check)
    EditText etOtherCheck;
    @BindView(rb_clinic_check_yes)
    RadioButton rbClinicCheckYes;
    @BindView(R.id.rb_clinic_check_no)
    RadioButton rbClinicCheckNo;
    @BindView(R.id.rg_clinic_check_yes)
    RadioGroup rgClinicCheck;
    @BindView(R.id.rb_check_status_yes)
    RadioButton rbCheckStatusYes;
    @BindView(R.id.rb_check_status_no)
    RadioButton rbCheckStatusNo;
    @BindView(rb_quarantine_rule_yes)
    RadioButton rbQuarantineRuleYes;
    @BindView(R.id.rb_quarantine_rule_no)
    RadioButton rbQuarantineRuleNo;
    @BindView(R.id.rg_check_jianyi)
    RadioGroup rgCheckJianyi;
    @BindView(R.id.et_quarantine_prove_number)
    EditText etQuarantineProveNumber;
    @BindView(R.id.lin_quarantine_rule_yes)
    LinearLayout linQuarantineRuleYes;
    @BindView(tv_object)
    TextView tvObject;
    @BindView(R.id.et_object_number)
    EditText etObjectNumber;
    @BindView(R.id.et_general_process)
    EditText etGeneralProcess;
    @BindView(R.id.cb_jiance)
    CheckBox cbJiance;
    @BindView(R.id.cb_geli)
    CheckBox cbGeli;
    @BindView(R.id.cb_zhiliao)
    CheckBox cbZhiliao;
    @BindView(R.id.cb_qita)
    CheckBox cbQita;
    @BindView(R.id.et_harmless_process)
    EditText etHarmlessProcess;
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
    @BindView(R.id.et_quarantine_notification_number)
    EditText etQuarantineNotificationNumber;
    @BindView(R.id.lin_quarantine_rule_no)
    LinearLayout linQuarantineRuleNo;
    @BindView(R.id.et_signagure_check)
    EditText etSignagureCheck;
    @BindView(tv_bj_time)
    TextView tvBjTime;
    @BindView(R.id.et_signagure_checked)
    EditText etSignagureChecked;
    @BindView(tv_bj_time_1)
    TextView tvBjTime1;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.SV)
    ScrollView scrollV;
    private AnimRuZhongYongRecordListBean.DataListBean dataListBean;
    private AnimRuZhongYongRecordBean.DataListBean uploadBean;          //上传的实体类

    AnimRuZhongYongRecordListBean.DataListBean transformeredd;
    private Intent intent1;

    private List<String> anniu;
    private List<String> anniu2;

    String[] jy_time = new String[4];
    String[] bj_time = new String[4];
    String[] bj_time1 = new String[4];

    private TimeSelectDialog jyDialog;//检疫时间选择
    private TimeSelectDialog bjDialog;//报检时间选择
    private TimeSelectDialog bjDialog1;//报检时间1选择
    private DataSelectDialog objectDialog;//对象选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_ru_zhong_record);
        ButterKnife.bind(this);
        scrollV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected AnimRuZhongRecordActivityPresenter createPresenter() {
        return new AnimRuZhongRecordActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("乳用、种用动物工作记录单");
        getPresenter().getBianhao();
        getPresenter().getJiludanhao();
        setTime();
        intent1 = getIntent();
        transformeredd = (AnimRuZhongYongRecordListBean.DataListBean) intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        //转换
        uploadBean = transformer(transformeredd);
        etApprovalTableNo.setText(uploadBean.getSpbhb());//审批表编号
        etUserName.setText(uploadBean.getShippername());//货主姓名
        etUserUnit.setText(uploadBean.getSupervisename());//动物卫生监督所名称
        etUserPhone.setText(uploadBean.getTeltphone());//联系电话
        etAnimType.setText(uploadBean.getAnimalsort());//动物种类
//        etRecordNo.setText(uploadBean.getRecordNo());  //记录单编号
        etAnimCount.setText(uploadBean.getAnimalnum());//数量
        tvDanwei.setText(uploadBean.getQDWDanWei());//单位
        etFarmName.setText(uploadBean.getFarmsnme());//养殖场名称
        etQuarantinePlace.setText(uploadBean.getQuarantineaddress());//检疫地点
        //etStartType.setText(dataListBean.getStartaddress5());
        etStartPlace.setText(uploadBean.getStartaddress() + uploadBean.getStartaddress1()
                + uploadBean.getStartaddress2() + uploadBean.getStartaddress3() +
                uploadBean.getStartaddress4());//起运地
        etAddressType.setText(uploadBean.getStartaddress5());
        etEndPlace.setText(uploadBean.getRdddzxx() + uploadBean.getRdddzxx1()
                + uploadBean.getRdddzxx2() + uploadBean.getRdddzxx3() + uploadBean.getRdddzxx4());//到达地
        etAddressType2.setText(uploadBean.getRdddzxx5());
        tvInspectionTime.setText(uploadBean.getInspectiontime() + " " + uploadBean.getDr() + "时");//报检时间
        etSignagureChecked.setText(uploadBean.getShippername());
        etSignagureCheck.setText(getPresenter().getFuName());
    }

    private AnimRuZhongYongRecordBean.DataListBean transformer(AnimRuZhongYongRecordListBean.DataListBean transformered) {
        AnimRuZhongYongRecordBean.DataListBean bean = new AnimRuZhongYongRecordBean.DataListBean();

//        bean.setZxqsc(transformered.getFqZxqscjyxkz());//有效《种畜禽生产经营许可证》
        bean.setZxqsc(rbConditionYes.isChecked() ? "有" : "无");//有效《种畜禽生产经营许可证》
//        bean.setDwtjhgz(transformered.);            //《动物防疫条件合格证》
        bean.setQDWNumber(transformered.getQDWNumber());//记录单编号
        bean.setShippername(transformered.getQDWCargoOwner());//货主姓名
        bean.setSupervisename(transformered.getFSenterpriseName());//动物卫生监督所名称
        bean.setTeltphone(transformered.getQDWPhone());////联系电话
        bean.setInspectiontime(transformered.getDateQF());//报检时间  年月日
        bean.setDr(transformered.getDr());//报检时间  小时
        bean.setFarmsnme(transformered.getYzcmc());////养殖场名称
        bean.setQuarantineaddress(transformered.getQDWAddress());//检疫地点
        bean.setAnimalsort(transformered.getQDWXuZhong());//动物种类
        bean.setAnimalnum(transformered.getQDWQuantity() + "");//数量
        bean.setQDWDanWei(transformered.getQDWDanWei());//单位
        bean.setQuarantinetime(transformered.getDateNpy());//检疫时间  年月日

        //起运地
        bean.setStartaddress(transformered.getQDWShengQy());
        bean.setStartaddress1(transformered.getQDWShiQy());
        bean.setStartaddress2(transformered.getQDWXianQy());
        bean.setStartaddress3(transformered.getQDWXiangQy());
        bean.setStartaddress4(transformered.getQDWCunQy());
        bean.setStartaddress5(transformered.getQDWTypeQy());

        //到达地
        bean.setRdddzxx(transformered.getQDWShengDd());
        bean.setRdddzxx1(transformered.getQDWShiDd());
        bean.setRdddzxx2(transformered.getQDWXianDd());
        bean.setRdddzxx3(transformered.getQDWXiangDd());
        bean.setRdddzxx4(transformered.getQDWCunDd());
        bean.setRdddzxx5(transformered.getQDWTypeDd());

        return bean;
    }


    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        jyDialog = new TimeSelectDialog(this, "选择检疫时间");
        jyDialog.initTime();
        jyDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                jy_time[0] = year;
                jy_time[1] = month;
                jy_time[2] = day;
                jy_time[3] = hour;
                tvJyTime.setText(year + "-" + month + "-" + day + " " + hour + "时");
            }
        });

        bjDialog = new TimeSelectDialog(this, "请选择报检时间");
        bjDialog.initTime();
        bjDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                bj_time[0] = year;
                bj_time[1] = month;
                bj_time[2] = day;
                bj_time[3] = hour;
                tvBjTime.setText(year + "-" + month + "-" + day + " " + hour + "时");
            }
        });

        bjDialog1 = new TimeSelectDialog(this, "请选择报检时间");
        bjDialog1.initTime();
        bjDialog1.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                bj_time1[0] = year;
                bj_time1[1] = month;
                bj_time1[2] = day;
                bj_time1[3] = hour;
                tvBjTime1.setText(year + "-" + month + "-" + day + " " + hour + "时");
            }
        });

        objectDialog = new DataSelectDialog(this);
        objectDialog.setTitle("对象选择");
        objectDialog.setDatas(getResources().getStringArray(R.array.quarantine_objects));
        objectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                tvObject.setText(data);
            }

            @Override
            public void cancel() {

            }
        });
    }

    boolean lock = false;

    @Override
    public void bindListener() {
        tvJyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jyDialog.show();
            }
        });

        tvBjTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bjDialog.show();
            }
        });

        tvBjTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bjDialog1.show();
            }
        });

        tvObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUserUnit.clearFocus();
                etUserUnit.clearFocus();
                etCarrierUtilNumber.clearFocus();
                etIdentityNumber.clearFocus();
                etAnimCount.clearFocus();
                etFarmName.clearFocus();
                etCentificationUnit.clearFocus();
                etCentificationUnitNo.clearFocus();
                etApprovalUnit.clearFocus();
                etApprovalTableNo.clearFocus();
                etOtherCheck.clearFocus();
                objectDialog.show();
            }
        });

        rgClinicCheck.setOnCheckedChangeListener((group, checkedId) -> {
            if (lock) return;
            lock = true;
            if (checkedId == rb_clinic_check_yes) {
                linQuarantineRuleYes.setVisibility(View.VISIBLE);
                linQuarantineRuleNo.setVisibility(View.GONE);
                rgCheckJianyi.check(rb_quarantine_rule_yes);
            } else {
                linQuarantineRuleYes.setVisibility(View.GONE);
                linQuarantineRuleNo.setVisibility(View.VISIBLE);
                rgCheckJianyi.check(R.id.rb_quarantine_rule_no);
            }
            lock = false;
        });

        rgCheckJianyi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (lock) return;
                lock = true;
                if (checkedId == rb_quarantine_rule_yes) {
                    linQuarantineRuleYes.setVisibility(View.VISIBLE);
                    linQuarantineRuleNo.setVisibility(View.GONE);
                    rgClinicCheck.check(rb_clinic_check_yes);
                } else {
                    linQuarantineRuleYes.setVisibility(View.GONE);
                    linQuarantineRuleNo.setVisibility(View.VISIBLE);
                    rgClinicCheck.check(R.id.rb_clinic_check_no);
                }
                lock = false;
            }
        });

        btUpload.setOnClickListener(v -> {

            if (check()) {
                showNormalDialog();
            }
        });
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AnimRuZhongRecordActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getData());
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

    public AnimRuZhongYongRecordBean.DataListBean getData() {
        uploadBean.setToolid(TextUtils.isEmpty(etCarrierUtilNumber.getText().toString()) ? "0" : etCarrierUtilNumber.getText().toString());
        uploadBean.setRecordNo(etRecordNo.getText().toString());//记录单编号
        uploadBean.setToolid(etCarrierUtilNumber.getText().toString());//运载工具号不能为空
        uploadBean.setIdcardnum(etIdentityNumber.getText().toString());//身份证号不能为空
        uploadBean.setFzdw(etCentificationUnit.getText().toString());//发证单位不能为空
        uploadBean.setBhid(etCentificationUnitNo.getText().toString());//发证单位号编号不能为空
        uploadBean.setSpdw(etApprovalUnit.getText().toString());//审批单位不能为空
        uploadBean.setSpbhb(etApprovalTableNo.getText().toString());//审批单位编号
        uploadBean.setOther(etOtherCheck.getText().toString());//其他项目检查不能为空

        uploadBean.setSupervisename(etUserUnit.getText().toString());//监督所名称
//        uploadBean.setAnimalnum(Integer.parseInt(etAnimCount.getText().toString()));//数量
        uploadBean.setAnimalnum(etAnimCount.getText().toString());//数量
        uploadBean.setQDWDanWei(tvDanwei.getText().toString());//单位


        uploadBean.setVetname(etSignagureCheck.getText().toString());//检疫人员签字不能为空
        uploadBean.setRhzqz(etSignagureChecked.getText().toString());//货主签字不能为空

        uploadBean.setEridemicarea(rbEpidemicAreaYes.isChecked() ? "是" : "否");//是否是疫区
        uploadBean.setRecordrule(rbKeepFileYes.isChecked() ? "符合" : "不符合");//养殖档案是否归档
        uploadBean.setOutbreak(rbEpidemicStatusYes.isChecked() ? "有" : "无");//养殖场疫情(6个月内)
        uploadBean.setQzmy(rbForceYes.isChecked() ? "是" : "否");//是否经强制免疫并在有效期内
        uploadBean.setClinical(rbClinicCheckYes.isChecked() ? "合格" : "不合格");//临床检查
        uploadBean.setSituation(rbCheckStatusYes.isChecked() ? "合格" : "不合格");//法定检疫对象实验室检测情况
        uploadBean.setQuarantinerule(rbQuarantineRuleYes.isChecked() ? "是" : "否");//是否符合检疫规定
        uploadBean.setLogorule(rbFlagYes.isChecked() ? "是" : "否");//耳标佩戴是否符合规定
        uploadBean.setDwtjhgz(rbConditionYes.isChecked() ? "有" : "无");//《动物防疫条件合格证》


        if (!rbClinicCheckYes.isChecked()) {
            uploadBean.setCode("");
            uploadBean.setJyclcard(etQuarantineNotificationNumber.getText().toString());//检疫处理通知单
        } else {
            uploadBean.setJyclcard("");
            uploadBean.setCode(etQuarantineProveNumber.getText().toString());//动物检疫合格证明
        }

//        uploadBean.setRqydzxx(etStartPlace.getText().toString() + etStartVillage.getText().toString() + etStartOther.getText().toString());//启用地址详细
        uploadBean.setRqydzxx(etStartPlace.getText().toString());//启用地址详细
//        uploadBean.setRdddzxxs(etEndPlace.getText().toString() + etEndVillage.getText().toString() + etEndOther.getText().toString());//到达地址详细
        uploadBean.setRdddzxxs(etEndPlace.getText().toString());//到达地址详细

        uploadBean.setStartaddress(uploadBean.getStartaddress());
        uploadBean.setStartaddress1(uploadBean.getStartaddress1());
        uploadBean.setStartaddress2(uploadBean.getStartaddress2());
        uploadBean.setStartaddress3(uploadBean.getStartaddress3());
        uploadBean.setStartaddress4(uploadBean.getStartaddress4());
        uploadBean.setStartaddress5(uploadBean.getStartaddress5());

        uploadBean.setRdddzxx(uploadBean.getRdddzxx());
        uploadBean.setRdddzxx1(uploadBean.getRdddzxx1());
        uploadBean.setRdddzxx2(uploadBean.getRdddzxx2());
        uploadBean.setRdddzxx3(uploadBean.getRdddzxx3());
        uploadBean.setRdddzxx4(uploadBean.getRdddzxx4());
        uploadBean.setRdddzxx5(uploadBean.getRdddzxx5());

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

        //设置不符合检疫规定的
        if (!rbQuarantineRuleYes.isChecked()) {
            if (tvObject.getText().toString().contains("法定检疫对象")) {
                uploadBean.setLegal("1");
                uploadBean.setOtherObj("2");
            } else {
                uploadBean.setOtherObj("1");
                uploadBean.setLegal("2");
            }
            uploadBean.setGeneral(sb1.toString());//一般处理对象
            uploadBean.setHarmless(sb2.toString());//无害化处理对象
            uploadBean.setHarmlesnum(Integer.valueOf(isEmpty(etHarmlessProcess.getText().toString()) ? "0" : etHarmlessProcess.getText().toString()));
        }

        if (tvObject.getText().toString().contains("其它对象")) {
            uploadBean.setLegal("0");
            uploadBean.setOtherObj("1");
            uploadBean.setOtherNum(Integer.parseInt(etObjectNumber.getText().toString()));
        } else {
            uploadBean.setOtherObj("0");
            uploadBean.setLegal("1");
            uploadBean.setLegalnum(Integer.parseInt(etObjectNumber.getText().toString()));
        }

        uploadBean.setMyuse(transformeredd.getQDWYongTu());//用途
        uploadBean.setRunit(tvObject.getText().toString());//对象对象
        uploadBean.setHarmlesnums(Integer.parseInt(etGeneralProcess.getText().toString()));//一般处理对象数量
        uploadBean.setHarmlesnum(Integer.parseInt(etHarmlessProcess.getText().toString()));///无害化处理对象数量

        //检疫时间
        uploadBean.setQuarantinetime(jy_time[0] + "/" + jy_time[1] + "/" + jy_time[2]);
        uploadBean.setDr1(jy_time[3]);
        //检疫人员签字时间
        uploadBean.setGftime(bj_time[0] + "/" + bj_time[1] + "/" + bj_time[2]);
        uploadBean.setDr2(bj_time[3]);
        //货主签字时间
        uploadBean.setRhzdate(bj_time1[0] + "/" + bj_time1[1] + "/" + bj_time1[2]);
        uploadBean.setDr3(bj_time1[3]);

        uploadBean.setFSm1("已保存");
        uploadBean.setId(transformeredd.getFStId());//关联id
        return uploadBean;
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
        }

        if (isEmpty(getString(etFarmName))) {
            showToast("养殖场名称不能为空");
            etFarmName.requestFocus();
            return false;
        }
        if (isEmpty(getString(etSignagureChecked))) {
            showToast("货主签字不能为空");
            etSignagureChecked.requestFocus();
            return false;
        }
        if (isEmpty(getString(etUserUnit))) {
            showToast("动物卫生监督所名称不能为空");
            etUserUnit.requestFocus();
            return false;
        }
        if (isEmpty(getString(etQuarantineProveNumber)) && rbClinicCheckYes.isChecked()) {
            showToast("《动物检疫合格证明》编号不能为空");
            etQuarantineProveNumber.performClick();
            return false;
        }

        if (tvJyTime.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择检疫时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tvBjTime.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择检疫人员下的报检时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tvBjTime1.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择货主签名下报检时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void uploadsuccess() {
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
                    bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_RUZHONG);
                    bundle.putString(Constance.START_ACTIVITY_PRINTID, printId);
                    bundle.putSerializable(Constance.START_ACTIVITY_DATA, uploadBean);
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
        etRecordNo.setText(result);
    }

    @Override
    public void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean) {
        String result = dataListBean.getResult();     //检疫处理通知单编号
        if (etQuarantineNotificationNumber.getText().toString().isEmpty()) {
            etQuarantineNotificationNumber.setText(result);
        }
    }

    private String printId;

    @Override
    public void getPrintid(String id) {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        this.printId = id + "";
    }
}
