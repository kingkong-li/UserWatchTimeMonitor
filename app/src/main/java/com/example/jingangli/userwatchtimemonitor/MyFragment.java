package com.example.jingangli.userwatchtimemonitor;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingang.li
 */
public class MyFragment extends Fragment {
    private static final String TAG =MyFragment.class.getSimpleName() ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recycler_view_in_fragment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<NodeInfo> data=new ArrayList<>();
        String content="大数据思维实质是实事求是的一种思维方式";
        for(int i=0;i<content.length();i++){
            NodeInfo nodeInfo=new NodeInfo();
            nodeInfo.nodeName=content.substring(i,i+1);
            // 初始化观看时间为0ms
            nodeInfo.nodeShowTime=" "+0+"ms";
            data.add(nodeInfo);
        }


        final MyRecyclerViewAdapter recyclerViewAdapter =new MyRecyclerViewAdapter(getContext(),data);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG,"newState="+newState);
                ViewDelegate.getInstance().execute();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                recyclerViewAdapter.refreshData(StaticItemShowTime.getInstance().getShowTimes());
            }
        });


        return view;

    }
}
