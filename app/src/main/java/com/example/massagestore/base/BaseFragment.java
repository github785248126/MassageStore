package com.example.massagestore.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.massagestore.util.EventBusUtil;

/**
 * Create by cbh on 2019/1/23
 * e-mail:yoursilib@qq.com
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(),container, false);

        //  EventBus注册
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }

        initView(inflate);

        init();

        return inflate;
    }

    protected abstract void init();

    protected abstract void initView(View inflate);

    public abstract int getLayout();

    /**
     * 是否注册事件分发
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }
}
