package com.example.massagestore.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.massagestore.R;
import com.example.massagestore.adapter.MemberAdapterSearch;
import com.example.massagestore.adapter.UserListAdapter;
import com.example.massagestore.base.BaseDialog;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.MemberDBDao;
import com.example.massagestore.dao.UserDBDao;
import com.example.massagestore.dao.entity.MemberDB;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.dao.entity.UserDB;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 老表 on 2019/9/1.
 */

public class DetailsDialog extends BaseDialog implements View.OnClickListener {

    private ImageView back_details;
    private TextView details_name;
    private TextView details_time;
    private RadioButton button1_details;
    private RadioButton button2_details;
    private EditText details_price;
    private EditText details_member;
    private TextView edit_price;
    private RecyclerView recycler_user;
    private Button button;
    private ProjectDB projectDB;
    private RelativeLayout relMember;
    private RecyclerView recycle_search;

    private MemberAdapterSearch memberAdapterSearch;
    private UserListAdapter userListAdapter;
    private List<MemberDB> memberDBList = new ArrayList<>();
    private List<MemberDB> memberSearchList = new ArrayList<>();
    private List<UserDB> userDBList = new ArrayList<>();
    private MemberDBDao memberDBDao;
    private UserDBDao userDBDao;
    private Context context;

    public DetailsDialog(@NonNull Context context, ProjectDB projectDB) {
        super(context);
        this.context = context;
        this.projectDB = projectDB;
    }

    @Override
    protected void init() {
        details_name.setText(projectDB.getName());
        details_time.setText(projectDB.getTime() + "分钟");
        details_price.setText(projectDB.getPrice());

        initDataBase();
        initRecycle();
        initListener();
    }

    private void initDataBase() {
        DaoSession daoSession1 = DaoMaster.newDevSession(context, MemberDBDao.TABLENAME);
        memberDBDao = daoSession1.getMemberDBDao();
        memberDBList.addAll(memberDBDao.loadAll());

        DaoSession daoSession2 = DaoMaster.newDevSession(context, UserDBDao.TABLENAME);
        userDBDao = daoSession2.getUserDBDao();
        userDBList.addAll(userDBDao.loadAll());
    }

    private void initRecycle() {
        recycle_search.setLayoutManager(new LinearLayoutManager(context));
        memberAdapterSearch = new MemberAdapterSearch(R.layout.item_member_search, memberDBList);
        recycle_search.setAdapter(memberAdapterSearch);

        recycler_user.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        userListAdapter = new UserListAdapter(R.layout.item_user_list, userDBList);
        recycler_user.setAdapter(userListAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_details;
    }

    @Override
    protected void initView() {
        back_details = findViewById(R.id.back_details);
        details_name = findViewById(R.id.details_name);
        details_time = findViewById(R.id.details_time);
        button1_details = findViewById(R.id.button1_details);
        button2_details = findViewById(R.id.button2_details);
        details_price = findViewById(R.id.details_price);
        edit_price = findViewById(R.id.edit_price);
        recycler_user = findViewById(R.id.details_user);
        button = findViewById(R.id.button);
        relMember = findViewById(R.id.rel_member);
        details_member = findViewById(R.id.details_member);
        recycle_search = findViewById(R.id.recycle_search);

        back_details.setOnClickListener(this);
        button1_details.setOnClickListener(this);
        button2_details.setOnClickListener(this);
        edit_price.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_details:
                dismiss();
                break;
            case R.id.button1_details:  //  散客
                relMember.setVisibility(View.GONE);
                break;
            case R.id.button2_details:  //  会员
                relMember.setVisibility(View.VISIBLE);
                break;
            case R.id.edit_price:

                break;
            case R.id.details_user:

                break;
            case R.id.button:

                break;
        }
    }

    private void initListener() {
        details_member.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String edit = s.toString();
                if (TextUtils.isEmpty(edit)) {
                    recycle_search.setVisibility(View.GONE);
                } else {
                    memberAdapterSearch.getFilter().filter(edit);
                }
            }
        });

        memberAdapterSearch.setOnClickListener(new MemberAdapterSearch.OnClickListener() {
            @Override
            public void onFilterResults(List<MemberDB> resultBeans) {
                memberSearchList.clear();
                memberSearchList.addAll(resultBeans);
                memberAdapterSearch.setNewData(resultBeans);
                recycle_search.setVisibility(View.VISIBLE);
            }
        });

        memberAdapterSearch.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                details_member.setText(memberSearchList.get(position).getName());
                recycle_search.setVisibility(View.GONE);
            }
        });

        userListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserDB userDB = userDBList.get(position);
                if (userDB.isCheck()){
                    userDB.setCheck(false);
                }else {
                    userDB.setCheck(true);
//                    for (int i = 0; i < userDBList; i++) {
//
//                    }
                }
            }
        });
    }
}
