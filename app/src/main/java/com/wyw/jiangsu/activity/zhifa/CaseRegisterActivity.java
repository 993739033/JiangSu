package com.wyw.jiangsu.activity.zhifa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.chaxun.BreedingAdapter;
import com.wyw.jiangsu.activity.chaxun.BreedingDetailActivity;
import com.wyw.jiangsu.activity.model.HeadSearchView;
import com.wyw.jiangsu.adapter.CaseRegisterAdapter;
import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.bean.CaseRegisterBean;
import com.wyw.jiangsu.bean.ChufaListBean;
import com.wyw.jiangsu.bean.XianchangCheckBean;
import com.wyw.jiangsu.bean.XunWenListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IACaseRegisterActivity;
import com.wyw.jiangsu.presenter.CaseRegisterPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * Created by mnkj004 on 2017/8/11.
 */

public class CaseRegisterActivity extends MVPActivity<CaseRegisterPresenter> implements IACaseRegisterActivity, SpringView.OnFreshListener {

    public static String table_1 = "dengji";
    public static String table_2 = "jianchabilu";
    public static String table_3 = "xunwenbilu";
    public static String table_4 = "chufa";
    public static String current_table = "";
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
    @BindView(R.id.rv_list)
    RecyclerView recycleViewList;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;

    String value = "";
    String startStr = "";
    String endStr = "";
    String fstid = "";
    private CaseRegisterAdapter adapter;
    private String tableName;
    private String name;
    private int beanType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_recycle);
    }

    @Override
    protected CaseRegisterPresenter createPresenter() {
        return new CaseRegisterPresenter(this);
    }

    @Override
    public void bindData() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String tableString = intent.getStringExtra(Constance.ZHIFAQUERY_TABLE);
        if (tableString.equals(table_1)) {
            setTitle("案件登记列表");
            current_table = table_1;
            tableName = "App_ZFDJ";
            beanType = 1;
            name = "Fdjrq,Fany,Fly,Fajlx";
        } else if (tableString.equals(table_2)) {
            setTitle("现场检查（勘验）笔录列表");
            current_table = table_2;
            tableName = "App_ZF_xcjcbl";
            name = "Fsdate,Fdz,Fxwjg,Fdsr,Fjg,Fjlr";
            beanType = 2;
        } else if (tableString.equals(table_3)) {
            setTitle("询问笔录列表");
            current_table = table_3;
            tableName = "App_ZF_xwbl";
            name = "Fsdate,Fdz,Fjg,Fxwr,Fname";
            beanType = 3;
        } else if (tableString.equals(table_4)) {
            setTitle("出具当场决定处罚书列表");
            current_table = table_4;
            tableName = "App_ZF_punishment";
            name = "zfdate,xm,zhuzhi,dwmc,dwdh,sfzh,dizhi";
            beanType = 4;
        }

        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        recycleViewList.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleViewList.setLayoutManager(manager);
        recycleViewList.setItemAnimator(new DefaultItemAnimator());
        HeadSearchView headerView = new HeadSearchView(this, recycleViewList, new HeadSearchView.OnSearchListener() {
            @Override
            public void onSearch(String strat, String end, String data) {
                value = data;
                startStr = strat;
                endStr = end;

                    if (beanType == 1) {
                        getPresenter().getCaseRegisterData(beanType,tableName,name,data, strat, end);
                    }else if(beanType ==2){
                        getPresenter().getXianChangData(beanType,tableName,name,data, strat, end);
                    }else if(beanType ==3){
                        getPresenter().getXunwenData(beanType,tableName,name,data, strat, end);
                    }else if(beanType ==4){
                        getPresenter().getChufaData(beanType,tableName,name,data, strat, end);
                    }

            }
        });
        adapter = new CaseRegisterAdapter(this, new ArrayList<>(), headerView.getHeaderView(),current_table);
        recycleViewList.setAdapter(adapter);

    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(recycleViewList).setOnItemClickListener((recyclerView, position, v) -> {
            if (position != 0) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getDates().get(position - 1));
                intent.putExtras(bundle);
                if (current_table.equals(CaseRegisterActivity.table_1)){
                    startActivity(intent.setClass(this,CaseRegisterDetailActivity.class));
                }else if (current_table.equals(CaseRegisterActivity.table_2)){
                    startActivity(intent.setClass(this,JianchaDetailActivity.class));
                }else if (current_table.equals(CaseRegisterActivity.table_3)){
                    startActivity(intent.setClass(this,XunwenDetailActivity.class));
                }else if (current_table.equals(CaseRegisterActivity.table_4)){
                    startActivity(intent.setClass(this,ChufaDetailActivity.class));
                }

            }
        });
    }

    /**
     * 手动刷新
     */
    private void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                getPresenter().search();
                springView.callFresh();
            }
        }, 500);
    }


    @Override
    public void addListData(List<?> dataListEntity) {
        adapter.addAll(dataListEntity);
    }

    @Override
    public void setData(List<?> dataList) {
        adapter.setmDatas(dataList);
    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onRefresh() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        if (adapter.getDates().size() > 0) {
            if (current_table.equals(CaseRegisterActivity.table_1)){
                getPresenter().loadCaseData(beanType,value, startStr, endStr, getfStId(),tableName,name);
            }else if (current_table.equals(CaseRegisterActivity.table_2)){
                getPresenter().loadXianchangData(beanType,value, startStr, endStr, getfStId(),tableName,name);
            }else if (current_table.equals(CaseRegisterActivity.table_3)){
                getPresenter().loadXunwenData(beanType,value, startStr, endStr, getfStId(),tableName,name);
            }else if (current_table.equals(CaseRegisterActivity.table_4)){
                getPresenter().loadChufaData(beanType,value, startStr, endStr, getfStId(),tableName,name);
            }

        }
    }

    private String getfStId() {
        Object object = adapter.getDates().get(adapter.getDates().size() - 1);
        if (beanType == 1) {
            CaseRegisterBean.DataListBean bean = (CaseRegisterBean.DataListBean) object;
            return bean.getFStId();
        } else if (beanType ==2) {
            XianchangCheckBean.DataListBean bean = (XianchangCheckBean.DataListBean) object;
            return bean.getFStId();
        } else if (beanType==3) {
            XunWenListBean.DataListBean bean = (XunWenListBean.DataListBean) object;
            return bean.getFStId();
        } else if (beanType==4) {
            ChufaListBean.DataListBean bean = (ChufaListBean.DataListBean) object;
            return bean.getFStId();
        }
        return "";
    }
}
