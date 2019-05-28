package com.maijia.QR.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;
import com.maijia.QR.R;
import com.maijia.QR.adapter.StyleAdapter;
import com.maijia.QR.bean.SortData;
import com.maijia.QR.bean.StyleData;

import java.util.ArrayList;
import java.util.List;

/**
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    TextView textView;
    private CustomPopWindow popWindow;
    private RecyclerView recyclerView;
//   private List<SortData> list=new ArrayList<>();

    public static HomeFragment newInstance(String text) {
        HomeFragment fragmentCommon = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", "首页");
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        textView.setText(getArguments().getString("text"));
        textView.setText("首页");
        initView();
        return view;
    }

    private void initView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopBottom();
            }
        });
//        list.add(new SortData(R.mipmap.b1));
//        list.add(new SortData(R.mipmap.b2));
//        list.add(new SortData(R.mipmap.b3));

        //初始化recyclerview
        List<StyleData> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StyleData data = new StyleData();
            data.item = "" + i;
            data.content = "你是大哥的时仍搜你发个脑的" + i;
            if (i > 3 && i < 7 || i == 11 || i == 13) {
                data.type = 2;//占两个span
            }else if (i == 15 || i == 18) {
                data.type = 3;//占三个span
            }else if(i == 19){
                data.type = 4;//占四个span
            }else {
                data.type = 1;//占一个span
            }
            list.add(data);
        }
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
//        handZoneData(list);
//        SortAdapter adapter=new SortAdapter(getActivity(),list);
        StyleAdapter adapter = new StyleAdapter(getActivity(), manager, list);
        recyclerView.setAdapter(adapter);
    }

    private void handZoneData(List<SortData> list) {

    }

    private void showPopBottom() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout1, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        popWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .setFocusable(true)
                .setOutsideTouchable(false)
                .create();
        popWindow.showAsDropDown(textView, 0, 10);
    }


    private void handleLogic(final View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null) {
                    popWindow.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.tv_click:
                        break;
                }
                Toast.makeText(getActivity(), "你点击了", Toast.LENGTH_SHORT).show();
            }
        };
        contentView.findViewById(R.id.tv_click).setOnClickListener(listener);
    }

}
