package com.example.jjj.crm_system.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseFragment;
import com.example.jjj.crm_system.ui.pulltorefresh.PullToRefreshListView;
import com.example.jjj.crm_system.ui.view.CircleImage;
import com.example.jjj.crm_system.ui.view.CircleImageView;
import com.example.jjj.crm_system.utils.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jjj on 2016/7/11 0011.
 */

/**
 * 客户个人中心
 */
public class CustomerIndividualFragment extends BaseFragment {
    private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;
    private TextView et_name_customerindividual;
    private  TextView et_account_coustomerindividual;
    private CircleImageView iv_portrait_customerindividual;
    private ImageLoader imageLoader;
    private PullToRefreshListView lv;
    private String phonenum;

    @Override
    protected int getRootView() {
        return R.layout.fragment_customer_individual;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {
        imageLoader = ImageLoader.getInstance(this.getContext());
        JSONObject jsonObject = myActivity.getObject();
        if (!(jsonObject.equals("")||jsonObject==null)){
            try {
                phonenum = jsonObject.getString("phonenum");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void initData(View view) {
        et_name_customerindividual = (TextView)view.findViewById(R.id.et_name_coustomerindividual);
        et_account_coustomerindividual = (TextView)view.findViewById(R.id.et_totalaccount_coustomerindividual);
        lv = (PullToRefreshListView) view.findViewById(R.id.lv_accountinfo_coustomerindividual);
        iv_portrait_customerindividual = (CircleImageView) view.findViewById(R.id.iv_portrait_customerindividual);

        if (phonenum!=null||!phonenum.equals("")){
            initCustmorInfo(phonenum);
        }
    }

    private void initCustmorInfo(String phonenum){

    }

}
