package com.example.jjj.crm_system.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.fragment.AccountFragment;
import com.example.jjj.crm_system.fragment.HomeFragment;
import com.example.jjj.crm_system.fragment.ScoreFragment;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MerchantActivity extends BaseActivity {
    private RadioButton rb_mydiscount_merchant,rb_home_merchant,rb_score_merchant;
    private RadioGroup rg_merchant;
    private MyViewPager vp_merchant;
    private TextView tv_title_merchant;
    private TextView tv_back_merchant;
    private List<BaseFragment> fragments;
    private android.support.v4.app.FragmentManager fm;
    private MyPagerAdapter adapter;


    @Override
    protected int getRootView() {
        return R.layout.activity_merchant;
    }

    @Override
    protected void init() {
        fragments = new ArrayList<BaseFragment>();
        fragments.add(0,new AccountFragment());
        fragments.add(1,new HomeFragment());
        fragments.add(2,new ScoreFragment());
        fm = getSupportFragmentManager();

    }

    @Override
    protected void setListener() {
        vp_merchant.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                alterTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (vp_merchant.getCurrentItem()) {
                    case 0:
                        rb_mydiscount_merchant.setChecked(true);
                        break;
                    case 1:
                        rb_home_merchant.setChecked(true);
                        break;

                    case 2:
                        rb_score_merchant.setChecked(true);
                        break;
                }
            }
        });

        rg_merchant.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position = -1;
                switch (checkedId) {
                    case R.id.rb_mydiscount_merchant:
                        position = 0;
                        break;
                    case R.id.rb_home_merchant:
                        position = 1;
                        break;
                    case R.id.rb_score_merchant:
                        position = 2;
                        break;
                }
                vp_merchant.setCurrentItem(position, false);
            }
        });

        tv_back_merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MerchantActivity.this,MainActivity.class);
                startActivity(intent);
                
            }
        });

    }

    @Override
    protected void initData() {
        rb_home_merchant = (RadioButton)findViewById(R.id.rb_home_merchant);
        rb_mydiscount_merchant = (RadioButton)findViewById(R.id.rb_mydiscount_merchant);
        rb_score_merchant = (RadioButton)findViewById(R.id.rb_score_merchant);
        vp_merchant = (MyViewPager)findViewById(R.id.vp_merchant);
        tv_title_merchant = (TextView)findViewById(R.id.tv_title_merchant);
        tv_back_merchant = (TextView)findViewById(R.id.tv_back_merchant);
        rg_merchant = (RadioGroup)findViewById(R.id.rg_merchant);
        adapter = new MyPagerAdapter(fm);
        vp_merchant.setOffscreenPageLimit(3);
        vp_merchant.setAdapter(adapter);

    }

    private void alterTitle(int position){
        switch (position){
            case 0:
                tv_title_merchant.setText("活动");
                break;
            case 1:
                tv_title_merchant.setText("主页");
                break;
            case 2:
                tv_title_merchant.setText("积分录入");
                break;
        }
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            return fragments.get(position);
        }



        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
