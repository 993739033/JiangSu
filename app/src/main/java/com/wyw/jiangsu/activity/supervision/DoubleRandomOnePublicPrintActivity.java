package com.wyw.jiangsu.activity.supervision;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.MainActivity;
import com.wyw.jiangsu.activity.PrintAcitivty;
import com.wyw.jiangsu.bean.DoubleRandomCenterBean;
import com.wyw.jiangsu.bean.upload.UploadDoubleRandomBean;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IDoubleRandomOnePublicPrintActivity;
import com.wyw.jiangsu.presenter.DoubleRandomOnePublicPrintActivityPresenter;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.NumFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DoubleRandomOnePublicPrintActivity extends MVPActivity<DoubleRandomOnePublicPrintActivityPresenter> implements IDoubleRandomOnePublicPrintActivity {
    @BindView(R.id.et_qy_name)
    EditText et_qy_name;
    @BindView(R.id.tv_jcsj)
    TextView tv_jcsj;
    @BindView(R.id.et_sum)
    EditText et_sum;
    @BindView(R.id.tv_zg_time)
    TextView tv_zg_time;
    @BindView(R.id.et_zy_name)
    EditText et_zy_name;
    @BindView(R.id.et_sjcy_name)
    EditText et_sjcy_name;
    @BindView(R.id.et_qyfzr_name)
    EditText et_qyfzr_name;
    @BindView(R.id.tv_sign_time_1)
    TextView tv_sign_time_1;
    @BindView(R.id.tv_sign_time_2)
    TextView tv_sign_time_2;
    @BindView(R.id.tv_sign_time_3)
    TextView tv_sign_time_3;
    @BindView(R.id.btn_print)
    Button btn_print;


    TimeSelectDialog timeSelectDialog;
    UploadDoubleRandomBean bean;

    private int TableNum = 10;//打印对应


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_random_one_public_print);
    }

    @Override
    protected DoubleRandomOnePublicPrintActivityPresenter createPresenter() {
        return new DoubleRandomOnePublicPrintActivityPresenter(this);
    }


    @Override
    public void bindData() {
        initDialog();
        Intent intent = getIntent();

        bean = (UploadDoubleRandomBean) intent.getSerializableExtra("Bean");
        if (bean==null) return;
        String Sum=NumFormat.toChineseCharI(bean.getNoSum());
        et_sum.setText(Sum);
        et_qy_name.setText(bean.getTopData().getFFarmName());
    }

    private void initDialog() {
        setTitle("屠宰“双随机一公开”");
        timeSelectDialog = new TimeSelectDialog(this, null);
        tv_jcsj.setText(timeSelectDialog.getNowTime());
        tv_sign_time_1.setText(timeSelectDialog.getNowTime());
        tv_sign_time_2.setText(timeSelectDialog.getNowTime());
        tv_sign_time_3.setText(timeSelectDialog.getNowTime());
    }

    @Override
    public void bindListener() {
        tv_zg_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
                    @Override
                    public void getTime(String year, String month, String day, String hour) {
                        tv_zg_time.setText(timeSelectDialog.getNowTime());
                    }
                });
                timeSelectDialog.show();
            }
        });
        tv_jcsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
                    @Override
                    public void getTime(String year, String month, String day, String hour) {
                        tv_jcsj.setText(timeSelectDialog.getNowTime());
                    }
                });
                timeSelectDialog.show();
            }
        });

        tv_sign_time_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
                    @Override
                    public void getTime(String year, String month, String day, String hour) {
                        tv_sign_time_1.setText(timeSelectDialog.getNowTime());
                    }
                });
                timeSelectDialog.show();
            }
        });


        tv_sign_time_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
                    @Override
                    public void getTime(String year, String month, String day, String hour) {
                        tv_sign_time_2.setText(timeSelectDialog.getNowTime());
                    }
                });
                timeSelectDialog.show();
            }
        });

        tv_sign_time_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
                    @Override
                    public void getTime(String year, String month, String day, String hour) {
                        tv_sign_time_3.setText(timeSelectDialog.getNowTime());
                    }
                });
                timeSelectDialog.show();
            }
        });
        btn_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()){
                    showNormalDialog();
                }
            }
        });
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(DoubleRandomOnePublicPrintActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            getPresenter().uploadDoubleRandom(getJson());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
        if (TextUtils.isEmpty(tv_zg_time.getText().toString()) || tv_zg_time.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择整改时间", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(et_zy_name.getText().toString())){
            Toast.makeText(this, "检查组成员签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(et_sjcy_name.getText().toString())){
            Toast.makeText(this, "市观察员签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(et_qyfzr_name.getText().toString())){
            Toast.makeText(this, "企业负责人签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();//dataCA 企业信息 签名 时间
        JSONArray jsonArray = new JSONArray();//检查内容

        jsonObject1.put("FGlid", bean.getTopData().getFStId());//企业自增id
        jsonObject1.put("Fnam", bean.getTopData().getFFarmName());//企业名称
        jsonObject1.put("FCheckDate", tv_jcsj.getText().toString());//检查时间
        jsonObject1.put("FerrorCount", et_sum.getText().toString());//不符合事项
        jsonObject1.put("FzgDate", tv_zg_time.getText().toString());//整改时间
        jsonObject1.put("Fjczqz", et_zy_name.getText().toString());//检查组成员签字
        jsonObject1.put("Fjczqzrq", tv_sign_time_1.getText().toString());//检查组成员签字时间
        jsonObject1.put("Fsgcyqz", et_sjcy_name.getText().toString());//市观察员签名
        jsonObject1.put("Fsgcyqzrq", tv_sign_time_2.getText().toString());//市观察员签名时间
        jsonObject1.put("Fqyfzrqz", et_qyfzr_name.getText().toString());//企业负责人签名
        jsonObject1.put("Fqyfzrqzrq", tv_sign_time_3.getText().toString());//企业负责人签名时间

        bean.setCheckTime(tv_jcsj.getText().toString());//检查时间
        bean.setZgTime(tv_zg_time.getText().toString());//整改时间

        jsonObject.put("dataCA", jsonObject1);
        jsonObject.put("FGlid", bean.getTopData().getFStId());//企业自增id
        jsonObject.put("Fnam", bean.getTopData().getFFarmName());//企业名称
        jsonObject.put("Ffzr", bean.getTopData().getFLegalPerson());//法人代表
        jsonObject.put("Fdz", bean.getTopData().getFCityAdd());//地址
        jsonObject.put("Fdh", bean.getTopData().getFPhone());//电话
        jsonObject.put("FerrorCount", bean.getNoSum());//不符合总数

        int sum = 0;
        //检查结果
        for (DoubleRandomCenterBean.DateBean bean1 : bean.getBean().getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        sum++;
                        JSONObject object = new JSONObject();
                        object.put("FGlid", "0");//无用默认为零
                        object.put("FResult", bean4.getCheckResult());//检查结果 是/否
                        object.put("FRemark", bean4.getCheckRemark());//检查备注
                        object.put("FOrder", sum+"");//检查数量
                        jsonArray.put(object);
                    }
                }
            }
        }

        jsonObject.put("dataList", jsonArray);
        return jsonObject.toString();
    }



    @Override
    public void uploadSuccess() {
        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        openPrintDialog();
    }

    @Override
    public void downImgFail() {
        Toast.makeText(this, "打印模板下载失败", Toast.LENGTH_SHORT).show();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View viewAddEmployee = layoutInflater.inflate(
                R.layout.tishi_print_dialog, null);
        new AlertDialog.Builder(this)
                .setTitle("下载提示")
                .setCancelable(false)
                .setView(viewAddEmployee)
                .setPositiveButton("重新下载", (dialog, which) -> {
                    //getPresenter().downPrintImg("http://pan.baidu.com/s/1mi0vzL2");
                })
                .show();
    }

    @Override
    public void downImgSuccess() {
        if (FileUtil.getInstance().getPrintBGSize("ssj")==8) {
            hideProgress();
            skipActivity();
        }
    }

    private boolean isDown() {
        Boolean b = true;
        if (FileUtil.getInstance().getPrintBGSize("ssj")==8) {
            b = false;
        }
        return b;
    }

    private String BASE_URL= NetClient.IMG_PRE+"/PrintImgs"+"/";
    public List<String> getUrllist() {
        String dir = "ssj";
        List<String> list = new ArrayList<>();
        list.add(BASE_URL+dir+"/ssj_1.jpg");
        list.add(BASE_URL+dir+"/ssj_2.jpg");
        list.add(BASE_URL+dir+"/ssj_3.jpg");
        list.add(BASE_URL+dir+"/ssj_4.jpg");
        list.add(BASE_URL+dir+"/ssj_5.jpg");
        list.add(BASE_URL+dir+"/ssj_6.jpg");
        list.add(BASE_URL+dir+"/ssj_7.jpg");
        list.add(BASE_URL+dir+"/ssj_8.jpg");
        return list;
    }

    public List<String> getFileNames() {
        List<String> list = new ArrayList<>();
        list.add("ssj_1.jpg");
        list.add("ssj_2.jpg");
        list.add("ssj_3.jpg");
        list.add("ssj_4.jpg");
        list.add("ssj_5.jpg");
        list.add("ssj_6.jpg");
        list.add("ssj_7.jpg");
        list.add("ssj_8.jpg");
        return list;
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
                    if (isDown()) {
                        List<String> urllist = getUrllist();
                        List<String> fileNames = getFileNames();
                        showProgress();
                        Toast.makeText(this, "正在下载打印模板", Toast.LENGTH_SHORT).show();
                        for (int i=0;i<urllist.size();i++) {
                            getPresenter().downPrintImg(urllist.get(i), fileNames.get(i));
                        }
                    }else{
                        skipActivity();
                    }
                })
                .setNegativeButton("否", (dialog, which) -> {
                    startActivity(new Intent(this, MainActivity.class));
                }).show();
    }

    public void skipActivity(){
        Intent intent=new Intent(DoubleRandomOnePublicPrintActivity.this, PrintAcitivty.class);
        Bundle bundle=new Bundle();
        bundle.putString(Constance.START_ACTIVITY_TYPE,String.valueOf(TableNum));
        bundle.putSerializable(Constance.ACTIVITY_DATA,bean);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
