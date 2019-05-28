package com.maijia.QR.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maijia.QR.R;
import com.maijia.QR.bean.StyleData;

import java.util.List;

public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.StyleViewHolder> {
    private Context context;
    private List<StyleData> list;
    private GridLayoutManager manager;


    public StyleAdapter(Context context, GridLayoutManager manager, List<StyleData> list) {
        this.context = context;
        this.manager = manager;
        this.list = list;
        manager.setSpanSizeLookup(new GridSpanSizeLookup());
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    @Override
    public StyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StyleViewHolder holder;
        if (viewType == 4) {
            holder = new ViewHolder4(LayoutInflater.from(context).inflate(R.layout.item_type_4, parent,
                    false));
        } else if (viewType == 3) {
            holder = new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.item_type_3, parent,
                    false));
        } else if (viewType == 2) {
            holder = new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.item_type_2, parent,
                    false));
        } else {
            holder = new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_type_1, parent,
                    false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(StyleViewHolder holder, int position) {
        holder.textView.setText(list.get(position).content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class StyleViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public StyleViewHolder(View view) {
            super(view);
        }
    }

    class ViewHolder1 extends StyleViewHolder {
        public ViewHolder1(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }
    }

    class ViewHolder2 extends StyleViewHolder {
        public ViewHolder2(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }
    }

    class ViewHolder3 extends StyleViewHolder {
        public ViewHolder3(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }
    }

    class ViewHolder4 extends StyleViewHolder {
        public ViewHolder4(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }
    }

    //GridSpanSizeLookup.getSpanSize 返回1表示占1个span，返回gridManager.getSpanCount()表示占用整行
    public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {

            //return gridManager.getSpanCount();
            return list.get(position).type;
        }
    }

//    class StyleData {
//        int type;
//        String item;
//    }
}






