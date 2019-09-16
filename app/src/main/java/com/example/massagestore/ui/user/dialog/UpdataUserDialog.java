package com.example.massagestore.ui.user.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.massagestore.R;
import com.example.massagestore.base.BaseDialog;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.UserDBDao;
import com.example.massagestore.dao.entity.UserDB;
import com.example.massagestore.eventbus.EventCode;
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.util.EventBusUtil;
import com.example.massagestore.util.ToastUtils;

/**
 * Created by 老表 on 2019/9/1.
 */

public class UpdataUserDialog extends BaseDialog implements View.OnClickListener {
    private Context context;
    private ImageView back;
    private ImageView save;
    private EditText editName;
    private EditText editPhone;
    private UserDBDao userDBDao;
    private UserDB userDB;

    public UpdataUserDialog(@NonNull Context context, UserDB userDB) {
        super(context);
        this.context = context;
        this.userDB = userDB;
    }

    @Override
    protected void init() {
        DaoSession daoSession = DaoMaster.newDevSession(context, UserDBDao.TABLENAME);
        userDBDao = daoSession.getUserDBDao();
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_user_insert;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_user_insert);
        save = findViewById(R.id.save_user_insert);
        editName = findViewById(R.id.edit_project_dialog_name);
        editPhone = findViewById(R.id.edit_user_dialog_phone);

        back.setOnClickListener(this);
        save.setOnClickListener(this);

        editName.setText(userDB.getName());
        editPhone.setText(userDB.getPhone());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_user_insert:
                dismiss();
                break;
            case R.id.save_user_insert:
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                //  插入到数据库
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showTextLong("请输入名称");
                } else if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showTextLong("请输入手机号码");
                } else {
                    userDB.setName(name);
                    userDB.setPhone(phone);
                    userDBDao.update(userDB);
                    EventBusUtil.sendStickyEvent(new EventMessage(EventCode.UserListFragment_UPDATE));
                    ToastUtils.showTextLong("保存成功");
                    dismiss();
                }
                break;
        }
    }
}
