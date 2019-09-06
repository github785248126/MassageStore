package com.example.massagestore.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.massagestore.R;
import com.example.massagestore.dao.entity.MemberDB;

import java.util.List;

/**
 * 创建日期：2019/9/6
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class MemberAdapter extends BaseQuickAdapter<MemberDB,BaseViewHolder>{
    public MemberAdapter(int layoutResId, @Nullable List<MemberDB> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberDB item) {
        helper.setText(R.id.tv_member_name,item.getName());
    }
}
