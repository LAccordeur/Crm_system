package com.example.jjj.crm_system.activity;

import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;
import com.example.jjj.crm_system.utils.CacheUtil;
import com.example.jjj.crm_system.utils.ImageUtil;
import com.lidroid.xutils.BitmapUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ImageViewActivity extends BaseActivity {
    private FrameLayout fl;
    private ImageView iv,iv_back;
    private BitmapUtils bitmapUtils;
    private TextView tv;
    private String imageUrl;
    private CacheUtil cacheUtil;
    private Bitmap bitmap;


    @Override
    protected void init() {

        imageUrl = getIntent().getStringExtra("Url");
       // cacheUtil = new CacheUtil(this);

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_image_view;
    }

    @Override
    protected void setListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.finishActivty();
            }
        });
        fl.setOnTouchListener(new View.OnTouchListener() {
            float CurrentDistance;
            float LastDistance = -1;
            float downX,downY;
            long downTime;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = motionEvent.getX();
                        downY = motionEvent.getY();
                        downTime = System.currentTimeMillis();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (motionEvent.getPointerCount() >= 2) {
                            float offsetX = motionEvent.getX(0) - motionEvent.getX(1);
                            float offsetY = motionEvent.getY(0) - motionEvent.getY(1);
                            CurrentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);

                            if (LastDistance < 0) {
                                LastDistance = CurrentDistance;
                            } else {
                                if (CurrentDistance - LastDistance > 5) {
                                    System.out.println("执行放大操作 .");
                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                    lp.width = (int) (1.1f * iv.getWidth());
                                    lp.height = (int) (1.1f * iv.getHeight());
                                    iv.setLayoutParams(lp);
                                    LastDistance = CurrentDistance;
                                } else if (LastDistance - CurrentDistance > 5) {
                                    System.out.println("执行收缩操作。");
                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                    lp.width = (int) (0.9f * iv.getWidth());
                                    lp.height = (int) (0.9f * iv.getHeight());
                                    if (lp.height < 20) {
                                        lp.height = 20;
                                    }
                                    if (lp.width < 20) {
                                        lp.width = 20;
                                    }

                                    iv.setLayoutParams(lp);
                                    LastDistance = CurrentDistance;
                                }
                            }

                        } else if (motionEvent.getPointerCount() == 1) {

                            float moveX = motionEvent.getX() - downX;
                            float moveY = motionEvent.getY() - downY;
                            float currentX = iv.getX();
                            float currentY = iv.getY();
                            //System.out.println("moveX->"+moveX+"  moveY->"+moveY);
                            iv.setX(currentX + moveX);
                            iv.setY(currentY + moveY);
                            downX = motionEvent.getX();
                            downY = motionEvent.getY();
                        }

                        break;

                }
                return true;
            }
        });

    }

    @Override
    protected void initData() {


        fl = (FrameLayout) findViewById(R.id.activity_image_view);
        iv = (ImageView) findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.tv_imagename);

        if (imageUrl!=null){
            ImageUtil.LoadImage(iv,imageUrl);
        }
        //iv.setImageBitmap(bitmap);


        tv.setText("查看图片");
        iv_back = (ImageView) findViewById(R.id.iv_back);
    }

}
