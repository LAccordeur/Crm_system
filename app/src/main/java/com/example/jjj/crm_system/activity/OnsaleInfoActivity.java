package com.example.jjj.crm_system.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.domain.CustomerOnsaleObject;
import com.example.jjj.crm_system.service.po.Activity;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.utils.ActivityUtil;
import com.example.jjj.crm_system.utils.ImageLoader;

public class OnsaleInfoActivity extends BaseActivity {
    private ImageView iv_back;
    private Activity onsaleObject;
    private TextView tv_onsalename;
    private ImageView iv_onsale;
    private TextView tv_starttime;
    private TextView tv_endtime;

    private ImageLoader imageLoader;
    private MyProgressDialog myProgressDialog;

    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        onsaleObject = (Activity)getIntent().getSerializableExtra("onsale_info");
        imageLoader = ImageLoader.getInstance(getBaseContext());

    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_onsale_info;
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
        iv_back = (ImageView)findViewById(R.id.iv_back_onsaleinfo);
        tv_onsalename = (TextView)findViewById(R.id.tv_onsalename_onsaleinfo);
        tv_starttime = (TextView)findViewById(R.id.tv_starttime_onsaleinfo);
        tv_endtime = (TextView)findViewById(R.id.tv_endtime_onsaleinfo);
        iv_onsale = (ImageView)findViewById(R.id.iv_image_onsaleinfo);

        tv_onsalename.setText(onsaleObject.getActivityname());
        tv_starttime.setText(onsaleObject.getActivitystarttime());
        tv_endtime.setText(onsaleObject.getActivitycuttime());
        imageLoader.loadImage(onsaleObject.getActivityImageUrl(),iv_onsale);
    }
}
