package com.example.jjj.crm_system.ui.Base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.jjj.crm_system.utils.ActivityUtil;

import org.json.JSONObject;

/**
 * Created by jjj on 2016/5/19 0019.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context baseContext;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }*/
        super.onCreate(savedInstanceState);

        ActivityUtil.addActivity(this);
        baseContext = this;
        init();
        setContentView(getRootView());

        initData();
        setListener();

    }

    /**
     * 加载UI前的预初始化
     */
    protected abstract void init();

    /**
     * 加载布局
     * @return 布局id
     */
    protected abstract int getRootView();

    /**
     * 设置监听器
     */
    protected abstract void setListener();

    /**
     * 请求数据，设置UI
     */
    protected abstract void initData();



    /**
     * activity销毁后的操作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 结束activity
     */
    @Override
    public void finish() {
        super.finish();
        ActivityUtil.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
