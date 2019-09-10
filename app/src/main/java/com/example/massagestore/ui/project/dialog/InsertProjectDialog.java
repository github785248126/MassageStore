package com.example.massagestore.ui.project.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.massagestore.R;
import com.example.massagestore.base.BaseDialog;
import com.example.massagestore.dao.DaoMaster;
import com.example.massagestore.dao.DaoSession;
import com.example.massagestore.dao.ProjectDBDao;
import com.example.massagestore.dao.entity.ProjectDB;
import com.example.massagestore.eventbus.EventCode;
import com.example.massagestore.util.AmountEditText;
import com.example.massagestore.util.EventBusUtil;
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.util.ToastUtils;
import java.util.List;

/**
 * Created by 老表 on 2019/8/25.
 */

public class InsertProjectDialog extends BaseDialog implements View.OnClickListener{
    private ImageView back;
    private ImageView save;
    private EditText editName;
    private AmountEditText editPrice;
    private AmountEditText editCommission;
    private EditText editTime;
    private EditText editRemarks;
    private SwitchCompat switchCompat;
    private EditText editV1;
    private EditText editV2;
    private EditText editV3;
    private EditText editV4;
    private RelativeLayout relSwitch;
    private ProjectDBDao projectDBDao;
    private Context context;

    public InsertProjectDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void init() {
        DaoSession daoSession = DaoMaster.newDevSession(context, ProjectDBDao.TABLENAME);
        projectDBDao = daoSession.getProjectDBDao();
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
        editTime = findViewById(R.id.edit_project_dialog_time);
        editRemarks = findViewById(R.id.edit_project_dialog_remarks);
        switchCompat = findViewById(R.id.switch_project_dialog);
        relSwitch = findViewById(R.id.rel_switch);
        editV1 = findViewById(R.id.v1_price);
        editV2 = findViewById(R.id.v2_price);
        editV3 = findViewById(R.id.v3_price);
        editV4 = findViewById(R.id.v4_price);

        back.setOnClickListener(this);
        save.setOnClickListener(this);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    relSwitch.setVisibility(View.VISIBLE);
                }else {
                    relSwitch.setVisibility(View.GONE);
                }
            }
        });
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
                String time = editTime.getText().toString().trim();
                String remarks = editRemarks.getText().toString().trim();

                //  插入到数据库
                if (TextUtils.isEmpty(name)){
                    ToastUtils.showTextLong("请输入名称");
                } else if (TextUtils.isEmpty(price)){
                    ToastUtils.showTextLong("请输入金额");
                } else if (TextUtils.isEmpty(commission)){
                    ToastUtils.showTextLong("请输入员工提成");
                }else if (TextUtils.isEmpty(time)){
                    ToastUtils.showTextLong("请输入项目时长");
                }else {
                    //  查询数据库是否存在该项目
                    List<ProjectDB> queryBuilder = projectDBDao.queryBuilder().where(ProjectDBDao.Properties.Name.eq(name)).list();
                    if (null != queryBuilder && queryBuilder.size() > 0){
                        ToastUtils.showTextLong("项目名称已存在");
                    }else {
                        String v1 = editV1.getText().toString();
                        String v2 = editV2.getText().toString();
                        String v3 = editV3.getText().toString();
                        String v4 = editV4.getText().toString();
                        projectDBDao.insert(new ProjectDB(null,name,price,time,commission,remarks,v1,v2,v3,v4));
                        EventBusUtil.sendStickyEvent(new EventMessage(EventCode.ProjectListFragment_UPDATE));
                        ToastUtils.showTextLong("保存成功");
                        dismiss();
                    }
                }
                break;
        }
    }
}
