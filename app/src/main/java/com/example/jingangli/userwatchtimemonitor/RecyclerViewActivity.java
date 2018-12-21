package com.example.jingangli.userwatchtimemonitor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingang.li
 */
public class RecyclerViewActivity extends Activity {

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
            // 这里要填写
            nodeInfo.nodeShowTime=" "+1000*Math.random()+"ms";
            data.add(nodeInfo);
        }
        MyRecyclerViewAdapter recyclerViewAdapter =new MyRecyclerViewAdapter(this,data);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


    }
}
