package com.wwzhang.rxjava2demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wwzhang.rxjava2demo.R;

import java.util.List;

/**
 * Created by wwzhang on 2019-07-16
 */
public class RxDemoAdapter extends RecyclerView.Adapter<RxDemoAdapter.RxDemoViewHolder> {

    private List<String> data;

    public RxDemoAdapter(List<String> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public RxDemoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_rx_demo_item, viewGroup);
        return new RxDemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RxDemoViewHolder rxDemoViewHolder, int i) {
        rxDemoViewHolder.tvTitle.setText(data.get(i));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class RxDemoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;

        public RxDemoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
