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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ProductA_BBeanListGai;
import com.wyw.jiangsu.bean.Qua_AnimalProductsBListBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IProductBActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.ProductBActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 产品检疫证明B 详情
 */
public class ProductBActivity extends MVPActivity<ProductBActivityPresenter> implements IProductBActivity, ITime {

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
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_product_name)
    EditText etProductName;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.et_product_area)
    EditText etProductArea;
    @BindView(R.id.et_quarantine_flag_no)
    EditText etQuarantineFlagNo;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.et_signature_checked)
    EditText etSignatureChecked;
    @BindView(R.id.et_prove_time)
    EditText etProveTime;
    @BindView(R.id.bt_print)
    Button btPrint;
    //    @BindView(R.id.et_danwei)
//    EditText etDanwei;
    @BindView(R.id.tv_danwei)
    TextView tv_danwei;
    private Qua_AnimalProductsBListBean.DataListBean dataListBean;
    private Qua_AnimalProductsBListBean.DataListBean bean;
    TimePresenter timePresenter;
    String mItem;
    private Intent intent1;

    private DataSelectDialog unitSelectDialog;//单位选择
    private String[] unit = {"头", "只", "匹", "羽", "箱"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_b);
        ButterKnife.bind(this);
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
    }

    @Override
    protected ProductBActivityPresenter createPresenter() {
        return new ProductBActivityPresenter(this);
    }

    @Override
    public void bindData() {
        tvTitle.setText("动物检疫证明（产品B）");
        getPresenter().getShouyiJiandu();
        intent1 = getIntent();
        ProductA_BBeanListGai.DataListBean transformered = (ProductA_BBeanListGai.DataListBean) intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        dataListBean = transformer(transformered);
        etQuarantineNo.setText(dataListBean.getPBNumber());//检疫证编号

        etUserName.setText(dataListBean.getPBCargoOwner());//货主名字
        etProductName.setText(dataListBean.getPBName());      //产品名称
        etAnimCount.setText(dataListBean.getPBQuantity());//数量和单位    要做判断

        tv_danwei.setText(dataListBean.getPBDanWei());

        unitSelectDialog = new DataSelectDialog(this);
        unitSelectDialog.setTitle("选择单位");
        unitSelectDialog.setDatas(unit);

        etProductArea.setText(dataListBean.getPBOrigin());//产地
        etQuarantineFlagNo.setText(dataListBean.getPBSign());//检疫标志号
//        etStartPlace.setText(dataListBean.getPBPumAdd());
        //dataListBean.getPBUnitName() +
        etStartPlace.setText(dataListBean.getPBProductionunit());//生产单位名称和地址
        etEndPlace.setText(dataListBean.getPBDestinations());//目的地
        etRemark.setText(dataListBean.getPBRemarks());//备注
        //getScdz


        etProveTime.setText(dataListBean.getDateQF());//签发日期

    }

    private Qua_AnimalProductsBListBean.DataListBean transformer(ProductA_BBeanListGai.DataListBean transformered) {
        Qua_AnimalProductsBListBean.DataListBean transDateBean = new Qua_AnimalProductsBListBean.DataListBean();
        transDateBean.setPBUnitName(transformered.getSCDWMC());//生产单为名称

        //货主名称  FSuserName
        transDateBean.setPBCargoOwner(transformered.getShippername());//货主名称
        transDateBean.setPBOrigin(transformered.getFarmsnme());//产地
        transDateBean.setPBProductionunit(transformered.getScdz());//生产单位地址
        transDateBean.setPBDestinations(transformered.getDddz());//目的地
        transDateBean.setFGlid(transformered.getFStId());//ID
        transDateBean.setPBName(transformered.getOnimalsort()); //产品名称getOnimalsort
        transDateBean.setPBNumber(transformered.getCode());//编号
        //数量及单位   animalnum   QCPDanWei
        transDateBean.setPBQuantity(transformered.getAnimalnum());//数量
        transDateBean.setPBDanWei(transformered.getQCPDanWei());//单位
        //transDateBean.setPVeterinary(getPresenter());
        transDateBean.setPBVeterinary(getPresenter().getUser().getFUNAME());//官方兽医签字
//        transDateBean.setDateQF(mItem);//签发日期
        return transDateBean;
    }

    @Override
    public void bindListener() {
        btPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChenked())
                    showNormalDialog();
            }
        });
        unitSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                tv_danwei.setText(data);
            }

            @Override
            public void cancel() {

            }
        });
        tv_danwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitSelectDialog.show();
            }
        });
    }
    private void showNormalDialog(){
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(ProductBActivity.this);
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
     * 需要上传的数据
     */

    private Qua_AnimalProductsBListBean.DataListBean getData() {
        bean = new Qua_AnimalProductsBListBean.DataListBean();
        bean.setPBNumber(etQuarantineNo.getText().toString());//检疫证编号
        bean.setPBCargoOwner(etUserName.getText().toString());//货主名字
        bean.setPBName(etProductName.getText().toString());//产品名称
        bean.setPBQuantity(etAnimCount.getText().toString());//数量
        bean.setPBDanWei(tv_danwei.getText().toString());//单位
        bean.setPBOrigin(etProductArea.getText().toString());//产地
        bean.setPBUnitName(dataListBean.getPBUnitName());//生产单位名称
        bean.setPBProductionunit(dataListBean.getPBProductionunit());//生产单位地址
        bean.setPBPumAdd(etStartPlace.getText().toString());//生产单位名称地址
        bean.setPBDestinations(etEndPlace.getText().toString());//目的地
        bean.setPBSign(etQuarantineFlagNo.getText().toString());//检疫编译号
        bean.setPBRemarks(etRemark.getText().toString());//备注
        bean.setSaveId(Integer.parseInt("2"));//备注ID
        bean.setFGlid(dataListBean.getFGlid());//ID   //以前是bean
        bean.setPBVeterinary(etSignatureChecked.getText().toString());//官方兽医签字
        bean.setDateQF(etProveTime.getText().toString());//签发日期
        bean.setIsPrant("已打印");
        return bean;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean isChenked() {
        if (isEmpty(getString(etQuarantineNo))) {
            etQuarantineNo.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etQuarantineNo.performClick();
            showToast("检疫证编号不能为空");
            return false;
        }
        if (isEmpty(getString(etUserName))) {
            etUserName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etUserName.performClick();
            showToast("货主姓名不能为空");
            return false;
        }
        if (isEmpty(getString(etProductName))) {
            etProductName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etProductName.performClick();
            showToast("产品名称不能为空");
            return false;
        }
        if (isEmpty(getString(etAnimCount))) {
            etAnimCount.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etAnimCount.performClick();
            showToast("数量不能为空");
            return false;
        }

        if (isEmpty(getString(tv_danwei))) {
//            etDanwei.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
//            etDanwei.performClick();
            showToast("单位不能为空");
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

    @Override
    public void upLoadSucceed(String guid) {
        bean.setGuid(guid);
//        finish();
        RxBus.getInstance().post(new RefreshBus(RefreshBus.QuarantineAnimFragment));
        //成功上传数据,显示是否打印的对话框
        Toast.makeText(this, "上传成功", Toast.LENGTH_LONG).show();

        openPrintDialog();
    }

    @Override
    public void getPrint(String id) {

    }

    @Override
    public void shouyijiandu(ShouyiJiandusuoBean.DataBean dataLiBean) {
        etSignatureChecked.setText(dataLiBean.getResult1());//官方兽医签字

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
                    bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_PRODUCT_B);
                    bundle.putSerializable(Constance.START_ACTIVITY_DATA, bean);
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
        etProveTime.setText(time);
    }
}
