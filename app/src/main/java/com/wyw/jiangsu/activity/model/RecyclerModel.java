package com.wyw.jiangsu.activity.model;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.BaseMsg;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.List;

/**
 * Created by wyw on 2017/2/28.
 */
interface RecyclerListener<T extends BaseMsg> {
    //网络请求数据的返回 同步刷新
    void refresh(List<T> t);

    //网路请求数据返回 同步加载更多
    void loadmore(List<T> t);

    //禁止刷新 下拉
    void forbidRefreshAndLoadMore();

    //获取集合数据
    List<T> getList();

    //回到最开始
    void turnToStart();
}

public class RecyclerModel<T extends BaseMsg> implements SpringView.OnFreshListener, RecyclerListener<T> {
    private SpringViewListener listener;
    private Context context;
    private SpringView springView;
    private RecyclerView recyclerView;
    private RelativeLayout emptyLayout;

    //BaseQuickAdapter 对adapter的封装
    private BaseQuickAdapter<T> adapter;

    public RecyclerModel(ViewGroup container, SpringViewListener listener, BaseQuickAdapter<T> adapter) {
        this.context = container.getContext();
        this.listener = listener;
        this.adapter = adapter;
        initView(container);
    }

    private void initView(ViewGroup container) {
        //创建总布局
        container.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //创建Recyclerview
        recyclerView = new RecyclerView(new ContextThemeWrapper(context,R.style.ScrollbarRecyclerView));
        recyclerView.setLayoutParams(new SpringView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        //设置adapter
        recyclerView.setAdapter(adapter);
        //创建SpringView
        springView = new SpringView(context, null);
        springView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        springView.addView(recyclerView);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
//        springView.setHeader(new AliHeader(this,R.drawable.ali,true));   //参数为：logo图片资源，是否显示文字
//        springView.setFooter(new AliFooter(this,false));
        springView.setHeader(new RotationHeader(context));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(context));
        springView.setListener(this);
        springView.addView(recyclerView);

        //创建empty布局
        emptyLayout = new RelativeLayout(context);
        emptyLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        TextView tips = new TextView(context);
        tips.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tips.setId(R.id.tips);
        tips.setTextColor(Color.BLACK);
        tips.setTextSize(12);
        tips.setGravity(Gravity.CENTER);
        tips.setText("没有数据,点击刷新");

        Button btRefresh = new Button(context);
        RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        btParams.addRule(RelativeLayout.BELOW, R.id.tips);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        btParams.topMargin = (int) (10 * metrics.density);
        btRefresh.setLayoutParams(btParams);
        btRefresh.setTextColor(Color.BLACK);
        btRefresh.setTextSize(12);
        btRefresh.setText("重试");
        btRefresh.setGravity(Gravity.CENTER);
        btRefresh.setOnClickListener(v -> {
            manualRefresh();
        });
        emptyLayout.addView(tips);
        emptyLayout.addView(btRefresh);
        container.addView(springView);
        container.addView(emptyLayout);

        //默认刷新
        manualRefresh();
    }

    public void manualRefresh() {
        emptyLayout.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                springView.callFresh();
            }
        }, 500);
    }

    @Override
    public void onRefresh() {
        emptyLayout.setVisibility(View.GONE);
        if (listener != null) listener.refresh();
    }

    @Override
    public void onLoadmore() {
        emptyLayout.setVisibility(View.GONE);
        if (listener != null) listener.loadMore();
    }

    @Override
    public void refresh(List<T> t) {
        if (t != null && t.size() > 0) {
            adapter.setNewData(t);
        } else {
            emptyLayout.setVisibility(View.VISIBLE);
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadmore(List<T> t) {
        if (t != null && t.size() > 0) {
            adapter.addData(t);
        } else {
            emptyLayout.setVisibility(View.VISIBLE);
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void forbidRefreshAndLoadMore() {
        springView.setEnable(false);
    }

    @Override
    public List<T> getList() {
        return adapter.getData();
    }

    @Override
    public void turnToStart() {
        recyclerView.smoothScrollToPosition(0);
    }

}

