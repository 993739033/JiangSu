package com.wyw.jiangsu.activity.wuhaihua;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.LocationActity;
import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.bean.local.LocalAnimTypeBean;
import com.wyw.jiangsu.bean.upload.UploadReplaceApplyBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.ErBiaoDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.test.interfac.IUpdateAgencyActivity;
import com.wyw.jiangsu.test.presenter.UpdateAgencyActivityPresenter;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.wyw.jiangsu.R.id.photo_view_group_photo;
import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
    public class UpdateAgencyActivity extends LocationActity<UpdateAgencyActivityPresenter>
        implements IUpdateAgencyActivity, ITime, PhotoViewModel.OnClickListener {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_idCrad)
    TextView tvIdCrad;
    @BindView(R.id.tv_icCrad)
    TextView tvIcCrad;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.img_takephoto_ic_id)
    ImageView imgTakephotoIcId;
    @BindView(photo_view_group_photo)
    ImageView photoViewIcId;
    @BindView(R.id.spinner_deadth_type)
    EditText spinnerDeadthType;
    @BindView(R.id.et_deadth_number)
    EditText etDeadthNumber;
    @BindView(R.id.et_insured_number)
    EditText etInsuredNumber;
    @BindView(R.id.photo_view_baodan)
    ImageView photoViewBaodan;
    @BindView(R.id.et_dead_erbiao)
    EditText etDeadErbiao;
    @BindView(R.id.photo_view_xuqin1)
    ImageView photoViewXuqin1;
    @BindView(R.id.photo_view_xuqin2)
    ImageView photoViewXuqin2;
    @BindView(R.id.photo_view_xuqin3)
    ImageView photoViewXuqin3;
    @BindView(R.id.photo_view_xuqin4)
    ImageView photoViewXuqin4;
    @BindView(R.id.photo_view_xuzhu1)
    ImageView photoViewXuzhu1;
    @BindView(R.id.photo_view_xuzhu2)
    ImageView photoViewXuzhu2;
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
    @BindView(R.id.photo_view_keep_file1)
    ImageView photoViewKeepFile1;
    @BindView(R.id.photo_view_keep_file2)
    ImageView photoViewKeepFile2;
    @BindView(R.id.lin_keep_file)
    LinearLayout linKeepFile;
    @BindView(R.id.photo_view_keep_file3)
    ImageView photoViewKeepFile3;
    @BindView(R.id.photo_view_keep_file4)
    ImageView photoViewKeepFile4;
    @BindView(R.id.img_principal_signature)
    ImageView imgPrincipalSignnature;
    @BindView(R.id.img_guanfangshouyi)
    ImageView imgGuanfangshouyi;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    ImageView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;
    @BindView(R.id.bt_erbiao)
    Button btErBiao;

    PhotoViewUtils photoViewUtils;
    String tempFileName;
    AnimTypeModel model;

    private DataSelectDialog deadthReasonDialog;
    private DataSelectDialog processMethod1Dialog;
    private DataSelectDialog processMethod2Dialog;
    private DataSelectDialog collectionLocationDialog;

    private int index=0;

    TimePresenter timePresenter;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    List<CollectionListBean.DataListBean> dataListBean;
    ArrayList<String> erBiaoAnimTypeList;//必须输入耳标的动物种类

    UploadReplaceApplyBean bean;
    private String jingdu;
    private String weidu;
    private String uuid;
    private AgencyDeclareDetilActivityBean.DataBean fdata;

    private PhotoViewModel ImageViewModel;
//    @BindView(R.id.photo_view_group_photo)
//    ImageView photoViewGroupPhoto;
    private String currentPhotoFile;

    @Override
    protected UpdateAgencyActivityPresenter createPresenter() {
        return new UpdateAgencyActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_replace_apply_query);
    }

    @Override
    public void bindData() {
        setTitle("代申报修改");
        bean = new UploadReplaceApplyBean();
        fdata = ((AgencyDeclareDetilActivityBean.DataBean) getIntent().getExtras().getSerializable(Constance.ACTIVITY_DATA));
        if (fdata != null)
            bean.setFStId(fdata.getFStId());
        uuid = fdata.getPictures1().getA1().split("_")[0];
        replaceData();


        timePresenter = new TimePresenter(this);
        //getAdd().setImageResource(android.R.drawable.ic_menu_search);
       // getAdd().setVisibility(View.VISIBLE);
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        // 启动定位
        locationClient.startLocation();


//        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
//        photoViewUtils.initPhotoView(photoViewIcId, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewBaodan, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 1, "A2", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuqin1, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 2, "A3", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuqin2, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 3, "A4", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuqin3, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 4, "A5", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuqin4, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 5, "A6", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuzhu1, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 6, "A7", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuzhu2, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 7, "A8", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuzhu3, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 8, "A9", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewXuzhu4, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 9, "A10", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewKeepFile1, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 10, "A11", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewKeepFile2, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 11, "A12", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewKeepFile3, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 12, "A13", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewKeepFile4, () -> tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 13, "A14", uuid + "_"));
//
        String nameManager1 = uuid + "_A1", nameManager2 = uuid + "_A2",
                nameManager3 = uuid + "_A3", nameManager4 = uuid + "_A4",
                nameManager5 = uuid + "_A5", nameManager6 = uuid + "_A6",
                nameManager7 = uuid + "_A7", nameManager8 = uuid + "_A8",
                nameManager9 = uuid + "_A9", nameManager10 = uuid + "_A10",
                nameManager11 = uuid + "_A11", nameManager12 = uuid + "_A12",
                nameManager13 = uuid + "_A13", nameManager14 = uuid + "_A14";
//

        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewIcId, REQUEST_CODE_START + 13, nameManager1, getWuHaiHuaDir(), this);
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

//        getPresenter().getCollectionLocationList(0+"");
        getPresenter().getAnimType();
//        getPresenter().getUserDetil();
        timePresenter.getTime();

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

    private void replaceData() {
        tvName.setText(fdata.getFxzxm());
        name.setText(fdata.getXm());//姓名
        bean.setFxzxm(fdata.getFxzxm());
        bean.setGlid(fdata.getGlid());

        //联系电话
        tvPhone.setText(fdata.getLxdh());
        bean.setLxdh(fdata.getLxdh());

        tvAddr.setText(fdata.getFxxdz());//地址
        bean.setFxxdz(fdata.getFxxdz());

        tvType.setText(fdata.getFyzclx());//养殖场类型
        bean.setFyzclx(fdata.getFyzclx());

        etNumber.setText(fdata.getFxcll());//现存栏量
        tvUnit.setText(fdata.getFdw1());
        bean.setFxcll(fdata.getFxcll());
        bean.setFdw1(fdata.getFdw1());

        tvIdCrad.setText(fdata.getFsfzh());//身份证号
        bean.setFsfzh(fdata.getFsfzh());

        tvIcCrad.setText(fdata.getFykth());//一卡通号
        spinnerDeadthType.setText(fdata.getFbsdwzl());//动物种类
        bean.setFykth(fdata.getFykth());

        etDeadErbiao.setText(fdata.getQDWErBiaoHao());
        bean.setQDWErBiaoHao(fdata.getQDWErBiaoHao());
        etDeadthNumber.setText(fdata.getFsws());
        bean.setFsws(fdata.getFsws());
        etInsuredNumber.setText(fdata.getFcbs());
        bean.setFcbs(fdata.getFcbs());

        getPresenter().getCollectionLocationList(fdata.getGlid());


        //图片
        if (!TextUtils.isEmpty(fdata.getPictures1().getA1())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewIcId);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA2())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA2())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewBaodan);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA3())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA3())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin1);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA4())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA4())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin2);
        }

        if (!TextUtils.isEmpty(fdata.getPictures1().getA5())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA5())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin3);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA6())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA6())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin4);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA7())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA7())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu1);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA8())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA8())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu2);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA9())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA9())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu3);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA10())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA10())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu4);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA11())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA11())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile1);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA12())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA12())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile2);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA13())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA13())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile3);
        }


        if (!TextUtils.isEmpty(fdata.getPictures1().getA14())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA14())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile4);
        }

        if (!TextUtils.isEmpty(fdata.getPictures1().getA15())) {
            //养殖场签名

                Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA15())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgPrincipalSignnature);


        }
        //兽医签名
        if (!TextUtils.isEmpty(fdata.getPictures1().getA16())) {

                Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA16())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgGuanfangshouyi);
        }

    }

    @Override
    protected void invokeMethod(double latitude, double longitude) {

    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                if (location.getErrorCode() == 0) {
                    jingdu = location.getLongitude() + "";
                    weidu = location.getLatitude() + "";
                }
            } else {
//                Toast.makeText(PhoneRegisteActivity.this, "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void bindListener() {
        getAdd().setOnClickListener(v -> {
            startActivityForResult(new Intent(this, UserDetilSearchActivity.class), Constance.ACTIVITY_CODE);
        });
        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                getPresenter().upload(getData(), uuid);
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
        btErBiao.setOnClickListener(v -> {
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
 /*       if (imgPrincipalSignnature.getDrawable() != null && !new File(PhotoUtils.getWuHaiHuaDir(), uuid + "_" + "A15.jpg").exists()) {
            return false;
        }*/
        if ( imgPrincipalSignnature.getDrawable() == null) {
            Toast.makeText(this, "畜主/养殖场签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (imgGuanfangshouyi.getDrawable() == null) {
            Toast.makeText(this, "官方兽医签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( imgTakephotoIcId.getDrawable() == null) {
            Toast.makeText(this, "请拍摄身份证和一卡通号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(bean.getGlid())) {
            Toast.makeText(this, "缺少养殖户相关信息,请点击右上角", Toast.LENGTH_SHORT).show();
            return false;
        }

        //收集地 不能为空
        if (linCollectionLocation.getVisibility()==View.VISIBLE&&spinnerCollectionLocation.getText().toString().equals("请选择")) {
            Toast.makeText(this, "病死畜禽移送地收集点选择不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        //必须输入耳标的动物种类
        String animType = model.getData().get(0).getAnimType();
        for (int i = 0; i < erBiaoAnimTypeList.size(); i++) {
            if (animType.equals(erBiaoAnimTypeList.get(i))) {
                if (TextUtils.isEmpty(etDeadErbiao.getText().toString())) {
                    Toast.makeText(this, "当前动物种类必须输入耳标号", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        //死亡数 不能为空
        if (TextUtils.isEmpty(model.getData().get(0).getDeadthNumber())) {
            Toast.makeText(this, "死亡数不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
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
        bean.setFxcll(etNumber.getText().toString());
        //经纬度
        if (!TextUtils.isEmpty(jingdu)) {
            bean.setJD(jingdu);
        } else {
            bean.setJD("");
        }
        if (!TextUtils.isEmpty(weidu)) {
            bean.setWD(weidu);
        } else {
            bean.setWD("");
        }


        LocalAnimTypeBean localAnimTypeBean = model.getData().get(0);
        bean.setFbsdwzl(localAnimTypeBean.getAnimType());
        bean.setFsws(localAnimTypeBean.getDeadthNumber());
        bean.setFcbs(localAnimTypeBean.getInsuredNumber());
        bean.setLen("A1.jpg,A2.jpg,A3.jpg,A4.jpg,A5.jpg,A6.jpg,A7.jpg,A8.jpg,A9.jpg,A10.jpg,A11.jpg,A12.jpg,A13.jpg,A14.jpg,A15.jpg,A16.jpg");
//        bean.setLen(PhotoUtils.getWuHaiHuaDir().listFiles().length);

//        bean.setFbsdwzl("猪");//病死动物种类
//        bean.setFsws("10");//死亡数
//        bean.setFcbs("10");//参保数
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
        //删除图片
//        new File(PhotoUtils.getWuHaiHuaDir(), "A11.jpg").delete();
//        new File(PhotoUtils.getWuHaiHuaDir(), "A12.jpg").delete();
//        new File(PhotoUtils.getWuHaiHuaDir(), "A13.jpg").delete();
//        new File(PhotoUtils.getWuHaiHuaDir(), "A14.jpg").delete();
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
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK) {
                if (requestCode == Constance.ACTIVITY_CODE) {
                    UserDetilBean.Data data1 = (UserDetilBean.Data) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                    tvName.setText(data1.getFFarmName());//畜主姓名
                    bean.setFxzxm(data1.getFFarmName());
                    bean.setGlid(data1.getFStId());
                    getPresenter().getCollectionLocationList(data1.getFStId());

                    //身份证和一卡通号的照片
                    //下载图片
                    if (!TextUtils.isEmpty(data1.getImg())) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Response response = new OkHttpClient.Builder().build().newCall(new Request.Builder().
                                            url(Constance.IMAGE_PATH + data1.getImg()).build()).execute();
                                    File file = new File(PhotoUtils.getWuHaiHuaDir(),
                                            uuid + "_A1.jpg");
                                    final Bitmap bitmap = BitmapFactory.decodeByteArray(response.body().bytes(), 0, (int) response.body().contentLength());
                                    FileOutputStream ostream = null;
                                    try {
                                        ostream = new FileOutputStream(file);
                                       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                                        ostream.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                                      @Override
                                                      public void run() {
                                                          photoViewIcId.setImageBitmap(bitmap);
                                                      }
                                                  }
                                    );
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }


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

                    tvIcCrad.setText(data1.getYKTH());//一卡通号
                    bean.setFykth(data1.getYKTH());
                    return;
                }
//                Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(tempFileName, PhotoUtils.DEFAULT_WIDTH, PhotoUtils.DEFAULT_HEIGHT);
                switch (requestCode) {
                    case PhotoUtils.REQUEST_CODE_START+13:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewIcId);
                        break;
                    case PhotoUtils.REQUEST_CODE_START :
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewBaodan);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 1:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuqin1);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 2:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuqin2);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 3:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuqin3);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 4:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuqin4);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 5:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuzhu1);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 6:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuzhu2);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 7:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuzhu3);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 8:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewXuzhu4);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 9:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewKeepFile1);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 10:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewKeepFile2);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 11:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewKeepFile3);
                        break;
                    case PhotoUtils.REQUEST_CODE_START + 12:
                        Glide.with(this).load(currentPhotoFile).asBitmap().into(photoViewKeepFile4);
                        break;
                }
            }
        }

        @OnClick({R.id.img_takephoto_ic_id, R.id.img_takephoto_baodan,
                R.id.img_takephoto_xuqin1, R.id.img_takephoto_xuqin2,
                R.id.img_takephoto_xuqin3, R.id.img_takephoto_xuqin4,
                R.id.img_takephoto_xuzhu1, R.id.img_takephoto_xuzhu2,
                R.id.img_takephoto_xuzhu3, R.id.img_takephoto_xuzhu4,
                R.id.img_takephoto_keep_file1, R.id.img_takephoto_keep_file2,
                R.id.img_takephoto_keep_file3, R.id.img_takephoto_keep_file4,
                R.id.bt_shouyiqianming, R.id.bt_guanfangshouyi})
        public void takePhoto(View view) {
            switch (view.getId()) {
//                case R.id.img_takephoto_ic_id:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
//                    break;
//                case R.id.img_takephoto_baodan:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 1, "A2", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuqin1:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 2, "A3", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuqin2:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 3, "A4", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuqin3:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 4, "A5", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuqin4:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 5, "A6", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuzhu1:
//                    new File(PhotoUtils.getWuHaiHuaDir(), "A7.jpg").delete();
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 6, "A7", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuzhu2:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 7, "A8", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuzhu3:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 8, "A9", uuid + "_");
//                    break;
//                case R.id.img_takephoto_xuzhu4:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 9, "A10", uuid + "_");
//                    break;
//                case R.id.img_takephoto_keep_file1:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 10, "A11", uuid + "_");
//                    break;
//                case R.id.img_takephoto_keep_file2:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 11, "A12", uuid + "_");
//                    break;
//                case R.id.img_takephoto_keep_file3:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 12, "A13", uuid + "_");
//                    break;
//                case R.id.img_takephoto_keep_file4:
//                    tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 13, "A14", uuid + "_");
//                    break;

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
                        imgPrincipalSignnature.setImageBitmap((Bitmap) object);
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
    public void onBackPressed() {
//        if (photoViewUtils.onBackPress()) super.onBackPressed();
        finish();
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

            concentrationProcessNot();
        } else {
            this.dataListBean = dataListBean;
            List<String> list = new ArrayList<>();
            for (int i = 0; i < dataListBean.size(); i++) {
                list.add(dataListBean.get(i).getFsjdName());

            }
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
        model = new AnimTypeModel(this, animType);
    }

    @Override
    public void uploadSuccessful() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void setUserMsg(UserDetilBean.Data data) {
        tvName.setText(data.getFFarmName());//畜主姓名
        bean.setFxzxm(data.getFFarmName());
        bean.setGlid(data.getFStId());

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
        bean.setFykth(data.getYKTH());

    }

    private void initdata() {
/*
        // 畜主姓名ID
       // private String Glid;
        bean.setGlid(fdata.getGlid());
        // 畜主姓名
       // private String Fxzxm;
        bean.setFxzxm(fdata.getFxzxm());
        //联系电话
        //private String lxdh;
        bean.setDZ(fdata.getFxxdz());
        // 养殖场类型
       // private String Fyzclx;
        bean.setFyzclx(fdata.getFyzclx());
        // 现存栏量
        //private String Fxcll;
        bean.setFxcll(fdata.getFxcll());
        // 申报日期
        //private String Fsbrq;
        bean.setFsbrq(fdata.getFsbrq());
        // 身份证号
        //private String Fsfzh ;
        bean.setFsfzh(fdata.getFsfzh());
        //一卡通号
        //private String fykth;
        bean.setFykth(fdata.getFykth());
        // 病死动物种类
        //private String Fbsdwzl;
        bean.setFbsdwzl(fdata.getFbsdwzl());
        // 死亡数
        //private String Fsws;
        bean.setFsws(fdata.getFsws());
        // 参保数
       // private String Fcbs;
        bean.setFcbs(fdata.getFcbs());
        //// 耳标号
        //private String QDWErBiaoHao;
        bean.setFyzclx(fdata.getQDWErBiaoHao());
        //// 疑似死亡原因
        //private String Fysswyy;
        bean.setFysswyy(fdata.getFysswyy());
        // 非正常死亡
       // private String Fsiwh;
        bean.setFsiwh(fdata.getFsiwh());
        //移送时间
       // private String Fyssj;
        bean.setFyssj(fdata.getFyssj());
        // 病死畜禽移送收集点
        //private String Fsjd;
        bean.setFsjd(fdata.getFsjd());
        //病死畜禽移送收集点id
       // private String FsjdID;
        bean.setFsjdID(fdata.getFsjdID());
        /// 关联监管兽医确认ID
        //private String FGlid;
        bean.setFGlid(fdata.getFGlid());
        //经度
        //private String JD;
        bean.setJD(fdata.getJD());
        //维度
        //private String WD;
        bean.setWD(fdata.getWD());
        //地址
       // private String DZ;
        bean.setDZ(fdata.getDZ());
        // 处理方式2
       // private String Fclfs2;
        bean.setFclfs2(fdata.getFclfs2());
        //官方兽医签字
        //private String Fgfsyqz;
        bean.setFgfsyqz(fdata.getFgfsyqz());
        ////处理方式1
        //private String Fclfs;
        bean.setFclfs(fdata.getFclfs());
        // 签名
        //private String Fqm;
        bean.setFqm(fdata.getFqm());
        //地址
        //private String Fxxdz;
        bean.setFxxdz(fdata.getFxxdz());
        //// 单位
       // private String Fdw1;
        bean.setFdw1(fdata.getFdw1());
        //以上内容是否符合要求
        //private String SFFH;
        bean.setSFFH(fdata.getSFFH());
        //长度
        //private String len;
        bean.setLxdh(fdata.getLxdh());
        bean.setFStId(fdata.getFStId());




        tvName.setText(fdata.getFxzxm());

        tvAddr.setText(fdata.getFxxdz());

        tvType.setText(fdata.getFyzclx());

        etNumber.setText(fdata.getFxcll());

        tvIdCrad.setText(fdata.getFsfzh());

        tvIcCrad.setText(fdata.getFykth());

        tvDate.setText(fdata.getFsbrq());
        etDeadthNumber.setText(fdata.getFsws());
        etInsuredNumber.setText(fdata.getFcbs());
        etDeadErbiao.setText(fdata.getQDWErBiaoHao());
        if(fdata.getFysswyy().equals("正常死亡")){
            rbYes.setChecked(true);
        }else {
            rbNo.setChecked(true);
        }

        uuid=fdata.getPictures1().getA1().split("_")[0];


        //图片
        if (!TextUtils.isEmpty(fdata.getPictures1().getA1())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewIcId);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA2())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA2())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewBaodan);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA3())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA3())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin1);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA4())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA4())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin2);
        }

        if (!TextUtils.isEmpty(fdata.getPictures1().getA5())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA5())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin3);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA6())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA6())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin4);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA7())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA7())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu1);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA8())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA8())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu2);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA9())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA9())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu3);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA10())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA10())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu4);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA11())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA11())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile1);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA12())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA12())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile2);
        }
        if (!TextUtils.isEmpty(fdata.getPictures1().getA13())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA13())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewKeepFile3);
        }


        if (!TextUtils.isEmpty(fdata.getPictures1().getA14())) {
            Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA14())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewKeepFile4);
        }
///////////////////////////////////////
        if (!TextUtils.isEmpty(fdata.getPictures1().getA15())) {
            //养殖场签名
            String yangFile = jieQu(fdata.getPictures1().getA15());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA15())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgPrincipalSignnature);

            } else {

            }
        } else {

        }
        //兽医签名
        if (!TextUtils.isEmpty(fdata.getPictures1().getA16())) {
            String file = jieQu(fdata.getPictures1().getA16());
            if (file.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + fdata.getPictures1().getA16())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgGuanfangshouyi);
            }
        } */

    }


    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
