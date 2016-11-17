package com.example.jjj.crm_system.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.activity.GoodsInfoActivity;
import com.example.jjj.crm_system.activity.ImageViewActivity;
import com.example.jjj.crm_system.activity.MemberInfoActivity;
import com.example.jjj.crm_system.activity.MerchintInfoActivity;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.ShopownerService;
import com.example.jjj.crm_system.service.po.Shopowner;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshBase;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshScrollView;
import com.example.jjj.crm_system.utils.CacheUtil;
import com.example.jjj.crm_system.utils.ImageUtil;

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
    private ImageView iv_shop;
    private MyProgressDialog myProgressDialog;
    private static boolean isFirstLoad = true;
    private CacheUtil cacheUtil;
    private String ivUrl = "http://img4.imgtn.bdimg.com/it/u=3245270535,3727521197&fm=21&gp=0.jpg";


    @Override
    protected int getRootView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        myProgressDialog = new MyProgressDialog(getContext());


    }

    @Override
    protected void initData(View view) {
        ptr_home = (PullToRefreshScrollView) view.findViewById(R.id.ptr_home);
        cacheUtil = new CacheUtil(getContext());

        tv_daylySale = (TextView) view.findViewById(R.id.tv_daylySale);
        tv_monthlySale = (TextView) view.findViewById(R.id.tv_monthlySale);
        tv_yearlySale = (TextView) view.findViewById(R.id.tv_yearlySale);
        iv_shop = (ImageView) view.findViewById(R.id.iv_storepng_customerhome);

        tv_acconthome = (TextView)view.findViewById(R.id.tv_acconthome);
        tv_details = (TextView)view.findViewById(R.id.tv_details_home);
        bt_goods = (Button)view.findViewById(R.id.goods_bt);

        tv_storename = (TextView) view.findViewById(R.id.tv_storename_coustomerhome);
        tv_address = (TextView) view.findViewById(R.id.tv_place_customerhome);
        tv_worktime = (TextView) view.findViewById(R.id.tv_worktime_customerhome);
        tv_phonenum = (TextView) view.findViewById(R.id.tv_connect_home);
        tv_others = (TextView) view.findViewById(R.id.tv_others_customerhome);

        Bitmap bitmap = ImageUtil.getNetImage(ivUrl);
        iv_shop.setImageBitmap(bitmap);

        loadCache();




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

        iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ImageViewActivity.class);
                intent.putExtra("Url",ivUrl);
                startActivity(intent);
            }
        });

    }

    public void getShopownerSaleInfo(){
        new NetTask(getContext()){
            /**
             * 异步任务执行前的预处理
             */
            @Override
            protected void onStart() {
              // myProgressDialog.show();
                super.onStart();
            }

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

                /*tv_daylySale.setText("日销售额："+"  "+DaylySales);
                tv_monthlySale.setText("月销售额："+"  "+MonthlySales);
                tv_yearlySale.setText("年销售额："+"  "+YealySales);

                tv_storename.setText(shopowner.getAccountname());
                tv_others.setText(shopowner.getAccountdetail());
                //tv_phonenum.setText();
                tv_worktime.setText(shopowner.getOpeningtime()+"--"+shopowner.getClosingtime());
                tv_address.setText(shopowner.getAccountaddress());
                Bitmap bitmap = ImageUtil.getNetImage(ivUrl);
                iv_shop.setImageBitmap(bitmap);
                myProgressDialog.dismiss();*/
                cacheUtil.setShopownerInfo(shopowner);
                cacheUtil.setDaylySales(DaylySales+"");
                cacheUtil.setMonthlySales(MonthlySales+"");
                cacheUtil.setYearlySales(YealySales+"");


                setInfoView(shopowner,DaylySales+"",YealySales+"",MonthlySales+"");
                isFirstLoad = false;
                myProgressDialog.dismiss();


            }
        }.execute();
    }
    private void setInfoView(Shopowner shopowner,String daylySales,String yealySales,String monthlySales){
        tv_daylySale.setText("日销售额："+"  "+daylySales);
        tv_monthlySale.setText("月销售额："+"  "+monthlySales);
        tv_yearlySale.setText("年销售额："+"  "+yealySales);

        tv_storename.setText(shopowner.getAccountname());
        tv_others.setText(shopowner.getAccountdetail());
        //tv_phonenum.setText();
        tv_worktime.setText(shopowner.getOpeningtime()+"--"+shopowner.getClosingtime());
        tv_address.setText(shopowner.getAccountaddress());

    }

    private void loadCache(){
        if ((cacheUtil.getShopownerInfo()!=null)&&(cacheUtil.getDayrlySales()!=null)&&(cacheUtil.getMonthlySales()!=null)&&(cacheUtil.getYearlySales()!=null)){
            shopowner = cacheUtil.getShopownerInfo();
            String DaylySales = cacheUtil.getDayrlySales();
            String MonthlySales = cacheUtil.getMonthlySales();
            String YealySales = cacheUtil.getYearlySales();
            setInfoView(shopowner,DaylySales,YealySales,MonthlySales);
        }

    }





}
