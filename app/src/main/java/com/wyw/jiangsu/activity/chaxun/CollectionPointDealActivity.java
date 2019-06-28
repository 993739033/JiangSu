package com.wyw.jiangsu.activity.chaxun;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.CollectionPointDealActivityBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ICollectionPointDealActivity;
import com.wyw.jiangsu.presenter.CollectionPointDealActivityPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收集点查询详情
 * Created by Administrator on 2017/3/29.
 */
public class CollectionPointDealActivity extends MVPActivity<CollectionPointDealActivityPresenter> implements ICollectionPointDealActivity {


    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_lianxi_phone)
    EditText tvLianxiPhone;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_type)
    EditText tvType;
    @BindView(R.id.tv_number)
    EditText tvNumber;
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
    @BindView(R.id.et_zhongliang_death)
    EditText etZhongliangDeath;
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
    @BindView(R.id.tv_shouyiqianming)
    TextView tvShouyiqianming;
    @BindView(R.id.img_shouyiqianming)
    ImageView imgShouyiqianming;
    @BindView(R.id.img_takephoto_confirm)
    ImageView imgTakephotoConfirm;
    @BindView(R.id.photo_view_confim)
    ImageView photoViewConfim;
    @BindView(R.id.img_principal_signature)
    ImageView imgPrincipalSignature;
    @BindView(R.id.img_collection_person)
    ImageView imgCollectionPerson;
    @BindView(R.id.tv_shouji)
    TextView tvShouji;
    @BindView(R.id.tv_ll_shouj)
    LinearLayout tvLlShouj;
    @BindView(R.id.et_check_number)
    EditText etCheckNumber;
    @BindView(R.id.et_ruku_number)
    EditText etRukuNumber;
    @BindView(R.id.iv_guanfang_shouyi)
    ImageView ivGuanfangShouyi;
    @BindView(R.id.name)
    TextView name;
    private CollectionPointDealActivityBean.DataBean dataBean = new CollectionPointDealActivityBean.DataBean();
    private WuHaiHuaCXbean.DataListBean dataListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_point_deal_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected CollectionPointDealActivityPresenter createPresenter() {
        return new CollectionPointDealActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("收集点查询详情");
        dataListBean = (WuHaiHuaCXbean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        int a = Integer.valueOf(dataListBean.getFStId());
        getPresenter().getDateList("V_APP_BSDWRK", a);
    }

    @Override
    public void bindListener() {

    }

    public void getDate() {

        etDeadErbiao.setText(dataBean.getQDWErBiaoHao());//畜畜耳标号
        tvName.setText(dataBean.getFxzxm());//畜主姓名
        tvAddr.setText(dataBean.getFxxdz());//地址
        tvType.setText(dataBean.getFyzclx());//养殖场类型
        tvNumber.setText(dataBean.getFxcll());//现存栏量
        name.setText(dataBean.getXm());//姓名
        tvIdCrad.setText(dataBean.getFsfzh());//身份证号
        tvIcCrad.setText(dataBean.getFykth());//一卡通号
        tvDate.setText(dataBean.getFsbrq());//申报日期
        tvLianxiPhone.setText(dataBean.getLxdh());//联系电话
        etDieAnimalType.setText(dataBean.getFbsdwzl());        //病死动物种类
        etDeadthNumber.setText(dataBean.getFsws());         //死亡数
        etZhongliangDeath.setText(dataBean.getZSL());      //重量
        etCheckNumber.setText(dataBean.getHDSL());      //核定数量
        etRukuNumber.setText(dataBean.getRKSL());       //入库数量

        if (dataBean.getPictures1() != null) {
            //身份证和一卡通号照片
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA1())) {
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA1())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewGroupPhoto);
            }
            //保单
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA2()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA2())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewBaodan);
            //病死禽畜照片
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA3()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA3())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewXuqin1);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA4()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA4())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewXuqin2);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA5()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA5()).into(photoViewXuqin3);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA6()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA6()).into(photoViewXuqin4);
            //病死禽畜和畜主合照
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA7()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA7()).into(photoViewXuzhu1);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA8()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA8()).into(photoViewXuzhu2);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA9()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA9()).into(photoViewXuzhu3);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA10()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA10()).into(photoViewXuzhu4);

            // 官方兽医签字
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA16())) {
                String yangFile = jieQu(dataBean.getPictures1().getA16());
                if (yangFile.equals("jpg")) {
                    Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA16())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgShouyiqianming);
                    tvShouyiqianming.setVisibility(View.GONE);
                } else {
                    tvShouyiqianming.setText(dataBean.getPictures1().getA11());
                }
            }
            //入库确认
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA17()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA17()).into(photoViewConfim);
            //畜主/养殖场负责人签名
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA18()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA18()).into(imgPrincipalSignature);
            //收集人员签名
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA19())) {
                String yangFile = jieQu(dataBean.getPictures1().getA19());
                if (yangFile.equals("jpg")) {
                    Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA19())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgCollectionPerson);
                    tvLlShouj.setVisibility(View.GONE);
                    imgCollectionPerson.setVisibility(View.VISIBLE);
                } else {
                    tvShouji.setText(dataBean.getPictures1().getA19());
                    imgCollectionPerson.setVisibility(View.GONE);
                }
            } else {
                tvLlShouj.setVisibility(View.VISIBLE);
                imgCollectionPerson.setVisibility(View.GONE);
            }

            //官方兽医签字
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA20()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA20()).into(ivGuanfangShouyi);
        }
    }

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }

    @Override
    public void setData(CollectionPointDealActivityBean.DataBean dataListEntity) {
        getDataBean(dataListEntity);
        getDate();
    }

    private void getDataBean(CollectionPointDealActivityBean.DataBean dataListEntity) {
        if (dataListEntity != null) {
            this.dataBean = dataListEntity;
        } else {
            dataBean = null;
            this.dataBean = dataListEntity;
        }
    }
}
