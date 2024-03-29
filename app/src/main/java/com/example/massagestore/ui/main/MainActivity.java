package com.example.massagestore.ui.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.massagestore.R;
import com.example.massagestore.adapter.MainAdapter;
import com.example.massagestore.base.BaseActivity;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.ProjectDBDao;
import com.example.massagestore.ui.OrderActivity;
import com.example.massagestore.ui.user.activity.UserActivity;
import com.example.massagestore.ui.member.activity.MemberActivity;
import com.example.massagestore.ui.project.activity.ProjectActivity;
import com.example.massagestore.util.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ImageView menu;
    private RecyclerView recycleCommodity;
    private RadioButton rbProject;
    private RadioButton rbArtificer;
    private RadioButton rbMember;
    private RadioButton rbOrder;
    private RadioGroup radioGroup;
    private MainAdapter mainAdapter;
    private ProjectDBDao projectDBDao;
    private DaoSession daoSession;
    private long mPressedTime;

    @Override
    protected void init() {
        initData();
        initRecycle();
        initListener();
    }

    private void initListener() {
        mainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                new DetailsDialog(MainActivity.this, projectDBDao.loadAll().get(position)).show();
            }
        });
    }

    private void initData() {
        daoSession = DaoMaster.newDevSession(MainActivity.this, ProjectDBDao.TABLENAME);
        projectDBDao = daoSession.getProjectDBDao();
    }

    private void initRecycle() {
        recycleCommodity.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainAdapter(R.layout.item_main, projectDBDao.loadAll());
        recycleCommodity.setAdapter(mainAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        menu = (ImageView) findViewById(R.id.menu_main);
        recycleCommodity = (RecyclerView) findViewById(R.id.recycle_main);
        rbProject = (RadioButton) findViewById(R.id.rb_project_main);
        rbArtificer = (RadioButton) findViewById(R.id.rb_artificer_main);
        rbMember = (RadioButton) findViewById(R.id.rb_member_main);
        rbOrder = (RadioButton) findViewById(R.id.rb_list_main);
        radioGroup = (RadioGroup) findViewById(R.id.rg_main);

        menu.setOnClickListener(this);
        rbProject.setOnClickListener(this);
        rbArtificer.setOnClickListener(this);
        rbMember.setOnClickListener(this);
        rbOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_main:
                if (radioGroup.getVisibility() == View.GONE) {
                    radioGroup.setVisibility(View.VISIBLE);
                } else {
                    radioGroup.setVisibility(View.GONE);
                }
                break;
            case R.id.rb_project_main:
                radioGroup.setVisibility(View.GONE);
                jumpActivity(ProjectActivity.class);
                break;
            case R.id.rb_artificer_main:
                radioGroup.setVisibility(View.GONE);
                jumpActivity(UserActivity.class);
                break;
            case R.id.rb_member_main:
                radioGroup.setVisibility(View.GONE);
                jumpActivity(MemberActivity.class);
                break;
            case R.id.rb_list_main:
                radioGroup.setVisibility(View.GONE);
                jumpActivity(OrderActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mainAdapter) {
            mainAdapter.setNewData(DaoMaster.newDevSession(MainActivity.this, ProjectDBDao.TABLENAME).getProjectDBDao().loadAll());
        }
    }

    @Override
    public void onBackPressed() {
        if (radioGroup.getVisibility() == View.VISIBLE){
            radioGroup.setVisibility(View.GONE);
        }

        //  获取第一次按键时间
        long mNowTime = System.currentTimeMillis();
        //  比较两次按键时间差
        if ((mNowTime - mPressedTime) > 2000) {
            ToastUtils.showTextShort("再按一次退出程序");
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }

    private void jumpActivity(Class cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }
}
