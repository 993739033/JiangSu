package com.wyw.jiangsu.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wyw.jiangsu.permisson.InvokeListener;
import com.wyw.jiangsu.permisson.InvokeParam;
import com.wyw.jiangsu.permisson.Permission;
import com.wyw.jiangsu.permisson.PermissionImp;
import com.wyw.jiangsu.permisson.PermissionInvocationHandler;
import com.wyw.jiangsu.permisson.PermissionManager;
import com.wyw.jiangsu.permisson.TContextWrap;
import com.wyw.jiangsu.permisson.TResult;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wyw on 2016/7/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, Permission.TakeResultListener, InvokeListener {
    protected DisplayMetrics metrics;
    private Unbinder bind;
    private ProgressDialog dialog;
    private InvokeParam invokeParam;
    private Permission permission;

    protected boolean isVisible;
    public BaseFragment() {
        setRetainInstance(true);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        }else{
            isVisible =false;
            onInvisible();
        }
    }

    protected abstract void onInvisible();

    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        dialog = new ProgressDialog(context);
        dialog.setMessage("加载中");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getResId(), container, false);
    }

    protected abstract int getResId();

    @Override
    public void onClick(View view) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null) {
            bind.unbind();
        }
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        bindData();
//        bindListener();
//    }

    //此时bind.unbind()抛出空指针异常
/*    @Override
    public void onDetach() {
        super.onDetach();
        if (bind != null)
            bind.unbind();
    }*/

    protected abstract void bindListener();

    protected abstract void bindData();

    public ProgressDialog getDialog() {
        return dialog;
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void requestPermission() {
        //默认申请全部权限
        applyAllPermission();
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
        if (permission == null) permission = PermissionInvocationHandler.of(this).bind(new PermissionImp());
        return permission;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PermissionManager.TPermissionType invoke(InvokeParam param) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this),param.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = param;
        }
        return type;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type= PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(getActivity(),type,invokeParam,this);
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
