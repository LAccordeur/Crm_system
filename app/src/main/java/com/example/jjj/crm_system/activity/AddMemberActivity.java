package com.example.jjj.crm_system.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;

public class AddMemberActivity extends BaseActivity {
    private ImageView iv_back;
    private EditText et_name,et_phonenum,et_details;
    private Button bt_ok;
    private String name,phonenum,detail;

    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {

    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_add_member;
    }

    /**
     * 设置监听器
     */
    @Override
    protected void setListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.finishActivty();
            }
        });

    }

    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {
        iv_back = (ImageView)findViewById(R.id.iv_back_addmember);
        bt_ok = (Button)findViewById(R.id.bt_ok_addmember);
        et_name = (EditText)findViewById(R.id.et_name_addmember);
        et_phonenum = (EditText)findViewById(R.id.et_phonenum_addmember);
        et_details = (EditText)findViewById(R.id.et_details_addmember);


    }
}
