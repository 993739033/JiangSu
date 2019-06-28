package com.wyw.jiangsu.activity.chaxun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.wyw.jiangsu.bean.BreedingDetailshowBean;
import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IBreedingDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mnkj004 on 2017/8/8.
 */

public class BreedingDetailActivity extends MVPActivity<BreedingDetailActivityPresenter> implements IBreedingDetailActivity {
    @BindView(R.id.tv_FFarmName)
    TextView tvFFarmName;
    @BindView(R.id.et_FFarmName)
    EditText etFFarmName;
    @BindView(R.id.tv_FCityAdd)
    TextView tvFCityAdd;
    @BindView(R.id.et_FCityAdd)
    EditText etFCityAdd;
    @BindView(R.id.tv_FCategory)
    TextView tvFCategory;
    @BindView(R.id.et_FCategory)
    EditText etFCategory;
    @BindView(R.id.lin_anim_type)
    LinearLayout linAnimType;
    @BindView(R.id.tv_clsl)
    TextView tvClsl;
    @BindView(R.id.et_clsl)
    EditText etClsl;
    @BindView(R.id.lin_anim_number)
    LinearLayout linAnimNumber;
    @BindView(R.id.et_FLegalPerson)
    EditText etFLegalPerson;
    @BindView(R.id.lin_FLegalPerson)
    LinearLayout linFLegalPerson;
    @BindView(R.id.tv_FPhone)
    TextView tvFPhone;
    @BindView(R.id.et_FPhone)
    EditText etFPhone;
    @BindView(R.id.ll_content)
    RecyclerView llContent;
    @BindView(R.id.et_other)
    EditText etOther;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewGroupPhoto;
    @BindView(R.id.et_check_advise)
    EditText etCheckAdvise;
    @BindView(R.id.et_name1)
    EditText etName1;
    @BindView(R.id.et_person_no1)
    EditText etPersonNo1;
    @BindView(R.id.et_name2)
    EditText etName2;
    @BindView(R.id.et_person_no2)
    EditText etPersonNo2;
    @BindView(R.id.img_sign_check1)
    ImageView imgSignCheck1;
    @BindView(R.id.img_sign_check2)
    ImageView imgSignCheck2;
    @BindView(R.id.img_sign_first)
    ImageView imgSignFirst;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.upload)
    Button upload;

    private BreedingRecordData.DataListBean data;
    private String tableName;
    private BreedingDetailRecycleAdapter breedingDetailRecycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_breedingdetail);
    }

    @Override
    protected BreedingDetailActivityPresenter createPresenter() {
        return new BreedingDetailActivityPresenter(this);
    }

    @Override
    public void bindData() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        data = (BreedingRecordData.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);

        tableName = intent.getStringExtra("table");
        if (tableName.equals("SXR_SgjcjlS")) {
            setTitle("生鲜乳收购站检查表");
            tvFFarmName.setText("收购站名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFCategory.setText(data.getFCategory());
            tvFCategory.setText("收购生鲜乳种类");
            etClsl.setText(data.getClsl());
            tvClsl.setText("日收奶量");
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());
        } else if (tableName.equals("Ds_GmyzcjcjlS")) {
            setTitle("规模养殖场日常监督检查记录详情");
            tvFFarmName.setText("养殖场所名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFCategory.setText(data.getFCategory());
            tvFCategory.setText("养殖动物种类");
            etClsl.setText(data.getClsl());
            tvClsl.setText("存栏动物数量");
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());

        } else if (tableName.equals("Ds_SztzcrcS")) {
            linAnimType.setVisibility(View.GONE);
            linAnimNumber.setVisibility(View.GONE);
            setTitle("生猪屠宰（厂）场日常监督检查记录表");
            tvFFarmName.setText("屠宰厂（场）名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());

        } else if (tableName.equals("Ds_ZlcsjcjlS")) {
            setTitle("动物诊疗场所监督检查记录表");
            tvFFarmName.setText("收购站名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFCategory.setText(data.getFCategory());
            tvFCategory.setText("诊疗动物种类");
            etClsl.setText(data.getClsl());
            tvClsl.setText("每日可诊疗动物数量");
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());

        } else if (tableName.equals("Ds_WhhjgjlS")) {
            setTitle("无害化处理场所监督记录详情");
            tvFFarmName.setText("场所名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFCategory.setText(data.getFCategory());
            tvFCategory.setText("无害化动物种类");
            etClsl.setText(data.getClsl());
            tvClsl.setText("日处理能力");
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());

        } else if (tableName.equals("Ds_GlcsjcjlS")) {
            tvFFarmName.setText("隔离场所名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFCategory.setText(data.getFCategory());
            tvFCategory.setText("隔离动物种类");
            etClsl.setText(data.getClsl());
            tvClsl.setText("每日可隔离动物数量");
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());
        } else if (tableName.equals("Ds_SlscqyjcjlS")) {
            setTitle("饲料生产企业日常检查详情");
            linAnimNumber.setVisibility(View.GONE);
            tvFFarmName.setText("企业名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFCategory.setText(data.getClsl());
            tvFCategory.setText("企业代码");
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());
        } else if (tableName.equals("Ds_SyscqyjcjlS")) {
            setTitle("兽药生产企业日常检查详情");
            linAnimNumber.setVisibility(View.GONE);
            linAnimType.setVisibility(View.GONE);
            tvFFarmName.setText("企业名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());
        } else if (tableName.equals("Ds_SyjyqyjcjlS")) {
            setTitle("兽药经营企业日常检查详情");
            linAnimNumber.setVisibility(View.GONE);
            linAnimType.setVisibility(View.GONE);
            tvFFarmName.setText("企业名称");
            etFFarmName.setText(data.getFName());
            etFCityAdd.setText(data.getFCityAdd());
            etFLegalPerson.setText(data.getFLegalPerson());
            etFPhone.setText(data.getFPhone());
        }
        etName1.setText(data.getJcryxmyi());
        etName2.setText(data.getJcryxmer());
        etPersonNo1.setText(data.getZfzhyi());
        etPersonNo2.setText(data.getZfzher());
        etCheckAdvise.setText(data.getJcclyj());
        etDate.setText(data.getJcdate());

        llContent.setHasFixedSize(true);
        llContent.setNestedScrollingEnabled(false);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        llContent.setLayoutManager(manager);
        breedingDetailRecycleAdapter = new BreedingDetailRecycleAdapter(this);
        llContent.setAdapter(breedingDetailRecycleAdapter);
        getPresenter().getBreedingDetailData1(tableName, data.getFStId());
//      getPresenter().getData(tableName,data.getFStId());

    }

    @Override
    public void bindListener() {
        upload.setVisibility(View.GONE);
    }

    @Override
    public void setData(BreedingDetailshowBean.DataListBean dataList) {
        breedingDetailRecycleAdapter.setData(dataList);
        imgLoad(dataList.getDataList2());

    }

    private void imgLoad(List<BreedingDetailshowBean.DataListBean.DataList2Bean> dataList2) {
        for (int i = 0; i < dataList2.size(); i++) {
                if (dataList2.get(i).getQz().contains("A2")){
                    Glide.with(this).load(Constance.IMAGE_PATH + dataList2.get(i).getQz())
                            .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgSignCheck1);
                }
                if (dataList2.get(i).getQz().contains("A3")){
                    Glide.with(this).load(Constance.IMAGE_PATH + dataList2.get(i).getQz())
                            .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgSignCheck2);
                }
                if (dataList2.get(i).getQz().contains("A4")){
                    Glide.with(this).load(Constance.IMAGE_PATH + dataList2.get(i).getQz())
                            .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgSignFirst);
                }
                if (dataList2.get(i).getQz().contains("A1")){
                    Glide.with(this).load(Constance.IMAGE_PATH + dataList2.get(i).getQz())
                            .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewGroupPhoto);
                }
        }
    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {

    }

    public void setEtOther(String otherText){
        etOther.setText(otherText);
    }
}
