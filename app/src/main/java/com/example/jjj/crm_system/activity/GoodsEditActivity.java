package com.example.jjj.crm_system.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;

public class GoodsEditActivity extends BaseActivity {
    private Goods good;

    private TextView tv_cancel;
    private EditText et_price,et_detail;
    private ImageView iv_pic,iv_back;
    private Button bt_uploadpic,bt_ok;

    private Float price;
    private String detail;

    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        good = (Goods)getIntent().getSerializableExtra("goods_info");

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



            }
        });
    }

    /**
     * 请求数据，设置UI
     */
    @Override
    protected void initData() {
        tv_cancel = (TextView)findViewById(R.id.tv_cancel_goodedit);
        et_price  = (EditText)findViewById(R.id.et_price_goodedit);
        et_detail = (EditText)findViewById(R.id.et_detail_goodedit);
        iv_back = (ImageView)findViewById(R.id.iv_back_goodedit);
        iv_pic = (ImageView)findViewById(R.id.iv_pic_goodedit);
        bt_uploadpic = (Button)findViewById(R.id.bt_uploadpic_goodedit);
        bt_ok = (Button)findViewById(R.id.bt_ok_goodedit);

    }
}
