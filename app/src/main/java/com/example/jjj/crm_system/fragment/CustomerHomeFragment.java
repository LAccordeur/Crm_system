package com.example.jjj.crm_system.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jjj.crm_system.activity.MerchintInfoActivity;
import com.example.jjj.crm_system.activity.OnsaleInfoActivity;
import com.example.jjj.crm_system.domain.CustomerOnsaleObject;
import com.example.jjj.crm_system.R;

import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshScrollView;
import com.example.jjj.crm_system.utils.ImageLoader;

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
    private List<CustomerOnsaleObject> onsaleList;
    private com.example.jjj.crm_system.ui.view.CircleImageView iv_storepng_customerhome;

    @Override
    protected void initData(View view) {
        tv_storename_customerhome = (TextView)view.findViewById(R.id.tv_storename_coustomerhome);
        tv_place_customerhome = (TextView)view.findViewById(R.id.tv_place_customerhome);
        tv_others_customerhome = (TextView)view.findViewById(R.id.tv_others_customerhome);
        tv_worktime_customerhome = (TextView)view.findViewById(R.id.tv_worktime_customerhome);
        tv_details_customerhome = (TextView)view.findViewById(R.id.tv_details_customerhome);
        lv_onsale_customerhome = (PullToRefreshListView) view.findViewById(R.id.lv_onsale_customerhome);
        iv_storepng_customerhome = (com.example.jjj.crm_system.ui.view.CircleImageView)view.findViewById(R.id.iv_storepng_customerhome);
        ptr_customerhome = (com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshScrollView)view.findViewById(R.id.ptr_customerhome);
        onsaleList = new ArrayList<CustomerOnsaleObject>();
        initOnsaleList();
        initListView();
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
                startActivity(intent);

            }
        });
        ptr_customerhome.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                onsaleList.add(new CustomerOnsaleObject());
            }
        });
        lv_onsale_customerhome.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                onsaleList.add(new CustomerOnsaleObject());
                //adpter = new CustomerHomeAdapter(onsaleList,getActivity().getApplicationContext());
                //lv_onsale_customerhome.setAdapter(adpter);
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
        adpter = new CustomerHomeAdapter(onsaleList,this.getContext());
        lv_onsale_customerhome.setAdapter(adpter);
    }
    private void initOnsaleList(){
        for(int i = 0;i < 5;i++){
            onsaleList.add(i,new CustomerOnsaleObject());
            System.out.println("插入第"+i+"个");
        }
    }


    private class CustomerHomeAdapter extends BaseAdapter {
        private List<CustomerOnsaleObject> list;
        private Context context;
        private ImageLoader imageLoader;
        public CustomerHomeAdapter(List<CustomerOnsaleObject> list,Context context){
            this.list = list;
            this.context = context;
            imageLoader = ImageLoader.getInstance(context);
        }

        //为每个活动设置点击查看详情
        private class onsaleIteminfoClickListener implements View.OnClickListener{

            private CustomerOnsaleObject onsaleObject;
            public onsaleIteminfoClickListener(CustomerOnsaleObject onsaleObject){
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

            tv_onsaleName.setText(list.get(position).getOnsaleName());
            tv_onsaleTime.setText(list.get(position).getOnsaleStartTime()+"-"+list.get(position).getOnsaleEndTime());
            tv_introduction.setText(list.get(position).getOnsaleIntroduction());
            imageLoader.loadImage(list.get(position).getOnsaleImageUrl(),iv_storepng);

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
