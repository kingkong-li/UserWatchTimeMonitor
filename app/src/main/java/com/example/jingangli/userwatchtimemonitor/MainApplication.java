package com.example.jingangli.userwatchtimemonitor;

import android.app.Application;


/**
 * description:
 *
 * @author kingkong
 * @date 2018/5/29 0029
 * changed by kingkong on 2018/5/29 0029.
 */

public class MainApplication extends Application {
    private static MainApplication mInstance;

    public static MainApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(mInstance==null)
        {
            mInstance=this;
        }

    }
}
