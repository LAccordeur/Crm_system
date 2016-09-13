package com.example.jjj.crm_system.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.CustomDialog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


/**
 * 客户登录程序
 */
public class CustomerloginActivity extends BaseActivity {
    private String phoneString,VerificationCodeString;
    private EditText et_phonenum_customerlogin;
    private Button bt_sendVerificationcode_customerlogin;
    private EditText et_VerificationCode_customerlogin;
    private TextView tv_back_customerlogin;
    private Button bt_login_customerlogin;
    private CustomDialog.Builder builder;
    @Override
    protected int getRootView() {
        return R.layout.activity_customerlogin;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        et_phonenum_customerlogin = (EditText)findViewById(R.id.et_phonenum_customerlogin);
        et_VerificationCode_customerlogin = (EditText)findViewById(R.id.et_VerificationCode_customerlogin);
        bt_sendVerificationcode_customerlogin = (Button)findViewById(R.id.bt_getVerificationcode_customerlogin);
        tv_back_customerlogin = (TextView)findViewById(R.id.tv_back_customerlogin);
        bt_login_customerlogin = (Button)findViewById(R.id.bt_login_customerlogin);
    }

    @Override
    protected void setListener() {
        tv_back_customerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_sendVerificationcode_customerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneString = et_phonenum_customerlogin.getText().toString();
                VerificationCodeString = et_VerificationCode_customerlogin.getText().toString();
                if(phoneString.equals("")||phoneString==null){
                    Toast.makeText(getBaseContext(),"电话号码不能为空！",Toast.LENGTH_LONG).show();
                    return;
                   // builder.setMessage("电话号码不能为空！！！");
                   // builder.setTitle("Error");
                   /*
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });*/
                }
                if(VerificationCodeString.equals("")||VerificationCodeString==null){
                   /* builder.setMessage("验证码不能为空！！！");
                    builder.setTitle("Error");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });*/
                    Toast.makeText(getBaseContext(),"验证码不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }




                Intent intent = new Intent(CustomerloginActivity.this,CustomerActivity.class);
                startActivity(intent);
            }
        });
        bt_login_customerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerloginActivity.this,CustomerActivity.class);
                startActivity(intent);
            }
        });

    }
}
