package com.example.jjj.crm_system.fragment;

import android.content.Intent;
import android.view.View;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.activity.AddOnsaleActivity;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.view.CircleImage;
import com.example.jjj.crm_system.ui.view.CircleImageView;

/**
 * Created by jjj on 2016/7/2 0002.
 */

/**
 * 商家优惠活动
 */
public class AccountFragment extends BaseFragment {
    private CircleImageView iv_add;
    @Override
    protected void setListener() {
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),AddOnsaleActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initData(View view) {
        iv_add = (CircleImageView)view.findViewById(R.id.iv_addOnsale_account);

    }

    @Override
    protected void init() {

    }

    @Override
    protected int getRootView() {
        return R.layout.fragment_account;
    }
}
