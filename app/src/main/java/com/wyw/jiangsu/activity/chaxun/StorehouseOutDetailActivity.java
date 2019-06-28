package com.wyw.jiangsu.activity.chaxun;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.StorehouseOutDetailAdapter;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.ChuKuDeatilBean;
import com.wyw.jiangsu.bean.StoreChukuBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IStorehouseOutDetailActivity;
import com.wyw.jiangsu.presenter.StorehouseOutDetailPresenter;
import com.wyw.jiangsu.view.CustomScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申报消息接收查询列表详情
 * Created by Administrator on 2017/3/28.
 */
public class StorehouseOutDetailActivity extends MVPActivity<StorehouseOutDetailPresenter> implements IStorehouseOutDetailActivity {


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
    @BindView(R.id.et_liushui_hao)
    EditText etLiushuiHao;
    @BindView(R.id.et_shoujidian_name)
    TextView etShoujidianName;
    @BindView(R.id.et_shoujidian_adress)
    EditText etShoujidianAdress;
    @BindView(R.id.et_chulizhongxin)
    EditText etChulizhongxin;
    @BindView(R.id.et_chukudate)
    TextView etChukudate;
    @BindView(R.id.vet_name)
    TextView vetName;
    @BindView(R.id.vet_chuzhengshu)
    TextView vetChuzhengshu;
    @BindView(R.id.vet_wuhaihua)
    TextView vetWuhaihua;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.rootView)
    CustomScrollView rootView;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    private StoreChukuBean.DataListBean dataListBeen;
    private StorehouseOutDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_chuku_detail_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected StorehouseOutDetailPresenter createPresenter() {
        return new StorehouseOutDetailPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("出库查询详情");
        adapter = new StorehouseOutDetailAdapter(this);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
        dataListBeen = (StoreChukuBean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        int FISid = Integer.valueOf(dataListBeen.getFStId());
        getPresenter().getFarmDeclare("APP_ZCDCKGL", FISid);

    }

    @Override
    public void setData(ChuKuDeatilBean.DataBean dataListEntity) {
        etLiushuiHao.setText(dataListEntity.getFckdNumber());
        etShoujidianName.setText(dataListEntity.getFZcdName());
        etShoujidianAdress.setText(dataListEntity.getFZcddz());
        etChulizhongxin.setText(dataListEntity.getFWhhName());
        etChukudate.setText(dataListEntity.getFckDate());
        List<ChuKuDeatilBean.DataBean.DataList1Bean> dataList1 = dataListEntity.getDataList1();
        adapter.setData(dataList1);
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {

    }



    @Override
    public void deletesucess(BaseMsgBean msg) {

    }
}

