package com.wyw.jiangsu.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Deprecated
public class FileExploreActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    FileExploreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explore);
    }


    @Override
    public void bindData() {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FileExploreAdapter(null);
        recycler.setAdapter(adapter);
        loadAdapter(Environment.getExternalStorageDirectory());
    }

    /**
     *
     * @param rootDir dir
     */
    public void loadAdapter(File rootDir) {
        adapter.clear();
        List<FileExploreBean> data = new ArrayList<>();
        for (File file : rootDir.listFiles()) {
            FileExploreBean bean = new FileExploreBean();
            bean.setName(file.getName())
                    .setPath(file.getAbsolutePath())
                    .setType(file.isDirectory() ? 0 : 1);
            data.add(bean);
        }
        adapter.addData(data);
    }

    @Override
    public void bindListener() {

    }
    public class FileExploreAdapter extends BaseQuickAdapter<FileExploreBean> {

        public FileExploreAdapter(List<FileExploreBean> data) {
            super(R.layout.adapter_file_explore, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FileExploreBean item) {
            helper.setText(R.id.tv_file_name, item.getName());
        }
    }
    class FileExploreBean {
        private String name;
        private String path;
        private int type;//文件还是文件夹 0 文件夹  1 文件

        public String getName() {
            return name;
        }

        public FileExploreBean setName(String name) {
            this.name = name;
            return this;
        }

        public String getPath() {
            return path;
        }

        public FileExploreBean setPath(String path) {
            this.path = path;
            return this;
        }

        public int getType() {
            return type;
        }

        public FileExploreBean setType(int type) {
            this.type = type;
            return this;
        }
    }

}
