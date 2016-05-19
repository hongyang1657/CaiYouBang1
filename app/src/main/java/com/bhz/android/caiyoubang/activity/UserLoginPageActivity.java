package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.db.DBConfig;
import com.bhz.android.caiyoubang.db.DBOperator;

/**
 * Created by Administrator on 2016/4/18 0018.
 */
// 登录页面缺少的内容  1. 三方登录的内容
//                     2. 缺少一个忘记密码的页面
//                     3. 缺少对比数据库中账号和密码的部分
public class UserLoginPageActivity extends Activity {

  //  SQLiteDatabase database;
  //  DBOperator operator;
    Button btnLogin;       // 登录页面的用户登录按钮

    EditText etUserName;       // 登录页面的用户名输入框
    EditText etUserPassword;        // 登录页面的用户密码输入框

    TextView tvBack;             // 登录页面的返回按钮
    TextView tvRegister;        // 登录页面的注册按钮
    TextView tvQQ;               // 登录页面的QQ登录按钮
    TextView tvWeiXin;          // 登录页面的微信登录按钮
    TextView tvWeiBo;           // 登录页面的微博登录按钮
    TextView tvForgetPassword;// 登录页面的忘记密码按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_loginpage_layout);
   //     operator = new DBOperator(this);
        initData();
        tvBack.setOnClickListener(click);
        tvRegister.setOnClickListener(click);
        btnLogin.setOnClickListener(click);
    }

    // 初始化所有控件;
    private void initData() {
        tvBack = (TextView) findViewById(R.id.tv_user_login_back);                             // 登录界面的返回按钮
        tvRegister = (TextView) findViewById(R.id.tv_user_login_login);                    // 登录界面的注册按钮
        tvForgetPassword = (TextView) findViewById(R.id.btn_user_login);     // 登录界面的忘记密码按钮
        tvQQ = (TextView) findViewById(R.id.tv_user_QQ_login);
        tvWeiXin = (TextView) findViewById(R.id.tv_user_weixin_login);
        tvWeiBo = (TextView) findViewById(R.id.tv_user_weibo_login);
        btnLogin = (Button) findViewById(R.id.btn_user_login);                                  // 登录界面的登录按钮
        etUserName = (EditText) findViewById(R.id.et_user_key);                                 // 用户id输入框
        etUserPassword = (EditText) findViewById(R.id.et_user_key);                                  // 用户密码输入框

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_user_login_back:                   // 用户的返回事件
                    Intent intent = new Intent(UserLoginPageActivity.this, UserMyActivity.class);   // ！！！！！！！！！！！！！！！！！！！！返回的是主页面的my界面
                    startActivity(intent);
                    break;
                case R.id.tv_user_login_login:                 // 用户的注册事件
                    Intent intentTwo = new Intent(UserLoginPageActivity.this, UserPhoneRegisterActivity.class);
                    startActivity(intentTwo);
                    break;
                case R.id.btn_user_login:                       // 用户的登录事件
                  //  compare();
                    Intent intentThree = new Intent(UserLoginPageActivity.this, UserMyActivity.class); // ！！！！！！！！！！！！！！！！登陆完毕后跳转我的页面查看自己的信息
                    startActivity(intentThree);
                    break;
                case R.id.tv_user_login_forget:
                    Intent intentFour = new Intent(UserLoginPageActivity.this, UserMyActivity.class);  //！！！！！！！！！！！！！！！！！缺少一个忘记密码的页面
                    startActivity(intentFour);
                    break;
            }
        }
    };

    // 比较库中的内容是否和输入的一样。
   /* private void compare() {
        String userId = etUserName.getText().toString();
        String userPassword = etUserPassword.getText().toString();
        if (userId == null || "".equals(userId)) {
            Toast.makeText(UserLoginPageActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();

        } else if (userPassword == null || "".equals(userPassword)) {
            Toast.makeText(UserLoginPageActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();

        } else {
            getDate();
        }
    }

    // 对比数据库中的资料
    private void getDate() {
        String[] str = {DBConfig.USER_ID, DBConfig.USER_PASSWORD};
        Cursor cursor = database.query(DBConfig.DB_NAME, null, DBConfig.USER_ID + "=?and" + DBConfig.USER_PASSWORD + "=?", str, null, null, null);
        int count = cursor.getCount();
        if (count > 0) {
            Toast.makeText(UserLoginPageActivity.this, "成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UserLoginPageActivity.this, "失败", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }*/
}
