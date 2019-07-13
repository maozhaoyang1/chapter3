package com.example.chapter3.homework;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlaceholderFragment extends Fragment {

    ListView list;
    private String[] mName = { "用户1", "用户2", "用户3", "用户4", "用户5", "用户6" };
    private ArrayList<Map<String, Object>> mData = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, null);

        list = view.findViewById(R.id.Items);

        for(int i = 0; i < mName.length; i ++){

            Map<String,Object> item = new HashMap();
            item.put("name", mName[i]);
            mData.add(item);

        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),mData,android.R.layout.simple_list_item_1,
                new String[]{"name"},new int[]{android.R.id.text1});

        list.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                View target1=getView().findViewById(R.id.target1);
                View list=getView().findViewById(R.id.Items);
                AnimatorSet animatorSet;
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(target1,"alpha",1.0f,0f);
                animator1.setDuration(1000);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(list,"alpha",0f,1.0f);
                animator2.setDuration(1000);
                animatorSet = new AnimatorSet();
                list.setVisibility(View.VISIBLE);
                animatorSet.playTogether(animator1,animator2);
                animatorSet.start();
            }
        }, 5000);


    }
}
