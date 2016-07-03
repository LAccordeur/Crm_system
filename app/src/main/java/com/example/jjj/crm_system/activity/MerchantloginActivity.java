package com.example.jjj.crm_system.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;

public class MerchantloginActivity extends BaseActivity {
    private EditText et_username_merchantlogin;
    private EditText et_password_merchantlogin;
    private Button bt_login_merchantlogin;
    private TextView tv_back_merchantlogin;

    @Override
    protected int getRootView() {
        return R.layout.activity_merchantlogin;
    }

    @Override
    protected void initData() {
        et_username_merchantlogin = (EditText)findViewById(R.id.et_username_meichantlogin);
        et_password_merchantlogin = (EditText)findViewById(R.id.et_password_meichantpogin);
        bt_login_merchantlogin = (Button)findViewById(R.id.bt_login_merchantlogin);
        tv_back_merchantlogin = (TextView)findViewById(R.id.tv_back_merchantlogin);

    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {
        tv_back_merchantlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_login_merchantlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MerchantloginActivity.this,MerchantActivity.class);
                startActivity(intent);
            }
        });
    }
}
