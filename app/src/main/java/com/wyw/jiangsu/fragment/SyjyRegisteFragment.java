package com.wyw.jiangsu.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AdressBean;
import com.wyw.jiangsu.bean.RegisteAddressBean;
import com.wyw.jiangsu.bean.RegisteMsgBean;
import com.wyw.jiangsu.bean.SyjyRegistBean;
import com.wyw.jiangsu.interfac.ISyjyRegiste;
import com.wyw.jiangsu.presenter.SyjyRegistePresenter;
import com.wyw.jiangsu.service.NetWorkUtils;
import com.wyw.jiangsu.utils.OtherUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HUANG on 2017/9/6.
 */
public class SyjyRegisteFragment extends MVPFragment<SyjyRegistePresenter> implements ISyjyRegiste, DatePickerDialog.OnDateSetListener {
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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_men)
    RadioButton rbMen;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd_ensure)
    EditText etPwdEnsure;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_QQ)
    EditText etQQ;
    @BindView(R.id.et_qiye_name)
    EditText etQiyeName;
    @BindView(R.id.et_yinyongdaima)
    EditText etYinyongdaima;
    @BindView(R.id.et_address_zhuce1)
    EditText etAddressZhuce1;
    @BindView(R.id.et_address_zhuce2)
    EditText etAddressZhuce2;
    @BindView(R.id.spinner_jingyin1)
    Spinner spinnerJingyin1;
    @BindView(R.id.spinner_jingyin2)
    Spinner spinnerJingyin2;
    @BindView(R.id.spinner_jingyin3)
    Spinner spinnerJingyin3;
    @BindView(R.id.et_address_detil)
    EditText etAddressDetil;
    @BindView(R.id.et_address_cangku)
    EditText etAddressCangku;
    @BindView(R.id.et_postalcode)
    EditText etPostalcode;
    @BindView(R.id.et_jindu)
    EditText etJindu;
    @BindView(R.id.et_weidu)
    EditText etWeidu;
    @BindView(R.id.et_space_jingyin)
    EditText etSpaceJingyin;
    @BindView(R.id.et_space_cangku)
    EditText etSpaceCangku;
    Unbinder unbinder;
    @BindView(R.id.et_darendaibiao)
    EditText etDarendaibiao;
    @BindView(R.id.et_phone_faren)
    EditText etPhoneFaren;
    @BindView(R.id.et_lianxiren)
    EditText etLianxiren;
    @BindView(R.id.et_phone_lianxiren)
    EditText etPhoneLianxiren;
    @BindView(R.id.et_xukezheng)
    EditText etXukezheng;
    @BindView(R.id.tv_date_fazheng)
    TextView etDateFazheng;
    @BindView(R.id.tv_date_youxiaoqi)
    TextView etDateYouxiaoqi;
    @BindView(R.id.et_number_zhigong)
    EditText etNumberZhigong;
    @BindView(R.id.rb_pifa)
    RadioButton rbPifa;
    @BindView(R.id.rb_lingshou)
    RadioButton rbLingshou;
    @BindView(R.id.rg_xiaoshou)
    RadioGroup rgXiaoshou;
    @BindView(R.id.cb_shouyao)
    CheckBox cbShouyao;
    @BindView(R.id.cb_swzhiping)
    CheckBox cbSwzhiping;
    @BindView(R.id.cb_zlshiji)
    CheckBox cbZlshiji;
    @BindView(R.id.et_zlfuzeren)
    EditText etZlfuzeren;
    @BindView(R.id.et_phone_zlfuzeren)
    EditText etPhoneZlfuzeren;
    @BindView(R.id.et_beizhu)
    EditText etBeizhu;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.tv_chengli_time)
    TextView tvChengliTime;
    @BindView(R.id.spinner_jingyin4)
    Spinner spinnerJingyin4;
    @BindView(R.id.et_fazhengDW)
    EditText etFazhengDW;
    private AMapLocationClientOption locationOption;
    private AMapLocationClient locationClient;
    private TextView curEt;
    private DatePickerDialog datePickerDialog;
    protected boolean isPrepared;
    private List<String> provinces;
    private List<String> cityls;
    private List<String> countyls;
    private List<String> streetls;
    private List<AdressBean.DataListBean> cities;
    private List<AdressBean.DataListBean> provincess;
    private Map<String, List<AdressBean.DataListBean>> countries;
    private Map<String, List<AdressBean.DataListBean>> streets;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> countyAdapter;
    private ArrayAdapter<String> streetAdapter;
    private AlertDialog.Builder uploadBuild;
    private TextView userid;


    @Override
    protected SyjyRegistePresenter createPresenter() {
        return new SyjyRegistePresenter(this);
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        bindData();
        bindListener();
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_syjy;
    }

    @Override
    protected void bindListener() {
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        spinnerJingyin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerJingyin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    spinnerJingyin3.setVisibility(View.VISIBLE);
                    notifySpinner3();
                } else {
                    countyAdapter.clear();
                    streetAdapter.clear();
                    spinnerJingyin3.setVisibility(View.GONE);
                    spinnerJingyin4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerJingyin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    spinnerJingyin4.setVisibility(View.VISIBLE);
                    notifySpinner4();
                } else {
                    streetAdapter.clear();
                    spinnerJingyin4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        etName.addTextChangedListener(new MTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                etDarendaibiao.setText(s.toString());

            }
        });
        etPhoneNumber.addTextChangedListener(new MTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                etPhoneFaren.setText(s.toString());
            }
        });
    }

    static class MTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void notifySpinner3() {
        String item = (String) spinnerJingyin2.getSelectedItem();
        List<AdressBean.DataListBean> dataListBeen = countries.get(item);
        if (dataListBeen != null) {
            countyls = new ArrayList<>();
            countyls.add("请选择县");
            for (AdressBean.DataListBean bean : dataListBeen) {
                countyls.add(bean.getFuName());
            }
            spinnerJingyin3.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countyls));
            //countyAdapter.addAll(countyls);
        }
    }

    private void notifySpinner4() {
        String item = (String) spinnerJingyin3.getSelectedItem();
        List<AdressBean.DataListBean> dataListBeen = streets.get(item);
        if (dataListBeen != null) {
            streetls = new ArrayList<>();
            streetls.add("请选择街道");
            for (AdressBean.DataListBean bean : dataListBeen) {
                streetls.add(bean.getFuName());
            }
            spinnerJingyin4.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, streetls));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void bindData() {
        tvTitle.setText("兽药经营注册");
        if (NetWorkUtils.isOpenNetwork(getContext())) {
            getPresenter().getRegisteAddress();
        } else {
            Toast.makeText(getContext(), "当前无网络，请确认网络后再注册！", Toast.LENGTH_LONG).show();
            ((Activity) getContext()).finish();
        }
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        datePickerDialog = new DatePickerDialog(getContext(), this, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        //初始化client
        locationClient = new AMapLocationClient(this.getContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        // 启动定位
        locationClient.startLocation();
        provinces = new ArrayList<>();
        cityls = new ArrayList<>();

        countyls = new ArrayList<>();

        streetls = new ArrayList<>();


        provinceAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, provinces);
        cityAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cityls);
        countyAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countyls);
        streetAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, streetls);
        spinnerJingyin1.setAdapter(provinceAdapter);
        spinnerJingyin2.setAdapter(cityAdapter);
        spinnerJingyin3.setAdapter(countyAdapter);
        spinnerJingyin4.setAdapter(streetAdapter);

        uploadBuild = new AlertDialog.Builder(getContext());
        uploadBuild.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((Activity) getContext()).finish();
            }
        });

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_registe, null);
        userid = ((TextView) inflate.findViewById(R.id.et_user_id));
        uploadBuild.setView(inflate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
        bindData();
        bindListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
            Log.e("czy", location.toString());
            if (null != location) {
                if (location.getErrorCode() == 0) {
                    etJindu.setText(location.getLongitude() + "");
                    etWeidu.setText(location.getLatitude() + "");
                } else {
//                    Toast.makeText(PhoneRegisteActivity.this, "定位失败", Toast.LENGTH_LONG).show();
                }
            } else {
//                Toast.makeText(PhoneRegisteActivity.this, "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    };

    @OnClick({R.id.tv_date_fazheng, R.id.tv_date_youxiaoqi, R.id.tv_chengli_time, R.id.bt_upload})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date_youxiaoqi:
                curEt = ((TextView) view);
                datePickerDialog.show();
                break;
            case R.id.tv_date_fazheng:
                curEt = ((TextView) view);
                datePickerDialog.show();
                break;
            case R.id.tv_chengli_time:
                curEt = ((TextView) view);
                datePickerDialog.show();
                break;
            case R.id.bt_upload:
                if (check()) {
                    SyjyRegistBean uploadData = getupload();
                    getPresenter().UploadFF(new Gson().toJson(uploadData), 1, "Reu_FirmRun", "1");
                }
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String time = year + "-" + (month + 1) + "-" + dayOfMonth;
        curEt.setText(time);
    }

    @Override
    public void setAddress(RegisteAddressBean bean) {
        if (bean == null) return;
        //省
        provinces = bean.getProvince();
        provinceAdapter.addAll(provinces);
        provincess = bean.getDataList();
        //市
        cities = bean.getCities();
        if (cities != null) {
            cityls = new ArrayList<>();
            cityls.add("请选择市");
            for (AdressBean.DataListBean be : cities) {
                cityls.add(be.getFuName());
            }
            cityAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cityls);
            spinnerJingyin2.setAdapter(cityAdapter);
        }
        //县
        countries = bean.getCountries();
        //街道
        streets = bean.getStreets();
    }

    public SyjyRegistBean getupload() {
        SyjyRegistBean uploadbean = new SyjyRegistBean();
        uploadbean.setFuName(etName.getText().toString());
        uploadbean.setFuPhone(etPhoneNumber.getText().toString());
        if (rbMan.isChecked()) {
            uploadbean.setFuSex(false);
        }
        if (rbMen.isChecked()) {
            uploadbean.setFuSex(true);
        }
        uploadbean.setFuPassWord(etPwd.getText().toString());
        uploadbean.setFuEmail(etEmail.getText().toString());
        uploadbean.setFuQQ(etQQ.getText().toString());
        uploadbean.setFPFirmName(etQiyeName.getText().toString());
        uploadbean.setFPsetUpDate(tvChengliTime.getText().toString());
        uploadbean.setFPAddressA(etAddressZhuce1.getText().toString());
        uploadbean.setFPAddressB(etAddressZhuce2.getText().toString());
        String addressc = ((String) spinnerJingyin1.getSelectedItem());
        if (spinnerJingyin2.getSelectedItemPosition() > 0) {
            addressc += ((String) spinnerJingyin2.getSelectedItem());
        }
        if (spinnerJingyin3.getSelectedItemPosition() > 0) {
            addressc += ((String) spinnerJingyin3.getSelectedItem());
        }
        if (spinnerJingyin4.getSelectedItemPosition() > 0) {
            addressc += ((String) spinnerJingyin4.getSelectedItem());
        }


        uploadbean.setFPAddressC(addressc);

        uploadbean.setFPAddressD(etAddressDetil.getText().toString());
        uploadbean.setFPAddressE(etAddressCangku.getText().toString());
        uploadbean.setFPpostcode(etPostalcode.getText().toString());
       /* uploadbean.setFlon("121.436");
        uploadbean.setFla("31.3410");*/


        uploadbean.setFlon(etJindu.getText().toString());
        uploadbean.setFla(etWeidu.getText().toString());
        if (TextUtils.isEmpty(etSpaceJingyin.getText().toString()))
            uploadbean.setFPareaA(Double.valueOf(etSpaceJingyin.getText().toString()));
        if (TextUtils.isEmpty(etSpaceCangku.getText().toString()))
            uploadbean.setFPareaB(Double.valueOf(etSpaceCangku.getText().toString()));


        uploadbean.setFPLegalPerson(etDarendaibiao.getText().toString());
        uploadbean.setFPlpPhone(etPhoneFaren.getText().toString());
        uploadbean.setFP(etLianxiren.getText().toString());
        uploadbean.setFPTel(etPhoneLianxiren.getText().toString());
        uploadbean.setFdm(etYinyongdaima.getText().toString());
        uploadbean.setFSYJYXKZNum(etXukezheng.getText().toString());
        uploadbean.setFSYJYXKZTime(etDateFazheng.getText().toString());
        uploadbean.setFSYJYXKZVal(etDateYouxiaoqi.getText().toString());
        uploadbean.setFPzgsum(Integer.valueOf(etNumberZhigong.getText().toString()));
        uploadbean.setFZDW(etFazhengDW.getText().toString());

        if (rbPifa.isChecked()) {
            uploadbean.setFHY(rbPifa.getText().toString());
        }
        if (rbLingshou.isChecked()) {
            uploadbean.setFHY(rbLingshou.getText().toString());
        }
        String jyfw = "";
        if (cbShouyao.isChecked())
            jyfw += cbShouyao.getText().toString();
        if (cbSwzhiping.isChecked())
            jyfw += cbSwzhiping.getText().toString();
        if (cbZlshiji.isChecked())
            jyfw += cbZlshiji.getText().toString();
        uploadbean.setFSm4(jyfw);
        uploadbean.setFSm2(etZlfuzeren.getText().toString());
        uploadbean.setFSm3(etPhoneZlfuzeren.getText().toString());
        uploadbean.setFPRemark(etBeizhu.getText().toString());
        uploadbean.setRegisterIP(OtherUtil.getPhoneIP());
        if (spinnerJingyin1.getVisibility() == View.VISIBLE) {

            AdressBean.DataListBean streetData = getProvincesData(((String) spinnerJingyin1.getSelectedItem()));
            uploadbean.setFSunitId(Integer.valueOf(streetData.getFStId()));
            uploadbean.setFSunitUstrId(streetData.getFuStrId());
            uploadbean.setFSunitName(streetData.getFuName());

        }
        if (spinnerJingyin2.getVisibility() == View.VISIBLE) {
            int selectedItemPosition = spinnerJingyin2.getSelectedItemPosition();
            if (selectedItemPosition > 0) {
                AdressBean.DataListBean streetData = getcityData(((String) spinnerJingyin2.getSelectedItem()));
                uploadbean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                uploadbean.setFSunitUstrId(streetData.getFuStrId());
                uploadbean.setFSunitName(streetData.getFuName());

            }
        }
        if (spinnerJingyin3.getVisibility() == View.VISIBLE) {
            int selectedItemPosition = spinnerJingyin3.getSelectedItemPosition();
            if (selectedItemPosition > 0) {
                AdressBean.DataListBean streetData = getcountyData(((String) spinnerJingyin3.getSelectedItem()));
                uploadbean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                uploadbean.setFSunitUstrId(streetData.getFuStrId());
                uploadbean.setFSunitName(streetData.getFuName());

            }
        }
        if (spinnerJingyin4.getVisibility() == View.VISIBLE) {
            int selectedItemPosition = spinnerJingyin4.getSelectedItemPosition();
            if (selectedItemPosition > 0) {
                AdressBean.DataListBean streetData = getStreetData(((String) spinnerJingyin4.getSelectedItem()));
                uploadbean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                uploadbean.setFSunitUstrId(streetData.getFuStrId());
                uploadbean.setFSunitName(streetData.getFuName());

            }
        }
        return uploadbean;
    }

    public AdressBean.DataListBean getProvincesData(String name) {
        for (AdressBean.DataListBean bean : provincess) {
            if (bean.getFuName().equals(name)) {
                return bean;
            }
        }
        return null;
    }

    public AdressBean.DataListBean getStreetData(String name) {
        for (AdressBean.DataListBean bean : streets.get(((String) spinnerJingyin3.getSelectedItem()))) {
            if (bean.getFuName().equals(name)) {
                return bean;
            }
        }
        return null;
    }

    public AdressBean.DataListBean getcityData(String name) {
        for (AdressBean.DataListBean bean : cities) {
            if (bean.getFuName().equals(name)) {
                return bean;
            }
        }
        return null;
    }

    public AdressBean.DataListBean getcountyData(String name) {
        for (AdressBean.DataListBean bean : countries.get(((String) spinnerJingyin2.getSelectedItem()))) {
            if (bean.getFuName().equals(name)) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public void upload(RegisteMsgBean baseMsgBean) {
        if (baseMsgBean.getErrorCode() == 0) {
            userid.setText("账号：" + baseMsgBean.getData().getResult2());
            uploadBuild.show();
        }else if (baseMsgBean.getErrorCode() == 1){
            getPresenter().getLock().unLock();
            Toast.makeText(getContext(), baseMsgBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        else {
            getPresenter().getLock().unLock();
            Toast.makeText(getContext(), "上传失败,检测所填内容，如手机号等是否注册过", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean check() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            Toast.makeText(getContext(), "用户姓名不能为空", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPhoneNumber.getText().toString())) {
            Toast.makeText(getContext(), "手机号码不能为空", Toast.LENGTH_SHORT).show();
            etPhoneNumber.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())) {
            Toast.makeText(getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
            etPwd.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPwdEnsure.getText().toString())) {
            Toast.makeText(getContext(), "确认密码不能为空", Toast.LENGTH_SHORT).show();
            etPwdEnsure.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString())){
            Toast.makeText(getContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etQiyeName.getText().toString())){
            Toast.makeText(getContext(), "企业名称不能为空", Toast.LENGTH_SHORT).show();
            etQiyeName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etYinyongdaima.getText().toString())){
            Toast.makeText(getContext(), "统一社会信用代码不能为空", Toast.LENGTH_SHORT).show();
            etYinyongdaima.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(tvChengliTime.getText().toString())){
            Toast.makeText(getContext(), "成立时间不能为空", Toast.LENGTH_SHORT).show();
            tvChengliTime.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etAddressZhuce1.getText().toString()) || TextUtils.isEmpty(etAddressZhuce2.getText().toString())) {
            Toast.makeText(getContext(), "注册地址不能为空", Toast.LENGTH_SHORT).show();
            etAddressZhuce1.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etAddressDetil.getText().toString())){
            Toast.makeText(getContext(), "详细地址不能为空", Toast.LENGTH_SHORT).show();
            etAddressDetil.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etAddressCangku.getText().toString())) {
            Toast.makeText(getContext(), "仓储地址不能为空", Toast.LENGTH_SHORT).show();
            etAddressCangku.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPostalcode.getText().toString())){
            Toast.makeText(getContext(), "邮政编码不能为空", Toast.LENGTH_SHORT).show();
            etPostalcode.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etJindu.getText().toString()) || TextUtils.isEmpty(etWeidu.getText().toString())) {
            Toast.makeText(getContext(), "经度纬度不能为空", Toast.LENGTH_SHORT).show();
            etJindu.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etSpaceJingyin.getText().toString())){
            Toast.makeText(getContext(), "经营场所面积不能为空", Toast.LENGTH_SHORT).show();
            etSpaceJingyin.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etSpaceCangku.getText().toString())){
            Toast.makeText(getContext(), "仓储场所面积不能为空", Toast.LENGTH_SHORT).show();
            etSpaceCangku.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etDarendaibiao.getText().toString())) {
            Toast.makeText(getContext(), "法人代表不能为空", Toast.LENGTH_SHORT).show();
            etDarendaibiao.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPhoneFaren.getText().toString())) {
            Toast.makeText(getContext(), "法人代表电话不能为空", Toast.LENGTH_SHORT).show();
            etPhoneFaren.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etLianxiren.getText().toString())) {
            Toast.makeText(getContext(), "联系人不能为空", Toast.LENGTH_SHORT).show();
            etLianxiren.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etYinyongdaima.getText().toString())) {
            Toast.makeText(getContext(), "统一社会信用代码不能为空", Toast.LENGTH_SHORT).show();
            etYinyongdaima.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etDateFazheng.getText().toString())) {
            Toast.makeText(getContext(), "发证日期不能为空", Toast.LENGTH_SHORT).show();
            etDateFazheng.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etDateYouxiaoqi.getText().toString())) {
            Toast.makeText(getContext(), "有效期至不能为空", Toast.LENGTH_SHORT).show();
            etDateYouxiaoqi.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etFazhengDW.getText().toString())) {
            Toast.makeText(getContext(), "发证单位不能为空", Toast.LENGTH_SHORT).show();
            etFazhengDW.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etXukezheng.getText().toString())) {
            Toast.makeText(getContext(), "兽药经营许可证编号不能为空", Toast.LENGTH_SHORT).show();
            etXukezheng.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etNumberZhigong.getText().toString())){
            Toast.makeText(getContext(), "职工总数不能为空", Toast.LENGTH_SHORT).show();
            etNumberZhigong.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etZlfuzeren.getText().toString())) {
            Toast.makeText(getContext(), "质量负责人不能为空", Toast.LENGTH_SHORT).show();
            etZlfuzeren.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPhoneZlfuzeren.getText().toString())) {
            Toast.makeText(getContext(), "质量负责人电话不能为空", Toast.LENGTH_SHORT).show();
            etPhoneZlfuzeren.requestFocus();
            return false;
        }


        //验证手机号码
        if (!OtherUtil.isPhoneNumber(etPhoneNumber.getText().toString())) {
            Toast.makeText(getContext(), "填写正确的手机号码", Toast.LENGTH_SHORT).show();
            etPhoneNumber.requestFocus();
            return false;
        }
        //性别选择
        if (!rbMen.isChecked() && !rbMan.isChecked()) {
            Toast.makeText(getContext(), "请选择性别", Toast.LENGTH_SHORT).show();
            rbMan.requestFocus();
            return false;
        }
        if (!OtherUtil.isPwdType(etPwd.getText().toString())) {
            Toast.makeText(getContext(), "密码格式不正确", Toast.LENGTH_SHORT).show();
            etPwd.requestFocus();
            return false;
        }
        //两次密码对比
        if (!etPwd.getText().toString().equals(etPwdEnsure.getText().toString())) {
            Toast.makeText(getContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
            etPwd.requestFocus();
            return false;
        }
        //邮箱
        if (!OtherUtil.isEmail(etEmail.getText().toString())) {
            Toast.makeText(getContext(), "填写正确的邮箱", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }
        if (!OtherUtil.isZipNO(etPostalcode.getText().toString())) {
            Toast.makeText(getContext(), "填写正确的邮编", Toast.LENGTH_SHORT).show();
            etPostalcode.requestFocus();
            return false;
        }
        //法人电话
        if (!TextUtils.isEmpty(etPhoneFaren.getText().toString())) {
            if (!OtherUtil.isPhoneNumber(etPhoneFaren.getText().toString())) {
                Toast.makeText(getContext(), "法人联系电话不符合", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (!TextUtils.isEmpty(etPhoneLianxiren.getText().toString())) {
            if (!OtherUtil.isPhoneNumber(etPhoneLianxiren.getText().toString())) {
                Toast.makeText(getContext(), "联系人电话不符合", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


}
