package com.wyw.jiangsu.activity.wuhaihua;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.HarmlessHomeVeterinarianAdapter;
import com.wyw.jiangsu.adapter.HarmlessStockTotalAdapter;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.upload.UploadHomeConfimBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessHomeVeterinarianConfirmActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.HarmlessHomeVeterinarianConfirmActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wyw.jiangsu.R.id.bt_collection_person_signature;
import static com.wyw.jiangsu.R.id.img_cold_storage_person_signature;
import static com.wyw.jiangsu.R.id.img_collection_person_signature;
import static com.wyw.jiangsu.R.id.img_veterinarians_signature;
import static com.wyw.jiangsu.R.id.photo_view_storage1;
import static com.wyw.jiangsu.R.id.rb_yes_accord;
import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;

/**
 * 驻场兽医确认
 * 上个页面 驻场兽医列表 {@link HarmlessHomeVeterinatiansListActivity}
 * 下一个页面 无害化处理厂集中处理列表 {@link HarmlessConcentrateProcessListActivity}
 */
public class HarmlessHomeVeterinarianConfirmActivity extends MVPActivity<HarmlessHomeVeterinarianConfirmActivityPresenter>
        implements IHarmlessHomeVeterinarianConfirmActivity, ITime, PhotoViewModel.OnClickListener {


    @BindView(R.id.tv_collection_car)
    TextView tvCollectionCar;
    @BindView(R.id.tv_collection_time)
    TextView tvCollectionTime;
    @BindView(R.id.tv_collection_person)
    TextView tvCollectionPerson;
    @BindView(R.id.tv_collection_enter_time)
    TextView tvCollectionEnterTime;
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(R.id.recycler_sum)
    RecyclerView recyclerSum;
    @BindView(R.id.img_takephoto_storage1)
    ImageView imgTakephotoStorage1;
    @BindView(photo_view_storage1)
    ImageView photoViewStorage1;
    @BindView(R.id.img_takephoto_storage2)
    ImageView imgTakephotoStorage2;
    @BindView(R.id.photo_view_storage2)
    ImageView photoViewStorage2;
    @BindView(img_collection_person_signature)
    ImageView imgCollectionPersonSignature;
    @BindView(img_veterinarians_signature)
    ImageView imgVeterinariansSignature;
    @BindView(img_cold_storage_person_signature)
    ImageView imgColdStoragePersonSignature;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(rb_yes_accord)
    RadioButton rbYesAccord;
    @BindView(R.id.rb_no_accord)
    RadioButton rbNoAccord;
    @BindView(R.id.rg_accord)
    RadioGroup rgAccord;
    @BindView(R.id.et_confirm_weight)
    EditText etConfirmWeight;
    @BindView(R.id.iv_none_1)
    ImageView iv_none_1;
    @BindView(R.id.iv_none_2)
    ImageView iv_none_2;

    private PhotoViewModel ImageViewModel;
    private String currentPhotoFile;
    //收集点
    HarmlessHomeVeterinarianAdapter adapterCollection;
//    TestAdadpter adapterCollection;
    //合计
    HarmlessStockTotalAdapter totalAdapter;

    TimePresenter timePresenter;

    UploadHomeConfimBean bean;
    private String uuid = UUID.randomUUID().toString();
    private int totalSum = 0;//获取到总数用于判断
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_home_veterinarian_confirm);
    }

    @Override
    protected HarmlessHomeVeterinarianConfirmActivityPresenter createPresenter() {
        return new HarmlessHomeVeterinarianConfirmActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("无害化处理中心驻场兽医入场确认");
        bean = new UploadHomeConfimBean();
        timePresenter = new TimePresenter(this);

        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));
        adapterCollection = new HarmlessHomeVeterinarianAdapter();
        recyclerAnimType.setHasFixedSize(true);
        recyclerAnimType.setNestedScrollingEnabled(false);
        recyclerAnimType.setAdapter(adapterCollection);

        recyclerSum.setLayoutManager(new FullyLinearLayoutManager(this));
        totalAdapter = new HarmlessStockTotalAdapter(new ArrayList<>());
        recyclerSum.setHasFixedSize(true);
        recyclerSum.setNestedScrollingEnabled(false);
        recyclerSum.setAdapter(totalAdapter);

//        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
//        photoViewUtils.initPhotoView(photoViewStorage1, () -> currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_"));
//        photoViewUtils.initPhotoView(photoViewStorage2, () -> currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 1, "A2", uuid + "_"));

        String fstId = getIntent().getStringExtra(Constance.ACTIVITY_DATA);
        bean.setGlid(fstId);
        getPresenter().getPageDetil(fstId);
        timePresenter.getTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
            if (file.getName().contains(uuid + "_A3")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgCollectionPersonSignature);
            }else if (file.getName().contains(uuid + "_A4")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgVeterinariansSignature);
            }else if (file.getName().contains(uuid + "_A5")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgColdStoragePersonSignature);
            }
        }
        String nameManager1 = uuid + "_A1";
        String nameManager2 = uuid + "_A2";
        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewStorage1, REQUEST_CODE_START, nameManager1, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewStorage2, REQUEST_CODE_START+1, nameManager2, getWuHaiHuaDir(), this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String weight = savedInstanceState.getString("weight");
        etConfirmWeight.setText(weight);
        uuid = savedInstanceState.getString("uuid");
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("weight",etConfirmWeight.getText().toString()==null?"":etConfirmWeight.getText().toString());
        outState.putString("uuid", uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void bindListener() {
        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                if (rbYesAccord.isChecked()) {
                    Intent intent = new Intent(this, HarmlessConcentrateProcessActivity.class);
                    intent.putExtra(Constance.ACTIVITY_DATA, getData());
//                    intent.putExtra(Constance.ACTIVITY_TYPE, bean.getId());
                    startActivityForResult(intent, Constance.ACTIVITY_REQUEST_CODE);
                } else {
                    showNormalDialog(uuid);
                }
            }
        });
    }

    private void showNormalDialog(String uuid){
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessHomeVeterinarianConfirmActivity.this);
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

    public boolean check() {
        //至少要有一张照片
        if (!new File(PhotoUtils.getWuHaiHuaDir(), uuid + "_" + "A1.jpg").exists() && !new File(PhotoUtils.getWuHaiHuaDir(), uuid + "_" + "A2.jpg").exists()) {
            Toast.makeText(this, "至少要有一张收集入库的照片", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imgCollectionPersonSignature.getDrawable() == null) {
            Toast.makeText(this, "收集人签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (imgVeterinariansSignature.getDrawable() == null) {
            Toast.makeText(this, "驻场兽医签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!rbYesAccord.isChecked() && imgColdStoragePersonSignature.getDrawable() == null) {
            Toast.makeText(this, "无害化处理中心冷库人员签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public UploadHomeConfimBean getData() {
        bean.setSFCL(rbYesAccord.isChecked() ? rbYesAccord.getText().toString() :
                rbNoAccord.getText().toString());
        bean.setQRZL(TextUtils.isEmpty(etConfirmWeight.getText().toString()) ? "0" : etConfirmWeight.getText().toString());
        bean.setLen("A1.jpg,A2.jpg,A3.jpg,A4.jpg,A5.jpg");
        return bean;
    }

    @OnClick({R.id.img_takephoto_storage1, R.id.img_takephoto_storage2,
            R.id.bt_collection_person_signature,
            R.id.bt_veterinarians_signature, R.id.bt_cold_storage_person_signature})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_storage1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
                break;
            case R.id.img_takephoto_storage2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 1, "A2", uuid + "_");
                break;
            case bt_collection_person_signature:
                //收集人员签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A3", uuid + "_");//名字约定的
                    imgCollectionPersonSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_veterinarians_signature:
                //驻场兽医签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A4", uuid + "_");//名字约定的
                    imgVeterinariansSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_cold_storage_person_signature:
                //冷库人员
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A5", uuid + "_");//名字约定的
                    imgColdStoragePersonSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Uri data1 = data.setData();
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_REQUEST_CODE) {
                uploadSuccessful();
                return;
            }
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
            PhotoUtils.saveBitmap(currentPhotoFile, bitmap);
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                    photoViewStorage1.setImageBitmap(bitmap);
                    iv_none_1.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 1:
                    photoViewStorage2.setImageBitmap(bitmap);
                    iv_none_2.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
//        if (photoViewUtils.onBackPress()) super.onBackPressed();
        finish();
    }

    @Override
    public void getPageDetil(HarmlessListDetilBean.DataBean data) {
        if (data != null) {
            //// TODO: 2016/12/29 将来扩展 多种动物种类
            //动物种类
            adapterCollection.setNewData(data.getDataList0());
            //获取总数
            try {
                totalSum = Integer.parseInt(data.getSWZL());
            }catch(Exception e){
              e.printStackTrace();
            }

            //合计
            totalAdapter.addData(data.getDataList2());

            tvCollectionCar.setText(data.getCPH());//收集车牌
            tvCollectionPerson.setText(data.getSJR());//收集人
            tvCollectionTime.setText(data.getFScTime());//收集时间

            bean.setCPH(data.getCPH());
            bean.setSJR(data.getSJR());
            bean.setSJRQ(data.getFScTime());

            //新加


            bean.setId(data.getDataList0().get(0).getDataList1().get(0).getFGlid());

            String id = "";
            for (int i = 0; i < data.getDataList0().size(); i++) {
                id += data.getDataList0().get(i).getId() + ",";
            }
            if (!TextUtils.isEmpty(id)) {
                id = id.substring(0, id.length() - 1);
            }
            //拼接
            bean.setFGlid(id);

            String dwzl = "", sws = "", cbs = "", zls = "", hj = "";
            for (int i = 0; i < data.getDataList2().size(); i++) {
                HarmlessListDetilBean.DataListBean2 dataListBean2 = data.getDataList2().get(i);
                if (TextUtils.isEmpty(dataListBean2.getFbsdwzl())) {
                    hj += dataListBean2.getSWZL();//最后一个总heji
                    dataListBean2.setItemType(HarmlessStockTotalAdapter.TYPE_FOOTER);
                    break;
                }
                dwzl += dataListBean2.getFbsdwzl() + ",";
                sws += dataListBean2.getFsws() + ",";
                cbs += dataListBean2.getCBSS() + ",";
                zls += dataListBean2.getSWZL() + ",";
            }
            //去除,
            dwzl = dwzl.substring(0, dwzl.length() - 1);
            sws = sws.substring(0, sws.length() - 1);
            cbs = cbs.substring(0, cbs.length() - 1);
            zls = zls.substring(0, zls.length() - 1);
            //动物种类数组
            bean.setDwzl(dwzl);
            //死亡数
            bean.setSws(sws);
            //参保数
            bean.setCbs(cbs);
            //重量
            bean.setZls(zls);
            //合计
            bean.setHJ(hj);
        } else {
            Toast.makeText(this, "数据出错", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void uploadSuccessful() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }


    @Override
    public void setTime(String time, long longTime) {
        tvCollectionEnterTime.setText(time);//入场时间
        bean.setRCRQ(time);
    }

    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
