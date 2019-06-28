package com.wyw.jiangsu.activity.chaxun;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.PrintAcitivty;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.Qua_QuarantineDeclarationCDQuery;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.view.FullPhotoView;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.R.id.et_donator_anim_kind;

/**
 * 动物检疫申报单查询详情
 * Created by Administrator on 2017/3/27.
 */
public class AnimalQuarantineDetilActivity extends MVPActivity {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_shenpibiao)
    TextView etShenpibiao;
    @BindView(R.id.et_xukezheng)
    TextView etXukezheng;
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
    @BindView(et_donator_anim_kind)
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
    @BindView(R.id.tv_source_title)
    TextView tvSourceTitle;
    @BindView(R.id.et_source_extra)
    TextView etSourceExtra;
    @BindView(R.id.lin_source_extra)
    LinearLayout linSourceExtra;
    @BindView(R.id.et_start_type)
    TextView etStartType;
    @BindView(R.id.et_start_place)
    TextView etStartPlace;
    @BindView(R.id.et_start_village)
    TextView etStartVillage;
//    @BindView(R.id.et_start_other)
//    TextView etStartOther;
    @BindView(R.id.et_end_type)
    TextView etEndType;
    @BindView(R.id.et_end_place)
    TextView etEndPlace;
    @BindView(R.id.et_end_village)
    TextView etEndVillage;
//    @BindView(R.id.et_end_other)
//    TextView etEndOther;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(R.id.tv_shipment_time)
    TextView tvShipmentTime;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.photo_view_group_photo)
    FullPhotoView photoViewIcId;
    @BindView(R.id.lin_take_photo)
    LinearLayout linTakePhoto;
    @BindView(R.id.et_shouli)
    TextView etShouli;
    @BindView(R.id.scrollV)
    ScrollView SV;
//    @BindView(R.id.btn_print)
//    Button btnprint;

    private AnimalQuarantineActivityBean.DataListBean dataListBean;
    private Qua_QuarantineDeclarationCDQuery printBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_quarantine_activity);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("动物检疫申报单详情");
        Intent intent = getIntent();
        dataListBean = (AnimalQuarantineActivityBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);

        printBean = new Qua_QuarantineDeclarationCDQuery();

        if (dataListBean.getYx().equals("有")) {
            linTakePhoto.setVisibility(View.VISIBLE);
        } else {
            linTakePhoto.setVisibility(View.GONE);
        }


        if (dataListBean.getImg() != null && !TextUtils.isEmpty(dataListBean.getImg())) {
            Glide.with(this).load(Constance.IMAGE_PATHH + dataListBean.getImg()).into(photoViewIcId);
        }

        linSourceExtra.setVisibility(View.VISIBLE);


        etShenpibiao.setText(dataListBean.getYx());//有效《跨省引进乳用种用动物检疫审批表》
        printBean.setYx(dataListBean.getYx());
        etShouli.setText(dataListBean.getQDWAccepted());//是否受理
        printBean.setQDWAccepted(dataListBean.getQDWAccepted());
        if (dataListBean.getFqSbType().contains("动物")) {
            llAnimKand.setVisibility(View.VISIBLE);
            llAnimProduct.setVisibility(View.GONE);
            llDonatorAnim.setVisibility(View.GONE);
            llLivestockPermission.setVisibility(View.GONE);
            llUse.setVisibility(View.VISIBLE);
            llLaiyuan.setVisibility(View.VISIBLE);
            etAnimType.setText(dataListBean.getQDWXuZhongZ());//动物种类
            printBean.setQDWXuZhongZ(dataListBean.getQDWXuZhongZ());
            etPurpose.setText(dataListBean.getQDWYongTu());//用途
            printBean.setQDWYongTu(dataListBean.getQDWYongTu());
            etSource.setText(dataListBean.getQDWLaiYuan());//来源
            printBean.setQDWLaiYuan(dataListBean.getQDWLaiYuan());
        } else {
            llAnimKand.setVisibility(View.GONE);
            llAnimProduct.setVisibility(View.VISIBLE);
            llDonatorAnim.setVisibility(View.VISIBLE);
            llLivestockPermission.setVisibility(View.VISIBLE);
            llUse.setVisibility(View.GONE);
            llLaiyuan.setVisibility(View.GONE);
            etDonatorAnimKind.setText(dataListBean.getQDWXuZhongZ());//供体动物种类
            printBean.setQDWXuZhongZ(dataListBean.getQDWXuZhongZ());
            etAnimProductKind.setText(dataListBean.getFqProduct());//动物产品种类
            printBean.setFqProduct(dataListBean.getFqProduct());
            etXukezheng.setText(dataListBean.getFqZxqscjyxkz());//有效《种畜禽生产经营许可证》
            printBean.setFqZxqscjyxkz(dataListBean.getFqZxqscjyxkz());
        }

        etBianhao.setText(dataListBean.getQDWNumber());//申报单编号
        printBean.setQDWNumber(dataListBean.getQDWNumber());
        printBean.setQDAddQy(dataListBean.getQDAddQy());//养殖户地址
        printBean.setXKZH(dataListBean.getXKZH());//繁殖许可证
        printBean.setQDWAddDd(dataListBean.getQDWAddDd());//到达地点

        etAppleyType.setText(dataListBean.getFqSbType());//申报类型
        printBean.setFqSbType(dataListBean.getFqSbType());
        etUserName.setText(dataListBean.getQDWCargoOwner());//申报人姓名
        printBean.setQDWCargoOwner(dataListBean.getQDWCargoOwner());
        etUserPhone.setText(dataListBean.getQDWPhone());//申报人电话
        printBean.setQDWPhone(dataListBean.getQDWPhone());
        tvUnit.setText(dataListBean.getQDWDanWei());//单位
        printBean.setQDWDanWei(dataListBean.getQDWDanWei());
        etAnimCount.setText(dataListBean.getQDWQuantity());//动物数量
        printBean.setQDWQuantity(Integer.valueOf(dataListBean.getQDWQuantity()));
        etStartType.setText(dataListBean.getQDWTypeQy());//起运地类别
        printBean.setQDWTypeQy(dataListBean.getQDWTypeQy());
        etStartPlace.setText(dataListBean.getQDWShengQy() + dataListBean.getQDWShiQy());//起运地址省、市
        printBean.setQDWShengQy(dataListBean.getQDWShengQy());
        printBean.setQDWShiQy(dataListBean.getQDWShiQy());
        etStartVillage.setText(dataListBean.getQDWXiangQy()+dataListBean.getQDWXianQy() + dataListBean.getQDWCunQy());//起运地址乡镇
        printBean.setQDWXiangQy(dataListBean.getQDWXiangQy());
        printBean.setQDWAttn(dataListBean.getQDWAttn());//经办人
        printBean.setCLRQ(dataListBean.getCLRQ());//经办人时间
        printBean.setQDWJBRDianHua(dataListBean.getQDWJBRDianHua());//经办联系电话

//        etStartOther.setText(dataListBean.getQDWXianQy() + dataListBean.getQDWCunQy());//起运地址乡村
        printBean.setQDWXianQy(dataListBean.getQDWXianQy());
        printBean.setQDWCunQy(dataListBean.getQDWCunQy());
        etEndType.setText(dataListBean.getQDWTypeDd());//到达地址类别
        printBean.setQDWTypeDd(dataListBean.getQDWTypeDd());
        etEndPlace.setText(dataListBean.getQDWShengDd() + dataListBean.getQDWShiDd());//到达地址省市
        printBean.setQDWShengDd(dataListBean.getQDWShengDd());
        printBean.setQDWShiDd(dataListBean.getQDWShiDd());
        etEndVillage.setText(dataListBean.getQDWXianDd()+dataListBean.getQDWXiangDd() + dataListBean.getQDWCunDd());//到达地址县镇
        printBean.setQDWXianDd(dataListBean.getQDWXianDd());
//        etEndOther.setText(dataListBean.getQDWXiangDd() + dataListBean.getQDWCunDd());//到达地址乡村
        printBean.setQDWXiangDd(dataListBean.getQDWXiangDd());
        printBean.setQDWCunDd(dataListBean.getQDWCunDd());
        tvInspectionTime.setText(dataListBean.getDateQF());//报检时间
        printBean.setDateQF(dataListBean.getDateQF());
        tvShipmentTime.setText(dataListBean.getDateQy());//启运时间
        printBean.setDateQy(dataListBean.getDateQy());
        tvErbiao.setText(dataListBean.getQDWErBiaoHao());//耳标号
        printBean.setQDWErBiaoHao(dataListBean.getQDWErBiaoHao());
    }

    @Override
    public void bindListener() {
//        btnprint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intentPrintActivity();
//            }
//        });
    }

    //动物(繁殖材料)产地检疫申报单查询打印（套打）
    public void intentPrintActivity() {
        Intent intent = new Intent(this, PrintAcitivty.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constance.START_ACTIVITY_TYPE, Constance.TYPE_PRINT_QUERY_APPLY_DETIL);
        bundle.putSerializable(Constance.START_ACTIVITY_DATA, printBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
