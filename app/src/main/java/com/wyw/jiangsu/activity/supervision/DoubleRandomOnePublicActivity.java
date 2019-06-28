package com.wyw.jiangsu.activity.supervision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.DoubleRandomOnePublicAdapter;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.bean.CommonSupervisionQyBean;
import com.wyw.jiangsu.bean.DoubleRandomCenterBean;
import com.wyw.jiangsu.bean.upload.UploadDoubleRandomBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IDoubleRandomOnePublicActivity;
import com.wyw.jiangsu.presenter.DoubleRandomOnePublicPresenter;

import butterknife.BindView;

//屠宰双随机一公开
public class DoubleRandomOnePublicActivity extends MVPActivity<DoubleRandomOnePublicPresenter> implements IDoubleRandomOnePublicActivity, DoubleRandomOnePublicAdapter.OnNextListener {
    public static final int REQUEST_CODE_ACTIVITY_ZXING = 111;
    public static final int REQUEST_CODE_ACTIVITY_QY = 112;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    DoubleRandomOnePublicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_random_one_public);
    }

    @Override
    protected DoubleRandomOnePublicPresenter createPresenter() {
        return new DoubleRandomOnePublicPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("屠宰“双随机一公开”");
        DoubleRandomCenterBean bean = DoubleRandomCenterBean.getInstance();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DoubleRandomOnePublicAdapter(bean, this,this);
        recyclerview.setAdapter(adapter);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void onNext(UploadDoubleRandomBean uploadBean) {
      Intent intent=  new Intent(this, DoubleRandomOnePublicPrintActivity.class);
        if (uploadBean.getTopData()==null){
            Toast.makeText(this, "请选择企业信息", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("Bean", uploadBean);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ACTIVITY_ZXING) {
                //二维码扫描成功
                String result = data.getExtras().getString("result");
                result = new String(Base64.decode(result, Base64.DEFAULT));
                Log.e("czy", "result" + result);
                getPresenter().getTopDetil("V_AH_SlaughterBk", result.split("-")[1]);
            }

            if (requestCode == REQUEST_CODE_ACTIVITY_QY) {
                CommonSupervisionQyBean.DataListBean bean = (CommonSupervisionQyBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                CommonSupervisionBean.Data beanData = new CommonSupervisionBean.Data();
                beanData.setFStId(bean.getFStId());
                beanData.setClsl(bean.getClsl());
                beanData.setFCategory(bean.getFCategory());
                beanData.setFCityAdd(bean.getFCityAdd());
                beanData.setFFarmName(bean.getFFarmName());
                beanData.setFLatitude(bean.getFLatitude());
                beanData.setFLegalPerson(bean.getFLegalPerson());
                beanData.setFPhone(bean.getFPhone());
                beanData.setFLongitude(bean.getFLongitude());
                adapter.setTopData(beanData);
            }
        }
    }

    @Override
    public void setCompanyDetil(CommonSupervisionBean.Data data) {
        adapter.setTopData(data);
    }
}
