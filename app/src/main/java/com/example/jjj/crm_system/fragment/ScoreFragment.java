package com.example.jjj.crm_system.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.ui.Base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjj on 2016/7/2 0002.
 */

/**
 * 商家积分录入
 */
public class ScoreFragment extends BaseFragment {
    private List<Goods> allGoods;
    private List<Goods> choosedGoods;

    @Override
    protected int getRootView() {
        return R.layout.fragment_score;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData(View view) {
        allGoods = new ArrayList<>();
        choosedGoods = new ArrayList<>();


    }

    @Override
    protected void setListener() {

    }

    //添加消费商品
    public void selectGoods(View v){


        final List<Goods> selectedGoods = new ArrayList<>();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("添加商品");
        final String[] items = null;
        for(int i = 0;i<allGoods.size();i++){
            items[i] = allGoods.get(i).getGoodsname().toString();
        }

        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                if (isChecked){
                    Goods good = findByName(items[i]);
                    selectedGoods.add(good);
                }else {
                    Goods good = findByName(items[i]);
                    if (selectedGoods.contains(good)) {
                        selectedGoods.remove(good);
                    }
                }
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

               choosedGoods.addAll(selectedGoods);
                initGoodsView();
                dialogInterface.dismiss();

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });



    }
    //根据商品名称获得商品对象
    private Goods findByName(String goodName){

        for (int i = 0;i<allGoods.size();i++){
            if (allGoods.get(i).getGoodsname().equals(goodName)){
                return allGoods.get(i);
            }
        }

        return null;
    }

    //载入选择的商品列表
    private void initGoodsView(){


    }
}
