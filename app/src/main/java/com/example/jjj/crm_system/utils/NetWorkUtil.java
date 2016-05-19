package com.example.jjj.crm_system.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jjj on 2016/5/19 0019.
 */
public class NetWorkUtil {
    private static NetWorkUtil instance;
    private ConnectivityManager manager;
    private NetworkInfo networkInfo;
    private Context context;

    private NetWorkUtil(Context context) {
        this.context = context;
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    }

    public static NetWorkUtil getInstance(Context context) {
        if (instance == null) {
            instance = new NetWorkUtil(context);
        }
        return instance;
    }

    //检测网络是否可用
    public boolean isConnectNet() {
        networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    //检测是否为移动数据网络
    public boolean isWIFI() {
        NetworkInfo.State state = manager.getNetworkInfo(manager.TYPE_MOBILE).getState();
        if (NetworkInfo.State.CONNECTED == state) {
            return true;
        } else {
            return false;
        }
    }

    //检测是否为WIFI
    public boolean isGPRS() {
        NetworkInfo.State state = manager.getNetworkInfo(manager.TYPE_WIFI).getState();
        if (NetworkInfo.State.CONNECTED == state) {
            return true;
        } else {
            return false;
        }
    }
}
