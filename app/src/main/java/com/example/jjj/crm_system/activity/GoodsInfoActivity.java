package com.example.jjj.crm_system.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.GoodsService;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.utils.ActivityUtil;
import com.example.jjj.crm_system.utils.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsInfoActivity extends BaseActivity {
    private ImageView iv_back;
    private String top_background;
    private RelativeLayout rl_top;
    private List<Goods> goodsList;
    private TextView tv_add;
    private int intent_id;
    private PullToRefreshListView ptr_goods;
    private GoodsAdapter adpter;


    private MyProgressDialog myProgressDialog;
    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        //top_background = (String)getIntent().getStringExtra("top_background");
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        intent_id = bundle.getInt("intent_id");
        System.out.println("--->-跳转信息--->"+intent_id+"getBaseContext = "+baseContext);




    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_goods_info;
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
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsInfoActivity.this,AddGoodsActivity.class);
                startActivity(intent);
            }
        });

        ptr_goods.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                initListview();
                /*ptr_goods.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptr_goods.onRefreshComplete();
                    }
                },1000);*/


            }
        });
    }

    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {

        myProgressDialog = new MyProgressDialog(baseContext);
        iv_back = (ImageView)findViewById(R.id.iv_back_goodsinfo);
        tv_add = (TextView)findViewById(R.id.iv_add_goodsinfo);
        ptr_goods = (PullToRefreshListView)findViewById(R.id.ptr_goods_goodsinfo);
        goodsList = new ArrayList<Goods>();
        viewManager(intent_id);

        initListview();

    }

    private void initGoodsList(){

        new NetTask(GoodsInfoActivity.this){

            @Override
            protected void onStart() {
                super.onStart();
                myProgressDialog.show();
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) throws Exception {
                myProgressDialog.dismiss();
                adpter = new GoodsAdapter(goodsList,getBaseContext(),intent_id);
                ptr_goods.setAdapter(adpter);
                ptr_goods.onRefreshComplete();
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                myProgressDialog.dismiss();
            }

            @Override
            protected JSONObject onLoad() {
                JSONObject jsonObject = new JSONObject();
                try {
                    goodsList = GoodsService.getGoodsInf();
                    jsonObject.put("StateCode",1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            }
        }.execute();
    }

    private void initListview(){
        initGoodsList();



    }

    private void viewManager(int Intent_id){

        if(Intent_id == 0){
            tv_add.setVisibility(View.INVISIBLE);
        }
    }

    private class GoodsAdapter extends BaseAdapter{
        private List<Goods> goods;
        private Context context;
        private int intent_id;
        private ImageLoader imageLoader;
        //int size = goods.size();
        String[] pics = {
                "http://img5.imgtn.bdimg.com/it/u=3927785294,2227203319&fm=21&gp=0.jpg",
                "http://img3.imgtn.bdimg.com/it/u=33661655,38962774&fm=21&gp=0.jpg",
                "http://img1.imgtn.bdimg.com/it/u=2961982195,2751054664&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=3395948725,3698335081&fm=21&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=1771546721,3299189929&fm=21&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=568074433,3501685007&fm=21&gp=0.jpg",
                "http://img1.imgtn.bdimg.com/it/u=1557064153,1321986084&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=3448216345,3013347829&fm=21&gp=0.jpg",
                "http://img1.imgtn.bdimg.com/it/u=2353894688,934344371&fm=21&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=2686286601,1285312835&fm=21&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=637985416,1384724170&fm=21&gp=0.jpg",
                "http://img3.imgtn.bdimg.com/it/u=1501848775,4221511710&fm=21&gp=0.jpg",

        };


        public GoodsAdapter(List<Goods> goods, Context context,int intent_id) {
            this.goods = goods;
            this.context = context;
            this.intent_id = intent_id;

            imageLoader = ImageLoader.getInstance(context);
        }

        @Override
        public int getCount() {
            return goods.size();
        }

        @Override
        public Object getItem(int positon) {
            return goods.get(positon);
        }

        @Override
        public long getItemId(int positon) {
            return positon;
        }

        private class EditGoodsListener implements View.OnClickListener{
            private Goods good;
            private String imageUrl;

            public EditGoodsListener(Goods good, String imageUrl) {
                this.good = good;
                this.imageUrl = imageUrl;
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsInfoActivity.this,GoodsEditActivity.class);
                intent.putExtra("goods_info",good);
                intent.putExtra("imageUrl",imageUrl);
                startActivity(intent);


            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_goodsinfo,null);
            ImageView iv_pic_goods = (ImageView)view.findViewById(R.id.iv_pic_goodsitem);
            TextView tv_name_goods = (TextView)view.findViewById(R.id.tv_name_goodsitem);
            TextView tv_price_goods = (TextView)view.findViewById(R.id.tv_price_goodsitem);
            TextView tv_detailss_goods = (TextView)view.findViewById(R.id.tv_details_goodsitem);
            ImageView iv_edit_goods = (ImageView)view.findViewById(R.id.iv_goodsedit_goodsitem);
            imageLoader.loadImage(pics[position],iv_pic_goods);

            if(intent_id == 0) iv_edit_goods.setVisibility(View.INVISIBLE);
            tv_name_goods.setText(goods.get(position).getGoodsname());
            tv_price_goods.setText(goods.get(position).getGoodsmoney()+"");
            tv_detailss_goods.setText(goods.get(position).getGoodsdetail());
            iv_edit_goods.setOnClickListener(new EditGoodsListener(goods.get(position),pics[position]));
            view.setOnClickListener(new EditGoodsListener(goods.get(position),pics[position]));

            return view;
        }
    }
}
