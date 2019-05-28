package com.maijia.QR.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.QR.R;
import com.maijia.QR.bean.HomePageData;
import com.maijia.QR.databinding.HomePageRecycleItemBinding;
import com.maijia.QR.listener.OnBack1Listener;

import java.util.List;

/**
 * Created by zhaoliang on 2018/6/19 0019
 */

public class NestedScrollViewAdapter extends RecyclerView.Adapter<NestedScrollViewAdapter.MyHolder> {
    HomePageRecycleItemBinding binding;
    private List<HomePageData> dataList;
    private Context context;
    private LayoutInflater inflater;
    private View mHeaderView;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public NestedScrollViewAdapter(List<HomePageData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    /**
     * 创建hodler对象
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (mHeaderView != null && viewType == TYPE_HEADER) {
//            return new MyHolder(mHeaderView);
//        }
        binding = DataBindingUtil.inflate(inflater, R.layout.home_page_recycle_item, parent, false);
        return new MyHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
//        if (getItemViewType(position) == TYPE_HEADER) {
//            return;
//        }
//        int pos = getRealPosition(holder);
//        HomePageData homePageData = dataList.get(pos);
        HomePageData homePageData = dataList.get(position);
        binding.title.setText(homePageData.getTitle());
        binding.time.setText(homePageData.getTime());
        if (holder != null) {
            binding.imageView.setImageResource(homePageData.getImageId());
            binding.llItemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.bank1(position );
                    }
                }
            });
        }


    }

//    public int getRealPosition(RecyclerView.ViewHolder holder) {
//        int position = holder.getLayoutPosition();
//        return mHeaderView == null ? position : position - 1;
//    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? dataList.size() : dataList.size() + 1;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (mHeaderView == null) {
//            return TYPE_NORMAL;
//        }
//        if (position == 0) {
//            return TYPE_HEADER;
//        }
//        return TYPE_NORMAL;
//    }

    class MyHolder extends RecyclerView.ViewHolder {
        MyHolder(View itemView) {
            super(itemView);
        }
    }

    public void setHeader(View view) {
        this.setHeaderView(view);
    }

    private OnBack1Listener mListener;

    public void setOnBack1Listener(OnBack1Listener mListener) {
        this.mListener = mListener;
    }
}
