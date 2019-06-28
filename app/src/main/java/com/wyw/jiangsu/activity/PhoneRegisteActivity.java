package com.wyw.jiangsu.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.SimpleOnItemSelectedListener;
import com.wyw.jiangsu.bean.AdressBean;
import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.bean.RegisteUploadBean;
import com.wyw.jiangsu.interfac.IPhoneRegisteActivity;
import com.wyw.jiangsu.presenter.PhoneRegisteActivityPresenter;
import com.wyw.jiangsu.utils.OtherUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/5/15.
 */
public class PhoneRegisteActivity extends MVPActivity<PhoneRegisteActivityPresenter> implements IPhoneRegisteActivity {

    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_yonghu_name)
    EditText etYonghuName;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.rb_apply_anim)
    RadioButton rbApplyAnim;
    @BindView(R.id.rb_apply_material)
    RadioButton rbApplyMaterial;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.et_user_mima)
    EditText etUserMima;
    @BindView(R.id.et_queren_mima)
    EditText etQuerenMima;
    @BindView(R.id.spinner_qiye_type)
    Spinner spinnerQiyeType;
    @BindView(R.id.et_youxiang)
    EditText etYouxiang;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.et_qiye_name)
    EditText etQiyeName;
    @BindView(R.id.et_qiye_xinyong)
    EditText etQiyeXinyong;
    @BindView(R.id.spinner_year)
    Spinner spinnerYear;
    @BindView(R.id.spinner_month)
    Spinner spinnerMonth;
    @BindView(R.id.spinner_day)
    Spinner spinnerDay;
    @BindView(R.id.spinner_province)
    Spinner spinnerProvince;
    @BindView(R.id.spinner_city)
    Spinner spinnerCity;
    @BindView(R.id.spinner_xian)
    Spinner spinnerXian;
    @BindView(R.id.spinner_jiedao)
    Spinner spinnerJiedao;
    @BindView(R.id.et_other)
    EditText etOther;
    @BindView(R.id.et_jingdu)
    EditText etJingdu;
    @BindView(R.id.et_weidu)
    EditText etWeidu;
    @BindView(R.id.et_faren_daibiao)
    EditText etFarenDaibiao;
    @BindView(R.id.et_faren_phone)
    EditText etFarenPhone;
    @BindView(R.id.et_lianxiren)
    EditText etLianxiren;
    @BindView(R.id.et_lianxiren_phone)
    EditText etLianxirenPhone;
    @BindView(R.id.bt_commit)
    Button btCommit;
    private List<String> endDayList;
    private RegisteAddressBean registeBean;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    //要上传的实体类
    private RegisteUploadBean bean = new RegisteUploadBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_registe);
        ButterKnife.bind(this);
    }

    @Override
    protected PhoneRegisteActivityPresenter createPresenter() {
        return new PhoneRegisteActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("用户注册");
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        // 启动定位
        locationClient.startLocation();

        getPresenter().getAddress();
        //设置时间
        setTime();
        //企业类型
        spinnerQiyeType.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
                getResources().getStringArray(R.array.qiye_type)));
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                if (location.getErrorCode() == 0) {
                    etJingdu.setText(location.getLongitude() + "");
                    etWeidu.setText(location.getLatitude() + "");
                } else {
//                    Toast.makeText(PhoneRegisteActivity.this, "定位失败", Toast.LENGTH_LONG).show();
                }
            } else {
//                Toast.makeText(PhoneRegisteActivity.this, "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    };

    private void setTime() {

        spinnerYear.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
                OtherUtil.createYears()));
        spinnerMonth.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
                OtherUtil.createMonths()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        endDayList = OtherUtil.createDays(String.valueOf(calendar.get(Calendar.YEAR)),
                String.valueOf(calendar.get(Calendar.MONTH) + 1));
        spinnerDay.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, endDayList));
        spinnerYear.setSelection(4);
        spinnerMonth.setSelection(OtherUtil.getCurrentMonthPosition());
        spinnerDay.setSelection(OtherUtil.getCurrentDayPosition());
    }

    @Override
    public void bindListener() {

        spinnerYear.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onStartSpinnerSelect();
            }
        });

        spinnerMonth.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onStartSpinnerSelect();
            }
        });
        //用户名
        etYonghuName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etFarenDaibiao.setText(s);
            }
        });
        //用户名手机号
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etFarenPhone.setText(s);
            }
        });


        spinnerCity.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> strings = getStrings(getStreet1(spinnerCity.getSelectedItem().toString()));
                if (strings.size() == 0)
                    spinnerXian.setVisibility(View.INVISIBLE);
                else {
                    spinnerXian.setVisibility(View.VISIBLE);
                    spinnerXian.setAdapter(new ArrayAdapter<>(PhoneRegisteActivity.this, android.R.layout.simple_dropdown_item_1line, strings));
                }
            }
        });

        spinnerXian.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> strings = getStrings(getStreet(spinnerXian.getSelectedItem().toString()));
                if (strings.size() == 0)
                    spinnerJiedao.setVisibility(View.INVISIBLE);
                else {
                    spinnerJiedao.setVisibility(View.VISIBLE);
                    spinnerJiedao.setAdapter(new ArrayAdapter<>(PhoneRegisteActivity.this, android.R.layout.simple_dropdown_item_1line, strings));
                }
            }
        });


        //提交
        btCommit.setOnClickListener(v -> {
            if (check()) {
                getPresenter().upLoad(getData());
            }
        });
    }

    private RegisteUploadBean getData() {
        bean = new RegisteUploadBean();
        AdressBean.DataListBean uploadBean;
        String dz;
        if (spinnerJiedao.getVisibility() == View.VISIBLE) {
            uploadBean = registeBean.getStreets().get(spinnerXian.getSelectedItem().toString()).get(spinnerJiedao.getSelectedItemPosition());
            dz = spinnerProvince.getSelectedItem().toString() + spinnerCity.getSelectedItem().toString()
                    + spinnerXian.getSelectedItem().toString() + spinnerJiedao.getSelectedItem().toString();
        } else {
            uploadBean = registeBean.getCountries().get(spinnerCity.getSelectedItem().toString()).get(spinnerXian.getSelectedItemPosition());
            dz = spinnerProvince.getSelectedItem().toString() + spinnerCity.getSelectedItem().toString()
                    + spinnerXian.getSelectedItem().toString();
        }
        bean.setFSunitId(Integer.parseInt(uploadBean.getFStId()));        //地址最后一个下拉的行政id
        bean.setFSunitUstrId(uploadBean.getFuStrId());  //地址最后一个下拉的行政等级
        bean.setFSunitName(uploadBean.getFuName());     //地址最后一个下拉的行政名称
        bean.setyName(etYonghuName.getText().toString());//用户名
        bean.setyPhone(etPhoneNumber.getText().toString());//手机号码
        bean.setySex(rbApplyAnim.isChecked() ? false : true);//性别
        bean.setyPassWord(etUserMima.getText().toString());//密码
        bean.setFleix(spinnerQiyeType.getSelectedItem().toString());//企业类型
        bean.setyEmail(etYouxiang.getText().toString());//邮箱
        bean.setyQQ(etQq.getText().toString());//QQ
        bean.setyQTName(etQiyeName.getText().toString());//企业名称
        bean.setyCLDate(spinnerYear.getSelectedItem().toString() + "-" + spinnerMonth.getSelectedItem().toString()
                + "-" + spinnerDay.getSelectedItem().toString());//成立时间
        bean.setyZCAddress(dz);//地址
        bean.setyXiang(etOther.getText().toString());//详细地址
        String jingdu = etJingdu.getText().toString();
        if (!TextUtils.isEmpty(jingdu)) {
            bean.setsLongitude(jingdu);//经度
        } else {
            bean.setsLongitude("0.0");//经度
        }
        String weidu = etWeidu.getText().toString();
        if (!TextUtils.isEmpty(weidu)) {
            bean.setsLatitude(weidu);//纬度
        } else {
            bean.setsLatitude("0.0");//纬度
        }

        bean.setyLXR(etFarenDaibiao.getText().toString());//法人代表
        bean.setyLXRPhone(etFarenPhone.getText().toString());//法人联系电话
        bean.setZdlxr(etLianxiren.getText().toString());//联系人
        bean.setLxdh(etLianxirenPhone.getText().toString());//联系人电话
        bean.setFqyshxydm(etQiyeXinyong.getText().toString());//企业社会信用代码
        return bean;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }


    private void onStartSpinnerSelect() {
        endDayList.clear();
        String year = spinnerYear.getSelectedItem().toString();
        Integer month = Integer.valueOf(spinnerMonth.getSelectedItem().toString());
        endDayList.addAll(OtherUtil.createDays(year, String.valueOf(month)));
        ((ArrayAdapter) spinnerDay.getAdapter()).notifyDataSetChanged();
    }

    private boolean check() {
        if (isEmpty(etYonghuName)) {
            showToast("用户名不能为空");
            return false;
        }

        if (isEmpty(etPhoneNumber)) {
            showToast("手机号不能为空");
            return false;
        }

        String number = etPhoneNumber.getText().toString();
        if (!number.substring(0, 1).equals("1")) {
            showToast("手机号格式错误");
            return false;
        }

        if (etUserMima.getText().toString().length() < 6 || etQuerenMima.getText().toString().length() < 6) {
            showToast("密码长度在6-14位之间");
            return false;
        }

        if (!getString(etUserMima).equals(getString(etQuerenMima))) {
            showToast("两次密码不一致");
            return false;
        }

        if (isEmpty(etQiyeName)) {
            showToast("企业名称不能为空");
            return false;
        }

        if (isEmpty(etQiyeXinyong)) {
            showToast("企业社会信用代码不能为空");
            return false;
        }

        if (isEmpty(etFarenDaibiao)) {
            showToast("法人代表不能为空");
            return false;
        }

        if (isEmpty(etFarenPhone)) {
            showToast("法人联系电话不能为空");
            return false;
        }

        String number1 = etFarenPhone.getText().toString();
        if (!number1.substring(0, 1).equals("1")) {
            showToast("手机格式错误");
            return false;
        }

//        if (!number1.substring(1, 2).contains("3") || !number1.substring(1, 2).contains("4")
//                || !number1.substring(1, 2).contains("5") || !number1.substring(1, 2).contains("8")) {
//            showToast("手机格式错误");
//            return false;
//        }

        if (isEmpty(etLianxiren)) {
            showToast("联系人不能为空");
            return false;
        }

        return true;
    }

    //验证邮箱
//    public static boolean isEmail(String email) {
//        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
//        Pattern p = Pattern.compile(str);
//        Matcher m = p.matcher(email);
//        return m.matches();
//    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    public void setAddress(RegisteAddressBean bean) {
        this.registeBean = bean;
        //设置adapter
        spinnerProvince.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new String[]{"江苏省"}));
        spinnerCity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getStrings(bean.getCities())));
        spinnerXian.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getStrings(getStreet1(spinnerCity.getSelectedItem().toString()))));
        spinnerJiedao.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getStrings(getStreet(spinnerXian.getSelectedItem().toString()))));

    }

    @Override
    public void uploadsuccess(String result2) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("恭喜您,注册成功!请牢记您的注册账号")
                .setMessage(result2)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();

    }

    public List<AdressBean.DataListBean> getStreet1(String name) {
        return registeBean.getCountries().get(name);
    }

    public List<AdressBean.DataListBean> getStreet(String name) {
        return registeBean.getStreets().get(name);
    }

    public List<String> getStrings(List<AdressBean.DataListBean> list) {
        List<String> data = new ArrayList<>();
        for (AdressBean.DataListBean bean : list) {
            data.add(bean.getFuName());
        }
        return data;
    }
}
