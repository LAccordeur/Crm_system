package com.example.jjj.crm_system.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.activity.MemberInfoActivity;
import com.example.jjj.crm_system.activity.SelectGoodsActivity;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.BonusPointService;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.utils.ActivityUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jjj on 2016/7/2 0002.
 */

/**
 * 商家积分录入
 */
public class ScoreFragment extends BaseFragment {
    private List<Goods> allGoods;
    private EditText et_phone,et_money,et_point;
    private Button bt_ok;
    private int phone,money,point;
    private choosedGoodsAdapter adapter;
    private TextView tv_selectGoods,tv_totalmoney;
    private ListView lv_choosedGoods;
    private List<Good_Num> choosedGoodsList;
    private Float totalMoney = Float.valueOf(0);
    private HashMap<String,Float> goodsMap;
    private MyProgressDialog myProgressDialog;

    @Override
    protected int getRootView() {
        return R.layout.fragment_score;
    }

    @Override
    protected void init() {
        goodsMap = new HashMap<>();
        myProgressDialog = new MyProgressDialog(getContext());
    }

    @Override
    protected void initData(View view) {
        allGoods = new ArrayList<>();
        choosedGoodsList = new ArrayList<>();

        et_money = (EditText) view.findViewById(R.id.RecordMoney);
        et_phone = (EditText) view.findViewById(R.id.phonenumber);
        et_point = (EditText) view.findViewById(R.id.BonusPoint);
        bt_ok = (Button) view.findViewById(R.id.button);
        tv_selectGoods = (TextView) view.findViewById(R.id.tv_selectGoods);
        lv_choosedGoods = (ListView) view.findViewById(R.id.lv_goods_score);

        tv_totalmoney = (TextView) view.findViewById(R.id.tv_totalmoney_score);



    }

    @Override
    protected void setListener() {
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_phone.getText()==null||et_phone.getText().equals("")){
                    Toast.makeText(getContext(),"请输入电话号码！",Toast.LENGTH_SHORT).show();
                }
                phone = Integer.parseInt(et_phone.getText().toString().trim());
               // money = Integer.parseInt(et_money.getText().toString().trim());
                //point = Integer.parseInt(et_point.getText().toString().trim());

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
                        boolean flag = false;
                        try {
                             flag = BonusPointService.logCreditAndHistory(phone, Integer.parseInt(totalMoney.toString().substring(0,totalMoney.toString().indexOf("."))),goodsMap);
                             jsonObject.put("StateCode",1);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        return jsonObject;
                    }

                    @Override
                    protected void onSuccess(JSONObject jsonObject) throws Exception {
                        myProgressDialog.dismiss();
                        Toast.makeText(getContext(),"上传成功！",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MemberInfoActivity.class);
                        startActivity(intent);
                        clearData();

                    }
                }.execute();
            }
        });

        tv_selectGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SelectGoodsActivity.class);
                startActivityForResult(intent,200);
            }
        });

    }

    //清空所填数据
    private void clearData(){
        et_point.setText("");
        et_money.setText("");
        et_phone.setText("");
        lv_choosedGoods.setAdapter(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==200){
            Bundle bundle = data.getExtras();
            HashMap<Goods,Float> map = (HashMap<Goods,Float>) bundle.getSerializable("goods");

            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                Goods key = (Goods) entry.getKey();
                Float value = (Float) entry.getValue();
                goodsMap.put(key.getGoodsid().toString(),value);
                choosedGoodsList.add(new Good_Num(key,value));
                totalMoney+=key.getGoodsmoney()*value;
                initGoodsView();

            }
            et_money.setText(totalMoney.toString());
            et_point.setText(totalMoney.toString());
            tv_totalmoney.setText(totalMoney+"");
        }
        if (resultCode==100){
            Toast.makeText(getContext(),"取消添加",Toast.LENGTH_SHORT).show();
        }

    }



    //载入选择的商品列表
    private void initGoodsView(){
        if (!choosedGoodsList.isEmpty()){
            adapter = new choosedGoodsAdapter(choosedGoodsList,getContext());
            lv_choosedGoods.setAdapter(adapter);
        }

    }
    private class choosedGoodsAdapter extends BaseAdapter{
        private List<Good_Num> choosedlist;
        private Context context;

        public choosedGoodsAdapter(List<Good_Num> choosedlist, Context context) {
            this.choosedlist = choosedlist;
            this.context = context;
        }

        @Override
        public int getCount() {
            return choosedlist.size();
        }

        @Override
        public Object getItem(int i) {
            return choosedlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View MyView = LayoutInflater.from(getContext()).inflate(R.layout.item_goods_score,null);
            TextView tv_name = (TextView) MyView.findViewById(R.id.tv_goodsname_item_goods);
            TextView tv_price = (TextView) MyView.findViewById(R.id.tv_price_item_goods);
            TextView tv_num = (TextView) MyView.findViewById(R.id.tv_num_item_goods);

            tv_name.setText(choosedlist.get(i).getGood().getGoodsname());
            tv_price.setText(choosedlist.get(i).getGood().getGoodsmoney().toString());
            tv_num.setText("*"+choosedlist.get(i).getNum());

            return MyView;
        }
    }

    private class Good_Num{
        private Goods good;
        private Float num;
        public Good_Num(Goods good,Float num){
            this.good = good;
            this.num = num;
        }

        public Goods getGood() {
            return good;
        }

        public Float getNum() {
            return num;
        }
    }
}
