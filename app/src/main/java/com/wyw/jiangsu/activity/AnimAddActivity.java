package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AdressBean;
import com.wyw.jiangsu.bean.Qua_QuarantineDeclarationCD;
import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.db.LocalGreenDao;
import com.wyw.jiangsu.db.Unit;
import com.wyw.jiangsu.dialog.AddressDialog;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.ErBiaoDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.IAnimAddActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.AnimAddActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.utils.OtherUtil;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

import static com.wyw.jiangsu.R.id.spinner_end_type;
import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.getQuarantineDir;

/**
 * 动物产地检疫申报
 */
public class AnimAddActivity extends MVPActivity<AnimAddActivityPresenter> implements IAnimAddActivity, ITime, PhotoViewModel.OnClickListener {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rb_apply_anim)
    RadioButton rbApplyAnim;
    @BindView(R.id.rb_apply_material)
    RadioButton rbApplyMaterial;
    @BindView(R.id.rg_apply_type)
    RadioGroup rgApplyType;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rb_permission_yes)
    RadioButton rbPermissionYes;
    @BindView(R.id.rb_permission_no)
    RadioButton rbPermissionNo;
    @BindView(R.id.lin_permission)
    LinearLayout linPermission;
    @BindView(R.id.img_takephoto_approve)
    ImageView imgTakephotoApprove;
    @BindView(R.id.photo_view_approve)
    ImageView photoViewApprove;
    @BindView(R.id.lin_take_photo)
    LinearLayout linTakePhoto;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.tv_5)
    TextView tv_5;
    @BindView(R.id.tv_anim_one_1)
    TextView tv_anim_one_1;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.spinner_unit)
    TextView spinnerUnit;
    @BindView(R.id.spinner_product_type)
    TextView spinnerProductType;
    @BindView(R.id.lin_product_type)
    LinearLayout linProductType;
    @BindView(R.id.spinner_purpose)
    TextView spinnerPurpose;
    @BindView(R.id.spinner_source)
    TextView spinnerSource;
    @BindView(R.id.lin_source)
    RelativeLayout linSource;
    @BindView(R.id.et_source_extra)
    EditText etSourceExtra;
    @BindView(R.id.lin_source_extra)
    RelativeLayout linSourceExtra;
    @BindView(R.id.liner_purpose)
    LinearLayout linerPurpose;
    @BindView(R.id.spinner_start_type)
    TextView spinnerStartType;
    @BindView(R.id.et_start_village)
    EditText etStartVillage;
    @BindView(R.id.et_start_other)
    EditText etStartOther;
    @BindView(spinner_end_type)
    TextView spinnerEndType;
    @BindView(R.id.et_end_village)
    EditText etEndVillage;
    @BindView(R.id.et_end_other)
    EditText etEndOther;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.bt_erbiao)
    Button btErbiao;
    @BindView(R.id.et_user_signature)
    EditText etUserSignature;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.SV)
    ScrollView SV;
    @BindView(R.id.et_shenbaodan_bianhao)
    EditText etShenbaodanBianhao;
    @BindView(R.id.ll_animaladd_yongtu)
    LinearLayout llAnimaladdYongtu;
    @BindView(R.id.iv_none)
    ImageView iv_none;
    @BindView(R.id.tv_yzc_select)
    TextView tv_yzc_select;
    @BindView(R.id.iv_unit_show)
    ImageView iv_unit_show;
    @BindView(R.id.tv_end_place)
    TextView tv_end_place;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;//起运时间选择



    private List<Unit> startCity, startCounty, endProvince, endCity, endCounty;
    private Unit startProvince;
    ErBiaoDialog dialog;
    private RegisteAddressBean registeBean;
    TimePresenter timePresenter;

    //上传实体类
    private Qua_QuarantineDeclarationCD bean = new Qua_QuarantineDeclarationCD();
    private int tid;
    private String hour;
    private String riqi;
    private String fsunitustrid;
    private String fsunitname = "范水镇";
    private List<String> list3;
    private List<String> list2;
    private String fudwtype;
    PhotoViewModel ImageViewModel;
    private String currentPhotoFile;
    private String uuid;
    private String etStartVillageText;
    private boolean isDestoryed;
    private DataSelectDialog animSelectDialog;//动物类型选择
    private DataSelectDialog unitSelectDialog;//单位选择
    private DataSelectDialog unitSelectDialog1;//单位选择1
    private DataSelectDialog purposeSelectDialog;//用途选择
    private DataSelectDialog sourceSelectDialog;//来源选择
    private DataSelectDialog startTypeDialog;//起运地类别选择
    private DataSelectDialog endTypeDialog;//到达地类别选择
    private DataSelectDialog animProductDialog;//动物产品类别选择
    private AddressDialog startAddressDialog;//起运地址选择
    private AddressDialog endAddressDialog;//终止地址选择
    private TimeSelectDialog timeSelectDialog;//起运时间选择

    private String[] startAddress = new String[3];//起始地址
    private String[] endAddress = new String[3];//终止地址
    private String[] qyTime = new String[4];//起运时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected AnimAddActivityPresenter createPresenter() {
        return new AnimAddActivityPresenter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //加载本地签名图片
        for (File file : PhotoUtils.getQuarantineDir().listFiles()) {
            if (file.getName().contains(uuid + "_A1")) {
                Picasso.with(this).load(Uri.fromFile(new File(getQuarantineDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(photoViewApprove);
            }
        }
        //对拍照控件 --> 加载照片,恢复拍照功能
        String nameManager1 = uuid + "_A1";
        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewApprove, REQUEST_CODE_START, nameManager1, getQuarantineDir(), this);
        Log.e("etStartVillage", "isDestoryed" + isDestoryed);
        if (isDestoryed) {
            etStartVillage.setText(etStartVillageText);
            Log.e("etStartVillage", "etStartVillage.onResume" + etStartVillageText);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
        uuid = savedInstanceState.getString("uuid");
        isDestoryed = true;
        etStartVillageText = savedInstanceState.getString("etStartVillage");
        Log.e("etStartVillage", "etStartVillage.onRestoreInstanceState" + etStartVillageText);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("uuid", uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        outState.putString("etStartVillage", etStartVillage.getText().toString());
        Log.e("etStartVillage", "etStartVillage.getText().toString()" + etStartVillage.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindData() {
        setTitle("产地检疫申报单");
        uuid = UUID.randomUUID().toString();
        getPresenter().getBianhao();
        getPresenter().getAdress();
        fudwtype = getPresenter().getUser().getFUDWTYPE();
        fsunitustrid = getPresenter().getUser().getFSUNITUSTRID();
//        fsunitname = getPresenter().getUser().getFSUNITNAME();
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        /*photoViewUtils.initPhotoView(photoViewApprove, () -> {
            tempFileName = PhotoUtils.takePictureQuarantine(this, PhotoUtils.REQUEST_CODE_START, "A1");
        });*/
        //判断是否可以点击
        if ("dwyzc".equals(fudwtype)) {
            etStartVillage.setEnabled(false);
            etStartOther.setEnabled(false);
        } else {
            etStartVillage.setEnabled(true);
            etStartOther.setEnabled(true);
        }


        //刚进来就有值
        etUserName.setText(getPresenter().getUser().getFUNAME());   //申报人姓名
        etUserPhone.setText(getPresenter().getUser().getFUPHONE()); //联系电话
        etUserSignature.setText(getPresenter().getUser().getFUNAME());//申报人签字
        if (getPresenter().getUser().getFUDWTYPE().equals("dwyzc")) {     //如果类型是动物养殖场，养殖场就有值
            etStartOther.setText(getPresenter().getUser().getFUSEENAME());
        }

        animSelectDialog = new DataSelectDialog(this);
        animSelectDialog.setTitle("选择动物种类");
        animSelectDialog.setDatas(getResources().getStringArray(R.array.anim_type));

        unitSelectDialog = new DataSelectDialog(this);
        unitSelectDialog.setTitle("选择单位");
        unitSelectDialog.setDatas(unit);

        unitSelectDialog1 = new DataSelectDialog(this);
        unitSelectDialog1.setTitle("选择单位");
        unitSelectDialog1.setDatas(getResources().getStringArray(R.array.anim_unit_type));

        purposeSelectDialog = new DataSelectDialog(this);
        purposeSelectDialog.setTitle("选择用途");
        purposeSelectDialog.setDatas(getResources().getStringArray(R.array.useage));


        sourceSelectDialog = new DataSelectDialog(this);
        sourceSelectDialog.setTitle("选择来源");
        sourceSelectDialog.setDatas(getResources().getStringArray(R.array.source));


        startTypeDialog = new DataSelectDialog(this);
        startTypeDialog.setTitle("选择起运地类别");
        startTypeDialog.setDatas(getResources().getStringArray(R.array.start_place_type));

        endTypeDialog = new DataSelectDialog(this);
        endTypeDialog.setTitle("选择到达地类别");
        endTypeDialog.setDatas(getResources().getStringArray(R.array.end_place_type));

        animProductDialog = new DataSelectDialog(this);
        animProductDialog.setTitle("选择动物产品种类");
        animProductDialog.setDatas(getResources().getStringArray(R.array.anim_product_type));


        startAddressDialog = new AddressDialog(this);
        startAddressDialog.setOnDecideListener(new AddressDialog.IOnDecideListener() {
            @Override
            public void onCancel(View v) {
                startAddressDialog.dismiss();
            }

            @Override
            public void onSure(View v, String province, String city, String district) {
                startAddress[0] = province;
                startAddress[1] = city;
                startAddress[2] = district;
                tv_yzc_select.setText(province + city + district);
                startAddressDialog.dismiss();
            }
        });


        endAddressDialog = new AddressDialog(this);
        endAddressDialog.setOnDecideListener(new AddressDialog.IOnDecideListener() {
            @Override
            public void onCancel(View v) {
                endAddressDialog.dismiss();
            }

            @Override
            public void onSure(View v, String province, String city, String district) {
                endAddress[0] = province;
                endAddress[1] = city;
                endAddress[2] = district;
                tv_end_place.setText(province + city + district);
                endAddressDialog.dismiss();
            }
        });


        timeSelectDialog = new TimeSelectDialog(this, "选择起运时间");
        timeSelectDialog.initTime();
        timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                tv_start_time.setText(year + "-" + month + "-" + day + " " + hour + "时");
                qyTime[0] = year;
                qyTime[1] = month;
                qyTime[2] = day;
                qyTime[3] = hour;
            }
        });
        qyTime[0] = timeSelectDialog.getmCurrentYear()+"";
        qyTime[1] = timeSelectDialog.getmCurrentMonth()+"";
        qyTime[2] = timeSelectDialog.getmCurrentDay()+"";
        qyTime[3] = timeSelectDialog.getmCurrentHour()+"";

        tv_start_time.setText(timeSelectDialog.getNowTime());


        setUnit("");

        setPlace();
        dialog = new ErBiaoDialog(this, "0", text -> tvErbiao.setText(text));

    }

    /**
     * 设置地点
     */
    private void setPlace() {
        startProvince = LocalGreenDao.getInstance().queryProvince(1435);
        endProvince = LocalGreenDao.getInstance().queryProvinces();

        startCity = LocalGreenDao.getInstance().queryCitys(startProvince.getTId());
        endCity = LocalGreenDao.getInstance().queryCitys(startProvince.getTId());

        startCounty = LocalGreenDao.getInstance().queryCitysOrCountries(startCity.get(0).getTId());
        endCounty = LocalGreenDao.getInstance().queryCitysOrCountries(endCity.get(0).getTId());

        //设置启运地省市县
//        spinnerStartProvince.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                new String[]{startProvince.getuName()}));
//
//        spinnerStartCity.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                getList(startCity)));

//        spinnerStartCounty.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                getList(startCounty)));
    }

    private List<String> getList(List<Unit> list) {

        List<String> list4 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list4.add(list.get(i).getuName());
        }
        return list4;
    }

    @Override
    public void bindListener() {
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_yes && rbApplyAnim.isChecked()) {
                //显示图片拍照
                linTakePhoto.setVisibility(View.VISIBLE);
            } else {
                linTakePhoto.setVisibility(View.GONE);
                //删除本地图片
                PhotoUtils.deletePic();
            }
        });

        rgApplyType.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.rb_apply_anim) {
                if (rbYes.isChecked()) {
                    linTakePhoto.setVisibility(View.VISIBLE);
                } else {
                    linTakePhoto.setVisibility(View.GONE);
                }
                tv_5.setText("动物种类");
                linerPurpose.setVisibility(View.VISIBLE);
                llAnimaladdYongtu.setVisibility(View.VISIBLE);
                linSource.setVisibility(View.VISIBLE);
                linProductType.setVisibility(View.GONE);
                linPermission.setVisibility(View.GONE);
                setUnit(tv_anim_one_1.getText().toString());
                if (tv_anim_one_1.getText().toString().equals("其他")) {
                    iv_unit_show.setBackgroundResource(R.drawable.drop_down_drawable);
                }else{
                    iv_unit_show.setBackgroundColor(Color.WHITE);
                }
                spinnerUnit.setBackgroundColor(Color.WHITE);
            } else {
                linTakePhoto.setVisibility(View.GONE);
                tv_5.setText("供体动物种类");
                linerPurpose.setVisibility(View.GONE);
                llAnimaladdYongtu.setVisibility(View.GONE);
                linSource.setVisibility(View.GONE);
                linProductType.setVisibility(View.VISIBLE);
                linPermission.setVisibility(View.VISIBLE);
                spinnerUnit.setText("枚");
                if (spinnerProductType.getText().toString().equals("精液")) {
                    iv_unit_show.setBackgroundResource(R.drawable.drop_down_drawable);
                }else{
                    iv_unit_show.setBackgroundColor(Color.WHITE);
                }

            }
        });

        unitSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerUnit.setText(data);
            }

            @Override
            public void cancel() {

            }
        });
        unitSelectDialog1.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerUnit.setText(data);

            }

            @Override
            public void cancel() {

            }
        });


        spinnerUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_anim_one_1.getText().toString().equals("其他") && rgApplyType.getCheckedRadioButtonId() == R.id.rb_apply_anim) {
                    unitSelectDialog.show();
                } else if (spinnerProductType.getText().toString().equals("精液")
                        && rgApplyType.getCheckedRadioButtonId() != R.id.rb_apply_anim) {
                    unitSelectDialog1.show();
                }
            }
        });

        iv_unit_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_anim_one_1.getText().toString().equals("其他") && rgApplyType.getCheckedRadioButtonId() == R.id.rb_apply_anim) {
                    unitSelectDialog.show();
                } else if (spinnerProductType.getText().toString().equals("精液") &&
                        rgApplyType.getCheckedRadioButtonId() != R.id.rb_apply_anim) {
                    unitSelectDialog1.show();
                }
            }
        });


        animSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                setUnit(data);
                tv_anim_one_1.setText(data);
                if (data.equals("其他") && rgApplyType.getCheckedRadioButtonId() == R.id.rb_apply_anim) {
                    iv_unit_show.setBackgroundResource(R.drawable.drop_down_drawable);
                } else if (rgApplyType.getCheckedRadioButtonId() == R.id.rb_apply_anim) {
                    iv_unit_show.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void cancel() {

            }
        });

        tv_anim_one_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animSelectDialog.show();
            }
        });

        purposeSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerPurpose.setText(data);

            }

            @Override
            public void cancel() {

            }
        });

        spinnerPurpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purposeSelectDialog.show();
            }
        });


        startTypeDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerStartType.setText(data);
                switch (data) {
                    case "养殖场":
                        etStartOther.setHint("请输入养殖场名称");
                        break;
                    case "交易市场":
                        etStartOther.setHint("请输入交易市场名称");
                        break;
                    case "散养户":
                        etStartOther.setHint("请输入散养户名称");
                        break;
                    default:
                        etStartOther.setHint("");
                        break;
                }

            }

            @Override
            public void cancel() {

            }
        });

        spinnerStartType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTypeDialog.show();
            }
        });

        tv_yzc_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddressDialog.show();
            }
        });

        tv_end_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endAddressDialog.show();
            }
        });


        endTypeDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerEndType.setText(data);
                switch (data) {
                    case "养殖场":
                        etEndOther.setHint("请输入养殖场名称");
                        break;
                    case "屠宰场":
                        etEndOther.setHint("请输入屠宰场名称");
                        break;
                    case "交易市场":
                        etEndOther.setHint("请输入交易市场名称");
                        break;
                    case "散养户":
                        etEndOther.setHint("请输入散养户名称");
                        break;
                    default:
                        etEndOther.setHint("");
                        break;
                }
            }

            @Override
            public void cancel() {

            }
        });
        spinnerEndType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTypeDialog.show();
            }
        });

        btErbiao.setOnClickListener(v -> new ErBiaoDialog(this, etAnimCount.getText().toString(), text -> tvErbiao.setText(text)).show());

        sourceSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                int position = sourceSelectDialog.getNowIndex();
                spinnerSource.setText(data);
                if (position == 1) {
                    linSourceExtra.setVisibility(View.VISIBLE);
                } else if (position == 2) {
                    linSourceExtra.setVisibility(View.VISIBLE);
                    etSourceExtra.setHint("野生动物捕捉（猎捕）许可证号");
                } else if (position == 0) {
                    linSourceExtra.setVisibility(View.GONE);
                }
            }

            @Override
            public void cancel() {

            }
        });

        spinnerSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceSelectDialog.show();
            }
        });


        animProductDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerProductType.setText(data);
                /**
                 *  <item>种蛋</item>
                 <item>胚胎</item>
                 <item>精液</item>
                 */
                switch (data) {
                    case "种蛋":
                    case "胚胎":
                        spinnerUnit.setText("枚");
                        iv_unit_show.setBackgroundColor(Color.WHITE);
                        break;
                    case "精液":
                        /**
                         * <item>支</item>
                         <item>剂</item>
                         */
                        spinnerUnit.setText("支");
                        iv_unit_show.setBackgroundResource(R.drawable.drop_down_drawable);
//                        R.array.anim_unit_type
                        break;
                }
            }

            @Override
            public void cancel() {

            }
        });

        spinnerProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animProductDialog.show();
            }
        });

        btErbiao.setOnClickListener(v -> {
            if (isEmpty(getString(etAnimCount)) || "0".equals(getString(etAnimCount))) {
                showToast("动物数量必填");
                etAnimCount.requestFocus();
            } else {
                dialog.show(getString(etAnimCount));
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
        tv_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectDialog.show();
            }
        });

    }


    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AnimAddActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().uploadNew(getData());
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
     * 需要上传的东西
     *
     * @return
     */
    private Qua_QuarantineDeclarationCD getData() {
        //Qua_QuarantineDeclarationCD bean = new Qua_QuarantineDeclarationCD();
        bean = new Qua_QuarantineDeclarationCD();
        if (rbApplyAnim.isChecked()) {
            bean.setQDWLaiYuan(spinnerSource.getText().toString());           //来源
            bean.setQDWYongTu(spinnerPurpose.getText().toString());           //用途
            if (spinnerSource.getText().toString().contains("人工饲养")) {
                bean.setXKZH(etSourceExtra.getText().toString());
            } else if (spinnerSource.getText().toString().contains("合法捕获")) {
                bean.setXKZH(etSourceExtra.getText().toString());
            }

        } else {
            //不一样的地方
            bean.setFqZxqscjyxkz(rbPermissionYes.isChecked() ? "是" : "否");    // 有效《种畜禽生产经营许可证》
            bean.setFqProduct(spinnerProductType.getText().toString());   // 动物产品种类

        }
//        bean.setLen(PhotoUtils.getQuarantineDir().listFiles().length + "");   //图片

        bean.setFqSbType(rbApplyAnim.isChecked() ? "动物" : "繁殖材料");  //申报类型
        bean.setYx(rbYes.isChecked() ? "有" : "无");  //有效《跨省引进乳用种用动物检疫审批表》
        //审查表   photoViewApprove

        bean.setQDWCargoOwner(etUserName.getText().toString());   //申报人姓名
        bean.setQDWPhone(etUserPhone.getText().toString());     //联系电话
        bean.setQDWXuZhong(tv_anim_one_1.getText().toString());         //动物种类
        bean.setQDWXuZhongZ(tv_anim_one_1.getText().toString());        //动物种类总

        String count = etAnimCount.getText().toString();        //数量
        bean.setQDWQuantity(Integer.parseInt(count));
        //单位
        bean.setQDWDanWei(spinnerUnit.getText().toString());
        bean.setQDWTypeQy(spinnerStartType.getText().toString());         //起运地类别

        bean.setQDWShengQy(startAddress[0]);
        bean.setQDWShiQy(startAddress[1]);
        bean.setQDWXianQy(startAddress[2]);       //养殖场地址  省 市 县 乡镇  村

        bean.setQDWXiangQy(OtherUtil.toString(etStartVillage));
        bean.setQDWCunQy(OtherUtil.toString(etStartOther));

        bean.setQDAddQy(startAddress[0] + startAddress[1] +
                startAddress[2] + etStartVillage.getText().toString() + etStartOther.getText().toString()); //起运地全称

        bean.setQDWTypeDd(spinnerEndType.getText().toString());           //到达地类别

        bean.setQDWShengDd(endAddress[0]);
        bean.setQDWShiDd(endAddress[1]);
        bean.setQDWXianDd(endAddress[2]);        //到达地地址  省 市 县 乡镇  村

        bean.setQDWXiangDd(etEndVillage.getText().toString());
        bean.setQDWCunDd(OtherUtil.toString(etEndOther));

        bean.setQDWAddDd(endAddress[0] + endAddress[1] +
                endAddress[2] + etEndVillage.getText().toString() + etEndOther.getText().toString());   //到达地全称

        bean.setDateQy(qyTime[0] + "-" + qyTime[1] + "-" +
                qyTime[2]);     //启运时间      年  月  日
        bean.setDateQF(riqi);   //报检时间
        bean.setDr(hour);   //小时
        bean.setQDWErBiaoHao(OtherUtil.toString(tvErbiao));     //耳标录入
        bean.setGZ(etUserSignature.getText().toString());         //申报人签名
        bean.setIsPrant("已保存");
        bean.setQDWNumber(etShenbaodanBianhao.getText().toString());//申报单编号
        return bean;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean check() {

        if (isEmpty(getString(etUserName))) {
            showToast("申报人姓名不能为空");
            etUserName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etUserName.requestFocus();
            return false;
        }
        if (isEmpty(getString(etUserPhone))) {
            showToast("申报人电话不能为空");
            etUserPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etUserPhone.requestFocus();
            return false;
        }
        if ((tv_anim_one_1.getText().toString()).contains("请选择")) {
            showToast("请选择动物种类");
            animSelectDialog.setDatas(getResources().getStringArray(R.array.anim_type));
            tv_5.requestFocus();
//            spinnerAnimOne.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            return false;
        }
        if (isEmpty(getString(etAnimCount)) || TextUtils.equals(getString(etAnimCount), "0")) {
            showToast("动物数量不能为空或者0");
            etAnimCount.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etAnimCount.requestFocus();
            return false;
        }
        if ((TextUtils.equals(getString(tv_anim_one_1), "猪") ||
                TextUtils.equals(getString(tv_anim_one_1), "牛") ||
                TextUtils.equals(getString(tv_anim_one_1), "羊")) &&
                TextUtils.equals(getString(tvErbiao), "")) {
            showToast("动物种类是" + getString(tv_anim_one_1) + "时,必须输入耳标号");
            btErbiao.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            btErbiao.performClick();
            return false;
        }

        if ((TextUtils.equals(getString(spinnerSource), "合法捕获")
                || TextUtils.equals(getString(spinnerSource), "人工饲养")) &&
                isEmpty(getString(etSourceExtra))) {
            showToast("野生动物捕捉许可证不能为空");
            etSourceExtra.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etSourceExtra.performClick();
            return false;
        }

        if ((spinnerPurpose.getText().toString()).contains("请选择")&&rbApplyAnim.isChecked()==true) {
            showToast("请选择用途");
            return false;
        }
        if (rbApplyAnim.isChecked()&&(spinnerSource.getText().toString()).contains("请选择")) {
            showToast("请选择来源");
            return false;
        }

        if ((spinnerStartType.getText().toString()).contains("请选择")) {
            showToast("请选择起运地类别");
            return false;
        }
        if ((tv_yzc_select.getText().toString()).contains("请选择")) {
            showToast("请选择养殖场地址");
            return false;
        }
        if ((spinnerEndType.getText().toString()).contains("请选择")) {
            showToast("请选择到达地类别");
            return false;
        }

        if ((tv_end_place.getText().toString()).contains("请选择")) {
            showToast("请选择到达地");
            return false;
        }

        if (isEmpty(getString(tv_start_time)) || TextUtils.equals(getString(tv_start_time), "请选择")) {
            showToast("起运时间不能为空");
            return false;
        }


        return true;
    }

    private String[] unit = {"头", "只", "匹", "羽", "箱"};

    /**
     * 设置单位
     */
    public void setUnit(String animType) {
        if (rbApplyMaterial.isChecked()) {
            return;
        }
        switch (animType) {
            case "猪":
            case "牛":
            case "鹿":
            case "骡":
            case "驴":
            case "骆驼":
                spinnerUnit.setText(unit[0]);
                break;
            case "羊":
            case "犬":
            case "猫":
            case "兔":
                spinnerUnit.setText(unit[1]);

                break;
            case "马":
                spinnerUnit.setText(unit[2]);

                break;
            case "鸡":
            case "鸭":
            case "鹅":
            case "鹌鹑":
            case "鸽":
                spinnerUnit.setText(unit[3]);

                break;
            case "蜜蜂":
                spinnerUnit.setText(unit[4]);

                break;
            case "其他":
                spinnerUnit.setText(unit[0]);//其他默认为头
                break;
            default:
                spinnerUnit.setText(unit[0]);
                break;
        }
    }

    //    @OnClick({R.id.img_takephoto_approve})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_approve:
                currentPhotoFile = PhotoUtils.takePictureQuarantine(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //        Uri data1 = data.setData();
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                    photoViewApprove.setImageBitmap(bitmap);
                    iv_none.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public void uploadsuccess() {
        finish();
    }


    @Override
    public void bianhao(ShenbaoJiluBianhaoBean.DataBean dataListBean) {
        etShenbaodanBianhao.setText(dataListBean.getResult());//申报单编号
//        changePosition(dataListBean.getShi(), dataListBean.getXian());
        etStartVillage.setText(dataListBean.getXiang());
    }

    @Override
    public void setAddress(RegisteAddressBean bean) {
        this.registeBean = bean;
        List<AdressBean.DataListBean> dataList = bean.getDataList();
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        if (fsunitustrid.length() == 6) {
            for (int k = 0; k < dataList.size(); k++) {
                if (dataList.get(k).getFuStrId().equals(fsunitustrid)) {
                    String fuParent = dataList.get(k).getFuParent();
                    String fuName = dataList.get(k).getFuName();
                    list2.add(fuName);                                              //区名
                    list.add(fuParent);
                }
            }
            for (int m = 0; m < dataList.size(); m++) {
                if (dataList.get(m).getFStId().equals(list.get(0))) {
                    String fuName = dataList.get(m).getFuName();
                    list3.add(fuName);                                              //市名
                }
            }
        } else if (fsunitustrid.length() == 8) {
            for (int k = 0; k < dataList.size(); k++) {
                if (dataList.get(k).getFuStrId().equals(fsunitustrid)) {
                    String fuParent = dataList.get(k).getFuParent();
                    list.add(fuParent);
                }
            }
            for (int m = 0; m < dataList.size(); m++) {
                if (dataList.get(m).getFStId().equals(list.get(0))) {
                    String fuName = dataList.get(m).getFuName();
                    String fStId = dataList.get(m).getFuParent();
                    list1.add(fStId);
                    list3.add(fuName);                                          //区名
                }
            }

            for (int n = 0; n < dataList.size(); n++) {
                if (dataList.get(n).getFStId().equals(list1.get(0))) {
                    String fuName1 = dataList.get(n).getFuName();
                    list4.add(fuName1);                                         //市名
                }
            }
        }

        if (isDestoryed) {
            etStartVillage.setText(etStartVillageText);
        }

    }

    public List<AdressBean.DataListBean> getStreet1(String name) {
        return registeBean.getCountries().get(name);
    }

    public List<String> getStrings(List<AdressBean.DataListBean> list) {
        List<String> data = new ArrayList<>();
        for (AdressBean.DataListBean bean : list) {
            data.add(bean.getFuName());
        }
        return data;
    }

    public void changePosition(String cityTid, String countryTid) {
        for (int i = 0; i < startCity.size(); i++) {
            Unit unit = startCity.get(i);
            if (unit.getTId().equals(cityTid)) {
                //设置adapter
                List<Unit> queryCitys = LocalGreenDao.getInstance().queryCitysOrCountries(unit.getTId());
                int selection = 0;
                for (int j = 0; j < queryCitys.size(); j++) {
                    Unit unitCountry = queryCitys.get(i);
                    if (unitCountry.getTId().equals(countryTid)) {
                        selection = j;
                        break;
                    }
                }
                break;
            }
        }

    }

    @Override
    public void setTime(String time, long longTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        riqi = time.substring(0, 11);
        hour = time.substring(11, 13);

        tvInspectionTime.setText(year + "-" + month + "-" + day + " " + hour + "时");//报检时间
    }

    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
