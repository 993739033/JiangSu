package com.wyw.jiangsu.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.wyw.jiangsu.bean.SyscRegisteUploadBean;
import com.wyw.jiangsu.interfac.IsyscFragment;
import com.wyw.jiangsu.presenter.SyscFragmentPresenter;
import com.wyw.jiangsu.service.NetWorkUtils;
import com.wyw.jiangsu.utils.OtherUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HUANG on 2017/9/7.
 */
public class SyscFragment extends MVPFragment<SyscFragmentPresenter> implements IsyscFragment {

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
    @BindView(R.id.et_name_qiye)
    EditText etNameQiye;
    @BindView(R.id.spinner_shengc1)
    Spinner spinnerShengc1;
    @BindView(R.id.spinner_shengc2)
    Spinner spinnerShengc2;
    @BindView(R.id.spinner_shengc3)
    Spinner spinnerShengc3;
    @BindView(R.id.spinner_shengc4)
    Spinner spinnerShengc4;
    @BindView(R.id.et_youbian)
    EditText etYoubian;
    @BindView(R.id.et_jindu)
    EditText etJindu;
    @BindView(R.id.et_weidu)
    EditText etWeidu;
    @BindView(R.id.et_phone_lianxiren)
    EditText etPhoneLianxiren;
    @BindView(R.id.cb_hy)
    CheckBox cbHy;
    @BindView(R.id.cb_zy)
    CheckBox cbZy;
    @BindView(R.id.cb_swzy)
    CheckBox cbSwzy;
    @BindView(R.id.cb_qita)
    CheckBox cbQita;
    @BindView(R.id.cb_yl)
    CheckBox cbYl;
    @BindView(R.id.cb_zlsj)
    CheckBox cbZlsj;
    @BindView(R.id.et_sjgm)
    EditText etSjgm;
    @BindView(R.id.bt_upload)
    Button btUpload;
    Unbinder unbinder;
    @BindView(R.id.et_address_detil)
    EditText etAddressDetil;
    private boolean isPrepared;
    private List<String> provinces;
    private List<String> cityls;
    private List<String> countyls;
    private List<String> streetls;
    private List<AdressBean.DataListBean> cities;
    private Map<String, List<AdressBean.DataListBean>> countries;
    private Map<String, List<AdressBean.DataListBean>> streets;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> countyAdapter;
    private ArrayAdapter<String> streetAdapter;
    private AMapLocationClientOption locationOption;
    private AMapLocationClient locationClient;
    private AlertDialog.Builder uploadBuild;
    private TextView userid;
    private List<AdressBean.DataListBean> provincess;

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

    @Override
    protected SyscFragmentPresenter createPresenter() {
        return new SyscFragmentPresenter(this);
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
        return R.layout.fragment_sysc;
    }

    @Override
    protected void bindListener() {
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        spinnerShengc1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerShengc2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    spinnerShengc3.setVisibility(View.VISIBLE);
                    notifySpinner3();
                } else {
                    countyAdapter.clear();
                    streetAdapter.clear();
                    spinnerShengc3.setVisibility(View.GONE);
                    spinnerShengc4.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerShengc3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    spinnerShengc4.setVisibility(View.VISIBLE);
                    notifySpinner4();
                } else {
                    streetAdapter.clear();
                    spinnerShengc4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public AdressBean.DataListBean getProvincesData(String name) {
        for (AdressBean.DataListBean bean : provincess) {
            if (bean.getFuName().equals(name)) {
                return bean;
            }
        }
        return null;
    }
    private void notifySpinner3() {
        String item = (String) spinnerShengc2.getSelectedItem();
        List<AdressBean.DataListBean> dataListBeen = countries.get(item);
        if (dataListBeen != null) {
            countyls = new ArrayList<>();
            countyls.add("请选择县");
            for (AdressBean.DataListBean bean : dataListBeen) {
                countyls.add(bean.getFuName());
            }
            spinnerShengc3.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countyls));
            //countyAdapter.addAll(countyls);
        }
    }

    private void notifySpinner4() {
        String item = (String) spinnerShengc3.getSelectedItem();
        List<AdressBean.DataListBean> dataListBeen = streets.get(item);
        if (dataListBeen != null) {
            streetls = new ArrayList<>();
            streetls.add("请选择街道");
            for (AdressBean.DataListBean bean : dataListBeen) {
                streetls.add(bean.getFuName());
            }
            spinnerShengc4.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, streetls));
        }
    }

    @Override
    protected void bindData() {
        tvTitle.setText("兽药生产企业注册");
        if (NetWorkUtils.isOpenNetwork(getContext())) {
            getPresenter().getRegisteAddress();
        }else{
            Toast.makeText(getContext(),"当前无网络，请确认网络后再注册！",Toast.LENGTH_LONG).show();
            ((Activity) getContext()).finish();
        }
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
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
        spinnerShengc1.setAdapter(provinceAdapter);
        spinnerShengc2.setAdapter(cityAdapter);
        spinnerShengc3.setAdapter(countyAdapter);
        spinnerShengc4.setAdapter(streetAdapter);


        uploadBuild=new AlertDialog.Builder(getContext());
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

    @OnClick({R.id.bt_upload})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_upload:
                if(check()){
                    SyscRegisteUploadBean uploadData = getUploadData();
                    getPresenter().UploadFF(new Gson().toJson(uploadData),1,"Reu_QYJBQK","1");
                }
                break;
        }
    }

    public SyscRegisteUploadBean getUploadData() {
        SyscRegisteUploadBean bean = new SyscRegisteUploadBean();
        //用户姓名
        bean.setFuName(etName.getText().toString());
        //手机号码
        bean.setFuPhone(etPhoneNumber.getText().toString());
        //性别
        if (rbMan.isChecked())
            bean.setFuSex(false);
        if (rbMen.isChecked())
            bean.setFuSex(true);
        //密码
        bean.setFuPassWord(etPwd.getText().toString());
        //邮箱
        bean.setFuEmail(etEmail.getText().toString());
        //QQ
        bean.setFuQQ(etQQ.getText().toString());
        //企业名称
        bean.setqCompanyName(etNameQiye.getText().toString());
        //生产地址
        String addressc = ((String) spinnerShengc1.getSelectedItem());
        if (spinnerShengc2.getSelectedItemPosition() > 0) {
            addressc += ((String) spinnerShengc2.getSelectedItem());
        }
        if (spinnerShengc3.getSelectedItemPosition() > 0) {
            addressc += ((String) spinnerShengc3.getSelectedItem());
        }
        if (spinnerShengc4.getSelectedItemPosition() > 0) {
            addressc += ((String) spinnerShengc4.getSelectedItem());
        }
        bean.setqCityName(addressc);
        //详细地址
        bean.setqLongaddress(etAddressDetil.getText().toString());
        //邮编
        bean.setqZipCode(etYoubian.getText().toString());
        //经纬度
        bean.setqLongitudes(etJindu.getText().toString());
        bean.setqLatitudes(etWeidu.getText().toString());
        String type="";
        if (cbHy.isChecked()){
            type+=cbHy.getText().toString();
        }
        if (cbZy.isChecked()){
            type+=cbZy.getText().toString();
        }
        if (cbSwzy.isChecked()){
            type+=cbSwzy.getText().toString();
        }
        if (cbQita.isChecked()){
            type+=cbQita.getText().toString();
        }
        if (cbYl.isChecked()){
            type+=cbYl.getText().toString();
        }
        if (cbZlsj.isChecked()){
            type+=cbZlsj.getText().toString();
        }
        bean.setFPSycpType(type);
        //年生产设计规模
        bean.setFPturnover(Double.valueOf(etSjgm.getText().toString()));
        //联系电话
        bean.setqLaborNum(etPhoneLianxiren.getText().toString());
        bean.setRegisterIP(OtherUtil.getPhoneIP());
        if (spinnerShengc1.getVisibility()==View.VISIBLE){

                AdressBean.DataListBean streetData = getProvincesData(((String) spinnerShengc1.getSelectedItem()));
                bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                bean.setFSunitUstrId(streetData.getFuStrId());
                bean.setFSunitName(streetData.getFuName());

        }
        if (spinnerShengc2.getVisibility()==View.VISIBLE){
            int selectedItemPosition = spinnerShengc2.getSelectedItemPosition();
            if (selectedItemPosition>0){
                AdressBean.DataListBean streetData = getcityData(((String) spinnerShengc2.getSelectedItem()));
                bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                bean.setFSunitUstrId(streetData.getFuStrId());
                bean.setFSunitName(streetData.getFuName());

            }
        }
        if (spinnerShengc3.getVisibility()==View.VISIBLE){
            int selectedItemPosition = spinnerShengc3.getSelectedItemPosition();
            if (selectedItemPosition>0){
                AdressBean.DataListBean streetData = getcountyData(((String) spinnerShengc3.getSelectedItem()));
                bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                bean.setFSunitUstrId(streetData.getFuStrId());
                bean.setFSunitName(streetData.getFuName());

            }
        }
        if (spinnerShengc4.getVisibility()==View.VISIBLE){
            int selectedItemPosition = spinnerShengc4.getSelectedItemPosition();
            if (selectedItemPosition>0){
                AdressBean.DataListBean streetData = getStreetData(((String) spinnerShengc4.getSelectedItem()));
                bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                bean.setFSunitUstrId(streetData.getFuStrId());
                bean.setFSunitName(streetData.getFuName());

            }
        }


        return bean;
    }
    public AdressBean.DataListBean getStreetData(String name) {
        for (AdressBean.DataListBean bean : streets.get(((String) spinnerShengc3.getSelectedItem()))) {
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
    public AdressBean.DataListBean getcountyData(String name){
        for (AdressBean.DataListBean bean:countries.get(((String) spinnerShengc2.getSelectedItem()))){
            if (bean.getFuName().equals(name)){
                return bean;
            }
        }
        return null;
    }
    public String getLocalIpAddress()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            // Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }
    @Override
    public void setAddress(RegisteAddressBean bean) {
        if (bean == null) return;
        //省
        provinces = bean.getProvince();
        provinceAdapter.addAll(provinces);
        provincess=bean.getDataList();
        //市
        cities = bean.getCities();
        if (cities != null) {
            cityls = new ArrayList<>();
            cityls.add("请选择市");
            for (AdressBean.DataListBean be : cities) {
                cityls.add(be.getFuName());
            }
            cityAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cityls);
            spinnerShengc2.setAdapter(cityAdapter);
        }
        //县
        countries = bean.getCountries();
        //街道
        streets = bean.getStreets();
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
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(getContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etNameQiye.getText().toString())) {
            Toast.makeText(getContext(), "企业名称不能为空", Toast.LENGTH_SHORT).show();
            etNameQiye.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etAddressDetil.getText().toString())){
            Toast.makeText(getContext(), "详细地址不能为空", Toast.LENGTH_SHORT).show();
            etAddressDetil.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPhoneLianxiren.getText().toString())) {
            Toast.makeText(getContext(), "联系电话不能为空", Toast.LENGTH_SHORT).show();
            etPhoneLianxiren.requestFocus();
            return false;
        }
        if (!cbHy.isChecked() && !cbQita.isChecked() && !cbSwzy.isChecked() && !cbYl.isChecked() && !cbZlsj.isChecked() && !cbZy.isChecked()) {

            Toast.makeText(getContext(), "兽药产品类型最少选择一个", Toast.LENGTH_SHORT).show();
            cbHy.requestFocus();
            return false;

        }
        if (TextUtils.isEmpty(etSjgm.getText().toString())) {
            Toast.makeText(getContext(), "年生产设计规模不能为空", Toast.LENGTH_SHORT).show();
            etSjgm.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etJindu.getText().toString()) || TextUtils.isEmpty(etWeidu.getText().toString())) {
            Toast.makeText(getContext(), "经度纬度不能为空", Toast.LENGTH_SHORT).show();
            etJindu.requestFocus();
            return false;
        }
        //验证手机号码
        if (!TextUtils.isEmpty(etPhoneNumber.getText().toString()))
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
        if (!TextUtils.isEmpty(etPwd.getText().toString()))
        if (!OtherUtil.isPwdType(etPwd.getText().toString())) {
            Toast.makeText(getContext(), "密码格式不正确", Toast.LENGTH_SHORT).show();
            etPwd.requestFocus();
            return false;
        }
        //两次密码对比
        if (!TextUtils.isEmpty(etPwd.getText().toString())&&!TextUtils.isEmpty(etPwdEnsure.getText().toString()))
        if (!etPwd.getText().toString().equals(etPwdEnsure.getText().toString())) {
            Toast.makeText(getContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
            etPwd.requestFocus();
            return false;
        }
        //邮箱
        if (!TextUtils.isEmpty(etEmail.getText().toString()))
        if (!OtherUtil.isEmail(etEmail.getText().toString())) {
            Toast.makeText(getContext(), "填写正确的邮箱", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }
        if (!TextUtils.isEmpty(etPhoneLianxiren.getText().toString()))
        if (!OtherUtil.isPhoneNumber(etPhoneLianxiren.getText().toString())) {
            Toast.makeText(getContext(), "填写正确的联系电话", Toast.LENGTH_SHORT).show();
            etPhoneLianxiren.requestFocus();
            return false;
        }
        if (!TextUtils.isEmpty(etYoubian.getText().toString()))
        if (!OtherUtil.isZipNO(etYoubian.getText().toString())){
            Toast.makeText(getContext(), "填写正确的邮编", Toast.LENGTH_SHORT).show();
            etYoubian.requestFocus();
            return false;
        }

        return true;
    }
}
