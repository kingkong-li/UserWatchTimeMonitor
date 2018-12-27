package com.example.jingangli.userwatchtimemonitor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingang.li
 */
public class RecyclerViewActivity extends Activity {

    private static final String TAG =RecyclerViewActivity.class.getSimpleName() ;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        List<NodeInfo> data=new ArrayList<>();
        String content="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<content.length();i++){
            NodeInfo nodeInfo=new NodeInfo();
            nodeInfo.nodeName=content.substring(i,i+1);
            // 初始化观看时间为0ms
            nodeInfo.nodeShowTime=" "+0+"ms";
            data.add(nodeInfo);
        }



        final MyRecyclerViewAdapter recyclerViewAdapter =new MyRecyclerViewAdapter(this,data);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG,"newState="+newState);
//                StaticItemShowTime.getInstance().reCalculateItemShowTime(recyclerView);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                StaticItemShowTime.getInstance().reCalculateItemShowTime(recyclerView);
                recyclerViewAdapter.refreshData(StaticItemShowTime.getInstance().getShowTimes());
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        StaticItemShowTime.getInstance().resetInitState();
        StaticItemShowTime.getInstance().reCalculateItemShowTime(mRecyclerView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        StaticItemShowTime.getInstance().reCalculateItemShowTime(mRecyclerView);
        StaticItemShowTime.getInstance().storeItemsWatchTime();

        StaticItemShowTime.getInstance().resetInitState();
    }
}
