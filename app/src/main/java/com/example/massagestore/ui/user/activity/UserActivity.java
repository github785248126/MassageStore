package com.example.massagestore.ui.user.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.massagestore.R;
import com.example.massagestore.adapter.PagerAdapter;
import com.example.massagestore.base.BaseActivity;
import com.example.massagestore.ui.project.fragment.ProjectListFragment;
import com.example.massagestore.ui.user.fragment.UserListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 老表 on 2019/8/25.
 * 员工管理
 */

public class UserActivity extends BaseActivity implements View.OnClickListener{
    private ImageView back;
    private TabLayout tableLayout;
    private ViewPager viewPager;

    private List<String> titleList;
    private List<Fragment> frameLayoutList;

    @Override
    protected void init() {
        titleList.add("技师列表");
        frameLayoutList.add(new UserListFragment());
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), frameLayoutList, titleList));
        tableLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_user);
        tableLayout = findViewById(R.id.tab_user);
        viewPager = findViewById(R.id.pager_user);
        back.setOnClickListener(this);

        titleList = new ArrayList<>();
        frameLayoutList = new ArrayList<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_user:
                finish();
                break;
        }
    }
}
