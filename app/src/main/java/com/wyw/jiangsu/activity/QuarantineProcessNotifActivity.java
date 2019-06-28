package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.QuarantineProcessNotifListBean;
import com.wyw.jiangsu.bean.QuarantineProcessNotifListBeanDetil;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bean.upload.UploadQuarantineProcessNotBeanDetil;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IQuarantineProcessNotifActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.QuarantineProcessNotifActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.OtherUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 检查处理通知单详情 用来打印的
 */
public class QuarantineProcessNotifActivity extends MVPActivity<QuarantineProcessNotifActivityPresenter> implements IQuarantineProcessNotifActivity, ITime {


    @BindView(R.id.et_quarantine_no)
    EditText etQuarantineNo;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_goods_name)
    EditText etGoodsName;
    @BindView(R.id.et_goods_unit)
    EditText etGoodsUnit;
    @BindView(R.id.textView2)
    TextView textView2;
    //    @BindView(R.id. )
//    Spinner spingAnimType;
    @BindView(R.id.et_goods_count)
    EditText etGoodsCount;
    @BindView(R.id.et_check_type)
    EditText etCheckType;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.tv_process_declare1)
    TextView tvProcessDeclare1;
    //    @BindView(R.id.spinner1_1)
//    Spinner spinner11;
//    @BindView(R.id.spinner1_2)
//    Spinner spinner12;
    @BindView(R.id.tv_process_declare2)
    TextView tvProcessDeclare2;
//    @BindView(R.id.spinner2_1)
//    Spinner spinner21;
//    @BindView(R.id.spinner2_2)
//    Spinner spinner22;
    @BindView(R.id.tv_process_declare3)
    TextView tvProcessDeclare3;
//    @BindView(R.id.spinner3_1)
//    Spinner spinner31;
//    @BindView(R.id.spinner3_2)
//    Spinner spinner32;
    @BindView(R.id.tv_process_declare4)
    TextView tvProcessDeclare4;
//    @BindView(R.id.spinner4_1)
//    Spinner spinner41;
//    @BindView(R.id.spinner4_2)
//    Spinner spinner42;
    @BindView(R.id.et_party_name)
    EditText etPartyName;
    @BindView(R.id.et_party_phone)
    EditText etPartyPhone;
    @BindView(R.id.et_veterinarian_name)
    EditText etVeterinarianName;
    @BindView(R.id.et_supervision_phone)
    EditText etSupervisionPhone;
    @BindView(R.id.bt_print)
    Button btPrint;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.SV)
    ScrollView SV;

    @BindView(R.id.tv_spinner1_1)
    TextView tv_spinner1_1;
    @BindView(R.id.tv_spinner1_2)
    TextView tv_spinner1_2;
    @BindView(R.id.tv_spinner2_1)
    TextView tv_spinner2_1;
    @BindView(R.id.tv_spinner2_2)
    TextView tv_spinner2_2;
    @BindView(R.id.tv_spinner3_1)
    TextView tv_spinner3_1;
    @BindView(R.id.tv_spinner3_2)
    TextView tv_spinner3_2;
    @BindView(R.id.tv_spinner4_1)
    TextView tv_spinner4_1;
    @BindView(R.id.tv_spinner4_2)
    TextView tv_spinner4_2;


    private DataSelectDialog clxxDialog;//处理选项
    private DataSelectDialog clfsDialog1;//处理方式1
    private DataSelectDialog clfsDialog2;//处理方式2

    private String[] itemNone = {"请选择"};
    private String[] item1 = {"一般处理  ", "生物安全处理"};
    private String[] item2_1 = {"实验室检测", "隔离", "治疗", "其他"};
    private String[] item2_2 = {"焚毁", "掩埋", "化制", "消毒"};
//    private String[] itme_anim_type = {"动物", "繁殖材料"};

    private QuarantineProcessNotifListBean.DataListBean dataListBean;
    private UploadQuarantineProcessNotBeanDetil uploadBeanDetil;
    TimePresenter timePresenter;
    private Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarantine_process_notif);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected QuarantineProcessNotifActivityPresenter createPresenter() {
        return new QuarantineProcessNotifActivityPresenter(QuarantineProcessNotifActivity.this);
    }

    @Override
    public void bindData() {
        setTitle("检疫处理通知单");
        getPresenter().getShouyiJiandu();
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        intent1 = getIntent();
        dataListBean = (QuarantineProcessNotifListBean.DataListBean) intent1.getSerializableExtra(Constance.ACTIVITY_DATA);
        //如果是这个表名，则是繁殖材料
        if (dataListBean.getMyuse().equals("AH_AEmbryoQuarantine")) {
            etAnimType.setText("繁殖材料");
        } else {         //为动物
            etAnimType.setText("动物");
        }
        getPresenter().getNetData(dataListBean.getMyuse(), dataListBean.getFStId());
        uploadBeanDetil = new UploadQuarantineProcessNotBeanDetil();

        //处理类型
        clxxDialog = new DataSelectDialog(this);
        clxxDialog.setTitle("请选择处理选项");
        clxxDialog.setDatas(item1);

        //处理方式1
        clfsDialog1 = new DataSelectDialog(this);
        clfsDialog1.setTitle("请选择处理方式");
        clfsDialog1.setDatas(item2_1);

        //处理方式2
        clfsDialog2 = new DataSelectDialog(this);
        clfsDialog2.setTitle("请选择处理方式");
        clfsDialog2.setDatas(item2_2);


        // TODO: 2017/12/22  
//        spinner11.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item1));
        // TODO: 2017/12/22  
//        spinner21.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item1));
        // TODO: 2017/12/22  
//        spinner31.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item1));
//        spinner41.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item1));
        // TODO: 2017/12/22  
//        spinner12.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
        // TODO: 2017/12/22  
//        spinner22.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
        // TODO: 2017/12/22  
//        spinner32.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
//        spinner42.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
//        spingAnimType.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, R.id.tv, itme_anim_type));
    }

    @Override
    public void bindListener() {
        //一级spinner监听
        // TODO: 2017/12/22  
//        spinner11.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        tvProcessDeclare1.setText("");
//                        spinner12.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
//                        break;
//                    case 1:
//                        spinner12.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_1));
//                        break;
//                    case 2:
//                        spinner12.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_2));
//                        break;
//                }
//            }
//        });

        tv_spinner1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clxxDialog.setCallBack(new DataSelectDialog.CallBack() {
                    @Override
                    public void enter(String data) {
                        tv_spinner1_1.setText(data);
                        tv_spinner1_2.setText("请选择");
                        tvProcessDeclare1.setText("");
                    }

                    @Override
                    public void cancel() {

                    }
                });
                clxxDialog.show();
            }
        });



        // TODO: 2017/12/22  
//        spinner21.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        tvProcessDeclare2.setText("");
//                        spinner22.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
//                        break;
//                    case 1:
//                        spinner22.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_1));
//                        break;
//                    case 2:
//                        spinner22.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_2));
//                        break;
//                }
//            }
//        });

        tv_spinner2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clxxDialog.setCallBack(new DataSelectDialog.CallBack() {
                    @Override
                    public void enter(String data) {
                        tv_spinner2_1.setText(data);
                        tv_spinner2_2.setText("请选择");
                        tvProcessDeclare2.setText("");
                    }

                    @Override
                    public void cancel() {

                    }
                });
                clxxDialog.show();
            }
        });

        // TODO: 2017/12/22  
//        spinner31.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        tvProcessDeclare3.setText("");
//                        spinner32.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
//                        break;
//                    case 1:
//                        spinner32.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_1));
//                        break;
//                    case 2:
//                        spinner32.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_2));
//                        break;
//                }
//            }
//        });

        tv_spinner3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clxxDialog.setCallBack(new DataSelectDialog.CallBack() {
                    @Override
                    public void enter(String data) {
                        tv_spinner3_1.setText(data);
                        tv_spinner3_2.setText("请选择");
                        tvProcessDeclare3.setText("");
                    }

                    @Override
                    public void cancel() {

                    }
                });
                clxxDialog.show();
            }
        });

        // TODO: 2017/12/22  
//        spinner41.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        tvProcessDeclare4.setText("");
//                        spinner42.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, itemNone));
//                        break;
//                    case 1:
//                        spinner42.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_1));
//                        break;
//                    case 2:
//                        spinner42.setAdapter(new ArrayAdapter<>(QuarantineProcessNotifActivity.this, R.layout.spinner_simple, tv, item2_2));
//                        break;
//                }
//            }
//        });


        tv_spinner4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clxxDialog.setCallBack(new DataSelectDialog.CallBack() {
                    @Override
                    public void enter(String data) {
                        tv_spinner4_1.setText(data);
                        tv_spinner4_2.setText("请选择");
                        tvProcessDeclare4.setText("");
                    }

                    @Override
                    public void cancel() {

                    }
                });
                clxxDialog.show();
            }
        });


        //二级spinner监听
        // TODO: 2017/12/22  
//        spinner12.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) tvProcessDeclare1.setText(generateString(spinner12));
//            }
//        });

        tv_spinner1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getString(tv_spinner1_1)))return;
                if (getString(tv_spinner1_1).equals(item1[0])) {
                 clfsDialog1.setCallBack(new DataSelectDialog.CallBack() {
                     @Override
                     public void enter(String data) {
                         tvProcessDeclare1.setText(generateString(data));
                         tv_spinner1_2.setText(data);
                     }

                     @Override
                     public void cancel() {

                     }
                 });
                    clfsDialog1.show();
                }else if (getString(tv_spinner1_1).equals(item1[1])){
                    clfsDialog2.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tvProcessDeclare1.setText(generateString(data));
                            tv_spinner1_2.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    clfsDialog2.show();
                }
            }
        });

        // TODO: 2017/12/22  
//        spinner22.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0 && !TextUtils.isEmpty(tvProcessDeclare2.getText().toString()))
//                    tvProcessDeclare2.setText(generateString2(spinner22));
//            }
//        });

        tv_spinner2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getString(tv_spinner2_1)))return;
                if (getString(tv_spinner2_1).equals(item1[0])) {
                    clfsDialog1.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tvProcessDeclare2.setText(generateString(data));
                            tv_spinner2_2.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    clfsDialog1.show();
                }else if (getString(tv_spinner2_1).equals(item1[1])){
                    clfsDialog2.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tvProcessDeclare2.setText(generateString(data));
                            tv_spinner2_2.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    clfsDialog2.show();
                }
            }
        });

        // TODO: 2017/12/22  
//        spinner32.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0 && !TextUtils.isEmpty(tvProcessDeclare3.getText().toString()))
//                    tvProcessDeclare3.setText(generateString3(spinner32));
//            }
//        });

        tv_spinner3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getString(tv_spinner3_1)))return;
                if (getString(tv_spinner3_1).equals(item1[0])) {
                    clfsDialog1.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tvProcessDeclare3.setText(generateString(data));
                            tv_spinner3_2.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    clfsDialog1.show();
                }else if (getString(tv_spinner3_1).equals(item1[1])){
                    clfsDialog2.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tvProcessDeclare3.setText(generateString(data));
                            tv_spinner3_2.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    clfsDialog2.show();
                }
            }
        });
        // TODO: 2017/12/22  
//        spinner42.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0 && !TextUtils.isEmpty(tvProcessDeclare4.getText().toString())) {
//                    tvProcessDeclare4.setText(generateString4(spinner42));
//                }
//            }
//        });

        tv_spinner4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getString(tv_spinner4_1)))return;
                if (getString(tv_spinner4_1).equals(item1[0])) {
                    clfsDialog1.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tvProcessDeclare4.setText(generateString(data));
                            tv_spinner4_2.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    clfsDialog1.show();
                }else if (getString(tv_spinner4_1).equals(item1[1])){
                    clfsDialog2.setCallBack(new DataSelectDialog.CallBack() {
                        @Override
                        public void enter(String data) {
                            tvProcessDeclare4.setText(generateString(data));
                            tv_spinner4_2.setText(data);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    clfsDialog2.show();
                }
            }
        });
//        spingAnimType.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                etAnimType.setText(getString(spingAnimType));
//            }
//        });

        btPrint.setOnClickListener(v -> {
            if (check()) {
                showNormalDialog();
            }
        });
    }
    private void showNormalDialog(){
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(QuarantineProcessNotifActivity.this);
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
    private UploadQuarantineProcessNotBeanDetil getData() {
        uploadBeanDetil.setFType(etCheckType.getText().toString());   /// 类型
        uploadBeanDetil.setFGlid(Integer.parseInt(dataListBean.getFStId())); /// 关联申报单id
        uploadBeanDetil.setNNumber(etQuarantineNo.getText().toString());/// 编号
        uploadBeanDetil.setNDanWei(etUserName.getText().toString());   /// 处理单位
        uploadBeanDetil.setFChuliNum(Float.valueOf(etGoodsCount.getText().toString()));//处理数量
        uploadBeanDetil.setNName(etGoodsName.getText().toString()); /// 不合格动物及动物产品名称
        uploadBeanDetil.setNTiaoLi("aaa");    /// 处理依据

        uploadBeanDetil.setNChuLi(tvProcessDeclare1.getText().toString());  /// 处理意见1
        uploadBeanDetil.setNChuLi1(tvProcessDeclare1.getText().toString());
        uploadBeanDetil.setNChuLi2(tvProcessDeclare2.getText().toString());
        uploadBeanDetil.setNChuLi3(tvProcessDeclare3.getText().toString());
        uploadBeanDetil.setNChuLi4(tvProcessDeclare4.getText().toString());
        uploadBeanDetil.setNVeterinary(etVeterinarianName.getText().toString()); /// 兽医签字
        uploadBeanDetil.setNParties(etPartyName.getText().toString());      /// 当事人签字
        uploadBeanDetil.setNDwPhone(etPartyPhone.getText().toString()); /// 联系电话
        uploadBeanDetil.setNDsrPhone(etSupervisionPhone.getText().toString()); /// 动物卫生监督所电话
        uploadBeanDetil.setSaveId(1);//区分表

//        uploadBeanDetil.setFChuliType(spingAnimType.getSelectedItem().toString());  /// 处理（动物/产品）种类
        uploadBeanDetil.setFChuliType(etAnimType.getText().toString());  /// 处理（动物/产品）种类

        if (!etGoodsCount.getText().toString().contains("") && !etGoodsCount.getText().toString().contains(null)) {
            uploadBeanDetil.setFChuliNum(Float.valueOf(etGoodsCount.getText().toString()));
        }

        uploadBeanDetil.setFChuliDanwei(etGoodsUnit.getText().toString()); /// 处理单位
        uploadBeanDetil.setNChuLi1txt(tvProcessDeclare1.getText().toString());/// 处理意见1---存第一处理意见1的第二行的打印时使用文本--新加字段
        uploadBeanDetil.setNChuLi2txt(tvProcessDeclare2.getText().toString());/// 处理意见2---存第二处理意见2的打印时使用文本--新加字段
        uploadBeanDetil.setFSm1(dataListBean.getMyuse());
        uploadBeanDetil.setIsPrant("已保存");
        return uploadBeanDetil;
    }

    @Override
    public void getNetData(QuarantineProcessNotifListBeanDetil.DataBean dataBean) {
        etQuarantineNo.setText(dataBean.getJyclcard());//检疫证编号
        etUserName.setText(dataBean.getFarmsnme());//被处理的厂家
        etGoodsName.setText(dataBean.getAnimalsort());//物品名称
        etGoodsUnit.setText(dataBean.getQDWDanWei());//物品单位
        //etAnimType.setText(dataBean.get);//物种类别
        etGoodsCount.setText(dataBean.getLegalnum());//物种数量
        etPartyName.setText(dataBean.getShippername());//当事人姓名
        etPartyPhone.setText(dataBean.getTeltphone());//当事人电话
//        etVeterinarianName.setText(getPresenter().getUser().getFRNAME());
    }

    @Override
    public void shouyijiandu(ShouyiJiandusuoBean.DataBean dataListBean) {
        etSupervisionPhone.setText(dataListBean.getResult());//监督所电话
        etVeterinarianName.setText(dataListBean.getResult1());//官方兽医签字
    }

    private String generateString(String data) {
        return "对" + getString(etUserName) + getString(etGoodsCount) + getString(etGoodsUnit) + getString(etGoodsName) +
                "执行" + data + "处理";
    }

    private String generateString2(String data) {
        if (tvProcessDeclare2.getText().toString().contains("执行") && tvProcessDeclare2.getText().toString().contains("处理")) {
            String str1 = tvProcessDeclare2.getText().toString().substring(0, tvProcessDeclare2.getText().toString().indexOf("执行"));
            return str1 + "执行" + data + "处理";
        } else {
            return "对" + getString(tvProcessDeclare2) + "执行" + data + "处理";
        }
    }

    private String generateString3(String data) {
        if (tvProcessDeclare3.getText().toString().contains("执行") && tvProcessDeclare3.getText().toString().contains("处理")) {
            String str1 = tvProcessDeclare3.getText().toString().substring(0, tvProcessDeclare3.getText().toString().indexOf("执行"));
            return str1 + "执行" + data + "处理";
        } else {
            return "对" + getString(tvProcessDeclare3) + "执行" + data + "处理";
        }
    }

    private String generateString4(String data) {
        if (tvProcessDeclare4.getText().toString().contains("执行") && tvProcessDeclare4.getText().toString().contains("处理")) {
            String str1 = tvProcessDeclare4.getText().toString().substring(0, tvProcessDeclare4.getText().toString().indexOf("执行"));
            return str1 + "执行" + data + "处理";
        } else {
            return "对" + getString(tvProcessDeclare4) + "执行" + data + "处理";
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean check() {
        if (isEmpty(getString(etPartyName))) {
            showToast("当事人姓名不能为空");
            etPartyName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etPartyName.requestFocus();
            return false;
        }
        if (isEmpty(getString(etPartyPhone))) {
            showToast("当事人电话不能为空");
            etPartyPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etPartyPhone.requestFocus();
            return false;
        }
        if (isEmpty(getString(etVeterinarianName))) {
            showToast("请去平台进行官方兽医备案");
            etVeterinarianName.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etVeterinarianName.requestFocus();
            return false;
        }
        if (isEmpty(getString(etSupervisionPhone))) {
            showToast("动物卫生监督所电话不能为空");
            etSupervisionPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etSupervisionPhone.requestFocus();
            return false;
        }

        if (!OtherUtil.isPhoneNumber(getString(etPartyPhone))) {
            showToast("当事人联系电话不符合规定");
            etPartyPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etPartyPhone.requestFocus();
            return false;
        }

        if (!OtherUtil.isPhoneNumber(getString(etPartyPhone))) {
            showToast("动物卫生监督所电话不符合规定");
            etPartyPhone.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            etPartyPhone.requestFocus();
            return false;
        }
        if (isEmpty(getString(tvProcessDeclare1)) &&
                isEmpty(getString(tvProcessDeclare2)) &&
                isEmpty(getString(tvProcessDeclare3)) &&
                isEmpty(getString(tvProcessDeclare4))) {
            showToast("至少选择一个处理结果");
            tvProcessDeclare1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getBaseContext(), R.color.bule1)));
            tvProcessDeclare1.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.QuarantineAnimFragment));
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        //成功上传数据,显示是否打印的对话框
        openPrintDialog();
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
                    bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_JIANYI_CHULI);
                    bundle.putSerializable(Constance.START_ACTIVITY_DATA, uploadBeanDetil);
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
        uploadBeanDetil.setFScTime(time);
        uploadBeanDetil.setFSuTime(time);
    }
}
