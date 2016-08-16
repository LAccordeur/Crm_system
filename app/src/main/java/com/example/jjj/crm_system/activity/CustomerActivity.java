package com.example.jjj.crm_system.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.fragment.CustomerHomeFragment;
import com.example.jjj.crm_system.fragment.CustomerIndividualFragment;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends BaseActivity {
    private RadioButton rb_home_customer,rb_individual_customer;
    private MyViewPager vp_customer;
    private RadioGroup rg_customer;
    private TextView tv_back_customer;
    private TextView tv_title_customer;
    private List<BaseFragment> fragments;
    private FragmentManager fm;
    private MyPagerAdapter adapter;

    @Override
    protected int getRootView() {
        return R.layout.activity_customer;
    }

    @Override
    protected void init() {
        fragments = new ArrayList<BaseFragment>();
        fragments.add(0,new CustomerHomeFragment());
        fragments.add(1,new CustomerIndividualFragment());
        fm = getSupportFragmentManager();


    }

    @Override
    protected void initData() {
        rb_home_customer = (RadioButton)findViewById(R.id.rb_home_customer);
        rb_individual_customer = (RadioButton)findViewById(R.id.rb_individual_customer);
        vp_customer = (MyViewPager)findViewById(R.id.vp_customer);
        rg_customer = (RadioGroup)findViewById(R.id.rg_customer);
        tv_back_customer = (TextView)findViewById(R.id.tv_back_customer);
        tv_title_customer = (TextView)findViewById(R.id.tv_title_customer);
        adapter = new MyPagerAdapter(fm);
        vp_customer.setOffscreenPageLimit(2);
        vp_customer.setAdapter(adapter);

    }

    @Override
    protected void setListener() {
        vp_customer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (vp_customer.getCurrentItem()){
                    case 0:
                        rb_home_customer.setChecked(true);
                        break;
                    case 1:
                        rb_individual_customer.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                alterTitle(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg_customer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position = -1;
                switch (checkedId) {
                    case R.id.rb_home_customer:
                        position = 0;
                        break;
                    case R.id.rb_individual_customer:
                        position = 1;
                        break;
                }
                vp_customer.setCurrentItem(position, false);
            }
        });
        tv_back_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void alterTitle(int position){
        switch (position){
            case 0:
                tv_title_customer.setText("首页");
                break;
            case 1:
                tv_title_customer.setText("个人中心");
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
