package com.wyw.jiangsu.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.wyw.jiangsu.utils.FileUtil.getFalv;

/**
 * Created by wyw on 2018/1/17.
 */

public class FalvDialog extends Dialog implements View.OnClickListener {

    private String fileName;
    private String filePath;
    private Context mContext;
    private TextView tvYulan, tvDayin, tvTitle;

    public FalvDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    private OnItemOpen onItemOpen;

    public interface OnItemOpen {
        void onItemOpen(String path);
    }

    public void setOnItemOpen(OnItemOpen onItemOpen) {
        this.onItemOpen = onItemOpen;
    }

    public FalvDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public FalvDialog(Context context, String fileName, String filePath) {
        super(context);
        this.mContext = context;
        this.fileName = fileName;
        this.filePath = filePath;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        File file = new File(getFalv().getPath() + "/" + fileName);
        if (file.exists()){
            tvYulan.setText("打开已下载文件");
        }else{
            tvYulan.setText("下载预览");
        }
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.falv_dialog_layout, null);
        setContentView(view);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvYulan = (TextView) view.findViewById(R.id.tv_yulan);
        tvDayin = (TextView) view.findViewById(R.id.tv_dayin);
        tvTitle.setText(fileName);
        tvYulan.setOnClickListener(this);
        tvDayin.setOnClickListener(this);
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) mContext).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER);//设置对话框位置
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_yulan:
                downLoad();
                dismiss();
                break;
            case R.id.tv_dayin:
                choose();
                dismiss();
                break;
            default:
                break;
        }
    }

    private void choose() {
        File[] files = getFalv().listFiles();
        int length = files.length;
        if (length == 0) {
            Toast.makeText(mContext, "请先下载", Toast.LENGTH_LONG).show();
        } else {
            for (File file : getFalv().listFiles()) {
                if (file.getName().contains(fileName)) {
                    new AlertDialog.Builder(mContext).setTitle("文件目录")
                            .setMessage("此文件在手机《江苏法律法规》这个文件里")
                            .setPositiveButton("确定", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    if (onItemOpen != null) {
//                                        onItemOpen.onItemOpen(getFalv().getAbsolutePath());
//                                    }
                                    dismiss();
                                }
                            }).show();
                    return;
                }
            }
            Toast.makeText(mContext, "请先下载", Toast.LENGTH_LONG).show();
        }
    }

    private void downLoad() {
        File[] files = getFalv().listFiles();
        int length = files.length;
        if (length == 0) {
            new Thread(new DownLoadThread(filePath, fileName)).start();
        } else {
            for (File file : getFalv().listFiles()) {
                if (file.getName().contains(fileName)) {
                    OtherUtil.openFile(file);
                    return;
                }
            }
            Log.i("tag", "我又开始下载了");
            new Thread(new DownLoadThread(filePath, fileName)).start();
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
                    File file = new File(getFalv().getAbsolutePath() + "/" + name);
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        is = response.body().byteStream();
                        fos = new FileOutputStream(getFalv().getAbsolutePath() + "/" + name);
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
}
