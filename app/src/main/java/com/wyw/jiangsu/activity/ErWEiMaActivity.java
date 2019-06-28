package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/28.
 */
public class ErWEiMaActivity extends MVPActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erweima_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("兽药基本信息");
        Intent intent = getIntent();
        String code = intent.getStringExtra("Code");

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);//启动JS脚本
        webview.loadUrl("http://36.111.192.50:8888/PT/SY/SyJyz_Query.aspx?code=" + code);
    }

    @Override
    public void bindListener() {

    }

}
