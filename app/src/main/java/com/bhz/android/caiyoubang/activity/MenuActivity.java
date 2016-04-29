package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.adapter.MenuBaseAdapter;
import com.bhz.android.caiyoubang.info.MenuInfo;
import com.bhz.android.caiyoubang.info.MenuStepInfo;
import com.bhz.android.caiyoubang.view.ScrollListView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 显示菜单的页面
 * Created by Administrator on 2016/4/18.
 */
public class MenuActivity extends Activity{
    String menuUrl = "http://apis.juhe.cn/cook/index?cid=5&dtype=&pn=&rn=&format=&key=d94c0e7caf770bceaca6362dc3d35150";
    MenuBaseAdapter adapter;
    ScrollListView methodList;
    ImageView imageHead;
    TextView tvMenuName;
    TextView tvMenuAbstract;
    TextView tvMenuStuff;
    TextView tvMenuTips;

    String jsonData;//json数据
    String MenuName;
    String MenuAbstract;
    String MenuStuff;
    String MenuTips;
    String MenuimageHead;//主图片
    int length;//菜谱步骤数
    List<MenuStepInfo> liststepInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        init();
        getMenuInfoFromNet();
    }



    private void init(){
        //初始化控件
        imageHead = (ImageView) findViewById(R.id.image_head);
        tvMenuName = (TextView) findViewById(R.id.menu_name);
        tvMenuAbstract = (TextView) findViewById(R.id.menu_abstract);
        tvMenuStuff = (TextView) findViewById(R.id.tv_menu_stuff);
        tvMenuTips = (TextView) findViewById(R.id.menu_tips_content);
        methodList = (ScrollListView) findViewById(R.id.list_menu_method);

        adapter = new MenuBaseAdapter(this,liststepInfos);
        methodList.setAdapter(adapter);
    }

    //使用handler加载数据
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //设置各个textView的值
            tvMenuName.setText(MenuName);
            tvMenuAbstract.setText(MenuAbstract);
            tvMenuStuff.setText(MenuStuff);
            tvMenuTips.setText(MenuTips);
            MenuimageHead = MenuimageHead.replaceAll("\\]|\"|\\[|\\\\","");//去掉图片uri中的多余字符
            Picasso.with(MenuActivity.this).load(MenuimageHead).into(imageHead);//用毕加索加载图片

        }
    };

    //获取网络数据
    private void getMenuInfoFromNet(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(menuUrl).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("result", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonData = response.body().string();
                doJsonAnalyzing();
                handler.sendEmptyMessage(10);//使用handler加载数据
            }
        });

    }

    //解析菜谱json数据
    public void doJsonAnalyzing(){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray dataArray = result.getJSONArray("data");
            JSONObject dataMain = dataArray.getJSONObject(0);
            MenuName = dataMain.getString("title");//解析得到菜谱名
            MenuAbstract = dataMain.getString("imtro");//解析得到菜谱简介
            MenuStuff = dataMain.getString("ingredients")+dataMain.getString("burden");//解析得到菜谱用料
            MenuTips = dataMain.getString("tags");//解析得到小贴士
            MenuimageHead = dataMain.getString("albums");//解析得到图片uri

            JSONArray stepArray = dataMain.getJSONArray("steps");
            length = stepArray.length();//菜谱的步骤数
            MenuStepInfo info;
            liststepInfos = new ArrayList<MenuStepInfo>();
            for(int i=0;i<length;i++){
                JSONObject stepInfo = stepArray.getJSONObject(i);
                String image = stepInfo.getString("img");//解析得到步骤图片地址
                image = image.replaceAll("\\]|\"|\\[|\\\\","");
                String text = stepInfo.getString("step");//解析得到步骤文字
                info = new MenuStepInfo(image,text);
                liststepInfos.add(info);
                info = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clickTwo(View view){
        switch (view.getId()){
            case R.id.text_click:
                adapter.addStepInfos(liststepInfos);//刷新adapter
                break;
        }
    }

}
