package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bhz.android.caiyoubang.R;

/**
 * Created by Administrator on 2016/4/30 0030.
 */
// 缺少的内容  1.获取手机验证码服务 发送
//             2.缺少一个方法，验证输入的验证码和该手机号码的验证码是否一致;
//             3.缺少一个食疗服务条款的页面跳转
public class UserPhoneRegisterActivity extends Activity {

    TextView tvBack;           //页面的返回上一步按钮
    TextView tvNext;           //页面的下一步按钮
    TextView tvGetCode;       //页面的获取验证码按钮
    TextView tvServe;         //页面的查看服务的按钮
    EditText etPhone;         //页面的输入手机号码框
    EditText etAuthCode;     //页面的输入验证码的框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_phone_register_layout);
        initData();
        tvNext.setOnClickListener(click);
        tvGetCode.setOnClickListener(click);
    }

    private void initData() {
        tvNext = (TextView) findViewById(R.id.tv_user_phone_register_next);
        tvGetCode = (TextView) findViewById(R.id.tv_user_phone_register_get_authcode);
        tvServe = (TextView) findViewById(R.id.tv_user_phone_register_serve);
        tvBack = (TextView) findViewById(R.id.tv_user_phone_register_back);
        etPhone = (EditText) findViewById(R.id.et_user_phone_register_phone_number);
        etAuthCode = (EditText) findViewById(R.id.et_user_phone_register_verification_code);
    }

    //手机注册页面的两个按钮  一个用来获取验证码，一个跳转到登录页面；
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_user_phone_register_back:                       // 返回按钮
                    Intent intentBack = new Intent(UserPhoneRegisterActivity.this, UserMyActivity.class);  //  !!!!!!!!!!!!!!!!!!!!!该页面跳转需要改成跳到主页面而不是这个页面
                    startActivity(intentBack);
                    break;
                case R.id.tv_user_phone_register_next:                       // 注册的下一步按钮
                    Intent intentNext = new Intent(UserPhoneRegisterActivity.this, UserRegisterActivity.class);
                    startActivity(intentNext);
                    break;
                case R.id.tv_user_phone_register_get_authcode:             // 获取验证码的按钮
                    break;
                case R.id.tv_user_phone_register_serve:                     // 食疗的服务检查按钮
                    break;
            }
        }
    };

    private void saveData() {
        String userPhone = etPhone.getText().toString();       // 获取用户输入的手机号码信息;
        String authCode = etAuthCode.getText().toString();    //  获取用户输入的验证码信息;
    }
}
