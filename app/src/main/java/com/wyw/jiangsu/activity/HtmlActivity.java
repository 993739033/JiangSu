package com.wyw.jiangsu.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.TongzhiGGBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.OtherUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.KeyEvent.KEYCODE_BACK;
import static com.wyw.jiangsu.utils.FileUtil.getGonggao;

/**
 * Created by wyw on 2018/1/18.
 */

public class HtmlActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webview;
    ProgressDialog dialog;

    private TongzhiGGBean.DataListBean listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.html_activity);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void bindData() {
        listBean = (TongzhiGGBean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        String ficontent = listBean.getFicontent();
        String content = new String(Base64.decode(ficontent, Base64.DEFAULT));
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.requestFocus();
        //为WebView设置WebViewClient处理某些操作
        webview.setWebViewClient(new webViewClient());
        webview.addJavascriptInterface(this, "android");
        webview.setDownloadListener(new MyWebViewDownLoadListener());
        //其他细节操作
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        settings.setAllowFileAccess(true); //设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("UTF-8");
        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 启用javascript
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //设置WebChromeClient类
        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress > 99) {
                    // 加载完毕
                    close();
                } else {
                    // 加载中
                    openDialog(newProgress);
                }
            }

            private void openDialog(int newProgress) {
                if (dialog == null) {
                    dialog = new ProgressDialog(HtmlActivity.this);
                    dialog.setMessage("正在加载页面，请稍候....");
                    dialog.setProgressStyle(TRIM_MEMORY_COMPLETE);
                    dialog.setProgress(newProgress);
                    dialog.show();
                } else {
                    dialog.setProgress(newProgress);
                }
            }

            private void close() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
        webview.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    public class webViewClient extends WebViewClient {

        // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        // 重写此方法可以让webview处理https请求
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            String[] split = url.split("src=");
            if (split.length == 1) return;
            Log.i("tag", split[1]);
            File[] files = getGonggao().listFiles();
            int length = files.length;
            if (length == 0) {
                new Thread(new DownLoadThread(url, split[1])).start();
            } else {
                for (File file : getGonggao().listFiles()) {
                    if (file.getName().contains(split[1])) {
                        OtherUtil.openFile(file);
                        return;
                    }
                }
                new Thread(new DownLoadThread(url, split[1])).start();
            }
        }
    }

    private class DownLoadThread implements Runnable {
        private String TAG = "tag";
        private String name;

        private String dUrl;

        public DownLoadThread(String dUrl, String name) {
            this.dUrl = dUrl;
            this.name = name;
        }

        @Override
        public void run() {
            //创建okHttpClient对象
            OkHttpClient mOkHttpClient = new OkHttpClient();
            //创建一个Request
            Request request = new Request.Builder().url(dUrl).build();
            //new call
            Call call = mOkHttpClient.newCall(request);
            //请求加入调度
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HtmlActivity.this, "正在下载", Toast.LENGTH_SHORT).show();
                        }
                    });
                    File file = new File(getGonggao().getAbsolutePath() + "/" + name);
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        is = response.body().byteStream();
                        fos = new FileOutputStream(getGonggao().getAbsolutePath() + "/" + name);
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.flush();
                        OtherUtil.openFile(file);
                    } catch (IOException e) {
                        Log.e(TAG, e.toString());
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                            }
                            if (fos != null) {
                                fos.close();
                            }
                        } catch (IOException e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                }
            });
        }
    }


    /**
     * js调用此方法. 并且将参数传递过来
     *
     * @param url js  传递过来的参数
     */
    @android.webkit.JavascriptInterface
    public void actionFromJsWithParam(final String url) {

    }

    @Override
    public void bindListener() {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (webview != null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }
}
