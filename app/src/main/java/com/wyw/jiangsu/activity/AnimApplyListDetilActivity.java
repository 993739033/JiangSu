package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.wuhaihua.PhotoViewUtils;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.view.FullPhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动物产地检疫申报单详情
 */
public class AnimApplyListDetilActivity extends MVPActivity {
    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rb_yes)
    TextView rbYes;
    @BindView(R.id.photo_view_group_photo)
    FullPhotoView photoViewIcId;
    @BindView(R.id.lin_take_photo)
    LinearLayout linTakePhoto;
    @BindView(R.id.rb_yes2)
    RadioButton rbYes2;
    @BindView(R.id.ll_livestock_permission)
    LinearLayout llLivestockPermission;
    @BindView(R.id.et_bianhao)
    TextView etBianhao;
    @BindView(R.id.et_appley_type)
    TextView etAppleyType;
    @BindView(R.id.et_user_name)
    TextView etUserName;
    @BindView(R.id.et_user_phone)
    TextView etUserPhone;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_type)
    TextView etAnimType;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.et_donator_anim_kind)
    TextView etDonatorAnimKind;
    @BindView(R.id.ll_donator_anim)
    LinearLayout llDonatorAnim;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.et_anim_product_kind)
    TextView etAnimProductKind;
    @BindView(R.id.ll_anim_product)
    LinearLayout llAnimProduct;
    @BindView(R.id.ll_anim_kand)
    LinearLayout llAnimKand;
    @BindView(R.id.et_anim_count)
    TextView etAnimCount;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_purpose)
    TextView etPurpose;
    @BindView(R.id.ll_use)
    LinearLayout llUse;
    @BindView(R.id.et_source)
    TextView etSource;
    @BindView(R.id.ll_laiyuan)
    LinearLayout llLaiyuan;
    @BindView(R.id.ll_yongtu_laiyuan)
    LinearLayout llYongtuLaiyuan;
    @BindView(R.id.tv_source_title)
    TextView tvSourceTitle;
    @BindView(R.id.et_source_extra)
    EditText etSourceExtra;
    @BindView(R.id.lin_source_extra)
    LinearLayout linSourceExtra;
    @BindView(R.id.et_start_type)
    TextView etStartType;
    @BindView(R.id.et_start_place)
    TextView etStartPlace;
    @BindView(R.id.et_start_village)
    TextView etStartVillage;
    @BindView(R.id.et_end_type)
    TextView etEndType;
    @BindView(R.id.et_end_place)
    TextView etEndPlace;
    @BindView(R.id.et_end_village)
    TextView etEndVillage;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(R.id.tv_shipment_time)
    TextView tvShipmentTime;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.tv_shengbaoren_qinazhi)
    TextView tvShengbaorenQinazhi;
    @BindView(R.id.et_chuli_jieguo)
    TextView etChuliJieguo;
    @BindView(R.id.et_nipai_time)
    TextView etNipaiTime;
    @BindView(R.id.et_place)
    EditText etPlace;
    @BindView(R.id.et_operator_name)
    TextView etOperatorName;
    @BindView(R.id.et_operator_phone)
    TextView etOperatorPhone;
    @BindView(R.id.et_chuli_time)
    TextView etChuliTime;
    @BindView(R.id.ll_shouji)
    LinearLayout llShouji;
    @BindView(R.id.scrollV)
    ScrollView scrollV;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    FullPhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;

    private GuarantineDeclareListDetilBean.DataListBean dataListBean;

    String tempFileName;
    PhotoViewUtils photoViewUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_apply_list_detil);
        ButterKnife.bind(this);
        scrollV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("产地检疫申报单详情");
        Intent intent = this.getIntent();
        dataListBean = (GuarantineDeclareListDetilBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
//        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
//        photoViewUtils.initPhotoView(photoViewIcId);
        bindDataLivestock();

        //是否受理
        if (dataListBean.getQDWAccepted().equals("受理")) {
            llShouji.setVisibility(View.VISIBLE);
        } else {
            llShouji.setVisibility(View.GONE);
        }

        if (dataListBean.getYx().equals("有")) {
            linTakePhoto.setVisibility(View.VISIBLE);
        } else {
            linTakePhoto.setVisibility(View.GONE);
        }

        if (dataListBean.getImg() != null && !TextUtils.isEmpty(dataListBean.getImg())) {
            Glide.with(this).load(Constance.IMAGE_PATHH + dataListBean.getImg()).into(photoViewIcId);
        }

        etChuliJieguo.setText(dataListBean.getQDWAccepted());//受理结果
        etPlace.setText(dataListBean.getQDWAddress());//检疫地点
        etOperatorName.setText(dataListBean.getQDWAttn());//经办人姓名
        etOperatorPhone.setText(dataListBean.getQDWJBRDianHua());//经办人电话
        etNipaiTime.setText(dataListBean.getCLRQ());//拟派时间
        etChuliTime.setText(dataListBean.getDateNpy());//处理时间

        rbYes.setText(dataListBean.getYx());//有效《跨省引进乳用种用动物检疫审批表》
        etBianhao.setText(dataListBean.getQDWNumber());//申报单编号
        etUserName.setText(dataListBean.getQDWCargoOwner());//申报人姓名
        etUserPhone.setText(dataListBean.getQDWPhone());//申报人电话
        tvUnit.setText(dataListBean.getQDWDanWei());//单位
        etAnimType.setText(dataListBean.getQDWXuZhongZ());//动物种类x
        etAnimCount.setText(dataListBean.getQDWQuantity());//动物数量
        etPurpose.setText(dataListBean.getQDWYongTu());//用途
        etSource.setText(dataListBean.getQDWLaiYuan());//来源
        if (dataListBean.getQDWLaiYuan().contains("人工饲养")) {//来源
            linSourceExtra.setVisibility(View.VISIBLE);
            etSourceExtra.setText(dataListBean.getXKZH());
        } else if (dataListBean.getQDWLaiYuan().contains("合法捕获")) {
            linSourceExtra.setVisibility(View.VISIBLE);
            tvSourceTitle.setText("野生动物捕捉(猎捕)许可证号");
            etSourceExtra.setText(dataListBean.getXKZH());
        }
        etStartType.setText(dataListBean.getQDWTypeQy());//起运地类别
        etStartPlace.setText(dataListBean.getQDWShengQy() + dataListBean.getQDWShiQy() + dataListBean.getQDWXianQy());//起运地址省、市
        etStartVillage.setText(dataListBean.getQDWXiangQy() + dataListBean.getQDWCunQy());//起运地址乡镇
        // etStartOther.setText(dataListBean.getQDWCunQy());//起运地址乡村
        etEndType.setText(dataListBean.getQDWTypeDd());//到达地址类别
        etEndPlace.setText(dataListBean.getQDWShengDd() + dataListBean.getQDWShiDd() + dataListBean.getQDWXianDd());//到达地址省市
        etEndVillage.setText(dataListBean.getQDWXiangDd() + dataListBean.getQDWCunDd());//到达地址县镇
        //etEndOther.setText(dataListBean.getQDWCunDd());//到达地址乡村
        tvInspectionTime.setText(dataListBean.getFScTime() + " " + dataListBean.getDr() + "时");//报检时间
        tvShipmentTime.setText(dataListBean.getDateQy());//启运时间
        tvErbiao.setText(dataListBean.getQDWErBiaoHao());//耳标号
        tvShengbaorenQinazhi.setText(dataListBean.getGZ());//申报人签字
    }

    public void bindDataLivestock() {
        if (dataListBean.getFqSbType().contains("繁殖材料")) {//繁殖材料
            etAppleyType.setText("繁殖材料");
            llLivestockPermission.setVisibility(View.GONE);//有效《种畜禽生产经营许可证》
            llDonatorAnim.setVisibility(View.VISIBLE);//供体动物种类
            llAnimProduct.setVisibility(View.VISIBLE);//动物产品种类et_end_village
            etDonatorAnimKind.setText(dataListBean.getQDWXuZhongZ());
            etAnimProductKind.setText(dataListBean.getFqProduct());
            llYongtuLaiyuan.setVisibility(View.GONE);
            if (dataListBean.getFqZxqscjyxkz().contains("无")) {
                rbYes2.setChecked(false);
                rbYes2.setText("无");
                llAnimKand.setVisibility(View.GONE);
                llLaiyuan.setVisibility(View.GONE);
                llUse.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void bindListener() {

        getAdd().setOnClickListener(v -> startActivityForResult(new Intent(this, AnimApplyListActivity.class), Constance.ACTIVITY_REQUEST_CODE));
    }
}
