package com.example.massagestore.ui.member.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.massagestore.R;
import com.example.massagestore.adapter.ProjectAdapter;
import com.example.massagestore.base.BaseFragment;
import com.example.massagestore.dao.ProjectDBDao;
import com.example.massagestore.dao.entity.ProjectDB;

import java.util.List;

/**
 * 创建日期：2019/9/4
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class MemberFragment extends BaseFragment{
    private RecyclerView recycleProject;
    private Button btInsert;
    private List<ProjectDB> list;
    private ProjectAdapter projectAdapter;
    private ProjectDBDao projectDBDao;

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_member;
    }
}
