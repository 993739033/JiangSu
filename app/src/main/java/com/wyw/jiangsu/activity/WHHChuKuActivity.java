package com.wyw.jiangsu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.WHHChuKuAdapter;
import com.wyw.jiangsu.bean.WHHChuKuChooseBean;
import com.wyw.jiangsu.bean.WHHChuKuUploadBean;
import com.wyw.jiangsu.bean.WHHChukuBean;
import com.wyw.jiangsu.bean.WHHChukuFirstBean;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IWHHChuKuActivity;
import com.wyw.jiangsu.presenter.WHHChuKuActivityPresenter;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 出库管理
 * Created by Administrator on 2017/6/13.
 */
public class WHHChuKuActivity extends MVPActivity<WHHChuKuActivityPresenter> implements IWHHChuKuActivity {

    @BindView(R.id.et_liushui_hao)
    EditText etLiushuiHao;
    @BindView(R.id.et_chulizhongxin)
    EditText etShoujidianName;      //处理中心
    @BindView(R.id.et_shoujidian_adress)
    EditText etShoujidianAdress;
    @BindView(R.id.sp_shoujidian_name)
    TextView spChuliZhongxin;       //收集点名称
//    @BindView(R.id.sp_nian)
//    Spinner spNian;
//    @BindView(R.id.sp_yue)
//    Spinner spYue;
//    @BindView(R.id.sp_ri)
//    Spinner spRi;
    @BindView(R.id.rootView)
    ScrollView rootView;
    @BindView(R.id.btn_choose)
    Button btnChoose;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_baocun)
    Button btnBaocun;
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
    @BindView(R.id.vet_name)
    TextView vetName;
    @BindView(R.id.vet_chuzhengshu)
    TextView vetChuzhengshu;
    @BindView(R.id.vet_wuhaihua)
    TextView vetWuhaihua;
    @BindView(R.id.tv_date)
    TextView tv_date;

    private List<String> endDayList;
    private WHHChuKuAdapter adapter;
    //要上传的实体类
    private WHHChuKuUploadBean bean = new WHHChuKuUploadBean();
    private String fStId;
    private WHHChukuBean.DataBean dataListBean;
    private List<WHHChuKuChooseBean.DataListBean> list1;
    private final String uuid = UUID.randomUUID().toString();

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            list5 = (List<WHHChuKuChooseBean.DataListBean>) msg.obj;
        }
    };
    private List<WHHChuKuChooseBean.DataListBean> list5;
    //每条的病死动物数量
    private int[] in;
    //每条的病死动物重量
    private String[] doubles;
    private String fSm1;
    private String fSm2;

    private DataSelectDialog sjdSelectDialog;//收集点选择
    private TimeSelectDialog ckTimeDialog;//出库时间选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whh_chuku_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected WHHChuKuActivityPresenter createPresenter() {
        return new WHHChuKuActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("出库管理");
        getPresenter().getChukuXinxi1(getPresenter().getUserId(),"0");
        adapter = new WHHChuKuAdapter(this, new ArrayList<>(), mHandler);
        recycler.setLayoutManager(new FullyLinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        setTime();
        initDialog();
    }

    private void initDialog() {
        //收集点选择弹窗
        sjdSelectDialog = new DataSelectDialog(this);
        sjdSelectDialog.setTitle("选择收集点");
        spChuliZhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjdSelectDialog.show();
            }
        });

        ckTimeDialog = new TimeSelectDialog(this, "选择出库日期");
        ckTimeDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                tv_date.setText(year+"-"+month+"-"+day);
            }
        });
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ckTimeDialog.show();
            }
        });

        tv_date.setText(ckTimeDialog.getmCurrentYear()+"-"+ckTimeDialog.getmCurrentMonth()+"-"+ckTimeDialog.getmCurrentDay());

    }

    /**
     * 设置年月日
     */
    private void setTime() {
//        spNian.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createYears()));
//        spYue.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                OtherUtil.createMonths()));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        endDayList = OtherUtil.createDays(String.valueOf(calendar.get(Calendar.YEAR)),
//                String.valueOf(calendar.get(Calendar.MONTH) + 1));
//        spRi.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, endDayList));
//        spNian.setSelection(4);
//        spYue.setSelection(OtherUtil.getCurrentMonthPosition());
//        spRi.setSelection(OtherUtil.getCurrentDayPosition());

    }

    @Override
    public void bindListener() {
//        spNian.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                onStartSpinnerSelect();
//            }
//        });
//
//        spYue.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                onStartSpinnerSelect();
//            }
//        });
        //库存管理选择
        btnChoose.setOnClickListener(v -> {
            Intent intent = new Intent(this, WHHCHuKuChooseActivity.class);
            intent.putExtra("FstId", fStId);
            startActivityForResult(intent, Constance.ACTIVITY_CODE);
        });

        btnBaocun.setOnClickListener(v -> {
            if (check()) {
                showNormalDialog(uuid);
            }

        });
    }
    private void showNormalDialog(String uuid){
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(WHHChuKuActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getData(), uuid);
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
    private WHHChuKuUploadBean getData() {
        bean = new WHHChuKuUploadBean();
        bean.setFZcdGlid(Integer.parseInt(fStId));        //收集点关联ID
//        bean.setFZcdName(spChuliZhongxin.getSelectedItem().toString());       //收集点名称
        bean.setFZcdName(spChuliZhongxin.getText().toString());       //收集点名称
        bean.setFZcddz(etShoujidianAdress.getText().toString());       //收集点地址
        bean.setFckdNumber(etLiushuiHao.getText().toString());       //出库单流水号
//        bean.setFckDate(spNian.getSelectedItem().toString() + "-" + spYue.getSelectedItem().toString()
//                + "-" + spRi.getSelectedItem().toString());//出库日期

        bean.setFckDate(tv_date.getText().toString());//出库日期

        bean.setFWhhName(etShoujidianName.getText().toString());       //无害化处理中心
        bean.setFWhhGlid(fSm1);       //无害化处理中心关联id
        List<WHHChuKuUploadBean.DataList1Bean> uploadlist = new ArrayList<>();
        if (list5 == null) {
            for (int i = 0; i < adapter.getData().size(); i++) {
                WHHChuKuUploadBean.DataList1Bean dataList1Bean = new WHHChuKuUploadBean.DataList1Bean();
                dataList1Bean.setFBsdwType(list1.get(i).getFbsdwzl());//病死动物种类
                dataList1Bean.setFBsdwNum(list1.get(i).getFsws());//病死动物数量
                dataList1Bean.setFBsdwWeight(list1.get(i).getZSL());//病死动物重量
                uploadlist.add(dataList1Bean);
            }
        } else {
            for (int i = 0; i < list5.size(); i++) {
                WHHChuKuUploadBean.DataList1Bean dataList2Bean = new WHHChuKuUploadBean.DataList1Bean();
                dataList2Bean.setFBsdwType(list5.get(i).getFbsdwzl());//病死动物种类
                dataList2Bean.setFBsdwNum(list5.get(i).getFsws());//病死动物数量
                dataList2Bean.setFBsdwWeight(list5.get(i).getZSL());//病死动物重量
                uploadlist.add(dataList2Bean);
            }
        }

        bean.setDataList(uploadlist);
        return bean;
    }

    private boolean check() {
        if (list1 == null || list1.size() == 0) {
            Toast.makeText(this, "请选择库存", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etLiushuiHao.getText().toString())) {
            Toast.makeText(this, "出库单流水号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etShoujidianName.getText().toString())||etShoujidianName.getText().toString().equals("请选择")) {
            Toast.makeText(this, "收集点名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etShoujidianAdress.getText().toString())) {
            Toast.makeText(this, "收集点地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tv_date.getText().toString())||tv_date.getText().toString().equals("请选择")) {
            Toast.makeText(this, "出库日期选择不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (list5 != null) {
            for (int i = 0; i < list5.size(); i++) {
                if (TextUtils.isEmpty(list5.get(i).getFsws())) {
                    Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库数量不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (TextUtils.isEmpty(list5.get(i).getZSL())) {
                    Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库重量不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (Integer.parseInt(list5.get(i).getFsws()) > in[i]) {
                    Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库数量不能超过库存数量", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (Double.parseDouble(list5.get(i).getZSL()) > Double.parseDouble(doubles[i])) {
                    Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库重量不能超过库存重量", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (Double.parseDouble(list5.get(i).getZSL()) <= 0) {
                    Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库重量必须大于0", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (Integer.parseInt(list5.get(i).getFsws()) == in[i]) {
                    if (Double.parseDouble(list5.get(i).getZSL()) != Double.parseDouble(doubles[i])) {
                        Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库数量等于库存数量时，出库重量也必须等于库存重量", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                if (Double.parseDouble(list5.get(i).getZSL()) == Double.parseDouble(doubles[i])) {
                    if (Integer.parseInt(list5.get(i).getFsws()) != in[i]) {
                        Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库重量等于库存重量时，出库数量也必须等于库存数量", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                if (Integer.parseInt(list5.get(i).getFsws()) <= 0) {
                    Toast.makeText(this, list5.get(i).getFbsdwzl() + "的出库数量必须大于0", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (TextUtils.isEmpty(list1.get(i).getFbsdwzl())) {
                    Toast.makeText(this, list1.get(i).getFbsdwzl() + "的出库种类不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (TextUtils.isEmpty(list1.get(i).getFsws())) {
                    Toast.makeText(this, list1.get(i).getFbsdwzl() + "的出库数量不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (TextUtils.isEmpty(list1.get(i).getZSL())) {
                    Toast.makeText(this, list1.get(i).getFbsdwzl() + "的出库重量不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (Integer.parseInt(list1.get(i).getFsws()) <= 0) {
                    Toast.makeText(this, list1.get(i).getFbsdwzl() + "的出库数量必须大于0", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (Double.parseDouble(doubles[i]) <= 0) {
                    Toast.makeText(this, list1.get(i).getFbsdwzl() + "的出库重量必须大于0", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    private void onStartSpinnerSelect() {
        endDayList.clear();
//        String year = spNian.getSelectedItem().toString();
//        Integer month = Integer.valueOf(spYue.getSelectedItem().toString());
//        endDayList.addAll(OtherUtil.createDays(year, String.valueOf(month)));
//        ((ArrayAdapter) spRi.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void chukuXinxi(WHHChukuBean.DataBean dataListBean) {
        /*  private String FStId;
        private String FckdNumber;
        private String FsjdName;
        private String pjdiz;
        private String FSm1;
        private String FSm2;*/
//        this.dataListBean = dataListBean;
        etLiushuiHao.setText(dataListBean.getFckdNumber());     //收集点流水号
        etShoujidianName.setText(dataListBean.getFSm2());   //收集点名称
        etShoujidianAdress.setText(dataListBean.getPjdiz());    //收集点地址
//        //收集点名称ID
        fStId = dataListBean.getFStId();
        fSm1 = dataListBean.getFSm1();
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < dataListBean.getDataList1().size(); i++) {
//            list.add(dataListBean.getDataList1().get(i).getFPlace());
//        }
//        spChuliZhongxin.setAdapter(new ArrayAdapter<>(WHHChuKuActivity.this, R.layout.spinner_simple, R.id.tv, list));
    }

    @Override
    public void chukuXinxi1(List<WHHChukuFirstBean.DataListBean> dataList) {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            nameList.add(dataList.get(i).getFsjdName());
        }
//        spChuliZhongxin.setAdapter(new ArrayAdapter<>(WHHChuKuActivity.this, R.layout.spinner_simple, R.id.tv, nameList));
//        spChuliZhongxin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String fStId = dataList.get(position).getFStId();
//                getPresenter().getChukuXinxi(getPresenter().getUserId(),fStId);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        //每个账号对应唯一一个收集点不用弹窗选择
        spChuliZhongxin.setText(nameList.get(0));

       /* sjdSelectDialog.setDatas(nameList);
        String fStId = dataList.get(sjdSelectDialog.getNowIndex()).getFStId();
        getPresenter().getChukuXinxi(getPresenter().getUserId(),fStId);
*/
        sjdSelectDialog.setDatas(nameList);
        sjdSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                String fStId = dataList.get(sjdSelectDialog.getNowIndex()).getFStId();
                getPresenter().getChukuXinxi(getPresenter().getUserId(),fStId);
                spChuliZhongxin.setText(data);
            }

            @Override
            public void cancel() {

            }
        });
        if (dataList.size()>0) {
            String fStId = dataList.get(0).getFStId();
            getPresenter().getChukuXinxi(getPresenter().getUserId(), fStId);
        }



//        this.dataListBean = dataListBean;
//        etLiushuiHao.setText(dataListBean.getFckdNumber());     //收集点流水号
//        etShoujidianName.setText(dataListBean.getFsjdName());   //收集点名称
//        etShoujidianAdress.setText(dataListBean.getPjdiz());    //收集点地址
//        //收集点名称ID
//        fStId = dataListBean.getFStId();
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < dataListBean.getDataList1().size(); i++) {
//            list.add(dataListBean.getDataList1().get(i).getFPlace());
//        }
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
                list1 = (List<WHHChuKuChooseBean.DataListBean>) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                in = new int[list1.size()];
                doubles = new String[list1.size()];
                for (int i = 0; i < list1.size(); i++) {
                    if (!TextUtils.isEmpty(list1.get(i).getFsws())) {
                        in[i] = Integer.parseInt(list1.get(i).getFsws());
                        doubles[i] = list1.get(i).getZSL();
                    }
                }
                if (list1 == null || list1.size() == 0) {
//
                } else {
                    adapter.clear();
                    adapter.setmDatas(list1);
                    recycler.setAdapter(adapter);
                }
            }
//            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
