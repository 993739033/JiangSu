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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimZhongDanRecordActivity;
import com.wyw.jiangsu.presenter.AnimZhongDanRecordActivityPresenter;
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
 * 种蛋、胚胎、精液检疫工作记录单
 */
public class AnimZhongDanRecordActivity extends MVPActivity<AnimZhongDanRecordActivityPresenter>
        implements IAnimZhongDanRecordActivity {


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
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.et_apply_type)
    EditText etApplyType;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.tv_dangwei)
    TextView tvDangwei;
    @BindView(R.id.et_farm_name)
    EditText etFarmName;
    @BindView(R.id.et_quarantine_place)
    EditText etQuarantinePlace;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_qiyundi_xiangqing)
    EditText etQiyundiXiangqing;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
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
    TextView etOtherCheck;
    @BindView(R.id.rb_clinic_check_yes)
    RadioButton rbClinicCheckYes;
    @BindView(R.id.rb_clinic_check_no)
    RadioButton rbClinicCheckNo;
    @BindView(R.id.rg_clinic_check_yes)
    RadioGroup rgClinicCheckYes;
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
    LinearLayout linQuarantineRuleYes;
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
    @BindView(R.id.SV)
    ScrollView SV;
    @BindView(R.id.tv_jy_time)
    TextView tv_jy_time;
    @BindView(R.id.tv_bj_time)
    TextView tv_bj_time;
    @BindView(R.id.tv_bj_time_1)
    TextView tv_bj_time_1;
    @BindView(R.id.tv_object)
    TextView tv_object;

    private AnimZhongDanRedordBean.DataListBean dataListBean;//上传的类
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
        setContentView(R.layout.activity_anim_zhong_dan_record);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected AnimZhongDanRecordActivityPresenter createPresenter() {
        return new AnimZhongDanRecordActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("种蛋、胚胎、精液检疫工作记录单");
        getPresenter().getBianhao();
        getPresenter().getJiludanhao();
        setTime();
        intent1 = getIntent();
        AnimZhongDanRedordBeangai.DataListBean transformered = (AnimZhongDanRedordBeangai.DataListBean)
                intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        etIdentityNumber.setText(transformered.getFSm1());          //默认带过来身份证号
        //转换
        dataListBean = transformer(transformered);
        etUserUnit.setText(getPresenter().getUser().getFUSEENAME());//动物卫生所名称
        etSignagureCheck.setText(getPresenter().getFuName()); //检疫人员签字
        etSignagureChecked.setText(dataListBean.getRhzqz());//货主签字
        etUserName.setText(dataListBean.getShippername());//货主姓名
        etUserPhone.setText(dataListBean.getTeltphone());//联系电话
        etAnimType.setText(dataListBean.getOnimalsort());//动物产品种类
        etApplyType.setText(dataListBean.getAnimalsort());//动物供体种类
        etAnimCount.setText(dataListBean.getAnimalnum());//数量
        tvDangwei.setText(dataListBean.getQCPDanWei());//单位
        etQuarantinePlace.setText(dataListBean.getQuarantineaddress());//检疫地点
//        etQuarantinePlace.setText("检疫地点");//检疫地点

        etStartPlace.setText(dataListBean.getStartaddress() + dataListBean.getStartaddress1() +
                dataListBean.getStartaddress2() + dataListBean.getStartaddress3());//起运地省市

        etEndPlace.setText(dataListBean.getRdddzxx() + dataListBean.getRdddzxx1() +
                dataListBean.getRdddzxx2() + dataListBean.getRdddzxx3());//到达地省市
        tvInspectionTime.setText(dataListBean.getInspectiontime() + " " +
                dataListBean.getDr() + "时");//报检时间
        tvErbiao.setText(dataListBean.getOnimalsortName());//耳标号
    }

    private AnimZhongDanRedordBean.DataListBean transformer(AnimZhongDanRedordBeangai.DataListBean
                                                                    transformered) {
        AnimZhongDanRedordBean.DataListBean uploadBean = new AnimZhongDanRedordBean.DataListBean();
        uploadBean.setQDWNumber(transformered.getQDWNumber());//记录单编号
        uploadBean.setId(transformered.getFStId());//ID
        uploadBean.setShippername(transformered.getQDWCargoOwner());//货主姓名
        uploadBean.setRhzqz(transformered.getQDWCargoOwner());//申报人
        uploadBean.setTeltphone(transformered.getQDWPhone());//联系电话
        uploadBean.setOnimalsort(transformered.getFqProduct());//动物产品种类
        uploadBean.setAnimalsort(transformered.getQDWXuZhong());   //动物供体种类
        uploadBean.setAnimalnum(transformered.getQDWQuantity() + "");//数量
        uploadBean.setQCPDanWei(transformered.getQDWDanWei());//单位
        uploadBean.setQuarantineaddress(transformered.getQDWAddress());//检疫地点
        uploadBean.setStartaddress(transformered.getQDWShengQy());//起运地省
        uploadBean.setStartaddress1(transformered.getQDWShiQy());//起运地市
        uploadBean.setStartaddress2(transformered.getQDWXianQy());//起运地县
        uploadBean.setStartaddress3(transformered.getQDWXiangQy());//起运地乡镇
        uploadBean.setRdddzxx(transformered.getQDWShengDd());//到达地省
        uploadBean.setRdddzxx1(transformered.getQDWShiDd());//到达地市
        uploadBean.setRdddzxx2(transformered.getQDWXianDd());//到达地县
        uploadBean.setRdddzxx3(transformered.getQDWXiangDd());//到达地乡镇
        uploadBean.setInspectiontime(transformered.getDateQF());//报检时间年月日
        uploadBean.setDr(transformered.getDr());//报检时间小时
        uploadBean.setQuarantinetime(transformered.getDateNpy());//检疫时间年月日
        // uploadBean.setDr1(transformered.getDr());//检疫时间小时
        uploadBean.setOnimalsortName(transformered.getQDWErBiaoHao());
        return uploadBean;
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
                tv_jy_time.setText(year + "-" + month + "-" + day + " " + hour + "时");
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
                tv_bj_time.setText(year + "-" + month + "-" + day + " " + hour + "时");
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
                tv_bj_time_1.setText(year + "-" + month + "-" + day + " " + hour + "时");
            }
        });

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

    boolean lock = false;

    @Override
    public void bindListener() {
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
                etUserUnit.clearFocus();
                etUserUnit.clearFocus();
                etCarrierUtilNumber.clearFocus();
                etIdentityNumber.clearFocus();
                etAnimCount.clearFocus();
                etFarmName.clearFocus();
                etQiyundiXiangqing.clearFocus();
                etCentificationUnit.clearFocus();
                etCentificationUnitNo.clearFocus();
                etApprovalUnit.clearFocus();
                etApprovalTableNo.clearFocus();
                etOtherCheck.clearFocus();
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

                if (check()) {
                    showNormalDialog();
                }
            }
        });
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AnimZhongDanRecordActivity.this);
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

    private AnimZhongDanRedordBean.DataListBean getData() {
        dataListBean.setSupervisename(etUserUnit.getText().toString());//动物卫生监督所名称
        dataListBean.setToolid(etCarrierUtilNumber.getText().toString());//运载工具号不能为空
        dataListBean.setAnimalnum(etAnimCount.getText().toString());//数量
        dataListBean.setIdcardnum(etIdentityNumber.getText().toString());//身份证号不能为空
        dataListBean.setFzdw(etCentificationUnit.getText().toString());//发证单位不能为空
        dataListBean.setBhid(etCentificationUnitNo.getText().toString());//发证单位号编号不能为空
        dataListBean.setSpdw(etApprovalUnit.getText().toString());//审批单位不能为空
        dataListBean.setSpbhb(etApprovalTableNo.getText().toString());//审批单位编号不能为空
        dataListBean.setOther(etOtherCheck.getText().toString());//其他项目检查不能为空
        dataListBean.setVetname(etSignagureCheck.getText().toString());//检疫人员签字不能为空
        dataListBean.setRhzqz(etSignagureChecked.getText().toString());//货主签字不能为空
        dataListBean.setFarmsnme(etFarmName.getText().toString());//养殖场名称
        dataListBean.setId(dataListBean.getId());//ID
        dataListBean.setRqydzxx(etStartPlace.getText().toString());//起运地详情
        dataListBean.setRdddzxxs(etEndPlace.getText().toString());//目的地详情
        dataListBean.setSCDWMC(etQiyundiXiangqing.getText().toString());//生产单位名称
        dataListBean.setEridemicarea(rbEpidemicAreaYes.isChecked() ? "是" : "否");//是否是疫区
        dataListBean.setRecordrule(rbKeepFileYes.isChecked() ? "符合" : "不符合");//养殖档案是否归档
        dataListBean.setOutbreak(rbEpidemicStatusYes.isChecked() ? "有" : "无");//养殖场疫情(6个月内)
        dataListBean.setZxqsc(rbZxqscYes.isChecked() ? "有" : "无");//种畜禽生产经营许可证
        dataListBean.setDwtjhgz(rbConditionYes.isChecked() ? "有" : "无");//动物防疫条件合格证
        dataListBean.setLogorule(rbFlagYes.isChecked() ? "符合" : "不符合");//禽畜标示
        dataListBean.setQzmy(rbForceYes.isChecked() ? "是" : " 否");//是否经强制免疫并在有效期内
        dataListBean.setClinical(rbClinicCheckYes.isChecked() ? "合格" : "不合格");//临床检查
        dataListBean.setSituation(rbCheckStatusYes.isChecked() ? "合格" : "不合格");//法定检疫对象实验室检测情况
        dataListBean.setQuarantinerule(rbQuarantineRuleYes.isChecked() ? "是" : "否");//是否符合检疫规定

        if (tv_object.getText().toString().equals("其它对象")) {
            dataListBean.setLegal("0");
            dataListBean.setOtherObj("1");
        } else {
            dataListBean.setOtherObj("0");
            dataListBean.setLegal("1");
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

        if (!rbQuarantineRuleYes.isChecked()) {

            dataListBean.setGeneral(sb1.toString());//一般处理对象
            dataListBean.setHarmless(sb2.toString());//无害化处理对象
        } else {
            dataListBean.setHarmless("");//无害化处理对象
            dataListBean.setGeneral("");//一般处理对象
        }


        dataListBean.setRunit(tv_object.getText().toString());//对象
        dataListBean.setRecordNo(etRecordNo.getText().toString()); //记录单编号
        dataListBean.setOtherNum(Integer.parseInt(etObjectNumber.getText().toString()));
        dataListBean.setLegalnum(Integer.parseInt(etObjectNumber.getText().toString()));
//        dataListBean.setGeneral(spinnerGeneralProcess.getSelectedItem().toString());//一般处理对象
        dataListBean.setHarmlesnums(Integer.parseInt(etGeneralProcess.getText().toString()));//一般处理对象数量
//        dataListBean.setHarmless(spinnerHarmlessProcess.getSelectedItem().toString());//无害化处理对象
        dataListBean.setHarmlesnum(Integer.parseInt(etHarmlessProcess.getText().toString()));///无害化处理对象数量
        if (!rbClinicCheckYes.isChecked()) {
            dataListBean.setCode("");
            dataListBean.setJyclcard(etQuarantineNotificationNumber.getText().toString());//检疫处理通知单
        } else {
            dataListBean.setJyclcard("");
            dataListBean.setCode(etQuarantineProveNumber.getText().toString());//动物检疫合格证明
        }

        //检疫时间
        dataListBean.setQuarantinetime(jy_time[0] + "/" + jy_time[1] + "/" + jy_time[2]);
        dataListBean.setDr1(jy_time[3]);
        //检疫人员签字时间
        dataListBean.setGftime(bj_time[0] + "/" + bj_time[1] + "/" + bj_time[2]);
        dataListBean.setDr2(bj_time[3]);
        //货主签字时间
        dataListBean.setRhzdate(bj_time1[0] + "/" + bj_time1[1] + "/" + bj_time1[2]);
        dataListBean.setDr3(bj_time1[3]);

        dataListBean.setFSm1("已保存");
        return dataListBean;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean check() {

        if (isEmpty(getString(etSignagureChecked))) {
            showToast("货主签字不能为空");
            etSignagureChecked.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.
                    getColor(getBaseContext(), R.color.bule1)));
            etSignagureChecked.requestFocus();
            return false;
        }
        if (isEmpty(getString(etUserUnit))) {
            showToast("动物卫生监督所名称不能为空");
            etUserUnit.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.
                    getColor(getBaseContext(), R.color.bule1)));
            etUserUnit.requestFocus();
            return false;
        }

        if (rbClinicCheckYes.isChecked()) {
            if (etQuarantineProveNumber.getText().length() != 10) {
                Toast.makeText(this, "动物检疫合格证明编号必须是10位", Toast.LENGTH_SHORT).show();
                etQuarantineProveNumber.setBackgroundTintList(ColorStateList.
                        valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
                etQuarantineProveNumber.requestFocus();
                return false;
            }
        } else {
            if (etQuarantineNotificationNumber.getText().length() != 12 && rbClinicCheckYes.isChecked()) {
                Toast.makeText(this, "动物处理通知单编号必须是12位", Toast.LENGTH_SHORT).show();
                etQuarantineNotificationNumber.setBackgroundTintList(ColorStateList.valueOf
                        (ContextCompat.getColor(getBaseContext(), R.color.bule1)));
                etQuarantineNotificationNumber.requestFocus();
                return false;
            }
        }

        if (tv_jy_time.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择检疫时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tv_bj_time.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择检疫人员下的报检时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tv_bj_time_1.getText().toString().equals("请选择")) {
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
                    bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_ZHONGDAN);
                    bundle.putString(Constance.START_ACTIVITY_PRINTID, printId);
                    bundle.putSerializable(Constance.START_ACTIVITY_DATA, dataListBean);
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
        etRecordNo.setText(dataListBean.getResult());//记录单编号
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
    public void getPrindId(String id) {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        this.printId = id + "";
    }
}
