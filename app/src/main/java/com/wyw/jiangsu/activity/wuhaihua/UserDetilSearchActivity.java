package com.wyw.jiangsu.activity.wuhaihua;

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
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.HarmlessUserDetilAdapter;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IUserDetilSearchActivity;
import com.wyw.jiangsu.presenter.UserDetilSearchActivityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserDetilSearchActivity extends MVPActivity<UserDetilSearchActivityPresenter> implements IUserDetilSearchActivity, SpringView.OnFreshListener {
    @BindView(R.id.search_background)
    View searchBackground;
    @BindView(R.id.search_view)
    SearchView searchView;
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

    HarmlessUserDetilAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detil_search);
    }

    @Override
    protected UserDetilSearchActivityPresenter createPresenter() {
        return new UserDetilSearchActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setupSearvhView();
        adapter = new HarmlessUserDetilAdapter(new ArrayList<>(), item -> {
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
        getPresenter().refresh();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setupSearvhView() {
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("养殖场（户）名称");
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().searchUserDetil(query);
                viewEmpty.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    getPresenter().searchUserDetil(newText);
                }
                return true;
            }
        });
//        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus && TextUtils.isEmpty(searchView.getQuery())) {
//                getPresenter().searchUserDetil("");
//            }
//        });
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        getPresenter().refresh();
    }

    @Override
    public void searchComplete(List<UserDetilBean.Data> datas) {
        if (datas.size() == 0) {
            viewEmpty.setVisibility(View.VISIBLE);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(datas);
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<UserDetilBean.Data> datas) {
        if (datas == null) {
            Toast.makeText(this, "没有数据了", Toast.LENGTH_SHORT).show();
        } else {
            adapter.addData(datas);
        }
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
