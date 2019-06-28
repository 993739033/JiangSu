package com.wyw.jiangsu.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import com.wyw.jiangsu.bean.TzRegisteUpLoadBean;
import com.wyw.jiangsu.interfac.ITzcFragment;
import com.wyw.jiangsu.presenter.TzcFragmentPresenter;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by HUANG on 2017/9/7.
 */
public class TzcFragment extends MVPFragment<TzcFragmentPresenter> implements ITzcFragment, DatePickerDialog.OnDateSetListener {

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
    @BindView(R.id.et_tzc_name)
    EditText etTzcName;
    @BindView(R.id.et_zibenofzhuce)
    EditText etZibenofzhuce;
    @BindView(R.id.et_yinyongdaima)
    EditText etYinyongdaima;
    @BindView(R.id.et_dingdiantz)
    EditText etDingdiantz;
    @BindView(R.id.spinner_jingyin1)
    Spinner spinnerJingyin1;
    @BindView(R.id.spinner_jingyin2)
    Spinner spinnerJingyin2;
    @BindView(R.id.spinner_jingyin3)
    Spinner spinnerJingyin3;
    @BindView(R.id.spinner_jingyin4)
    Spinner spinnerJingyin4;
    @BindView(R.id.et_addressofdetil)
    EditText etAddressofdetil;
    @BindView(R.id.et_jindu)
    EditText etJindu;
    @BindView(R.id.et_weidu)
    EditText etWeidu;
    @BindView(R.id.et_farendaibiao)
    EditText etFarendaibiao;
    @BindView(R.id.et_youzhengbianma)
    EditText etYouzhengbianma;
    @BindView(R.id.et_phone_faren)
    EditText etPhoneFaren;
    @BindView(R.id.et_idcardoffaren)
    EditText etIdcardoffaren;
    @BindView(R.id.et_fuzeren)
    EditText etFuzeren;
    @BindView(R.id.et_phoneoffuzeren)
    EditText etPhoneoffuzeren;
    @BindView(R.id.et_shouji)
    EditText etShouji;
    @BindView(R.id.cb_zhu)
    CheckBox cbZhu;
    @BindView(R.id.cb_niu)
    CheckBox cbNiu;
    @BindView(R.id.cb_yang)
    CheckBox cbYang;
    @BindView(R.id.cb_ji)
    CheckBox cbJi;
    @BindView(R.id.cb_ya)
    CheckBox cbYa;
    @BindView(R.id.cb_qita)
    CheckBox cbQita;
    @BindView(R.id.et_tuzainengli)
    EditText etTuzainengli;
    @BindView(R.id.spinner_jyms)
    Spinner spinnerJyms;
    @BindView(R.id.et_numzcsy)
    EditText etNumzcsy;
    @BindView(R.id.et_phone_gfsy)
    EditText etPhoneGfsy;
    @BindView(R.id.et_numberofqyyg)
    EditText etNumberofqyyg;
    @BindView(R.id.et_numberofpzjyy)
    EditText etNumberofpzjyy;
    @BindView(R.id.bt_upload)
    Button btUpload;
    private boolean isPrepared;
    private List<String> provinces;
    private List<String> cityls;
    private List<String> countyls;
    private List<String> streetls;
    private List<AdressBean.DataListBean> cities;
    private Map<String, List<AdressBean.DataListBean>> countries;
    private Map<String, List<AdressBean.DataListBean>> streets;
    private AMapLocationClientOption locationOption;
    private AMapLocationClient locationClient;
    private TextView curEt;
    private DatePickerDialog datePickerDialog;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> countyAdapter;
    private ArrayAdapter<String> streetAdapter;
    private AlertDialog.Builder uploadBuild;
    private TextView userid;
    private List<AdressBean.DataListBean> provincess;


    @Override
    protected TzcFragmentPresenter createPresenter() {
        return new TzcFragmentPresenter(this);
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
        return R.layout.fragment_tzc;
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

    @Override
    protected void bindData() {
        tvTitle.setText("动物屠宰场注册");
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
        if (NetWorkUtils.isOpenNetwork(getContext())) {
            getPresenter().getRegisteAddress();
        } else {
            Toast.makeText(getContext(), "当前无网络，请确认网络后再注册！", Toast.LENGTH_LONG).show();
            ((Activity) getContext()).finish();
        }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

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
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
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

    @OnClick({R.id.bt_upload})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_upload:
                if (check()) {
                    TzRegisteUpLoadBean uploadData = getUploadData();
                    getPresenter().UploadFF(new Gson().toJson(uploadData), 1, "Reu_SlaughterBk", "1");
                }
                break;
        }
    }

    //提交数据
    public TzRegisteUpLoadBean getUploadData() {
        TzRegisteUpLoadBean bean = new TzRegisteUpLoadBean();
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
        //动物屠宰场名称
        bean.setFhName(etTzcName.getText().toString());
        //注册资本(万元)
        bean.setFhZCMoney(Integer.valueOf(etZibenofzhuce.getText().toString()));
        bean.setFhNature("");
        //邮政编码
        // bean.setFhYB(etYouzhengbianma.getText().toString());
        //统一社会信用代码
        bean.setFdm(etYinyongdaima.getText().toString());

        bean.setFddtzz(etDingdiantz.getText().toString());

        bean.setFjyms(((String) spinnerJyms.getSelectedItem()));


        //地址
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
        bean.setFPAddressA(addressc);
        //详细地址
        bean.setFPAddressB(etAddressofdetil.getText().toString());
        //经纬度
        bean.setFLongitude(etJindu.getText().toString());
        bean.setFLatitude(etWeidu.getText().toString());
        //法人代表
        bean.setFhLegal(etFarendaibiao.getText().toString());
        //法人联系电话
        bean.setFhPhone(etPhoneFaren.getText().toString());
        //法人身份证号
        bean.setFPLegalCardId(etIdcardoffaren.getText().toString());
        //负责人
        bean.setFhFZR(etFuzeren.getText().toString());
        //负责人联系电话
        bean.setFhFZRPhone(etPhoneoffuzeren.getText().toString());
        //手机
        // bean.setFhMobPhone(etShouji.getText().toString());
        //屠宰种类
        String tzzl = "";
        if (cbZhu.isChecked()) {
            tzzl += cbZhu.getText().toString();
        }
        if (cbNiu.isChecked()) {
            tzzl += cbNiu.getText().toString();
        }
        if (cbYang.isChecked()) {
            tzzl += cbYang.getText().toString();
        }
        if (cbJi.isChecked()) {
            tzzl += cbJi.getText().toString();
        }
        if (cbYa.isChecked()) {
            tzzl += cbYa.getText().toString();
        }
        if (cbQita.isChecked()) {
            tzzl += cbQita.getText().toString();
        }
        bean.setFhKind(tzzl);

        //年屠宰能力
        bean.setFhNTZNL(etTuzainengli.getText().toString());

        //每日可屠宰动物数量
        //bean.setFtrtznl(Integer.valueOf(etNumberTuzai.getText().toString()));
        //驻场官方兽医数量
        bean.setFuserNumber(Integer.valueOf(etNumzcsy.getText().toString()));
        //驻场官方兽医联系电话
        bean.setFhzcgfsydh(etPhoneGfsy.getText().toString());
      /*  //质量负责人
        bean.setFhzlfzr(etFuzerenofzhiliang.getText().toString());
        //质量负责人联系电话
        bean.setFhzlfzrdh(etPhoneZlfuzeren.getText().toString());*/


        //企业员工数
        bean.setFhQYZRS(Integer.valueOf(etNumberofqyyg.getText().toString()));

        //品质检验员数量
        bean.setFhPZJYY(Integer.valueOf(etNumberofpzjyy.getText().toString()));





/*
        //安全生产负责人
        bean.setFhaqscfzr(etAqfuzeren.getText().toString());
        //屠宰企业类型
        bean.setFhTZLX(((String) spinnerJyms.getSelectedItem()));

        //运营起始时间
        bean.setFhZCTime(tvDateofyzstart.getText().toString());
        //组织机构代码
        bean.setFhZZJGDM(etDaimaofzljg.getText().toString());
        //上年度屠宰数量
        bean.setFhSNDCount(Integer.valueOf(etNumberofsntz.getText().toString()));

        //总资产(万元)
        bean.setFhZZCMoney(Integer.valueOf(etZichanofzong.getText().toString()));
        //其中自营数量
        bean.setFhZYSL(Integer.valueOf(etZiyingsl1.getText().toString()));
        //产品主要销往地区
        bean.setFhCPXS(etCpxwd.getText().toString());
        //企业员工数
        bean.setFhQYZRS(Integer.valueOf(etNumberofqyyg.getText().toString()));
        //屠宰技术员数量
        bean.setFhTZJSY(Integer.valueOf(etNumberoftzjsy.getText().toString()));
        //品质检验员数量
        bean.setFhPZJYY(Integer.valueOf(etNumberofpzjyy.getText().toString()));
        //上年度营业收入(万元)
        bean.setFhYYSR(Double.valueOf(etShouruofshangnian.getText().toString()));
        //上年度实现利税(万元)
        bean.setFhSXLS(Double.valueOf(etLishuiofshangnian.getText().toString()));
*/
        bean.setRegisterIP(OtherUtil.getPhoneIP());
        if (spinnerJingyin1.getVisibility() == View.VISIBLE) {

            AdressBean.DataListBean streetData = getProvincesData(((String) spinnerJingyin1.getSelectedItem()));
            bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
            bean.setFSunitUstrId(streetData.getFuStrId());
            bean.setFSunitName(streetData.getFuName());


        }
        if (spinnerJingyin2.getVisibility() == View.VISIBLE) {
            int selectedItemPosition = spinnerJingyin2.getSelectedItemPosition();
            if (selectedItemPosition > 0) {
                AdressBean.DataListBean streetData = getcityData(((String) spinnerJingyin2.getSelectedItem()));
                bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                bean.setFSunitUstrId(streetData.getFuStrId());
                bean.setFSunitName(streetData.getFuName());

            }
        }
        if (spinnerJingyin3.getVisibility() == View.VISIBLE) {
            int selectedItemPosition = spinnerJingyin3.getSelectedItemPosition();
            if (selectedItemPosition > 0) {
                AdressBean.DataListBean streetData = getcountyData(((String) spinnerJingyin3.getSelectedItem()));
                bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                bean.setFSunitUstrId(streetData.getFuStrId());
                bean.setFSunitName(streetData.getFuName());

            }
        }
        if (spinnerJingyin4.getVisibility() == View.VISIBLE) {
            int selectedItemPosition = spinnerJingyin4.getSelectedItemPosition();
            if (selectedItemPosition > 0) {
                AdressBean.DataListBean streetData = getStreetData(((String) spinnerJingyin4.getSelectedItem()));
                bean.setFSunitId(Integer.valueOf(streetData.getFStId()));
                bean.setFSunitUstrId(streetData.getFuStrId());
                bean.setFSunitName(streetData.getFuName());

            }
        }

        return bean;
    }

    public AdressBean.DataListBean getProvincesData(String name) {
        for (AdressBean.DataListBean bean : provincess) {
            if (bean.getFuName().equals(name)) {
                return bean;
            }
        }
        return null;
    }

    //提交前的检测
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
        if (TextUtils.isEmpty(etTzcName.getText().toString())){
            Toast.makeText(getContext(), "屠宰场名称不能为空", Toast.LENGTH_SHORT).show();
            etTzcName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etJindu.getText().toString()) || TextUtils.isEmpty(etWeidu.getText().toString())) {
            Toast.makeText(getContext(), "经度纬度不能为空", Toast.LENGTH_SHORT).show();
            etJindu.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etAddressofdetil.getText().toString())){
            Toast.makeText(getContext(), "详细地址不能为空", Toast.LENGTH_SHORT).show();
            etAddressofdetil.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etFarendaibiao.getText().toString())) {
            Toast.makeText(getContext(), "法人代表不能为空", Toast.LENGTH_SHORT).show();
            etFarendaibiao.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPhoneFaren.getText().toString())) {
            Toast.makeText(getContext(), "法人联系电话不能为空", Toast.LENGTH_SHORT).show();
            etPhoneFaren.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etIdcardoffaren.getText().toString())){
            Toast.makeText(getContext(), "法人身份证号不能为空", Toast.LENGTH_SHORT).show();
            etAddressofdetil.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etFuzeren.getText().toString())) {
            Toast.makeText(getContext(), "负责人不能为空", Toast.LENGTH_SHORT).show();
            etFuzeren.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPhoneoffuzeren.getText().toString())) {
            Toast.makeText(getContext(), "负责人联系电话不能为空", Toast.LENGTH_SHORT).show();
            etPhoneoffuzeren.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(etYinyongdaima.getText().toString())) {
            Toast.makeText(getContext(), "统一社会信用代码不能为空", Toast.LENGTH_SHORT).show();
            etYinyongdaima.requestFocus();
            return false;
        }
        if (!isTyCode(etYinyongdaima.getText().toString())) {
            Toast.makeText(getContext(), "统一社会信用代码错误", Toast.LENGTH_SHORT).show();
            etYinyongdaima.requestFocus();
            return false;
        }
        if (!cbJi.isChecked() && !cbNiu.isChecked() && !cbQita.isChecked() && !cbYa.isChecked() && !cbYang.isChecked() && !cbZhu.isChecked()) {
            Toast.makeText(getContext(), "屠宰种类最少选一个", Toast.LENGTH_SHORT).show();
            cbJi.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etTuzainengli.getText().toString())) {
            Toast.makeText(getContext(), "年屠宰能力不能为空", Toast.LENGTH_SHORT).show();
            etTuzainengli.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etZibenofzhuce.getText().toString())) {
            Toast.makeText(getContext(), "注册资本(万元)不能为空", Toast.LENGTH_SHORT).show();
            etZibenofzhuce.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etNumberofqyyg.getText().toString())) {
            Toast.makeText(getContext(), "企业员工数不能为空", Toast.LENGTH_SHORT).show();
            etNumberofqyyg.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etNumzcsy.getText().toString())) {
            Toast.makeText(getContext(), "驻场官方兽医人数不能为空", Toast.LENGTH_SHORT).show();
            etNumzcsy.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(etNumberofpzjyy.getText().toString())) {
            Toast.makeText(getContext(), "品质检验员数量不能为空", Toast.LENGTH_SHORT).show();
            etNumberofpzjyy.requestFocus();
            return false;
        }

        if (!cbQita.isChecked() && !cbYa.isChecked() && !cbJi.isChecked() && !cbNiu.isChecked() && !cbYang.isChecked() && !cbZhu.isChecked()) {
            Toast.makeText(getContext(), "屠宰种类最少选一个", Toast.LENGTH_SHORT).show();
            cbYa.requestFocus();
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
            if (!isPwdType(etPwd.getText().toString())) {
                Toast.makeText(getContext(), "密码格式不正确", Toast.LENGTH_SHORT).show();
                etPwd.requestFocus();
                return false;
            }
        //两次密码对比
        if (!TextUtils.isEmpty(etPwd.getText().toString()) && !TextUtils.isEmpty(etPwdEnsure.getText().toString()))
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
        if (!TextUtils.isEmpty(etPhoneFaren.getText().toString()))
            if (!OtherUtil.isPhoneNumber(etPhoneFaren.getText().toString())) {
                Toast.makeText(getContext(), "填写正确的法人联系电话", Toast.LENGTH_SHORT).show();
                etPhoneFaren.requestFocus();
                return false;
            }
        if (!TextUtils.isEmpty(etPhoneoffuzeren.getText().toString()))
            if (!OtherUtil.isPhoneNumber(etPhoneoffuzeren.getText().toString())) {
                Toast.makeText(getContext(), "填写正确的负责人联系电话", Toast.LENGTH_SHORT).show();
                etPhoneoffuzeren.requestFocus();
                return false;
            }
        return true;
    }

    //验证密码格式
    public boolean isPwdType(String pwd) {
        //^[A-Za-z0-9@]{6,16}
        String str = "^[A-Za-z0-9@]{6,16}";
        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(pwd);

        return m.matches();
    }

    public boolean isTyCode(String code) {
        String str = "^([A-Z0-9]{15})$|^([A-Z0-9]{18})$|^([A-Z0-9]{20})$";
        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(code);

        return m.matches();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String time = year + "-" + (month + 1) + "-" + dayOfMonth;
        curEt.setText(time);
    }
}
