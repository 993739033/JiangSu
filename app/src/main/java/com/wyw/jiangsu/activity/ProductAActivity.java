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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ProductA_BBeanListGai;
import com.wyw.jiangsu.bean.Qua_AnimalProductsAListBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.db.CodeXD;
import com.wyw.jiangsu.db.LocalGreenDao;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.LimitTimeSelectDialog;
import com.wyw.jiangsu.dialog.ListViewDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IProductAActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.ProductAActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 产品检疫证明A 详情
 */
public class ProductAActivity extends MVPActivity<ProductAActivityPresenter> implements IProductAActivity, ITime {


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
    //    @BindView(R.id.rg_transportation)
//    RadioGroup rgTransportation;
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

    Qua_AnimalProductsAListBean.DataListBean dataListBean;

    @BindView(R.id.et_danwei)
    EditText etDanwei;
    private ListViewDialog listViewDialog;
    TimePresenter timePresenter;
    String mTiem;
    private Intent intent1;
    private String printId;

    DataSelectDialog dsDialog;//消毒方式选择
    LimitTimeSelectDialog ltDialog;//日期选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_a);
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
    }

    @Override
    protected ProductAActivityPresenter createPresenter() {
        return new ProductAActivityPresenter(this);
    }

    @Override
    public void bindData() {
        tvTitle.setText("动物检疫证明（产品A）");
        getPresenter().getShouyiJiandu();
        intent1 = getIntent();
        ProductA_BBeanListGai.DataListBean transformered = (ProductA_BBeanListGai.DataListBean) intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        //转化
        dataListBean = transformer(transformered);
//        etStartPlace.setText(dataListBean.getPUnitName() + dataListBean.getPProductionunit());//生产单位名称和地址
        etStartPlace.setText(dataListBean.getPProductionunit());//生产单位名称和地址
        etEndPlace.setText(dataListBean.getPMemo2());//目的地
        etQuarantineNo.setText(dataListBean.getPNumber()); //检疫站编号
        etProductName.setText(dataListBean.getPName());//产品名称
        etAnimCount.setText(dataListBean.getPQuantity());//数量

        etUserName.setText(dataListBean.getPCargoOwner());//货主
        etUserPhone.setText(dataListBean.getPPhone());//联系电话

        etDanwei.setText(dataListBean.getPDanWei()); //单位
//        etEndPlace.setText(dataListBean.getPSheng() + dataListBean.getPShi() + dataListBean.getPXian());//目的地
        // etCarrierName.setText(dataListBean.getPCarrier());//承运人
        // etCarrierPhone.setText(dataListBean.getPPhoneCyr());//联系电话
        //etCarrierUtilNumber.setText(dataListBean.getPTrademark());//运载工具号
        //etSteriallierMethod.setText(dataListBean.getPDisinfection());//请输入或选择消毒方式
        //etRemark.setText(dataListBean.getPMemo1());//备注

        //etCheckPhone.setText(dataListBean.getPDwPhone());//动物卫生监督所电话

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
    }

    //获取消毒方式集合
    private List getXDMethod() {
        List<CodeXD>list = LocalGreenDao.getInstance().querySterilizeWay();
        List<String> methods = new ArrayList<>();
        for (CodeXD d : list) {
            methods.add(d.getcName());
        }
        return methods;
    }

    private Qua_AnimalProductsAListBean.DataListBean transformer(ProductA_BBeanListGai.DataListBean transformered) {
        Qua_AnimalProductsAListBean.DataListBean transDateBean = new Qua_AnimalProductsAListBean.DataListBean();
        transDateBean.setPNumber(transformered.getCode());  //动物检疫合格编号
        transDateBean.setPName(transformered.getOnimalsort()); //产品名称
        transDateBean.setPQuantity(transformered.getAnimalnum());//数量
        transDateBean.setPDanWei(transformered.getQCPDanWei());//单位
        transDateBean.setPUnitName(transformered.getSCDWMC());//生产单为名称
        transDateBean.setPProductionunit(transformered.getScdz());//生产单位地址
        transDateBean.setPMemo2(transformered.getDddz());  //目的地
        transDateBean.setFGlid(transformered.getFStId());//ID
        transDateBean.setPSheng(transformered.getRdddzxx());//目的地省
        transDateBean.setPShi(transformered.getRdddzxx1());//目的地市
        transDateBean.setPXian(transformered.getRdddzxx2());//目的地乡
        //transDateBean.setPVeterinary(getPresenter());
        transDateBean.setPVeterinary(getPresenter().getUser().getFUNAME());//官方兽医签字
        //transDateBean.setDateQF(mTiem);//签发日期

        transDateBean.setPCargoOwner(transformered.getShippername());//货主名字
        transDateBean.setPPhone(transformered.getTeltphone());//联系电话

        return transDateBean;
    }
    private void bindRadioListener() {
        rbRailway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) return;
                rbHighway.setChecked(false);
                rbWaveway.setChecked(false);
                rbAviation.setChecked(false);
            }
        });
        rbHighway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) return;
                rbRailway.setChecked(false);
                rbWaveway.setChecked(false);
                rbAviation.setChecked(false);
            }
        });

        rbWaveway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) return;
                rbRailway.setChecked(false);
                rbHighway.setChecked(false);
                rbAviation.setChecked(false);
            }
        });
        rbAviation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) return;
                rbRailway.setChecked(false);
                rbHighway.setChecked(false);
                rbWaveway.setChecked(false);
            }
        });

    }


    @Override
    public void bindListener() {
        btPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked())
                    showNormalDialog();
            }
        });
        btSteriallierMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsDialog.show();
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

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(ProductAActivity.this);
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean isChecked() {
        if (isEmpty(getString(etQuarantineNo))) {
            etQuarantineNo.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etQuarantineNo.requestFocus();
            showToast("检疫证编号不能为空");
            return false;
        }
        if (isEmpty(getString(etUserName))) {
            etUserName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etUserName.requestFocus();
            showToast("货主姓名不能为空");
            return false;
        }
        if (isEmpty(getString(etProductName))) {
            etProductName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etProductName.requestFocus();
            showToast("产品名称不能为空");
            return false;
        }
        if (isEmpty(getString(etAnimCount))) {
            etAnimCount.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etAnimCount.requestFocus();
            showToast("数量不能为空");
            return false;
        }
        if (isEmpty(getString(etDanwei))) {
            etDanwei.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etDanwei.requestFocus();
            showToast("单位不能为空");
            return false;
        }
        if (isEmpty(getString(etCarrierName))) {
            etCarrierName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etCarrierName.requestFocus();
            showToast("承运人不能为空");
            return false;
        }
        if (isEmpty(getString(etCarrierPhone))) {
            etQuarantineNo.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            showToast("承运人电话不能为空");
            return false;
        }
        if (isEmpty(getString(etCarrierUtilNumber))) {
            etCarrierUtilNumber.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etCarrierUtilNumber.requestFocus();
            showToast("运载工具号不能为空");
            return false;
        }
        if (isEmpty(getString(etSteriallierMethod))) {
            etSteriallierMethod.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etSteriallierMethod.requestFocus();
            showToast("消毒方式不能为空");
            return false;
        }
        //etCheckPhone
        if (isEmpty(getString(etCheckPhone))) {
            etCheckPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etCheckPhone.requestFocus();
            showToast("动物卫生监督所电话不能为空");
            return false;
        }
        if (isEmpty(getString(tv_spinner))||getString(tv_spinner).equals("请选择")) {
            showToast("请选择到达有效日期");
            return false;
        }

        if (isEmpty(getString(etSignatureChecked))) {
            etSignatureChecked.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etSignatureChecked.requestFocus();
            showToast("官方兽医签字不能为空");
            return false;
        }


        return true;
    }

    private Qua_AnimalProductsAListBean.DataListBean getData() {

        dataListBean.setPNumber(etQuarantineNo.getText().toString());//检疫证编号
        dataListBean.setPCargoOwner(etUserName.getText().toString());//货主名字
        dataListBean.setPPhone(etUserPhone.getText().toString());    //联系电话
        dataListBean.setPName(etProductName.getText().toString());   //产品名称
        dataListBean.setPQuantity(etAnimCount.getText().toString());      //数量
        dataListBean.setPDanWei(etDanwei.getText().toString());          //单位
        dataListBean.setPCarrier(etCarrierName.getText().toString());//承运人
        dataListBean.setPPhoneCyr(etCarrierPhone.getText().toString());//联系电话
        //运载方式
        if (rbRailway.isChecked()) {
            dataListBean.setPYunZai("铁路");
        } else if (rbHighway.isChecked()) {
            dataListBean.setPYunZai("公路");
        } else if (rbWaveway.isChecked()) {
            dataListBean.setPYunZai("水路");
        } else {
            dataListBean.setPYunZai("航空");
        }
        dataListBean.setSaveId("2");
        dataListBean.setFGlid(dataListBean.getFGlid());//ID
        dataListBean.setPYouXiaoRi(Integer.parseInt(tv_spinner.getText().toString().trim()));// 有效到达日
        dataListBean.setPTrademark(etCarrierUtilNumber.getText().toString()); //运载工具牌号
        dataListBean.setPDisinfection(etSteriallierMethod.getText().toString());//消毒方式
        dataListBean.setPVeterinary(etSignatureChecked.getText().toString());   //官方兽医签字
        dataListBean.setDateQF(tvProveTime.getText().toString());               //签发日期
        dataListBean.setPMemo1(etRemark.getText().toString());                  //备注
        dataListBean.setPMemo4(etStartPlace.getText().toString());//拼接生产单位地址
        dataListBean.setPDwPhone(etCheckPhone.getText().toString()); //动物卫生监督所联系电话
        dataListBean.setIsPrant("已打印");


        return dataListBean;

    }

    @Override
    public void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean) {
        etCheckPhone.setText(dataListBean.getResult());//监督所电话
        etSignatureChecked.setText(dataListBean.getResult1());//官方兽医签字
    }

    @Override
    public void uploadComplete(String guid) {
        dataListBean.setGuid(guid);
        //成功上传数据,显示是否打印的对话框
        openPrintDialog();
    }

    @Override
    public void getPrintId(String id, String guid) {
        this.printId = id + "";
    }

    private void openPrintDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View inflate = layoutInflater.inflate(R.layout.tishi_activity, null);
        new AlertDialog.Builder(this)
                .setTitle("是否打印")
                .setCancelable(false)
                .setView(inflate)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), PrintAcitivty.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(Constance.ACTIVITY_TYPE, Constance.TYPE_PRINT_PRODUCT_A);
                        bundle.putString(Constance.START_ACTIVITY_PRINTID, printId);
                        bundle.putSerializable(Constance.ACTIVITY_DATA, dataListBean);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        setResult(RESULT_OK, intent1);
                        finish();

                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_OK, intent1);
                        finish();
                    }
                })
                .show();

    }

    @Override
    public void setTime(String time, long longTime) {
        tvProveTime.setText(time);//签发日期
    }
}
