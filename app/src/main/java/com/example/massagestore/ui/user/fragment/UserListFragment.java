package com.example.massagestore.ui.user.fragment;

import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.massagestore.R;
import com.example.massagestore.adapter.UserAdapter;
import com.example.massagestore.base.BaseFragment;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.UserDBDao;
import com.example.massagestore.dao.entity.UserDB;
import com.example.massagestore.eventbus.EventCode;
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.ui.user.dialog.InsertUserDialog;
import com.example.massagestore.ui.user.dialog.UpdataUserDialog;
import com.example.massagestore.util.AlertDialogUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;

/**
 * Created by 老表 on 2019/8/31.
 */

public class UserListFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycle;
    private Button button;
    private UserDBDao userDBDao;
    private UserAdapter userAdapter;
    private List<UserDB> list;

    @Override
    protected void init() {
        initDataBase();
        initRecycle();
    }

    @Override
    protected void initView(View inflate) {
        recycle = inflate.findViewById(R.id.recycle_user);
        button = inflate.findViewById(R.id.bt_insert_user);

        button.setOnClickListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list_user;
    }

    private void initDataBase() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDBDao.TABLENAME);
        userDBDao = daoSession.getUserDBDao();
        list = userDBDao.loadAll();
    }

    private void initRecycle() {
        recycle.setLayoutManager(new GridLayoutManager(getContext(),4));
        userAdapter = new UserAdapter(R.layout.item_user,list);
        recycle.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //  编辑员工
                new UpdataUserDialog(getContext(),list.get(position)).show();
            }
        });

        userAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                AlertDialogUtils.getSingle().dialogMessage(getContext(), "是否删除该技师！", new AlertDialogUtils.DialogClickListener() {
                    @Override
                    public void onPositiveListener(DialogInterface dialog) {
                        userDBDao.delete(list.get(position));
                        list.remove(position);
                        userAdapter.notifyDataSetChanged();
                        dialog.dismiss();
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
            case EventCode.UserListFragment_UPDATE:
                db_updata();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_insert_user:
                new InsertUserDialog(getContext()).show();
                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    private void db_updata() {
        list.clear();
        list.addAll(userDBDao.loadAll());
        userAdapter.notifyDataSetChanged();
    }
}
