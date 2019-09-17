package com.example.massagestore.ui.project.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.massagestore.R;
import com.example.massagestore.adapter.ProjectAdapter;
import com.example.massagestore.base.BaseFragment;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.ProjectDBDao;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.eventbus.EventCode;
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.ui.OrderActivity;
import com.example.massagestore.ui.project.dialog.InsertProjectDialog;
import com.example.massagestore.ui.project.dialog.UpdataProjectDialog;
import com.example.massagestore.util.AlertDialogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cos.mos.kdialog.ConfirmClick;
import cos.mos.kdialog.UDialog;

/**
 * Created by 老表 on 2019/8/25.
 */

public class ProjectListFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycleProject;
    private Button btInsert;
    private ProjectAdapter projectAdapter;
    private ProjectDBDao projectDBDao;

    @Override
    protected void init() {
        initDataBase();
        initRecycle();
    }

    @Override
    protected void initView(View inflate) {
        recycleProject = inflate.findViewById(R.id.recycle_project);
        btInsert = inflate.findViewById(R.id.bt_insert_projeck);

        btInsert.setOnClickListener(this);
    }

    private void initDataBase() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), ProjectDBDao.TABLENAME);
        projectDBDao = daoSession.getProjectDBDao();
    }

    private void initRecycle() {
        recycleProject.setLayoutManager(new GridLayoutManager(getContext(), 4));
        projectAdapter = new ProjectAdapter(R.layout.item_project, projectDBDao.loadAll());
        recycleProject.setAdapter(projectAdapter);

        projectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //  编辑项目
                new UpdataProjectDialog(getContext(), projectDBDao.loadAll().get(position)).show();
            }
        });

        projectAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                UDialog.builder(getContext(), "是否删除该项目！")
                        .button("确定", "取消")
                        .confirmClick(new ConfirmClick() {
                            @Override
                            public void onConfirmClick(String result, Dialog dialog) {
                                projectDBDao.delete(projectDBDao.loadAll().get(position));
                                projectAdapter.setNewData(projectDBDao.loadAll());
                                dialog.dismiss();
                            }
                        }).build();
                return false;
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list_project;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_insert_projeck:
                new InsertProjectDialog(getContext()).show();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventBus(EventMessage event) {
        switch (event.getCode()) {
            case EventCode.ProjectListFragment_UPDATE:
                projectAdapter.setNewData(projectDBDao.loadAll());
                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
}
