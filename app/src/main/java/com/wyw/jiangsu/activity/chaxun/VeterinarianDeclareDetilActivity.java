package com.wyw.jiangsu.activity.chaxun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.bean.upload.UploadReplaceApplyBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IVeterinarianDeclareDetilActivity;
import com.wyw.jiangsu.presenter.VeterinarianDeclareDetilActivityPresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 乡镇站代申报详情
 * Created by mnkj004 on 2017/7/27.
 */

public class VeterinarianDeclareDetilActivity extends MVPActivity<VeterinarianDeclareDetilActivityPresenter> implements IVeterinarianDeclareDetilActivity, View.OnClickListener {


    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_type)
    EditText tvType;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_idCrad)
    EditText tvIdCrad;
    @BindView(R.id.tv_icCrad)
    EditText tvIcCrad;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewGroupPhoto;
    @BindView(R.id.et_die_animal_type)
    EditText etDieAnimalType;
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
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.photo_view_xuzhu1)
    ImageView photoViewXuzhu1;
    @BindView(R.id.photo_view_xuzhu2)
    ImageView photoViewXuzhu2;
    @BindView(R.id.photo_view_xuzhu3)
    ImageView photoViewXuzhu3;
    @BindView(R.id.photo_view_xuzhu4)
    ImageView photoViewXuzhu4;
    @BindView(R.id.et_die_cause)
    TextView etDieCause;
    @BindView(R.id.et_die_cause2)
    TextView etDieCause2;
    @BindView(R.id.et_process_mode)
    TextView etProcessMode;
    @BindView(R.id.et_process_mode2)
    TextView etProcessMode2;
    @BindView(R.id.et_collection_location)
    TextView etCollectionLocation;
    @BindView(R.id.tv_evoke_time)
    TextView tvEvokeTime;
    @BindView(R.id.photo_view_keep_file1)
    ImageView photoViewKeepFile1;
    @BindView(R.id.photo_view_keep_file2)
    ImageView photoViewKeepFile2;
    @BindView(R.id.photo_view_keep_file3)
    ImageView photoViewKeepFile3;
    @BindView(R.id.photo_view_keep_file4)
    ImageView photoViewKeepFile4;
    @BindView(R.id.lin_keep_file)
    LinearLayout linKeepFile;
    @BindView(R.id.img_principal_signature)
    ImageView imgPrincipalSignature;
    @BindView(R.id.img_veterinarian_signature)
    ImageView imgVeterinarianSignature;
    @BindView(R.id.bt_veterinarian)
    Button btVeterinarian;
    @BindView(R.id.img_guanfangshouyi)
    ImageView imgGuanfangshouyi;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.name)
    EditText name;
    private WuHaiHuaCXbean.DataListBean dataListBean;
    private static String fstid;
    private AgencyDeclareDetilActivityBean.DataBean dateben;
    private String uuid;
    private VeterinarianDeclareDetilActivityPresenter presenter;
    private UploadReplaceApplyBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_veterinarian_apply);
        ButterKnife.bind(this);
    }

    @Override
    public void bindData() {
        setTitle("乡镇站代理申报详情");
        bean = new UploadReplaceApplyBean();
        presenter = getPresenter();
        Intent intent = getIntent();
        dataListBean = (WuHaiHuaCXbean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
        fstid = dataListBean.getFStId();
        int Fstid = Integer.valueOf(dataListBean.getFStId());
        getPresenter().getFarmDeclare("HT_JGSYDSB", Fstid);
    }

    @Override
    public void bindListener() {
        btVeterinarian.setOnClickListener(this);
        harmlessBtnSubmit.setOnClickListener(this);
        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                showNormalDialog(uuid);
            }
        });

    }
    private void showNormalDialog(String uuid){
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(VeterinarianDeclareDetilActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getData(),uuid);
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
    private boolean check() {
//        if (imgPrincipalSignature.getDrawable() == null) {
//            showToast("畜主/养殖场负责人没有签名");
//            return false;
//        }
//
//        if (imgGuanfangshouyi.getDrawable() == null) {
//            showToast("兽医人员没有签字");
//            return false;
//        }

        if (imgVeterinarianSignature.getDrawable() == null) {
            showToast("乡镇站确认请签字");
            return false;
        }
        return true;
    }


    @Override
    public void uploadSuccessful() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }


    private UploadReplaceApplyBean getData() {
        //姓名
        bean.setXm(name.getText().toString());
        //疑似死亡原因
        bean.setFysswyy(dateben.getFysswyy());
        if (etProcessMode.getText().toString() != null) {
            //处理方式1
            bean.setFclfs((String) etProcessMode.getText());
        } else {
            //處理方式2
            bean.setFclfs2((String) etProcessMode2.getText());
        }
        //移送时间
        bean.setFyssj(tvEvokeTime.getText().toString());
        //耳标号
        bean.setQDWErBiaoHao(etDeadErbiao.getText().toString());
        bean.setFsjd(dateben.getFsjd());
        bean.setFStId(dateben.getFStId());
        bean.setFsjdID(dateben.getFsjdID());

        //经纬度
        if (!TextUtils.isEmpty(dateben.getJD())) {
            bean.setJD(dateben.getJD());
        } else {
            bean.setJD("0.0");
        }
        if (!TextUtils.isEmpty(dateben.getWD())) {
            bean.setWD(dateben.getWD());
        } else {
            bean.setWD("0.0");
        }

        bean.setFxzxm(dateben.getFxzxm());
        bean.setLxdh(dateben.getLxdh());
        bean.setDZ(dateben.getDZ());
        bean.setFyzclx(dateben.getFyzclx());
        bean.setFxcll(dateben.getFxcll());
        bean.setFsfzh(dateben.getFsfzh());
        bean.setFykth(dateben.getFykth());
        bean.setFsbrq(dateben.getFsbrq());
        bean.setGlid(dateben.getGlid());
        bean.setFxxdz(dateben.getFxxdz());
        bean.setFdw1(dateben.getFdw1());

        bean.setFbsdwzl(etDieAnimalType.getText().toString());
        bean.setFsws(etDeadthNumber.getText().toString());
        bean.setFcbs(etInsuredNumber.getText().toString());
        bean.setLen("A1.jpg,A2.jpg,A3.jpg,A4.jpg,A5.jpg,A6.jpg,A7.jpg,A8.jpg,A9.jpg,A10.jpg,A11.jpg,A12.jpg,A13.jpg,A14.jpg,A15.jpg,A16.jpg,A17.jpg");
        bean.setFSm2("2");
        return bean;
    }

    @Override
    protected VeterinarianDeclareDetilActivityPresenter createPresenter() {
        return new VeterinarianDeclareDetilActivityPresenter(this);
    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {

    }


    @Override
    public void getCollectionLocationList(List<CollectionListBean.DataListBean> dataListBeen) {

    }

    @Override
    public void setData(AgencyDeclareDetilActivityBean.DataBean dataEntity) {
        settex(dataEntity);

    }

    //    VeterinarianDeclareBean dataEntity
    public void settex(AgencyDeclareDetilActivityBean.DataBean dataEntity) {
        dateben = dataEntity;
        uuid = dateben.getPictures1().getA1().split("_")[0];
        tvName.setText(dataEntity.getFxzxm()); //养殖场名称
        tvPhone.setText(dataEntity.getLxdh());  //联系电话
        tvAddr.setText(dataEntity.getFxxdz());      //地址
        tvType.setText(dataEntity.getFyzclx());     //养殖场类型
        etNumber.setText(dataEntity.getFxcll());    //现存栏量
        tvIdCrad.setText(dataEntity.getFsfzh());    //身份证号
        name.setText(dataEntity.getXm());    //姓名
        tvIcCrad.setText(dataEntity.getFykth());    //一卡通号
        tvDate.setText(dataEntity.getFsbrq());      //申报日期
        etDieAnimalType.setText(dataEntity.getFbsdwzl());//病死动物类型
        etDeadthNumber.setText(dataEntity.getFsws());    //死亡数
        etInsuredNumber.setText(dataEntity.getFcbs()); //参保数
        etDeadErbiao.setText(dataEntity.getQDWErBiaoHao()); //耳标号

        etProcessMode.setText(dataEntity.getFclfs());       //处理方式
        etProcessMode2.setText(dataEntity.getFclfs2());     //处理方式2
        if (dataEntity.getFclfs().equals("集中处理")){
            linKeepFile.setVisibility(View.GONE);
        }

        etCollectionLocation.setText(dataEntity.getFsjd()); //病死畜禽移送收集点
        tvEvokeTime.setText(dataEntity.getFyssj());         //移送时间

        etDieCause.setText(dataEntity.getFysswyy());//疑似死亡原因
        etDieCause2.setText(dataEntity.getFsiwh()); //非正常死亡
        //图片
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA1())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewGroupPhoto);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA2())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA2())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewBaodan);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA3())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA3())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin1);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA4())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA4())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin2);
        }

        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA5())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA5())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin3);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA6())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA6())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin4);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA7())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA7())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu1);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA8())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA8())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu2);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA9())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA9())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu3);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA10())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA10())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu4);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA11())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA11())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile1);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA12())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA12())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile2);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA13())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA13())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile3);
        }


        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA14())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA14())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile4);
        }

        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA15())) {
            //养殖场签名
            String yangFile = jieQu(dataEntity.getPictures1().getA15());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA15())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgPrincipalSignature);
            }
        }
        //兽医签名
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA16())) {
            String file = jieQu(dataEntity.getPictures1().getA16());
            if (file.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA16())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgGuanfangshouyi);
            }
        }
    }

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_veterinarian:
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A17", uuid + "_");
                    imgVeterinarianSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
        }
    }
}
