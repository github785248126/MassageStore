package com.example.massagestore.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

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
        String v1 = item.getV1();
        String v2 = item.getV2();
        String v3 = item.getV3();
        String v4 = item.getV4();

        if (TextUtils.isEmpty(v1) && TextUtils.isEmpty(v2) && TextUtils.isEmpty(v3) && TextUtils.isEmpty(v4)){
            return;
        }else {
            StringBuffer stringBuffer = new StringBuffer();
            if (!TextUtils.isEmpty(v1)){
                stringBuffer.append(v1);
            }

            if (!TextUtils.isEmpty(v2)){
                stringBuffer.append("/").append(v2);
            }

            if (!TextUtils.isEmpty(v3)){
                stringBuffer.append("/").append(v3);
            }

            if (!TextUtils.isEmpty(v4)){
                stringBuffer.append("/").append(v4);
            }
            helper.setText(R.id.remarks,stringBuffer.toString());
        }
    }
}
