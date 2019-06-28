package com.wyw.jiangsu.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wyw on 2016/8/19.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    List<String> title;

    public MyViewPagerAdapter(List<View> views, List<String> title) {
        this.views = views;
        this.title = title;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (title!=null){
            return title.get(position) == null?"":title.get(position);
        }else {
            return "";
        }
    }
}
