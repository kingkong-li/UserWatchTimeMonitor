package com.example.jingangli.userwatchtimemonitor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

/**
 * 统计Item显示时间辅助类
 * @author jingang.li
 */
public class StaticItemShowTime {
    private static final String TAG = StaticItemShowTime.class.getSimpleName();
    private static StaticItemShowTime mInstance;
    /**
     * 每个Item显示时间
     */
    private long[] mWatchTimes;
    /**
     * 中间变量，可见时刻
     */
    private long[] mVisibleMoment;
    private long[] mInVisibleMoment;
    private boolean[] mLastVisibleState;
    private int mItemCount=0;
    /**第一个可见的Item*/
    private int mFirstVisibleChild;
    /**最后一个可见的Item*/
    private int mLastVisibleChild;
    /**是否初始化*/
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
    private void init(int count){
        mWatchTimes=new long[count];
        mVisibleMoment =new long[count];
        mInVisibleMoment =new long[count];
        mLastVisibleState=new boolean[count];
        for(int i=0;i<count;i++)
        {
            mWatchTimes[i]=0;
            mVisibleMoment[i]=0;
            mInVisibleMoment[i]=0;
            mLastVisibleState[i]=false;

        }
        mItemCount=count;
    }
    /**
     * 重新计算每项显示时间
     * 要进行异步处理
     * 可以通过切点路径来区分RecyclerView
     */
    public void reCalculateItemShowTime(RecyclerView recyclerView){

        Log.d(TAG,recyclerView.getAdapter().getClass().getSimpleName());
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
                    mVisibleMoment[i]=System.currentTimeMillis();
                    mLastVisibleState[i]=true;
                    mIsInit=true;
                }else if(!mLastVisibleState[i])
                {
                    mVisibleMoment[i]=System.currentTimeMillis();
                    mLastVisibleState[i]=true;
                }else if(mLastVisibleState[i])
                {
                    mInVisibleMoment[i]=System.currentTimeMillis();
                    if(mInVisibleMoment[i]> mVisibleMoment[i])
                    {
                        mWatchTimes[i]=mWatchTimes[i]+ mInVisibleMoment[i]- mVisibleMoment[i];
                        mInVisibleMoment[i]=0;
                        mVisibleMoment[i]=System.currentTimeMillis();

                    }
                }

            }
            if((i< mFirstVisibleChild || i> mLastVisibleChild) && mLastVisibleState[i]){


                    mInVisibleMoment[i]=System.currentTimeMillis();
                   if(mInVisibleMoment[i]> mVisibleMoment[i])
                    {
                        mWatchTimes[i]=mWatchTimes[i]+ mInVisibleMoment[i]- mVisibleMoment[i];
                        mInVisibleMoment[i]=0;

                    }
                    mLastVisibleState[i]=false;

            }

            Log.d(TAG,"mWatchTimes["+i+"]:"+mWatchTimes[i]);

        }


    }

    /**
     * 存储每一个Item的显示时间、可以跟以前的进行合并
     */
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
