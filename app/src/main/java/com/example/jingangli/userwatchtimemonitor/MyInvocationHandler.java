package com.example.jingangli.userwatchtimemonitor;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jingang.li
 */
public class MyInvocationHandler implements InvocationHandler {

    private static final String TAG =MyInvocationHandler.class.getSimpleName() ;
    private Object mObject;

    public MyInvocationHandler(Object object) {
        this.mObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Log.v(TAG,"before method invoke");
        Object result = method.invoke(mObject, args);
        Log.v(TAG,"after method invoke");
        return result;

    }
}
