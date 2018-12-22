package com.example.jingangli.userwatchtimemonitor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StaticItemShowTime {
    private static final String TAG = StaticItemShowTime.class.getSimpleName();
    private static StaticItemShowTime mInstance;


    private long mWatchTimes[];
    private long mTimeIn[];
    private long mTimeOut[];
    private boolean mLastVisibleState[];
    private int mItemCount=0;
    private int mFirstVisibleChild;
    private int mLastVisibleChild;
    private boolean mIsInit=false;

    public static synchronized StaticItemShowTime getInstance(){
        if(null == mInstance){
            mInstance = new StaticItemShowTime();
        }
        return mInstance;
    }

    public void resetInitState()
    {
        mIsInit=false;
    }
    /**
     * 初始化值
     * @param count item数目
     */
    public void init(int count){

        mWatchTimes=new long[count];
        mTimeIn=new long[count];
        mTimeOut=new long[count];
        mLastVisibleState=new boolean[count];
        for(int i=0;i<count;i++)
        {
            mWatchTimes[i]=0;
            mTimeIn[i]=0;
            mTimeOut[i]=0;
            mLastVisibleState[i]=false;

        }
        mItemCount=count;
    }
    /**
     * 重新计算每项显示时间
     */
    public void reCalculateItemShowTime(RecyclerView recyclerView){

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            mFirstVisibleChild = linearManager.findFirstVisibleItemPosition();
            mLastVisibleChild = linearManager.findLastVisibleItemPosition();
            if(!mIsInit)
            {
                mItemCount=layoutManager.getItemCount();
                init(mItemCount);
            }

        }else
        {
            Log.e(TAG,"not layoutManager,beyond our ability");
        }

        for(int i=0;i<mItemCount;i++)
        {

            if(i>= mFirstVisibleChild && i<= mLastVisibleChild )
            {
                if(!mIsInit){
                    mTimeIn[i]=System.currentTimeMillis();
                    mLastVisibleState[i]=true;
                    mIsInit=true;
                }else if(!mLastVisibleState[i])
                {
                    mTimeIn[i]=System.currentTimeMillis();
                    mLastVisibleState[i]=true;
                }else if(mLastVisibleState[i])
                {
                    mTimeOut[i]=System.currentTimeMillis();
                    if(mTimeOut[i]>mTimeIn[i])
                    {
                        mWatchTimes[i]=mWatchTimes[i]+mTimeOut[i]-mTimeIn[i];
                        mTimeOut[i]=0;
                        mTimeIn[i]=System.currentTimeMillis();

                    }
                }


            }
            if((i< mFirstVisibleChild || i> mLastVisibleChild) && mLastVisibleState[i]){


                    mTimeOut[i]=System.currentTimeMillis();
                   if(mTimeOut[i]>mTimeIn[i])
                    {
                        mWatchTimes[i]=mWatchTimes[i]+mTimeOut[i]-mTimeIn[i];
                        mTimeOut[i]=0;

                    }
                    mLastVisibleState[i]=false;

            }else{

                // 可见状态未变化 不做记录
            }


            Log.d(TAG,"mWatchTimes["+i+"]:"+mWatchTimes[i]);

        }


    }
    public void storeItemsWatchTime()
    {
        for(int i=0;i<mItemCount;i++)
        {
            Log.e(TAG,"mWatchTimes["+i+"]:"+mWatchTimes[i]);
        }

    }


    public ArrayList<String> getShowTimes() {
        ArrayList<String> showTimes=new ArrayList<>();
        for(int i=0;i<mItemCount;i++)
        {
            showTimes.add(String.valueOf(mWatchTimes[i]));
        }
        return showTimes;

    }
}
