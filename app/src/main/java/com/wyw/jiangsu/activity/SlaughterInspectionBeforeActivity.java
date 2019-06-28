package com.wyw.jiangsu.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.SlaughterBeforeItmeAdapter;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.SlaghterNumberBean;
import com.wyw.jiangsu.bean.SlaughterBeforeBean;
import com.wyw.jiangsu.bean.SlaughterChooseBean;
import com.wyw.jiangsu.bean.SlaughterInspectionBeforeBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ISlaughterInspectionBeforeActivity;
import com.wyw.jiangsu.presenter.SlaughterInspectionBeforeActivityPresenter;
import com.wyw.jiangsu.runnable.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 宰前检查
 */

public class SlaughterInspectionBeforeActivity extends MVPActivity<SlaughterInspectionBeforeActivityPresenter>
        implements ISlaughterInspectionBeforeActivity, View.OnClickListener {
    @BindView(R.id.slaughter_listview)
    ListView slaughterListview;
    @BindView(R.id.rootView)
    ScrollView rootView;
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
    @BindView(R.id.et_declaration_no)
    EditText etDeclarationNo;
    @BindView(R.id.btn_choose)
    Button btnChoose;
    @BindView(R.id.et_check_number)
    EditText etCheckNumber;
    //    @BindView(R.id.spinner_year)
//    Spinner spinnerYear;
//    @BindView(R.id.spinner_month)
//    Spinner spinnerMonth;
//    @BindView(R.id.spinner_day)
//    Spinner spinnerDay;
//    @BindView(R.id.spinner_hour)
//    Spinner spinnerHour;
//    @BindView(R.id.spinner_minites)
//    Spinner spinnerMinites;
    @BindView(R.id.et_mustKill_number)
    EditText etMustKillNumber;
    @BindView(R.id.et_process_number)
    EditText etProcessNumber;
    @BindView(R.id.sp_takeToMeasures)
    TextView spTakeToMeasures;
    @BindView(R.id.et_qtnnNumber)
    EditText etQtnnNumber;
    @BindView(R.id.et_coliStockQuantity)
    EditText etColiStockQuantity;
    @BindView(R.id.et_quarantine_personnel)
    EditText etQuarantinePersonnel;
    @BindView(R.id.et_recored_personnel)
    EditText etRecoredPersonnel;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.activity_slaughter_inspection_before)
    LinearLayout activitySlaughterInspectionBefore;
    @BindView(R.id.tv_date)
    TextView tv_date;//时间选择
    @BindView(R.id.tv_cqcs)
    TextView tv_cqcs;//采取措施选择

    TimeSelectDialog dateSelectDialog;//时间选择弹窗
    DataSelectDialog cqcsSelectDialog;//采取措施弹窗
    String times[] = new String[4];

    SlaughterBeforeItmeAdapter adapter;
    String[] title = {"精神状况", "外貌", "呼吸状况", "运动状况", "饮水饮食情况", "排泄物状态", "精神状况", "体温", "呼吸状况", "排泄情况"};
    String[] title1 = {"精神状况", "外貌", "呼吸状况", "运动状况", "饮水饮食情况", "排泄物状态", "精神状况", "体温", "呼吸状况", "皮肤", "蹄部",
            "可视黏膜", "体表淋巴结", "排泄情况"};
    String[] title2 = {"精神状况", "外貌", "呼吸状况", "运动状况", "饮水饮食情况", "排泄物状态", "精神状况", "体温", "呼吸状况", "羽毛", "天然孔",
            "冠、髯、爪", "嗉囊内容物性状", "排泄情况"};
    SlaughterInspectionBeforeBean bean = new SlaughterInspectionBeforeBean();
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String ob = (String) msg.obj;
            etProcessNumber.setText(ob);
        }
    };
    @BindView(R.id.et_check_danwei)
    EditText etCheckDanwei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slaughter_inspection_before);
        ButterKnife.bind(this);
    }

    @Override
    protected SlaughterInspectionBeforeActivityPresenter createPresenter() {
        return new SlaughterInspectionBeforeActivityPresenter(this);
    }

    @Override
    public void bindData() {
        rootView.setVerticalScrollBarEnabled(false);
        setTitle("宰前检查");
        adapter = new SlaughterBeforeItmeAdapter(this, new ArrayList<>(), mHandler);
        slaughterListview.setAdapter(adapter);
        adapter.setmDatas(getDataList(""));


        rootView.smoothScrollTo(0, 0);

        etRecoredPersonnel.setText(getPresenter().getUser().getFSUSERNAME());
        getPresenter().getSlaughterNumber();

        initDialog();

    }

    private void initDialog() {
        dateSelectDialog = new TimeSelectDialog(this, null);
        times[0] = dateSelectDialog.getmCurrentYear() + "";
        times[1] = dateSelectDialog.getmCurrentMonth() + "";
        times[2] = dateSelectDialog.getmCurrentDay() + "";
        times[3] = dateSelectDialog.getmCurrentHour() + "";
        dateSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                times[0] = year;
                times[1] = month;
                times[2] = day;
                times[3] = hour;
                tv_date.setText(year + "/" + month + "/" + day + " " + hour + "时");
            }
        });

        tv_date.setText(times[0] + "/" + times[1] + "/" + times[2] + " " + times[3] + "时");

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateSelectDialog.show();
            }
        });

        cqcsSelectDialog = new DataSelectDialog(this);
        cqcsSelectDialog.setDatas(getResources().getStringArray(R.array.sp_measures));
        cqcsSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spTakeToMeasures.setText(data);
            }

            @Override
            public void cancel() {

            }
        });

        spTakeToMeasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cqcsSelectDialog.show();
            }
        });
    }

    @Override
    public void bindListener() {
        btnChoose.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        etProcessNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etProcessNumber.getText().toString()) && !TextUtils.isEmpty(etCheckNumber.getText().toString())) {
                    etMustKillNumber.setText((Integer.parseInt(etCheckNumber.getText().toString()) -
                            Integer.parseInt(etProcessNumber.getText().toString())) + "");
                }

                if (etProcessNumber.getText().toString().length() == 0 || "0".equals(etProcessNumber.getText().toString())) {
                    etQtnnNumber.setText("");
                    spTakeToMeasures.setText("");
                    spTakeToMeasures.setEnabled(false);
                } else {
                    //通知单编号
                    getPresenter().getJiludanhao();
                    spTakeToMeasures.setEnabled(true);
                }
            }
        });
    }

    //设置标题
    @Override
    public List<SlaughterBeforeBean.DataList> getDataList(String type) {
        List<SlaughterBeforeBean.DataList> list = new ArrayList<>();
        if (TextUtils.isEmpty(type)) {
            for (int i = 0; i < title.length; i++) {
                SlaughterBeforeBean.DataList entity = new SlaughterBeforeBean.DataList();
                if (i == 0) {
                    entity.setTitle("一、群体检查");
                }
                if (i == 6) {
                    entity.setTitle("二、个体检查");
                }
                if (i < 6) {
                    entity.setPosition(i + 1 + "、");
                } else {
                    entity.setPosition(i - 5 + "、");
                }
                entity.setType(title[i]);
                list.add(entity);
            }
        } else if (type.equals("猪") || type.equals("牛") || type.equals("羊")) {
            for (int i = 0; i < title1.length; i++) {
                SlaughterBeforeBean.DataList entity = new SlaughterBeforeBean.DataList();
                if (i == 0) {
                    entity.setTitle("一、群体检查");
                }
                if (i == 6) {
                    entity.setTitle("二、个体检查");
                }
                if (i < 6) {
                    entity.setPosition(i + 1 + "、");
                } else {
                    entity.setPosition(i - 5 + "、");
                }
                entity.setType(title1[i]);
                list.add(entity);
            }
        } else {
            for (int i = 0; i < title2.length; i++) {
                SlaughterBeforeBean.DataList entity = new SlaughterBeforeBean.DataList();
                if (i == 0) {
                    entity.setTitle("一、群体检查");
                }
                if (i == 6) {
                    entity.setTitle("二、个体检查");
                }
                if (i < 6) {
                    entity.setPosition(i + 1 + "、");
                } else {
                    entity.setPosition(i - 5 + "、");
                }
                entity.setType(title2[i]);
                list.add(entity);
            }
        }

        return list;
    }

    @Override
    public void setSlaughterNumber(List<SlaghterNumberBean.DataEntity> list) {
        etQuarantineNo.setText(list.get(0).getResult());//屠宰检疫编号
    }

    @Override
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        finish();
    }

    @Override
    public void jiludan(JianyiChuliTongzhihaobean.DataBean dataListBean) {
        String result = dataListBean.getResult();     //检疫处理通知单编号
        etQtnnNumber.setText(result);
    }


    //要上传的数据
    public void setBean() {
        String quanNo = "";
        String number = "";
        String isSomting = "";
        bean.setTZJYBH(etQuarantineNo.getText().toString());//屠宰检疫编号
        // TODO: 2018/1/4  
//        bean.setFDate(spinnerYear.getSelectedItem().toString() + "-" + spinnerMonth.getSelectedItem().toString() + "-"
//                + spinnerDay.getSelectedItem().toString());//日期
        bean.setFDate(times[0] + "-" + times[1] + "-"
                + times[2]);//日期
        bean.setSBDBH(etDeclarationNo.getText().toString());//申报单编号
//bean.setJCSL(Integer.valueOf(etCheckNumber.getText().toString().substring(0, etCheckNumber.getText().toString().length() - 1)));//检查数量
        if (!TextUtils.isEmpty(etCheckNumber.getText().toString())) {
            bean.setJCSL(Integer.valueOf(etCheckNumber.getText().toString()));//检查数量
        } else {
            bean.setJCSL(0);
        }

        if (!TextUtils.isEmpty(etCheckNumber.getText().toString())) {
            bean.setZZSL(Integer.valueOf(etMustKillNumber.getText().toString()));//准宰数量
        } else {
            bean.setZZSL(0);
        }

        if (TextUtils.isEmpty(etCheckNumber.getText().toString())) {
            bean.setCLSL(0);//处理数量
        } else {
            bean.setCLSL(Integer.valueOf(etProcessNumber.getText().toString()));//处理数量
        }

        // TODO: 2018/1/4
//        bean.setCQCS(spTakeToMeasures.getSelectedItem().toString());//采取措施
        bean.setCQCS(spTakeToMeasures.getText().toString());//采取措施
        bean.setCode(etQtnnNumber.getText().toString());//出具《检疫处理通知单》
        if (!TextUtils.isEmpty(etColiStockQuantity.getText().toString())) {
            bean.setQCSL(Integer.valueOf(etColiStockQuantity.getText().toString()));//圈存数量
        } else {
            bean.setQCSL(0);//圈存数量
        }

        bean.setJYRY(etQuarantinePersonnel.getText().toString());//检疫人员
        bean.setJLRY(etRecoredPersonnel.getText().toString());//记录人员
        // TODO: 2018/1/4  
//        bean.setXs(Integer.valueOf(spinnerHour.getSelectedItem().toString()));//小时
        bean.setXs(Integer.valueOf(times[3]));//小时
        for (int i = 0; i < adapter.getDateList().size(); i++) {
            if (TextUtils.isEmpty(adapter.getDateList().get(i).getQuanNo())) {
                quanNo += "" + ",";                                                 // 圈号集合以逗号隔开
            } else {
                quanNo += adapter.getDateList().get(i).getQuanNo() + ",";           // 圈号集合以逗号隔开
            }

            if (TextUtils.isEmpty(adapter.getDateList().get(i).getNumber())) {
                number += "" + ",";                                                 // 数量集合以逗号隔开
            } else {
                number += adapter.getDateList().get(i).getNumber() + ",";           // 数量集合以逗号隔开
            }

            if (TextUtils.isEmpty(adapter.getDateList().get(i).getIsSomething())) {
                isSomting += "正常" + ",";                                              //临床检查情况
            } else {
                isSomting += adapter.getDateList().get(i).getIsSomething() + ",";   //临床检查情况
            }

        }
        bean.setZCYC(isSomting.substring(0, isSomting.length() - 1)); //临床检查情况(正常，异常）集合以逗号隔开
        bean.setQHS(quanNo.substring(0, quanNo.length() - 1));// 圈号集合以逗号隔开
        bean.setSLS(number.substring(0, number.length() - 1));// 数量集合以逗号隔开
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_choose:
                startActivityForResult(new Intent(SlaughterInspectionBeforeActivity.this, SlaughterChooseActivity.class), Constance.ACTIVITY_CODE);
                break;
            case R.id.btn_submit:
                if (check()) {
                    setBean();
                    showNormalDialog(bean);
                }
                break;
        }
    }

    private void showNormalDialog(SlaughterInspectionBeforeBean bean) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(SlaughterInspectionBeforeActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(bean);
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

    //点击选择按钮，返回的值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                String qcpNumber = "";
                int checkNumber = 0;
                String fstid = "";
                List<SlaughterChooseBean.DataListEntity> list = (List<SlaughterChooseBean.DataListEntity>) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                if (list == null || list.size() == 0) {
                    adapter.setmDatas(getDataList(""));
                    etCheckNumber.setText("");//检查数量
                    bean.setFAnimalSpecies("");//动物种类
                    bean.setFGlid(Integer.valueOf(0));//企业关联ID
                    bean.setFRCGlid("");
                } else {
                    adapter.setmDatas(getDataList(list.get(0).getQCProduct()));
                    for (int i = 0; i < list.size(); i++) {
                        if (list.size() == 1) {
                            qcpNumber = list.get(i).getQCPNumber();
                        } else {
                            qcpNumber += list.get(i).getQCPNumber() + ",";
                        }

                        if (!TextUtils.isEmpty(list.get(i).getQCPQuantity().substring(0, list.get(i).getQCPQuantity().length() - 1))) {
                            checkNumber += Integer.valueOf(list.get(i).getQCPQuantity().substring(0, list.get(i).getQCPQuantity().length() - 1));
                        } else {
                            checkNumber += 0;
                        }
                        fstid += list.get(i).getFStId() + ",";
                    }
                    etCheckNumber.setText(checkNumber + list.get(0).getQCPQuantity().substring(list.get(0).getQCPQuantity().length() - 1));//检查数量
                    etCheckDanwei.setText(list.get(0).getQCPDanWei());
                    bean.setFAnimalSpecies(list.get(0).getQCProduct());//动物种类
                    bean.setFGlid(Integer.valueOf(list.get(0).getFSenterpriseId()));//企业关联ID
                    bean.setFRCGlid(fstid.substring(0, fstid.length() - 1));
                }


                etDeclarationNo.setText(qcpNumber);//申报单编号


            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean check() {
        if (tv_date.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etMustKillNumber.getText().toString())) {
            Toast.makeText(this, "准宰数量不能为空", Toast.LENGTH_SHORT).show();
            etMustKillNumber.requestFocus();
            focusKeywordView(etMustKillNumber);
            changeTintColor(etMustKillNumber);
            return false;
        }
        changeTintColor(etMustKillNumber);


        if (TextUtils.isEmpty(etProcessNumber.getText().toString())) {
            Toast.makeText(this, "处理数量不能为空", Toast.LENGTH_SHORT).show();
            etProcessNumber.requestFocus();
            focusKeywordView(etProcessNumber);
            changeTintColor(etProcessNumber);
            return false;
        }
        changeTintColor(etProcessNumber);

        if (TextUtils.isEmpty(etDeclarationNo.getText().toString())) {
            Toast.makeText(this, "请选择申报单编号", Toast.LENGTH_SHORT).show();
            etDeclarationNo.requestFocus();
            focusKeywordView(etDeclarationNo);
            changeTintColor(etDeclarationNo);
            return false;
        }
        changeTintColor(etDeclarationNo);
        if (TextUtils.isEmpty(etMustKillNumber.getText().toString()) && !TextUtils.isEmpty(etProcessNumber.getText().toString())) {
            if (Integer.valueOf(etProcessNumber.getText().toString()) > Integer.valueOf(etCheckNumber.getText().toString())) {
                Toast.makeText(this, "准宰数量与处理数量之和不能大于检查数量", Toast.LENGTH_SHORT).show();
                etCheckNumber.requestFocus();
                focusKeywordView(etCheckNumber);
                changeTintColor(etCheckNumber);
                return false;
            }
        }

        changeTintColor(etCheckNumber);
        if (TextUtils.isEmpty(etProcessNumber.getText().toString()) && !TextUtils.isEmpty(etMustKillNumber.getText().toString())) {
            if (Integer.valueOf(etMustKillNumber.getText().toString()) > Integer.valueOf(etCheckNumber.getText().toString())) {
                Toast.makeText(this, "准宰数量与处理数量之和不能大于检查数量", Toast.LENGTH_SHORT).show();
                etCheckNumber.requestFocus();
                focusKeywordView(etCheckNumber);
                changeTintColor(etCheckNumber);
                return false;
            }
        }
        changeTintColor(etCheckNumber);
        if ((Integer.valueOf(etMustKillNumber.getText().toString()) + Integer.valueOf(etProcessNumber.getText().toString()))
                > Integer.valueOf(etCheckNumber.getText().toString())) {
            Toast.makeText(this, "准宰数量与处理数量之和不能大于检查数量", Toast.LENGTH_SHORT).show();
            etCheckNumber.requestFocus();
            focusKeywordView(etCheckNumber);
            changeTintColor(etCheckNumber);
            return false;
        }
        changeTintColor(etCheckNumber);

        if (TextUtils.isEmpty(etColiStockQuantity.getText().toString())) {
            Toast.makeText(this, "请输入圈存数量", Toast.LENGTH_SHORT).show();
            etColiStockQuantity.requestFocus();
            focusKeywordView(etColiStockQuantity);
            return false;
        }
        if (TextUtils.isEmpty(etQuarantinePersonnel.getText().toString())) {
            Toast.makeText(this, "请输入检疫人员", Toast.LENGTH_SHORT).show();
            etQuarantinePersonnel.requestFocus();
            focusKeywordView(etQuarantinePersonnel);
            return false;
        }
        return true;
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

}
