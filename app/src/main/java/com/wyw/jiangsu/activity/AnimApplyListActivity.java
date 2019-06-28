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

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.GuarantineDeclareListDetilAdapter;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimApplylistActivity;
import com.wyw.jiangsu.presenter.AnimApplylistActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 动物检疫省报列表
 */
public class AnimApplyListActivity extends MVPActivity<AnimApplylistActivityPresenter> implements IAnimApplylistActivity, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.springView)
    SpringView springView;

    private GuarantineDeclareListDetilAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
    }

    @Override
    protected AnimApplylistActivityPresenter createPresenter() {
        return new AnimApplylistActivityPresenter(this);
    }

    @Override
    public void bindData() {
        getAdd().setVisibility(View.VISIBLE);
        setTitle("产地检疫申报单列表");
        adapter = new GuarantineDeclareListDetilAdapter(new ArrayList<>());
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
//        springView.setHeader(new AliHeader(this,R.drawable.ali,true));   //参数为：logo图片资源，是否显示文字
//        springView.setFooter(new AliFooter(this,false));
        springView.setHeader(new RotationHeader(this));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(this));
        springView.setListener(this);
        getPresenter().refresh();

//        RxBus2.getDefault().tObservable(NoDealWithInfoBean.class)
//                //在iox线程进行订阅,可以执行一些耗时操作
//                .subscribeOn(Schedulers.io())
//                //在主线程进行观察,可以作UI更新操作
//                .observeOn(AndroidSchedulers.mainThread())
//                //观察者对象
//                .subscribe(noDealWithInfoBean -> {
//                    if (noDealWithInfoBean.getInfo().equals("测试")){
//                        Toast.makeText(this,"这是测试",Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    @Override
    public void bindListener() {
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //Toast.makeText(AnimApplyListActivity.this,adapter.getItem(position).getFSenterpriseId(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AnimApplyListActivity.this,AnimApplyListDetilActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        viewEmpty.setOnClickListener(v -> manualRefresh());
        getAdd().setOnClickListener(v -> startActivityForResult(new Intent(this,AnimAddActivity.class), Constance.ACTIVITY_REQUEST_CODE));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        manualRefresh();
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

    @Override
    public void refresh(List<GuarantineDeclareListDetilBean.DataListBean> dataListBean) {
        if (dataListBean != null) {
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListBean);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.VISIBLE);
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<GuarantineDeclareListDetilBean.DataListBean> dataListBean) {
        if (dataListBean != null) {
            adapter.addData(dataListBean);
        } else {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }
}
