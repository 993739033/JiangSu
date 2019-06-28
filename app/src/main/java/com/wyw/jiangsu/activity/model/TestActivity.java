package com.wyw.jiangsu.activity.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.BaseMsg;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements SpringViewListener{

    RecyclerListener<BaseMsg> listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ArrayList<BaseMsg> baseMsgs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BaseMsg bean = new BaseMsg();
            bean.setErrorMsg("error:"+i);
            bean.setErrorCode(i);
            baseMsgs.add(bean);
        }
        listener = new RecyclerModel<>(((ViewGroup) findViewById(R.id.container)), this, new MyAdapter(baseMsgs));
    }


    class MyAdapter extends BaseQuickAdapter<BaseMsg>{

        public MyAdapter(List<BaseMsg> data) {
            super(R.layout.layout_test_adapter,data);

        }

        @Override
        protected void convert(BaseViewHolder helper, BaseMsg item) {
            helper.setText(R.id.tv,item.getErrorMsg());
        }
    }

    @Override
    public void refresh() {
        //从服务器获取数据
        //getPresenter().load()
        //返回刷新ui
        List<BaseMsg> mDatas = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            BaseMsg bean = new BaseMsg();
            bean.setErrorMsg("search error:"+i);
            bean.setErrorCode(i);
            mDatas.add(bean);
        }
        listener.refresh(mDatas);
    }

    @Override
    public void loadMore() {
        List<BaseMsg> mDatas = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            BaseMsg bean = new BaseMsg();
            bean.setErrorMsg("load error:"+i);
            bean.setErrorCode(i);
            mDatas.add(bean);
        }
        listener.loadmore(mDatas);
   }
}
