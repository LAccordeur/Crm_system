package com.example.jjj.crm_system.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.activity.MerchintInfoActivity;
import com.example.jjj.crm_system.activity.OnsaleInfoActivity;
import com.example.jjj.crm_system.domain.CustomerOnsaleObject;
import com.example.jjj.crm_system.R;

import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.ActivityService;
import com.example.jjj.crm_system.service.CustomerService;
import com.example.jjj.crm_system.service.ShopownerService;
import com.example.jjj.crm_system.service.po.Activity;
import com.example.jjj.crm_system.service.po.Shopowner;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshScrollView;
import com.example.jjj.crm_system.utils.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjj on 2016/7/11 0011.
 */

/**
 * 客户首页
 */
public class CustomerHomeFragment extends BaseFragment {
    private TextView tv_storename_customerhome;
    private TextView tv_place_customerhome;
    private TextView tv_worktime_customerhome;
    private TextView tv_others_customerhome;
    private PullToRefreshScrollView ptr_customerhome;
    private TextView tv_details_customerhome;
    private PullToRefreshListView lv_onsale_customerhome;
    private CustomerHomeAdapter adpter;
    private List<Activity> onsaleList;
    private com.example.jjj.crm_system.ui.view.CircleImageView iv_storepng_customerhome;
    private MyProgressDialog myProgressDialog;
    private Shopowner shopownerInfo;

    @Override
    protected void initData(View view) {
        onsaleList = new ArrayList<Activity>();
        myProgressDialog = new MyProgressDialog(getContext());
        initOnsaleList();
        tv_storename_customerhome = (TextView)view.findViewById(R.id.tv_storename_coustomerhome);
        tv_place_customerhome = (TextView)view.findViewById(R.id.tv_place_customerhome);
        tv_others_customerhome = (TextView)view.findViewById(R.id.tv_others_customerhome);
        tv_worktime_customerhome = (TextView)view.findViewById(R.id.tv_worktime_customerhome);
        tv_details_customerhome = (TextView)view.findViewById(R.id.tv_details_customerhome);
        lv_onsale_customerhome = (PullToRefreshListView) view.findViewById(R.id.lv_onsale_customerhome);
        iv_storepng_customerhome = (com.example.jjj.crm_system.ui.view.CircleImageView)view.findViewById(R.id.iv_storepng_customerhome);
        ptr_customerhome = (com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshScrollView)view.findViewById(R.id.ptr_customerhome);
        ptr_customerhome.setPullToRefreshOverScrollEnabled(false);


        initListView();
        tv_storename_customerhome.setText(shopownerInfo.getAccountname());
        tv_others_customerhome.setText(shopownerInfo.getAccountdetail());
        tv_worktime_customerhome.setText(shopownerInfo.getOpeningtime()+"--"+shopownerInfo.getClosingtime());
        tv_place_customerhome.setText(shopownerInfo.getAccountaddress());
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {
        tv_details_customerhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MerchintInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("intent_id",0);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        ptr_customerhome.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                onsaleList.add(new Activity());
            }
        });
        lv_onsale_customerhome.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                initListView();
                lv_onsale_customerhome.onRefreshComplete();


            }

        });

    }

    @Override
    protected int getRootView() {
        return R.layout.fragment_customer_home;
    }



    private void initListView(){
        initOnsaleList();
        adpter = new CustomerHomeAdapter(onsaleList,this.getContext());
        lv_onsale_customerhome.setAdapter(adpter);
    }
    private void initOnsaleList(){
        new NetTask(getContext()){

            @Override
            protected void onStart() {
                myProgressDialog.show();
                super.onStart();
            }

            @Override
            protected JSONObject onLoad() {
                JSONObject jsonObject = new JSONObject();

                try {
                    onsaleList = ActivityService.getActivityInfo();
                    shopownerInfo = ShopownerService.getShopownerInf("1");
                    jsonObject.put("StateCode",1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return jsonObject;
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) throws Exception {
                 myProgressDialog.dismiss();
            }
        }.execute();
    }


    private class CustomerHomeAdapter extends BaseAdapter {
        private List<Activity> list;
        private Context context;
        private ImageLoader imageLoader;
        public CustomerHomeAdapter(List<Activity> list,Context context){
            this.list = list;
            this.context = context;
            imageLoader = ImageLoader.getInstance(context);
        }

        //为每个活动设置点击查看详情
        private class onsaleIteminfoClickListener implements View.OnClickListener{

            private Activity onsaleObject;
            public onsaleIteminfoClickListener(Activity onsaleObject){
                this.onsaleObject = onsaleObject;
            }
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OnsaleInfoActivity.class);
                intent.putExtra("onsale_info",onsaleObject);
                startActivity(intent);

            }
        }



        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_onsale,null);
            TextView tv_onsaleName = (TextView)view.findViewById(R.id.tv_onsalename_onsaleitem);
            TextView tv_introduction = (TextView)view.findViewById(R.id.tv_introdaction_onsaleitem);
            TextView tv_onsaleTime = (TextView)view.findViewById(R.id.tv_onsaletime_onsaleitem);
            ImageView iv_storepng = (ImageView)view.findViewById(R.id.iv_png_onsaleitem);
            TextView tv_details = (TextView)view.findViewById(R.id.tv_details_onsaleitme);

            tv_onsaleName.setText(list.get(position).getActivityname());
            tv_onsaleTime.setText(list.get(position).getActivitystarttime()+"-"+list.get(position).getActivitycuttime());
            tv_introduction.setText(list.get(position).getActivitydetail());
            imageLoader.loadImage(list.get(position).getActivityImageUrl(),iv_storepng);

            tv_details.setOnClickListener(new onsaleIteminfoClickListener(list.get(position)));

            return view;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return this.list.get(position);
        }
    }

}
