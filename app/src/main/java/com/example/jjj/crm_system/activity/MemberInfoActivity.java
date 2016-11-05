package com.example.jjj.crm_system.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.BonusPointService;
import com.example.jjj.crm_system.service.CustomerService;
import com.example.jjj.crm_system.service.po.Customer;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshScrollView;
import com.example.jjj.crm_system.utils.ActivityUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MemberInfoActivity extends BaseActivity{
    private List<Customer> memberlist;
    private ImageView iv_back;
    private TextView tv_addmember,tv_totalnum;
    private memberAdapter adapter;
    private ListView lv_member;
    private PullToRefreshScrollView ptr_member;
    private MyProgressDialog myProgressDialog;
    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {


    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_member_info;
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
        tv_addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberInfoActivity.this,AddMemberActivity.class);
                startActivity(intent);
            }
        });
        ptr_member.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                initMemberlist();
                ptr_member.onRefreshComplete();
            }
        });

    }

    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {

        myProgressDialog = new MyProgressDialog(baseContext);

        iv_back = (ImageView)findViewById(R.id.iv_back_memberinfo);
        tv_addmember = (TextView)findViewById(R.id.tv_add_memberinfo);
        tv_totalnum = (TextView)findViewById(R.id.tv_membernum_memberinfo);
        lv_member = (ListView)findViewById(R.id.lv_memberlist_memberinfo);
        ptr_member = (PullToRefreshScrollView) findViewById(R.id.lv_ptr_memberinfo);

        memberlist = new ArrayList<>();

        //initMemberlist();
        //tv_totalnum.setText(memberlist.size()+"");
        initListview();
    }

    private void initListview(){
        initMemberlist();


    }

    private void initMemberlist(){

        new NetTask(baseContext){

            @Override
            protected JSONObject onLoad() {
                JSONObject jsonObject = new JSONObject();
                try {
                    memberlist = BonusPointService.getCustomersCreditByOrder();
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
                myProgressDialog.dismiss();
                //System.out.println(memberlist.toString());
                adapter = new memberAdapter(memberlist,getBaseContext());
                lv_member.setAdapter(adapter);
                fixListViewHeight(lv_member);
                int size = memberlist.size();
                tv_totalnum.setText(size+"");
            }

        }.execute();
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
    private class memberAdapter extends BaseAdapter{

        private List<Customer> memberlist;
        private Context context;

        public memberAdapter(List<Customer> memberlist, Context context) {
            this.memberlist = memberlist;
            this.context = context;
        }

        @Override
        public int getCount() {
            return memberlist.size();
        }

        @Override
        public Object getItem(int position) {
            return memberlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_memberlist,null);
            TextView tv_id = (TextView)view.findViewById(R.id.tv_id_memberitem);
            TextView tv_name = (TextView)view.findViewById(R.id.tv_name_memberitem);
            TextView tv_phonenum = (TextView)view.findViewById(R.id.tv_phonenum_memberitem);
            TextView tv_account = (TextView)view.findViewById(R.id.tv_account_memberitem);

            tv_id.setText((position+1)+"");
            tv_name.setText(memberlist.get(position).getCustomname());
            tv_phonenum.setText(memberlist.get(position).getTelephonenumber());
            tv_account.setText(memberlist.get(position).getBonuspoint()+"");
            final Customer customer = memberlist.get(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MemberInfoActivity.this,MemberDetailActivity.class);
                    intent.putExtra("member_info",customer);
                    startActivity(intent);
                }
            });

            return view;
        }
    }
}
