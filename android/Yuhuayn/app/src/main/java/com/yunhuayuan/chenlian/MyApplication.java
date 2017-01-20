package com.yunhuayuan.chenlian;

import android.app.Application;

/**
 * Created by chenlian on 16/8/27.
 */
public class MyApplication extends Application {

    private static MyApplication appInstance;

    @Override
    public void onCreate() {
        appInstance = this;
        super.onCreate();

    }
    public static MyApplication getInstance()
    {
        return appInstance;
    }
}
