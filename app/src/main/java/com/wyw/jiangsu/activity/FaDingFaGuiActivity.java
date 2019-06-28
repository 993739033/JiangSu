package com.wyw.jiangsu.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.FadingFaguiBean;
import com.wyw.jiangsu.fragment.TiTleFragment;
import com.wyw.jiangsu.interfac.IFaDingFaGui;
import com.wyw.jiangsu.presenter.FaDingFaGuiPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wyw on 2018/1/17.
 */

public class FaDingFaGuiActivity extends MVPActivity<FaDingFaGuiPresenter> implements IFaDingFaGui {

    @BindView(R.id.et_seek_content)
    EditText etSeekContent;
    @BindView(R.id.bt_search)
    ImageView btSearch;
    private List<FadingFaguiBean.DataListBean> beans1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fading_fagui);
        setTitle("法律法规");
        ButterKnife.bind(this);
    }

    @Override
    protected FaDingFaGuiPresenter createPresenter() {
        return new FaDingFaGuiPresenter(this);
    }

    @Override
    public void bindData() {
        getPresenter().loadLaw(etSeekContent.getText().toString());
    }

    @Override
    public void bindListener() {
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().loadLaw(etSeekContent.getText().toString());
            }
        });
    }

    private void setDefaultFragment(List<FadingFaguiBean.DataListBean> dataLists) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        TiTleFragment title = new TiTleFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("DataList", (Serializable) dataLists);
        bundle1.putSerializable("DataList1", (Serializable) beans1);
        title.setArguments(bundle1);
        transaction.replace(R.id.layout, title);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void loadLaw(List<FadingFaguiBean.DataListBean> dataLists) {
        if (dataLists == null || dataLists.size() == 0) return;
        List<FadingFaguiBean.DataListBean> beans = dataLists;
        beans1 = new ArrayList<>();
        for (int i = 0; i < dataLists.size(); i++) {
            FadingFaguiBean.DataListBean dataListBean = new FadingFaguiBean.DataListBean();
            if (beans.get(i).getMenuName().contains("典型案卷")) {
                String menuName = beans.get(i).getMenuName();
                String[] split = menuName.split("/");
                dataListBean.setMenuName(split[1]);
                dataListBean.setFile(dataLists.get(i).getFile());
                beans.remove(i);
                i--;
                beans1.add(dataListBean);
            }
        }
        FadingFaguiBean.DataListBean dataListBean1 = new FadingFaguiBean.DataListBean();
        if (beans1 != null && beans1.size() > 0) {
            dataListBean1.setMenuName("典型案卷");
            beans.add(dataListBean1);
        }
        setDefaultFragment(beans);
    }
}
