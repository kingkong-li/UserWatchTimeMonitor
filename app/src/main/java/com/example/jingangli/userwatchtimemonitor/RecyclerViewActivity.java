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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        List<NodeInfo> data=new ArrayList<>();
        String content="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<content.length();i++){
            NodeInfo nodeInfo=new NodeInfo();
            nodeInfo.nodeName=content.substring(i,i+1);
            // 这里要填上具体的观看时间
            nodeInfo.nodeShowTime=" "+1000*Math.random()+"ms";
            data.add(nodeInfo);
        }
        MyRecyclerViewAdapter recyclerViewAdapter =new MyRecyclerViewAdapter(this,data);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG,"newState="+newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int childCount= recyclerView.getChildCount();
                    int visibleCount= linearManager.getChildCount();
                    int total=linearManager.getItemCount();
                    int firstVisibleChild=linearManager.findFirstVisibleItemPosition();
                    int lastVisibleChild=linearManager.findLastVisibleItemPosition();
                    Log.v(TAG,"onScrolled:"+"childCount="+childCount+
                            ",visibleCount="+visibleCount+", total="+total+",firstVisibleChild ="
                            +firstVisibleChild+", lastVisibleChild="+lastVisibleChild);
                }
            }
        });



    }
}
