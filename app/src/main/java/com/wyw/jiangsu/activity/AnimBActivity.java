package com.wyw.jiangsu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.print.JQPrintActivity;
import com.wyw.jiangsu.activity.print.PrintMainActivity;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.bean.AnimAProcessUserDetilBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.AddressDialog;
import com.wyw.jiangsu.dialog.ErBiaoDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimBActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.AnimBActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动物检疫证明B 详情
 */
public class AnimBActivity extends MVPActivity<AnimBActivityPresenter> implements IAnimBActivity, ITime {

    @BindView(R.id.et_quarantine_no)
    EditText etQuarantineNo;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.tv_purpose)
    TextView tvPurpose;
    @BindView(R.id.tv_start_place)
    TextView tvStartPlace;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.tv_end_place)
    TextView tvEndPlace;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.et_erbiao)
    EditText etErbiao;
    @BindView(R.id.btn_erbiao)
    Button btnErbiao;
    @BindView(R.id.et_signature_checked)
    EditText etSignatureChecked;
    @BindView(R.id.et_prove_time)
    TextView tvProveTime;
    @BindView(R.id.bt_print)
    Button btPrint;
    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_anim_type)
    TextView tvAnimType;
    private TimePresenter timePresenter;
    private AnimBlistBean.DataListBean uploadBean;
    ErBiaoDialog dialog;
    private String printId;
    private String guid;
    private TimeSelectDialog timeSelectDialog;
    private AddressDialog addressDialog;
    AnimAProcessListBean.DataListBean dateBeans;
    private Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_b);
        ButterKnife.bind(this);

    }

    @Override
    protected AnimBActivityPresenter createPresenter() {
        return new AnimBActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("动物检疫证明B");
        getPresenter().getShouyiJiandu();
        uploadBean = new AnimBlistBean.DataListBean();
        intent1 = getIntent();
        dateBeans = (AnimAProcessListBean.DataListBean) intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        getPresenter().getUserDetil(dateBeans);
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        dialog = new ErBiaoDialog(this, "0", text -> etErbiao.setText(text));

    }

    @Override
    public void bindListener() {

        btnErbiao.setOnClickListener(v -> new ErBiaoDialog(this, etAnimCount.getText().toString(), text -> etErbiao.setText(text)).show());
        btnErbiao.setOnClickListener(v -> {
            if (isEmpty(getString(etAnimCount)) || "0".equals(getString(etAnimCount))) {
                showToast("动物数量必填");
            } else {
                dialog.show(getString(etAnimCount));
            }
        });
        btPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadBean.setAQEarTag(etErbiao.getText().toString());
                if (check()) {
                    showNormalDialog(uploadBean);
                }
            }
        });
    }

    private void showNormalDialog(AnimBlistBean.DataListBean uploadBean) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AnimBActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(uploadBean);
                        openPrintDialog();
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

    private boolean check() {
        if (isEmpty(getString(etSignatureChecked))) {
            showToast("官方兽医签字不能为空");
            return false;
        }
        String type = tvAnimType.getText().toString();
        if (type.equals("猪") || type.equals("牛") || type.equals("羊")) {
            if (TextUtils.isEmpty(etErbiao.getText().toString())) {
                showToast("猪、牛、羊的耳标号不能为空");
                return false;
            }
        }

        return true;
    }

    @Override
    public void uploadSucceed() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.QuarantineAnimFragment));
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        //成功上传数据,显示是否打印的对话框
        openPrintDialog();
    }

    @Override
    public void getUserDetil1(AnimAProcessUserDetilBean.DataListBean dataListBean) {
        transforme(dataListBean);
        etErbiao.setText(uploadBean.getAQEarTag());//// 牲畜耳标号
        //        etSignatureChecked.setText(uploadBean.getAQVeterinary());// 官方兽医签字
        //        etProveTime.setText(uploadBean.getDateQF());// 签发日期
        etQuarantineNo.setText(uploadBean.getAQNumber());//检疫证编号
        etUserName.setText(uploadBean.getAQCargoOwner());//货主
        etUserPhone.setText(uploadBean.getAQPhone());//联系电话
        tvAnimType.setText(uploadBean.getAQXuZhong());//动物种类
        etAnimCount.setText(uploadBean.getAQQuantity() + "");//数量及单位
        tvDanwei.setText(uploadBean.getAQDanWei());
        tvPurpose.setText(uploadBean.getAQYongTu());//用途
        tvStartPlace.setText(uploadBean.getAQShiQy() + uploadBean.getAQXianQy() + "");//起运地省市县
        etStartPlace.setText(uploadBean.getAQXiangQy() + uploadBean.getAQCunQy());//起运地乡cun
        // etStartOther.setText(uploadBean.getAQCunQy());//保存起运地 某某 养殖场信息等
        tvEndPlace.setText(uploadBean.getAQShiDd() + uploadBean.getAQXianDd() + "");//到达地省市县
        etEndPlace.setText(uploadBean.getAQXiangDd() + uploadBean.getAQCunDd());//到达地乡cun
        //etEndOther.setText(uploadBean.getAQCunDd());//保存到达地 某某 养殖场信息等
        //        tvErbiao.setText(uploadBean.getAEarTag());//耳标号
        //        etCarrierUtilNumber.setText(uploadBean.getATrademark());
        //        etSignatureChecked.setText(uploadBean.getAQVeterinary());//官方兽医签字
        tvProveTime.setText(uploadBean.getDateQF());//签发日期
    }

    @Override
    public void shouyijiandu(ShouyiJiandusuoBean.DataBean dataLiBean) {
        etSignatureChecked.setText(dataLiBean.getResult1());//官方兽医签字
        uploadBean.setAQVeterinary(etSignatureChecked.getText().toString());
    }

    @Override
    public void getPrintId(String id, String guid) {
        this.printId = id;
        this.guid = guid;
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
                    uploadBean.setGuid(this.guid);
                    int printer = getSharedPreferences("print", MODE_PRIVATE).getInt("printer", 0);
                    //公司热敏打印机
                    if (printer == 2) {
                        Intent intent = new Intent(this, PrintMainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_ANIM_B);
                        bundle.putString(Constance.START_ACTIVITY_PRINTID, this.printId);
                        bundle.putSerializable(Constance.START_ACTIVITY_DATA, uploadBean);
                        bundle.putInt("index", 0);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        setResult(RESULT_OK, intent1);
                        finish();

                    }if (printer==3){
                        Intent intent = new Intent(this, JQPrintActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_ANIM_B);
                        bundle.putString(Constance.START_ACTIVITY_PRINTID, this.printId);
                        bundle.putSerializable(Constance.START_ACTIVITY_DATA, uploadBean);
                        bundle.putInt("index", 0);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        setResult(RESULT_OK, intent1);
                        finish();

                    } else {
                        Intent intent = new Intent(this, PrintAcitivty.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_ANIM_B);
                        bundle.putString(Constance.START_ACTIVITY_PRINTID, this.printId);
                        bundle.putSerializable(Constance.START_ACTIVITY_DATA, uploadBean);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        setResult(RESULT_OK, intent1);
                        finish();
                    }

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

    private AnimBlistBean.DataListBean transforme(AnimAProcessUserDetilBean.DataListBean dateBean) {
        AnimBlistBean.DataListBean bean = uploadBean;
        bean.setFGlid(dateBean.getFStId());//关联申报id
        bean.setFSm5(dateBeans.getMyuse());//区分记录表
        bean.setAQNumber(dateBean.getCode());//检疫证编号
        bean.setAQCargoOwner(dateBean.getShippername());//货主
        bean.setAQPhone(dateBean.getTeltphone());//联系电话
        bean.setAQXuZhong(dateBean.getAnimalsort());//动物种类
        bean.setAQQuantity(TextUtils.isEmpty(dateBean.getAnimalnum()) ? 0 : Integer.valueOf(dateBean.getAnimalnum()));//数量
        bean.setAQDanWei(dateBean.getQDWDanWei());//单位
        bean.setAQYongTu(dateBean.getMyuse());//用途
        bean.setAQPlace(dateBean.getRqydzxx());//起运地
        bean.setAQMemo2(dateBean.getRqydzxx());
        //        bean.setAShengQy(dateBean.getStartaddress());//启运省
        bean.setAQShiQy(dateBean.getStartaddress1());//启运市
        bean.setAQXianQy(dateBean.getStartaddress2());//启运县
        bean.setAQXiangQy(dateBean.getStartaddress3());//启运乡
        bean.setAQCunQy(dateBean.getStartaddress4());//保存起运地 某某 养殖场信息等

        bean.setAQArr(dateBean.getRdddzxxs());//到达地
        bean.setAQMemo3(dateBean.getRdddzxxs());
        //        bean.setAQShengDd(dateBean.getRdddzxx());
        bean.setAQShiDd(dateBean.getRdddzxx1());
        bean.setAQXianDd(dateBean.getRdddzxx2());
        bean.setAQXiangDd(dateBean.getRdddzxx3());
        bean.setAQCunDd(dateBean.getRdddzxx4());
        bean.setAQEarTag(dateBean.getQDWErBiaoHao());//耳标号
        //运载工具号
        //        bean.setAQTrademark(dateBean.getToolid());
        bean.setIsPrant("已保存");
        //        //承运人
        //        bean.setACarrier(dateBean.get);
        //        //承运人联系电话
        //        bean.setAPhoneCyr();
        bean.setAQVeterinary(getPresenter().getUser().getFUNAME());
        //        etEndPlace.setText(uploadBean.getAShengDd()+ uploadBean.getAShiDd()+uploadBean.getAXianDd());//到达地省市县
        //        etEndVillage.setText(uploadBean.getAXiangDd());//到达地乡cun
        //        etEndOther.setText(uploadBean.getACunDd());//保存到达地 某某 养殖场信息等
        //        tvErbiao.setText(uploadBean.getAEarTag());//耳标号
        //        etSignatureChecked.setText(uploadBean.getAQVeterinary());//官方兽医签字
        if (dateBeans.getMyuse().contains("AH_AnimalOrigin")) {
            uploadBean.setSaveId(2);
        } else {
            uploadBean.setSaveId(3);
        }
        //        uploadBean.setSaveId(2);
        return bean;
    }

}
