package com.example.massagestore.base;

import android.app.Application;
import android.content.Context;
/**
 * Create by cbh on 2019/1/17
 * e-mail:yoursilib@qq.com
 */
public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    //  全局上下文
    public static Context getContext() {
        return context;
    }
}
