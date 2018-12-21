package com.example.jingangli.userwatchtimemonitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author jingang.li
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private Context mContext;
    private List<NodeInfo> mData;

    MyRecyclerViewAdapter(Context context, List<NodeInfo> data){
        mContext=context;
        mData=data;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.nodeName.setText(mData.get(position).nodeName);
        String showTimeString = String.format(mContext.getResources().getString(R.string.show_time),
                mData.get(position).nodeShowTime);
        holder.showTime.setText(showTimeString);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView nodeName;
        TextView showTime;
        MyHolder(View itemView) {
            super(itemView);
            nodeName =itemView.findViewById(R.id.tv_item_name);
            showTime=itemView.findViewById(R.id.tv_show_time);

        }
    }
}
