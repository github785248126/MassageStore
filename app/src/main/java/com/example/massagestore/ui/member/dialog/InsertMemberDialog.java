package com.example.massagestore.ui.member.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.massagestore.R;
import com.example.massagestore.base.BaseDialog;

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
    private Context context;

    public InsertMemberDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void init() {
        radio1.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_member_insert:
                dismiss();
                break;
            case R.id.save_member_insert:

                break;
            case R.id.radio_v1:
                radio1.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                break;
            case R.id.radio_v2:
                radio2.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                break;
            case R.id.radio_v3:
                radio3.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                break;
            case R.id.radio_v4:
                radio4.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                break;
        }
    }
}
