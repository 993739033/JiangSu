package com.wyw.jiangsu.activity.zhifa;
/**
 * 添加案件登记
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AddAnjiandengjiActivityBean;
import com.wyw.jiangsu.bean.BanshpeopleleixzActivityBean;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAddCaseRegisterActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.AddCaseRegisterActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCaseRegisterActivity extends MVPActivity<AddCaseRegisterActivityPresenter> implements IAddCaseRegisterActivity, ITime {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.et_anyou)
    EditText etAnyou;
    @BindView(R.id.et_people)
    EditText etPeople;
    @BindView(R.id.bt_xuanze)
    Button btXuanze;
    @BindView(R.id.sp_takeToMeasures)
    TextView sp_takeToMeasures;
    @BindView(R.id.sp_anjian)
    TextView sp_anjian;
    @BindView(R.id.button)
    Button button;
    private AddAnjiandengjiActivityBean bean;//上传的实体类
    private TimeSelectDialog dateDialogStart;
    TimePresenter timePresenter;
    private List<BanshpeopleleixzActivityBean.DataListBean> list;
    private StringBuffer sb2;
    private DataSelectDialog sourceSelectDialog;//来源选择
    private DataSelectDialog caseTypeSelectDialog;//案件类型选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_anjiandengji);
        ButterKnife.bind(this);
    }

    @Override
    protected AddCaseRegisterActivityPresenter createPresenter() {
        return new AddCaseRegisterActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("案件登记");
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        // TODO: 2017/12/27
//        dateDialogStart = new DatePickerDialog(this, this, mCalendar.get(Calendar.YEAR),
//                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));

        dateDialogStart = new TimeSelectDialog(this, "起运时间选择");
        dateDialogStart.initTime();
        dateDialogStart.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                String time = year + "-" + month + "-" + day;
                tvDate.setText(time);
            }
        });
        sourceSelectDialog = new DataSelectDialog(this);
        sourceSelectDialog.setTitle("选择来源");
        sourceSelectDialog.setDatas(getResources().getStringArray(R.array.sp_laiyuan));

        caseTypeSelectDialog = new DataSelectDialog(this);
        caseTypeSelectDialog.setTitle("选择案件类型");
        caseTypeSelectDialog.setDatas(getResources().getStringArray(R.array.sp_anjiantype));

    }

    @OnClick({R.id.tv_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                dateDialogStart.show();
                break;
        }
    }

    @Override
    public void bindListener() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check()) {
                    showNormalDialog();
                }
            }
        });
        btXuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCaseRegisterActivity.this, BanshpeopleixzActivity.class);
                startActivityForResult(intent, Constance.ACTIVITY_CODE);
            }
        });

        sourceSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                sp_takeToMeasures.setText(data);
            }

            @Override
            public void cancel() {

            }
        });

        //来源选择
        sp_takeToMeasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceSelectDialog.show();
            }
        });

        caseTypeSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                sp_anjian.setText(data);
            }

            @Override
            public void cancel() {

            }
        });


        //案件选择
        sp_anjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseTypeSelectDialog.show();
            }
        });
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AddCaseRegisterActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getBean());
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
        if (TextUtils.isEmpty(etPeople.getText().toString())) {
            Toast.makeText(this, "办案人员不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sp_takeToMeasures.getText().toString().equals("请选择")) {
            Toast.makeText(this, "来源选择不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sp_anjian.getText().toString().equals("请选择")) {
            Toast.makeText(this, "案件类型选择不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void uploadsuccess(String result2) {
        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                list = (List<BanshpeopleleixzActivityBean.DataListBean>) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                if (list == null || list.size() == 0) {

                } else {
                    StringBuffer sb = new StringBuffer();
                    sb2 = new StringBuffer();
                    for (int i = 0; i < list.size(); i++) {
                        if (i == list.size() - 1) {
                            sb.append(list.get(i).getFname());
                            sb2.append(list.get(i).getFUID());
                        } else {
                            sb.append(list.get(i).getFname() + ",");
                            sb2.append(list.get(i).getFUID() + ",");
                        }
                    }
                    etPeople.setText(sb.toString());
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 上传的数据
     *
     * @return
     */
    private AddAnjiandengjiActivityBean getBean() {
        bean = new AddAnjiandengjiActivityBean();
        bean.setFSunitUstrId(getPresenter().getUser().getFSUNITUSTRID());//行政ID
        bean.setFdjrq(tvDate.getText().toString());                      //日期
        bean.setFany(etAnyou.getText().toString());                      //案由
        bean.setFly(sp_takeToMeasures.getText().toString());      //案件来源
        bean.setFajlx(sp_anjian.getText().toString());            //案件类型
        bean.setUid(sb2.toString());                                     //UID
        bean.setFSuserId(getPresenter().getUserId());                    //用户Id
        return bean;
    }

    @Override
    public void setTime(String time, long longTime) {
        String riqi = time.substring(0, 11);
        tvDate.setText(riqi);//报检时间
    }
}
