package com.example.jjj.crm_system.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.net.NetTask;
import com.example.jjj.crm_system.service.GoodsService;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.utils.ActivityUtil;
import com.example.jjj.crm_system.utils.ImageLoader;

import org.json.JSONObject;

public class GoodsEditActivity extends BaseActivity {
    private Goods good = new Goods();

    private TextView tv_cancel;
    private EditText et_price,et_detail;
    private ImageView iv_pic,iv_back;
    private Button bt_uploadpic,bt_ok;

    private Float price;
    private String detail;
    private String imageUrl;

    private MyProgressDialog myProgressDialog;
    private ImageLoader imageLoader;

    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {

        imageLoader = ImageLoader.getInstance(this);
        good = (Goods) getIntent().getSerializableExtra("goods_info");
        imageUrl = getIntent().getStringExtra("imageUrl");


    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    protected int getRootView() {
        return R.layout.activity_goods_edit;
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
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.finishActivty();
            }
        });
        //点击上传图片
        bt_uploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //点击保存本次更新信息
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                price = Float.valueOf(et_price.getText().toString());
                detail = et_detail.getText().toString();
                if(price.equals("")||price==null){
                    Toast.makeText(getBaseContext(),"请输入价格！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (detail==null||detail.equals("")){
                    Toast.makeText(getBaseContext(),"请添加描述",Toast.LENGTH_LONG).show();
                    return;
                }
                System.out.println("goodinfo-->"+good.getGoodsmoney()+"   "+good.getGoodsdetail());
                good.setGoodsmoney(price);
                good.setGoodsdetail(detail);
                System.out.println("goodinfo-->"+good.getGoodsmoney()+"   "+good.getGoodsdetail());

                new NetTask(baseContext){

                    /**
                     * 异步任务执行前的预处理
                     */
                    @Override
                    protected void onStart() {
                        super.onStart();
                        myProgressDialog.show();
                    }

                    /**
                     * 加载数据
                     *
                     * @return
                     */
                    @Override
                    protected JSONObject onLoad() {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            GoodsService.upadateGoodsInf(good.getGoodsid().toString(),good);
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
                        Toast.makeText(getBaseContext(),"操作成功",Toast.LENGTH_LONG).show();
                        ActivityUtil.finishActivty();
                    }


                }.execute();


            }
        });
    }



    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {
        myProgressDialog = new MyProgressDialog(baseContext);

        tv_cancel = (TextView)findViewById(R.id.tv_cancel_goodedit);
        et_price  = (EditText)findViewById(R.id.et_price_goodedit);
        et_detail = (EditText)findViewById(R.id.et_detail_goodedit);
        iv_back = (ImageView)findViewById(R.id.iv_back_goodedit);
        iv_pic = (ImageView)findViewById(R.id.iv_pic_goodedit);
        bt_uploadpic = (Button)findViewById(R.id.bt_uploadpic_goodedit);
        bt_ok = (Button)findViewById(R.id.bt_ok_goodedit);

        imageLoader.loadImage(imageUrl,iv_pic);
        et_price.setText(good.getGoodsmoney()+"");
        et_detail.setText(good.getGoodsdetail());

    }
}
