package com.example.massagestore.ui.member.dialog;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.example.massagestore.R;
import com.example.massagestore.base.BaseDialog;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.MemberDBDao;
import com.example.massagestore.dao.entity.MemberDB;
import com.example.massagestore.eventbus.EventCode;
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.util.EventBusUtil;
import com.example.massagestore.util.ToastUtils;

import java.util.List;

/**
 * 创建日期：2019/9/4
 * 创建人：崔斌浩
 * QQ:785248126
 */
public class InsertMemberDialog extends BaseDialog implements View.OnClickListener{
    private ImageView back;
    private ImageView save;
    private EditText editName;
    private EditText editPhone;
    private EditText editPrice;
    private EditText editRemarks;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private MemberDBDao memberDBDao;
    private String level;
    private Context context;

    public InsertMemberDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init() {
        level = "LV1";
        radio1.setBackground(context.getDrawable(R.mipmap.v1_check));
        DaoSession daoSession = DaoMaster.newDevSession(context, MemberDBDao.TABLENAME);
        memberDBDao = daoSession.getMemberDBDao();
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_member_insert;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_member_insert);
        save = findViewById(R.id.save_member_insert);
        editName = findViewById(R.id.edit_member_dialog_name);
        editPhone = findViewById(R.id.edit_project_dialog_phone);
        editPrice = findViewById(R.id.edit_project_dialog_price);
        editRemarks = findViewById(R.id.edit_project_dialog_remarks);
        radio1 = findViewById(R.id.radio_v1);
        radio2 = findViewById(R.id.radio_v2);
        radio3 = findViewById(R.id.radio_v3);
        radio4 = findViewById(R.id.radio_v4);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);
        radio4.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_member_insert:
                dismiss();
                break;
            case R.id.save_member_insert:
                String name = editName.getText().toString();
                String phone = editPhone.getText().toString();
                String price = editPrice.getText().toString();
                String remarks = editRemarks.getText().toString();
                if (TextUtils.isEmpty(name)){
                    ToastUtils.showTextLong("请输入会员名");
                } else if (TextUtils.isEmpty(phone)){
                    ToastUtils.showTextLong("请输入手机号码");
                } else if (TextUtils.isEmpty(price)){
                    ToastUtils.showTextLong("请输入充值金额");
                } else {
                    //  查询数据库是否存在该会员
                    List<MemberDB> queryBuilder = memberDBDao.queryBuilder().where(MemberDBDao.Properties.Name.eq(name)).list();
                    if (null != queryBuilder && queryBuilder.size() > 0){
                        ToastUtils.showTextLong("会员已存在");
                    }else {
                        memberDBDao.insert(new MemberDB(null,name,phone,price,level,remarks));
                        EventBusUtil.sendStickyEvent(new EventMessage(EventCode.MemberListFragment_UPDATE));
                        ToastUtils.showTextLong("保存成功");
                        dismiss();
                    }
                }
                break;
            case R.id.radio_v1:
                level = "LV1";
                radio1.setBackground(context.getDrawable(R.mipmap.v1_check));
                radio2.setBackground(context.getDrawable(R.mipmap.v2));
                radio3.setBackground(context.getDrawable(R.mipmap.v3));
                radio4.setBackground(context.getDrawable(R.mipmap.v4));
                break;
            case R.id.radio_v2:
                level = "LV2";
                radio2.setBackground(context.getDrawable(R.mipmap.v2_check));
                radio1.setBackground(context.getDrawable(R.mipmap.v1));
                radio3.setBackground(context.getDrawable(R.mipmap.v3));
                radio4.setBackground(context.getDrawable(R.mipmap.v4));
                break;
            case R.id.radio_v3:
                level = "LV3";
                radio3.setBackground(context.getDrawable(R.mipmap.v3_check));
                radio1.setBackground(context.getDrawable(R.mipmap.v1));
                radio2.setBackground(context.getDrawable(R.mipmap.v2));
                radio4.setBackground(context.getDrawable(R.mipmap.v4));
                break;
            case R.id.radio_v4:
                level = "LV4";
                radio4.setBackground(context.getDrawable(R.mipmap.v4_check));
                radio1.setBackground(context.getDrawable(R.mipmap.v1));
                radio2.setBackground(context.getDrawable(R.mipmap.v2));
                radio3.setBackground(context.getDrawable(R.mipmap.v3));
                break;
        }
    }
}
