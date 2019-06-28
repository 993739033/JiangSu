package com.wyw.jiangsu.activity.wuhaihua;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.bean.upload.UploadFarmUploadBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.ErBiaoDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessApplyActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.HarmlessApplyActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;
import com.wyw.jiangsu.view.photoview.PhotoView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.wyw.jiangsu.R.id.img_principal_signature;
import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.compressBmpToFile;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;


/**
 * 无害化申报(养殖场)
 * 下一步 病死动物死亡申报消息接收列表 {@link HarmlessApplyMessageAcceptListActivity}
 */
public class HarmlessApplyActivity extends MVPActivity<HarmlessApplyActivityPresenter> implements IHarmlessApplyActivity, ITime, PhotoViewModel.OnClickListener {

    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
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
    ImageView photoViewIcId;
    @BindView(R.id.iv_none)
    ImageView iv_none;
    @BindView(R.id.spinner_deadth_type)
    EditText spinnerDeadthType;
    @BindView(R.id.et_deadth_number)
    EditText etDeadthNumber;
    @BindView(R.id.et_insured_number)
    EditText etInsuredNumber;
    @BindView(R.id.table_row_anim_detil)
    TableRow tableRowAnimDetil;
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
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    PhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;
    @BindView(R.id.name)
    TextView name;
    PhotoViewUtils photoViewUtils;

    TimePresenter timePresenter;
    private PhotoViewModel ImageViewModel;
    private String currentPhotoFile;
    private String uuid;
    //上传的实体类
    UploadFarmUploadBean bean = new UploadFarmUploadBean();
    AnimTypeModel model;
    ArrayList<String> erBiaoAnimTypeList;//必须输入耳标的动物种类
    private boolean tLFocusable = true;
    private boolean isDestoryed;
    private String sws;
    private String cbs;
    private String animtype;
    private int flag = 1;
    private String num;


    private DataSelectDialog clMethodDialog;//处理方式选择dialog
    private DataSelectDialog deadthReasonDialog;//死亡原因选择dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_apply_acctivity);
        ButterKnife.bind(this);
    }

    @Override
    protected HarmlessApplyActivityPresenter createPresenter() {
        return new HarmlessApplyActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("病死动物死亡申报");
        uuid = UUID.randomUUID().toString();
        Log.e("uuid", "uuid bindData " + uuid);

        timePresenter = new TimePresenter(this);
        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);

        //请求数据
        getPresenter().getAnimType();
        timePresenter.getTime();

        initDialog();//初始化选择弹窗
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

    private void showNormalDialog(String uuid) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessApplyActivity.this);
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
        findViewById(R.id.harmless_btn_submit).setOnClickListener(v -> {
            if (check()) showNormalDialog(uuid);
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
            //这里改了
            if (!TextUtils.isEmpty(model.getData().get(0).getDeadthNumber())) {
                new ErBiaoDialog(this, model.getData().get(0).getDeadthNumber(),
                        text -> etDeadErbiao.setText(text), false).show();
            } else {
                Toast.makeText(this, "请输入动物数量", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //加载本地签名图片
        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
            if (file.getName().contains(uuid + "_A2")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgPrincipalSignature);
            }
            if (file.getName().contains(uuid + "_A1")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(photoViewIcId);
            }
        }


        //对拍照控件 --> 加载照片,恢复拍照功能
        String nameManager1 = uuid + "_A1";
        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewIcId, REQUEST_CODE_START, nameManager1, getWuHaiHuaDir(), this);
        if (isDestoryed) {
            ArrayList<String> list = new ArrayList<>();
            list.add(animtype);
            model = new AnimTypeModel(this, list);
            model.add(animtype, sws, cbs, true);
        }
        if (flag < 2) getPresenter().getUserDetil();
        flag++;
        initInfo();
    }

    private void initInfo() {
        tvName.setText(bean.getFxzxm());//畜主姓名
        tvAddr.setText(bean.getFxxdz());//地址
        tvPhone.setText(bean.getLxdh());
        tvType.setText(bean.getFyzclx());//养殖场类型
        if (isDestoryed) etNumber.setText(num);//现存栏量
        tvUnit.setText(bean.getFdw1());
        tvIdCrad.setText(bean.getFsfzh());//身份证号
        tvIcCrad.setText(bean.getFykth());//一卡通号
        name.setText(bean.getXm());//姓名
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uuid = savedInstanceState.getString("uuid");
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
        bean = (UploadFarmUploadBean) savedInstanceState.getSerializable("bean");
        sws = savedInstanceState.getString("sws");
        cbs = savedInstanceState.getString("cbs");
        animtype = savedInstanceState.getString("animtype");
        flag = savedInstanceState.getInt("flag");
        num = savedInstanceState.getString("num");
        flag++;
        isDestoryed = true;
        tLFocusable = false;
    }

    public String getSwsOrCbsFromLayout(int times) {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("uuid", uuid);
        Log.e("uuid", "uuid onSaveInstanceState " + uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        outState.putInt("flag", flag);
        bean.setFxcll(etNumber.getText().toString());
        outState.putSerializable("bean", bean);
        outState.putString("sws", getSwsOrCbsFromLayout(1));
        outState.putString("cbs", getSwsOrCbsFromLayout(2));
        String animTypeFromLayout = getAnimTypeFromLayout();
        outState.putString("animtype", animTypeFromLayout);
        outState.putString("num", etNumber.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private boolean check() {
        if (!model.check1()) {
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
        if (!new File(PhotoUtils.getWuHaiHuaDir(), uuid + "_" + "A1.jpg").exists() || photoViewIcId.getDrawable() == null) {
            Toast.makeText(this, "请拍摄身份证和一卡通号", Toast.LENGTH_SHORT).show();
            return false;
        }
        /*if (!new File(PhotoUtils.getWuHaiHuaDir(), uuid + "_" + "A2.jpg").exists() || imgPrincipalSignature.getDrawable() == null) {
            Toast.makeText(this, "请进行养殖场签字", Toast.LENGTH_SHORT).show();
            return false;
        }*/

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
        if (TextUtils.isEmpty(getSwsOrCbsFromLayout(2))) {
            Toast.makeText(this, "死亡数不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        //死亡数 不能为空
        if (Integer.parseInt(getSwsOrCbsFromLayout(2)) <= 0) {
            Toast.makeText(this, "死亡数必须大于0", Toast.LENGTH_SHORT).show();
            return false;
        }


        //死亡数不能大于现存栏数
        if (!TextUtils.isEmpty(getSwsOrCbsFromLayout(3))) {
            if (Integer.parseInt(getSwsOrCbsFromLayout(3)) > Integer.parseInt(etNumber.getText().toString())) {
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
        bean.setXm(name.getText().toString());  //姓名

        bean.setFbsdwzl(getAnimTypeFromLayout());//病死动物种类
        bean.setFsws(getSwsOrCbsFromLayout(2));//死亡数
        bean.setFcbs(getSwsOrCbsFromLayout(3));//参保数

        bean.setLen("A1.jpg,A2.jpg");
        return bean;
    }


    @OnClick({R.id.img_takephoto_ic_id, R.id.bt_shouyiqianming})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_ic_id:
//                tempFileName = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1",uuid);
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
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
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                    photoViewIcId.setImageBitmap(bitmap);
                    iv_none.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (parent.getVisibility() == View.VISIBLE) {
            //PhotoViewUtils的实例调用
            photoViewUtils.onBackPress();
            return;
        }
        finish();
    }

    @Override
    public void setUserMsg(UserDetilBean.Data data) {
        tvName.setText(data.getFFarmName());//畜主姓名
        bean.setFxzxm(data.getFFarmName());
        bean.setGlid(data.getFStId());

        //电话
        tvPhone.setText(data.getFPhone());
        bean.setLxdh(data.getFPhone());

        tvAddr.setText(data.getFCityAdd());//地址
        bean.setFxxdz(data.getFCityAdd());

        tvType.setText(data.getFyzcType());//养殖场类型
        bean.setFyzclx(data.getFyzcType());
        etNumber.setText(data.getFherdsScale());//现存栏量
        tvUnit.setText(data.getFSmemo1());
        bean.setFxcll(data.getFherdsScale());
        bean.setFdw1(data.getFSmemo1());

        tvIdCrad.setText(data.getFPLegalCardId());//身份证号
        bean.setFsfzh(data.getFPLegalCardId());

        name.setText(data.getFLegalPerson());//姓名
        bean.setXm(data.getFLegalPerson());

        tvIcCrad.setText(data.getYKTH());//一卡通号
        bean.setFykth(data.getYKTH());

        //身份证和一卡通号的照片
        //下载图片

        if (!TextUtils.isEmpty(data.getImg())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response response = new OkHttpClient.Builder().build().newCall(new Request.Builder().
                                url(Constance.IMAGE_PATH + data.getImg()).build()).execute();
                        File file = new File(PhotoUtils.getWuHaiHuaDir(),
                                uuid + "_A1.jpg");
                        Log.e("uuid", "uuid new Thread bind bitmap " + uuid);
                        byte[] responseBytes = response.body().bytes();
                        final Bitmap bitmap = BitmapFactory.decodeByteArray(responseBytes, 0, (int) response.body().contentLength());
                        FileOutputStream ostream = null;
                        if (response.body().contentLength() > 102400) {
                            try {
                                ostream = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                                compressBmpToFile(file.getPath());
                                ostream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
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
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              try {
                                                  if (bitmap != null) {
                                                      Log.e("uuid", "uuid runOnUiThread " + uuid);
                                                      if (!isDestoryed && tLFocusable) {
                                                          photoViewIcId.setImageBitmap(bitmap);
                                                      }
                                                  }
                                              } catch (Exception e) {
                                                  e.printStackTrace();
                                              }
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
        if (!isDestoryed) {
            model = new AnimTypeModel(this, animType);
        }
        isDestoryed = false;
    }

    @Override
    public void uploadsuccess() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void setTime(String time, long longTime) {
        String time1 = time;
        //long --> y m r h
        tvDate.setText(time);//申报时间
        bean.setFsbrq(time);
    }

    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
