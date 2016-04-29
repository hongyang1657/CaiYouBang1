package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bhz.android.caiyoubang.R;

/**
 * Created by Administrator on 2016/4/18 0018.
 */
public class UserLoginPageActivity extends Activity {

    Button btnLogin;       // 登录页面的用户登录按钮

    EditText etUserName;       // 登录页面的用户名输入框
    EditText etUserKey;        // 登录页面的用户密码输入框

    TextView tvBack;             // 登录页面的返回按钮
    TextView tvRegister;        // 登录页面的注册按钮
    TextView tvQQ;               // 登录页面的QQ登录按钮
    TextView tvWeiXin;          // 登录页面的微信登录按钮
    TextView tvWeiBo;           // 登录页面的微博登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_loginpage_layout);
        tvBack.setOnClickListener(click);
        tvRegister.setOnClickListener(click);
        btnLogin.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_user_login_back:
                    Intent intent = new Intent();
                    startActivity(intent);
                case R.id.tv_user_login_login:
                    Intent intentTwo = new Intent();
                    startActivity(intentTwo);
                case R.id.btn_user_login:
                    Intent intentThree = new Intent();
                    startActivity(intentThree);
                    break;
            }
        }
    };

    // 初始化所有控件;
    private void initData() {
        tvBack = (TextView) findViewById(R.id.tv_user_login_back);
        tvRegister = (TextView) findViewById(R.id.tv_user_login_login);
        btnLogin = (Button) findViewById(R.id.btn_user_login);
        tvQQ = (TextView) findViewById(R.id.tv_user_QQ_login);
        tvWeiXin = (TextView) findViewById(R.id.tv_user_weixin_login);
        tvWeiBo = (TextView) findViewById(R.id.tv_user_weibo_login);
        etUserName = (EditText) findViewById(R.id.et_user_key);
        etUserKey = (EditText) findViewById(R.id.et_user_key);
    }


}
