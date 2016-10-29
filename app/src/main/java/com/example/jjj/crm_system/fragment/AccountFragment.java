package com.example.jjj.crm_system.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.activity.AddOnsaleActivity;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.ActivityService;
import com.example.jjj.crm_system.service.po.Activity;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.ui.view.CircleImage;
import com.example.jjj.crm_system.ui.view.CircleImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjj on 2016/7/2 0002.
 */

/**
 * 商家优惠活动
 */
public class AccountFragment extends BaseFragment {
    private CircleImageView iv_add;
    private PullToRefreshListView ptr_onsaleList;
    private List<Activity> activityList;
    private MyAdapter adapter;
    @Override
    protected void setListener() {
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),AddOnsaleActivity.class);
                startActivity(intent);
            }
        });


        ptr_onsaleList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            /**
             * onRefresh will be called for both a Pull from start, and Pull from
             * end
             *
             * @param refreshView
             */
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                initListView();
                ptr_onsaleList.onRefreshComplete();
            }
        });
    }

    @Override
    protected void initData(View view) {
        iv_add = (CircleImageView)view.findViewById(R.id.iv_addOnsale_account);
        ptr_onsaleList = (PullToRefreshListView)view.findViewById(R.id.ptr_onsaleList_account);
        activityList = new ArrayList<>();
        initListView();
    }

    @Override
    protected void init() {

    }


    //设置listview
    private void initListView(){
        initList();
        adapter = new MyAdapter(activityList,getContext());
        ptr_onsaleList.setAdapter(adapter);

    }
    //获取活动的list
    private void initList(){
        new NetTask(getContext()){

            /**
             * 加载数据
             *
             * @return
             */
            @Override
            protected JSONObject onLoad() {

                JSONObject jsonObject = new JSONObject();
                try {
                    activityList = ActivityService.getActivityInfo();
                    jsonObject.put("StateCode",1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            }

            /**
             * 请求数据成功后的处理
             *
             * @param jsonObject
             * @throws Exception
             */
            @Override
            protected void onSuccess(JSONObject jsonObject) throws Exception {

            }
        }.execute();
    }




    @Override
    protected int getRootView() {
        return R.layout.fragment_account;
    }

    private class MyAdapter extends BaseAdapter{
        private List<Activity> list;
        private Context context;

        public MyAdapter(List<Activity> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View Myview = LayoutInflater.from(getContext()).inflate(R.layout.item_onsale,null);
            ImageView iv = (ImageView) Myview.findViewById(R.id.iv_png_onsaleitem);
            TextView  tv_name = (TextView) Myview.findViewById(R.id.tv_onsalename_onsaleitem);
            TextView tv_detail = (TextView) Myview.findViewById(R.id.tv_details_onsaleitme);
            TextView tv_time = (TextView) Myview.findViewById(R.id.tv_onsaletime_onsaleitem);

            tv_name.setText(list.get(i).getActivityname());
            tv_detail.setText(list.get(i).getActivitydetail());
            tv_time.setText(list.get(i).getActivitystarttime()+" - - "+list.get(i).getActivitycuttime());


            return Myview;
        }
    }
}
