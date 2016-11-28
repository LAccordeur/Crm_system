package com.example.jjj.crm_system.utils;

import android.os.CountDownTimer;
import android.widget.Button;

import com.example.jjj.crm_system.R;

/**
 * Created by 坤霖 on 2016/11/23.
 */
public class CountDownTimerUtils extends CountDownTimer {
    private Button button;

    public CountDownTimerUtils(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long l) {

        button.setClickable(false);
        button.setText(l/1000+"s后可重新发送");
        button.setBackgroundResource(R.drawable.bg_identify_press);

    }

    @Override
    public void onFinish() {

        button.setText("重新获取");
        button.setClickable(true);
        button.setBackgroundResource(R.drawable.bg_identify_normal);
    }
}
