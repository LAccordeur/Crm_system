package com.example.jjj.crm_system.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.ShopownerService;
import com.example.jjj.crm_system.service.po.Shopowner;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.utils.ActivityUtil;

import org.json.JSONObject;

public class MerchintInfoActivity extends BaseActivity {
    private ImageView iv_back;
    private ImageView iv_picture;
    private TextView tv_merchintname;
    private TextView tv_merchintplace;
    private TextView tv_worktime;
    private TextView tv_phonenum;
    private TextView tv_goods;
    private int Intent_id;



    private Shopowner merchint;
    private MyProgressDialog myProgressDialog;


    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        Intent_id = bundle.getInt("intent_id");
        merchint.setAccountid(1);

/*
        try {
            merchint = ShopownerService.getShopownerInf(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        getMerchintInfo();


    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_merchint_info;
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

        tv_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MerchintInfoActivity.this,GoodsInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("intent_id",Intent_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 请求数据，设置UI
     */

    private void getMerchintInfo(){
        new NetTask(getBaseContext()){
            /**
             * 异步任务执行前的预处理
             */
            @Override
            protected void onStart() {
                super.onStart();
                myProgressDialog.show();
            }

            /**
             * 加载数据
             *
             * @return
             */
            @Override
            protected JSONObject onLoad() {
                try {
                    merchint = ShopownerService.getShopownerInf(merchint.getAccountid().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
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
             * 返回错误时的处理逻辑
             *
             * @param errorCode
             * @param errorStr
             */
            @Override
            protected void onError(int errorCode, String errorStr) {
                super.onError(errorCode, errorStr);
            }

            /**
             * 请求失败的处理逻辑
             */
            @Override
            protected void onFail() {
                super.onFail();
            }

            /**
             * 完成后的处理逻辑
             */
            @Override
            protected void onFinish() {
                super.onFinish();
            }
        }.execute();
    }


    //需要商家电话信息
    @Override
    protected void initData() {

        iv_back = (ImageView)findViewById(R.id.iv_back_merchintinfo);
        tv_merchintname = (TextView)findViewById(R.id.tv_merchintname_merchintinfo);
        tv_merchintplace = (TextView)findViewById(R.id.tv_merchintplace_merchintinfo);
        tv_worktime = (TextView)findViewById(R.id.tv_worktime_merchintinfo);
        tv_phonenum = (TextView)findViewById(R.id.tv_phonenum_merchintinfo);
        iv_picture = (ImageView)findViewById(R.id.iv_image_merchintinfo);
        tv_goods = (TextView)findViewById(R.id.tv_togoods_merchintinfo);

        tv_merchintname.setText(merchint.getAccountname());
        tv_merchintplace.setText(merchint.getAccountaddress());
        tv_worktime.setText(merchint.getOpeningtime()+"--"+merchint.getClosingtime());

    }
}
