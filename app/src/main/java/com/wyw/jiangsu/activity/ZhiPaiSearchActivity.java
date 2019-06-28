package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.WHHZhiPaiChooseAdapter;
import com.wyw.jiangsu.bean.WHHZhiPaiChooseBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IZhiPaiSearchActivity;
import com.wyw.jiangsu.presenter.ZhiPaiSearchActivityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 指派查询界面
 * Created by Administrator on 2017/6/13.
 */
public class ZhiPaiSearchActivity extends MVPActivity<ZhiPaiSearchActivityPresenter> implements IZhiPaiSearchActivity, SpringView.OnFreshListener {

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.results_container)
    FrameLayout resultsContainer;
    private WHHZhiPaiChooseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhipai_search_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected ZhiPaiSearchActivityPresenter createPresenter() {
        return new ZhiPaiSearchActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("收集点选择");
        setupSearvhView();
        getPresenter().refresh();
        adapter = new WHHZhiPaiChooseAdapter(new ArrayList<>());

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(this));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(this));
        springView.setListener(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setupSearvhView() {

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("请输入关键字");
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().searchUserDetil(query);
                viewEmpty.setVisibility(View.GONE);
                return true;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    getPresenter().searchUserDetil(newText);
                }
                return true;
            }
        });
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        btnQueren.setOnClickListener(v -> {
            List<WHHZhiPaiChooseBean.DataListBean> list = new ArrayList<WHHZhiPaiChooseBean.DataListBean>();
            for (int i = 0; i < adapter.getData().size(); i++) {
                if (adapter.getData().get(i).isCheak()) {
                    list.add(adapter.getData().get(i));
                }
            }

            Intent intent = new Intent();
            intent.putExtra(Constance.ACTIVITY_DATA, (Serializable) list);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
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
        manualRefresh();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().loadmore();
    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        getPresenter().refresh();
    }

    @Override
    public void searchComplete(List<WHHZhiPaiChooseBean.DataListBean> datas) {
        if (null != datas) {
            if (datas.size() == 0) {
                viewEmpty.setVisibility(View.VISIBLE);
            } else {
                adapter.clear();
                viewEmpty.setVisibility(View.GONE);
                adapter.setNewData(datas);
            }

        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<WHHZhiPaiChooseBean.DataListBean> datas) {
        if (datas == null) {
            Toast.makeText(this, "没有数据了", Toast.LENGTH_SHORT).show();
        } else {
            adapter.addData(datas);
        }
        springView.onFinishFreshAndLoad();
    }
}
