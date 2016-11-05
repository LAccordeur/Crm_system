package com.example.jjj.crm_system.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.GoodsService;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.utils.ActivityUtil;
import com.example.jjj.crm_system.utils.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SelectGoodsActivity extends BaseActivity {
    private ImageView iv_back;
    private TextView tv_ok;
    private PullToRefreshListView ptr_goods;
    private HashMap<Goods,Float> map;
    private List<Goods> goods;
    private SelectAdapter adapter;
    @Override
    protected void init() {
        goods = new ArrayList<>();
        map = new HashMap<>();
        new AsyncTask<String ,Integer,List<Goods>>(){


            @Override
            protected List<Goods> doInBackground(String... strings) {
                List<Goods> goodses = new ArrayList<Goods>();
                try {
                    goodses = GoodsService.getGoodsInf();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return goodses;
            }

            @Override
            protected void onPostExecute(List<Goods> goodses) {
                goods.addAll(goodses);
                super.onPostExecute(goodses);
            }
        }.execute();
        System.out.println("goods.size()-->"+goods.size());

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_select_goods;
    }

    @Override
    protected void setListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(100,null);
                ActivityUtil.finishActivty();
            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map = adapter.getMap();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("goods",map);
                intent.putExtras(bundle);
                setResult(200,intent);
                ActivityUtil.finishActivty();
            }
        });
        ptr_goods.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                initGoodsList();
                initListView();
            }
        });

    }

    private void initListView(){
        initGoodsList();

    }
    private void initGoodsList(){
        new NetTask(SelectGoodsActivity.this){

            @Override
            protected JSONObject onLoad() {
                JSONObject jsonObject = new JSONObject();
                try {
                    System.out.println("begin goodsInit!");
                    goods = GoodsService.getGoodsInf();
                    jsonObject.put("StateCode",1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) throws Exception {
                adapter = new SelectAdapter(goods,getBaseContext());
                ptr_goods.setAdapter(adapter);
            }
        }.execute();
    }



    @Override
    protected void initData() {
        iv_back = (ImageView) findViewById(R.id.iv_back_selectGoods);
        tv_ok = (TextView) findViewById(R.id.tv_ok_selectgoods);
        ptr_goods = (PullToRefreshListView) findViewById(R.id.ptr_goods_selectGoods);

        initGoodsList();
        initListView();

    }


    private class SelectAdapter extends BaseAdapter{
        private List<Goods> goods;
        private Context context;
        private HashMap<Goods,Float> map;
        private ImageLoader imageLoader;
        //private Float num = Float.valueOf(0);
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

        public HashMap<Goods, Float> getMap() {
            System.out.println("getMap()-->map.size()-->"+map.size());
            System.out.println(map);
            return map;
        }

        public SelectAdapter(List<Goods> goods, Context context) {
            this.goods = goods;
            this.context = context;
            imageLoader = ImageLoader.getInstance(context);
            map = new HashMap<>();
        }



        @Override
        public int getCount() {
            return goods.size();
        }

        @Override
        public Object getItem(int i) {
            return goods.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {


            final Float[] num = {Float.valueOf(0)};

            View MyView = LayoutInflater.from(context).inflate(R.layout.item_select_goods,null);
            ImageView iv_pic = (ImageView) MyView.findViewById(R.id.iv_pic_selectitem);
            TextView tv_name = (TextView) MyView.findViewById(R.id.tv_goodsname_selectitem);
            TextView tv_price = (TextView) MyView.findViewById(R.id.tv_price_selectgoods);
            ImageView iv_remove = (ImageView) MyView.findViewById(R.id.iv_removenum_selectitem);
            ImageView iv_add  = (ImageView) MyView.findViewById(R.id.iv_addnum_selectitem);
            final EditText et_num = (EditText) MyView.findViewById(R.id.et_num_selectitem);


            try {
                num[0] = Float.valueOf(et_num.getText().toString().trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            imageLoader.loadImage(pics[i],iv_pic);
            tv_name.setText(goods.get(i).getGoodsname());
            tv_price.setText(goods.get(i).getGoodsmoney().toString());

            et_num.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    if(Float.valueOf(et_num.getText().toString())!=0){
                        if(map.containsKey(goods.get(i))){
                            map.remove(goods.get(i));
                            map.put(goods.get(i),Float.valueOf(et_num.getText().toString()));
                        }else {
                            map.put(goods.get(i),Float.valueOf(et_num.getText().toString()));
                        }
                    }else{
                        if(map.containsKey(goods.get(i))){
                            map.remove(goods.get(i));
                        }
                    }

                }
            });

            iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    num[0]++;
                    et_num.setText(num[0] +"");
                }
            });
            iv_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    num[0]--;
                    et_num.setText(num[0] +"");
                }
            });

            return MyView;
        }
    }


}

