package com.example.massagestore.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

/**
 * Create by cbh on 2019/3/29
 * e-mail:yoursilib@qq.com
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        /**
         *  初始化布局
         */
        initView();

        /**
         *  初始化
         */
        init();

        //  点击外部取消
        setCanceledOnTouchOutside(false);

        //设置背景透明，不然会出现白色直角问题
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    protected abstract void init();

    protected abstract int getLayout();

    protected abstract void initView();
}
