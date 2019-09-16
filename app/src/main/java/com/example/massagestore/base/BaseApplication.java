package com.example.massagestore.base;

import android.app.Application;
import android.content.Context;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;

/**
 * Create by cbh on 2019/1/17
 * e-mail:yoursilib@qq.com
 */
public class BaseApplication extends Application {

    private static Context context;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        daoSession = getDaoMaster();
    }

    //  全局上下文
    public static Context getContext() {
        return context;
    }

    //  全局上下文
    public static DaoSession getDaoMaster() {
        return DaoMaster.newDevSession(context,"store.db");
    }
}
