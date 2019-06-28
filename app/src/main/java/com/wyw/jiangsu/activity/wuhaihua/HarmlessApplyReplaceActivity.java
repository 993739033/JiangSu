package com.wyw.jiangsu.activity.wuhaihua;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.LocationActity;
import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.bean.upload.UploadReplaceApplyBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.ErBiaoDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessReplaceApplyActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.HarmlessReplaceApplyActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.compressBmpToFile;
import static com.wyw.jiangsu.utils.PhotoUtils.decodeSampleBitmapFromFile;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;

/**
 * 监管兽医代申报
 * 下一个页面 病死动物收集点人员信息接收  {@link HarmlessMessageAcceptCollectionListActivity}
 */

public class HarmlessApplyReplaceActivity extends LocationActity<HarmlessReplaceApplyActivityPresenter>
        implements IHarmlessReplaceApplyActivity, ITime, PhotoViewModel.OnClickListener, View.OnClickListener {


    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_type)
    EditText tvType;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_idCrad)
    EditText tvIdCrad;
    @BindView(R.id.tv_icCrad)
    EditText tvIcCrad;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.img_takephoto_ic_id)
    ImageView imgTakephotoIcId;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewGroupPhoto;
    @BindView(R.id.spinner_deadth_type)
    EditText spinnerDeadthType;
    @BindView(R.id.et_deadth_number)
    EditText etDeadthNumber;
    @BindView(R.id.et_insured_number)
    EditText etInsuredNumber;
    @BindView(R.id.tl_anim)
    TableLayout tlAnim;
    @BindView(R.id.bt_add_item)
    Button btAddItem;
    @BindView(R.id.img_takephoto_baodan)
    ImageView imgTakephotoBaodan;
    @BindView(R.id.photo_view_baodan)
    ImageView photoViewBaodan;
    @BindView(R.id.et_dead_erbiao)
    EditText etDeadErbiao;
    @BindView(R.id.bt_erbiao)
    Button btErbiao;
    @BindView(R.id.img_takephoto_xuqin1)
    ImageView imgTakephotoXuqin1;
    @BindView(R.id.img_takephoto_xuqin2)
    ImageView imgTakephotoXuqin2;
    @BindView(R.id.photo_view_xuqin1)
    ImageView photoViewXuqin1;
    @BindView(R.id.photo_view_xuqin2)
    ImageView photoViewXuqin2;
    @BindView(R.id.img_takephoto_xuqin3)
    ImageView imgTakephotoXuqin3;
    @BindView(R.id.img_takephoto_xuqin4)
    ImageView imgTakephotoXuqin4;
    @BindView(R.id.photo_view_xuqin3)
    ImageView photoViewXuqin3;
    @BindView(R.id.photo_view_xuqin4)
    ImageView photoViewXuqin4;
    @BindView(R.id.img_takephoto_xuzhu1)
    ImageView imgTakephotoXuzhu1;
    @BindView(R.id.img_takephoto_xuzhu2)
    ImageView imgTakephotoXuzhu2;
    @BindView(R.id.photo_view_xuzhu1)
    ImageView photoViewXuzhu1;
    @BindView(R.id.photo_view_xuzhu2)
    ImageView photoViewXuzhu2;
    @BindView(R.id.img_takephoto_xuzhu3)
    ImageView imgTakephotoXuzhu3;
    @BindView(R.id.img_takephoto_xuzhu4)
    ImageView imgTakephotoXuzhu4;
    @BindView(R.id.photo_view_xuzhu3)
    ImageView photoViewXuzhu3;
    @BindView(R.id.photo_view_xuzhu4)
    ImageView photoViewXuzhu4;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.spinner_deadth_reason)
    TextView spinnerDeadthReason;
    @BindView(R.id.spinner_process_method1)
    TextView spinnerProcessMethod1;
    @BindView(R.id.spinner_process_method2)
    TextView spinnerProcessMethod2;
    @BindView(R.id.spinner_collection_location)
    TextView spinnerCollectionLocation;
    @BindView(R.id.lin_collection_location)
    RelativeLayout linCollectionLocation;
    @BindView(R.id.tv_evoke_time)
    TextView tvEvokeTime;
    @BindView(R.id.lin_evoke_time)
    RelativeLayout linEvokeTime;
    @BindView(R.id.img_takephoto_keep_file1)
    ImageView imgTakephotoKeepFile1;
    @BindView(R.id.img_takephoto_keep_file2)
    ImageView imgTakephotoKeepFile2;
    @BindView(R.id.photo_view_keep_file1)
    ImageView photoViewKeepFile1;
    @BindView(R.id.photo_view_keep_file2)
    ImageView photoViewKeepFile2;
    @BindView(R.id.img_takephoto_keep_file3)
    ImageView imgTakephotoKeepFile3;
    @BindView(R.id.img_takephoto_keep_file4)
    ImageView imgTakephotoKeepFile4;
    @BindView(R.id.photo_view_keep_file3)
    ImageView photoViewKeepFile3;
    @BindView(R.id.photo_view_keep_file4)
    ImageView photoViewKeepFile4;
    @BindView(R.id.lin_keep_file)
    LinearLayout linKeepFile;
    @BindView(R.id.bt_shouyiqianming)
    Button btShouyiqianming;
    @BindView(R.id.img_principal_signature)
    ImageView imgPrincipalSignature;
    @BindView(R.id.bt_guanfangshouyi)
    Button btGuanfangshouyi;
    @BindView(R.id.img_guanfangshouyi)
    ImageView imgGuanfangshouyi;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.tv_name)
    EditText tvName;
    private PhotoViewModel ImageViewModel;
    private String currentPhotoFile;
    AnimTypeModel model;

    TimePresenter timePresenter;
    @BindView(R.id.tv_row_anim_detil_bottom)
    TextView tvRowAnimDetilBottom;
    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.iv_none)
    ImageView iv_none;
    @BindView(R.id.iv_none_1)
    ImageView iv_none_1;
    @BindView(R.id.iv_none_2)
    ImageView iv_none_2;
    @BindView(R.id.iv_none_3)
    ImageView iv_none_3;
    @BindView(R.id.iv_none_4)
    ImageView iv_none_4;
    @BindView(R.id.iv_none_5)
    ImageView iv_none_5;
    @BindView(R.id.iv_none_6)
    ImageView iv_none_6;
    @BindView(R.id.iv_none_7)
    ImageView iv_none_7;
    @BindView(R.id.iv_none_8)
    ImageView iv_none_8;
    @BindView(R.id.iv_none_9)
    ImageView iv_none_9;
    @BindView(R.id.iv_none_10)
    ImageView iv_none_10;
    @BindView(R.id.iv_none_11)
    ImageView iv_none_11;
    @BindView(R.id.iv_none_12)
    ImageView iv_none_12;
    @BindView(R.id.iv_none_13)
    ImageView iv_none_13;


    List<CollectionListBean.DataListBean> dataListBean;
    ArrayList<String> erBiaoAnimTypeList;//必须输入耳标的动物种类
    UploadReplaceApplyBean bean;

    private String uuid;
    private UserDetilBean.Data data1;
    private boolean isDestoryed;
    private long currentProcessPositoin1 = 0;
    private long currentProcessPositoin2 = 0;
    private String fstid;
    private boolean isFarmInfoIntent;
    private String sws;
    private String cbs;
    private String animtype;
    private int index=0;

    private DataSelectDialog deadthReasonDialog;
    private DataSelectDialog processMethod1Dialog;
    private DataSelectDialog processMethod2Dialog;
    private DataSelectDialog collectionLocationDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_replace_apply);
        ButterKnife.bind(this);
        Log.e("HarmlessApplyReplaceAct", "onCreate");
        uuid = UUID.randomUUID().toString();
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        Log.e("isDestoryed", "" + "isDestoryed oncreate " + isDestoryed);
        getPresenter().getAnimType();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("HarmlessApplyReplaceAct", "onResume");
        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
            if (file.getName().contains(uuid + "_A15")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgPrincipalSignature);
            } else if (file.getName().contains(uuid + "_A16")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgGuanfangshouyi);
            }
        }
        String nameManager1 = uuid + "_A1", nameManager2 = uuid + "_A2",
                nameManager3 = uuid + "_A3", nameManager4 = uuid + "_A4",
                nameManager5 = uuid + "_A5", nameManager6 = uuid + "_A6",
                nameManager7 = uuid + "_A7", nameManager8 = uuid + "_A8",
                nameManager9 = uuid + "_A9", nameManager10 = uuid + "_A10",
                nameManager11 = uuid + "_A11", nameManager12 = uuid + "_A12",
                nameManager13 = uuid + "_A13", nameManager14 = uuid + "_A14";
        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewGroupPhoto, REQUEST_CODE_START + 13, nameManager1, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewBaodan, REQUEST_CODE_START, nameManager2, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuqin1, REQUEST_CODE_START + 1, nameManager3, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuqin2, REQUEST_CODE_START + 2, nameManager4, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuqin3, REQUEST_CODE_START + 3, nameManager5, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuqin4, REQUEST_CODE_START + 4, nameManager6, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu1, REQUEST_CODE_START + 5, nameManager7, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu2, REQUEST_CODE_START + 6, nameManager8, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu3, REQUEST_CODE_START + 7, nameManager9, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu4, REQUEST_CODE_START + 8, nameManager10, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewKeepFile1, REQUEST_CODE_START + 9, nameManager11, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewKeepFile2, REQUEST_CODE_START + 10, nameManager12, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewKeepFile3, REQUEST_CODE_START + 11, nameManager13, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewKeepFile4, REQUEST_CODE_START + 12, nameManager14, getWuHaiHuaDir(), this);

        if (fstid != null) {
            getPresenter().getCollectionLocationList(data1.getFStId());
        }
        Log.e("isDestoryed", "" + "isDestoryed onresume " + isDestoryed);
        if (isDestoryed) {
            ArrayList<String> list = new ArrayList<>();
            list.add(animtype);
            model = new AnimTypeModel(this, list);
            model.add(animtype, sws, cbs, true);
            Log.e("AnimTypeModel", "onresume data.get(0).getAnimType() = " + animtype + "data.get(0).getDeadthNumber() = "
                    + sws + "data.get(0).getInsuredNumber() = " + cbs);
//            spinnerProcessMethod1.setSelection((int) currentProcessPositoin1);

//            spinnerProcessMethod1.performItemClick()
//            spinnerProcessMethod2.setSelection((int) currentProcessPositoin2);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("HarmlessApplyReplaceAct", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("HarmlessApplyReplaceAct", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("HarmlessApplyReplaceAct", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("HarmlessApplyReplaceAct", "onRestart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("HarmlessApplyReplaceAct", "onRestoreInstanceState");
        uuid = savedInstanceState.getString("uuid");
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
        fstid = savedInstanceState.getString("fstid");
        data1 = (UserDetilBean.Data) savedInstanceState.getSerializable("data1");
        Log.e("isDestoryed", "" + "isDestoryed onRestoreInstanceState " + isDestoryed);
        isDestoryed = true;
        Log.e("isDestoryed", "" + "isDestoryed onRestoreInstanceState " + isDestoryed);
        currentProcessPositoin2 = savedInstanceState.getLong("currentProcessPositoin2");
        currentProcessPositoin1 = savedInstanceState.getLong("currentProcessPositoin1");

        sws = savedInstanceState.getString("sws");
        cbs = savedInstanceState.getString("cbs");
        animtype = savedInstanceState.getString("animtype");
        Log.e("AnimTypeModel", "onRestoreInstanceState data.get(0).getAnimType() = " + animtype + "data.get(0).getDeadthNumber() = "
                + sws + "data.get(0).getInsuredNumber() = " + cbs);
        bean = savedInstanceState.getParcelable("bean");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("HarmlessApplyReplaceAct", "onSaveInstanceState");
        outState.putString("uuid", uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);


        outState.putString("sws", getSwsOrCbsFromLayout(1));
        outState.putString("cbs", getSwsOrCbsFromLayout(2));
        String animTypeFromLayout = getAnimTypeFromLayout();
        outState.putString("animtype", animTypeFromLayout);
        Log.e("AnimTypeModel", "onSaveInstanceState data.get(0).getAnimType() = " + getAnimTypeFromLayout() + "data.get(0).getDeadthNumber() = "
                + getSwsOrCbsFromLayout(1) + "data.get(0).getInsuredNumber() = " + getSwsOrCbsFromLayout(2));

        outState.putParcelable("bean", bean);
        outState.putLong("currentProcessPositoin2", currentProcessPositoin2);
        outState.putLong("currentProcessPositoin1", currentProcessPositoin1);
        if (data1 != null) {
            outState.putSerializable("data1", data1);
            outState.putString("fstid", data1.getFStId());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected HarmlessReplaceApplyActivityPresenter createPresenter() {
        return new HarmlessReplaceApplyActivityPresenter(this);
    }

    @Override
    public void bindData() {
        super.bindData();
        setTitle("代申报");
        bean = new UploadReplaceApplyBean();
        getAdd().setImageResource(android.R.drawable.ic_menu_search);
        getAdd().setVisibility(View.VISIBLE);
        //检查权限
        permissionCheck();
//        if (!isDestoryed) spinnerDeadthType.setSelection(2, true);

        //初始化数据选择弹窗
        initDialog();
    }

    private void initDialog(){
        //死亡原因选择弹窗
        deadthReasonDialog = new DataSelectDialog(this);
        deadthReasonDialog.setTitle("选择死亡原因");
        deadthReasonDialog.setDatas(getResources().getStringArray(R.array.deadth_reason));
        deadthReasonDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerDeadthReason.setText(data);
            }

            @Override
            public void cancel() {

            }
        });
        spinnerDeadthReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deadthReasonDialog.show();
            }
        });


        //处理方式1选择弹窗
        processMethod1Dialog = new DataSelectDialog(this);
        processMethod1Dialog.setTitle("选择处理方式");
        processMethod1Dialog.setDatas(getResources().getStringArray(R.array.process_method1));
        processMethod1Dialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerProcessMethod1.setText(data);
                switch (data) {
                    case "集中处理":
                        //集中处理
                        concentrationProcess();
                        break;
                    default:
                        //自行处理 特殊处理
                        concentrationProcessNot();
                        break;
                }
            }
            @Override
            public void cancel() {

            }
        });
        spinnerProcessMethod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processMethod1Dialog.show();
            }
        });

        //处理方式2选择弹窗
        processMethod2Dialog = new DataSelectDialog(this);
        processMethod2Dialog.setTitle("选择处理方式");
        processMethod2Dialog.setDatas(getResources().getStringArray(R.array.process_method2));
        processMethod2Dialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerProcessMethod2.setText(data);
            }
            @Override
            public void cancel() {

            }
        });
        spinnerProcessMethod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processMethod2Dialog.show();
            }
        });


        //收集点选择弹窗
        collectionLocationDialog = new DataSelectDialog(this);
        collectionLocationDialog.setTitle("选择收集点");
        collectionLocationDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerCollectionLocation.setText(data);
                index = collectionLocationDialog.getNowIndex();
            }
            @Override
            public void cancel() {

            }
        });
        spinnerCollectionLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionLocationDialog.show();
            }
        });

    }


    int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;

    private void permissionCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        }
    }

    @Override
    protected void invokeMethod(double latitude, double longitude) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showNormalDialog(String uuid) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessApplyReplaceActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getData(), uuid);
                        dialog.dismiss();
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    @Override
    public void bindListener() {
        imgTakephotoIcId.setOnClickListener(this);
        imgTakephotoBaodan.setOnClickListener(this);
        imgTakephotoXuqin1.setOnClickListener(this);
        imgTakephotoXuqin2.setOnClickListener(this);
        imgTakephotoXuqin3.setOnClickListener(this);
        imgTakephotoXuqin4.setOnClickListener(this);
        imgTakephotoXuzhu1.setOnClickListener(this);
        imgTakephotoXuzhu2.setOnClickListener(this);
        imgTakephotoXuzhu3.setOnClickListener(this);
        imgTakephotoXuzhu4.setOnClickListener(this);
        imgTakephotoKeepFile1.setOnClickListener(this);
        imgTakephotoKeepFile2.setOnClickListener(this);
        imgTakephotoKeepFile3.setOnClickListener(this);
        imgTakephotoKeepFile4.setOnClickListener(this);
        btShouyiqianming.setOnClickListener(this);
        btGuanfangshouyi.setOnClickListener(this);

        getAdd().setOnClickListener(v -> {
            startActivityForResult(new Intent(this, UserDetilSearchActivity.class), Constance.ACTIVITY_CODE);
        });
        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                Log.e("datad", getData().toString());
                showNormalDialog(uuid);
            }
        });

        //正常死亡 非正常死亡
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_yes:
                    spinnerDeadthReason.setVisibility(View.GONE);
                    break;
                case R.id.rb_no:
                    spinnerDeadthReason.setVisibility(View.VISIBLE);
                    break;
            }
        });
        btErbiao.setOnClickListener(v -> {
            //这里改了
            if (!TextUtils.isEmpty(model.getData().get(0).getDeadthNumber())) {
                new ErBiaoDialog(this, model.getData().get(0).getDeadthNumber(),
                        text -> etDeadErbiao.setText(text), false).show();
            } else {
                Toast.makeText(this, "请输入动物数量", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean check() {

        if (TextUtils.isEmpty(bean.getGlid())) {
            Toast.makeText(this, "缺少养殖户相关信息,请点击右上角", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!new File(getWuHaiHuaDir(), uuid + "_" + "A1.jpg").exists() || photoViewGroupPhoto.getDrawable() == null) {
            Toast.makeText(this, "请拍摄身份证和一卡通号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (photoViewXuqin1.getDrawable() == null && photoViewXuqin2.getDrawable() == null && photoViewXuqin3.getDrawable() == null && photoViewXuqin4.getDrawable() == null) {
            Toast.makeText(this, "缺少病死畜禽照片，至少一张", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (photoViewXuzhu1.getDrawable() == null && photoViewXuzhu2.getDrawable() == null && photoViewXuzhu3.getDrawable() == null && photoViewXuzhu4.getDrawable() == null) {
            Toast.makeText(this, "缺少病死畜禽和畜主合照，至少一张", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tvIdCrad.getText())) {
            Toast.makeText(this, "请填写身份证号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tvIcCrad.getText())) {
            Toast.makeText(this, "请填写一卡通号", Toast.LENGTH_SHORT).show();
            return false;
        }
        //死亡数 不能为空
        if (TextUtils.isEmpty(getSwsOrCbsFromLayout(2))) {
            Toast.makeText(this, "死亡数不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        //死亡数 不能为空
        if (Integer.parseInt(getSwsOrCbsFromLayout(2)) <= 0) {
            Toast.makeText(this, "死亡数必须大于0", Toast.LENGTH_SHORT).show();
            return false;
        }
        //参保数不能大于死亡数
        if (Integer.parseInt(getSwsOrCbsFromLayout(2)) <Integer.parseInt(getSwsOrCbsFromLayout(3))) {
            Toast.makeText(this, "参保数不能大于死亡数", Toast.LENGTH_SHORT).show();
            return false;
        }

        //收集地 不能为空
        if (linCollectionLocation.getVisibility()==View.VISIBLE&&spinnerCollectionLocation.getText().toString().equals("请选择")) {
            Toast.makeText(this, "病死畜禽移送地收集点选择不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!new File(getWuHaiHuaDir(), uuid + "_" + "A16.jpg").exists() || imgGuanfangshouyi.getDrawable() == null) {
            Toast.makeText(this, "兽医人员签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getSwsOrCbsFromLayout(int times) {

        if (model.tlAnim==null) return "";

        for (int i = 0; i < model.tlAnim.getChildCount(); i++) {
            if (model.tlAnim.getChildAt(i) instanceof TableRow) {
                int k = 1;
                for (int j = 0; j < ((TableRow) model.tlAnim.getChildAt(i)).getChildCount(); j++) {
                    TableRow tablerowLine = (TableRow) model.tlAnim.getChildAt(i);
                    if ((tablerowLine).getChildAt(j) instanceof EditText) {
                        String string = ((EditText) ((tablerowLine).getChildAt(j))).getText().toString();
                        if (times == k) {
                            return string;
                        }
                        k++;
                    }
                }
            }
        }
        return "";
    }

    public String getAnimTypeFromLayout() {
        for (int i = 0; i < model.tlAnim.getChildCount(); i++) {
            if (model.tlAnim.getChildAt(i) instanceof TableRow) {
                TableRow tablerowLine = (TableRow) model.tlAnim.getChildAt(i);
                for (int j = 0; j < tablerowLine.getChildCount(); j++) {
                    Log.e("class", "i =  " + i + " j =  " + j + "   class =" + tablerowLine.getChildAt(j).getClass().getName());
                    if (tablerowLine.getChildAt(j) instanceof EditText) {
                        return (String) ((EditText) (tablerowLine).getChildAt(j)).getText().toString();
                    } else if (((TableRow) model.tlAnim.getChildAt(i)).getChildAt(j) instanceof TextView && j > 0 && i >= 3) {
                        return ((TextView) (tablerowLine.getChildAt(j))).getText().toString();
                    }
                }
            }
        }
        return "";
    }

    private UploadReplaceApplyBean getData() {
        //疑似死亡原因
        if (rbYes.isChecked()) {
            bean.setFysswyy(rbYes.getText().toString());
        } else {
            bean.setFysswyy(rbNo.getText().toString());
            bean.setFsiwh(spinnerDeadthReason.getText().toString());
        }
        //处理方式1
        bean.setFclfs(spinnerProcessMethod1.getText().toString());


        if (dataListBean != null && spinnerProcessMethod1.getText().toString().equals("集中处理")) {
            //集中处理
            bean.setFyssj(tvEvokeTime.getText().toString());//移送时间
            CollectionListBean.DataListBean listBean = this.dataListBean.get(index);
            bean.setFsjd(listBean.getFsjdName());
            bean.setFsjdID(listBean.getFStId());
        } else {
            //处理方式2
            bean.setFclfs2(spinnerProcessMethod2.getText().toString());
        }

        //耳标号
        bean.setQDWErBiaoHao(etDeadErbiao.getText().toString());
        bean.setJD(String.valueOf(latitude));
        bean.setWD(String.valueOf(longitude));

        bean.setFxcll(etNumber.getText().toString());

        bean.setFbsdwzl(getAnimTypeFromLayout());
        bean.setFsws(getSwsOrCbsFromLayout(2));
        bean.setFcbs(getSwsOrCbsFromLayout(3));

        /*LocalAnimTypeBean localAnimTypeBean = model.getData().get(0);
        bean.setFbsdwzl(localAnimTypeBean.getAnimType());
        bean.setFsws(localAnimTypeBean.getDeadthNumber());
        bean.setFcbs(localAnimTypeBean.getInsuredNumber());*/
        bean.setLen("A1.jpg,A2.jpg,A3.jpg,A4.jpg,A5.jpg,A6.jpg,A7.jpg,A8.jpg,A9.jpg,A10.jpg,A11.jpg,A12.jpg,A13.jpg,A14.jpg,A15.jpg,A16.jpg");
        return bean;
    }

    /**
     * 集中处理
     */
    private void concentrationProcess() {
        linCollectionLocation.setVisibility(View.VISIBLE);
        linEvokeTime.setVisibility(View.VISIBLE);
        spinnerProcessMethod2.setVisibility(View.GONE);
        linKeepFile.setVisibility(View.GONE);
    }

    /**
     * 自行处理 特殊处理
     */
    private void concentrationProcessNot() {
        spinnerProcessMethod2.setVisibility(View.VISIBLE);
        linKeepFile.setVisibility(View.VISIBLE);
        linCollectionLocation.setVisibility(View.GONE);
        linEvokeTime.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_takephoto_ic_id:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 13, "A1", uuid + "_");
                break;
            case R.id.img_takephoto_baodan:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START, "A2", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 1, "A3", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 2, "A4", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin3:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 3, "A5", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin4:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 4, "A6", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 5, "A7", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 6, "A8", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu3:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 7, "A9", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu4:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 8, "A10", uuid + "_");
                break;
            case R.id.img_takephoto_keep_file1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 9, "A11", uuid + "_");
                break;
            case R.id.img_takephoto_keep_file2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 10, "A12", uuid + "_");
                break;
            case R.id.img_takephoto_keep_file3:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 11, "A13", uuid + "_");
                break;
            case R.id.img_takephoto_keep_file4:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, REQUEST_CODE_START + 12, "A14", uuid + "_");
                break;
            case R.id.bt_shouyiqianming:
                //养殖户签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A15", uuid + "_");
                    imgPrincipalSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_guanfangshouyi:
                //官方兽医签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A16", uuid + "_");
                    imgGuanfangshouyi.setImageBitmap((Bitmap) object);
                }).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAGTAG", "onActivityResult执行了 ");
        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                data1 = (UserDetilBean.Data) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                bean.setFxzxm(data1.getFFarmName());
                bean.setGlid(data1.getFStId());
                getPresenter().getCollectionLocationList(data1.getFStId());
                //返回设置身份证和一卡通号的照片
                //下载图片
                if ((!TextUtils.isEmpty(data1.getImg()))) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Response response = new OkHttpClient.Builder().build().newCall(new Request.Builder().
                                        url(Constance.IMAGE_PATH + data1.getImg()).build()).execute();
                                File file = new File(getWuHaiHuaDir(),
                                        uuid + "_A1.jpg");
                                if (file.exists()) file.delete();
                                byte[] responseBytes = response.body().bytes();
                                final Bitmap bitmap = BitmapFactory.decodeByteArray(responseBytes, 0, (int) response.body().contentLength());
                                FileOutputStream ostream = null;
                                //如果请求图片大于100kb就进行压缩，否则将数组转化为流保存到本地
                                if (response.body().contentLength() > 102400) {
                                    try {
                                        ostream = new FileOutputStream(file);
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                                        ostream.close();
                                        compressBmpToFile(file.getPath());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    ByteArrayInputStream inputStream = new ByteArrayInputStream(responseBytes);
                                    ostream = new FileOutputStream(file);
                                    byte[] bytes = new byte[1024 * 10];
                                    int byteread = 0;
                                    while ((byteread = inputStream.read(bytes)) != -1) {
                                        ostream.write(bytes, 0, byteread);
                                    }
                                    inputStream.close();
                                    ostream.close();
                                }

                                if (response.body().contentLength() < 400000) {
                                    photoViewGroupPhoto.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (bitmap != null) {
                                                photoViewGroupPhoto.setImageBitmap(bitmap);
                                            }
                                        }
                                    }, 500);
                                } else {
                                    runOnUiThread(new Runnable() {
                                                      @Override
                                                      public void run() {
                                                          if (bitmap != null) {
                                                              photoViewGroupPhoto.setImageBitmap(bitmap);
                                                          }
                                                      }
                                                  }
                                    );
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                tvName.setText(data1.getFFarmName());
                //联系电话
                tvPhone.setText(data1.getFPhone());
                bean.setLxdh(data1.getFPhone());

                tvAddr.setText(data1.getFCityAdd());//地址
                bean.setFxxdz(data1.getFCityAdd());

                tvType.setText(data1.getFyzcType());//养殖场类型
                bean.setFyzclx(data1.getFyzcType());

                etNumber.setText(data1.getFherdsScale());//现存栏量
                tvUnit.setText(data1.getFSmemo1());
                bean.setFxcll(data1.getFherdsScale());
                bean.setFdw1(data1.getFSmemo1());

                tvIdCrad.setText(data1.getFPLegalCardId());//身份证号
                bean.setFsfzh(data1.getFPLegalCardId());

                name.setText(data1.getFLegalPerson());//姓名
                bean.setXm(data1.getFLegalPerson());

                tvIcCrad.setText(data1.getYKTH());//一卡通号
                bean.setFykth(data1.getYKTH());
                isDestoryed = false;
                isFarmInfoIntent = true;
                return;
            }
            Bitmap bitmap;
            bitmap = decodeSampleBitmapFromFile(compressBmpToFile(currentPhotoFile));
            switch (requestCode) {
                case REQUEST_CODE_START + 13:
                    iv_none.setVisibility(View.GONE);
                    photoViewGroupPhoto.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START:
                    iv_none_1.setVisibility(View.GONE);
                    photoViewBaodan.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 1:
                    iv_none_2.setVisibility(View.GONE);
                    photoViewXuqin1.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 2:
                    iv_none_3.setVisibility(View.GONE);
                    photoViewXuqin2.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 3:
                    iv_none_4.setVisibility(View.GONE);
                    photoViewXuqin3.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 4:
                    iv_none_5.setVisibility(View.GONE);
                    photoViewXuqin4.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 5:
                    iv_none_6.setVisibility(View.GONE);
                    photoViewXuzhu1.setImageBitmap(bitmap);
                    photoViewXuzhu1.invalidate();
                    break;
                case REQUEST_CODE_START + 6:
                    iv_none_7.setVisibility(View.GONE);
                    photoViewXuzhu2.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 7:
                    iv_none_8.setVisibility(View.GONE);
                    photoViewXuzhu3.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 8:
                    iv_none_9.setVisibility(View.GONE);
                    photoViewXuzhu4.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 9:
                    iv_none_10.setVisibility(View.GONE);
                    photoViewKeepFile1.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 10:
                    iv_none_11.setVisibility(View.GONE);
                    photoViewKeepFile2.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 11:
                    iv_none_12.setVisibility(View.GONE);
                    photoViewKeepFile3.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_START + 12:
                    iv_none_13.setVisibility(View.GONE);
                    photoViewKeepFile4.setImageBitmap(bitmap);
                    break;
            }
        }

    }

    @Override
    public void setTime(String time, long longTime) {
        tvDate.setText(time);//申报时间
        bean.setFsbrq(time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(longTime);
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        String evokeTime = format.format(calendar.getTime());

        tvEvokeTime.setText(evokeTime);//移送时间
        bean.setFyssj(evokeTime);
    }


    @Override
    public void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBean) {
        if (dataListBean == null) {
            Toast.makeText(this, "所在行政没有收集点不能集中处理", Toast.LENGTH_SHORT).show();

            processMethod1Dialog.setDatas(getResources().getStringArray(R.array.process_method1_not));
            spinnerProcessMethod1.setText("自行处理");
            concentrationProcessNot();
            /*//这里为什么要将监听器设置为空
            spinnerProcessMethod1.setOnItemSelectedListener(null);*/
        } else {
            this.dataListBean = dataListBean;

            processMethod1Dialog.setDatas(getResources().getStringArray(R.array.process_method1));
            spinnerProcessMethod1.setText("集中处理");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < dataListBean.size(); i++) {
                list.add(dataListBean.get(i).getFsjdName());

            }

            collectionLocationDialog.setDatas(list);
            spinnerCollectionLocation.setText("请选择");
            concentrationProcess();
        }
    }

    @Override
    public void getAnimType(AnimTypeListBean.DataBean data) {
        //动物种类
        ArrayList<String> animType = new ArrayList<>();
        for (int i = 0; i < data.getDataList1().size(); i++) {
            animType.add(data.getDataList1().get(i).getZL());
        }
        erBiaoAnimTypeList = new ArrayList<>();
        for (int i = 0; i < data.getDataList2().size(); i++) {
            erBiaoAnimTypeList.add(data.getDataList2().get(i).getZL());
        }
        Log.e("isDestoryed", "" + "isDestoryed getAnimType " + isDestoryed);
        if (!isDestoryed) {
            model = new AnimTypeModel(this, animType);
            Log.e("AnimTypeModel", "getAnimType data.get(0).getAnimType() = " + model.getData().get(0).getAnimType() + "data.get(0).getDeadthNumber() = "
                    + model.getData().get(0).getDeadthNumber() + "data.get(0).getInsuredNumber() = " + model.getData().get(0).getInsuredNumber());
        }
        isDestoryed = false;

    }

    @Override
    public void uploadSuccessful() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void setUserMsg(UserDetilBean.Data data) {
       /* tvName.setText(data.getFFarmName());//畜主姓名
        bean.setFxzxm(data.getFFarmName());
        name.setText(data.getFLegalPerson());
        bean.setXm(name.getText().toString());
        tvAddr.setText(data.getFCityAdd());//地址
        bean.setFxxdz(data.getFCityAdd());

        tvType.setText(data.getFyzcType());//养殖场类型
        bean.setFyzclx(data.getFyzcType());

        etNumber.setText(data.getFherdsScale() + " " + data.getFSmemo1());//现存栏量
        bean.setFxcll(data.getFherdsScale());
        bean.setFdw1(data.getFSmemo1());

        tvIdCrad.setText(data.getFPLegalCardId());//身份证号
        bean.setFsfzh(data.getFPLegalCardId());

        tvIcCrad.setText(data.getYKTH());//一卡通号
        bean.setFykth(data.getYKTH());*/
    }

    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }

}

