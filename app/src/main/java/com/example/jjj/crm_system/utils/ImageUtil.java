package com.example.jjj.crm_system.utils;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.ImageView;

import com.example.jjj.crm_system.activity.ImageViewActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by 坤霖 on 2016/10/12.
 */

public class ImageUtil {
    public static Bitmap bitmapforShow = null;


    /**
     * 在网上获取图片加载至UI组件
     *
     * @param
     * @view  加载图片的UI组件
     * @path  图片的路径
     * @return
     * @author 坤霖
     * @time 2016/10/12 18:22
    */
    public static void LoadImage(ImageView view, String path){
        Bitmap bitmap = getNetImage(path);
        view.setImageBitmap(bitmap);
    }



/**
 * 从网上获取一张图片的bitmap对象
 *
 * @param  path 传入的图片地址
 *
 * @return 返回一个bitmap对象
 * @author 坤霖
 * @time 2016/10/12 18:19
*/
    public static Bitmap getNetImage(String path) {
        Bitmap bm = null;
        AsyncTask asyncTask = new AsyncTask<String, Integer, Bitmap>() {

            //后台进程，执行从网络上获取图片的操作
            @Override
            protected Bitmap doInBackground(String... strings) {
                //输入流
                InputStream inputStream = null;

                try {
                    URL url = new URL(strings[0]);//得到URL对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();//得到HttpURLConnection对象
                    inputStream = conn.getInputStream();//打开InputStream
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);//用BitmapFactory将inputStream包装为一个bitmap对象
                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(path);

        try {
            //获取asynctask的返回对象
            bm = (Bitmap) asyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return bm;
    }


/**
 * 从网上下载图片保存至指定路径
 *
 * @param  url 目标图片地址
 *          path 图片保存的地址
 *          bitName 保存的图片名称
 * @return
 * @author 坤霖
 * @time 2016/10/12 18:20
*/
    public static void downloadImage(String url, String path, String bitName) throws IOException {
        Bitmap bitmap = getNetImage(url);
        File file = new File(path);//打开指定文件夹
        //System.out.println("file.exists()->"+file.exists());
        //文件夹不存在则新创建
        if (!file.exists()){
            file.mkdirs();
            //System.out.println("file.exists()->"+file.exists());
        }
        File f = new File(path+bitName+".png");
        f.createNewFile();
        FileOutputStream fout = null;
        fout = new FileOutputStream(f);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fout);
        fout.flush();
        fout.close();

        return;
    }

    public static void showBitmap(String url,Context context){
        Intent intent = new Intent(context,ImageViewActivity.class);
        intent.putExtra("Url",url);
        context.startActivity(intent);
    }

    public static void showBitmap(Bitmap bitmap, Context context){
        System.out.println("ImageUtil-->Bitmap-->"+bitmap);
        Intent intent = new Intent(context, ImageViewActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        /*String path = "./sdcard/MyApp/";
        String bitmapPath = path+ System.currentTimeMillis()+".png";
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
            //System.out.println("file.exists()->"+file.exists());
        }
        File f = new File(bitmapPath);
        try {
            f.createNewFile();
            FileOutputStream fout = null;
            fout = new FileOutputStream(f);

            boolean flag = bitmap.compress(Bitmap.CompressFormat.PNG,100,fout);
            System.out.println("ImageUtil-->flag-->"+flag);
            if (flag=true){
                Intent intent = new Intent(context, ImageViewActivity.class);
                intent.putExtra("bitmapPath",bitmapPath);
                context.startActivity(intent);
            }
            fout.flush();
            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        //Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(),bitmap,null,null));
        //System.out.println("Uri-->"+uri);
        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        /*bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[] bytes = outputStream.toByteArray();

        intent.putExtra("bitmap",bytes);*/
        //Bundle bundle = new Bundle();
        //bundle.putBundle("outputStream",outputStream);
        //intent.putExtra();

        setBitmapforShow(bitmap);
        context.startActivity(intent);

    }


    public static Bitmap getBitmapforShow() {
        return bitmapforShow;
    }

    public static void setBitmapforShow(Bitmap bitmapforShow) {
        ImageUtil.bitmapforShow = bitmapforShow;
    }
}
