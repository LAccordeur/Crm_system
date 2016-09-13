package com.example.jjj.crm_system.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.service.po.Customer;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;

public class MemberDetailActivity extends BaseActivity {
    private Customer customer;
    private ImageView iv_back,iv_pic;
    private TextView tv_name,tv_phone,tv_details,tv_totalaccount,tv_totalmoney;
    private ListView lv_consumedetail;

    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        customer = (Customer)getIntent().getSerializableExtra("member_info");

    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_member_detail;
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
        iv_back = (ImageView)findViewById(R.id.iv_back_memberdetail);
        iv_pic = (ImageView)findViewById(R.id.iv_potrait_memberdetail);

        tv_name = (TextView)findViewById(R.id.tv_name_memberdetail);
        tv_phone = (TextView)findViewById(R.id.tv_phonenum_memberdetail);
        tv_details = (TextView)findViewById(R.id.tv_details_memberdetail);
        tv_totalaccount = (TextView)findViewById(R.id.tv_totalaccount_memberdetail);
        tv_totalmoney = (TextView)findViewById(R.id.tv_totalmoney_memberdetail);

        lv_consumedetail = (ListView)findViewById(R.id.lv_consumedetail_memberdeail);

        tv_name.setText(customer.getCustomname());
        tv_phone.setText(customer.getTelephonenumber());
        tv_totalaccount.setText(customer.getBonuspoint());



    }
}
