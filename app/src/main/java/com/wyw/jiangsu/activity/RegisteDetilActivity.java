package com.wyw.jiangsu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.fragment.DwyzFragment;
import com.wyw.jiangsu.fragment.DwzlFragment;
import com.wyw.jiangsu.fragment.SlscFragment;
import com.wyw.jiangsu.fragment.SxrFragment;
import com.wyw.jiangsu.fragment.SyjyRegisteFragment;
import com.wyw.jiangsu.fragment.SyscFragment;
import com.wyw.jiangsu.fragment.TzcFragment;
import com.wyw.jiangsu.fragment.WhhFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HUANG on 2017/9/6.
 */
public class RegisteDetilActivity extends AppCompatActivity {
    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    private Fragment curFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registedetil);
        ButterKnife.bind(this);
        ft = getSupportFragmentManager().beginTransaction();
        int from = getIntent().getIntExtra("from", 0);
        switch (from){

            case 1:
                curFragment = new SyjyRegisteFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
            case 2:
                curFragment =new TzcFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
            case 3:
                curFragment =new DwzlFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
            case 4:

                curFragment =new WhhFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
            case 5:
                curFragment =new SlscFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
            case 6:
                curFragment =new SyscFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
            case 7:
                curFragment =new DwyzFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
            case 8:
                curFragment =new SxrFragment();
                ft.add(R.id.fragment_layout, curFragment);
                ft.commit();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ft.remove(curFragment);
    }
}
