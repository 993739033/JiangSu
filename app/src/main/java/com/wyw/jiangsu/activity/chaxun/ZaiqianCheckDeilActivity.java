package com.wyw.jiangsu.activity.chaxun;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.QuerySlaughterBeforeItmeAdapter;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.ZaiqianCheckQueryBean;
import com.wyw.jiangsu.bean.SlaughterBeforeBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 宰前检查查询详情
 * Created by Administrator on 2017/4/20.
 */
public class ZaiqianCheckDeilActivity extends MVPActivity {

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
    @BindView(R.id.et_check_number)
    EditText etCheckNumber;
    @BindView(R.id.et_shijian)
    TextView etShijian;
    @BindView(R.id.slaughter_listview)
    NoScrollListView slaughterListview;
    @BindView(R.id.et_mustKill_number)
    EditText etMustKillNumber;
    @BindView(R.id.et_process_number)
    EditText etProcessNumber;
    @BindView(R.id.et_caiqu_cuoshi)
    TextView etCaiquCuoshi;
    @BindView(R.id.et_qtnnNumber)
    EditText etQtnnNumber;
    @BindView(R.id.et_coliStockQuantity)
    EditText etColiStockQuantity;
    @BindView(R.id.et_quarantine_personnel)
    EditText etQuarantinePersonnel;
    @BindView(R.id.et_recored_personnel)
    EditText etRecoredPersonnel;
    @BindView(R.id.rootView)
    ScrollView rootView;

    @BindView(R.id.activity_slaughter_inspection_before)
    LinearLayout activitySlaughterInspectionBefore;
    private ZaiqianCheckQueryBean.DataListBean bean;

    QuerySlaughterBeforeItmeAdapter adapter;

    String[] title = {"精神状况", "外貌", "呼吸状况", "运动状况", "饮水饮食情况", "排泄物状态", "精神状况", "体温", "呼吸状况", "排泄情况"};
    String[] title1 = {"精神状况", "外貌", "呼吸状况", "运动状况", "饮水饮食情况", "排泄物状态", "精神状况", "体温", "呼吸状况", "皮肤", "蹄部",
            "可视黏膜", "体表淋巴结", "排泄情况"};
    String[] title2 = {"精神状况", "外貌", "呼吸状况", "运动状况", "饮水饮食情况", "排泄物状态", "精神状况", "体温", "呼吸状况", "羽毛", "天然孔",
            "冠、髯、爪", "嗉囊内容物性状", "排泄情况"};
    private List<ZaiqianCheckQueryBean.DataListBean.DataList1Bean> dataList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zaiqian_check_deil_activity);
        ButterKnife.bind(this);
        rootView.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {

        tvTitle.setText("宰前检查查询详情");
        adapter = new QuerySlaughterBeforeItmeAdapter(this, new ArrayList<>());
        bean = (ZaiqianCheckQueryBean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        dataList1 = bean.getDataList1();
        adapter.setmDatas(getDataList(bean.getFAnimalSpecies()));
        slaughterListview.setAdapter(adapter);
        etQuarantineNo.setText(bean.getTZJYBH());//屠宰检疫编号
        etDeclarationNo.setText(bean.getSBDBH());//申报单编号
        etCheckNumber.setText(bean.getJCSL());//检查数量
        etShijian.setText(bean.getFDate() + "" + bean.getXs() + "时");//时间
        etMustKillNumber.setText(bean.getZZSL());//准宰数量
        etProcessNumber.setText(bean.getCLSL());//处理数量
        etCaiquCuoshi.setText(bean.getCQCS());//采取措施
        etQtnnNumber.setText(bean.getCode());//出具《检疫处理通知单》
        etColiStockQuantity.setText(bean.getQCSL());//圈存数量
        etQuarantinePersonnel.setText(bean.getJYRY());//检疫人员
        etRecoredPersonnel.setText(bean.getJLRY());//记录人员

    }


    private List<SlaughterBeforeBean.DataList> getDataList(String type) {
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
                if(dataList1 != null){
                    entity.setIsSomething(dataList1.get(i).getDR());
                    entity.setQuanNo(dataList1.get(i).getQH());
                    entity.setNumber(dataList1.get(i).getSL());
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
                if(dataList1 != null){
                    entity.setIsSomething(dataList1.get(i).getDR());
                    entity.setQuanNo(dataList1.get(i).getQH());
                    entity.setNumber(dataList1.get(i).getSL());
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
                if(dataList1 != null){
                    entity.setIsSomething(dataList1.get(i).getDR());
                    entity.setQuanNo(dataList1.get(i).getQH());
                    entity.setNumber(dataList1.get(i).getSL());
                }
                entity.setType(title1[i]);
                list.add(entity);
            }
        }

        return list;
    }


    @Override
    public void bindListener() {

    }
}
