package com.example.jjj.crm_system.ui.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.jjj.crm_system.utils.ActivityUtil;

import org.json.JSONObject;

/**
 * Created by jjj on 2016/5/19 0019.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context baseContext;
    private  JSONObject object;//静态变量保存信息用于与fragment交互数据

    public  JSONObject getObject() {
        return object;
    }

    public  void setObject(JSONObject object) {
        this.object = object;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
