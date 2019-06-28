package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.FadingFaguiBean;

import java.io.File;
import java.util.List;

import javax.annotation.Nullable;

import static com.wyw.jiangsu.utils.FileUtil.getFalv;

/**
 * Created by wyw on 2018/1/31.
 */

public class ContentRecAdapter extends RecyclerView.Adapter<ContentRecAdapter.ViewHolder> {
    private List<FadingFaguiBean.DataListBean> mData1;
    private List<FadingFaguiBean.DataListBean.FileBean> mData;
    private Context mContext;

    private OnItemClick onItemClick;
    private OnItemDXClick onItemDXCLick;

    private LayoutInflater inflater;

    public interface OnItemClick {
        void onItemClick(List<FadingFaguiBean.DataListBean.FileBean> mData, int position);
    }

    public interface OnItemDXClick {
        void onItemDXCLick(List<FadingFaguiBean.DataListBean> mData1, int position, int tag);
    }

    public void setOnItemDXClick(OnItemDXClick onItemDXCLick) {
        this.onItemDXCLick = onItemDXCLick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public ContentRecAdapter(Context context, List<FadingFaguiBean.DataListBean.FileBean> mData) {
        if (mData1 != null) {
            mData1.clear();
        }

        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.mData = mData;
    }

    public ContentRecAdapter(Context context, List<FadingFaguiBean.DataListBean> bean1, int position) {
        if (mData != null) {
            mData.clear();
        }
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.mData1 = bean1;
    }


    @Override
    public int getItemCount() {
        if (mData1 == null) {
            return mData == null ? 0 : mData.size();
        } else {
            return mData1.size();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.content_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mData1 == null) {
            File[] files = getFalv().listFiles();
            if (files.length != 0) {
                for (File file : files) {
                    if (mData.get(position).getFileName().equals(file.getName())) {
                        holder.title.setTextColor(mContext.getResources().getColor(R.color.bule));
                        break;
                    } else {
                        holder.title.setTextColor(mContext.getResources().getColor(R.color.black));
                    }
                }
            }
            holder.title.setText(mData.get(position).getFileName());
            //绑定icon
            bindIcon(holder.img_icon, position, FadingFaguiBean.DataListBean.FileBean.class.getName(), -1);

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick != null) {
                        onItemClick.onItemClick(mData, position);
                    }
                }
            });
            holder.linAdd.setFocusable(false);
            holder.linAdd.setVisibility(View.GONE);
        } else {
            holder.linAdd.setVisibility(View.VISIBLE);
            holder.title.setText(mData1.get(position).getMenuName());
            holder.title.setTextColor(mContext.getResources().getColor(R.color.orange));
            holder.img_icon.setVisibility(View.GONE);
            holder.img_top_icon.setVisibility(View.VISIBLE);

            holder.linAdd.removeAllViews();
            View v = new View(mContext);
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
            v.setBackgroundResource(R.color.background);
            holder.linAdd.addView(v);
            File[] files = getFalv().listFiles();

            for (int m = 0; m < mData1.get(position).getFile().size(); m++) {
                View view = inflater.inflate(R.layout.item_fadingfagui_tv, null);
                ImageView img = (ImageView) view.findViewById(R.id.img_icon);
                TextView tv = (TextView) view.findViewById(R.id.tv_content);
                bindIcon(img, position, FadingFaguiBean.DataListBean.class.getName(), m);
                tv.setText(mData1.get(position).getFile().get(m).getFileName());
                tv.setTag(m);
                if (files.length != 0) {
                    for (File file : files) {
                        if (mData1.get(position).getFile().get(m).getFileName().equals(file.getName())) {
                            tv.setTextColor(mContext.getResources().getColor(R.color.bule));
                            break;
                        } else {
                            tv.setTextColor(mContext.getResources().getColor(R.color.black));
                        }
                    }
                }

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int tag = (int) tv.getTag();
                        if (onItemDXCLick != null) {
                            onItemDXCLick.onItemDXCLick(mData1, position, tag);
                        }
                    }
                });
                holder.linAdd.addView(view);
            }
        }
    }

    private void bindIcon(ImageView img, int position, String className, @Nullable int m) {
        //添加icon
        String[] s = new String[3];
        if (className.equals(FadingFaguiBean.DataListBean.FileBean.class.getName())) {
            s = mData.get(position).getFileName().split("\\.");
        } else if (className.equals(FadingFaguiBean.DataListBean.class.getName())) {
            s = mData1.get(position).getFile().get(m).getFileName().split("\\.");
        }
        try {
            for (String content : s) {
                if (content.contains("docx")) {
                    img.setBackgroundResource(R.drawable.docx);
                } else if (content.contains("doc")) {
                    img.setBackgroundResource(R.drawable.doc);
                } else if (content.contains("xlsx")) {
                    img.setBackgroundResource(R.drawable.xlsx);
                } else if (content.contains("xls")) {
                    img.setBackgroundResource(R.drawable.xls);
                } else if (content.contains("pdf")) {
                    img.setBackgroundResource(R.drawable.pdf);
                } else {
                    img.setBackgroundResource(R.drawable.rar);
                }
            }
        } catch (Exception e) {
            img.setVisibility(View.GONE);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,img_top_icon;
        private LinearLayout linAdd;
        private LinearLayout layout;
        private ImageView img_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            linAdd = (LinearLayout) itemView.findViewById(R.id.lin_add);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            img_top_icon = (TextView) itemView.findViewById(R.id.img_top_icon);
        }
    }
}
