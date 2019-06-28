package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimProcessListDetilActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.AnimProcessListDetilActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.view.FullPhotoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.R.id.rb_result_yes;

/**
 * 动物产地检疫申报处理详情
 */
public class AnimProcessListDetilActivity extends MVPActivity<AnimProcessListDetilActivityPresenter> implements IAnimProcessListDetilActivity, ITime {


    @BindView(R.id.rb_yes)
    EditText rbYes;
    @BindView(R.id.rb_zhongxu_xuke)
    EditText rbZhongxuXuke;
    @BindView(R.id.ll_zhongxu_xukezheng)
    RelativeLayout llZhongxuXukezheng;
    @BindView(R.id.et_apply_number)
    EditText etApplyNumber;
    @BindView(R.id.et_apply_type)
    EditText etApplyType;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.ll_anim_animtype)
    RelativeLayout llAnimAnimtype;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.et_donator_anim_kind)
    EditText etDonatorAnimKind;
    @BindView(R.id.ll_donator_anim)
    LinearLayout llDonatorAnim;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.et_anim_product_kind)
    EditText etAnimProductKind;
    @BindView(R.id.ll_anim_product)
    LinearLayout llAnimProduct;
    @BindView(R.id.ll_anim_kand)
    LinearLayout llAnimKand;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.et_purpose)
    EditText etPurpose;
    @BindView(R.id.ll_use)
    RelativeLayout llUse;
    @BindView(R.id.et_source)
    EditText etSource;
    @BindView(R.id.ll_laiyuan)
    RelativeLayout llLaiyuan;
    @BindView(R.id.ll_yongtu_laiyuan)
    LinearLayout llYongtuLaiyuan;
    @BindView(R.id.tv_source_title)
    TextView tvSourceTitle;
    @BindView(R.id.et_source_extra)
    EditText etSourceExtra;
    @BindView(R.id.lin_source_extra)
    RelativeLayout linSourceExtra;
    @BindView(R.id.et_start_type)
    EditText etStartType;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_start_village)
    EditText etStartVillage;
    @BindView(R.id.et_start_other)
    EditText etStartOther;
    @BindView(R.id.et_end_type)
    EditText etEndType;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.et_end_village)
    EditText etEndVillage;
    @BindView(R.id.et_end_other)
    EditText etEndOther;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(R.id.tv_shipment_time)
    TextView tvShipmentTime;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.rb_result_yes)
    RadioButton rbResultYes;
    @BindView(R.id.rb_result_no)
    RadioButton rbResultNo;
    @BindView(R.id.rg_accept_chekt)
    RadioGroup rgAcceptChekt;
    //    @BindView(R.id.spinner_year)
//    Spinner spinnerYear;
//    @BindView(R.id.spinner_month)
//    Spinner spinnerMonth;
//    @BindView(R.id.spinner_day)
//    Spinner spinnerDay;
    @BindView(R.id.et_place)
    EditText etPlace;
    @BindView(R.id.ll_accept)
    LinearLayout llAccept;
    @BindView(R.id.et_reason)
    EditText etReason;
    @BindView(R.id.ll_unaccept)
    LinearLayout llUnaccept;
    @BindView(R.id.et_operator_name)
    EditText etOperatorName;
    @BindView(R.id.et_operator_phone)
    EditText etOperatorPhone;
    //    @BindView(R.id.spinner_year2)
//    Spinner spinnerYear2;
//    @BindView(R.id.spinner_month2)
//    Spinner spinnerMonth2;
//    @BindView(R.id.spinner_day2)
//    Spinner spinnerDay2;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.img_takephoto_approve)
    ImageView imgTakephotoApprove;
    @BindView(R.id.photo_view_group_photo)
    FullPhotoView photoViewIcId;
    @BindView(R.id.lin_take_photo)
    LinearLayout linTakePhoto;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    FullPhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;

    @BindView(R.id.tv_cl_date)
    TextView tv_cl_date;//处理时间
    @BindView(R.id.tv_np_date)
    TextView tv_np_date;//拟派时间

    private AnimProcessListBean.DataListBean dataListBean;
    String mTime;
    TimePresenter timePresenter;
    private String printId;

    private List<String> endDayList;

    private TimeSelectDialog npDialog;//拟派时间选择
    private TimeSelectDialog clDialog;//处理时间选择

    private String[] npTime = new String[4];//拟派时间
    private String[] clTime = new String[4];//处理时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_process_list_detil);
        ButterKnife.bind(this);
    }

    @Override
    protected AnimProcessListDetilActivityPresenter createPresenter() {
        return new AnimProcessListDetilActivityPresenter(this);
    }

    @Override
    public void bindData() {
        timePresenter = new TimePresenter(this);
        setTitle("产地检疫申报单处理");
        Intent intent = this.getIntent();
        dataListBean = (AnimProcessListBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);

        //放大功能
//        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
//        photoViewUtils.initPhotoView(photoViewIcId);
        setTime();
        bindDataLivestock();
        if (dataListBean.getYx().equals("有")) {
            linTakePhoto.setVisibility(View.VISIBLE);
        } else {
            linTakePhoto.setVisibility(View.GONE);
        }
        if (dataListBean.getImg() != null && !TextUtils.isEmpty(dataListBean.getImg())) {
            Glide.with(this).load(Constance.IMAGE_PATHH + dataListBean.getImg()).into(photoViewIcId);
        }

        rbYes.setText(dataListBean.getYx());//有效《跨省引进乳用种用动物检疫审批表》
        etApplyNumber.setText(dataListBean.getQDWNumber());//申报单编号
        etApplyType.setText(dataListBean.getFqSbType());//申报类型
        etUserName.setText(dataListBean.getQDWCargoOwner());//申报人姓名
        etUserPhone.setText(dataListBean.getQDWPhone());//申报人电话
        etAnimType.setText(dataListBean.getQDWXuZhongZ());//动物种类
        etAnimCount.setText(dataListBean.getQDWQuantity() + "");//动物数量
        tvDanwei.setText(dataListBean.getQDWDanWei());
        etPurpose.setText(dataListBean.getQDWYongTu());//用途
        etSource.setText(dataListBean.getQDWLaiYuan());//来源
        if (dataListBean.getQDWLaiYuan().contains("人工饲养")) {//来源
            linSourceExtra.setVisibility(View.VISIBLE);
            etSourceExtra.setText(dataListBean.getXKZH());
        } else if (dataListBean.getQDWLaiYuan().contains("合法捕获")) {
            linSourceExtra.setVisibility(View.VISIBLE);
            tvSourceTitle.setText("野生动物捕捉(猎捕)许可证号");
            etSourceExtra.setText(dataListBean.getXKZH());
        }

//        etOperatorName.setText(fuName1);//经办人姓名
        etOperatorName.setText(getPresenter().getUser().getFUNAME());//经办人姓名
//        etOperatorPhone.setText(fuPhone1);//经办人电话
        etOperatorPhone.setText(getPresenter().getUser().getFUPHONE());//经办人电话

        rbZhongxuXuke.setText(dataListBean.getFqZxqscjyxkz());//有效《种畜禽生产经营许可证》
        etStartType.setText(dataListBean.getQDWTypeQy());//起运地类别
        etStartPlace.setText(dataListBean.getQDWShengQy() + dataListBean.getQDWShiQy() + dataListBean.getQDWXianQy());//起运地址省、市
        etStartVillage.setText(dataListBean.getQDWXiangQy());//起运地址乡镇
        etStartOther.setText(dataListBean.getQDWCunQy());//起运地址乡村
        etEndType.setText(dataListBean.getQDWTypeDd());//到达地址类别
        etEndPlace.setText(dataListBean.getQDWShengDd() + dataListBean.getQDWShiDd() + dataListBean.getQDWXianDd());//到达地址省市
        etEndVillage.setText(dataListBean.getQDWXiangDd());//到达地址县镇
        etEndOther.setText(dataListBean.getQDWCunDd());//到达地址乡村
        tvInspectionTime.setText(dataListBean.getFScTime() + " " + dataListBean.getDr() + "时");//报检时间
        tvShipmentTime.setText(dataListBean.getDateQy());//启运时间
        tvErbiao.setText(dataListBean.getQDWErBiaoHao());//耳标号

        if (rbResultYes.isChecked()) {//受理结果
            dataListBean.setQDWAccepted("受理");
        } else {
            dataListBean.setQDWAccepted("不受理");
        }
        if ("不受理".equals(dataListBean.getQDWAccepted())) {
            rgAcceptChekt.check(R.id.rb_result_no);
        } else {
            rgAcceptChekt.check(R.id.rb_result_yes);
        }
        if (!"1900-01-01".equals(dataListBean.getCLRQ())) {
            //检疫时间
//            etStart.setText(dataListBean.getCLRQ());
            mTime = dataListBean.getCLRQ();
        } else {
            timePresenter.getTime();
        }
        if (!"1900-01-01".equals(dataListBean.getDateNpy())) {
            //处理时间
//            etDealWithTime.setText(dataListBean.getDateNpy());
        }
        if (!isEmpty(dataListBean.getQDWAttn())) {
            //经办人
            etOperatorName.setText(dataListBean.getQDWAttn());
        }
        if (!isEmpty(dataListBean.getQDWJBRDianHua())) {
            //经办人电话
            etOperatorPhone.setText(dataListBean.getQDWJBRDianHua());
        }
        if (!isEmpty(dataListBean.getQDWAddress())) {
            //检疫地点
            etPlace.setText(dataListBean.getQDWAddress());
        }
        if (!isEmpty(dataListBean.getQDWLiYou())) {
            //不受理理由
            etReason.setText(etReason.getText().toString());
        }
    }

    /**
     * 设置年月日
     *
     * @return
     */

    private void setTime() {
        npDialog = new TimeSelectDialog(this, "选择派员时间");
        npDialog.initTime();
        npDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                tv_np_date.setText(year + "/" + month + "/" + day);
                npTime[0] = year;
                npTime[1] = month;
                npTime[2] = day;
                npTime[3] = hour;
            }
        });
        npTime[0] = npDialog.getmCurrentYear() + "";
        npTime[1] = npDialog.getmCurrentMonth() + "";
        npTime[2] = npDialog.getmCurrentDay() + "";
        npTime[3] = npDialog.getmCurrentHour() + "";

        tv_np_date.setText(npTime[0] + "/" + npTime[1] + "/" + npTime[2]);

        clDialog = new TimeSelectDialog(this, "选择处理时间");
        clDialog.initTime();
        clDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                tv_cl_date.setText(year + "/" + month + "/" + day);
                clTime[0] = year;
                clTime[1] = month;
                clTime[2] = day;
                clTime[3] = hour;
            }
        });
        clTime[0] = clDialog.getmCurrentYear() + "";
        clTime[1] = clDialog.getmCurrentMonth() + "";
        clTime[2] = clDialog.getmCurrentDay() + "";
        clTime[3] = clDialog.getmCurrentHour() + "";

        tv_cl_date.setText(clTime[0] + "/" + clTime[1] + "/" + clTime[2]);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean check() {
        if (isEmpty(getString(etOperatorName))) {
//            etOperatorName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etOperatorName.requestFocus();
            showToast("经办人不能为空");
            return false;
        }
        if (isEmpty(getString(etOperatorPhone))) {
            showToast("经办人电话不能为空");
//            etOperatorPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etOperatorPhone.requestFocus();
            return false;
        }
        if (getString(tv_np_date).equals("请选择")) {
            showToast("请选择拟派员时间");
//            etOperatorPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            return false;
        }
        if (rbResultYes.isChecked()) {
            if (isEmpty(getString(etPlace))) {
                showToast("检疫地不能为空");
//                etPlace.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
                etPlace.requestFocus();
                return false;
            }
        } else {
            if (isEmpty(getString(etReason))) {
                showToast("不受理理由不能为空");
//                etReason.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
                etReason.requestFocus();
                return false;
            }
        }
        if (getString(tv_cl_date).equals("请选择")) {
            showToast("请选择处理时间");
//            etOperatorPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
    }


    private AnimProcessListBean.DataListBean getData() {
        if (rbResultYes.isChecked()) {//受理结果
            dataListBean.setQDWAccepted("受理");
        } else {
            dataListBean.setQDWAccepted("不受理");
        }
        dataListBean.setQDWAddress(etPlace.getText().toString());//检疫地点
        dataListBean.setQDWAttn(etOperatorName.getText().toString());//经办人姓名
        dataListBean.setQDWJBRDianHua(etOperatorPhone.getText().toString());//经办人电话
        dataListBean.setQDWLiYou(etReason.getText().toString());//不受理理由
        // dataListBean.setDateNpy(etDealWithTime.getText().toString());
        dataListBean.setCLRQ(npTime[0] + "/" + npTime[1] + "/" +
                npTime[2]);//检疫时间
        dataListBean.setDateNpy(clTime[0] + "/" + clTime[1] + "/"
                + clTime[2]);//处理时间
        dataListBean.setIsPrant("已保存");
        dataListBean.setUserId(getPresenter().getUserId());//用户ID
//        "已打印".equals(dataListBean)
//        if (!) ;
        return dataListBean;
    }


    private void bindDataLivestock() {
        rgAcceptChekt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_result_yes) {
                    llAccept.setVisibility(View.VISIBLE);
                    llUnaccept.setVisibility(View.GONE);
                    etPlace.setText("现场");
                } else {
                    llAccept.setVisibility(View.GONE);
                    llUnaccept.setVisibility(View.VISIBLE);
                    etPlace.setText("");
                }
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

        if (dataListBean.getFqSbType().contains("繁殖材料")) {//申报类型
            etApplyType.setText("繁殖材料");
            llDonatorAnim.setVisibility(View.VISIBLE);//供体动物种类
            llAnimProduct.setVisibility(View.VISIBLE);//动物产品种类
            etDonatorAnimKind.setText(dataListBean.getQDWXuZhongZ());
            etAnimProductKind.setText(dataListBean.getFqProduct());
            llYongtuLaiyuan.setVisibility(View.GONE);//用途来源
            llZhongxuXukezheng.setVisibility(View.VISIBLE);//有效《种畜禽生产经营许可证》
            llAnimAnimtype.setVisibility(View.GONE);//动物种类
//            if (dataListBean.getFqZxqscjyxkz().contains("无")) {
//
//                llLaiyuan.setVisibility(View.GONE);
//                llUse.setVisibility(View.GONE);
//            }
        } else {
            etApplyType.setText("动物");
            llDonatorAnim.setVisibility(View.GONE);//供体动物种类
            llAnimProduct.setVisibility(View.GONE);//动物产品种类
            llZhongxuXukezheng.setVisibility(View.GONE);//有效《种畜禽生产经营许可证》
            llAnimAnimtype.setVisibility(View.VISIBLE);//动物种类
            llYongtuLaiyuan.setVisibility(View.VISIBLE);//用途来源
        }

    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AnimProcessListDetilActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(tempFileName, PhotoUtils.DEFAULT_WIDTH, PhotoUtils.DEFAULT_HEIGHT);
//        switch (requestCode) {
//            case R.id.img_takephoto_ic_id:
//                photoViewIcId.setImageBitmap(bitmap);
//                break;
//        }
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void bindListener() {
        tv_np_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npDialog.show();
            }
        });

        tv_cl_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clDialog.show();
            }
        });

        getAdd().setOnClickListener(v -> startActivityForResult(new Intent(this, AnimApplyListActivity.class), Constance.ACTIVITY_REQUEST_CODE));
        getBack().setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });
    }


    @Override
    public void setTime(String time, long longTime) {
        mTime = time.substring(0, 11);
        dataListBean.setCLRQ(mTime);
        dataListBean.setDateNpy(mTime);
//        etStart.setText(mTime);
//        etDealWithTime.setText(mTime);
    }

    @Override
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        if (dataListBean.getFqSbType().contains("动物")) {
            Intent intent = new Intent(AnimProcessListDetilActivity.this, PrintAcitivty.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_ANIM_APPLY_DETIL);
            bundle.putString(Constance.START_ACTIVITY_PRINTID, printId);
            bundle.putSerializable(Constance.START_ACTIVITY_DATA, dataListBean);
            intent.putExtras(bundle);
            startActivityForResult(intent, 100);
        } else if (dataListBean.getFqSbType().contains("繁殖材料")) {
            Intent intent = new Intent(AnimProcessListDetilActivity.this, PrintAcitivty.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_APPLY_DETIL);
            bundle.putString(Constance.START_ACTIVITY_PRINTID, printId);
            bundle.putSerializable(Constance.START_ACTIVITY_DATA, dataListBean);
            intent.putExtras(bundle);
            startActivityForResult(intent, 100);
        }
    }

    @Override
    public void getToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getPrintId(String s) {
        this.printId = s + "";
    }

}
