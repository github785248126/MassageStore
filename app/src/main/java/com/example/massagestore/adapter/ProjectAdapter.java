package com.example.massagestore.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.massagestore.R;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.util.AmountUtils;

import java.util.List;

/**
 * Created by 老表 on 2019/8/26.
 */

public class ProjectAdapter extends BaseQuickAdapter<ProjectDB,BaseViewHolder>{

    public ProjectAdapter(int layoutResId, @Nullable List<ProjectDB> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectDB item) {
        helper.setText(R.id.tv_project_name,item.getName());
        helper.setText(R.id.tv_project_price, AmountUtils.formatMoney(item.getPrice()));
    }
}
