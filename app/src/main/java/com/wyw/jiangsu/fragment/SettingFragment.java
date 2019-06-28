package com.wyw.jiangsu.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.LoginActivity;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bean.RegistryRecordBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.dialog.DownloadDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHomeFragment;
import com.wyw.jiangsu.presenter.HomeFragmentPresenter;
import com.wyw.jiangsu.service.downapk.DownloadReceiver;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.HiddenAnimUtils;
import com.wyw.jiangsu.utils.OtherUtil;
import com.wyw.jiangsu.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by wyw on 2016/12/20.
 */

public class SettingFragment extends MVPFragment<HomeFragmentPresenter> implements IHomeFragment {
//    @BindView(R.id.bt_exit)
//    Button btExit;
    //    @BindView(R.id.bt_update_apk)
//    Button btUpdateApk;

    @BindView(R.id.layout_update_apk)
    View layout_update_apk;
    @BindView(R.id.layout_exit)
    View layout_exit;
    @BindView(R.id.layout_user_info)
    View layout_user_info;
    @BindView(R.id.layout_user_info_show)
    View layout_user_info_show;
    @BindView(R.id.layout_about_mnkj)
    View layout_about_mnkj;
    @BindView(R.id.layout_about_mnkj_show)
    View layout_about_mnkj_show;
    @BindView(R.id.RLayout_print_show)
    View RLayout_print_show;
    @BindView(R.id.layout_print)
    View layout_print;

    @BindView(R.id.tv_unit)
    TextView tv_unit;
    @BindView(R.id.tv_account)
    TextView tv_account;
    @BindView(R.id.tv_role)
    TextView tv_role;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.img_about_drop)
    ImageView img_about_drop;
    @BindView(R.id.img_user_drop)
    ImageView img_user_drop;
    @BindView(R.id.img_print)
    ImageView img_print;
    @BindView(R.id.RG_print)
    RadioGroup RG_print;

    @BindView(R.id.epsonPrint)
    RadioButton epsonPrint;
    @BindView(R.id.huipuRint)
    RadioButton huipuRint;
    @BindView(R.id.reMingPrint)
    RadioButton reMingPrint;
    @BindView(R.id.JQTEKreMingPrint)
    RadioButton JQTEKreMingPrint;

    private int mHeight1 = 0, mHeight2 = 0, mHeight3 = 0;

    Unbinder unbinder;
//    @BindView(R.id.bt_fl)
//    Button btFl;

    private DownloadReceiver downloadReceiver;
    private IntentFilter intentFilter;

    private DownloadDialog downloadDialog;
    protected boolean isPrepared;

    private SharedPreferences spf;
    private SharedPreferences.Editor edit;


    @Override
    protected HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter(this);
    }


    @Override
    protected int getResId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        bindData();
        bindListener();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void bindListener() {
        layout_exit.setOnClickListener(v -> {
            RegistryRecordBean registryRecordBean = new RegistryRecordBean();
            registryRecordBean.setLuId(Integer.valueOf(getPresenter().getUserId()));
            registryRecordBean.setLIsOnline("2");
            registryRecordBean.setLLandDate(OtherUtil.getCurrentTime());
            registryRecordBean.setFLIP(OtherUtil.getPhoneIP());
            registryRecordBean.setDeviceId(Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID));
            getPresenter().uploadLoginInfo(registryRecordBean);

        });

        layout_update_apk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().updateApk();
            }
        });

        ViewGroup.LayoutParams layoutParams1 = layout_user_info.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layout_about_mnkj.getLayoutParams();
        ViewGroup.LayoutParams layoutParams3 = layout_print.getLayoutParams();
        if (mHeight1 == 0) {
            mHeight1 = layoutParams1.height;
        }
        if (mHeight2 == 0) {
            mHeight2 = layoutParams2.height;
        }
        if (mHeight3 == 0) {
            mHeight3 = layoutParams3.height;
        }

        layout_user_info_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!((boolean) img_user_drop.getTag())) {
                    img_user_drop.setTag(true);
                    img_user_drop.setBackgroundResource(R.drawable.setting_pack_up);
                } else {
                    img_user_drop.setTag(false);
                    img_user_drop.setBackgroundResource(R.drawable.setting_drop_down);
                }

                HiddenAnimUtils utils = HiddenAnimUtils.newInstance(getContext(), layout_user_info, null, mHeight1);
                utils.setCallBack(new HiddenAnimUtils.refreshCallBack() {
                    @Override
                    public void refresh() {
                        onCreate(null);
                    }
                });
                utils.toggle();

            }
        });

        layout_about_mnkj_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!((boolean) img_about_drop.getTag())) {
                    img_about_drop.setTag(true);
                    img_about_drop.setBackgroundResource(R.drawable.setting_pack_up);
                } else {
                    img_about_drop.setTag(false);
                    img_about_drop.setBackgroundResource(R.drawable.setting_drop_down);
                }
                HiddenAnimUtils.newInstance(getContext(), layout_about_mnkj, null, mHeight2).toggle();


            }
        });
        RLayout_print_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!((boolean) img_print.getTag())) {
                    img_print.setTag(true);
                    img_print.setBackgroundResource(R.drawable.setting_pack_up);
                } else {
                    img_print.setTag(false);
                    img_print.setBackgroundResource(R.drawable.setting_drop_down);
                }
                HiddenAnimUtils.newInstance(getContext(), layout_print, null, mHeight3).toggle();
            }
        });


        int zt = ((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt();
        if (zt == 1) {
            huipuRint.setVisibility(View.VISIBLE);
            huipuRint.setChecked(true);
        }
        if (zt == 0) {
            epsonPrint.setChecked(true);
        }
        if (zt == 2) {
            reMingPrint.setChecked(true);
        }
        if (zt == 3) {
            JQTEKreMingPrint.setChecked(true);
        }


        spf = getActivity().getSharedPreferences("print", MODE_PRIVATE);
        edit = spf.edit();
        RG_print.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                User user = (User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT);
                switch (i) {
                    case R.id.epsonPrint:
                        user.setZt(0);
                        edit.putInt("printer", 0);
                        edit.commit();
                        break;
                    case R.id.huipuRint:
                        user.setZt(1);
                        edit.putInt("printer", 1);
                        edit.commit();
                        break;
                    case R.id.reMingPrint:
                        user.setZt(2);
                        edit.putInt("printer", 2);
                        edit.commit();
                        break;
                    case R.id.JQTEKreMingPrint:
                        user.setZt(3);
                        edit.putInt("printer", 3);
                        edit.commit();
                        break;

                }

                SPUtils.getInstance().saveObjectData(Constance.USER_OBJECT, user);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        downloadReceiver = new DownloadReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadReceiver.Action);
        getActivity().registerReceiver(downloadReceiver, intentFilter);

    }

    @Override
    protected void bindData() {
        downloadDialog = new DownloadDialog.Builder(getContext()).setTitle("下载apk")
                .setMessage("下载中").setCanCancel(false).create();
        tv_unit.setText(getPresenter().getUser().getFSENTERPRISENAME());//单位
        tv_account.setText(getPresenter().getUser().getFUACCOUNT());//账户
        tv_role.setText(getPresenter().getUser().getFRNAME());//角色
        tv_name.setText(getPresenter().getUser().getFUNAME());//姓名
        String sex = getPresenter().getUser().getFUSEX() ? "女" : "男";
        tv_sex.setText(sex + "");//性别
        tv_type.setText(getPresenter().getUser().getFUQUANXIAN());//类别
        tv_phone.setText(getPresenter().getUser().getFUPHONE());//电话

        img_about_drop.setTag(false);
        img_user_drop.setTag(false);
        img_print.setTag(false);

    }

    @Override
    public void onClick(View view) {
    }


    @Override
    public void setDownDialogProgress(long bytesRead, long contentLength, boolean done) {
        downloadDialog.setProgress((int) (bytesRead / 1024));
        downloadDialog.setMax((int) (contentLength / 1024));
        if (done) {
            downloadDialog.dismiss();
        }
    }


    @Override
    public void showApkDialog() {
        new AlertDialog.Builder(getContext()).setTitle("下载apk")
                .setMessage("是否下载apk")
                .setCancelable(false)
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().startDownApk();
                        //显示进度栏
                        SettingFragment.this.downloadDialog.show();
                    }
                }).setPositiveButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SettingFragment.this.showToast("已取消更新");
            }
        }).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //getActivity().unregisterReceiver(downloadReceiver);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(downloadReceiver);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadDone(int type) {
        Intent intent = new Intent();
        intent.setAction(DownloadReceiver.Action);
        switch (type) {
            case Constance.TYPE_DB_DONE:
                intent.putExtra(DownloadReceiver.DOWNLOAD, DownloadReceiver.DOWNLOAD_DB);
                intent.putExtra(DownloadReceiver.PATH, FileUtil.getInstance().getDbFile().getAbsolutePath());
                break;
            case Constance.TYPE_APK_DONE:
                intent.putExtra(DownloadReceiver.DOWNLOAD, DownloadReceiver.DOWNLAOD_APK);
                intent.putExtra(DownloadReceiver.PATH, FileUtil.getInstance().getApkFile().getAbsolutePath());
                break;
        }
        getContext().sendBroadcast(intent);
    }


    @Override
    public void showDbDialog(String version) {
        new AlertDialog.Builder(getContext()).setTitle("下载数据库")
                .setMessage("是否下载")
                .setCancelable(false)
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //开始下载数据库
                        getPresenter().startDownDb(version);
                        SettingFragment.this.downloadDialog.show();
                    }
                }).show();

    }

    @Override
    public void recordSuccessful() {
        getPresenter().getSpUtils().removeKey(Constance.USER_OBJECT);
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void refreshComplete(List<HomeBean.DataList> dataLists) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {

    }

    @Override
    public void onActivityResult(RefreshBus bean) {

    }

}