package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.Qua_AnimalProductsABeanAdapter;
import com.wyw.jiangsu.bean.ProductA_BBeanListGai;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IProductBListActivity;
import com.wyw.jiangsu.presenter.ProductBListActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * Created by Administrator on 2017/3/15.
 */

public class ProductBListActivity extends MVPActivity<ProductBListActivityPresenter> implements IProductBListActivity, SpringView.OnFreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;

    Qua_AnimalProductsABeanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productsb_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected ProductBListActivityPresenter createPresenter() {
        return new ProductBListActivityPresenter(this);
    }

    @Override
    public void bindData() {
//        getAdd().setVisibility(View.VISIBLE);
        setTitle("产品B列表");
        adapter = new Qua_AnimalProductsABeanAdapter(null);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        recycler.setItemAnimator(new DefaultItemAnimator());
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        //进来时，先刷新
        getPresenter().refresh();
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
//            startActivityForResult(new Intent(getContext(),AnimProcessListDetilActivity.class), Activity.RESULT_FIRST_USER);
            Intent intent = new Intent(this, ProductBActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getItem(position));
            intent.putExtras(bundle);
//            startActivity(intent);
            startActivityForResult(intent, Constance.ACTIVITY_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                ProductA_BBeanListGai.DataListBean bean = (ProductA_BBeanListGai.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                manualRefresh();
            }
        }
    }

    /**
     * 手动刷新
     */

    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().refresh();
                springView.callFresh();
            }
        }, 500);
    }

    @Override
    public void onRefresh() {
        springView.onFinishFreshAndLoad();
        getPresenter().refresh();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().loadmore();
    }

    @Override
    public void refresh(List<ProductA_BBeanListGai.DataListBean> dataListBean) {
        if (dataListBean != null) {
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListBean);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.INVISIBLE);
            Toast.makeText(MyApplication.getContext(), "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<ProductA_BBeanListGai.DataListBean> dataListBean) {
        if (dataListBean == null) {
            Toast.makeText(MyApplication.getContext(), "没有数据", Toast.LENGTH_SHORT).show();
        } else {
            adapter.addData(dataListBean);
        }

        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onError() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
    }
}
