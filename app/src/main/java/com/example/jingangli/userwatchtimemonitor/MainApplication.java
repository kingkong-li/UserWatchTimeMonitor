package com.example.jingangli.userwatchtimemonitor;

import android.app.Application;

import com.tendcloud.tenddata.TCAgent;


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
        super.onCreate();

        TCAgent.LOG_ON=true;
        // App ID: 在TalkingData创建应用后，进入数据报表页中，在“系统设置”-“编辑应用”页面里查看App ID。
        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
        TCAgent.init(this);
        // 如果已经在AndroidManifest.xml配置了App ID和渠道ID，调用TCAgent.init(this)即可；或与AndroidManifest.xml中的对应参数保持一致。
        TCAgent.setReportUncaughtExceptions(true);

    }
}
