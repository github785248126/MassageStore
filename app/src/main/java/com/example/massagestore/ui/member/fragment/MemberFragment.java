package com.example.massagestore.ui.member.fragment;

import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.massagestore.R;
import com.example.massagestore.adapter.MemberAdapter;
import com.example.massagestore.adapter.ProjectAdapter;
import com.example.massagestore.base.BaseFragment;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.MemberDBDao;
import com.example.massagestore.dao.ProjectDBDao;
import com.example.massagestore.dao.entity.MemberDB;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.eventbus.EventCode;
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.ui.member.dialog.InsertMemberDialog;
import com.example.massagestore.ui.member.dialog.UpdataMemberDialog;
import com.example.massagestore.ui.project.dialog.UpdataProjectDialog;
import com.example.massagestore.util.AlertDialogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 创建日期：2019/9/4
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class MemberFragment extends BaseFragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private Button button;
    private List<MemberDB> list;
    private MemberDBDao memberDBDao;
    private MemberAdapter memberAdapter;

    @Override
    protected void init() {
        initDataBase();
        initRecycle();
    }

    @Override
    protected void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recycle_member);
        button = inflate.findViewById(R.id.bt_insert_member);
        button.setOnClickListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_member;
    }

    private void initDataBase() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(),MemberDBDao.TABLENAME);
        memberDBDao = daoSession.getMemberDBDao();
        list = memberDBDao.loadAll();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    private void initRecycle() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        memberAdapter = new MemberAdapter(R.layout.item_member,list);
        recyclerView.setAdapter(memberAdapter);

        memberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //  编辑项目
            new UpdataMemberDialog(getContext(),list.get(position)).show();
            }
        });

        memberAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                AlertDialogUtils.getSingle().dialogMessage(getContext(), "是否删除该项目！", new AlertDialogUtils.DialogClickListener() {
                    @Override
                    public void onPositiveListener(DialogInterface dialog) {

                    }

                    @Override
                    public void onNegativeListener(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventBus(EventMessage event) {
        switch (event.getCode()) {
            case EventCode.MemberListFragment_UPDATE:
                memberAdapter.setNewData(memberDBDao.loadAll());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_insert_member:
                new InsertMemberDialog(getContext()).show();
                break;
        }
    }
}
