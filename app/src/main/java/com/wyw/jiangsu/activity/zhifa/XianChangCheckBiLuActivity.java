package com.wyw.jiangsu.activity.zhifa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AnjiandengjiActivityBean;
import com.wyw.jiangsu.bean.BanshpeopleleixzActivityBean;
import com.wyw.jiangsu.bean.XianChangCheckBiLuBean;
import com.wyw.jiangsu.dialog.datepickdialog.HourMinutePickDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IXianChangCheckBiLuActivity;
import com.wyw.jiangsu.presenter.XianChangCheckBiLuActivityPresenter;
import com.wyw.jiangsu.utils.OtherUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Windows on 2017/7/6.
 */

public class XianChangCheckBiLuActivity extends MVPActivity<XianChangCheckBiLuActivityPresenter> implements IXianChangCheckBiLuActivity {


    @BindView(R.id.bt_xuanze_wenjian)
    TextView btXuanzeWenjian;
    //    @BindView(R.id.spinner_year)
//    Spinner spinnerYear;
//    @BindView(R.id.spinner_month)
//    Spinner spinnerMonth;
//    @BindView(R.id.spinner_day)
//    Spinner spinnerDay;
//    @BindView(R.id.spinner_start_shi)
//    Spinner spinnerStartShi;
//    @BindView(R.id.spinner_start_fen)
//    Spinner spinnerStartFen;
//    @BindView(R.id.spinner_end_shi)
//    Spinner spinnerEndShi;
//    @BindView(R.id.spinner_end_fen)
//    Spinner spinnerEndFen;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_timefrom)
    TextView tv_timefrom;
    @BindView(R.id.tv_timeto)
    TextView tv_timeto;

    @BindView(R.id.et_check_didian)
    EditText etCheckDidian;
    @BindView(R.id.et_xunwen_jiguan)
    EditText etXunwenJiguan;
    @BindView(R.id.et_dangshiren)
    EditText etDangshiren;
    @BindView(R.id.et_check_jiguan)
    EditText etCheckJiguan;
    @BindView(R.id.et_check_renyuan1)
    EditText etCheckRenyuan1;
    @BindView(R.id.bt_check_renyuan)
    Button btCheckRenyuan;
    @BindView(R.id.et_zhifa_zhengjianhao1)
    EditText etZhifaZhengjianhao1;
    @BindView(R.id.et_check_renyuan2)
    EditText etCheckRenyuan2;
    @BindView(R.id.et_zhifa_zhengjianhao2)
    EditText etZhifaZhengjianhao2;
    @BindView(R.id.et_jiluren)
    EditText etJiluren;
    @BindView(R.id.SV)
    ScrollView SV;
    @BindView(R.id.et_check_qingkuang)
    EditText etCheckQingkuang;
    @BindView(R.id.bt_baocun)
    Button btBaocun;
    //要上传的实体类
    private XianChangCheckBiLuBean bean = new XianChangCheckBiLuBean();
    private List<String> endDayList;
    private List<BanshpeopleleixzActivityBean.DataListBean> list;
    private AnjiandengjiActivityBean.DataListBean databean;
    private String Glid;
    private String fanbh;

    private TimeSelectDialog dateSelectDialog;
    private HourMinutePickDialog hmSelectDialog;
    private String hmfrom[] = new String[2];//开始小时分钟
    private String hmto[] = new String[2];//终止小时分钟

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xianchang_check_bilu);
        ButterKnife.bind(this);
    }

    @Override
    protected XianChangCheckBiLuActivityPresenter createPresenter() {
        return new XianChangCheckBiLuActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("现场检查(勘验)笔录");
        setTime();
        initDialog();
    }

    private void initDialog() {
        //日期选择
        dateSelectDialog = new TimeSelectDialog(this,null);
        dateSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                tv_date.setText(year+"-"+month+"-"+day);
            }
        });
        tv_date.setText(dateSelectDialog.getmCurrentYear()
                +"-"+dateSelectDialog.getmCurrentMonth()
                +"-"+dateSelectDialog.getmCurrentDay());

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateSelectDialog.show();
            }
        });

        //小时分钟选择
        hmSelectDialog = new HourMinutePickDialog(this,null);
        hmfrom[0] = hmSelectDialog.getmCurrentHour() + "";
        hmfrom[1] = hmSelectDialog.getmCurrentMinute() + "";
        hmto[0] = hmSelectDialog.getmCurrentHour() + "";
        hmto[1] = hmSelectDialog.getmCurrentMinute() + "";
        tv_timefrom.setText(hmfrom[0]+":"+hmfrom[1]);
        tv_timeto.setText(hmto[0]+":"+hmto[1]);

        tv_timefrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hmSelectDialog.setmTimePickListener1(new HourMinutePickDialog.TimePickListener1() {
                    @Override
                    public void getTime(String hour, String minute) {
                        String tempHour = hmfrom[0];
                        String tempMinute =  hmfrom[1];
                        hmfrom[0] = hour;
                        hmfrom[1] = minute;
                        if (!checkTime()) {
                            Toast.makeText(XianChangCheckBiLuActivity.this, "终止时间不能大于初始时间", Toast.LENGTH_SHORT).show();
                            hmfrom[0] = tempHour;
                            hmfrom[1] = tempMinute;
                            return;
                        }

                        tv_timefrom.setText(hmfrom[0]+":"+hmfrom[1]);
                    }
                });
                hmSelectDialog.show();
            }
        });


        tv_timeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hmSelectDialog.setmTimePickListener1(new HourMinutePickDialog.TimePickListener1() {
                    @Override
                    public void getTime(String hour, String minute) {
                        String tempHour = hmto[0];
                        String tempMinute =  hmto[1];
                        hmto[0] = hour;
                        hmto[1] = minute;
                        if (!checkTime()) {
                            Toast.makeText(XianChangCheckBiLuActivity.this, "终止时间不能大于初始时间", Toast.LENGTH_SHORT).show();
                            hmto[0] = tempHour;
                            hmto[1] = tempMinute;
                            return;
                        }

                        tv_timeto.setText(hmto[0]+":"+hmto[1]);
                    }
                });
                hmSelectDialog.show();
            }
        });
    }

    private boolean checkTime(){
        if(Integer.parseInt(hmfrom[0])>Integer.parseInt(hmto[0])){
            return false;
        }else if (Integer.parseInt(hmfrom[0])==Integer.parseInt(hmto[0])&&Integer.parseInt(hmfrom[1])>Integer.parseInt(hmto[1])){
            return false;
        }
            return true;
    }

    /**
     * 设置年月日时分
     */
    private void setTime() {
        // TODO: 2018/1/5
//        spinnerYear.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createYears()));
//        spinnerMonth.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createMonths()));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        endDayList = OtherUtil.createDays(String.valueOf(calendar.get(Calendar.YEAR)),
//                String.valueOf(calendar.get(Calendar.MONTH) + 1));
//        spinnerDay.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, endDayList));
//        spinnerStartShi.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createHours()));
//        spinnerStartFen.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createMinutes()));
//        spinnerEndShi.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createHours()));
//        spinnerEndFen.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createMinutes()));
//        spinnerYear.setSelection(4);
//        spinnerMonth.setSelection(OtherUtil.getCurrentMonthPosition());
//        spinnerDay.setSelection(OtherUtil.getCurrentDayPosition());
//        spinnerStartShi.setSelection(OtherUtil.getCurrentHourPosition());
//        spinnerStartFen.setSelection(OtherUtil.getCurrentMinutePosition());
//        spinnerEndShi.setSelection(OtherUtil.getCurrentHourPosition() + 2);
//        spinnerEndFen.setSelection(OtherUtil.getCurrentMinutePosition());

    }

    @Override
    public void bindListener() {
        //选择案件
        btXuanzeWenjian.setOnClickListener(v -> {
            startActivityForResult(new Intent(this, AnjiandengjiActivity.class), Constance.ACTIVITY_CODE + 1);
        });

        //检查人员选择
        btCheckRenyuan.setOnClickListener(v -> {
            startActivityForResult(new Intent(this, BanshpeopleixzActivity.class), Constance.ACTIVITY_CODE);
        });
        // TODO: 2018/1/5
//        spinnerYear.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                onStartSpinnerSelect();
//            }
//        });
//
//        spinnerMonth.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                onStartSpinnerSelect();
//            }
//        });

        btBaocun.setOnClickListener(view -> {
            if (check()) {
                showNormalDialog();
            }
        });
    }

    private void showNormalDialog(){
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(XianChangCheckBiLuActivity.this);
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
    private boolean check() {
        if (TextUtils.isEmpty(Glid)) {
            showToast("请选择案件");
            return false;
        }
        if (TextUtils.isEmpty(etCheckDidian.getText().toString())) {
            showToast("请填写检查地点");
            return false;
        }
        if (TextUtils.isEmpty(etXunwenJiguan.getText().toString())) {
            showToast("请填写询问机关");
            return false;
        }
        if (TextUtils.isEmpty(etDangshiren.getText().toString())) {
            showToast("请填写当事人");
            return false;
        }
        if (TextUtils.isEmpty(etCheckJiguan.getText().toString())) {
            showToast("请填写检查机关");
            return false;
        }
        if (TextUtils.isEmpty(etJiluren.getText().toString())) {
            showToast("请填写记录人");
            return false;
        }
        if (TextUtils.isEmpty(etCheckQingkuang.getText().toString())) {
            showToast("请填写现场检查情况");
            return false;
        }

        return true;
    }

    private void onStartSpinnerSelect() {
        endDayList.clear();
        // TODO: 2018/1/5
//        String year = spinnerYear.getSelectedItem().toString();
//        Integer month = Integer.valueOf(spinnerMonth.getSelectedItem().toString());
//        endDayList.addAll(OtherUtil.createDays(year, String.valueOf(month)));
//        ((ArrayAdapter) spinnerDay.getAdapter()).notifyDataSetChanged();
    }

    public XianChangCheckBiLuBean getData() {
        bean = new XianChangCheckBiLuBean();
        bean.setFGlid(Integer.parseInt(Glid));  ////案件登记关联id
        // TODO: 2018/1/5
//        bean.setFsdate(spinnerYear.getSelectedItem().toString() + "-" + spinnerMonth.getSelectedItem().toString()
//                + "-" + spinnerDay.getSelectedItem().toString());//询问时间
        bean.setFsdate(tv_date.getText().toString());//询问时间
        bean.setFdz(OtherUtil.toString(etCheckDidian));  //检查地点
        bean.setFxwjg(OtherUtil.toString(etXunwenJiguan));  //询问机关
        bean.setFdsr(OtherUtil.toString(etDangshiren));  //当事人
        bean.setFjg(OtherUtil.toString(etCheckJiguan));  //检查机关
        bean.setFkcry(OtherUtil.toString(etCheckRenyuan1));  //检查人员1
        bean.setFzfzjh(OtherUtil.toString(etZhifaZhengjianhao1));  //执法证件号1
        bean.setFkcry1(OtherUtil.toString(etCheckRenyuan2));  //检查人员2
        bean.setFzfzjh1(OtherUtil.toString(etZhifaZhengjianhao2));  //执法证件号2
        bean.setFjlr(OtherUtil.toString(etJiluren));  //记录人
        bean.setFkcjl(OtherUtil.toString(etCheckQingkuang));  //现场检查情况
        // TODO: 2018/1/5
//        bean.setFshij(spinnerStartShi.getSelectedItem().toString());//小时1
//        bean.setFfengz(spinnerStartFen.getSelectedItem().toString());//分钟1
//        bean.setFshij1(spinnerEndShi.getSelectedItem().toString()); //小时2
//        bean.setFfengz1(spinnerEndFen.getSelectedItem().toString());//分钟2

        bean.setFshij(hmfrom[0]);//小时1
        bean.setFfengz(hmfrom[1]);//分钟1
        bean.setFshij1(hmto[0]); //小时2
        bean.setFfengz1(hmto[1]);//分钟2
        bean.setFSunitUstrId(getPresenter().getUser().getFSUNITUSTRID());//行政id
        bean.setFSuserId(getPresenter().getUserId());                   //用户ID
        bean.setFajbh(fanbh);                                       //案件编号
        return bean;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //检查人员
        if (requestCode == Constance.ACTIVITY_CODE) {
            if (resultCode == RESULT_OK) {
                list = (List<BanshpeopleleixzActivityBean.DataListBean>) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                if (list != null && list.size() == 2) {
                    etCheckRenyuan1.setText(list.get(0).getFname());
                    etCheckRenyuan2.setText(list.get(1).getFname());
                    etZhifaZhengjianhao1.setText(list.get(0).getFzfzjh());
                    etZhifaZhengjianhao2.setText(list.get(1).getFzfzjh());
                }
            }
        }
        //选择案件
        if (requestCode == Constance.ACTIVITY_CODE + 1) {
            if (resultCode == RESULT_OK) {
                databean = (AnjiandengjiActivityBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                if (databean != null) {
                    Glid = databean.getFStId();
                    String filename = databean.getFname();
                    String s1 = filename.substring(0, filename.lastIndexOf(","));
                    String s2 = filename.substring(filename.lastIndexOf(",") + 1);
                    etCheckRenyuan1.setText(s1);
                    etCheckRenyuan2.setText(s2);
                    String filename1 = databean.getFzfzjh();
                    String s3 = filename1.substring(0, filename1.lastIndexOf(","));
                    String s4 = filename1.substring(filename1.lastIndexOf(",") + 1);
                    etZhifaZhengjianhao1.setText(s3);
                    etZhifaZhengjianhao2.setText(s4);
                    fanbh = databean.getFanbh();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void isfinish() {
        Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
        finish();
    }
}
