package com.example.jjj.crm_system.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.activity.GoodsInfoActivity;
import com.example.jjj.crm_system.activity.MerchintInfoActivity;
import com.example.jjj.crm_system.ui.Base.BaseFragment;

/**
 * Created by jjj on 2016/7/2 0002.
 */

/**
 * 商家首页
 */
public class HomeFragment extends BaseFragment{
    private TextView tv_details,tv_acconthome;
    private Button bt_goods;
    @Override
    protected int getRootView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData(View view) {
        tv_acconthome = (TextView)view.findViewById(R.id.tv_acconthome);
        tv_details = (TextView)view.findViewById(R.id.tv_details_home);
        bt_goods = (Button)view.findViewById(R.id.goods_bt);

    }

    @Override
    protected void setListener() {

        bt_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
               // intent.putExtra("top_background","#fa9e26");
                startActivity(intent);
            }
        });

        tv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MerchintInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}
