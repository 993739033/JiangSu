package com.wyw.jiangsu.fragment;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.ContentRecAdapter;
import com.wyw.jiangsu.bean.FadingFaguiBean;
import com.wyw.jiangsu.utils.FalvDialog;
import com.wyw.jiangsu.utils.OtherUtil;
import com.wyw.jiangsu.view.DivItemDecoration;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wyw on 2018/1/29.
 */

public class ContentFragment extends Fragment implements ContentRecAdapter.OnItemClick, ContentRecAdapter.OnItemDXClick, FalvDialog.OnItemOpen {

    Unbinder unbinder;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ContentRecAdapter adapter;
    MyReceiver myReceiver;
    private List<FadingFaguiBean.DataListBean> bean1;
    private FadingFaguiBean.DataListBean bean;

    public ContentFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.wyw.jiangsu.contentfragment");
        getActivity().registerReceiver(myReceiver, filter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        setData();
        return view;
    }

    private void setData() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.addItemDecoration(new DivItemDecoration(getActivity(), DivItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onItemClick(List<FadingFaguiBean.DataListBean.FileBean> mData, int position) {
        if (mData == null || mData.size() == 0) return;
        FadingFaguiBean.DataListBean.FileBean bean = mData.get(position);
        String fileName = bean.getFileName();
        String filePath = bean.getFilePath();
        FalvDialog falvDialog1 = new FalvDialog(getActivity(), fileName, filePath);
        falvDialog1.setOnItemOpen(this);
        falvDialog1.show();
    }

    @Override
    public void onItemDXCLick(List<FadingFaguiBean.DataListBean> mData1, int position, int tag) {
        if (mData1 == null || mData1.size() == 0) return;
        FadingFaguiBean.DataListBean.FileBean bean = mData1.get(position).getFile().get(tag);
        String fileName = bean.getFileName();
        String filePath = bean.getFilePath();
        FalvDialog falvDialog2 = new FalvDialog(getActivity(), fileName, filePath);
        falvDialog2.setOnItemOpen(this);
        falvDialog2.show();
    }

    @Override
    public void onItemOpen(String path) {
//        File file = new File(path);
//        if (null == file || !file.exists()) {
//            return;
//        }
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
////        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        Uri contentUri = null;
//        if (Build.VERSION.SDK_INT >= 23) {
//            contentUri = FileProvider.getUriForFile(getActivity(), "com.wyw.jiangsu.fileprovider", file);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
//                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        } else {
//            contentUri = Uri.fromFile(file);
//        }
////        intent.setDataAndType(contentUri, "file/*");
//        intent.setDataAndType(contentUri, "*/*");
//        try {
////            getActivity().startActivity(intent);
//            startActivityForResult(Intent.createChooser(intent, "选择浏览工具"), 1);
////            OtherUtil.openFile(file);
//        } catch (ActivityNotFoundException e) {
//            e.printStackTrace();
//        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/msword");//无类型限制
        intent.setType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");//无类型限制
        intent.setType("application/pdf");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, 1);

    }
    String path;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            Uri uri = data.getData();
            String path = data.getData().getPath();
            File file = new File(path);
            OtherUtil.openFile(file);
//            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
//                this.path = uri.getPath();
//                Toast.makeText(getActivity(), this.path +"11111",Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
//                path = getPath(this, uri);
//                tv.setText(path);
//                Toast.makeText(getActivity(),path,Toast.LENGTH_SHORT).show();
//            } else {//4.4以下下系统调用方法
//                path = getRealPathFromURI(uri);
//                tv.setText(path);
//                Toast.makeText(getActivity(), path+"222222", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.wyw.jiangsu.contentfragment")) {
                bean = (FadingFaguiBean.DataListBean)
                        intent.getSerializableExtra("component");
                bean1 = (List<FadingFaguiBean.DataListBean>)
                        intent.getSerializableExtra("component1");
                save();
            }
        }
    }

    private void save() {
        if (bean1 != null && bean1.size() > 0) {
            adapter = new ContentRecAdapter(getActivity(), bean1, 0);
            adapter.setOnItemDXClick(this);
            recycler.setAdapter(adapter);
        } else {
            List<FadingFaguiBean.DataListBean.FileBean> itemsBeanses = bean.getFile();
            adapter = new ContentRecAdapter(getActivity(), itemsBeanses);
            adapter.setOnItemClick(this::onItemClick);
            recycler.setAdapter(adapter);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getActivity().unregisterReceiver(myReceiver);
    }
}
