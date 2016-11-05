package com.example.jjj.crm_system.activity;

import android.content.Intent;
import android.net.Uri;
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
import com.example.jjj.crm_system.service.ShopownerService;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.ui.dialog.MyProgressDialog;
import com.example.jjj.crm_system.utils.ActivityUtil;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

public class GoodsEditActivity extends BaseActivity {
    private Goods good;

    private TextView tv_cancel;
    private EditText et_price, et_detail;
    private ImageView iv_pic, iv_back;
    private Button bt_uploadpic, bt_ok;

    private Float price;
    private String detail;

    private MyProgressDialog myProgressDialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * 加载UI前的预初始化
     */
    @Override
    protected void init() {
        good = (Goods) getIntent().getSerializableExtra("goods_info");

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
                //调转到选择照片页面
                Intent intent = new Intent(GoodsEditActivity.this,SelectPicActivity.class);
                startActivity(intent);

            }
        });
        //点击保存本次更新信息
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                price = Float.valueOf(et_price.getText().toString());
                detail = et_detail.getText().toString();
                if (price.equals("") || price == null) {
                    Toast.makeText(getBaseContext(), "请输入价格！", Toast.LENGTH_LONG).show();
                    return;
                }
                if (detail == null || detail.equals("")) {
                    Toast.makeText(getBaseContext(), "请添加描述", Toast.LENGTH_LONG).show();
                    return;
                }

                good.setGoodsmoney(price);
                good.setGoodsdetail(detail);

                new NetTask(getBaseContext()) {

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
                        try {
                            GoodsService.upadateGoodsInf(good.getGoodsid().toString(), good);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
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
                        Toast.makeText(getBaseContext(), "操作成功", Toast.LENGTH_LONG).show();
                    }

                    /**
                     * 返回错误时的处理逻辑
                     *
                     * @param errorCode
                     * @param errorStr
                     */
                    @Override
                    protected void onError(int errorCode, String errorStr) {
                        super.onError(errorCode, errorStr);
                    }

                    /**
                     * 请求失败的处理逻辑
                     */
                    @Override
                    protected void onFail() {
                        super.onFail();
                    }

                    /**
                     * 完成后的处理逻辑
                     */
                    @Override
                    protected void onFinish() {
                        super.onFinish();
                        myProgressDialog.dismiss();
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
        tv_cancel = (TextView) findViewById(R.id.tv_cancel_goodedit);
        et_price = (EditText) findViewById(R.id.et_price_goodedit);
        et_detail = (EditText) findViewById(R.id.et_detail_goodedit);
        iv_back = (ImageView) findViewById(R.id.iv_back_goodedit);
        iv_pic = (ImageView) findViewById(R.id.iv_pic_goodedit);
        bt_uploadpic = (Button) findViewById(R.id.bt_uploadpic_goodedit);
        bt_ok = (Button) findViewById(R.id.bt_ok_goodedit);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("GoodsEdit Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
