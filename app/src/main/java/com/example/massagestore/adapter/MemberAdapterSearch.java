package com.example.massagestore.adapter;

import android.support.annotation.Nullable;
import android.widget.Filter;
import android.widget.Filterable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.massagestore.R;
import com.example.massagestore.dao.entity.MemberDB;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2019/9/6
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class MemberAdapterSearch extends BaseQuickAdapter<MemberDB,BaseViewHolder> implements Filterable {
    private PersonFilter filter;
    private List<MemberDB> data;
    private OnClickListener onClickListener;

    public interface OnClickListener {
        void onFilterResults(List<MemberDB> resultBeans);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MemberAdapterSearch(int layoutResId, @Nullable List<MemberDB> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberDB item) {
        helper.setText(R.id.memberName,item.getName());
        helper.setText(R.id.memberPhone,item.getPhone());
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new PersonFilter(data);
        }
        return filter;
    }

    private class PersonFilter extends Filter {
        private List<MemberDB> original;
        public PersonFilter(List<MemberDB> list) {
            this.original = list;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = original;
                results.count = original.size();
            } else {
                List<MemberDB> mList = new ArrayList<MemberDB>();
                for (MemberDB p : original) {
                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()) || p.getPhone().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        mList.add(p);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data = (List<MemberDB>) results.values;
            onClickListener.onFilterResults(data);
        }
    }
}
