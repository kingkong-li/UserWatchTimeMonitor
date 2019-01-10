package com.example.jingangli.userwatchtimemonitor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author jingang.li
 */
@Aspect
public class GetDataUseAspectJ {
    private static String TAG=GetDataUseAspectJ.class.getSimpleName();

    @Pointcut("execution(* *onClick(..))")
    public void onViewClicked() {

    }
    @Before("onViewClicked()")
    public void beforeViewClick(JoinPoint joinPoint) {
        Log.d(TAG, "beforeViewClick:" + joinPoint.toShortString()+joinPoint.getKind());
    }
    @After("onViewClicked()")
    public void afterViewClick(JoinPoint joinPoint) {
        Log.d(TAG, "afterViewClick:" + joinPoint.toShortString()+joinPoint.getKind());
    }

    @Pointcut("execution(* *onScrolled(..))")
    public void onViewScrolled() {

    }

    @After("onViewScrolled()")
    public void onRecyclerViewScrolled(JoinPoint joinPoint) {
        Log.d(TAG, "onRecyclerViewScrolled length" + joinPoint.getArgs().length
                +joinPoint.getSignature().toLongString()+" short String="+
                joinPoint.getSignature().toShortString());
        RecyclerView recyclerView;
        if(joinPoint.getArgs()[0] instanceof   RecyclerView )
        {
            Log.d(TAG, "instanceof   RecyclerView ");
            recyclerView =(RecyclerView)joinPoint.getArgs()[0];
            StaticItemShowTime.getInstance().reCalculateItemShowTime(recyclerView);
            Log.d(TAG, "getScroll child" + recyclerView.getChildCount()+
                    "0 position=");
        }else
        {
            return;
        }

    }

    @Pointcut("execution(* *onBindViewHolder(..))")
    public void onViewBind() {

    }

    @After("onViewBind()")
    public void afterViewBind(JoinPoint joinPoint) {

        Log.d(TAG, "afterViewBind" + joinPoint.getArgs().length
                +joinPoint.getSignature().toLongString()+" short String="+
                joinPoint.getSignature().toShortString());


    }
    @Pointcut("execution(* *onViewAttachedToWindow(..))")
    public void onAdapterViewAttachedToWindow() {

    }
    @After("onAdapterViewAttachedToWindow()")
    public void afterAdapterViewAttachedToWindow(JoinPoint joinPoint) {

        Log.d(TAG, "afterAdapterViewAttachedToWindow" + joinPoint.getArgs().length
                +joinPoint.getSignature().toLongString()+" short String="+
                joinPoint.getSignature().toShortString());


    }



    @Pointcut("execution(* *onDraw(..))")
    public void onViewDraw() {

    }

    @After("onViewDraw()")
    public void afterViewDraw(JoinPoint joinPoint) {

        Log.d(TAG, "afterViewDraw length" + joinPoint.getArgs().length
                +joinPoint.getSignature().toLongString()+" short String="+
                joinPoint.getSignature().toShortString());


    }

}
