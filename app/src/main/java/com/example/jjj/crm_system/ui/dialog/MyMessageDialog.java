package com.example.jjj.crm_system.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.utils.UIUtil;


/**
 * Created by Mr.Jadyn on 15/12/23.
 */
public class MyMessageDialog extends Dialog {

    private Context context;
    private TextView tv_message_dialog;
    private Button btn_message_dialog;

    public MyMessageDialog(Context context) {
        super(context, R.style.MyBaseDialog);
        this.context = context;
        initView();
    }


    private void initView() {
        View view = View.inflate(context, R.layout.dialog_message, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtil.dip2px(200), UIUtil.dip2px(110));
        setContentView(view, params);
        tv_message_dialog = (TextView) view.findViewById(R.id.tv_message_dialog);
        btn_message_dialog = (Button) view.findViewById(R.id.btn_message_dialog);
        setCanceledOnTouchOutside(false);
        btn_message_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setText(String msg) {
        tv_message_dialog.setText(msg);
    }
}
