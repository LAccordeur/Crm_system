package com.example.jjj.crm_system.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jjj.crm_system.R;
import com.example.jjj.crm_system.ui.Base.BaseActivity;
import com.example.jjj.crm_system.utils.ActivityUtil;



public class SelectPhotoActivity extends BaseActivity {

    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;//使用相机拍摄图片
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;//选择手机中的图片
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    public static final String KEY_PHOTO_PATH = "photo_path";//从intent中获取图片路径
    private String picPath;
    private Intent lastIntent;

    private Uri photoUri;


    private RelativeLayout rl;
    private String[] items = {"本地上传","拍照上传"};

    @Override
    protected void init() {

        lastIntent = getIntent();
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_select_photo;
    }

    @Override
    protected void setListener() {
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.finishActivty();
            }
        });

    }

    @Override
    protected void initData() {
        rl = (RelativeLayout) findViewById(R.id.activity_select_photo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("选择方式").setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        pickPhoto();
                        break;
                    case 1:
                        if (ContextCompat.checkSelfPermission(SelectPhotoActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(SelectPhotoActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                        }else {
                            takePhoto();
                            //Toast.makeText(SelectPhotoActivity.this,"拒绝授予权限！",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityUtil.finishActivty();
            }
        });
        builder.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            doPhoto(requestCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void doPhoto(int requestCode, Intent data) {
        if (requestCode==SELECT_PIC_BY_PICK_PHOTO){
            if (data==null){
                Toast.makeText(this,"选择图片文件出错",Toast.LENGTH_SHORT).show();
                return;
            }
            photoUri = data.getData();
            if (photoUri==null){
                Toast.makeText(this,"选择图片文件出错",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String[] pojo = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(photoUri,pojo,null,null,null);
        if(cursor != null )
        {
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            cursor.close();
        }
        //Log.i(TAG, "imagePath = "+picPath);
        if(picPath != null && ( picPath.endsWith(".png") || picPath.endsWith(".PNG") ||picPath.endsWith(".jpg") ||picPath.endsWith(".JPG") ))
        {
            lastIntent.putExtra(KEY_PHOTO_PATH, picPath);
            setResult(Activity.RESULT_OK, lastIntent);
            finish();
        }else{
            Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
        }
    }

    private void pickPhoto(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
    }
    private void takePhoto(){
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();

            photoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
        }else{
            Toast.makeText(this,"内存卡不存在",Toast.LENGTH_SHORT).show();
            ActivityUtil.finishActivty();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        /*if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                choosePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }*/
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
