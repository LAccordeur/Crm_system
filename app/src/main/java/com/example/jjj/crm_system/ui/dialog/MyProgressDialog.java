package com.example.jjj.crm_system.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.utils.UIUtil;

/**
 * Created by Mr.Jadyn on 15/12/20.
 */
public class MyProgressDialog extends Dialog {
    private Context context;

    public MyProgressDialog(Context context) {
        super(context,R.style.MyProgressDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_myprogress, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtil.dip2px(100),UIUtil.dip2px(100));
        setContentView(view,params);
        setCanceledOnTouchOutside(false);
    }

}
