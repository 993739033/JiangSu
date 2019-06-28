package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.SimpleListAdapter;
import com.wyw.jiangsu.bean.upload.SeekBean;
import com.wyw.jiangsu.fragment.QueryFragment;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ISeekActivity;
import com.wyw.jiangsu.presenter.SeekActivityPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

;

public class SeekActivity extends MVPActivity<SeekActivityPresenter> implements ISeekActivity {

    @BindView(R.id.lv_list)
    ListView lvList;
    private SimpleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        ButterKnife.bind(this);
    }

    @Override
    protected SeekActivityPresenter createPresenter() {
        return new SeekActivityPresenter(this);
    }

    public ArrayList<SeekBean> getData() {
        ArrayList<SeekBean> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            SeekBean bean = new SeekBean();
            if (i == 0) {
                bean.setItmeType(0);
                bean.setItemTitle("条目" + (i + 1));
            } else {
                bean.setItmeType(1);
                bean.setItemTitle("条目" + (i + 1));
            }
            list.add(bean);
        }
        return list;
    }

    @Override
    public void bindData() {
        getAdd().setVisibility(View.VISIBLE);
        setTitle("搜索目录");
        adapter = new SimpleListAdapter(this, getData());
        lvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void bindListener() {
        getAdd().setOnClickListener(v -> startActivityForResult(new Intent(this, QueryFragment.class), Constance.ACTIVITY_REQUEST_CODE));
    }

}
