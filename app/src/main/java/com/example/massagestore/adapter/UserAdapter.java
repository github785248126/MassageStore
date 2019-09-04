package com.example.massagestore.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.massagestore.R;
import com.example.massagestore.dao.UserDBDao;
import com.example.massagestore.dao.entity.UserDB;

import java.util.List;

/**
 * Created by 老表 on 2019/8/31.
 */

public class UserAdapter extends BaseQuickAdapter<UserDB,BaseViewHolder> {
    public UserAdapter(int layoutResId, @Nullable List<UserDB> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserDB item) {
        helper.setText(R.id.tv_user_name,item.getName());
        helper.setText(R.id.tv_user_price,item.getPhone());
    }
}
