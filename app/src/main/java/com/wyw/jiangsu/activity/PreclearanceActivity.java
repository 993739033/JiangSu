package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.wuhaihua.AnimAListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 产地检疫(包含动物检疫申报单,动物检疫申报单处理,动物产地检疫工作记录单,
 * 乳用、种用动物检疫工作记录单,动物A检疫证明,动物B检疫证明,检疫处理通知单)
 */
public class PreclearanceActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_anim_apply)
    Button btAnimApply;
    @BindView(R.id.bt_anim_apply_handle)
    Button btAnimApplyHandle;
    @BindView(R.id.bt_product_area_record)
    Button btProductAreaRecord;
    @BindView(R.id.bt_other_record)
    Button btOtherRecord;
    @BindView(R.id.bt_A_quarantine)
    Button btAQuarantine;
    @BindView(R.id.bt_B_quarantine)
    Button btBQuarantine;
    @BindView(R.id.bt_quarantine_process)
    Button btQuarantineProcess;
    @BindView(R.id.bt_A_product)
    Button btAProduct;
    @BindView(R.id.bt_B_product)
    Button btBProduct;
    @BindView(R.id.activity_preclearance)
    LinearLayout activityPreclearance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preclearance);
        ButterKnife.bind(this);
        btAnimApply.setOnClickListener(this);
        btAnimApplyHandle.setOnClickListener(this);
        btProductAreaRecord.setOnClickListener(this);
        btOtherRecord.setOnClickListener(this);
        btAQuarantine.setOnClickListener(this);
        btBQuarantine.setOnClickListener(this);
        btAProduct.setOnClickListener(this);
        btBProduct.setOnClickListener(this);
        btQuarantineProcess.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // * 产地检疫(包含动物检疫申报单,动物检疫申报单处理,动物产地检疫工作记录单,
        // * 乳用、种用动物检疫工作记录单,动物A检疫证明,动物B检疫证明,检疫处理通知单)
        switch (v.getId()) {
            case R.id.bt_anim_apply:
                //动物检疫申报单
                startActivity(new Intent(this, AnimApplyListActivity.class));
                break;
            case R.id.bt_anim_apply_handle:
                //动物检疫申报单处理
                startActivity(new Intent(this, AnimProcessListActivity.class));
                break;
            case R.id.bt_product_area_record:
                //动物产地检疫工作记录单  AnimWorkRecordActivity
                startActivity(new Intent(this, AnimWorkRecordActivity.class));

                break;
            case R.id.bt_other_record:
                //乳用、种用动物检疫工作记录单

                break;
            case R.id.bt_A_quarantine:
                //动物A检疫证明
                startActivity(new Intent(this, AnimAListActivity.class));
                break;
            case R.id.bt_B_quarantine:
                //动物B检疫证明
                startActivity(new Intent(this, AnimBlistActivity.class));
                break;
            case R.id.bt_A_product:
                //产品A检疫证明
                startActivity(new Intent(this, ProductAListActivity.class));
                break;
            case R.id.bt_B_product:
                //产品B检疫证明
                startActivity(new Intent(this, ProductBListActivity.class));
                break;
            case R.id.bt_quarantine_process:
                //检疫处理通知单
                startActivity(new Intent(this, QuarantineProcessNotifListActivity.class));
                break;
        }
    }
}
