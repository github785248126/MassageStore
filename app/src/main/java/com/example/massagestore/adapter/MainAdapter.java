package com.example.massagestore.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.massagestore.R;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.util.AmountUtils;

import java.util.List;

/**
 * 创建日期：2019/9/4
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class MainAdapter extends BaseQuickAdapter<ProjectDB,BaseViewHolder>{
    public MainAdapter(int layoutResId, @Nullable List<ProjectDB> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectDB item) {
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.time,item.getTime()+" min");
        helper.setText(R.id.price, AmountUtils.formatMoney(item.getPrice()));
        helper.setText(R.id.remarks,item.getRemarks());
    }
}
