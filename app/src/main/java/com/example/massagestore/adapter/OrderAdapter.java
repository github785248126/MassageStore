package com.example.massagestore.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.massagestore.R;
import com.example.massagestore.dao.entity.OrderDB;
import java.util.List;

/**
 * 创建日期：2019/9/10
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class OrderAdapter extends BaseQuickAdapter<OrderDB, BaseViewHolder> {
    public OrderAdapter(int layoutResId, @Nullable List<OrderDB> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDB item) {
        helper.setText(R.id.id, String.valueOf(helper.getPosition()+1));
        helper.setText(R.id.orderid, item.getOrderid());
        helper.setText(R.id.name, item.getProject());
        helper.setText(R.id.t_user, item.getUser());
        helper.setText(R.id.t_price, item.getCommission());
        helper.setText(R.id.member, item.getMember());
        helper.setText(R.id.ys_price, item.getYf_price());
        helper.setText(R.id.ss_price, item.getSf_price());
    }
}
