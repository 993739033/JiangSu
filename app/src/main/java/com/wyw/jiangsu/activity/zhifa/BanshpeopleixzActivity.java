package com.wyw.jiangsu.activity.zhifa;
/**
 * 添加办案人员
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.BanshpeopleixzActivityAdapter;
import com.wyw.jiangsu.bean.BanshpeopleleixzActivityBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IBanshpeopleixzActivity;
import com.wyw.jiangsu.presenter.BanshpeopleixzActivityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

public class BanshpeopleixzActivity extends MVPActivity<BanshpeopleixzActivityPresenter> implements IBanshpeopleixzActivity, SpringView.OnFreshListener {

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
    @BindView(R.id.btn_xuanze)
    Button btnXuanze;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private BanshpeopleixzActivityAdapter adapter;
    private BanshpeopleleixzActivityBean.DataListBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banshpeopleixz);
        ButterKnife.bind(this);
    }

    @Override
    protected BanshpeopleixzActivityPresenter createPresenter() {
        return new BanshpeopleixzActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("检查人员选择");
        recycler.setHasFixedSize(true);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        getPresenter().getFarmDeclare(getPresenter().getUser().getFSUNITUSTRID());

        adapter = new BanshpeopleixzActivityAdapter(new ArrayList<>());
        recycler.setAdapter(adapter);
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());

        btnXuanze.setOnClickListener(v -> {
            List<BanshpeopleleixzActivityBean.DataListBean> list = new ArrayList<>();

            for (int i = 0; i < adapter.getData().size(); i++) {
                if (adapter.getData().get(i).isCheak()) {
                    list.add(adapter.getData().get(i));
                }
            }
            if (list.size() != 2) {
                Toast.makeText(this, "办案人员要选两个", Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = getIntent();
            intent.putExtra(Constance.ACTIVITY_DATA, (Serializable) list);
            setResult(Activity.RESULT_OK, intent);
            finish();


        });
    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().getFarmDeclare(getPresenter().getUser().getFSUNITUSTRID());
                springView.callFresh();
            }
        }, 500);
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
    public void onRefresh() {
        springView.onFinishFreshAndLoad();
        getPresenter().getFarmDeclare(getPresenter().getUser().getFSUNITUSTRID());
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().getFarmDeclare(getPresenter().getUser().getFSUNITUSTRID());
    }

    @Override
    public void setData(List<BanshpeopleleixzActivityBean.DataListBean> dataListEntity) {
        if (dataListEntity.size() == 0) {
            viewEmpty.setVisibility(View.VISIBLE);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListEntity);
        }
    }
}
