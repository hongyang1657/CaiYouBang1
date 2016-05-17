package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.adapter.HyBaseAdapter;
import com.bhz.android.caiyoubang.db.MyDbHelper;
import com.bhz.android.caiyoubang.info.CreateMenuStepInfo;
import com.bhz.android.caiyoubang.view.ScrollListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 创建菜单的页面
 * Created by Administrator on 2016/4/13.
 */
public class CreateMenuActivity extends Activity{
    public static final int TAKE_PHOTO = 11;
    public static final int CROP_PHOTO = 22;
    ScrollListView listView;
    ImageView imageView;
    Button btAddStep;
    Button btSend;
    HyBaseAdapter adapter;
    int addCaipuNumber = 2;
    Uri imageUri;
    Uri imageUri1;
    Uri stepImageUri;
    MyDbHelper dbHelper;
    CreateMenuStepInfo stepInfo;
    List<CreateMenuStepInfo> menuInfoList;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_menu_layout);
        dbHelper = new MyDbHelper(this,"MenuInfo.db",null,1);
        init();
        registerForContextMenu(imageView);//为imageview注册上下文菜单


    }
    private void init() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        listView = (ScrollListView) findViewById(R.id.list_step);
        imageView = (ImageView) findViewById(R.id.image_head);
        btAddStep = (Button) findViewById(R.id.bt_addstep);
        btSend = (Button) findViewById(R.id.bt_send);
        //menuInfoList = new ArrayList<>();
        adapter = new HyBaseAdapter(this,this,menuInfoList,stepImageUri);
        listView.setAdapter(adapter);

    }

    //创建上下文菜单时触发该方法，点击图片上传菜谱的照片
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.add_photo_menu,menu);
    }

    //选择菜单的项，通过拍照上传，或通过相册上传
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case R.id.take_photo:
                //创建一个file储存照片
                File fileImage = new File(Environment.getExternalStorageDirectory(),"headImage.jpg");
                try {
                    if (fileImage.exists()){
                        fileImage.delete();
                }
                    fileImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(fileImage);
                //跳转相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
                break;
            case R.id.from_gallery:
                //跳转相册
                File outputImage = new File(Environment.getExternalStorageDirectory(), "outputImage.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);

                Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent1.setType("image/*");
                intent1.putExtra("crop", true);
                intent1.putExtra("scale", true);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                //startActivityForResult(intent1,CROP_PHOTO);
                startActivity(intent1);
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                    imageView.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                        imageView.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 10:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(stepImageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, stepImageUri);
                    startActivityForResult(intent,20); // 启动裁剪程序
                    adapter.addInfo(stepInfo);
                }
                break;
            case 20:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(stepImageUri));

                        imageView.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }



    //点击事件,添加菜谱步骤，发布等
    public void click(View view){
        switch (view.getId()){
            case R.id.image_head:

                break;
            case R.id.bt_addstep:
                addCaipuNumber++;
                //菜谱步骤数目加一，重新加载adapter

                adapter.addInfo(stepInfo);

                break;
            case R.id.bt_send:
                //getItemContent();

                dbHelper.getWritableDatabase();
                addDataToSQLite();
                Intent intent = new Intent(this,MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.list_step:
                Log.i("tag", "click: -------");
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    public void addDataToSQLite(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //添加数据
        values.put("content","content----");
        values.put("image","imageUri----");
        db.insert("menu",null,values);
    }
}
