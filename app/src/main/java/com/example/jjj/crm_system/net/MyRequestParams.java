package com.example.jjj.crm_system.net;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by jjj on 2016/7/26 0026.
 */
public class MyRequestParams extends RequestParams{

    public MyRequestParams() {
        this.addHeader("Content-Type", "application/json");
        this.addHeader("Content-Type-Charset", "utf-8");
        this.addHeader("Accept", "application/json");
    }
}
