package com.example.massagestore.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.massagestore.R;
import com.example.massagestore.base.BaseDialog;
import com.example.massagestore.dao.ProjectDBDao;
import com.example.massagestore.dao.entity.ProjectDB;

/**
 * Created by 老表 on 2019/9/1.
 */

public class DetailsDialog extends BaseDialog implements View.OnClickListener {

    private ImageView back_details;
    private TextView details_name;
    private TextView details_time;
    private RadioButton button1_details;
    private RadioButton button2_details;
    private TextView details_price;
    private TextView edit_price;
    private TextView details_user;
    private Button button;
    private ProjectDB projectDB;
    private Context context;

    public DetailsDialog(@NonNull Context context,ProjectDB projectDB) {
        super(context);
        this.context = context;
        this.projectDB = projectDB;
    }

    @Override
    protected void init() {
        details_name.setText(projectDB.getName());
        details_time.setText(projectDB.getTime()+"分钟");
        details_price.setText(projectDB.getPrice());
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
        details_user = findViewById(R.id.details_user);
        button = findViewById(R.id.button);

        back_details.setOnClickListener(this);
        button1_details.setOnClickListener(this);
        button2_details.setOnClickListener(this);
        edit_price.setOnClickListener(this);
        details_user.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_details:
                dismiss();
                break;
            case R.id.button1_details:

                break;
            case R.id.button2_details:

                break;
            case R.id.edit_price:

                break;
            case R.id.details_user:

                break;
            case R.id.button:

                break;
        }
    }
}
