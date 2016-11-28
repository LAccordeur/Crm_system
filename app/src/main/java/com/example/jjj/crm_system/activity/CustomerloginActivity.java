package com.example.jjj.crm_system.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.utils.CountDownTimerUtils;
import com.example.jjj.crm_system.utils.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;
import cn.smssdk.SMSSDK;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.smssdk.EventHandler;


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
    private EventHandler eventHandler;
    private MyProgressDialog myProgressDialog;

    @Override
    protected int getRootView() {
        return R.layout.activity_customerlogin;
    }

    @Override
    protected void init() {
        myProgressDialog = new MyProgressDialog(this);
        cn.smssdk.SMSSDK.initSDK(this,"1933fe74843bb","e1561120e91c3dc95908a4f1878e6ba0");
        eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == cn.smssdk.SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == cn.smssdk.SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        System.out.println("验证成功！");
                        Intent intent = new Intent(CustomerloginActivity.this,CustomerActivity.class);
                        /*HashMap<String,Object> map = (HashMap<String, Object>) data;
                        //System.out.println(map);
                        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                        while(iterator.hasNext()){
                            intent.putExtra("phonenum",iterator.next().getKey());
                        }*/

                        myProgressDialog.dismiss();
                        startActivity(intent);
                        cn.smssdk.SMSSDK.unregisterAllEventHandler();
                    }else if (event == cn.smssdk.SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功

                    }else if (event ==cn.smssdk.SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
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
                }else if(!isMobileNo(phoneString)) {//判断是否是合法的电话号码
                    Toast.makeText(getBaseContext(),"请输入合法的电话号码！",Toast.LENGTH_SHORT).show();
                    et_phonenum_customerlogin.setText("");
                }else
                {
                    new NetTask(getBaseContext()){
                        /**
                         * 异步任务执行前的预处理
                         */
                        @Override
                        protected void onStart() {
                            super.onStart();
                        }

                        /**
                         * 加载数据
                         *
                         * @return
                         */
                        @Override
                        protected JSONObject onLoad() {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                cn.smssdk.SMSSDK.registerEventHandler(eventHandler);
                                cn.smssdk.SMSSDK.getSupportedCountries();
                                cn.smssdk.SMSSDK.getVerificationCode("86", phoneString);
                                jsonObject.put("StateCode",1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return jsonObject;
                        }

                        /**
                         * 请求数据成功后的处理
                         *
                         * @param jsonObject
                         * @throws Exception
                         */
                        @Override
                        protected void onSuccess(JSONObject jsonObject) throws Exception {
                            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(60000,1000,bt_sendVerificationcode_customerlogin);
                            countDownTimerUtils.start();
                        }
                    }.execute();
                    }






               /* Intent intent = new Intent(CustomerloginActivity.this,CustomerActivity.class);
                startActivity(intent);*/
            }
        });
        bt_login_customerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneString = et_phonenum_customerlogin.getText().toString();
                VerificationCodeString = et_VerificationCode_customerlogin.getText().toString();

                if (VerificationCodeString.equals("")){
                    Toast.makeText(getBaseContext(),"请输入验证码",Toast.LENGTH_SHORT).show();
                }
                new NetTask(getBaseContext()){
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
                            cn.smssdk.SMSSDK.submitVerificationCode("86",phoneString,VerificationCodeString);
                            jsonObject.put("StateCode",1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return jsonObject;
                    }

                    /**
                     * 请求数据成功后的处理
                     *
                     * @param jsonObject
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(JSONObject jsonObject) throws Exception {

                    }

                    /**
                     * 请求失败的处理逻辑
                     */
                    @Override
                    protected void onFail() {
                        super.onFail();
                        Toast.makeText(getBaseContext(),"验证码错误",Toast.LENGTH_SHORT).show();
                    }
                }.execute();



            }
        });

    }

    private boolean isMobileNo(String num){
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(num))
            return false;
        else
            return num.matches(telRegex);
    }
    private class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:

            }
        }
    }
}
