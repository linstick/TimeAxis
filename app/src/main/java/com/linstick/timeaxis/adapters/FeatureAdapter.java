package com.linstick.timeaxis.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linstick.timeaxis.R;
import com.linstick.timeaxis.adapters.callback.OnFeatureItemClick;
import com.linstick.timeaxis.beans.Feature;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {

    private List<Feature> mList;
    private OnFeatureItemClick listener;

    public FeatureAdapter(List<Feature> mList) {
        this.mList = mList;
    }

    public void setOnItemClickListener(OnFeatureItemClick listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main_feature, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.featureIv.setImageResource(mList.get(position).getImgRes());
        holder.titleTv.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_feature)
        ImageView featureIv;
        @BindView(R.id.tv_title)
        TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.iv_feature)
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_feature:
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                    break;
            }
        }
    }
}
