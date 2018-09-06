package com.linstick.timeaxis.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linstick.timeaxis.R;
import com.linstick.timeaxis.adapters.callback.OnFeatureItemClick;
import com.linstick.timeaxis.beans.Feature;

import java.util.List;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class FeatureAdapter extends BaseAdapter {
    private static final String TAG = "FeatureAdapter";

    private List<Feature> mList;
    private OnFeatureItemClick listener;

    public FeatureAdapter(List<Feature> mList) {
        this.mList = mList;
    }

    public void setOnItemClickListener(OnFeatureItemClick listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main_feature, parent, false);
        ((ImageView) view.findViewById(R.id.iv_feature)).setImageResource(mList.get(position).getImgRes());
        ((TextView) view.findViewById(R.id.tv_title)).setText(mList.get(position).getTitle());
        return view;
    }

    public void onItemClick(View view, int position) {
        if (listener != null) {
            listener.onItemClick(position);
        }
    }
}
