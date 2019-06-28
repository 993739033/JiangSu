package com.wyw.jiangsu.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.ViewPagerAdapter;
import com.wyw.jiangsu.fragment.AnimProductionRecordFragment;
import com.wyw.jiangsu.fragment.AnimRuZhongYongRecordFragment;
import com.wyw.jiangsu.fragment.AnimZhongDanRecordFragment;
import com.wyw.jiangsu.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 工作记录单 包含动物产地检疫工作记录单
 *                  乳用、种用动物检疫工作记录单
 */
public class AnimWorkRecordActivity extends BaseActivity{
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_work_record);
    }
    @Override
    public void bindData() {
        setTitle("工作记录单");
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        title.add("动物产地工作记录单");
        title.add("乳用、种用动物工作记录单");
        title.add("种蛋、胚胎、精液检疫工作记录单");
        fragments.add(new AnimProductionRecordFragment());
        fragments.add(new AnimRuZhongYongRecordFragment());
        fragments.add(new AnimZhongDanRecordFragment());
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,title);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void bindListener() {

    }
}
