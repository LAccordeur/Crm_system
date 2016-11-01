package com.example.jjj.crm_system.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.BonusPointService;
import com.example.jjj.crm_system.service.po.Customer;
import com.example.jjj.crm_system.service.po.Purchasehistory;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MemberDetailActivity extends BaseActivity {
    private Customer customer;
    private ImageView iv_back,iv_pic;
    private TextView tv_name,tv_phone,tv_details,tv_totalaccount,tv_totalmoney;
    private ListView lv_consumedetail;
    private List<Purchasehistory> list;
    private PurchaseAdapter adapter;
    private TextView tv_refresh;
    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        customer = (Customer)getIntent().getSerializableExtra("member_info");
        list = new ArrayList<>();
        initPurchaseList();

    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_member_detail;
    }

    /**
     * 设置监听器
     */
    @Override
    protected void setListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.finishActivty();
            }
        });
        tv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPurchaseList();
            }
        });

    }
    private void initPurchaseList(){
        new NetTask(MemberDetailActivity.this){

            @Override
            protected JSONObject onLoad() {
                JSONObject jsonObject = new JSONObject();
                try {
                    list = BonusPointService.getCustomerHistory(customer.getCustomid());
                    jsonObject.put("StateCode",1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) throws Exception {
                adapter = new PurchaseAdapter(getBaseContext(),list);

            }
        }.execute();
    }

    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {
        iv_back = (ImageView)findViewById(R.id.iv_back_memberdetail);
        iv_pic = (ImageView)findViewById(R.id.iv_potrait_memberdetail);
        tv_refresh = (TextView) findViewById(R.id.tv_refresh_memberdetail);

        tv_name = (TextView)findViewById(R.id.tv_name_memberdetail);
        tv_phone = (TextView)findViewById(R.id.tv_phonenum_memberdetail);
        tv_details = (TextView)findViewById(R.id.tv_details_memberdetail);
        tv_totalaccount = (TextView)findViewById(R.id.tv_totalaccount_memberdetail);
        tv_totalmoney = (TextView)findViewById(R.id.tv_totalmoney_memberdetail);

        lv_consumedetail = (ListView)findViewById(R.id.lv_consumedetail_memberdeail);
        initPurchaseList();
        lv_consumedetail.setAdapter(adapter);
        fixListViewHeight(lv_consumedetail);

        tv_name.setText(customer.getCustomname());
        tv_phone.setText(customer.getTelephonenumber());
        tv_totalaccount.setText("总积分："+customer.getBonuspoint());

    }

    private void fixListViewHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        int totalHeight = 0;
        if(adapter==null){
            return;
        }
        for(int index = 0,len = adapter.getCount();index<len;index++){
            View listViewItem = adapter.getView(index,null,listView);
            listViewItem.measure(0,0);
            totalHeight += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight()*(adapter.getCount()-1));
        listView.setLayoutParams(params);
    }

    private class PurchaseAdapter extends BaseAdapter{
        private Context context;
        private List<Purchasehistory> list;



        public PurchaseAdapter(Context context, List<Purchasehistory> list) {
            this.context = context;
            this.list = list;
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
            View MyView = LayoutInflater.from(context).inflate(R.layout.item_purchase,null);
            TextView tv_money = (TextView) MyView.findViewById(R.id.tv_money_purchaseitem);
            TextView tv_point = (TextView) MyView.findViewById(R.id.tv_point_purchaseitem);
            TextView tv_time = (TextView) MyView.findViewById(R.id.tv_time_purchaseitem);
            tv_money.setText(list.get(i).getRecordmoney()+"");
            tv_point.setText(list.get(i).getRemark()+"");
            tv_time.setText(list.get(i).getRecordtime()+"");

            return MyView;
        }
    }
}
