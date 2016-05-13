package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bhz.android.caiyoubang.R;

/**
 * Created by Administrator on 2016/4/26 0026.
 */

//  本页面缺少的内容   1.日期设置器
//                     2.往数据库传输内容
public class UserEditDataActivity extends Activity {

    TextView tvBack;            // 资料编辑页面的返回按钮
    TextView tvSave;            // 资料编辑页面的保存按钮
    EditText etName;            // 资料编辑页面的昵称输入框
    EditText etSex;             // 资料编辑页面的性别输入框
    EditText etArea;            // 资料编辑页面的地区输入框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_data_layout);
        initData();
        tvBack.setOnClickListener(click);
        tvSave.setOnClickListener(click);
    }

    private void initData() {
        tvBack = (TextView) findViewById(R.id.tv_user_edit_data_back);
        tvSave = (TextView) findViewById(R.id.tv_user_edit_data_save);
        etName = (EditText) findViewById(R.id.et_user_edit_data_name);
        etSex = (EditText) findViewById(R.id.et_user_edit_data_sex);
        etArea = (EditText) findViewById(R.id.et_user_edit_data_area);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_user_edit_data_back:                                               // 用户点击了返回的事件
                    Intent intentBack = new Intent(UserEditDataActivity.this, UserMyActivity.class);
                    startActivity(intentBack);
                    break;
                case R.id.tv_user_edit_data_save:                                               // 用户点击了保存的事件
                    Intent intentSave = new Intent(UserEditDataActivity.this, UserMyActivity.class);
                    break;
            }
        }
    };

    private void dataSave() {
        String UserName = etName.getText().toString();     //获取用户输入的昵称
        String UserSex = etSex.getText().toString();       //获取用户输入的性别
        String UserArea = etArea.getText().toString();     //获取用户输入的地域
    }
}
