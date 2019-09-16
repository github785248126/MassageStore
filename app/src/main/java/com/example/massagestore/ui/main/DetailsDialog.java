package com.example.massagestore.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.massagestore.dao.OrderDBDao;
import com.example.massagestore.dao.UserDBDao;
import com.example.massagestore.dao.entity.MemberDB;
import com.example.massagestore.dao.entity.OrderDB;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.dao.entity.UserDB;
import com.example.massagestore.util.AmountUtils;
import com.example.massagestore.util.ToastUtils;

import org.greenrobot.eventbus.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private RelativeLayout member;
    private MemberAdapterSearch memberAdapterSearch;
    private UserListAdapter userListAdapter;
    private List<MemberDB> memberDBList = new ArrayList<>();
    private List<MemberDB> memberSearchList = new ArrayList<>();
    private List<UserDB> userDBList = new ArrayList<>();
    private MemberDBDao memberDBDao;
    private UserDBDao userDBDao;
    private Context context;
    private TextWatcher textWatcher;
    private String isMember = "0";
    private String userName = null;
    private OrderDBDao orderDBDao;
    private String ys_price;

    public DetailsDialog(@NonNull Context context, ProjectDB projectDB) {
        super(context);
        this.context = context;
        this.projectDB = projectDB;
    }

    @Override
    protected void init() {
        details_price.setCursorVisible(false);
        details_name.setText(projectDB.getName());
        details_time.setText(projectDB.getTime() + "分钟");
        details_price.setText(AmountUtils.formatMoney(projectDB.getPrice()));
        ys_price = projectDB.getPrice();

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

        DaoSession daoSession3 = DaoMaster.newDevSession(context, OrderDBDao.TABLENAME);
        orderDBDao = daoSession3.getOrderDBDao();
    }

    private void initRecycle() {
        recycle_search.setLayoutManager(new LinearLayoutManager(context));
        memberAdapterSearch = new MemberAdapterSearch(R.layout.item_member_search, memberDBList);
        recycle_search.setAdapter(memberAdapterSearch);

        recycler_user.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
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
        member = findViewById(R.id.member);

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
                isMember = "0";
                relMember.setVisibility(View.GONE);
                break;
            case R.id.button2_details:  //  会员
                isMember = "1";
                relMember.setVisibility(View.VISIBLE);
                break;
            case R.id.edit_price:
                details_price.setCursorVisible(true);
                break;
            case R.id.button:
                String projectName = details_name.getText().toString();
                String projectMember = details_member.getText().toString();
                String projectPrice = details_price.getText().toString();

                if (isMember.equals("1") && TextUtils.isEmpty(projectMember)) {
                    ToastUtils.showTextLong("请输入会员");
                } else if (TextUtils.isEmpty(projectPrice)) {
                    ToastUtils.showTextLong("金额不能为空");
                } else {
                    if (TextUtils.isEmpty(projectName)){
                        Log.e("--------->>>",new OrderDB(null, getOrderId(), projectName, projectMember, userName,"", ys_price, AmountUtils.formatMoney(projectPrice)).toString());
                        orderDBDao.insert(new OrderDB(null, getOrderId(), projectName, projectMember, userName,"", ys_price, AmountUtils.formatMoney(projectPrice)));
                    }else {
                        Log.e("--------->>>",new OrderDB(null, getOrderId(), projectName, projectMember, userName,projectDB.getCommission(), ys_price, AmountUtils.formatMoney(projectPrice)).toString());
                        orderDBDao.insert(new OrderDB(null, getOrderId(), projectName, projectMember, userName,projectDB.getCommission(), ys_price, AmountUtils.formatMoney(projectPrice)));
                    }
                    ToastUtils.showTextLong("订单已保存");
                    dismiss();
                }
                break;
        }
    }

    private void initListener() {
        textWatcher = new TextWatcher() {
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
                    member.setVisibility(View.GONE);
                } else {
                    memberAdapterSearch.getFilter().filter(edit);
                }
            }
        };
        details_member.addTextChangedListener(textWatcher);

        memberAdapterSearch.setOnClickListener(new MemberAdapterSearch.OnClickListener() {
            @Override
            public void onFilterResults(List<MemberDB> resultBeans) {
                memberSearchList.clear();
                memberSearchList.addAll(resultBeans);
                memberAdapterSearch.setNewData(resultBeans);
                member.setVisibility(View.VISIBLE);
            }
        });

        memberAdapterSearch.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                details_member.removeTextChangedListener(textWatcher);
                details_member.setText(memberSearchList.get(position).getName());
                details_member.addTextChangedListener(textWatcher);
                member.setVisibility(View.GONE);

                switch (memberSearchList.get(position).getLevel()){
                    case "LV1":
                        details_price.setText(AmountUtils.formatMoney(projectDB.getV1()));
                        ys_price = projectDB.getV1();
                        break;
                    case "LV2":
                        details_price.setText(AmountUtils.formatMoney(projectDB.getV2()));
                        ys_price = projectDB.getV2();
                        break;
                    case "LV3":
                        details_price.setText(AmountUtils.formatMoney(projectDB.getV3()));
                        ys_price = projectDB.getV3();
                        break;
                    case "LV4":
                        details_price.setText(AmountUtils.formatMoney(projectDB.getV4()));
                        ys_price = projectDB.getV4();
                        break;
                }
            }
        });

        userListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserDB userDB = userDBList.get(position);
                if (userDB.isCheck()) {
                    userName = "";
                    userDB.setCheck(false);
                } else {
                    for (int i = 0; i < userDBList.size(); i++) {
                        if (i == position) {
                            userDBList.get(i).setCheck(true);
                        } else {
                            userDBList.get(i).setCheck(false);
                        }
                    }
                    userDB.setCheck(true);
                    userName = userDB.getName();
                }
                userListAdapter.notifyDataSetChanged();
            }
        });
    }

    private String getOrderId() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        return date;
    }
}
