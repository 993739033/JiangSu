package com.wyw.jiangsu.activity.chaxun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.RuchangQuerenSearchActivity;
import com.wyw.jiangsu.activity.SpinerPopWindow;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.RuChangChaXunBean;
import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.bean.RuChangQuerenUploadBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.AddressDialog;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IRuChangQuerenActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.RuChangQuerenActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/11.
 */
public class RuChangQuerenActivity extends MVPActivity<RuChangQuerenActivityPresenter> implements IRuChangQuerenActivity, ITime, AddressDialog.IOnDecideListener {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_tuzhaiName)
    EditText et_tuzhaiName;
    @BindView(R.id.btn_tuzaichang)
    Button btnTuzaichang;
    @BindView(R.id.et_tuzaichang_time)
    TextView etTuzaichangTime;
    @BindView(R.id.img_tuzaichang_time)
    ImageView img_tuzaichang_time;
    @BindView(R.id.et_tuzaichang_query)
    EditText etTuzaichangQuery;
    @BindView(R.id.btn_tuzaichang_query)
    ImageView btnTuzaichangQuery;
    @BindView(R.id.et_tuzaichang_huozhuname)
    EditText etTuzaichangHuozhuname;
    @BindView(R.id.et_tuzaichang_phone)
    EditText etTuzaichangPhone;
    @BindView(R.id.tv_zhonglei)
    TextView tv_zhonglei;
    @BindView(R.id.tv_shuliang)
    TextView tv_shuliang;
    @BindView(R.id.tv_yiban)
    TextView tv_yiban;
    @BindView(R.id.tv_shiyan)
    TextView tv_shiyan;
    @BindView(R.id.tv_xiaodu)
    TextView tv_xiaodu;
    @BindView(R.id.et_tuzaichang_number)
    EditText etTuzaichangNumber;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.et_chouyan_number)
    EditText etChouyanNumber;
    @BindView(R.id.et_laike_yinxing)
    EditText etLaikeYinxing;
    @BindView(R.id.et_laike_yangxing)
    EditText etLaikeYangxing;
    @BindView(R.id.et_kelun_yinxing)
    EditText etKelunYinxing;
    @BindView(R.id.et_kelun_yangxing)
    EditText etKelunYangxing;
    @BindView(R.id.et_shading_yinxing)
    EditText etShadingYinxing;
    @BindView(R.id.et_shading_yangxing)
    EditText etShadingYangxing;
    @BindView(R.id.et_chouyan_jianceren)
    EditText etChouyanJianceren;
    @BindView(R.id.et_chouyan_beizhu)
    EditText etChouyanBeizhu;
    @BindView(R.id.et_result_deal)
    EditText etResultDeal;
    @BindView(R.id.et_ruchang_number)
    EditText etRuchangNumber;
    @BindView(R.id.et_daizai_quanhao)
    EditText etDaizaiQuanhao;
    @BindView(R.id.et_chuli_number)
    EditText etChuliNumber;
    @BindView(R.id.et_jianyichuli_bianhao)
    EditText etJianyichuliBianhao;
    @BindView(R.id.rb_jianyizhengming_yes)
    RadioButton rb_jianyizhengming_yes;
    @BindView(R.id.rb_jianyizhengming_no)
    RadioButton rb_jianyizhengming_no;
    @BindView(R.id.rb_peidaierbiao_yes)
    RadioButton rb_peidaierbiao_yes;
    @BindView(R.id.rb_peidaierbiao_no)
    RadioButton rb_peidaierbiao_no;
    @BindView(R.id.rb_tongdaodiaoru_no)
    RadioButton rb_tongdaodiaoru_no;
    @BindView(R.id.rb_tongdaodiaoru_yes)
    RadioButton rb_tongdaodiaoru_yes;
    @BindView(R.id.rb_zhengchang)
    RadioButton rb_zhengchang;
    @BindView(R.id.rb_yichang)
    RadioButton rb_yichang;
    @BindView(R.id.et_chayan_renyuan)
    EditText etChayanRenyuan;
    @BindView(R.id.et_jilu_renyuan)
    EditText etJiluRenyuan;
    @BindView(R.id.btn_baocun)
    Button btnBaocun;
    @BindView(R.id.et_laiyuandi_xiangxi)
    EditText et_laiyuandi_xiangxi;
    @BindView(R.id.ll_ruchang_chouyan)
    LinearLayout llRuchangChouyan;
    @BindView(R.id.ll_zhidingtongdao)
    LinearLayout ll_zhidingtongdao;
    @BindView(R.id.scroll_v)
    ScrollView scrollV;
    private TimePresenter timePresenter;
    private String[] unit = {"头", "只", "匹", "羽", "箱"};
    private RuChangQuerenUploadBean uploadBean;//要上传的实体类
    private String hour;
    private String id;
    private SpinerPopWindow<String> mSpinerPopWindow, mSpinerPopWindow2, mSpinerPopWindow3, mSpinerPopWindow4, mSpinerPopWindow5;
    private List<String> anim_typelist;
    List<String> unitList = new ArrayList<>();
    List<String> yibanList = new ArrayList<>();
    List<String> shiyanList = new ArrayList<>();
    List<String> xiaoduList = new ArrayList<>();
    private String[] buhege_jiance2;
    private String[] shiyanshiArray;
    private String[] anim_type;
    private String[] buhege_chuli;
    private AddressDialog dialog;
    private String provinces, citys, districts;
    private DataSelectDialog dataSelectDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruchang_queren);
        ButterKnife.bind(this);
    }


    private void init() {
        buhege_chuli = getResources().getStringArray(R.array.buhege_chuli);
        anim_type = getResources().getStringArray(R.array.anim_type);
        shiyanshiArray = getResources().getStringArray(R.array.buhege_jiance);
        buhege_jiance2 = getResources().getStringArray(R.array.buhege_jiance2);
        anim_typelist = new ArrayList<>();
        for (String array : anim_type) {
            anim_typelist.add(array);
        }
        for (String array : buhege_chuli) {
            yibanList.add(array);
        }
        for (String array : shiyanshiArray) {
            shiyanList.add(array);
        }
        xiaoduList.add("0.05%过氧乙酸");
        tv_xiaodu.setText(xiaoduList.get(0));

        tv_zhonglei.setOnClickListener(clickListener);
        tv_shuliang.setOnClickListener(clickListener);
        tv_yiban.setOnClickListener(clickListener);
        tv_shiyan.setOnClickListener(clickListener);
        tv_xiaodu.setOnClickListener(clickListener);

        mSpinerPopWindow = new SpinerPopWindow<String>(this, anim_typelist, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSpinerPopWindow.dismiss();
                tv_zhonglei.setText(anim_typelist.get(position));
                unitList.clear();
                setUnit(anim_typelist.get(position));
                tv_shuliang.setText(unitList.get(0));
                if (anim_typelist.get(position).toString().contains("猪") || anim_typelist.get(position).toString().contains("牛")
                        || anim_typelist.get(position).toString().contains("羊")) {
                    llRuchangChouyan.setVisibility(View.VISIBLE);
                } else {
                    llRuchangChouyan.setVisibility(View.GONE);
                }

            }
        });//动物种类
        mSpinerPopWindow2 = new SpinerPopWindow<String>(this, unitList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSpinerPopWindow2.dismiss();
                tv_shuliang.setText(unitList.get(position));
            }
        });//动物数量及单位
        mSpinerPopWindow3 = new SpinerPopWindow<String>(this, yibanList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSpinerPopWindow3.dismiss();
                tv_yiban.setText(yibanList.get(position));
                shiyanList.clear();
                if (yibanList.get(position).toString().equals("一般处理")) {
                    for (String array : shiyanshiArray) {
                        shiyanList.add(array);
                    }
                } else {
                    for (String array : buhege_jiance2) {
                        shiyanList.add(array);
                    }
                }
                tv_shiyan.setText(shiyanList.get(0));
            }
        });//一般处理
        mSpinerPopWindow4 = new SpinerPopWindow<String>(this, shiyanList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSpinerPopWindow4.dismiss();
                tv_shiyan.setText(shiyanList.get(position));
            }
        });//实验室检测
        mSpinerPopWindow5 = new SpinerPopWindow<String>(this, xiaoduList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSpinerPopWindow5.dismiss();
                tv_xiaodu.setText(xiaoduList.get(position));
            }
        });//消毒

        mSpinerPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextImage(R.drawable.drop_down, R.id.tv_zhonglei);
            }
        });
        mSpinerPopWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextImage(R.drawable.drop_down, R.id.tv_shuliang);

            }
        });
        mSpinerPopWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextImage(R.drawable.drop_down, R.id.tv_yiban);
            }
        });
        mSpinerPopWindow4.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextImage(R.drawable.drop_down, R.id.tv_shiyan);
            }
        });
        mSpinerPopWindow5.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextImage(R.drawable.drop_down, R.id.tv_xiaodu);
            }
        });
    }


    @Override
    protected RuChangQuerenActivityPresenter createPresenter() {
        return new RuChangQuerenActivityPresenter(this);
    }

    @Override
    public void bindData() {
        scrollV.setVerticalScrollBarEnabled(false);
        setTitle("入场查验登记");
        if (getPresenter().getUser().getFUDWTYPE().equals("dwtzc")) {
            et_tuzhaiName.setText(getPresenter().getUser().getFUSEENAME());
            id = getPresenter().getUser().getFUSEEID() + "";
        }
        etJiluRenyuan.setText(getPresenter().getUser().getFUNAME());//刚进来，就有值
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        setUnit("");
        init();
        dialog = new AddressDialog(this);
        dialog.setOnDecideListener(this);

        initDialog();
    }

    private void initDialog() {
        dataSelectDialog = new DataSelectDialog(this);
        dataSelectDialog.setTitle("请选择");
    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_yiban:
                    dataSelectDialog.setDatas(yibanList);
                    dataSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tv_yiban.setText(data);
                            shiyanList.clear();
                            if (yibanList.get(dataSelectDialog.getNowIndex()).toString().equals("一般处理")) {
                                for (String array : shiyanshiArray) {
                                    shiyanList.add(array);
                                }
                            } else {
                                for (String array : buhege_jiance2) {
                                    shiyanList.add(array);
                                }
                            }
                            tv_shiyan.setText(shiyanList.get(0));

                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    dataSelectDialog.show();

//                    mSpinerPopWindow3.setWidth(400);
//                    mSpinerPopWindow3.showAsDropDown(tv_yiban);
//                    setTextImage(R.drawable.drop_top, R.id.tv_yiban);
                    break;
                case R.id.tv_shiyan:
                    dataSelectDialog.setDatas(shiyanList);
                    dataSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tv_shiyan.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    dataSelectDialog.show();

//                    mSpinerPopWindow4.setWidth(400);
//                    mSpinerPopWindow4.showAsDropDown(tv_shiyan);
//                    setTextImage(R.drawable.drop_top, R.id.tv_shiyan);
                    break;
                case R.id.tv_xiaodu:
                    dataSelectDialog.setDatas(xiaoduList);
                    dataSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tv_xiaodu.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    dataSelectDialog.show();


//                    mSpinerPopWindow5.setWidth(400);
//                    mSpinerPopWindow5.showAsDropDown(tv_xiaodu);
//                    setTextImage(R.drawable.drop_top, R.id.tv_xiaodu);
                    break;
                case R.id.tv_zhonglei:
                    dataSelectDialog.setDatas(anim_typelist);
                    dataSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tv_zhonglei.setText(data);
                            unitList.clear();
                            setUnit(anim_typelist.get(dataSelectDialog.getNowIndex()));
                            tv_shuliang.setText(unitList.get(0));
                            if (anim_typelist.get(dataSelectDialog.getNowIndex()).toString().contains("猪") || anim_typelist.get(dataSelectDialog.getNowIndex()).toString().contains("牛")
                                    || anim_typelist.get(dataSelectDialog.getNowIndex()).toString().contains("羊")) {
                                llRuchangChouyan.setVisibility(View.VISIBLE);
                            } else {
                                llRuchangChouyan.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    dataSelectDialog.show();

//                    mSpinerPopWindow.setWidth(400);
//                    mSpinerPopWindow.showAsDropDown(tv_zhonglei);
//                    setTextImage(R.drawable.drop_top, R.id.tv_zhonglei);
                    break;
                case R.id.tv_shuliang:
                    dataSelectDialog.setDatas(unitList);
                    dataSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tv_shuliang.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    dataSelectDialog.show();
//                    mSpinerPopWindow2.setWidth(300);
//                    mSpinerPopWindow2.showAsDropDown(tv_shuliang);
//                    setTextImage(R.drawable.drop_top, R.id.tv_shuliang);
                    break;
            }
        }
    };

    private void setTextImage(int resId, int id) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 必须设置图片大小，否则不显示  
        switch (id) {
            case R.id.tv_yiban:
                tv_yiban.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.tv_shiyan:
                tv_shiyan.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.tv_xiaodu:
                tv_xiaodu.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.tv_zhonglei:
                tv_zhonglei.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.tv_shuliang:
                tv_shuliang.setCompoundDrawables(null, null, drawable, null);
                break;
        }


    }

    /**
     * 设置单位  头", "只", "匹", "羽", "箱
     */
    public void setUnit(String animType) {

        switch (animType) {
            case "猪":
            case "牛":
            case "鹿":
            case "骡":
            case "驴":
            case "骆驼":
                unitList.add(unit[0]);
                break;
            case "羊":
            case "犬":
            case "猫":
            case "兔":
                unitList.add(unit[1]);
                break;
            case "马":
                unitList.add(unit[2]);
                break;
            case "鸡":
            case "鸭":
            case "鹅":
            case "鹌鹑":
            case "鸽":
                unitList.add(unit[3]);
                break;
            case "蜜蜂":
                unitList.add(unit[4]);
                break;
            case "其他":
                for (String array : unit) {
                    unitList.add(array);
                }
                break;
            default:
                unitList.add(unit[0]);
                break;
        }
    }


    @Override
    public void bindListener() {

        //不合格处理,,改变数据
        etChuliNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etRuchangNumber.getText().toString()) && !TextUtils.isEmpty(etResultDeal.getText().toString())
                        && !TextUtils.isEmpty(etChuliNumber.getText().toString())) {
                    etRuchangNumber.setText((Integer.parseInt(etResultDeal.getText().toString())
                            - Integer.parseInt(etChuliNumber.getText().toString())) + "");
                }

                if (etChuliNumber.getText().toString().length() == 0 || "0".equals(etChuliNumber.getText().toString())) {
                    etJianyichuliBianhao.setText("");
                } else {
                    //通知单编号
                    getPresenter().getJiludanhao();
                }
            }
        });

        etTuzaichangQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().startsWith("32") && !s.toString().equals("")) {
                    ll_zhidingtongdao.setVisibility(View.VISIBLE);
                } else {
                    ll_zhidingtongdao.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //选择
        btnTuzaichang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(RuChangQuerenActivity.this, RuchangQuerenSearchActivity.class), Constance.ACTIVITY_CODE);
            }
        });
        img_tuzaichang_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeSelectDialog dialog = new TimeSelectDialog(view.getContext(), "时间选择").initTime();
                dialog.setTimePickListener(new TimeSelectDialog.TimePickListener() {
                    @Override
                    public void choseTime(String time) {
                        etTuzaichangTime.setText(time);
                    }
                });
                dialog.show();
            }
        });
        //查询
        btnTuzaichangQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etQuery = etTuzaichangQuery.getText().toString();
                if (!TextUtils.isEmpty(etQuery)) {
                    getPresenter().getQuery(etQuery);
                } else {
                    Toast.makeText(MyApplication.getContext(), "请输入检疫证明号码", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnBaocun.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //保存
                if (check()) {
                    showNormalDialog();
                }
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(RuChangQuerenActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                RuChangChaYanQueryBean.DataListBean bean = (RuChangChaYanQueryBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                et_tuzhaiName.setText(bean.getFhName());
                id = bean.getFStId();
            }
        }
    }

    /**
     * 要上传的数据
     */
    public RuChangQuerenUploadBean getData() {
        uploadBean = new RuChangQuerenUploadBean();
        uploadBean.seteOwner(et_tuzhaiName.getText().toString());//屠宰场名称
        uploadBean.seteDate(etTuzaichangTime.getText().toString());//时间
        uploadBean.seteNo(etTuzaichangQuery.getText().toString());//检疫证明号码
        uploadBean.seteCOwner(etTuzaichangHuozhuname.getText().toString());//货主(经纪人)
        uploadBean.setePhone(etTuzaichangPhone.getText().toString());//联系电话
        uploadBean.seteAnimal(tv_zhonglei.getText().toString());//动物种类
        uploadBean.seteNum(Integer.parseInt(etTuzaichangNumber.getText().toString()));//数量
        uploadBean.seteUnit(tv_shuliang.getText().toString());//单位
        uploadBean.seteSeedbed(provinces);//省
        uploadBean.seteShi(citys);//市
        uploadBean.seteXian(districts);//县
        uploadBean.seteZhen(et_laiyuandi_xiangxi.getText().toString());//镇
        if (rb_jianyizhengming_yes.isChecked()) {
            uploadBean.seteCertificate(rb_jianyizhengming_yes.getText().toString()); //是否附有检疫证明
        } else {
            uploadBean.seteCertificate(rb_jianyizhengming_no.getText().toString()); //是否附有检疫证明
        }
        if (rb_peidaierbiao_yes.isChecked()) {
            uploadBean.seteIdentifica(rb_peidaierbiao_yes.getText().toString()); //是否佩戴耳标
        } else {
            uploadBean.seteIdentifica(rb_peidaierbiao_no.getText().toString()); //是否佩戴耳标
        }
        if (etTuzaichangQuery.getText().toString().startsWith("32")) {
            uploadBean.seteConform(""); //是否经指定通道调入
        } else {
            if (rb_tongdaodiaoru_yes.isChecked()) {
                uploadBean.seteConform(rb_tongdaodiaoru_yes.getText().toString()); //是否经指定通道调入
            } else {
                uploadBean.seteConform(rb_tongdaodiaoru_no.getText().toString()); //是否经指定通道调入
            }

        }
        if (rb_zhengchang.isChecked()) {
            uploadBean.seteHealthy(rb_zhengchang.getText().toString()); //临床检查情况;
        } else {
            uploadBean.seteHealthy(rb_yichang.getText().toString()); //临床检查情况;
        }
        uploadBean.seteSum(Integer.parseInt(etResultDeal.getText().toString())); //结果处理(总数)
        uploadBean.setRCSL(Integer.parseInt(etRuchangNumber.getText().toString())); //入场数量
        uploadBean.seteDZQ(etDaizaiQuanhao.getText().toString()); //待宰圈号
        if (TextUtils.isEmpty(etChuliNumber.getText().toString())) {
            etChuliNumber.setText(0);
        }
        uploadBean.setCLSL(Integer.parseInt(etChuliNumber.getText().toString())); //处理数量
        uploadBean.setCLFS(tv_yiban.getText().toString());//不合格处理
        uploadBean.seteLcjcjg(tv_shiyan.getText().toString());//不合格检测
        uploadBean.setCode(etJianyichuliBianhao.getText().toString());//检疫处理通知单编号
        uploadBean.setXd(tv_xiaodu.getText().toString());//消毒
        uploadBean.seteRummager(etChayanRenyuan.getText().toString());//查验人员
        uploadBean.settIdBc(Integer.parseInt(id));//赋值过来的ID
        uploadBean.setJLRY(etJiluRenyuan.getText().toString());//记录人员
        uploadBean.setXs(Integer.parseInt(hour));//小时

        if (tv_zhonglei.getText().toString().contains("猪") || tv_zhonglei.getText().toString().contains("牛")
                || tv_zhonglei.getText().toString().contains("羊")) {
            uploadBean.setCJSL(etChouyanNumber.getText().toString().length() == 0 ? 0 : Integer.parseInt(etChouyanNumber.getText().toString()));//抽验数量
            uploadBean.setLYIN(etLaikeYinxing.getText().toString().length() == 0 ? 0 : Integer.parseInt(etLaikeYinxing.getText().toString()));// 莱克多巴胺-阴性
            uploadBean.setLYANG(etLaikeYangxing.getText().toString().length() == 0 ? 0 : Integer.parseInt(etLaikeYangxing.getText().toString()));// 莱克多巴胺-阳性
            uploadBean.setKYIN(etKelunYinxing.getText().toString().length() == 0 ? 0 : Integer.parseInt(etKelunYinxing.getText().toString()));// 克伦特罗-阴性
            uploadBean.setKYANG(etKelunYangxing.getText().toString().length() == 0 ? 0 : Integer.parseInt(etKelunYangxing.getText().toString()));// 克伦特罗-阳性
            uploadBean.setSYIN(etShadingYinxing.getText().toString().length() == 0 ? 0 : Integer.parseInt(etShadingYinxing.getText().toString()));// 沙丁胺醇-阳性
            uploadBean.setSYANG(etShadingYangxing.getText().toString().length() == 0 ? 0 : Integer.parseInt(etShadingYangxing.getText().toString()));// 沙丁胺醇-阳性
            uploadBean.setJCR(etChouyanJianceren.getText().toString());// 检测人
            uploadBean.setBZ(etChouyanBeizhu.getText().toString());// 备注
        }

        return uploadBean;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeTintColor(EditText et) {
        if (et.requestFocus() == true) {
            et.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
        } else
            et.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule)));
        if (et.requestFocus() == false) {
            et.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule)));
        }
    }

    //检查
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean check() {

        if (isEmpty(getString(et_tuzhaiName))) {
            showToast("屠宰场名称不能为空");
            et_tuzhaiName.requestFocus();
            focusKeywordView(et_tuzhaiName);
            changeTintColor(et_tuzhaiName);
            return false;
        }
        changeTintColor(et_tuzhaiName);

        if (isEmpty(getString(etTuzaichangPhone))) {
            showToast("联系电话不能为空");
            etTuzaichangPhone.requestFocus();
            focusKeywordView(etTuzaichangPhone);
            changeTintColor(etTuzaichangPhone);
            return false;
        }
        changeTintColor(etTuzaichangPhone);

        if (isEmpty(getString(etTuzaichangNumber))) {
            showToast("数量不能为空");
            etTuzaichangNumber.requestFocus();
            focusKeywordView(etTuzaichangNumber);
            changeTintColor(etTuzaichangNumber);
            return false;
        }
        changeTintColor(etTuzaichangNumber);

        if (TextUtils.isEmpty(etResultDeal.getText().toString())) {
            showToast("处理总数量不能为空");
            etResultDeal.requestFocus();//获取焦点
            focusKeywordView(etResultDeal);
            changeTintColor(etResultDeal);
            return false;
        }
        changeTintColor(etResultDeal);

        if (TextUtils.isEmpty(etChuliNumber.getText().toString())) {
            showToast("处理数量不能为空");
            etChuliNumber.requestFocus();//获取焦点
            focusKeywordView(etChuliNumber);
            changeTintColor(etChuliNumber);
            return false;
        }
        changeTintColor(etChuliNumber);

        if (TextUtils.isEmpty(etRuchangNumber.getText().toString())) {
            showToast("入场数量不能为空");
            etRuchangNumber.requestFocus();//获取焦点
            focusKeywordView(etRuchangNumber);
            changeTintColor(etRuchangNumber);
            return false;
        }

        changeTintColor(etRuchangNumber);

        if ((Integer.parseInt(etRuchangNumber.getText().toString()) + Integer.parseInt(etChuliNumber.getText().toString()))
                > Integer.parseInt(etResultDeal.getText().toString())) {
            showToast("入场数量和处理数量之和不能大于总数");
            etResultDeal.requestFocus();//获取焦点
            focusKeywordView(etResultDeal);
            etResultDeal.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            return false;
        }
        changeTintColor(etResultDeal);


        if (isEmpty(getString(etTuzaichangQuery))) {
            showToast("检疫证明号码不能为空");
            etTuzaichangQuery.requestFocus();
            focusKeywordView(etTuzaichangQuery);
            changeTintColor(etTuzaichangQuery);
            return false;
        }
        changeTintColor(etTuzaichangQuery);


        if (isEmpty(getString(etTuzaichangHuozhuname))) {
            showToast("货主不能为空");
            etTuzaichangHuozhuname.requestFocus();
            focusKeywordView(etTuzaichangHuozhuname);
            changeTintColor(etTuzaichangHuozhuname);
            return false;
        }
        changeTintColor(etTuzaichangHuozhuname);

        if (isEmpty(getString(etRuchangNumber))) {
            etRuchangNumber.setText("0");
            return false;
        }
        if (isEmpty(getString(etChuliNumber))) {
            etRuchangNumber.setText("0");
            return false;
        }


        if (isEmpty(getString(etChayanRenyuan))) {
            showToast("查验人员不能为空");
            etChayanRenyuan.requestFocus();
            focusKeywordView(etChayanRenyuan);
            changeTintColor(etChayanRenyuan);
            return false;
        }
        changeTintColor(etChayanRenyuan);

        if (isEmpty(getString(etJiluRenyuan))) {
            showToast("记录人员不能为空");
            etJiluRenyuan.requestFocus();
            focusKeywordView(etJiluRenyuan);
            changeTintColor(etJiluRenyuan);
            return false;
        }
        changeTintColor(etJiluRenyuan);

        if (tv_zhonglei.getText().toString().contains("请选择")) {
            Toast.makeText(MyApplication.getContext(), "请选择动物种类", Toast.LENGTH_SHORT).show();
            tv_zhonglei.requestFocus();
            tv_zhonglei.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            return false;
        }

        if (tv_shuliang.getText().toString().contains("请选择")) {
            Toast.makeText(MyApplication.getContext(), "请选择单位", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (address.getText().toString().contains("请选择")) {
            Toast.makeText(MyApplication.getContext(), "请选择来源地", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (isEmpty(getString(etRuchangNumber))) {
            etRuchangNumber.setText("0");
        }
        if (isEmpty(getString(etLaikeYinxing))) {
            etLaikeYinxing.setText("0");
        }
        if (isEmpty(getString(etLaikeYangxing))) {
            etLaikeYangxing.setText("0");
        }
        if (isEmpty(getString(etKelunYinxing))) {
            etKelunYinxing.setText("0");
        }
        if (isEmpty(getString(etKelunYangxing))) {
            etKelunYangxing.setText("0");
        }
        if (isEmpty(getString(etShadingYinxing))) {
            etShadingYinxing.setText("0");
        }
        if (isEmpty(getString(etShadingYangxing))) {
            etShadingYangxing.setText("0");
        }
        return true;
    }

    @Override
    public void setTime(String time, long longTime) {
        if (!TextUtils.isEmpty(time)) {
            String riqi = time.substring(0, 11);
            String date = riqi.replaceAll("-", "/");
            hour = time.substring(11, 13);
            etTuzaichangTime.setText(date + " " + hour + "时");
        }
    }

    @Override
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        Toast.makeText(MyApplication.getContext(), "保存完成", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void chaxun(RuChangChaXunBean.DataBean bean) {

        if (null != bean.getResult()) {
            Toast.makeText(MyApplication.getContext(), bean.getResult(), Toast.LENGTH_SHORT).show();
            return;
        }

        etTuzaichangNumber.setText(bean.getENum());//数量
        etResultDeal.setText(bean.getESum());//结果处理(总数)
        etRuchangNumber.setText(bean.getESum());//入场数量
        etTuzaichangHuozhuname.setText(bean.getECOwner());//货主(经纪人)
        address.setText(bean.getEZhen());//镇
        etTuzaichangPhone.setText(bean.getEPhone());//联系电话

    }

    @Override
    public void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean) {
        String result = dataListBean.getResult();     //检疫处理通知单编号
        etJianyichuliBianhao.setText(result);
    }

    /**
     *   
     *  * 得到输入框的文字  
     *  * @return  
     *  
     */
    public String getKeywordText(EditText edt) {
        return edt.getText().toString().trim();
    }

    /**
     *   
     *  * 将焦点移到输入框，弹起输入法  
     *  
     */
    public void focusKeywordView(EditText edt) {
        if (edt != null) {
            edt.requestFocus();
            edt.setSelection(getKeywordText(edt).length());
            showInputMethod(edt, true, 500);
        }
    }

    /**
     *   
     *  * 弹起输入法  
     *  * @param edit  
     *  * @param delay  
     *  * @param delayTime  
     *  
     */
    private void showInputMethod(final EditText edit, boolean delay, int delayTime) {
        if (delay) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);

                    if (imm != null) {
                        imm.showSoftInput(edit, 0);
                    }
                }
            }, delayTime);
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edit, 0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog.cancel();
        dialog = null;
    }

    @Override
    public void onCancel(View v) {
        dialog.dismiss();
    }

    @Override
    public void onSure(View v, String province, String city, String district) {
        provinces = province;
        citys = city;
        districts = district;
        String finalAddress = provinces + "" + citys + "" + districts;
        address.setText(finalAddress);
        dialog.dismiss();
    }
}
