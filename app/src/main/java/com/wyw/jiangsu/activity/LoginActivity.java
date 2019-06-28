package com.wyw.jiangsu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.RegistryRecordBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ILoginActivity;
import com.wyw.jiangsu.presenter.LoginActivityPresenter;
import com.wyw.jiangsu.utils.AppUtils;
import com.wyw.jiangsu.utils.OtherUtil;
import com.wyw.jiangsu.utils.SPUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wyw on 2016/10/31.
 */

public class LoginActivity extends MVPActivity<LoginActivityPresenter> implements ILoginActivity {

    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.bt_registe)
    Button btRegiste;
    //    @BindView(R.id.tv_subtitle)
//    TextView tvSubtitle;
    public static int REQURES_CODE = 1000;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    //将权限用数组装起来，当loginactivity类显示的时候，检查必要的这些权限
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    private static final int PERMISSON_REQUESTCODE = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        setContentView(R.layout.activity_login);
        return new LoginActivityPresenter(this);
    }


    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
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
        List<String> needRequestPermissonList = new ArrayList<>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }


    @Override
    public void bindData() {
//        setTitle("登录");
//        getBack().setVisibility(View.GONE);
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkIterface : list) {
                ArrayList<InetAddress> list1 = Collections.list(networkIterface.getInetAddresses());
                for (InetAddress inetAddress : list1) {
                    inetAddress.isLoopbackAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        //账号密码版本号的回显
        etUserName.setText(SPUtils.getInstance().getData(Constance.USER_NAME, "", String.class));
        etPwd.setText(SPUtils.getInstance().getData(Constance.USER_PSW, "", String.class));
//        etUserName.setText("admin");
//        etPwd.setText("mnkj2016");
        tvVersion.setText("当前版本: " + AppUtils.getAppVersionName(this, getPackageName()));

        if (Build.VERSION.SDK_INT >= 23) {
            if (!MPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 100)) {
                MPermissions.requestPermissions(this, 100, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA});
//            MPermissions.requestPermissions(this, 100, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }

//        LocalRealmInstance.getInstance().queryProvinces();
//        LocalRealmInstance.getInstance().querySterilizeWay();
//        checkPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, 100, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(100)
    public void permissionSuccess() {
        Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(100)
    public void permissionFail() {
        Toast.makeText(this, "权限申请失败", Toast.LENGTH_SHORT).show();
    }

    //标准模板
//    private void checkPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE)
//                    != PackageManager.PERMISSION_DENIED) {
//                //告诉用户为什么需要这个权限
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//                } else {
//                    //申请授权 这个项目需要读写sdcard的权限
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//                }
//            } else {
//
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 100:
//                //如果权限拒绝 那么grantResults长度是0
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                    //获取权限
//                } else {
//
//                }
//                break;
//        }
//    }

    @Override
    public void bindListener() {
        btLogin.setOnClickListener(v -> {
//            startActivity(new Intent(LoginActivity.this, HarmlessConcentrateProcessActivity.class));

//            getPresenter().upload();
            if (TextUtils.isEmpty(etUserName.getText().toString())) {
                Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(etPwd.getText().toString())) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }
            getPresenter().login(etUserName.getText().toString(), etPwd.getText().toString());
        });
    }


    @Override
    public void recordSuccessful() {
        if (etPwd.getText().toString().equals("123456")) {
            Intent intent = new Intent(this, ModifyPwdActivity.class);
            intent.putExtra("name", etUserName.getText().toString());
            intent.putExtra("pwd", etPwd.getText().toString());
            startActivityForResult(intent, REQURES_CODE);
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQURES_CODE) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loginSuccessful() {
//        startActivity(new Intent(this, TestRecyclerViewActivity.class));
        RegistryRecordBean registryRecordBean = new RegistryRecordBean();
        registryRecordBean.setLuId(Integer.valueOf(getPresenter().getUserId()));
        registryRecordBean.setLIsOnline("1");
        registryRecordBean.setLLandDate(OtherUtil.getCurrentTime());
        registryRecordBean.setFLIP(OtherUtil.getPhoneIP());
        registryRecordBean.setDeviceId(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        getPresenter().uploadLoginInfo(registryRecordBean);
        /*startActivity(new Intent(this, MainActivity.class));
        finish();*/
    }

    @Override
    public void loginSuccessful(int uid, String uname, String phone) {
        if (etPwd.getText().toString().equals("123456")) {
            Intent intent = new Intent(this, ModifyPwdActivity.class);
            intent.putExtra("name", etUserName.getText().toString());
            intent.putExtra("pwd", etPwd.getText().toString());
            intent.putExtra("uid", uid);
            intent.putExtra("username", uname);
            intent.putExtra("phone", phone);
            startActivityForResult(intent, REQURES_CODE);
        } else {
            RegistryRecordBean registryRecordBean = new RegistryRecordBean();
            registryRecordBean.setLuId(Integer.valueOf(getPresenter().getUserId()));
            registryRecordBean.setLIsOnline("1");
            registryRecordBean.setLLandDate(OtherUtil.getCurrentTime());
            registryRecordBean.setFLIP(OtherUtil.getPhoneIP());
            registryRecordBean.setDeviceId(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
            getPresenter().uploadLoginInfo(registryRecordBean);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void regsite(View view) {
//        startActivity(new Intent(this, RegisteActivity.class));
        //startActivity(new Intent(this, UserRegisteActivity.class));
    }
}
