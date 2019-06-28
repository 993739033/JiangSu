package com.wyw.jiangsu.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.ErWEiMaActivity;
import com.wyw.jiangsu.activity.FaDingFaGuiActivity;
import com.wyw.jiangsu.activity.ShowSdActivity;
import com.wyw.jiangsu.activity.flow.HarmlessFlow1Activity;
import com.wyw.jiangsu.activity.flow.QuarantineFlowActivity;
import com.wyw.jiangsu.activity.flow.SlaughterQuarantineFlowActivity;
import com.wyw.jiangsu.activity.supervision.CommonSupervisionListActivity;
import com.wyw.jiangsu.activity.zhifa.ZhifaActivity;
import com.wyw.jiangsu.adapter.HomeBeanAdapeter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.IHomeFragment;
import com.wyw.jiangsu.presenter.HomeFragmentPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wyw on 2016/12/20.
 */

public class HomeFragment extends MVPFragment<HomeFragmentPresenter> implements IHomeFragment, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private HomeBeanAdapeter adapter;
    protected boolean isPrepared;
    private static final int REQUEST_CODE_ACTIVITY_ZXING = 1000;

    @Override
    protected HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter(this);
    }

    @Override
    protected void onInvisible() {

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || adapter != null) {
            return;
        }
        bindData();
        bindListener();
    }


    @Override
    protected int getResId() {
        return R.layout.fragment_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "39":
                    //产地检疫
//                    adapter.notifyDataSetChanged();
                    startActivity(new Intent(getContext(), QuarantineFlowActivity.class));
                    break;
                case "9":
                    //无害化处理`
                    startActivity(new Intent(getContext(), HarmlessFlow1Activity.class));
                    break;
                case "42":
                    //屠宰检疫
                    startActivity(new Intent(getContext(), SlaughterQuarantineFlowActivity.class));
                    break;
                case "54":
                    //日常监管
                    startActivity(new Intent(getContext(), CommonSupervisionListActivity.class));
                    break;
                case "55":
                    //通知公告
                    startActivity(new Intent(getContext(), ShowSdActivity.class));
                    break;
                //执法
                case "49":
                    startActivity(new Intent(getContext(), ZhifaActivity.class));
                    break;
                //兽药追溯
                case "56":
                    startActivityForResult(new Intent(getContext(), CaptureActivity.class), REQUEST_CODE_ACTIVITY_ZXING);
                    break;
                //法律法规
                case "72":
                    startActivity(new Intent(getContext(), FaDingFaGuiActivity.class));
                    break;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void bindData() {
//        List<HomeBean.DataList> list1 = (List<HomeBean.DataList>) SPUtils.getInstance().getObjectData(Constance.DATA_PERMISSION);
//        for (int i = 0; i < list1.size(); i++) {
//            if ("0".equals(list1.get(i).getFmParent()) && "0".equals(list1.get(i).getType())) {
//                list.add(list1.get(i));
//            }
//        }
        adapter = new HomeBeanAdapeter(null);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        ((SimpleItemAnimator)recycler.getItemAnimator()).setSupportsChangeAnimations(false);
        recycler.setAdapter(adapter);
        swipeLayout.post(() -> swipeLayout.setRefreshing(true));
        presenter.refresh();

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDownDialogProgress(long bytesRead, long contentLength, boolean done) {

    }

    @Override
    public void showApkDialog() {

    }

    @Override
    public void onDownloadDone(int type) {

    }

    @Override
    public void showDbDialog(String version) {

    }

    @Override
    public void recordSuccessful() {

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void refreshComplete(List<HomeBean.DataList> dataLists) {
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
        ArrayList<HomeBean.DataList> list = new ArrayList<>();
        List<UploadNoDealWithBean.DataListBean> upload = new ArrayList<>();
        for (int i = 0; i < dataLists.size(); i++) {
//            if ("0".equals(dataLists.get(i).getFmParent()) && "0".equals(dataLists.get(i).getType())) {
            if ("47".equals(dataLists.get(i).getFmParent()) && "0".equals(dataLists.get(i).getType())) {
                list.add(dataLists.get(i));
            }
            if (dataLists.get(i).getFSm2().equals("1")) {
                UploadNoDealWithBean.DataListBean bean = new UploadNoDealWithBean.DataListBean();
                bean.setQxid(Integer.parseInt(dataLists.get(i).getFSm1()));
                bean.setTableName(dataLists.get(i).getFSm3());
                upload.add(bean);
            }
//            }
        }
        adapter.setNewData(list);
//        adapter.notifyDataSetChanged();
        UploadNoDealWithBean uploadNoDealWithBean = new UploadNoDealWithBean();
        uploadNoDealWithBean.setDataList(upload);
        getPresenter().withoutDealMsg(uploadNoDealWithBean);
    }


    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {
        for (int i = 0; i < dataLists.size(); i++) {
            for (int j = 0; j < adapter.getData().size(); j++) {
                if (adapter.getData().get(j).getFSm1().equals(dataLists.get(i).getQxid())) {
//                        list.get(j).setNumber(dataLists.get(i).getSum());
                    adapter.getData().get(j).setNumber(dataLists.get(i).getSum());
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(RefreshBus number) {
//        if (number.getNumber()==5){
//            Log.e("text","HomeFragment");
//        }
        if (number.getNumber() == 4) {
            presenter.refresh();
        }
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ACTIVITY_ZXING) {
                if (data != null && data.getExtras() != null) {
                    String result = data.getExtras().getString("result");
                    String s = result.substring(0, 24);
                    Intent intent = new Intent(getContext(), ErWEiMaActivity.class);
                    intent.putExtra("Code", s);
                    startActivity(intent);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
