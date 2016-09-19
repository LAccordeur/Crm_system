package com.example.jjj.crm_system.net;

import android.content.Context;


import com.example.jjj.crm_system.ui.dialog.MyMessageDialog;
import com.example.jjj.crm_system.utils.NetWorkUtil;
import com.example.jjj.crm_system.utils.ThreadPoolUtil;
import com.example.jjj.crm_system.utils.UIUtil;

import org.json.JSONObject;

/**
 * Created by Mr.Jadyn on 15/10/15.
 */
public abstract class NetTask {
    private JSONObject jsonObject;
    private Context context;
    private MyMessageDialog myMessageDialog;


    public NetTask(Context context) {
        this.context = context;
        myMessageDialog=new MyMessageDialog(context);
    }


    public void execute() {

        if (NetWorkUtil.getInstance(context).isConnectNet()) {
            //showProgress
            onStart();
            TaskRunnable mTask = new TaskRunnable();
            ThreadPoolUtil.getLongPool().execute(mTask);
        } else {
            onFail();
            onFinish();
        }
    }

    /**
     * 异步任务执行前的预处理
     */
    protected void onStart() {

    }

    /**
     * 加载数据
     *
     * @return
     */
    protected abstract JSONObject onLoad();

    /**
     * 请求数据成功后的处理
     *
     * @param jsonObject
     * @throws Exception
     */
    protected abstract void onSuccess(JSONObject jsonObject) throws Exception;

    /**
     * 返回错误时的处理逻辑
     *
     * @param errorCode
     * @param errorStr
     */
    protected void onError(int errorCode, String errorStr) {

        if(errorStr!=null&&!errorStr.equals("")&&context==UIUtil.getCurrentContext()){
            myMessageDialog.setText(errorStr);
            myMessageDialog.show();
        }
        else{
            if(UIUtil.getCurrentContext()==context)
            myMessageDialog.setText("服务器未知错误");
            myMessageDialog.show();
        }
    }

    /**
     * 请求失败的处理逻辑
     */
    protected void onFail() {
        myMessageDialog.setText("请求失败,请重试");
        if(context==UIUtil.getCurrentContext())
        myMessageDialog.show();
    }

    /**
     * 完成后的处理逻辑
     */
    protected void onFinish() {

    }

    private class TaskRunnable implements Runnable {

        @Override
        public void run() {
            jsonObject = onLoad();
            UITaskRunnable uiTaskRunnable = new UITaskRunnable();
            UIUtil.runInMainThread(uiTaskRunnable);
        }
    }


    private class UITaskRunnable implements Runnable {

        @Override
        public void run() {
            if (jsonObject == null) {
                onFail();
                onFinish();

            }
            try {

                if (jsonObject.getInt("StateCode") == 1||jsonObject.getInt("StateCode")==0) {
                    onSuccess(jsonObject);
                } else {
                    int errorCode = jsonObject.getInt("StateCode");
                    String errorStr = jsonObject.getString("StateMessage");
                    onError(errorCode, errorStr);
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                onFinish();
            }
        }
    }
}
