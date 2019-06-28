package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.bean.AnimAProcessUserDetilBean;
import com.wyw.jiangsu.bean.AnimAlistBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.db.CodeXD;
import com.wyw.jiangsu.db.LocalGreenDao;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.ErBiaoDialog;
import com.wyw.jiangsu.dialog.LimitTimeSelectDialog;
import com.wyw.jiangsu.dialog.ListViewDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimAActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.AnimAActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.R.id.et_steriallier_method;

/**
 * 动物检疫证明A 详情
 */
public class AnimAActivity extends MVPActivity<AnimAActivityPresenter> implements IAnimAActivity, ITime {
    //sadsadsasd
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
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
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
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    //    @BindView(R.id.et_start_village)
//    EditText etStartVillage;
//    @BindView(R.id.et_start_other)
//    EditText etStartOther;
    @BindView(R.id.et_start_address)
    EditText etStartAddress;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    //    @BindView(R.id.et_end_village)
//    EditText etEndVillage;
//    @BindView(R.id.et_end_other)
//    EditText etEndOther;
    @BindView(R.id.et_end_address)
    EditText etEndAddress;
    @BindView(R.id.et_carrier_name)
    EditText etCarrierName;
    @BindView(R.id.et_carrier_phone)
    EditText etCarrierPhone;
    @BindView(R.id.et_carrier_util_number)
    EditText etCarrierUtilNumber;
    @BindView(et_steriallier_method)
    EditText etSteriallierMethod;
    @BindView(R.id.bt_steriallier_method)
    Button btSteriallierMethod;
    @BindView(R.id.rb_railway)
    RadioButton rbRailway;
    @BindView(R.id.rb_highway)
    RadioButton rbHighway;
    @BindView(R.id.rb_waveway)
    RadioButton rbWaveway;
    @BindView(R.id.rb_aviation)
    RadioButton rbAviation;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.bt_erbiao)
    Button btErbiao;
    //    @BindView(R.id.spinner)
//    Spinner spinner;
    @BindView(R.id.spinner)
    TextView tv_spinner;
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
    @BindView(R.id.rg_accept_chekt)
    RadioGroup rg_accept_chekt;
    private AnimAlistBean.DataListBean uploadBean;
    private TimePresenter timePresenter;
    private ListViewDialog listViewDialog;
    ErBiaoDialog dialog;

    AnimAProcessListBean.DataListBean dateBeans;
    private String printId;
    private Intent intent1;

    LimitTimeSelectDialog ltDialog;//日期选择
    DataSelectDialog dsDialog;//消毒方式选择


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_a);
        ButterKnife.bind(this);
    }

    @Override
    protected AnimAActivityPresenter createPresenter() {
        return new AnimAActivityPresenter(this);
    }

    @Override
    public void bindData() {
//        setData2();
        uploadBean = new AnimAlistBean.DataListBean();
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        setTitle("动物检疫证明A");
        getPresenter().getShouyiJiandu();
        intent1 = getIntent();
        dateBeans = (AnimAProcessListBean.DataListBean) intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        getPresenter().getUserDetil(dateBeans);

        listViewDialog = new ListViewDialog.Buidle(this).setTitle("消毒方法")
                .setListener(new ListViewDialog.OnClickListener() {
                    @Override
                    public void onClick(String selectSterilize) {
                        etSteriallierMethod.setText(selectSterilize);
                    }
                }).create();
        dialog = new ErBiaoDialog(this, "0", text -> tvErbiao.setText(text));

        ltDialog = new LimitTimeSelectDialog(this);
        ltDialog.setCallBack(new LimitTimeSelectDialog.CallBack() {
            @Override
            public void enter(String beSelectDay) {
                tv_spinner.setText(beSelectDay);
            }

            @Override
            public void cancel() {

            }
        });

        dsDialog = new DataSelectDialog(this);
        dsDialog.setTitle("选择消毒方式");
        dsDialog.setDatas(getXDMethod());
        dsDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                etSteriallierMethod.setText(data);
            }

            @Override
            public void cancel() {

            }
        });
    }

    private AnimAlistBean.DataListBean transforme(AnimAProcessUserDetilBean.DataListBean dateBean) {
        AnimAlistBean.DataListBean bean = uploadBean;
        bean.setFGlid(dateBean.getFStId());//关联申报id
        bean.setFSm5(dateBeans.getMyuse());//区分记录表
//        bean.setFSm1(dateBeans.getMyuse());//区分记录表
        bean.setANumber(dateBean.getCode());//检疫证编号
        bean.setACargoOwner(dateBean.getShippername());//货主
        bean.setShippername(dateBean.getShippername());//货主
        bean.setAPhone(dateBean.getTeltphone());//联系电话
        bean.setAXuZhong(dateBean.getAnimalsort());//动物种类
        bean.setAQuantity(TextUtils.isEmpty(dateBean.getAnimalnum()) ? 0 : Integer.valueOf(dateBean.getAnimalnum()));//数量
        bean.setADanWei(dateBean.getQDWDanWei());//单位
        bean.setAYongTu(dateBean.getMyuse());//用途
        bean.setAPlace(dateBean.getRqydzxx());//起运地
        bean.setAMemo2(dateBean.getRqydzxx());
        bean.setAShengQy(dateBean.getStartaddress());//启运省
        bean.setAShiQy(dateBean.getStartaddress1());//启运市
        bean.setAXianQy(dateBean.getStartaddress2());//启运县
        bean.setAXiangQy(dateBean.getStartaddress3());//启运乡
        bean.setACunQy(dateBean.getStartaddress4());//保存起运地 某某 养殖场信息等

        bean.setAArr(dateBean.getRdddzxxs());//到达地
        bean.setAMemo3(dateBean.getRdddzxxs());
        bean.setAShengDd(dateBean.getRdddzxx());
        bean.setAShiDd(dateBean.getRdddzxx1());
        bean.setAXianDd(dateBean.getRdddzxx2());
        bean.setAXiangDd(dateBean.getRdddzxx3());
        bean.setACunDd(dateBean.getRdddzxx4());
        bean.setAEarTag(dateBean.getQDWErBiaoHao());//耳标号
        //运载工具号
        bean.setATrademark(dateBean.getToolid());
        bean.setIsPrant("已保存");
//        //承运人
//        bean.setACarrier(dateBean.get);
//        //承运人联系电话
//        bean.setAPhoneCyr();
        bean.setAVeterinary(getPresenter().getUser().getFUNAME());
//        etEndPlace.setText(uploadBean.getAShengDd()+ uploadBean.getAShiDd()+uploadBean.getAXianDd());//到达地省市县
//        etEndVillage.setText(uploadBean.getAXiangDd());//到达地乡cun
//        etEndOther.setText(uploadBean.getACunDd());//保存到达地 某某 养殖场信息等
//        tvErbiao.setText(uploadBean.getAEarTag());//耳标号
//        etSignatureChecked.setText(uploadBean.getAVeterinary());//官方兽医签字
        if (dateBeans.getMyuse().contains("AH_AnimalOrigin")) {
            uploadBean.setSaveId(2);
        } else {
            uploadBean.setSaveId(3);
        }
        return bean;
    }

    @Override
    public void bindListener() {
        btPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    showNormalDialog();
                }
            }
        });
        btSteriallierMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsDialog.show();
            }
        });

        btErbiao.setOnClickListener(v -> new ErBiaoDialog(this, etAnimCount.getText().toString(), text -> tvErbiao.setText(text)).show());
        btErbiao.setOnClickListener(v -> {
            if (isEmpty(getString(etAnimCount)) || "0".equals(getString(etAnimCount))) {
                showToast("动物数量必填");
            } else {
                dialog.show(getString(etAnimCount));
            }
        });
        tv_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltDialog.show();
            }
        });

        bindRadioListener();
    }

    private void bindRadioListener() {
        rbRailway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) return;
                rbHighway.setChecked(false);
                rbWaveway.setChecked(false);
                rbAviation.setChecked(false);
            }
        });
        rbHighway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) return;
                rbRailway.setChecked(false);
                rbWaveway.setChecked(false);
                rbAviation.setChecked(false);
            }
        });

        rbWaveway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) return;
                rbRailway.setChecked(false);
                rbHighway.setChecked(false);
                rbAviation.setChecked(false);
            }
        });
        rbAviation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) return;
                rbRailway.setChecked(false);
                rbHighway.setChecked(false);
                rbWaveway.setChecked(false);
            }
        });

    }

    private List getXDMethod() {
        List<CodeXD> list = LocalGreenDao.getInstance().querySterilizeWay();
        List<String> methods = new ArrayList<>();
        for (CodeXD d : list) {
            methods.add(d.getcName());
        }
        return methods;
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AnimAActivity.this);
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

    private AnimAlistBean.DataListBean getData() {
        uploadBean.setACarrier(etCarrierName.getText().toString());//承运人
        uploadBean.setAPhoneCyr(etCarrierPhone.getText().toString());//承运人联系电话
        uploadBean.setATrademark(etCarrierUtilNumber.getText().toString()); //运载工具牌号
        uploadBean.setADisinfection(etSteriallierMethod.getText().toString());//装运前消毒情况
        if (rbRailway.isChecked()) {//运输方式
            uploadBean.setAYunZai("铁路");
        } else if (rbHighway.isChecked()) {
            uploadBean.setAYunZai("公路");
        } else if (rbWaveway.isChecked()) {
            uploadBean.setAYunZai("水路");
        } else if (rbAviation.isChecked()) {
            uploadBean.setAYunZai("航空");
        }
        uploadBean.setAYouXiaoRi(Integer.parseInt(tv_spinner.getText().toString()));//有效到达日
        uploadBean.setAMemo1(etRemark.getText().toString());//备注
        uploadBean.setADwPhone(etCheckPhone.getText().toString());//监督所电话
        uploadBean.setAEarTag(tvErbiao.getText().toString());
        uploadBean.setAVeterinary(etSignatureChecked.getText().toString());//官方兽医签字
//        uploadBean.setSaveId(2);
        return uploadBean;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean check() {
        if (isEmpty(getString(etCarrierName))) {
            etCarrierName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etCarrierName.requestFocus();
            showToast("承运人不能为空");
            return false;
        }
        if (isEmpty(getString(etCarrierPhone))) {
            etCarrierPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etCarrierPhone.requestFocus();
            showToast("承运人电话不能为空");
            return false;
        }

        String type = etAnimType.getText().toString();
        if (type.equals("猪") || type.equals("牛") || type.equals("羊")) {
            if (TextUtils.isEmpty(tvErbiao.getText().toString())) {
                showToast("猪、牛、羊的耳标号不能为空");
                return false;
            }
        }

        if (isEmpty(getString(etCarrierUtilNumber))) {
            etCarrierUtilNumber.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etCarrierUtilNumber.requestFocus();
            showToast("运载工具号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etSteriallierMethod.getText().toString())) {
            showToast("请选择消毒方式");
            return false;
        }
        if (isEmpty(getString(etSignatureChecked))) {
            etSignatureChecked.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etSignatureChecked.requestFocus();
            showToast("官方兽医签字不能为空");
            return false;
        }

        if (isEmpty(getString(etCheckPhone))) {
            etCheckPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etCheckPhone.requestFocus();
            showToast("动物卫生监督所电话不能为空");
            return false;
        }

        if (isEmpty(getString(tv_spinner)) || getString(tv_spinner).equals("请选择")) {
            showToast("请选择有效到达日期");
            return false;
        }
        return true;
    }

    @Override
    public void uploadSuccess(String guid) {
        uploadBean.setGuid(guid);
        RxBus.getInstance().post(new RefreshBus(RefreshBus.QuarantineAnimFragment));
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        //成功上传数据,显示是否打印的对话框
        openPrintDialog();
    }

    @Override
    public void getUserDetil1(AnimAProcessUserDetilBean.DataListBean dataList) {
        transforme(dataList);
        etQuarantineNo.setText(uploadBean.getANumber());//检疫证编号
        etUserName.setText(uploadBean.getACargoOwner());//货主
        etUserPhone.setText(uploadBean.getAPhone());//联系电话
        etAnimType.setText(uploadBean.getAXuZhong());//动物种类
        etAnimCount.setText(uploadBean.getAQuantity() + "");//数量及单位
        tvDanwei.setText(uploadBean.getADanWei());
        etPurpose.setText(uploadBean.getAYongTu());//用途
        etStartPlace.setText(uploadBean.getAShengQy() + uploadBean.getAShiQy() + uploadBean.getAXianQy());//起运地省市县
//        etStartVillage.setText(uploadBean.getAXiangQy());//起运地乡cun
//        etStartOther.setText(uploadBean.getACunQy());//保存起运地 某某 养殖场信息等
        etStartAddress.setText(uploadBean.getAXiangQy() + " " + uploadBean.getACunQy());

        etEndPlace.setText(uploadBean.getAShengDd() + uploadBean.getAShiDd() + uploadBean.getAXianDd());//到达地省市县
//        etEndVillage.setText(uploadBean.getAXiangDd());//到达地乡cun
//        etEndOther.setText(uploadBean.getACunDd());//保存到达地 某某 养殖场信息等
        etEndAddress.setText(uploadBean.getAXiangDd() + " " + uploadBean.getACunDd());

        tvErbiao.setText(uploadBean.getAEarTag());//耳标号
        etCarrierUtilNumber.setText(uploadBean.getATrademark());
//        etSignatureChecked.setText(uploadBean.getAVeterinary());//官方兽医签字
        tvProveTime.setText(uploadBean.getDateQF());//签发日期

    }

    @Override
    public void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean) {
        etCheckPhone.setText(dataListBean.getResult());//监督所电话
        etSignatureChecked.setText(dataListBean.getResult1());//官方兽医签字
    }

    @Override
    public void getPrintId(String id, String guid) {
        this.printId = id + "";
    }


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
                    bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_ANIM_A);
                    bundle.putString(Constance.START_ACTIVITY_PRINTID, this.printId);
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
    public void setTime(String time, long longTime) {
        uploadBean.setDateQF(time);//签发日期
        tvProveTime.setText(time);//签发日期

    }
}
