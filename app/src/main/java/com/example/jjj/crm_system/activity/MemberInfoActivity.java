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
import android.widget.ListView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.service.BonusPointService;
import com.example.jjj.crm_system.service.CustomerService;
import com.example.jjj.crm_system.service.po.Customer;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class MemberInfoActivity extends BaseActivity{
    private List<Customer> memberlist;
    private ImageView iv_back;
    private TextView tv_addmember,tv_totalnum;
    private memberAdapter adapter;
    private ListView lv_member;

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

    }

    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {
        iv_back = (ImageView)findViewById(R.id.iv_back_memberinfo);
        tv_addmember = (TextView)findViewById(R.id.tv_add_memberinfo);
        tv_totalnum = (TextView)findViewById(R.id.tv_membernum_memberinfo);
        lv_member = (ListView)findViewById(R.id.lv_memberlist_memberinfo);


        memberlist = new ArrayList<>();

        tv_totalnum.setText(memberlist.size()+"");
        initListview();


    }

    private void initListview(){
        initMemberlist();
        adapter = new memberAdapter(memberlist,getBaseContext());
        lv_member.setAdapter(adapter);

    }

    private void initMemberlist(){
        try {
            memberlist = BonusPointService.getCustomersCreditByOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            return memberlist.get(position).getCustomid();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_memberlist,null);
            TextView tv_id = (TextView)view.findViewById(R.id.tv_id_memberitem);
            TextView tv_name = (TextView)view.findViewById(R.id.tv_name_memberitem);
            TextView tv_phonenum = (TextView)view.findViewById(R.id.tv_phonenum_memberitem);
            TextView tv_account = (TextView)view.findViewById(R.id.tv_account_memberitem);

            tv_id.setText(memberlist.get(position).getCustomid());
            tv_name.setText(memberlist.get(position).getCustomname());
            tv_phonenum.setText(memberlist.get(position).getTelephonenumber());
            tv_account.setText(memberlist.get(position).getBonuspoint());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MemberInfoActivity.this,MemberDetailActivity.class);
                    intent.putExtra("member_info",memberlist.get(position));
                    startActivity(intent);
                }
            });

            return view;
        }
    }
}
