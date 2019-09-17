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
import com.example.massagestore.eventbus.EventMessage;
import com.example.massagestore.util.AmountEditText;
import com.example.massagestore.util.EventBusUtil;
import com.example.massagestore.util.ToastUtils;

/**
 * Created by 老表 on 2019/8/31.
 */
public class UpdataProjectDialog extends BaseDialog implements View.OnClickListener {
    private ImageView back;
    private ImageView save;
    private EditText editName;
    private AmountEditText editPrice;
    private AmountEditText editCommission;
    private EditText editTime;
    private EditText editRemarks;
    private ProjectDBDao projectDBDao;
    private ProjectDB projectDB;
    private EditText editV1;
    private EditText editV2;
    private EditText editV3;
    private EditText editV4;
    private RelativeLayout relSwitch;
    private SwitchCompat switchCompat;
    private String isMember;
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

        isMember = projectDB.getIsMember();
        editName.setText(projectDB.getName());
        editPrice.setText(projectDB.getPrice());
        editTime.setText(projectDB.getTime());
        editCommission.setText(projectDB.getCommission());
        editRemarks.setText(projectDB.getRemarks());
        String v1 = projectDB.getV1();
        String v2 = projectDB.getV2();
        String v3 = projectDB.getV3();
        String v4 = projectDB.getV4();

        if (isMember.equals("0")){
            relSwitch.setVisibility(View.GONE);
            switchCompat.setChecked(false);
        }else {
            relSwitch.setVisibility(View.VISIBLE);
            switchCompat.setChecked(true);
            editV1.setText(v1);
            editV2.setText(v2);
            editV3.setText(v3);
            editV4.setText(v4);
        }
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
        relSwitch = findViewById(R.id.rel_switch);
        switchCompat = findViewById(R.id.switch_project_dialog);
        editRemarks = findViewById(R.id.edit_project_dialog_remarks);
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
                    isMember = "1";
                    relSwitch.setVisibility(View.VISIBLE);
                }else {
                    isMember = "0";
                    relSwitch.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_projeck_insert:
                dismiss();
                break;
            case R.id.save_projeck_insert:
                String name = editName.getText().toString().trim();
                String price = editPrice.getText().toString().trim();
                String commission = editCommission.getText().toString().trim();
                String time = editTime.getText().toString().trim();
                //  插入到数据库
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showTextLong("请输入名称");
                } else if (TextUtils.isEmpty(price)) {
                    ToastUtils.showTextLong("请输入金额");
                } else if (TextUtils.isEmpty(commission)) {
                    ToastUtils.showTextLong("请输入员工提成");
                } else {
                    projectDB.setName(name);
                    projectDB.setPrice(price);
                    projectDB.setCommission(commission);
                    projectDB.setTime(time);
                    projectDB.setIsMember(isMember);
                    projectDB.setRemarks(editRemarks.getText().toString());
                    projectDB.setV1(editV1.getText().toString());
                    projectDB.setV2(editV2.getText().toString());
                    projectDB.setV3(editV3.getText().toString());
                    projectDB.setV4(editV4.getText().toString());

                    Log.e("--->>>",projectDB.toString());
                    projectDBDao.insertOrReplace(projectDB);
                    Log.e("--->>>>>",projectDBDao.loadAll().toString());
                    EventBusUtil.sendStickyEvent(new EventMessage(EventCode.ProjectListFragment_UPDATE));
                    ToastUtils.showTextLong("修改成功");
                    dismiss();
                }
                break;
        }
    }
}
