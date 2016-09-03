package com.example.jjj.crm_system.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;

public class GoodsInfoActivity extends BaseActivity {
    private ImageView iv_back;
    private String top_background;
    private RelativeLayout rl_top;

    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        //top_background = (String)getIntent().getStringExtra("top_background");

    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_goods_info;
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
        iv_back = (ImageView)findViewById(R.id.iv_back_goodsinfo);

       // if(top_background!=null&&top_background!=""){
           // rl_top.setBackgroundColor(Color.parseColor(top_background));
         //   rl_top.setBackgroundColor(Color.RED);
       // }

    }
}
