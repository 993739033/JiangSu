package com.wyw.jiangsu.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.interfac.IActivity;
import com.wyw.jiangsu.permisson.InvokeListener;
import com.wyw.jiangsu.permisson.InvokeParam;
import com.wyw.jiangsu.permisson.Permission;
import com.wyw.jiangsu.permisson.PermissionImp;
import com.wyw.jiangsu.permisson.PermissionInvocationHandler;
import com.wyw.jiangsu.permisson.PermissionManager;
import com.wyw.jiangsu.permisson.TContextWrap;
import com.wyw.jiangsu.permisson.TResult;
import com.wyw.jiangsu.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wyw on 2016/10/31.
 */

public abstract class BaseActivity extends FragmentActivity implements IActivity, InvokeListener, Permission.TakeResultListener {

    protected ImageView back, add;
    private TextView tvTitle;
    private Unbinder bind;
    private ProgressDialog dialog;
    private InvokeParam invokeParam;
    private Permission permission;

    public ImageView getBack() {
        return back;
    }

    public ImageView getAdd() {
        return add;
    }

    public TextView getTitltView() {
        return tvTitle;
    }

    public ProgressDialog getDialog() {
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    public void setContentView(int layoutResID, Bundle savedInstanceState) {
//        super.setContentView(layoutResID);
//        bind = ButterKnife.bind(this);
//        setStatusBarColor();
//        initBase();
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("加载中");
//        beforeBindData(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //设置代理
//            PermissionInvocationHandler.of(this).bind(new PermissionImp());
//        } else {
//            bindData();
//            bindListener();
//        }
//    }

//    protected void beforeBindData(Bundle savedInstanceState) {
//    }

    protected String getString(View view) {
        if (view instanceof EditText) return ((TextView) view).getText().toString();
        if (view instanceof Spinner)
            return ((Spinner) view).getSelectedItem().toString();
        if (view instanceof TextView) return ((TextView) view).getText().toString();
        return view.toString();
    }


    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 如果是空 则返回false
     *
     * @param text
     * @return
     */
    protected boolean isEmpty(String text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        return false;
    }

    protected boolean isEmpty(TextView tv) {
        return isEmpty(tv.getText().toString());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bind = ButterKnife.bind(this);
        setStatusBarColor();
        initBase();
        dialog = new ProgressDialog(this);
        dialog.setMessage("加载中");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //申请权限
//            requestPermission();
//        } else {
        bindData();

        bindListener();
//        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void requestPermission() {
        //默认申请全部权限
//        applyAllPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void applyAllPermission() {
        getPermissionHandler().applyAllPermission();
    }

    /**
     * 申请定位权限
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void applyLocationPermission() {
        getPermissionHandler().applyLocationPermission();
    }

    /**
     * 申请sd卡权限
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void applyStoragePermission() {
        getPermissionHandler().applyStoragePermission();
    }

    /**
     * 申请照相权限
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void applyCameraPermission() {
        getPermissionHandler().applyCameraPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Permission getPermissionHandler() {
        if (permission == null)
            permission = PermissionInvocationHandler.of(this).bind(new PermissionImp());
        return permission;
    }


    protected void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 23) {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.titleBarBg, getTheme()));
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.titleBarBg));
        }
    }

    protected void initBase() {
        try {
            findBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            findTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            findAdd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void findAdd() throws Exception {
        add = (ImageView) findViewById(R.id.bt_add);
    }

    ;

    protected void setTitle(String title) {
        try {
            if (tvTitle != null) {
                tvTitle.setText(title);
            }
        } catch (Exception e) {

        }
    }

    private void findTitle() throws Exception {
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    private void findBack() throws Exception {
        back = (ImageView) findViewById(R.id.bt_back);
        if (back != null) {
            back.setOnClickListener((view) -> finish());
        }
//        RxView.clicks(back).throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(aVoid -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        //删除文件
//        PhotoUtils.deletePic();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    protected void hideBack() {
        if (back != null) {
            back.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PermissionManager.TPermissionType invoke(InvokeParam param) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), param.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = param;
        }
        return type;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void takeSuccess(TResult result) {
        bindData();
        bindListener();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.e("BaseActivity", "权限申请失败");
    }

    @Override
    public void takeCancel() {

    }
}
