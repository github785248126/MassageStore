package com.example.massagestore.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.massagestore.R;
import com.example.massagestore.dao.entity.UserDB;
import java.util.List;

/**
 * 创建日期：2019/9/6
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class UserListAdapter extends BaseQuickAdapter<UserDB,BaseViewHolder>{
    public UserListAdapter(int layoutResId, @Nullable List<UserDB> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserDB item) {
        helper.setText(R.id.user_name,item.getName());
    }
}
