package com.example.jjj.crm_system.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.activity.AddOnsaleActivity;
import com.example.jjj.crm_system.activity.OnsaleInfoActivity;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.ActivityService;
import com.example.jjj.crm_system.service.po.Activity;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.ui.view.CircleImage;
import com.example.jjj.crm_system.ui.view.CircleImageView;
import com.example.jjj.crm_system.utils.ImageLoader;
import com.mob.commons.SMSSDK;

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
    private ImageButton iv_add;
    private PullToRefreshListView ptr_onsaleList;
    private List<Activity> activityList;
    private MyAdapter adapter;
    private MyProgressDialog myProgressDialog;

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
                Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void initData(View view) {
        iv_add = (ImageButton)view.findViewById(R.id.iv_addOnsale_account);
        ptr_onsaleList = (PullToRefreshListView)view.findViewById(R.id.ptr_onsaleList_account);
        activityList = new ArrayList<>();
        initListView();
    }

    @Override
    protected void init() {
        myProgressDialog = new MyProgressDialog(getContext());

    }


    //设置listview
    private void initListView(){
        initList();



    }
    //获取活动的list
    private void initList(){
        new NetTask(getContext()){

            /**
             * 异步任务执行前的预处理
             */
            @Override
            protected void onStart() {
                myProgressDialog.show();
                super.onStart();
            }

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
                adapter = new MyAdapter(activityList,getContext());
                ptr_onsaleList.setAdapter(adapter);
                myProgressDialog.dismiss();
                ptr_onsaleList.onRefreshComplete();
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
        private ImageLoader imageLoader;
        private String[] urls = {
                "http://img3.imgtn.bdimg.com/it/u=1155943206,1987444143&fm=21&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=1781372704,1641418402&fm=21&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=3864008374,840659723&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=105076888,1508232468&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=120685712,350781314&fm=21&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=3476470103,3834397702&fm=21&gp=0.jpg",
                "http://img1.imgtn.bdimg.com/it/u=1079672554,2664045843&fm=21&gp=0.jpg",
        };

        public MyAdapter(List<Activity> list, Context context) {
            this.list = list;
            this.context = context;
            imageLoader = ImageLoader.getInstance(context);
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View Myview = LayoutInflater.from(getContext()).inflate(R.layout.item_onsale,null);
            ImageView iv = (ImageView) Myview.findViewById(R.id.iv_png_onsaleitem);
            TextView  tv_name = (TextView) Myview.findViewById(R.id.tv_onsalename_onsaleitem);
            TextView tv_detail = (TextView) Myview.findViewById(R.id.tv_details_onsaleitme);
            TextView tv_intro = (TextView) Myview.findViewById(R.id.tv_introdaction_onsaleitem);
            TextView tv_time = (TextView) Myview.findViewById(R.id.tv_onsaletime_onsaleitem);

            imageLoader.loadImage(urls[i],iv);
            tv_name.setText(list.get(i).getActivityname());
            tv_intro.setText(list.get(i).getActivitydetail());
            tv_time.setText(list.get(i).getActivitystarttime()+" - - "+list.get(i).getActivitycuttime());


            final Activity onsaleObject = list.get(i);

            Myview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OnsaleInfoActivity.class);
                    intent.putExtra("onsale_info",onsaleObject);
                    intent.putExtra("imageUrl",urls[i]);
                    startActivity(intent);
                }
            });

            tv_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("tv_detail Clicked!");
                    Intent intent = new Intent(context, OnsaleInfoActivity.class);
                    intent.putExtra("onsale_info",onsaleObject);
                    intent.putExtra("imageUrl",urls[i]);
                    startActivity(intent);
                }
            });

            return Myview;
        }
    }
}
