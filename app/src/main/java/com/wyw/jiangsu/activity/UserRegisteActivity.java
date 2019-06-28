package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wyw.jiangsu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HUANG on 2017/9/6.
 */
public class UserRegisteActivity extends BaseActivity {
    @BindView(R.id.btn_syjy)
    Button btnSyjy;
    @BindView(R.id.btn_tzc)
    Button btnTzc;
    @BindView(R.id.btn_dwzlc)
    Button btnDwzlc;
    @BindView(R.id.btn_whh)
    Button btnWhh;
    @BindView(R.id.btn_slsc)
    Button btnSlsc;
    @BindView(R.id.btn_sysc)
    Button btnSysc;
    @BindView(R.id.btn_dwyzc)
    Button btnDwyzc;
    @BindView(R.id.btn_sxr)
    Button btnSxr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregiste);
        ButterKnife.bind(this);
    }

    @Override
    public void bindData() {
        setTitle("用户注册");
    }

    @Override
    public void bindListener() {
    }
    @OnClick({R.id.btn_syjy,R.id.btn_tzc,R.id.btn_dwzlc,R.id.btn_whh,R.id.btn_slsc,R.id.btn_sysc,R.id.btn_dwyzc,R.id.btn_sxr})
    public void onClick(View view){
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.btn_syjy://兽药经营
               intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",1);
                startActivity(intent);
                break;
            case R.id.btn_tzc://动物屠宰场
                intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",2);
                startActivity(intent);
                break;
            case R.id.btn_dwzlc://动物诊疗机构
                intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",3);
                startActivity(intent);
                break;
            case R.id.btn_whh://无害化
                intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",4);
                startActivity(intent);
                break;
            case R.id.btn_slsc://饲料生产企业
                intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",5);
                startActivity(intent);
                break;
            case R.id.btn_sysc://兽药生产
                intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",6);
                startActivity(intent);
                break;
            case R.id.btn_dwyzc://动物养殖场
                intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",7);
                startActivity(intent);
                break;
            case R.id.btn_sxr://生鲜乳
                intent.setClass(this,RegisteDetilActivity.class);
                intent.putExtra("from",8);
                startActivity(intent);
                break;
        }
    }

}
