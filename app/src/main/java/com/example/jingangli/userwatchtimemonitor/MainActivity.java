package com.example.jingangli.userwatchtimemonitor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author jingang.li
 */
public class MainActivity extends Activity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        Button btnEnterRecyclerView=findViewById(R.id.enter_recycler_view);
        btnEnterRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext,RecyclerViewActivity.class);
                mContext.startActivity(intent);
            }
        });
        Button btnEnterFragment=findViewById(R.id.enter_fragment_activity);
        btnEnterFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext,MyFragmentActivity.class);
                mContext.startActivity(intent);
            }
        });

    }
}
