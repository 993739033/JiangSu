package com.wyw.jiangsu.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.SlaghterChooseAdapter;
import com.wyw.jiangsu.bean.SlaughterAnimTypeBean;
import com.wyw.jiangsu.bean.SlaughterChooseBean;
import com.wyw.jiangsu.bean.SlaughterSpHouseBean;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ISlaughterChooseActivity;
import com.wyw.jiangsu.presenter.SlaughterChooseActivityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 宰前检查选择界面
 */
public class SlaughterChooseActivity extends MVPActivity<SlaughterChooseActivityPresenter> implements ISlaughterChooseActivity, SpringView.OnFreshListener {

    @BindView(R.id.search_view)
    SearchView searchView;
//    @BindView(R.id.bt_back)
//    ImageButton btBack;
    @BindView(R.id.sp_searchUser)
    TextView spSearchUser;
    @BindView(R.id.sp_searchAnimType)
    TextView spSearchAnimType;
    @BindView(R.id.search_toolbar)
    LinearLayout searchToolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.results_container)
    FrameLayout resultsContainer;

    SlaghterChooseAdapter adapter;
    String tid = "";
    String animType = "";
    @BindView(R.id.btn_determine)
    Button btnDetermine;

    private DataSelectDialog farmSelectDialog;
    private DataSelectDialog animTypeSelectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slaughter_choose);
        ButterKnife.bind(this);
    }

    @Override
    protected SlaughterChooseActivityPresenter createPresenter() {
        return new SlaughterChooseActivityPresenter(this);
    }

    @Override
    public void bindData() {
        getPresenter().getSpSlaughterData();
        adapter = new SlaghterChooseAdapter(new ArrayList<>());
        setTitle("申报单编号查询");

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(this));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(this));
        springView.setListener(this);

//        getPresenter().getDataList(tid,animType,"");

        initDialog();

    }

    private void initDialog() {
        farmSelectDialog = new DataSelectDialog(this);
        farmSelectDialog.setTitle("选择屠宰场");
        farmSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spSearchUser.setText(data);
                setSpAnimTypeData(new ArrayList<>());
                tid = getPresenter().getSpHouseList().get(farmSelectDialog.getNowIndex()).getFSenterpriseId();
                getPresenter().getAnimaType(tid);
                getPresenter().getDataList(tid, animType, "");
            }

            @Override
            public void cancel() {

            }
        });

        spSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farmSelectDialog.show();
            }
        });


        animTypeSelectDialog = new DataSelectDialog(this);
        animTypeSelectDialog.setTitle("选择屠宰场");
        animTypeSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spSearchAnimType.setText(data);
                if (getPresenter().getSpAnimTypeList().size() > 0) {
                    animType = getPresenter().getSpAnimTypeList().get(animTypeSelectDialog.getNowIndex()).getQCProduct();
                } else {
                    animType = "";
                }
                getPresenter().getDataList(tid, animType, "");
            }

            @Override
            public void cancel() {

            }
        });

        spSearchAnimType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animTypeSelectDialog.show();
            }
        });
    }

    @Override
    public void bindListener() {
        // TODO: 2018/1/4
//        spSearchUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                setSpAnimTypeData(new ArrayList<>());
//                tid = getPresenter().getSpHouseList().get(position).getFSenterpriseId();
//                getPresenter().getAnimaType(tid);
//                getPresenter().getDataList(tid, animType, "");
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        // TODO: 2018/1/4
//        spSearchAnimType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (getPresenter().getSpAnimTypeList().size() > 0) {
//                    animType = getPresenter().getSpAnimTypeList().get(position).getQCProduct();
//                } else {
//                    animType = "";
//                }
//                getPresenter().getDataList(tid, animType, "");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        btnDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SlaughterChooseBean.DataListEntity> list = new ArrayList<SlaughterChooseBean.DataListEntity>();

                for (int i = 0; i < adapter.getData().size(); i++) {
                    if (adapter.getData().get(i).isCheak()) {
                        list.add(adapter.getData().get(i));
                    }
                }

                Intent intent = new Intent();
                intent.putExtra(Constance.ACTIVITY_DATA, (Serializable) list);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
    }

    @Override
    public void onError() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void refreshNone() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onRefresh() {
        viewEmpty.setVisibility(View.GONE);
        springView.onFinishFreshAndLoad();
        getPresenter().refresh();
    }

    @Override
    public void onLoadmore() {
        getPresenter().loadMore();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setupSearvhView() {
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("请输入要查询的关键字");
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().getDataList(tid, animType, query);
                viewEmpty.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    getPresenter().getDataList(tid, animType, newText);
                }
                return true;
            }
        });
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && TextUtils.isEmpty(searchView.getQuery())) {
                getPresenter().getDataList(tid, animType, "");
            }
        });
    }

    @Override
    public void loadMore(List<SlaughterChooseBean.DataListEntity> list) {
        if (list == null) {
            Toast.makeText(this, "没有数据了", Toast.LENGTH_SHORT).show();
        } else {
            adapter.addData(list);
        }
        springView.onFinishFreshAndLoad();
    }


    @Override
    public void setSpSlaughterData(List<SlaughterSpHouseBean.DataListEntity> list) {
        ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            strList.add(list.get(i).getFSenterpriseName());
        }
        // TODO: 2018/1/4
//        spSearchUser.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                strList));

        farmSelectDialog.setDatas(strList);
    }

    @Override
    public void setSpAnimTypeData(List<SlaughterAnimTypeBean.DataListEntity> list) {
        ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            strList.add(list.get(i).getQCProduct());
        }
//        spSearchAnimType.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                strList));
        animTypeSelectDialog.setDatas(strList);
        setupSearvhView();
    }

    @Override
    public void setDataList(List<SlaughterChooseBean.DataListEntity> list) {
        if (list.size() == 0) {
            viewEmpty.setVisibility(View.VISIBLE);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(list);
        }
    }
}
