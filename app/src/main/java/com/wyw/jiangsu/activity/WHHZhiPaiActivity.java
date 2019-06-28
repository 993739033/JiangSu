package com.wyw.jiangsu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.WHHZhiPaiAdapter;
import com.wyw.jiangsu.bean.WHHZhiPaiBean;
import com.wyw.jiangsu.bean.WHHZhiPaiChooseBean;
import com.wyw.jiangsu.bean.WHHZhiPaiUploadBean;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IWHHZhiPaiActivity;
import com.wyw.jiangsu.presenter.WHHZhiPaiActivityPresenter;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 指派界面
 * Created by Administrator on 2017/6/13.
 */
public class WHHZhiPaiActivity extends MVPActivity<WHHZhiPaiActivityPresenter> implements IWHHZhiPaiActivity {

    @BindView(R.id.et_xiaoxi_fasongren)
    EditText etXiaoxiFasongren;
    //    @BindView(R.id.sp_shoujiren)
    //    Spinner spShoujiren;
    @BindView(R.id.sp_shoujiren)
    TextView tv_shoujiren;//收集人
    @BindView(R.id.et_chepai_hao)
    EditText etChepaiHao;
    @BindView(R.id.btn_xuanze)
    Button btnXuanze;
    @BindView(R.id.btn_baocun)
    Button btnBaocun;
    @BindView(R.id.rootView)
    ScrollView rootView;
    @BindView(R.id.lv_shouji)
    RecyclerView lvShouji;
    //    @BindView(R.id.sp_chepai_hao)
    //    Spinner spChepaiHao;
    @BindView(R.id.sp_chepai_hao)
    TextView tvChepaiHao;//车牌号
    private WHHZhiPaiAdapter adapter;
    private List<WHHZhiPaiBean.DataListBean> dataListBean;
    private final String uuid = UUID.randomUUID().toString();
    //要上传的实体类
    private WHHZhiPaiUploadBean bean = new WHHZhiPaiUploadBean();
    private String fStIdd1;
    private List<WHHZhiPaiChooseBean.DataListBean> list1;

    private DataSelectDialog sjrSelectDialog;//收集人选择
    private DataSelectDialog cphSelectDialog;//车牌号选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhipai_layout);
        ButterKnife.bind(this);
        initDialog();
    }

    private void initDialog() {
        //收集人选择弹窗
        sjrSelectDialog = new DataSelectDialog(this);
        sjrSelectDialog.setTitle("选择收集人");
        tv_shoujiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjrSelectDialog.show();
            }
        });

        sjrSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                int position = sjrSelectDialog.getNowIndex();
                tv_shoujiren.setText(data);
                List<String> xzchepai = new ArrayList<>();
                if (null != dataListBean && dataListBean.size() != 0) {
                    for (int j = 0; j < dataListBean.get(position).getDataList1().size(); j++) {
                        xzchepai.add(dataListBean.get(position).getDataList1().get(j).getCPH1());
                    }
                }
                cphSelectDialog.setDatas(xzchepai);
                if (null == xzchepai || xzchepai.size() == 0) {
                    etChepaiHao.setText("");
                    tvChepaiHao.setText("请选择");
                }
            }

            @Override
            public void cancel() {

            }
        });

        cphSelectDialog = new DataSelectDialog(this);
        cphSelectDialog.setTitle("选择车牌号");
        cphSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                tvChepaiHao.setText(data);
                etChepaiHao.setText(data);

            }

            @Override
            public void cancel() {

            }
        });

        tvChepaiHao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cphSelectDialog.show();
            }
        });


    }

    @Override
    protected WHHZhiPaiActivityPresenter createPresenter() {
        return new WHHZhiPaiActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("收集任务指派");
        adapter = new WHHZhiPaiAdapter(new ArrayList<>());
        lvShouji.setLayoutManager(new FullyLinearLayoutManager(this));
        lvShouji.setHasFixedSize(true);
        lvShouji.setAdapter(adapter);
        getPresenter().getShouyiXinxi(getPresenter().getUserId());//获取收集信息
        etXiaoxiFasongren.setText(getPresenter().getUser().getFUNAME());        //消息发送人

    }

    @Override
    public void bindListener() {
        btnXuanze.setOnClickListener(v -> {
            Intent intent = new Intent(this, ZhiPaiSearchActivity.class);
            startActivityForResult(intent, Constance.ACTIVITY_CODE);
        });
//        spShoujiren.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                List<String> xzchepai = new ArrayList<>();
//                if (null != dataListBean && dataListBean.size() != 0) {
//                    for (int j = 0; j < dataListBean.get(position).getDataList1().size(); j++) {
//                        xzchepai.add(dataListBean.get(position).getDataList1().get(j).getCPH1());
//                    }
//                }
//                spChepaiHao.setAdapter(new ArrayAdapter<>(WHHZhiPaiActivity.this, R.layout.spinner_simple, R.id.tv, xzchepai)); //车牌号
//                if (null == xzchepai || xzchepai.size() == 0) {
//                    etChepaiHao.setText("");
//                }
//            }
//        });
//        spChepaiHao.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                etChepaiHao.setText(spChepaiHao.getSelectedItem().toString());
//            }
//        });

        btnBaocun.setOnClickListener(v -> {
            if (check()) {
                showNormalDialog(uuid);
            }
        });
    }

    private void showNormalDialog(String uuid) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(WHHZhiPaiActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upLoad(getData(), uuid);
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

    private WHHZhiPaiUploadBean getData() {
        bean = new WHHZhiPaiUploadBean();
        bean.setCPH(etChepaiHao.getText().toString());  //车牌号
        bean.setCPH1(etChepaiHao.getText().toString());  //车牌号
//        bean.setSJR(spShoujiren.getSelectedItem().toString()); //收集人
        bean.setSJR(tv_shoujiren.getText().toString()); //收集人
        bean.setXXFSR(etXiaoxiFasongren.getText().toString());//消息发送人
        for (int i = 0; i < dataListBean.size(); i++) {
//            if (spShoujiren.getSelectedItem().toString().equals(dataListBean.get(i).getFuName())) {
//                fStIdd1 = dataListBean.get(i).getFStId();
//            }

            if (tv_shoujiren.getText().toString().equals(dataListBean.get(i).getFuName())) {
                fStIdd1 = dataListBean.get(i).getFStId();
            }
        }

        bean.setSJRID(Integer.parseInt(fStIdd1));//收集人id
        List<WHHZhiPaiUploadBean.DataList1Bean> dataList = new ArrayList<>();
        for (int i = 0; i < adapter.getData().size(); i++) {
            WHHZhiPaiUploadBean.DataList1Bean dataList1Bean = new WHHZhiPaiUploadBean.DataList1Bean();
            dataList1Bean.setBSDWZL(list1.get(i).getFBsdwType());//病死动物种类
            dataList1Bean.setBSDWSL(list1.get(i).getFBsdwNum());//病死动物数量
            dataList1Bean.setCBS(list1.get(i).getFBsdwWeight());//病死动物重量
            dataList1Bean.setBSCQCZDZ(list1.get(i).getLXDZ());//地址
            dataList1Bean.setBSCQCZXM(list1.get(i).getYZCCZMC());//收集点名称
            if (!TextUtils.isEmpty(list1.get(i).getFWhhGlid())) {
                dataList1Bean.setFBSGlid(Integer.parseInt(list1.get(i).getFWhhGlid()));//病死动物id
            } else {
                dataList1Bean.setFBSGlid(0);//病死动物id
            }
            if (!TextUtils.isEmpty(list1.get(i).getFStId())) {
                dataList1Bean.setDWMCID(Integer.parseInt(list1.get(i).getFStId()));//入库单关联id
            } else {
                dataList1Bean.setDWMCID(0);//入库单关联id
            }
            dataList.add(dataList1Bean);
        }
        bean.setDataList(dataList);

        return bean;
    }

    private boolean check() {
        if (list1 == null || list1.size() == 0) {
            Toast.makeText(this, "请选择收集点", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tv_shoujiren.getText().toString()) || tv_shoujiren.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请输入收集人", Toast.LENGTH_SHORT).show();
            return false;
        }

//        if (TextUtils.isEmpty(tvChepaiHao.getText().toString())||tvChepaiHao.getText().toString().equals("请选择")) {
//            Toast.makeText(this, "请选择车牌号", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        if (TextUtils.isEmpty(etChepaiHao.getText().toString())) {
            Toast.makeText(this, "请输入或选择车牌号", Toast.LENGTH_SHORT).show();
            return false;
        }


        for (int i = 0; i < list1.size(); i++) {
            if (TextUtils.isEmpty(list1.get(i).getFBsdwType())) {
                Toast.makeText(this, "病死动物种类不能为空", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(list1.get(i).getFBsdwNum())) {
                Toast.makeText(this, "病死动物数量不能为空", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(list1.get(i).getFBsdwWeight())) {
                Toast.makeText(this, "病死动物重量不能为空", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (Integer.parseInt(list1.get(i).getFBsdwNum()) <= 0) {
                Toast.makeText(this, "病死动物数量不能小于或等于0", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    @Override
    public void zhipaiXinxi(List<WHHZhiPaiBean.DataListBean> dataListBean) {
        this.dataListBean = dataListBean;
        List<String> shouji = new ArrayList<>();
        for (int i = 0; i < dataListBean.size(); i++) {
            shouji.add(dataListBean.get(i).getFuName());
        }

        List<String> chepai = new ArrayList<>();
        if (null != dataListBean && dataListBean.size() != 0) {
            for (int j = 0; j < dataListBean.get(0).getDataList1().size(); j++) {
                chepai.add(dataListBean.get(0).getDataList1().get(j).getCPH1());
            }
        }

//        spChepaiHao.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, chepai)); //车牌号

        cphSelectDialog.setDatas(chepai); //车牌号
        StringBuffer sb = new StringBuffer();
        if (null != dataListBean && dataListBean.size() != 0
                && null != dataListBean.get(0).getDataList1()
                && dataListBean.get(0).getDataList1().size() != 0) {
            sb.append(dataListBean.get(0).getDataList1().get(0).getCPH1());
        }
//        etChepaiHao.setText(sb.toString());         //车牌号

//        spShoujiren.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, shouji)); //收集人

        sjrSelectDialog.setDatas(shouji); //收集人
    }

    @Override
    public void upLoadSucceed() {
        Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                list1 = (List<WHHZhiPaiChooseBean.DataListBean>) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                if (list1 == null || list1.size() == 0) {
//
                } else {
                    adapter.clear();
                    adapter.setNewData(list1);
                    lvShouji.setAdapter(adapter);
                }
            }
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
