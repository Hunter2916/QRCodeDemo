package com.maijia.QR.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maijia.QR.R;
import com.maijia.QR.bean.MsgBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：created by zhaoliang
 * @date 2019/3/5 09:39
 * @desc：
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private Context context;
    /**
     * 消息列表数据
     */
    private List<MsgBean> lists;

    /**
     * 标记展开的item
     */
    private int opened = -1;

    public MsgAdapter(Context context) {
        this.context = context;
        lists = new ArrayList<>();
    }

    /**
     * 设置列表数据
     * @param lists
     */
    public void setLists(List<MsgBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindView(position,lists.get(position));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView msgTime;
        private TextView msgContent;
        private TextView msgContentMore;
        private RelativeLayout msgRl;
        private LinearLayout msgLl;

        public ViewHolder(View itemView) {
            super(itemView);
            msgTime = (TextView) itemView.findViewById(R.id.msg_time);
            msgContent = (TextView) itemView.findViewById(R.id.msg_content);
            msgContentMore = (TextView) itemView.findViewById(R.id.msg_contentMore);
            msgRl = (RelativeLayout) itemView.findViewById(R.id.msg_rl);
            msgLl = (LinearLayout) itemView.findViewById(R.id.msg_ll);
            msgRl.setOnClickListener(this);
        }

        /**
         * 此方法实现列表数据的绑定和item的展开/关闭
         */
        void bindView(int pos, MsgBean bean) {
            msgTime.setText(bean.created);
            msgContent.setText(bean.content);
            msgContentMore.setText(bean.contentMore);

            if (pos == opened){
                msgLl.setVisibility(View.VISIBLE);
            } else{
                msgLl.setVisibility(View.GONE);
            }

        }
        /**
         * item的点击事件
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (opened == getAdapterPosition()) {
                //当点击的item已经被展开了, 就关闭.
                opened = -1;
                notifyItemChanged(getAdapterPosition());
            } else {
                int oldOpened = opened;
                opened = getAdapterPosition();
                notifyItemChanged(oldOpened);
                notifyItemChanged(opened);
            }
        }
    }
}