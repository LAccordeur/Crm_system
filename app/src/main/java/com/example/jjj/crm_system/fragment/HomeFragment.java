package com.example.jjj.crm_system.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.activity.GoodsInfoActivity;
import com.example.jjj.crm_system.activity.MemberInfoActivity;
import com.example.jjj.crm_system.activity.MerchintInfoActivity;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.ShopownerService;
import com.example.jjj.crm_system.service.po.Shopowner;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshScrollView;

import org.json.JSONObject;

/**
 * Created by jjj on 2016/7/2 0002.
 */

/**
 * 商家首页
 */
public class HomeFragment extends BaseFragment{


    private PullToRefreshScrollView ptr_home;
    private Shopowner shopowner;
    private TextView tv_details,tv_acconthome,tv_storename,tv_address,tv_worktime,tv_phonenum,tv_others;
    private Button bt_goods;
    private TextView tv_yearlySale,tv_daylySale,tv_monthlySale;
    private int DaylySales,MonthlySales,YealySales;

    @Override
    protected int getRootView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {


    }

    @Override
    protected void initData(View view) {
        ptr_home = (PullToRefreshScrollView) view.findViewById(R.id.ptr_home);

        tv_daylySale = (TextView) view.findViewById(R.id.tv_daylySale);
        tv_monthlySale = (TextView) view.findViewById(R.id.tv_monthlySale);
        tv_yearlySale = (TextView) view.findViewById(R.id.tv_yearlySale);

        tv_acconthome = (TextView)view.findViewById(R.id.tv_acconthome);
        tv_details = (TextView)view.findViewById(R.id.tv_details_home);
        bt_goods = (Button)view.findViewById(R.id.goods_bt);

        tv_storename = (TextView) view.findViewById(R.id.tv_storename_coustomerhome);
        tv_address = (TextView) view.findViewById(R.id.tv_place_customerhome);
        tv_worktime = (TextView) view.findViewById(R.id.tv_worktime_customerhome);
        tv_phonenum = (TextView) view.findViewById(R.id.tv_connect_home);
        tv_others = (TextView) view.findViewById(R.id.tv_others_customerhome);

        getShopownerSaleInfo();

    }

    @Override
    protected void setListener() {

        ptr_home.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getShopownerSaleInfo();
                Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                ptr_home.onRefreshComplete();
            }
        });

        bt_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("intent_id",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        tv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MerchintInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("intent_id",1);
                intent.putExtras(bundle);
                intent.putExtra("MerchintInfo",shopowner);
                startActivity(intent);
            }
        });
        tv_acconthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), MemberInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getShopownerSaleInfo(){
        new NetTask(getContext()){

            @Override
            protected JSONObject onLoad() {
                JSONObject jsonObject = new JSONObject();
                try {
                    shopowner = ShopownerService.getShopownerInf("1");
                    DaylySales = ShopownerService.getDailySales();
                    MonthlySales = ShopownerService.getMonthlySales();
                    YealySales = ShopownerService.getYearlySales();
                    jsonObject.put("StateCode",1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) throws Exception {
                setSalesInfo();
            }
        }.execute();
    }
    private void setSalesInfo(){
        tv_daylySale.setText("日销售额："+"  "+DaylySales);
        tv_monthlySale.setText("月销售额："+"  "+MonthlySales);
        tv_yearlySale.setText("年销售额："+"  "+YealySales);

        tv_storename.setText(shopowner.getAccountname());
        tv_others.setText(shopowner.getAccountdetail());
        //tv_phonenum.setText();
        tv_worktime.setText(shopowner.getOpeningtime()+"--"+shopowner.getClosingtime());
        tv_address.setText(shopowner.getAccountaddress());
    }





}
