package com.example.jjj.crm_system.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jjj.crm_system.domain.CustomerOnsaleObject;
import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.utils.ImageLoader;

import java.util.List;

/**
 * Created by jjj on 2016/7/22 0022.
 */
public class CustomerHomeAdapter extends BaseAdapter{
    private List<CustomerOnsaleObject> list;
    private Context context;
    private ImageLoader imageLoader;
    public CustomerHomeAdapter(List<CustomerOnsaleObject> list,Context context){
        this.list = list;
        this.context = context;
        imageLoader = ImageLoader.getInstance(context);
    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_onsale,null);
        TextView tv_onsaleName = (TextView)view.findViewById(R.id.tv_onsalename_onsaleitem);
        TextView tv_introduction = (TextView)view.findViewById(R.id.tv_introdaction_onsaleitem);
        TextView tv_onsaleTime = (TextView)view.findViewById(R.id.tv_onsaletime_onsaleitem);
        ImageView iv_storepng = (ImageView)view.findViewById(R.id.iv_png_onsaleitem);

        tv_onsaleName.setText(list.get(position).getOnsaleName());
        tv_onsaleTime.setText(list.get(position).getOnsaleTime());
        tv_introduction.setText(list.get(position).getOnsaleIntroduction());
        //imageLoader.loadImage(list.get(position).getOnsaleImageUrl(),iv_storepng);
        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }
}
