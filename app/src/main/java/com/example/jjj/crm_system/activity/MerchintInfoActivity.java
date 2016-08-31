package com.example.jjj.crm_system.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;

public class MerchintInfoActivity extends BaseActivity {
    private ImageView iv_back;
    private ImageView iv_picture;
    private TextView tv_merchintname;
    private TextView tv_merchintplace;
    private TextView tv_worktime;
    private TextView tv_phonenum;
    private TextView tv_goods;

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
            }
        });
    }

    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {

        iv_back = (ImageView)findViewById(R.id.iv_back_merchintinfo);
        tv_merchintname = (TextView)findViewById(R.id.tv_merchintname_merchintinfo);
        tv_merchintplace = (TextView)findViewById(R.id.tv_merchintplace_merchintinfo);
        tv_worktime = (TextView)findViewById(R.id.tv_worktime_merchintinfo);
        tv_phonenum = (TextView)findViewById(R.id.tv_phonenum_merchintinfo);
        iv_picture = (ImageView)findViewById(R.id.iv_image_merchintinfo);
        tv_goods = (TextView)findViewById(R.id.tv_togoods_merchintinfo);
    }
}
