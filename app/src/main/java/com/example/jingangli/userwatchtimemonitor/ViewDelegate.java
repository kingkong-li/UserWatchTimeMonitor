package com.example.jingangli.userwatchtimemonitor;

import android.support.v4.view.ScrollingView;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Proxy;

/**
 * @author jingang.li
 * 通过动态代理方式
 */
public class ViewDelegate {
    private static ViewDelegate mInstance;

    public static synchronized ViewDelegate getInstance(){
        if(null == mInstance){
            mInstance = new ViewDelegate();
        }
        return mInstance;
    }

    public void execute()
    {
        RecyclerView recyclerView = new RecyclerView(MainApplication.getInstance());
        MyInvocationHandler myInvocationHandler=new MyInvocationHandler(recyclerView);
        ScrollingView scrollingView = (ScrollingView) Proxy.newProxyInstance(myInvocationHandler.getClass().getClassLoader(),
                recyclerView.getClass().getInterfaces(), myInvocationHandler);

        scrollingView.computeHorizontalScrollRange();

    }

}
