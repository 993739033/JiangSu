package com.wyw.jiangsu.activity.wuhaihua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.LocationActity;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.local.LocalAnimTypeBean;
import com.wyw.jiangsu.bean.upload.UploadReplaceApplyBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessSupervisionVeterinariansConfirmActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.HarmlessSupervisionVeterinariansConfirmActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;
import com.wyw.jiangsu.utils.SPUtils;
import com.wyw.jiangsu.view.photoview.PhotoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;

/**
 * 监管兽医确认(现场核查)   (自行处理 特殊处理确认,集中处理)
 * 处理方式（如果是自行处理或特殊处理，则不出现移送收集点时间，出现处理方式的选择；
 * 如果处理方式为集中处理，则出现移送时间，移送时间默认为当天。）
 * 处理方式 集中处理 病死动物移送收集点，默认为当前管辖乡镇站名称）
 * <p>
 * APP_TSCL—特殊处理
 * APP_ZXCL—自行处理
 * APP_JZCL—集中处理
 * 上个页面  监管兽医确认列表 {@link HarmlessSupervisionVeterinatiansListActivity}
 * 下个页面  暂存点消息接收列表 {@link HarmlessMessageAcceptCollectionListActivity}
 */
public class HarmlessSupervisionVeterinariansConfirmActivity extends LocationActity<HarmlessSupervisionVeterinariansConfirmActivityPresenter>
        implements IHarmlessSupervisionVeterinariansConfirmActivity, ITime, PhotoViewModel.OnClickListener {

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_type)
    EditText tvType;
    @BindView(R.id.et_number)
    EditText tvNumber;
    @BindView(R.id.tv_idCrad)
    EditText tvIdCrad;
    @BindView(R.id.tv_icCrad)
    EditText tvIcCrad;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewIcId;
    //    @BindView(R.id.spinner_deadth_type)
//    Spinner spinnerDeadthType;
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
    @BindView(R.id.rb_yes_accord)
    RadioButton rbYesAccord;
    @BindView(R.id.tv_process_method1)
    TextView tvProcessMethod1;
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
    @BindView(R.id.img_takephoto_process1)
    ImageView imgTakephotoProcess1;
    @BindView(R.id.photo_view_process1)
    ImageView photoViewProcess1;
    @BindView(R.id.img_takephoto_process2)
    ImageView imgTakephotoProcess2;
    @BindView(R.id.photo_view_process2)
    ImageView photoViewProcess2;
    @BindView(R.id.img_takephoto_process3)
    ImageView imgTakephotoProcess3;
    @BindView(R.id.photo_view_process3)
    ImageView photoViewProcess3;
    @BindView(R.id.img_takephoto_process4)
    ImageView imgTakephotoProcess4;
    @BindView(R.id.photo_view_process4)
    ImageView photoViewProcess4;
    @BindView(R.id.img_principal_signature)
    ImageView imgPrincipalSignature;
    @BindView(R.id.img_guanfangshouyi)
    ImageView imgGuanfangshouyi;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    PhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;
    @BindView(R.id.lin_process)
    LinearLayout linProcess;

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

    UploadReplaceApplyBean bean;
    List<CollectionListBean.DataListBean> dataListBean;//收集点
    PhotoViewUtils photoViewUtils;
    String fclfs;//自行处理 集中处理 特殊处理
    AnimTypeModel animTypeModel;
    private boolean tLFocusable = true;

    TimePresenter timePresenter;
    @BindView(R.id.name)
    TextView name;
    private String fbsdwzl;
    private String xcllNumber;

    private PhotoViewModel ImageViewModel;
    private String currentPhotoFile;

    private String uuid;
    HarmlessListDetilBean.DataBean dataBean;
    private boolean isDetoryed;
    private String deadErbiao;
    private String fstId;
    private ArrayList<LocalAnimTypeBean> data;
    private int collectionLocationPosition;
    private boolean firstTimeNotPassed = true;
    private String sws;
    private String cbs;
    private String animtype;
    private int whencreate;
    private int collectIndex=-1;//收集点选中index


    private DataSelectDialog deadthReasonDialog;
    private DataSelectDialog processMethod1Dialog;
    private DataSelectDialog processMethod2Dialog;
    private DataSelectDialog collectionLocationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_veterinarians_confirm);
        ButterKnife.bind(this);
        uuid = UUID.randomUUID().toString();
    }

    @Override
    protected HarmlessSupervisionVeterinariansConfirmActivityPresenter createPresenter() {
        return new HarmlessSupervisionVeterinariansConfirmActivityPresenter(this);
    }

    @Override
    public void bindData() {
        super.bindData();
        timePresenter = new TimePresenter(this);

        bean = new UploadReplaceApplyBean();
        whencreate = 1;
        fclfs = getIntent().getStringExtra(Constance.ACTIVITY_TYPE);   //处理方式
//        spinnerProcessMethod2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
//                getResources().getStringArray(R.array.process_method2)));
//        if (fclfs.equals("自行处理")) {
//            spinnerProcessMethod2.setSelection(0);
//        } else {
//            spinnerProcessMethod2.setSelection(1);
//        }
        if (fclfs.equals("自行处理")) {
            spinnerProcessMethod2.setText(getResources().getStringArray(R.array.process_method2)[0]);
        } else {
            spinnerProcessMethod2.setText(getResources().getStringArray(R.array.process_method2)[1]);
        }

        //死亡原因
//        spinnerDeadthReason.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
//                getResources().getStringArray(R.array.deadth_reason)));
        //默认 集中处理
        concentrationProcess();
        fstId = getIntent().getStringExtra(Constance.ACTIVITY_DATA);
        tvProcessMethod1.setText(fclfs);
        setTitle(fclfs);
        bean.setFGlid(fstId);

        initDialog();
    }


    private void initDialog() {
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
                collectIndex = collectionLocationDialog.getNowIndex();
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

    @Override
    protected void onResume() {
        super.onResume();
        if (whencreate == 1)
        getPresenter().getPageDetil(fstId, firstTimeNotPassed);

            for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
                if (file.getName().contains(uuid + "_A16")) {
                    Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgPrincipalSignature);
                } else if (file.getName().contains(uuid + "_A17")) {
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
        ImageViewModel.bindData(photoViewXuqin1, REQUEST_CODE_START + 2, nameManager3, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuqin2, REQUEST_CODE_START + 3, nameManager4, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuqin3, REQUEST_CODE_START + 4, nameManager5, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuqin4, REQUEST_CODE_START + 5, nameManager6, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu1, REQUEST_CODE_START + 6, nameManager7, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu2, REQUEST_CODE_START + 7, nameManager8, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu3, REQUEST_CODE_START + 8, nameManager9, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewXuzhu4, REQUEST_CODE_START + 9, nameManager10, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewProcess1, REQUEST_CODE_START + 10, nameManager11, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewProcess2, REQUEST_CODE_START + 11, nameManager12, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewProcess3, REQUEST_CODE_START + 12, nameManager13, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewProcess4, REQUEST_CODE_START + 13, nameManager14, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewBaodan, REQUEST_CODE_START + 1, nameManager2, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewIcId, REQUEST_CODE_START, nameManager1, getWuHaiHuaDir(), this);

        if (isDetoryed) {
            ArrayList<String> list = new ArrayList<>();
            list.add(animtype);
            animTypeModel = new AnimTypeModel(this, list);
            animTypeModel.add(animtype, sws, cbs, true);
//            spinnerCollectionLocation.setSelection(collectionLocationPosition);
        }
        whencreate++;

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uuid = savedInstanceState.getString("uuid");
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
        deadErbiao = savedInstanceState.getString("deadErbiao");
        fbsdwzl = savedInstanceState.getString("fbsdwzl");
        fstId = savedInstanceState.getString("fstId");
        firstTimeNotPassed = savedInstanceState.getBoolean("firstTimeNotPassed");
        sws = savedInstanceState.getString("sws");
        cbs = savedInstanceState.getString("cbs");
        animtype = savedInstanceState.getString("animtype");
        collectionLocationPosition = savedInstanceState.getInt("CollectionLocation");
        tLFocusable = false;
        isDetoryed = true;
    }

    public String getSwsOrCbsFromLayout(int times) {
        for (int i = 0; i < animTypeModel.tlAnim.getChildCount(); i++) {
            if (animTypeModel.tlAnim.getChildAt(i) instanceof TableRow) {
                int k = 1;
                for (int j = 0; j < ((TableRow) animTypeModel.tlAnim.getChildAt(i)).getChildCount(); j++) {
                    TableRow tablerowLine = (TableRow) animTypeModel.tlAnim.getChildAt(i);
                    if (tablerowLine.getVisibility()==View.VISIBLE) {
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
        }
        return "";
    }

    public String getAnimTypeFromLayout() {
        for (int i = 0; i < animTypeModel.tlAnim.getChildCount(); i++) {
            if (animTypeModel.tlAnim.getChildAt(i) instanceof TableRow) {
                TableRow tablerowLine = (TableRow) animTypeModel.tlAnim.getChildAt(i);
                for (int j = 0; j < tablerowLine.getChildCount(); j++) {
                    Log.e("class", "i =  " + i + " j =  " + j + "   class =" + tablerowLine.getChildAt(j).getClass().getName());
                    if (tablerowLine.getChildAt(j) instanceof EditText) {
                        return (String) ((EditText) (tablerowLine).getChildAt(j)).getText().toString();
                    } else if (((TableRow) animTypeModel.tlAnim.getChildAt(i)).getChildAt(j) instanceof TextView && j > 0 && i >= 3) {
                        return ((TextView) (tablerowLine.getChildAt(j))).getText().toString();
                    }
                }
            }
        }
        return "";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        firstTimeNotPassed = false;
        outState.putString("uuid", uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        outState.putString("fstId", fstId);
        outState.putBoolean("firstTimeNotPassed", firstTimeNotPassed);
        String deadErbiao = etDeadErbiao.getText().toString();
        outState.putString("deadErbiao", deadErbiao);
        outState.putString("fbsdwzl", fbsdwzl);
        outState.putString("sws", getSwsOrCbsFromLayout(2));
        outState.putString("cbs", getSwsOrCbsFromLayout(3));
        String animTypeFromLayout = getAnimTypeFromLayout();
        outState.putString("animtype", animTypeFromLayout);
//        outState.putInt("CollectionLocation", spinnerCollectionLocation.getSelectedItemPosition());
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void invokeMethod(double latitude, double longitude) {

    }

    @Override
    public void bindListener() {

        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                Log.e("getData", getData().toString());
                upData();
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
    }

    private void upData() {
        //死亡率超过10%请再次确认信息是否准确。
        if ((((Integer.parseInt(animTypeModel.getDataVeterinariansConfirm().get(0).getDeadthNumber()) - 1) * 100) / (Integer.parseInt(xcllNumber))) >= 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示:")
                    .setMessage("死亡率超过10%,是否保存!")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getPresenter().upload(getData(), uuid);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        } else {
            showNormalDialog();
        }
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessSupervisionVeterinariansConfirmActivity.this);
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


    public UploadReplaceApplyBean getData() {
        bean.setFsws(getSwsOrCbsFromLayout(2));//死亡数
        bean.setFcbs(getSwsOrCbsFromLayout(3));//参保数
//        bean.setFbsdwzl(data.get(0).getAnimType());//动物种类
        bean.setFbsdwzl(getAnimTypeFromLayout());//动物种类
        bean.setFclfs(tvProcessMethod1.getText().toString());//处理方式1
//        bean.setFclfs2(spinnerProcessMethod2.getSelectedItem().toString());//处理方式2

        bean.setFclfs2(spinnerProcessMethod2.getText().toString());//处理方式2
        bean.setSFFH(rbYesAccord.isChecked() ? "是" : "否");//以上是否符合要求
        //经纬度 地址
        bean.setJD(String.valueOf(longitude));
        bean.setWD(String.valueOf(latitude));
        bean.setDZ(addr);

        //疑似死亡原因
        if (rbYes.isChecked()) {
            bean.setFysswyy(rbYes.getText().toString());
        } else {
            bean.setFysswyy(rbNo.getText().toString());
//            bean.setFsiwh(spinnerDeadthReason.getSelectedItem().toString());
            bean.setFsiwh(spinnerDeadthReason.getText().toString());
        }
        if ("集中处理".equals(fclfs)) {
            //移送时间
            bean.setFyssj(tvEvokeTime.getText().toString());
//            CollectionListBean.DataListBean bean1 = dataListBean.get(spinnerCollectionLocation.getSelectedItemPosition());
            CollectionListBean.DataListBean bean1 = dataListBean.get(collectIndex);
            //收集点
            bean.setFsjd(bean1.getFsjdName());
            //收集点id
            bean.setFsjdID(bean1.getFStId());
        }
        bean.setQDWErBiaoHao(etDeadErbiao.getText().toString());
        bean.setLen("A1.jpg,A2.jpg,A3.jpg,A4.jpg,A5.jpg,A6.jpg,A7.jpg,A8.jpg,A9.jpg,A10.jpg,A11.jpg,A12.jpg,A13.jpg,A14.jpg,A15.jpg,A16.jpg,A17.jpg");
        return bean;
    }

    public boolean check() {
//        if (longitude == 0 || latitude == 0 || TextUtils.isEmpty(addr)) {
//            Toast.makeText(this, "请等待定位结束", Toast.LENGTH_SHORT).show();
//            return false;
//        }

    /*    if (imgPrincipalSignature.getDrawable() == null) {
            Toast.makeText(this, "负责人签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        if (TextUtils.isEmpty(spinnerDeadthReason.getText().toString())) {
            Toast.makeText(this, "请选择死亡原因", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(spinnerProcessMethod2.getText().toString())) {
            Toast.makeText(this, "请选择处理方式", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(spinnerCollectionLocation.getText().toString())||spinnerCollectionLocation.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择移送收集点", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imgGuanfangshouyi.getDrawable() == null) {
            Toast.makeText(this, "兽医人员签字不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

      /*  if (photoViewVeterinariansHezhao.getDrawable() == null) {
            Toast.makeText(this, "病死畜禽和官方兽医合照不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        if (photoViewXuqin1.getDrawable() == null && photoViewXuqin2.getDrawable() == null && photoViewXuqin3.getDrawable() == null && photoViewXuqin4.getDrawable() == null) {
            Toast.makeText(this, "病死畜禽照片至少需要一张", Toast.LENGTH_SHORT).show();
            return false;
        }
        //死亡数 不能为空
        if (TextUtils.isEmpty(getSwsOrCbsFromLayout(2))) {
            Toast.makeText(this, "死亡数不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        animTypeModel.check1();
        return true;
    }

    /**
     * 集中处理
     */
    private void concentrationProcess() {
        linCollectionLocation.setVisibility(View.VISIBLE);
        linEvokeTime.setVisibility(View.VISIBLE);
        linProcess.setVisibility(View.GONE);
    }

    /**
     * 自行处理 特殊处理
     */
    private void concentrationProcessNot() {
        linCollectionLocation.setVisibility(View.GONE);
        linEvokeTime.setVisibility(View.GONE);
        linProcess.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                    photoViewIcId.setImageBitmap(bitmap);
                    iv_none.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 1:
                    photoViewBaodan.setImageBitmap(bitmap);
                    iv_none_1.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 2:
                    photoViewXuqin1.setImageBitmap(bitmap);
                    iv_none_2.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 3:
                    photoViewXuqin2.setImageBitmap(bitmap);
                    iv_none_3.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 4:
                    photoViewXuqin3.setImageBitmap(bitmap);
                    iv_none_4.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 5:
                    photoViewXuqin4.setImageBitmap(bitmap);
                    iv_none_5.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 6:
                    photoViewXuzhu1.setImageBitmap(bitmap);
                    iv_none_6.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 7:
                    photoViewXuzhu2.setImageBitmap(bitmap);
                    iv_none_7.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 8:
                    photoViewXuzhu3.setImageBitmap(bitmap);
                    iv_none_8.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 9:
                    photoViewXuzhu4.setImageBitmap(bitmap);
                    iv_none_9.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 10:
                    photoViewProcess1.setImageBitmap(bitmap);
                    iv_none_10.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 11:
                    photoViewProcess2.setImageBitmap(bitmap);
                    iv_none_11.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 12:
                    photoViewProcess3.setImageBitmap(bitmap);
                    iv_none_12.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 13:
                    photoViewProcess4.setImageBitmap(bitmap);
                    iv_none_13.setVisibility(View.GONE);
                    break;

            }
        }
    }

    @OnClick({R.id.img_takephoto_ic_id, R.id.img_takephoto_baodan,
            R.id.img_takephoto_xuqin1, R.id.img_takephoto_xuqin2,
            R.id.img_takephoto_xuqin3, R.id.img_takephoto_xuqin4,
            R.id.img_takephoto_xuzhu1, R.id.img_takephoto_xuzhu2,
            R.id.img_takephoto_xuzhu3, R.id.img_takephoto_xuzhu4,
            R.id.img_takephoto_process1, R.id.img_takephoto_process2,
            R.id.img_takephoto_process3, R.id.img_takephoto_process4,
//            R.id.img_takephoto_veterinarians_hezhao,
            R.id.bt_shouyiqianming, R.id.bt_guanfangshouyi})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_ic_id:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
                break;
            case R.id.img_takephoto_baodan:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 1, "A2", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 2, "A3", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 3, "A4", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin3:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 4, "A5", uuid + "_");
                break;
            case R.id.img_takephoto_xuqin4:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 5, "A6", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 6, "A7", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 7, "A8", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu3:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 8, "A9", uuid + "_");
                break;
            case R.id.img_takephoto_xuzhu4:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 9, "A10", uuid + "_");
                break;
            case R.id.img_takephoto_process1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 10, "A11", uuid + "_");
                break;
            case R.id.img_takephoto_process2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 11, "A12", uuid + "_");
                break;
            case R.id.img_takephoto_process3:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 12, "A13", uuid + "_");
                break;
            case R.id.img_takephoto_process4:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 13, "A14", uuid + "_");
                break;
            /*case R.id.img_takephoto_veterinarians_hezhao:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 14, "A15", uuid + "_");
                break;*/
            case R.id.bt_shouyiqianming:
                //养殖户签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A16", uuid + "_");
                    imgPrincipalSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_guanfangshouyi:
                //官方兽医签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A17", uuid + "_");
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
    public void getPageDetil(HarmlessListDetilBean.DataBean data, boolean ifNewAnim) {
        switch (fclfs) {
            case "集中处理":
                concentrationProcess();
                getPresenter().getCollectionLocationList(data.getGlid());
                timePresenter.getTime();
                break;
            case "自行处理":
            case "特殊处理":
                concentrationProcessNot();
                break;
        }
        if (data != null) {
            //照片
            if (data.getPictures() != null) {
                if (!TextUtils.isEmpty(data.getPictures().getA1())) {
//                    Picasso.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA1()).into(photoViewIcId);
                    if (!isDetoryed && ifNewAnim) {
                        Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA1())
                                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                .into(photoViewIcId);
                        iv_none.setVisibility(View.GONE);
                        getPresenter().loadPicture(data.getPictures().getA1(), uuid);
                    }
                }
            }
            //// TODO: 2016/12/29 将来扩展 多种动物种类
            //动物种类
//            List<NetworkAnimType> list = new ArrayList<>();
//            NetworkAnimType type = new NetworkAnimType();
//            type.setFbsdwzl();
//            type.setFsws();
//            type.setFcbs();
//            list.add(type);
//            adapter.addData(list);


            tvName.setText(data.getFxzxm());//畜主姓名
            bean.setFxzxm(data.getFxzxm());

            tvPhone.setText(data.getLxdh());//联系电话
            bean.setLxdh(data.getLxdh());

            tvAddr.setText(data.getFxxdz());//地址
            bean.setFxxdz(data.getFxxdz());

            tvType.setText(data.getFyzclx());//养殖场类型
            bean.setFyzclx(data.getFyzclx());

            xcllNumber = data.getFxcll();//现存栏量数量
            tvNumber.setText(data.getFxcll() + " " + data.getFdw1());//现存栏量
            bean.setFxcll(data.getFxcll());
            bean.setFdw1(data.getFdw1());

            name.setText(data.getXm());//姓名
            bean.setXm(name.getText().toString());

            tvIdCrad.setText(data.getFsfzh());//身份证号
            bean.setFsfzh(data.getFsfzh());

            tvIcCrad.setText(data.getFykth());//一卡通号
            bean.setFykth(data.getFykth());

            tvDate.setText(data.getFsbrq());//申报日期
            bean.setFsbrq(data.getFsbrq());

            ArrayList<String> list = new ArrayList<>();
            fbsdwzl = data.getFbsdwzl();
            list.add(data.getFbsdwzl());
            if (!isDetoryed && ifNewAnim) {
                animTypeModel = new AnimTypeModel(this, list);
                animTypeModel.add(data.getFbsdwzl(), data.getFsws(), data.getFcbs(), true);
            }
            if (isDetoryed) {
                etDeadErbiao.setText(deadErbiao);//畜畜耳标号
                bean.setQDWErBiaoHao(etDeadErbiao.getText().toString());
            } else {
                etDeadErbiao.setText(data.getQDWErBiaoHao());//畜畜耳标号
                bean.setQDWErBiaoHao(data.getQDWErBiaoHao());
            }
        } else {
            Toast.makeText(this, "数据出错", Toast.LENGTH_SHORT).show();
        }
        isDetoryed = false;
    }

    @Override
    public void uploadSuccessful() {
        finish();
    }

    @Override
    public void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBean) {
        concentrationProcess();
        if (dataListBean == null) {
            Toast.makeText(this, "所在行政没有收集点不能集中处理", Toast.LENGTH_SHORT).show();
        } else {
            this.dataListBean = dataListBean;
            List<String> list = new ArrayList<>();
            int selectPosition = 0;
            User user = (User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT);
            for (int i = 0; i < dataListBean.size(); i++) {
                if (user != null && dataListBean.get(i).getFsjdName().equals(user.getFUSEENAME())) {
                    selectPosition = i;
                    collectIndex = i;
                }
                list.add(dataListBean.get(i).getFsjdName());
            }
//            spinnerCollectionLocation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, list));
//            spinnerCollectionLocation.setSelection(selectPosition);

            collectionLocationDialog.setDatas(list);
            String temp= list.get(selectPosition);
            collectIndex = selectPosition;
            spinnerCollectionLocation.setText(temp);

        }
    }

    @Override
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void loadPicture(Bitmap bitmap) {
//        photoViewIcId.setImageBitmap(bitmap);
    }

    @Override
    public void setTime(String time, long longTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(longTime);
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        String evokeTime = format.format(calendar.getTime());
        tvEvokeTime.setText(evokeTime);
    }

    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
