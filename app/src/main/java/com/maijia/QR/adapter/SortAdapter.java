package com.maijia.QR.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maijia.QR.R;
import com.maijia.QR.bean.SortData;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.StyleViewHolder> {
    private Context context;
    private List<SortData> list;
    private GridLayoutManager manager;


    public SortAdapter(Context context,  List<SortData> list) {
        this.context = context;
        this.manager = manager;
        this.list = list;
    }


    @Override
    public StyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StyleViewHolder holder;

            holder = new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_sort_recyclerveiw, parent,
                    false));

        return holder;
    }

    @Override
    public void onBindViewHolder(StyleViewHolder holder, int position) {
        holder.textView.setImageResource(list.get(position).getPic());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class StyleViewHolder extends RecyclerView.ViewHolder {

        ImageView textView;

        public StyleViewHolder(View view) {
            super(view);
        }
    }

    class ViewHolder1 extends StyleViewHolder {
        public ViewHolder1(View view) {
            super(view);
            textView = (ImageView) view.findViewById(R.id.iv_img);
        }
    }



//    class StyleData {
//        int type;
//        String item;
//    }
}






