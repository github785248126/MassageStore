package com.example.massagestore.ui.project.dialog;

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
import com.example.massagestore.dao.ProjectDBDao;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.eventbus.EventCode;
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.util.AmountEditText;
import com.example.massagestore.util.EventBusUtil;
import com.example.massagestore.util.ToastUtils;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by 老表 on 2019/8/31.
 */

public class UpdataProjectDialog extends BaseDialog implements View.OnClickListener{
    private ImageView back;
    private ImageView save;
    private EditText editName;
    private AmountEditText editPrice;
    private AmountEditText editCommission;
    private ProjectDBDao projectDBDao;
    private ProjectDB projectDB;
    private Context context;

    public UpdataProjectDialog(@NonNull Context context, ProjectDB projectDB) {
        super(context);
        this.context = context;
        this.projectDB = projectDB;
    }

    @Override
    protected void init() {
        DaoSession daoSession = DaoMaster.newDevSession(context, ProjectDBDao.TABLENAME);
        projectDBDao = daoSession.getProjectDBDao();
//        projectDB = projectDBDao.queryBuilder().where(ProjectDBDao.Properties.Name.eq(projectDB.getName())).unique();

        editName.setText(projectDB.getName());
        editPrice.setText(projectDB.getPrice());
        editCommission.setText(projectDB.getCommission());
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_project_insert;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_projeck_insert);
        save = findViewById(R.id.save_projeck_insert);
        editName = findViewById(R.id.edit_project_dialog_name);
        editPrice = findViewById(R.id.edit_project_dialog_price);
        editCommission = findViewById(R.id.edit_project_dialog_commission);

        back.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_projeck_insert:
                dismiss();
                break;
            case R.id.save_projeck_insert:
                String name = editName.getText().toString().trim();
                String price = editPrice.getText().toString().trim();
                String commission = editCommission.getText().toString().trim();
                //  插入到数据库
                if (TextUtils.isEmpty(name)){
                    ToastUtils.showTextLong("请输入名称");
                } else if (TextUtils.isEmpty(price)){
                    ToastUtils.showTextLong("请输入金额");
                } else if (TextUtils.isEmpty(commission)){
                    ToastUtils.showTextLong("请输入员工提成");
                }else {
                    projectDB.setName(name);
                    projectDB.setPrice(price);
                    projectDB.setCommission(commission);
                    projectDBDao.update(projectDB);

                    EventBusUtil.sendStickyEvent(new EventMessage(EventCode.ProjectListFragment_UPDATE));
                    ToastUtils.showTextLong("修改成功");
                    dismiss();
                }
                break;
        }
    }
}
