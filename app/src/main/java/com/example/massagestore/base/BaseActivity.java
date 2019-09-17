package com.example.massagestore.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Create by cbh on 2019/1/17
 * e-mail:yoursilib@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /**
         *  配置透明状态栏
         */
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); //  SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            //  透明状态栏
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        /**
         *  初始化布局
         */
        initView();

        init();
    }

    protected abstract void init();

    protected abstract void initView();

    protected abstract int getLayout();
}
