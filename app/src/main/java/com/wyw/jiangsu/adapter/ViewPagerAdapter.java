package com.wyw.jiangsu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wyw.jiangsu.fragment.BaseFragment;

import java.util.List;

/**
 * Created by wyw on 2016/8/19.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;
    private List<String> title;
    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> mFragments, List<String> title) {
        super(fm);
        this.mFragments = mFragments;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null?0:mFragments.size();
    }
    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return title==null?null:title.get(position);
    }
}
