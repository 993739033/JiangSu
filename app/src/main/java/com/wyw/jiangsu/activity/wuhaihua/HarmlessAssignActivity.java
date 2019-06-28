package com.wyw.jiangsu.activity.wuhaihua;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.AnimTypeAdapter;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.NetworkAnimType;
import com.wyw.jiangsu.bean.SupervisionVeterinarianListBean;
import com.wyw.jiangsu.bean.upload.UploadAssignBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessAssignActivity;
import com.wyw.jiangsu.presenter.HarmlessAssignActivityPresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.view.FullPhotoView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 监管兽医指派
 * 上个页面  病死动物死亡申报消息接收 {@link HarmlessApplyMessageAcceptListActivity}
 * 下个页面  监管兽医确认 {@link HarmlessSupervisionVeterinatiansListActivity}
 */
public class HarmlessAssignActivity extends MVPActivity<HarmlessAssignActivityPresenter> implements IHarmlessAssignActivity, TimeSelectDialog.TimePickListener1 {

    @BindView(R.id.tv_name)
    EditText tvName;
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
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(R.id.et_dead_erbiao)
    EditText etDeadErbiao;
    @BindView(R.id.tv_process_method)
    TextView tvProcessMethod;
    @BindView(R.id.spinner_assign)
    TextView spinnerAssign;
    @BindView(R.id.img_zhanzhang)
    ImageView imgZhanzhang;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    FullPhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;

    AnimTypeAdapter adapter;
    PhotoViewUtils photoViewUtils;
    String tempFileName;

    UploadAssignBean bean;
    List<SupervisionVeterinarianListBean.DataListBean> assignList;//监管兽医
    List<CollectionListBean.DataListBean> sjdList;//监管兽医

    String uuid = UUID.randomUUID().toString();
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rb_survey_sm)
    RadioButton rbSurveySm;
    @BindView(R.id.rb_survey_sjd)
    RadioButton rbSurveySjd;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.time_ys)
    TextView time_ys;
    @BindView(R.id.lin_kyfs)
    LinearLayout lin_kyfs;
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;
    private DataSelectDialog jgsyDialog;//监管兽医选择
    private DataSelectDialog sjdDialog;//收集点选择
    private TimeSelectDialog timeSelectDialog;
    private String glid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_assign);
        ButterKnife.bind(this);
    }

    @Override
    protected HarmlessAssignActivityPresenter createPresenter() {
        return new HarmlessAssignActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("官方兽医确认");
        bean = new UploadAssignBean();
        timeSelectDialog = new TimeSelectDialog(this, null);
        timeSelectDialog.setmTimePickListener1(this);
        adapter = new AnimTypeAdapter(new ArrayList<>());
        recyclerAnimType.setAdapter(adapter);
        recyclerAnimType.setLayoutManager(new LinearLayoutManager(this));

        String fstId = getIntent().getStringExtra(Constance.ACTIVITY_DATA);
        getPresenter().getPageDetil(fstId);
        getPresenter().getSupervisionVeterinariansList();
        bean.setGlid(fstId);

        initDialog();
    }


    private void initDialog() {
        jgsyDialog = new DataSelectDialog(this);
        jgsyDialog.setTitle("选择监管兽医");
        jgsyDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerAssign.setText(data);
            }

            @Override
            public void cancel() {

            }
        });


        sjdDialog = new DataSelectDialog(this);
        sjdDialog.setTitle("选择收集点");
        sjdDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerAssign.setText(data);
            }

            @Override
            public void cancel() {

            }
        });

        spinnerAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbSurveySm.isChecked())
                    jgsyDialog.show();
                if (rbSurveySjd.isChecked())
                    sjdDialog.show();
            }
        });

    }


    @Override
    public void bindListener() {
        harmlessBtnSubmit.setOnClickListener(v -> {
//            RxBus.getInstance().post(new RefreshBus(RefreshBus.Text));
            if (check()) {
                showNormalDialog(uuid);
            }
        });

        //收集点勘验
        rbSurveySjd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (TextUtils.isEmpty(glid)) {
                    Toast.makeText(HarmlessAssignActivity.this, "基础信息未获取", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isChecked) {
                    rl_time.setVisibility(View.VISIBLE);
                    tv7.setText("病死畜禽移送收集点");
                    spinnerAssign.setText("请选择");
                    time_ys.setText("");
                    getPresenter().getCollectionLocationList(glid);
                }
            }
        });

        //上门勘验
        rbSurveySm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rl_time.setVisibility(View.GONE);
                    tv7.setText("监管兽医指派");
                    spinnerAssign.setText("请选择");
                    time_ys.setText("");
                    getPresenter().getSupervisionVeterinariansList();
                }
            }
        });
    }

    private void showNormalDialog(String uuid) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessAssignActivity.this);
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

    public boolean check() {
        if (assignList == null || assignList.size() == 0) {
            Toast.makeText(this, "没有可指派兽医,请重新打开", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (spinnerAssign.getText().toString().equals("请选择")) {
            if (rbSurveySjd.isChecked()) {
                Toast.makeText(this, "请选择收集点", Toast.LENGTH_SHORT).show();
                return false;
            }
            Toast.makeText(this, "请选择指派兽医", Toast.LENGTH_SHORT).show();
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
        //        if (imgTakephotoIcId.getDrawable() == null) {
//            Toast.makeText(this, "请拍摄身份证和一卡通号", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if (imgZhanzhang.getDrawable() == null) {
            Toast.makeText(this, "兽医人员签字不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rbSurveySjd.isChecked())
            if (TextUtils.isEmpty(time_ys.getText().toString())) {
                Toast.makeText(this, "未选择移送时间", Toast.LENGTH_SHORT).show();
                return false;
            }
        return true;
    }

    public UploadAssignBean getData() {

//        //设置监管兽医
        if (rbSurveySm.isChecked()) {
            bean.setZPID(assignList.get(jgsyDialog.getNowIndex()).getFStId());
            bean.setZPR(assignList.get(jgsyDialog.getNowIndex()).getFuName());
        }
        //设置收集点
        if (rbSurveySjd.isChecked()) {
            bean.setZPID(sjdList.get(sjdDialog.getNowIndex()).getFStId());
            bean.setZPR(sjdList.get(sjdDialog.getNowIndex()).getFsjdName());
        }

        bean.setXm(name.getText().toString());
        bean.setLen("A1.jpg");
        if (rbSurveySm.isChecked()) {
            bean.setFSm3(rbSurveySm.getText().toString());
        }
        if (rbSurveySjd.isChecked()) {
            bean.setFSm3(rbSurveySjd.getText().toString());
            bean.setFSm4(time_ys.getText().toString());
        }
        return bean;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(tempFileName));
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                    photoViewIcId.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    @OnClick({R.id.bt_shouyiqianming, R.id.time_ys})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_ic_id:
//                tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1",uuid);
                break;
            case R.id.bt_shouyiqianming:
                //乡镇站签字
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A1", uuid + "_");
                    if (rbSurveySjd.isChecked())
                        PhotoUtils.saveSignPicture((Bitmap) object, "A17", uuid + "_");
                    imgZhanzhang.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.time_ys:
                timeSelectDialog.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        if (photoViewUtils.onBackPress()) super.onBackPressed();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timeSelectDialog != null) {
            timeSelectDialog.dismiss();
            timeSelectDialog = null;
        }
    }

    @Override
    public void getPageDetil(HarmlessListDetilBean.DataBean data) {
        if (data != null) {
            if (data.getPictures() != null && !TextUtils.isEmpty(data.getPictures().getA1())) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA1()).into(photoViewIcId);
            }
            //// TODO: 2016/12/29 将来扩展 多种动物种类
            //动物种类
            List<NetworkAnimType> list = new ArrayList<>();
            NetworkAnimType type = new NetworkAnimType();
            type.setFbsdwzl(data.getFbsdwzl());
            type.setFsws(data.getFsws());
            type.setFcbs(data.getFcbs());
            list.add(type);
            adapter.addData(list);
            etDeadErbiao.setText(data.getQDWErBiaoHao());//畜畜耳标号
            tvDate.setText(data.getFsbrq());//申报日期
            tvName.setText(data.getFxzxm());//畜主姓名
            tvAddr.setText(data.getFxxdz());//地址
            tvType.setText(data.getFyzclx());//养殖场类型
            tvNumber.setText(data.getFxcll() + " " + data.getFdw1());//现存栏量
            tvIdCrad.setText(data.getFsfzh());//身份证号
            name.setText(data.getXm());//姓名
            tvIcCrad.setText(data.getFykth());//一卡通号
            tvProcessMethod.setText(data.getFclfs());//处理方式
            glid = data.getGlid();
            if (data.getFclfs().equals("集中处理")) {
                lin_kyfs.setVisibility(View.VISIBLE);
            } else {
                lin_kyfs.setVisibility(View.GONE);
                rl_time.setVisibility(View.GONE);
                tv7.setText("监管兽医指派");
                spinnerAssign.setText("请选择");
                time_ys.setText("");
                getPresenter().getSupervisionVeterinariansList();
            }
//            rbSurveySjd.setChecked(true);
        } else {
            Toast.makeText(this, "数据出错", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getSupervisonVeterinatianList(List<SupervisionVeterinarianListBean.DataListBean> dataBean) {
        assignList = dataBean;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < dataBean.size(); i++) {
            list.add(dataBean.get(i).getFuName());
        }
//        spinnerAssign.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
//                list));

        jgsyDialog.setDatas(list);
    }

    @Override
    public void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBean) {
        sjdList = dataListBean;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < dataListBean.size(); i++) {
            list.add(dataListBean.get(i).getFsjdName());
        }
//        spinnerAssign.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
//                list));

        sjdDialog.setDatas(list);
    }

    @Override
    public void uploadSuccessful() {
        finish();
    }

    @Override
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }


    @Override
    public void getTime(String year, String month, String day, String hour) {
        time_ys.setText(year + "-" + month + "-" + day);
    }
}
