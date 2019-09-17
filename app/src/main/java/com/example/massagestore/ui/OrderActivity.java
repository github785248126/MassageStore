package com.example.massagestore.ui;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.massagestore.R;
import com.example.massagestore.adapter.OrderAdapter;
import com.example.massagestore.base.BaseActivity;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.OrderDBDao;
import cos.mos.kdialog.ConfirmClick;
import cos.mos.kdialog.UDialog;

/**
 * 创建日期：2019/9/10
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class OrderActivity extends BaseActivity {
    private ImageView back;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private OrderDBDao orderDBDao;


    @Override
    protected void init() {
        DaoSession daoSession = DaoMaster.newDevSession(this, OrderDBDao.TABLENAME);
        orderDBDao = daoSession.getOrderDBDao();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(R.layout.item_order, orderDBDao.loadAll());
        recyclerView.setAdapter(orderAdapter);

        orderAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                UDialog.builder(OrderActivity.this, "是否删除该订单！")
                        .button("确定","取消")
                        .confirmClick(new ConfirmClick() {
                            @Override
                            public void onConfirmClick(String result, Dialog dialog) {
                                orderDBDao.delete(orderDBDao.loadAll().get(position));
                                orderAdapter.setNewData(orderDBDao.loadAll());
                                dialog.dismiss();
                            }
                        }).build();
                return false;
            }
        });
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_order);
        recyclerView = findViewById(R.id.recycle_order);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }
}
