package com.bhz.android.caiyoubang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.bhz.android.caiyoubang.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/7.
 */
public class ClassifyActivity extends Activity{
    ListView lvClassify;//菜谱分类标签
    ListView lvContent;//标签具体内容
    String url = "http://apis.juhe.cn/cook/category?parentid=&dtype=&key=d94c0e7caf770bceaca6362dc3d35150";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_classify_layout);
        init();
    }
    private void init(){
        lvClassify = (ListView) findViewById(R.id.list_classify);
        lvContent = (ListView) findViewById(R.id.list_classify_content);
    }
    private void doJson(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ClassifyActivity.this, "网络获取失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    int i = jsonArray.length();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
