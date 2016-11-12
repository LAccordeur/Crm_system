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
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.ShopownerService;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.utils.ActivityUtil;
import com.example.jjj.crm_system.utils.GsonUtil;

import org.json.JSONObject;

import static com.example.jjj.crm_system.service.ShopownerService.shopownerValidate;

public class MerchantloginActivity extends BaseActivity {
    private EditText et_username_merchantlogin;
    private EditText et_password_merchantlogin;
    private Button bt_login_merchantlogin;
    private TextView tv_back_merchantlogin;
    private MyProgressDialog myProgressDialog;
    private Integer MerchintId;

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

        myProgressDialog = new MyProgressDialog(baseContext);
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
                final String username,password;
                username = et_username_merchantlogin.getText().toString();
                password = et_password_merchantlogin.getText().toString();
                if (username==null||username.equals("")){
                    Toast.makeText(baseContext,"用户名不能为空!",Toast.LENGTH_SHORT).show();
                }
                else if (password==null||password.equals("")){
                    Toast.makeText(baseContext,"密码不能为空!",Toast.LENGTH_SHORT).show();
                }else{

                    new NetTask(baseContext){
                        /**
                         * 异步任务执行前的预处理
                         */
                        @Override
                        protected void onStart() {
                            myProgressDialog.show();
                            super.onStart();
                        }

                        @Override
                        protected JSONObject onLoad() {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                MerchintId = ShopownerService.shopownerValidate(username,password);
                                jsonObject.put("StateCode",1);
                                return jsonObject;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return jsonObject;
                        }

                        /**
                         * 请求失败的处理逻辑
                         */
                        @Override
                        protected void onFail() {
                            super.onFail();
                            myProgressDialog.dismiss();
                        }

                        @Override
                        protected void onSuccess(JSONObject jsonObject) throws Exception {
                            //myProgressDialog.dismiss();
                            Toast.makeText(getBaseContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MerchantloginActivity.this,MerchantActivity.class);
                            intent.putExtra("MerchintId",MerchintId);
                            startActivity(intent);
                            finish();
                        }
                    }.execute();

                }



            }


        });
    }
}
