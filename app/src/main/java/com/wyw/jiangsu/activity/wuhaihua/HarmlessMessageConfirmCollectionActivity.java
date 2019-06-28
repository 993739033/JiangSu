package com.wyw.jiangsu.activity.wuhaihua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.AnimTypeTotalAdapter;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.NetworkAnimType;
import com.wyw.jiangsu.bean.upload.UploadSickDeadAnimBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessMessageConfirmCollectionActivity;
import com.wyw.jiangsu.presenter.HarmlessMessageConfirmCollectionActivityPresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;
import com.wyw.jiangsu.view.photoview.PhotoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wyw.jiangsu.R.id.photo_view_confim;
import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;

/**
 * 暂存点收集人员确认(病死动物入库) (收集入库)
 * 上个页面 病死动物收集点人员信息接收 {@link HarmlessMessageAcceptCollectionListActivity}
 * 下个页面 收集人员消息接收(无害化处理厂) {@link HarmlessMessageAcceptStorageListActivity}
 */
public class HarmlessMessageConfirmCollectionActivity extends MVPActivity<HarmlessMessageConfirmCollectionActivityPresenter>
        implements IHarmlessMessageConfirmCollectionActivity, PhotoViewModel.OnClickListener {

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
    @BindView(R.id.img_takephoto_ic_id)
    ImageView imgTakephotoIcId;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewIcId;
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(R.id.img_takephoto_baodan)
    View imgTakephotoBaodan;
    @BindView(R.id.photo_view_baodan)
    ImageView photoViewBaodan;
    @BindView(R.id.et_dead_erbiao)
    EditText etDeadErbiao;
    //    @BindView(R.id.img_takephoto_xuqin1)
//    ImageView imgTakephotoXuqin1;
    @BindView(R.id.photo_view_xuqin1)
    ImageView photoViewXuqin1;
    //    @BindView(R.id.img_takephoto_xuqin2)
//    ImageView imgTakephotoXuqin2;
    @BindView(R.id.photo_view_xuqin2)
    ImageView photoViewXuqin2;
    //    @BindView(R.id.img_takephoto_xuqin3)
//    ImageView imgTakephotoXuqin3;
    @BindView(R.id.photo_view_xuqin3)
    ImageView photoViewXuqin3;
    @BindView(R.id.photo_view_xuqin4)
    ImageView photoViewXuqin4;
    //    @BindView(R.id.img_takephoto_xuzhu1)
//    ImageView imgTakephotoXuzhu1;
    @BindView(R.id.photo_view_xuzhu1)
    ImageView photoViewXuzhu1;
    //    @BindView(R.id.img_takephoto_xuzhu2)
//    ImageView imgTakephotoXuzhu2;
    @BindView(R.id.photo_view_xuzhu2)
    ImageView photoViewXuzhu2;
    //    @BindView(R.id.img_takephoto_xuzhu3)
//    ImageView imgTakephotoXuzhu3;
    @BindView(R.id.photo_view_xuzhu3)
    ImageView photoViewXuzhu3;
    //    @BindView(R.id.img_takephoto_xuzhu4)
//    ImageView imgTakephotoXuzhu4;
    @BindView(R.id.photo_view_xuzhu4)
    ImageView photoViewXuzhu4;
    @BindView(R.id.img_shouyiqianming)
    ImageView imgShouyiqianming;
    @BindView(photo_view_confim)
    ImageView photoViewConfim;
    @BindView(R.id.img_principal_signature)
    ImageView imgPrincipalSignature;
    @BindView(R.id.img_collection_person)
    ImageView imgCollectionPerson;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    PhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;
    @BindView(R.id.tv_shouyiqianming)
    TextView tvShouYiQianMing;
    PhotoViewUtils photoViewUtils;
    UploadSickDeadAnimBean bean;
    AnimTypeTotalAdapter typeAdapter;
    @BindView(R.id.et_check_number)
    EditText etCheckNumber;
    @BindView(R.id.img_guanfangshouyi_qianzi)
    ImageView imgGuanfangshouyiQianzi;
    @BindView(R.id.name)
    TextView name;
    private String deadNumber;
    private String uuid;
    private PhotoViewModel ImageViewModel;
    private String currentPhotoFile;
    private ArrayList<NetworkAnimType> data;
    private boolean isDestoryed;
    private boolean isclick;
    private ImageView curImg;
    private String uuid2;
    private String mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_message_confirm_collection);
        ButterKnife.bind(this);
        uuid = UUID.randomUUID().toString();
    }

    @Override
    protected HarmlessMessageConfirmCollectionActivityPresenter createPresenter() {
        return new HarmlessMessageConfirmCollectionActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("收集点收集人员确认");
//        AnimTypeModel animTypeModel = new AnimTypeModel(this,list);
        bean = new UploadSickDeadAnimBean();
        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));
        typeAdapter = new AnimTypeTotalAdapter(new ArrayList<>());
        recyclerAnimType.setAdapter(typeAdapter);
        String fstId = getIntent().getStringExtra(Constance.ACTIVITY_DATA);
        bean.setGlid(fstId);
        getPresenter().getPageDetil(fstId);

        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
            if (file.getName().contains(uuid + "_A2")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgPrincipalSignature);
            } else if (file.getName().contains(uuid + "_A3")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgCollectionPerson);
            } else if (file.getName().contains(uuid + "_A4")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgGuanfangshouyiQianzi);
            }
        }
        String nameManager1 = uuid + "_A1";
        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewConfim, REQUEST_CODE_START, nameManager1, getWuHaiHuaDir(), this);
        /*if (isDestoryed) {
            typeAdapter.setNewData(data);
        }*/
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uuid = savedInstanceState.getString("uuid");
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
        data = savedInstanceState.getParcelableArrayList("data");
        isDestoryed = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("uuid", uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        outState.putParcelableArrayList("data", (ArrayList<NetworkAnimType>) typeAdapter.getData());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindListener() {
        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                Log.e("getData",getData().toString());
                showNormalDialog(uuid);
            }
        });
    }

    private void showNormalDialog(String uuid) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessMessageConfirmCollectionActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getData(), uuid,uuid2);
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

        //获取重量
        String weight = ((NetworkAnimType) typeAdapter.getData().get(1)).getSWZL();
        if (weight == null) {
            weight = "0";
        }
        /*if (TextUtils.isEmpty(weight)) {
            Toast.makeText(this, "重量不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        if (Integer.parseInt(weight) <= 0) {
            Toast.makeText(this, "重量必须大于0", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etCheckNumber.getText().toString())) {
            Toast.makeText(this, "核定数量不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imgCollectionPerson.getDrawable() == null) {
            Toast.makeText(this, "收集人员签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imgGuanfangshouyiQianzi.getDrawable() == null) {
            Toast.makeText(this, "官方兽医不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imgPrincipalSignature.getDrawable() == null) {
            Toast.makeText(this, "畜主/养殖场负责人签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (photoViewConfim.getDrawable() == null) {
            Toast.makeText(this, "入库确认图片不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        //核定数量小于等于死亡数量，入库数量小于等于核定数量
        if (Integer.parseInt(etCheckNumber.getText().toString()) > Integer.parseInt(deadNumber)) {
            Toast.makeText(this, "核定数量不能大于死亡数量", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isclick){
            if (photoViewBaodan.getDrawable()==null){
                Toast.makeText(this, "保单图片未拍摄", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (photoViewXuqin1.getDrawable()==null&&photoViewXuqin2.getDrawable()==null&&photoViewXuqin3.getDrawable()==null&&photoViewXuqin4.getDrawable()==null){
                Toast.makeText(this, "病死畜禽照片未拍摄", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (photoViewXuzhu1.getDrawable()==null&&photoViewXuzhu2.getDrawable()==null&&photoViewXuzhu3.getDrawable()==null&&photoViewXuzhu4.getDrawable()==null){
                Toast.makeText(this, "病死畜禽照片未拍摄", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    public UploadSickDeadAnimBean getData() {
        String weight = ((NetworkAnimType) typeAdapter.getData().get(1)).getSWZL();
        bean.setZSL(TextUtils.isEmpty(weight) ? 0 : Double.valueOf(weight));
        //录入的核定数量放到入库数量上
        bean.setRKSL(Integer.valueOf(etCheckNumber.getText().toString()));
        bean.setHDSL(0);
        bean.setLen("A1.jpg,A2.jpg,A3.jpg,A4.jpg");
        if (isclick) {
            bean.setUuid(uuid2);
            bean.setLens("A2.jpg,A3.jpg,A4.jpg,A5.jpg,A6.jpg,A7.jpg,A8.jpg,A9.jpg,A10.jpg");
            bean.setId(mid);
        }else{
            bean.setId("");
            bean.setUuid("");
            bean.setLens("");
        }
        bean.setXm(name.getText().toString());
        return bean;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                  //  photoViewConfim.setImageBitmap(bitmap);
                    curImg.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    @OnClick({R.id.img_takephoto_confirm,
            R.id.bt_principal_qianming, R.id.bt_collection_person, R.id.bt_guanfangshouyi_qianzi,R.id.img_takephoto_baodan,R.id.img_takephoto_xuqin1,R.id.img_takephoto_xuqin2,R.id.img_takephoto_xuqin3,R.id.img_takephoto_xuqin4,
            R.id.img_takephoto_xuzhu1,R.id.img_takephoto_xuzhu2,R.id.img_takephoto_xuzhu3,R.id.img_takephoto_xuzhu4
    })
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_confirm:
                curImg=photoViewConfim;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
                break;
            //保单
            case R.id.img_takephoto_baodan:
                if(!isclick)break;
                curImg=photoViewBaodan;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A2", uuid2 + "_");

                break;

            //病死畜禽照片1
            case R.id.img_takephoto_xuqin1:

                if(!isclick)break;
                curImg=photoViewXuqin1;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A3", uuid2 + "_");

                break;
            //病死畜禽照片2
            case R.id.img_takephoto_xuqin2:
                if(!isclick)break;
                curImg=photoViewXuqin2;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A4", uuid2 + "_");

                break;
            //病死畜禽照片3
            case R.id.img_takephoto_xuqin3:
                if(!isclick)break;
                curImg=photoViewXuqin3;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A5", uuid2 + "_");

                break;
            //病死畜禽照片4
            case R.id.img_takephoto_xuqin4:
                if(!isclick)break;
                curImg=photoViewXuqin4;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A6", uuid2 + "_");

                break;


            //病死畜禽和畜主合照1
            case R.id.img_takephoto_xuzhu1:
                if(!isclick)break;
                curImg=photoViewXuzhu1;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A7", uuid2 + "_");
                break;
            //病死畜禽和畜主合照2
            case R.id.img_takephoto_xuzhu2:
                if(!isclick)break;
                curImg=photoViewXuzhu2;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A8", uuid2 + "_");
                break;
            //病死畜禽和畜主合照3
            case R.id.img_takephoto_xuzhu3:
                if(!isclick)break;
                curImg=photoViewXuzhu3;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A9", uuid2 + "_");
                break;
            //病死畜禽和畜主合照4
            case R.id.img_takephoto_xuzhu4:
                if(!isclick)break;
                curImg=photoViewXuzhu4;
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A10", uuid2 + "_");

                break;

            case R.id.bt_principal_qianming:
                //养殖户签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A2", uuid + "_");
                    imgPrincipalSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_collection_person:
                //收集人员签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A3", uuid + "_");
                    imgCollectionPerson.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_guanfangshouyi_qianzi:
                //官方兽医签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A4", uuid + "_");
                    imgGuanfangshouyiQianzi.setImageBitmap((Bitmap) object);
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
    public void getPageDetil(HarmlessListDetilBean.DataBean data) {
        if (data != null) {
            if (data.getPictures() != null) {
                //照片
                if (!TextUtils.isEmpty(data.getPictures().getA1())) {
//                    Picasso.with(this).load(Constance.IMAGE_PATH +"_" +data.getPictures().getA1())
//                            .into(photoViewIcId);
//                    Glide.with(this).load(Constance.IMAGE_PATH + "_" + data.getPictures().getA1())
//                            .placeholder(null)
//                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewIcId);
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA1())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewIcId);
                }
                if (!TextUtils.isEmpty(data.getPictures().getA2()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA2())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewBaodan);
                if (!TextUtils.isEmpty(data.getPictures().getA3()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA3())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewXuqin1);
                if (!TextUtils.isEmpty(data.getPictures().getA4()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA4())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewXuqin2);
                if (!TextUtils.isEmpty(data.getPictures().getA5()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA5()).into(photoViewXuqin3);
                if (!TextUtils.isEmpty(data.getPictures().getA6()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA6()).into(photoViewXuqin4);
                if (!TextUtils.isEmpty(data.getPictures().getA7()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA7()).into(photoViewXuzhu1);
                if (!TextUtils.isEmpty(data.getPictures().getA8()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA8()).into(photoViewXuzhu2);
                if (!TextUtils.isEmpty(data.getPictures().getA9()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA9()).into(photoViewXuzhu3);
                if (!TextUtils.isEmpty(data.getPictures().getA10()))
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA10()).into(photoViewXuzhu4);

                //官方兽医签字
                if (!TextUtils.isEmpty(data.getPictures().getA17()) && data.getPictures().getA17().endsWith(".jpg")) {
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA17()).into(imgShouyiqianming);
                    tvShouYiQianMing.setVisibility(View.GONE);
                    imgShouyiqianming.setVisibility(View.VISIBLE);
                } else {
//                    tvShouYiQianMing.setTag(data.getPictures().getA17());
//                    tvShouYiQianMing.setVisibility(View.VISIBLE);
//                    imgShouyiqianming.setVisibility(View.GONE);
                    Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures().getA16()).into(imgShouyiqianming);
                    tvShouYiQianMing.setVisibility(View.GONE);
                    imgShouyiqianming.setVisibility(View.VISIBLE);
                }
                try {
                    uuid2 = data.getPictures().getA1().split("_")[0];
                }catch (NullPointerException e){
                    uuid2=UUID.randomUUID().toString();
                    e.printStackTrace();
                }catch (ArrayIndexOutOfBoundsException e){
                    uuid2=UUID.randomUUID().toString();
                    e.printStackTrace();
                }
            }

            // TODO: 2016/12/29 将来扩展 多种动物种类
            //动物种类
            List<NetworkAnimType> list = new ArrayList<>();
            NetworkAnimType type = new NetworkAnimType();
            type.setFbsdwzl(data.getFbsdwzl());
            type.setFsws(data.getFsws());
            type.setFcbs(data.getFcbs());
            list.add(type);
            typeAdapter.addData(list);
            //死亡数
            deadNumber = ((NetworkAnimType) typeAdapter.getData().get(1)).getFsws();
//            animTypeModel.add(data.getFbsdwzl(), data.getFsws(), data.getFcbs(), true);

            etDeadErbiao.setText(data.getQDWErBiaoHao());//畜畜耳标号

            tvName.setText(data.getFxzxm());//畜主姓名

            tvAddr.setText(data.getFxxdz());//地址

            tvType.setText(data.getFyzclx());//养殖场类型

            tvNumber.setText(data.getFxcll() + " " + data.getFdw1());//现存栏量

            tvIdCrad.setText(data.getFsfzh());//身份证号

            name.setText(data.getXm());//姓名

            tvIcCrad.setText(data.getFykth());//一卡通号

            tvDate.setText(data.getFsbrq());//申报日期

            if(!TextUtils.isEmpty(data.getFSm4())){
                if (data.getFSm4().equals("收集点勘验")){
                    isclick=true;
                }
            }
            mid = data.getFStId();

        } else {
            Toast.makeText(this, "数据出错", Toast.LENGTH_SHORT).show();
        }
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
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
