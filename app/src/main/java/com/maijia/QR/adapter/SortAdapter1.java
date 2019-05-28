package com.maijia.QR.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.QR.R;
import com.maijia.QR.bean.SortData;
import com.maijia.QR.databinding.ItemSortRecyclerveiwBinding;

import java.util.List;

public class SortAdapter1 extends RecyclerView.Adapter<SortAdapter1.ViewHolder> {
    private List<SortData> dataList;
    private Context context;
    private LayoutInflater inflater;
    ItemSortRecyclerveiwBinding binding;

    public SortAdapter1(List<SortData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_sort_recyclerveiw, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SortData sortData = dataList.get(position);
        binding.ivImg.setImageResource(sortData.getPic());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
