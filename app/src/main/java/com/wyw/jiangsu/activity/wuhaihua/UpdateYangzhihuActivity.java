package com.wyw.jiangsu.activity.wuhaihua;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;
import com.wyw.jiangsu.bean.local.LocalAnimTypeBean;
import com.wyw.jiangsu.bean.upload.UploadFarmUploadBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.ErBiaoDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.test.interfac.IUpdateYangzhiView;
import com.wyw.jiangsu.test.presenter.UpDateYangzhihuPresenter;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.view.photoview.PhotoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wyw.jiangsu.R.id.img_principal_signature;

/**
 * Created by Administrator on 2017/7/12 0012.
 */
public class UpdateYangzhihuActivity extends MVPActivity<UpDateYangzhihuPresenter> implements IUpdateYangzhiView {
    AnimTypeModel model;
    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.name)
    TextView Name;
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
    @BindView(R.id.photo_view_group_photo)
    PhotoView photoViewIcId;
    @BindView(R.id.spinner_deadth_type)
    EditText spinnerDeadthType;
    @BindView(R.id.et_deadth_number)
    EditText etDeadthNumber;
    @BindView(R.id.et_insured_number)
    EditText etInsuredNumber;
    @BindView(R.id.table_row_anim_detil)
    TableRow tableRowAnimDetil;
    @BindView(R.id.tv_row_anim_detil_bottom)
    TextView tvRowAnimDetilBottom;
    @BindView(R.id.tl_anim)
    TableLayout tlAnim;
    @BindView(R.id.bt_add_item)
    Button btAddItem;
    @BindView(R.id.et_dead_erbiao)
    EditText etDeadErbiao;
    @BindView(R.id.bt_erbiao)
    Button btErbiao;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.spinner_deadth_reason)
    TextView spinnerDeadthReason;
    @BindView(R.id.spinner_process_method)
    TextView spinnerProcessMethod;
    @BindView(R.id.bt_shouyiqianming)
    Button btShouyiqianming;
    @BindView(img_principal_signature)
    ImageView imgPrincipalSignature;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    PhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;
    private String tempFileName;
    PhotoViewUtils photoViewUtils;

    TimePresenter timePresenter;
    YangzhihuDetailActivityBean.DataBean databean;
    //上传的实体类
    UploadFarmUploadBean bean = new UploadFarmUploadBean();
    ArrayList<String> erBiaoAnimTypeList;//必须输入耳标的动物种类

    private  String uuid;
    private    String a1;

    private DataSelectDialog clMethodDialog;//处理方式选择dialog
    private DataSelectDialog deadthReasonDialog;//死亡原因选择dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_apply_acctivity);
    }

    @Override
    protected UpDateYangzhihuPresenter createPresenter() {
        return new UpDateYangzhihuPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("病死动物死亡申报修改");
        List<String> list = new ArrayList<>();

        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
        photoViewUtils.initPhotoView(photoViewIcId, () -> {
            tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid);
        });

        //请求数据
        setUserMsg();
        getPresenter().getAnimType();
//        //死亡原因
//        spinnerDeadthReason.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
//                getResources().getStringArray(R.array.deadth_reason)));
//        //处理方式
//        spinnerProcessMethod.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
//                getResources().getStringArray(R.array.process_method1)));

        initDialog();
    }

    private void initDialog() {
        clMethodDialog = new DataSelectDialog(this);
        clMethodDialog.setTitle("选择处理方式");
        clMethodDialog.setDatas(getResources().getStringArray(R.array.process_method1));
        clMethodDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerProcessMethod.setText(data);
            }

            @Override
            public void cancel() {

            }
        });

        spinnerProcessMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clMethodDialog.show();
            }
        });


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

    }

    @Override
    public void bindListener() {
        findViewById(R.id.harmless_btn_submit).setOnClickListener(v -> {
            if (check())
                getPresenter().upload(getData(), uuid);
        });
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

            if (!TextUtils.isEmpty(model.getData().get(0).getDeadthNumber())) {
                new ErBiaoDialog(this, model.getData().get(0).getDeadthNumber(),
                        text -> etDeadErbiao.setText(text), false).show();
            } else {
                Toast.makeText(this, "请输入动物数量", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean check() {
        if (!model.check1()) {
            return false;
        }

        if (imgTakephotoIcId.getDrawable() == null) {
            Toast.makeText(this, "请拍摄身份证和一卡通号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(bean.getGlid())) {
            Toast.makeText(this, "缺少养殖户相关信息,请重新打开页面", Toast.LENGTH_SHORT).show();
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

        //死亡数 不能为空
        if (Integer.parseInt(model.getData().get(0).getDeadthNumber()) <= 0) {
            Toast.makeText(this, "死亡数不能小于0", Toast.LENGTH_SHORT).show();
            return false;
        }


        //死亡数不能大于现存栏数
        if (!TextUtils.isEmpty(model.getData().get(0).getDeadthNumber())) {
            if (Integer.parseInt(model.getData().get(0).getDeadthNumber()) > Integer.parseInt(etNumber.getText().toString())) {
                Toast.makeText(this, "死亡数不能大于现存栏数", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        //处理方式选择不能为空
        if (TextUtils.isEmpty(spinnerProcessMethod.getText())||spinnerProcessMethod.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择处理方式", Toast.LENGTH_SHORT).show();
            return false;
        }


        //处理方式选择不能为空
        if (imgPrincipalSignature.getDrawable()==null) {
            Toast.makeText(this, "请畜主/养殖场负责人签名", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private UploadFarmUploadBean getData() {
        //疑似死亡原因
        if (rbYes.isChecked()) {
            bean.setFysswyy(rbYes.getText().toString());
        } else {
            bean.setFysswyy(rbNo.getText().toString());
            bean.setFsiwh(spinnerDeadthReason.getText().toString());
        }
        //处理方式
        bean.setFclfs(spinnerProcessMethod.getText().toString());
        //耳标号
        bean.setQDWErBiaoHao(etDeadErbiao.getText().toString());

        bean.setFxcll(etNumber.getText().toString());//现存栏量

        LocalAnimTypeBean localAnimTypeBean = model.getData().get(0);
        bean.setFbsdwzl(localAnimTypeBean.getAnimType());//病死动物种类
        bean.setFsws(localAnimTypeBean.getDeadthNumber());//死亡数
        bean.setFcbs(localAnimTypeBean.getInsuredNumber());//参保数
        bean.setLen("A1.jpg,A2.jpg");
        return bean;
    }


    @OnClick({R.id.img_takephoto_ic_id, R.id.bt_shouyiqianming})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_ic_id:
//                tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1",uuid);
                tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
                break;
            case R.id.bt_shouyiqianming:
                //养殖户签名
                new CcWriteDiolog(this, object -> {
                    //保存
//                    PhotoUtils.saveSignPicture((Bitmap) object, "A2",uuid);//名字约定的
//                    imgPrincipalSignature.setImageBitmap((Bitmap) object);
                    PhotoUtils.saveSignPicture((Bitmap) object, "A2", uuid + "_");
                    imgPrincipalSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //        Uri data1 = data.setData();
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(tempFileName, PhotoUtils.DEFAULT_WIDTH, PhotoUtils.DEFAULT_HEIGHT);
            PhotoUtils.saveBitmap(tempFileName, bitmap);
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
//                    photoViewIcId.setImageBitmap(bitmap);
                    Glide.with(this).load(tempFileName).asBitmap().into(photoViewIcId);

                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
//        if (photoViewUtils.onBackPress()) super.onBackPressed();
        finish();
    }


    public void setUserMsg() {
        databean=(YangzhihuDetailActivityBean.DataBean)getIntent().getExtras().getSerializable(Constance.ACTIVITY_DATA);
        if(databean.getPictures1()!=null) {
            a1 = databean.getPictures1().getA1();
            uuid=a1.split("_")[0];
        }
        // 畜主姓名ID
        //private String Glid;

        bean.setGlid(databean.getGlid());
        //联系电话
       // private String lxdh;
        bean.setLxdh(databean.getLxdh());

        // 养殖场类型
       // private String Fyzclx;
        bean.setFyzclx(databean.getFyzclx());

        // 畜主姓名
       // private String Fxzxm;
        bean.setFxzxm(databean.getFxzxm());

        // 现存栏量
        //private String Fxcll;
        bean.setFxcll(databean.getFxcll());

        // 申报日期
        //private String Fsbrq;

        bean.setFsbrq(databean.getFsbrq());
        // 身份证号
        //private String Fsfzh ;
        bean.setFsfzh(databean.getFsfzh());

        //一卡通号
        //private String fykth;
        bean.setFykth(databean.getFykth());

        // 病死动物种类
        //private String Fbsdwzl;
        bean.setFbsdwzl(databean.getFbsdwzl());

        spinnerDeadthType.setText(databean.getFbsdwzl());

        // 死亡数
       // private String Fsws;
        bean.setFsws(databean.getFsws());

        // 参保数
        //private String Fcbs;
        bean.setFcbs(databean.getFcbs());

        //// 耳标号
        //private String QDWErBiaoHao;
        bean.setQDWErBiaoHao(databean.getQDWErBiaoHao());

        //// 疑似死亡原因
        //private String Fysswyy;
        bean.setFysswyy(databean.getFysswyy());

        // 非正常死亡
        //private String Fsiwh;
        bean.setFsiwh(databean.getFsiwh());

        // 处理方式
        //private String Fclfs;
        bean.setFclfs(databean.getFclfs());

        // 签名
       // private String Fqm;
        bean.setFqm(databean.getFqm());

        //地址
        //private String Fxxdz;
        bean.setFxxdz(databean.getFxxdz());

        //// 单位
       // private String Fdw1;
        bean.setFdw1(databean.getFdw1());

        bean.setFsiwh(databean.getFsiwh());

        bean.setFStId(databean.getFStId());

        bean.setXm(databean.getXm());


        tvName.setText(databean.getFxzxm());

        Name.setText(databean.getXm());


        //电话
        tvPhone.setText(databean.getLxdh());

        tvAddr.setText(databean.getFxxdz());//地址

        tvType.setText(databean.getFyzclx());//养殖场类型

        etNumber.setText(databean.getFxcll());//现存栏量
        tvUnit.setText(databean.getFdw1());

        tvIdCrad.setText(databean.getFsfzh());//身份证号

        tvIcCrad.setText(databean.getFykth());//一卡通号

        etDeadthNumber.setText(databean.getFsws());

        etInsuredNumber.setText(databean.getFcbs());

        etDeadErbiao.setText(databean.getQDWErBiaoHao());

        tvDate.setText(databean.getFsbrq());

         //疑似死亡原因
        if (databean.getFysswyy().equals("正常死亡")) {
                rbYes.setChecked(true);

        } else {
                rbNo.setChecked(false);
        }
        uuid=databean.getPictures1().getA1().split("_")[0];

        if (!TextUtils.isEmpty(databean.getPictures1().getA1())) {        //身份证和一卡通照片
            Glide.with(UpdateYangzhihuActivity.this).load(Constance.IMAGE_PATH + databean.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewIcId);
        } else {
            Glide.with(UpdateYangzhihuActivity.this).load("")
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewIcId);
        }

        if (!TextUtils.isEmpty(databean.getPictures1().getA2())) {            //养殖场负责人签名
                Glide.with(UpdateYangzhihuActivity.this).load(Constance.IMAGE_PATH + databean.getPictures1().getA2())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache( true ).into(imgPrincipalSignature);

        } else {
            Glide.with(UpdateYangzhihuActivity.this).load("")
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache( true ).into(imgPrincipalSignature);
        }
    }

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
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
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }



}
