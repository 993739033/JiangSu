package com.wyw.jiangsu.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.roughike.bottombar.BottomBar;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.ViewPagerAdapter;
import com.wyw.jiangsu.bean.RegistryRecordBean;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.dialog.DownloadDialog;
import com.wyw.jiangsu.fragment.BaseFragment;
import com.wyw.jiangsu.fragment.HomeFragment;
import com.wyw.jiangsu.fragment.QueryFragment;
import com.wyw.jiangsu.fragment.SettingFragment;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IMainActivity;
import com.wyw.jiangsu.presenter.MainActivityPresenter;
import com.wyw.jiangsu.service.DemoIntentService;
import com.wyw.jiangsu.service.DemoPushService;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.service.downapk.DownService;
import com.wyw.jiangsu.utils.AppUtils;
import com.wyw.jiangsu.utils.OtherUtil;
import com.wyw.jiangsu.utils.SPUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends MVPActivity<MainActivityPresenter> implements IMainActivity, ViewPager.OnPageChangeListener {
    public static final int TASK_APK = 0;
    public static final int TASK_DB = 1;
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;
    private static final int PERMISSON_REQUESTCODE = 0;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @BindView(R.id.lin_container)
    LinearLayout linContainer;
    @BindView(R.id.bt_retry)
    Button btRetry;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvName;

    private int currentTaskType = -1;

    private DownloadDialog dialog;

    private List<BaseFragment> mTabs;
    private DownStatus downStatus;
    private long firstTime = 0;
    private SharedPreferences spf;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                RegistryRecordBean registryRecordBean = new RegistryRecordBean();
                registryRecordBean.setLuId(Integer.valueOf(getPresenter().getUserId()));
                registryRecordBean.setLIsOnline("2");
                registryRecordBean.setLLandDate(OtherUtil.getCurrentTime());
                registryRecordBean.setFLIP(OtherUtil.getPhoneIP());
                registryRecordBean.setDeviceId(Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
                getPresenter().uploadLoginInfo(registryRecordBean);

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(this);
    }

    @Override
    public void bindData() {
        // 注册 intentService 后 PushDemoReceiver 无效, sdk 会使用 DemoIntentService 传递数据,
        // AndroidManifest 对应保留一个即可(如果注册 DemoIntentService, 可以去掉 PushDemoReceiver, 如果注册了
        // IntentService, 必须在 AndroidManifest 中声明)
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        new Handler().postDelayed(() -> PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class), 1000);
        setTitle(getResources().getString(R.string.app_name));
        tvName.setVisibility(View.VISIBLE);
        tvName.setText(getPresenter().getUser().getFUNAME());
        hideBack();
//        getPresenter().loadPermission();
        dialog = new DownloadDialog.Builder(this)
                .OnClickListener((dialog1, which) -> {
//                    addTask(currentTaskType);
//                    dialog.show();
                })
                .setStyle(ProgressDialog.STYLE_HORIZONTAL)
                .setCanCancel(false)
                .setTitle("下载")
                .create();
//        getPresenter().checkApkVersion();
        initFragment();
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTabs));
        viewPager.setOffscreenPageLimit(3);
        rlEmpty.setVisibility(View.GONE);
        linContainer.setVisibility(View.VISIBLE);
//        getPresenter().loadPermission();
//        BottomBarTab tabOne = bottomBar.getTabWithId(R.id.id_indicator_one);
//        tabOne.setBadgeCount(5);

        Intent intent = new Intent(this, UploadPictureService.class);
        intent.setAction(UploadPictureService.CLEAR_DB);
        startService(intent);

        spf = this.getSharedPreferences("print", Context.MODE_PRIVATE);
        edit = spf.edit();
        int zt = ((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt();
        // 1代表是惠普打印机
        if (zt == 1) {
            edit.putInt("printer", 1);
        }
        //0代表是EPSON打印机
        if (zt == 0) {
            edit.putInt("printer", 0);
        }
        edit.commit();
    }

    /**
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onStart() {
        super.onStart();
        downStatus = new DownStatus();
        IntentFilter f = new IntentFilter();
        f.addAction(DownService.TASK_STARTDOWN);
        f.addAction(DownService.UPDATE_DOWNSTAUS);
        f.addAction(DownService.TASKS_CHANGED);
        f.addAction(DownService.TASKS_ERROR);
        f.addAction(DownService.UPDATE_COMPLETE);
        registerReceiver(downStatus, new IntentFilter(f));
    }


    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(downStatus);
    }

    private void initFragment() {
        mTabs = new ArrayList<>();
        mTabs.add(new HomeFragment());
        mTabs.add(new QueryFragment());
        mTabs.add(new SettingFragment());
    }

    @Override
    public void bindListener() {

        viewPager.addOnPageChangeListener(this);
        btRetry.setOnClickListener(v -> getPresenter().loadPermission());
        bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.id_indicator_one:
                    bottomBar.getTabAtPosition(0);
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.id_indicator_two:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.id_indicator_three:
                    viewPager.setCurrentItem(2);
                    break;
            }
        });
        bottomBar.setTabSelectionInterceptor((oldTabId, newTabId) -> {
            if (oldTabId == newTabId) return true;
            return false;
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        bottomBar.selectTabAtPosition(position, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void loadFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        rlEmpty.setVisibility(View.VISIBLE);
        linContainer.setVisibility(View.GONE);
    }

    @Override
    public void loadSuccess() {
        initFragment();
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTabs));
        viewPager.setOffscreenPageLimit(3);
        rlEmpty.setVisibility(View.GONE);
        linContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void recordSuccessful() {
        getPresenter().getSpUtils().removeKey(Constance.USER_OBJECT);
        finish();
    }

    @Override
    public void addTask(int type) {
        this.currentTaskType = type;
        Intent i = new Intent(DownService.ADD_DOWNTASK);
        i.setAction(DownService.ADD_DOWNTASK);
        i.putExtra("name", "JiangSu.apk");
        i.putExtra("path", Environment.getExternalStorageDirectory().getAbsolutePath());
        i.putExtra("url", "http://36.111.192.50:8888/JiangSuAPP/UserDatabase/JiangSu.apk");
//        i.putExtra("url", "http://36.111.192.50:8888/JiangSuAPPCS/UserDatabase/JiangSu.apk");
        i.setPackage(DownService.PACKAGE);
        startService(i);
        dialog.show();
    }


    private class DownStatus extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case DownService.UPDATE_DOWNSTAUS:
                    dialog.setProgress((int) intent.getIntExtra("completesize", 0));
                    dialog.setMax((int) intent.getIntExtra("totalsize", -1));
//                    adapter.notifyItem(intent.getLongExtra("completesize", 0), intent.getLongExtra("totalsize", -1));
                    break;
                case DownService.TASK_STARTDOWN:
                    dialog.setProgress((int) intent.getLongExtra("completesize", 0));
                    dialog.setMax((int) intent.getLongExtra("totalsize", -1));
//                    adapter.notifyItem(intent.getLongExtra("completesize", 0), intent.getLongExtra("totalsize", -1));
                    break;
                case DownService.TASKS_CHANGED:
//                    reload();
                    break;
                case DownService.UPDATE_COMPLETE:
                    dialog.dismiss();
                    String fileDir = intent.getStringExtra("fileDir");
                    String fileName = intent.getStringExtra("fileName");
                    if (fileName.endsWith(".apk")) {
                        //安装apk
                        AppUtils.installApk(context, fileDir + File.separator + fileName);
                    }
                    break;
                case DownService.TASKS_ERROR:
                    //需要自定义布局
//                    dialog.setRetryVisivible(View.VISIBLE);
                    dialog.dismiss();
                    Toast.makeText(context, "下载失败,请前往更多栏目中更新", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
}
