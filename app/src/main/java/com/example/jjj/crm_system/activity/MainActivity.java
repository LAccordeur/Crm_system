package com.example.jjj.crm_system.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity {
    private Button bt_merchantlogin_main;
    private Button bt_customerlogin_main;

    @Override
    public int getRootView(){
        return R.layout.activity_main;
    }
    @Override
    public void setListener(){
        bt_merchantlogin_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MerchantloginActivity.class);
                startActivity(intent);
            }
        });
        bt_customerlogin_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomerloginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void init(){

    }
    @Override
    public void initData(){
        bt_customerlogin_main = (Button)findViewById(R.id.bt_customerlogin_main);
        bt_merchantlogin_main = (Button)findViewById(R.id.bt_merchantlogin_main);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
            ActivityUtil.appExit(this);
            return true;
        }
        else
            return super.onKeyDown(keyCode, event);


    }
}
