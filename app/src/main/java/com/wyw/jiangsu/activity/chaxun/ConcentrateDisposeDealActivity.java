package com.wyw.jiangsu.activity.chaxun;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.wyw.jiangsu.adapter.JiZhongChuLiCXDetilAdapter;
import com.wyw.jiangsu.bean.JiZhongChuLiDetilBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IWuHaiHua;
import com.wyw.jiangsu.presenter.WuHaiHuaPresenter;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 集中处理查询详情
 * Created by Administrator on 2017/3/29.
 */
public class ConcentrateDisposeDealActivity extends MVPActivity<WuHaiHuaPresenter> implements IWuHaiHua {

    @BindView(R.id.tv_harmlsee_name)
    TextView tvHarmlseeName;
    @BindView(R.id.tv_harmlsee_person)
    TextView tvHarmlseePerson;
    @BindView(R.id.tv_harmlsee_process_date_start)
    TextView tvHarmlseeProcessDateStart;
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(R.id.photo_view_confirm1)
    ImageView photoViewConfirm1;
    @BindView(R.id.photo_view_confirm2)
    ImageView photoViewConfirm2;
    @BindView(R.id.tv_chuli_fangshi)
    TextView tvChuliFangshi;
    @BindView(R.id.et_grease)
    EditText etGrease;
    @BindView(R.id.et_residue)
    EditText etResidue;
    @BindView(R.id.et_liquid_products)
    EditText etLiquidProducts;
    @BindView(R.id.tv_jieshu_riqi)
    TextView tvJieshuRiqi;
    @BindView(R.id.img_process_person_signature)
    ImageView imgProcessPersonSignature;
    @BindView(R.id.img_veterinarians_signature)
    ImageView imgVeterinariansSignature;
    @BindView(R.id.ll_chuliren_qianming)
    LinearLayout llChulirenQianming;
    @BindView(R.id.tv_view_qianming1)
    TextView tvViewQianming1;
    @BindView(R.id.tv_view_qianming1_ll)
    LinearLayout tvViewQianming1Ll;
    @BindView(R.id.ll_shouyi_qianming)
    LinearLayout llShouyiQianming;
    @BindView(R.id.tv_view_qianming2)
    TextView tvViewQianming2;
    @BindView(R.id.tv_view_qianming2_ll)
    LinearLayout tvViewQianming2Ll;
    private WuHaiHuaCXbean.DataListBean listBean;
    private JiZhongChuLiDetilBean.DataBean data;

    private JiZhongChuLiCXDetilAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concentrate_dispose_deal_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected WuHaiHuaPresenter createPresenter() {
        return new WuHaiHuaPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("集中处理查询详情");
        adapter = new JiZhongChuLiCXDetilAdapter(new ArrayList<>());
        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerAnimType.setHasFixedSize(true);
        recyclerAnimType.setNestedScrollingEnabled(false);
        recyclerAnimType.setAdapter(adapter);

        listBean = (WuHaiHuaCXbean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        getPresenter().getDateList(Integer.parseInt(listBean.getFStId()));

    }


    @Override
    public void bindListener() {

    }

    public void getDate() {
        tvHarmlseeName.setText(data.getWHHCLZX());      //无害化处理中心
        tvHarmlseePerson.setText(data.getCLR());        //处理人
        tvHarmlseeProcessDateStart.setText(data.getCLRQ());//处理开始日期
        tvJieshuRiqi.setText(data.getSJ());//处理结束日期
        tvChuliFangshi.setText(data.getCLFSS());//处理方式
        etGrease.setText(data.getYZ());     //油脂
        etResidue.setText(data.getCZ());    //残渣
        etLiquidProducts.setText(data.getYTCW());//液体产物

        //处理确认1
        if (!TextUtils.isEmpty(data.getPictures1().getA6()))
            Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA6())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewConfirm1);
        //处理确认2
        if (!TextUtils.isEmpty(data.getPictures1().getA7()))
            Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA7())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewConfirm2);

        // 处理人签名
        if (!TextUtils.isEmpty(data.getPictures1().getA8())) {
            String yangFile = jieQu(data.getPictures1().getA8());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA8())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgProcessPersonSignature);
                tvViewQianming1Ll.setVisibility(View.GONE);
            } else {
                tvViewQianming1.setText(data.getPictures1().getA8());
                llChulirenQianming.setVisibility(View.GONE);
            }
        } else {
            tvViewQianming1Ll.setVisibility(View.VISIBLE);
            llChulirenQianming.setVisibility(View.GONE);
        }

        // 驻场兽医签名
        if (!TextUtils.isEmpty(data.getPictures1().getA9())) {
            //养殖场签名
            String yangFile = jieQu(data.getPictures1().getA9());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA9())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgVeterinariansSignature);
                tvViewQianming2Ll.setVisibility(View.GONE);
            } else {
                tvViewQianming2.setText(data.getPictures1().getA9());
                llShouyiQianming.setVisibility(View.GONE);
            }
        } else {
            tvViewQianming2Ll.setVisibility(View.VISIBLE);
            llShouyiQianming.setVisibility(View.GONE);
        }
    }

    private void getDataBean(JiZhongChuLiDetilBean.DataBean dataListEntity) {
        if (dataListEntity != null) {
            this.data = dataListEntity;
        } else {
            data = null;
            this.data = dataListEntity;
        }
        adapter.addData(dataListEntity.getDataList1());
    }

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }


    @Override
    public void setData(JiZhongChuLiDetilBean.DataBean dataListEntity) {
        getDataBean(dataListEntity);
        getDate();
    }
}
