package com.maijia.QR.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maijia.QR.R;

import java.text.MessageFormat;

/**
 * @author：created by zhaoliang
 * @date 2019/3/14 15:03
 * @desc：
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    /**
     * 定义数组
     */
    private int selPos;
    private boolean groupWhite;
    private Context mContext;
    private int[] images = new int[]{
            R.drawable.ic_arrow_back,
            R.drawable.ic_vector_delete,
            R.drawable.ic_vector_check
    };
    private String[] armTypes;
    private String[][] arms;

    public ExpandableListAdapter(Context mContext, String[] armTypes, String[][] arms) {
        this.mContext = mContext;
        this.armTypes = armTypes;
        this.arms = arms;
    }


    //组元素表示可折叠的列表项，子元素表示列表项展开后看到的多个子元素项
    @Override
    public int getGroupCount() {
        return armTypes.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arms[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return armTypes[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arms[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getCombinedGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //获取一个视图显示给定组，存放armTypes
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        TextView textView = getTextView();//调用定义的getTextView()方法
//        textView.setText(getGroup(groupPosition).toString());//添加数据
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.group_show, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (selPos == groupPosition && !groupWhite) {
            groupViewHolder.llRoot.setBackgroundColor(Color.parseColor("#F9F9F9"));
        } else {
            groupViewHolder.llRoot.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        //设置数据
        groupViewHolder.tvAmount.setText(armTypes[groupPosition]);
        if (isExpanded) {
            groupViewHolder.imageView.setImageResource(R.drawable.permission_ic_location);
        }else {
            groupViewHolder.imageView.setImageResource(R.drawable.permission_ic_phone);
        }
        return convertView;
    }

    //获取二个视图显示给定组，存放arms
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        LinearLayout ll = new LinearLayout(mContext);
//        ll.setOrientation(LinearLayout.VERTICAL);//定义为纵向排列
//        ImageView logo = new ImageView(mContext);
//        logo.setImageResource(images[groupPosition]);//添加图片
//        ll.addView(logo);
//        TextView textView = getTextView();//调用定义的getTextView()方法
//        textView.setText(getChild(groupPosition, childPosition).toString());//添加数据
//        ll.addView(textView);
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.children_show, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //设置数据
        childViewHolder.tvTitle1.setText(MessageFormat.format("{0}:  {1}", arms[groupPosition][childPosition], arms[groupPosition][childPosition]));

        return convertView;
    }

    //定义一个TextView
//    private TextView getTextView() {
//        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120);
//
//        TextView textView = new TextView(mContext);
//        textView.setGravity(Gravity.CENTER_VERTICAL);
//        textView.setLayoutParams(lp);
//        textView.setPadding(65, 5, 0, 5);
//        textView.setTextSize(20);
//        return textView;
//    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        TextView tvPhone, tvAmount, tvTime, tvSale;
        ImageView imageView;
        private final LinearLayout llRoot;

        public GroupViewHolder(View view) {
//            tvPhone=view.findViewById(R.id.tvPhone);
//            tvTime=view.findViewById(R.id.tvTime);
//            tvSale=view.findViewById(R.id.tvSale);
            tvAmount = view.findViewById(R.id.text_view_group);
            imageView = view.findViewById(R.id.iv_image);
            llRoot = view.findViewById(R.id.llRoot);
        }
    }

    class ChildViewHolder {

        TextView tvTitle1, tvTitle2, tvTitle3, tvTitle4;

        public ChildViewHolder(View view) {
            tvTitle1 = view.findViewById(R.id.text_view_child);
//            tvTitle2=view.findViewById(R.id.tvTitle2);
//            tvTitle3=view.findViewById(R.id.tvTitle3);
//            tvTitle4=view.findViewById(R.id.tvTitle4);
        }
    }

    public void setSelPos(int pos, boolean bgWhite) {
        this.selPos = pos;
        this.groupWhite = bgWhite;
    }
}

