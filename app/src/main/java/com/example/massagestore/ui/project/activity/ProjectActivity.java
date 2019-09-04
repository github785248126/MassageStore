package com.example.massagestore.ui.project.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.example.massagestore.R;
import com.example.massagestore.adapter.PagerAdapter;
import com.example.massagestore.base.BaseActivity;
import com.example.massagestore.ui.project.fragment.ProjectListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 老表 on 2019/8/25.
 * 项目管理页面
 */

public class ProjectActivity extends BaseActivity implements View.OnClickListener{
    private ImageView back;
    private TabLayout tableLayout;
    private ViewPager viewPager;

    private List<String> titleList;
    private List<Fragment> frameLayoutList;

    @Override
    protected void init() {
        initPager();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_project;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_projeck);
        tableLayout = findViewById(R.id.tab_projeck);
        viewPager = findViewById(R.id.pager_project);
        back.setOnClickListener(this);

        titleList = new ArrayList<>();
        frameLayoutList = new ArrayList<>();
    }

    private void initPager() {
        titleList.add("项目列表");
        frameLayoutList.add(new ProjectListFragment());
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), frameLayoutList, titleList));
        tableLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_projeck:
                finish();
                break;
        }
    }
}
