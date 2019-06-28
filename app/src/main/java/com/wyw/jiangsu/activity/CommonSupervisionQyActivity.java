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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.CommonSupervisionQyAdapter;
import com.wyw.jiangsu.bean.CommonSupervisionQyBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ICommonSupervisionQy;
import com.wyw.jiangsu.presenter.CommonSupervisionQyPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wyw on 2018/1/25.
 */

public class CommonSupervisionQyActivity extends MVPActivity<CommonSupervisionQyPresenter> implements ICommonSupervisionQy, SpringView.OnFreshListener {

    @BindView(R.id.search_background)
    View searchBackground;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.bt_back)
    ImageButton btBack;
    @BindView(R.id.search_toolbar)
    FrameLayout searchToolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.results_container)
    FrameLayout resultsContainer;
    private CommonSupervisionQyAdapter adapter;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detil_search);
        ButterKnife.bind(this);

    }

    @Override
    protected CommonSupervisionQyPresenter createPresenter() {
        return new CommonSupervisionQyPresenter(this);
    }

    @Override
    public void bindData() {

        setupSearvhView();
        adapter = new CommonSupervisionQyAdapter(new ArrayList<>(), item -> {
            Intent intent = new Intent();
            intent.putExtra(Constance.ACTIVITY_DATA, item);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

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
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("TableName");
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("请输入企业名称");
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        getPresenter().searchUserDetil("", type);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().searchUserDetil(query, type);
                viewEmpty.setVisibility(View.GONE);
                return true;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    getPresenter().searchUserDetil(newText, type);
                }
                return true;
            }
        });
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
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
        manualRefresh();
    }

    @Override
    public void onLoadmore() {
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
    public void searchComplete(List<CommonSupervisionQyBean.DataListBean> datas) {
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
    public void loadMore(List<CommonSupervisionQyBean.DataListBean> datas) {
        if (datas == null) {
            Toast.makeText(this, "没有数据了", Toast.LENGTH_SHORT).show();
        } else {
            adapter.addData(datas);
        }
        springView.onFinishFreshAndLoad();
    }
}
