package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.db.DBConfig;
import com.bhz.android.caiyoubang.db.MyDbHelper;

/**
 * Created by Administrator on 2016/4/30 0030.
 */
// 缺少的内容  1.存储用户账号密码的输入库方法
//             2.返回主页面的My的跳转
public class UserRegisterActivity extends Activity {


    MyDbHelper helper;                  //创建操作数据库的对象
    SQLiteDatabase database;

    TextView tvBack;                      //账号密码注册页面的返回
    TextView tvLogin;                     //账号密码注册页面的注册
    EditText etPhoneNumber;              //账号密码注册页面的手机号输入
    EditText etPassWord;                  //账号密码注册页面的密码输入

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_layout);
        initData();
        tvBack.setOnClickListener(click);
        tvLogin.setOnClickListener(click);
    }

    private void initData() {
        tvBack = (TextView) findViewById(R.id.tv_user_register_back);
        tvLogin = (TextView) findViewById(R.id.tv_user_register_login);
        etPhoneNumber= (EditText) findViewById(R.id.et_user_register_phone_number);
        etPassWord= (EditText) findViewById(R.id.et_user_register_password);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_user_register_back:                          // 返回点击事件
                    Intent intentBack = new Intent(UserRegisterActivity.this,UserPhoneRegisterActivity.class);
                    startActivity(intentBack);
                    break;
                case R.id.tv_user_register_login:                         // 注册点击事件
           //         saveData();
                    Intent intentLogin = new Intent(UserRegisterActivity.this,UserLoginPageActivity.class);  // ！！！！！！！！！！！！！！！！需要修改成跳转主页面MY
                    startActivity(intentLogin);
                    break;
            }
        }
    };

    //  从输入框中输入内容到库中
    private void saveData(){
        ContentValues contentValues = new ContentValues();
        String registerUserId =etPhoneNumber.getText().toString();          //从id框获取用户注册的id 并保存到数据库中
        String registerUserPassword = etPassWord.getText().toString();                //从密码框获取用户注册的密码 并保存到数据库中
        contentValues.put(DBConfig.USER_ID,registerUserId);
        contentValues.put(DBConfig.USER_PASSWORD,registerUserPassword);
        database.insert(DBConfig.DB_NAME,null,contentValues);
    }

    //@Override
    //protected void onStart() {
     //   super.onStart();
     //   database=helper.getWritableDatabase();
    //}

    //@Override
    //protected void onStop() {
    //    super.onStop();
     //   database.close();
    //}
}
