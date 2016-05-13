package com.bhz.android.caiyoubang.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.activity.DemoMenuActivity;
import com.bhz.android.caiyoubang.activity.EventForMoreActivity;
import com.bhz.android.caiyoubang.activity.TestActivity;
import com.bhz.android.caiyoubang.adapter.EventSummaryAdapter;
import com.bhz.android.caiyoubang.adapter.TitleAdapter;
import com.bhz.android.caiyoubang.data.EventSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class HomeFragment extends Fragment {
    //系统设定按钮
    TextView tv_systemseting;
    //搜索栏
    EditText ed_forsearch;
    //标题banner
    Gallery gallery_title;
    //活动banner
    Gallery gallery_event;
    //热门菜banner
    Gallery gallery_hot;
    //更多活动
    Button btn_eventformore;
    //更多热门菜
    Button btn_hotformore;
    //bannner相关
    RadioGroup group_title;
    //菜系选择按钮
    Button btn_chuan;
    Button btn_lu;
    Button btn_yue;
    Button btn_su;
    Button btn_zhe;
    Button btn_min;
    Button btn_xiang;
    Button btn_hui;
    //设置用fragment
    FragmentManager fragmentmanager;
    FragmentTransaction transaction;
    //判定是否isshow
    int systemsetjudge = 0;
    //当前Activity
    Activity homepage;
    List<EventSummary> list;
    List<EventSummary> randomlist;
    List<EventSummary> fulleventlist;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_fragment_mainpage, null);
        init(view);
        return view;
    }

    //初始化控件
    private void init(View view) {
        View btnview = view;
        tv_systemseting = (TextView) view.findViewById(R.id.mainpage_home_systemset);
        group_title = (RadioGroup) view.findViewById(R.id.mainpage_home_gallerychoose);
        gallery_title = (Gallery) view.findViewById(R.id.mainpage_home_gallery);
        gallery_event = (Gallery) view.findViewById(R.id.mainpage_home_eventgallery);
        ed_forsearch = (EditText) view.findViewById(R.id.mainpage_home_search);
        gallery_hot = (Gallery) view.findViewById(R.id.mainpage_home_recipegallery);
        btn_eventformore = (Button) view.findViewById(R.id.mainpage_home_moreevent);
        btn_hotformore = (Button) view.findViewById(R.id.mainpage_home_recipe);
        fragmentmanager = getChildFragmentManager();
        homepage = getActivity();
        initbutton(btnview);
        list = new ArrayList<>();
        randomlist = new ArrayList<>();
        fulleventlist=new ArrayList<>();
        setList();
        fulleventlist=list;
        setRandomList();
        EventSummaryAdapter eventadapter = new EventSummaryAdapter(homepage, randomlist);
        TitleAdapter titleadapter = new TitleAdapter(homepage, list);
        gallery_event.setAdapter(eventadapter);
        gallery_title.setAdapter(titleadapter);
        gallery_title.setOnItemSelectedListener(titleselect);
        group_title.setOnCheckedChangeListener(titlegroupchoose);
        btn_eventformore.setOnClickListener(eventformore);
    }

    View.OnClickListener eventformore =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(homepage,EventForMoreActivity.class);
            Bundle bundle=new Bundle();
/*            bundle.putParcelableArray("event",fulleventlist);*/
            startActivity(intent);
        }
    };

    private void setRandomList() {  //随机抽取四张图片作为活动图片装填
        for (int i = 0; i < 4; i++) {
            int random = ((int) (Math.random() * list.size()));
            randomlist.add(list.get(random));
            list.remove(random);
        }

    }

    //初始化按钮
    private void initbutton(View view) {
        btn_chuan = (Button) view.findViewById(R.id.button_chuan);
        btn_lu = (Button) view.findViewById(R.id.button_lu);
        btn_yue = (Button) view.findViewById(R.id.button_yue);
        btn_su = (Button) view.findViewById(R.id.button_su);
        btn_zhe = (Button) view.findViewById(R.id.button_zhe);
        btn_min = (Button) view.findViewById(R.id.button_min);
        btn_xiang = (Button) view.findViewById(R.id.button_xiang);
        btn_hui = (Button) view.findViewById(R.id.button_xiang);
        btn_chuan.setOnClickListener(caixi);
        btn_lu.setOnClickListener(caixi);
        btn_yue.setOnClickListener(caixi);
        btn_su.setOnClickListener(caixi);
        btn_zhe.setOnClickListener(caixi);
        btn_min.setOnClickListener(caixi);
        btn_xiang.setOnClickListener(caixi);
        btn_hui.setOnClickListener(caixi);
        btn_eventformore.setOnClickListener(jump);
        btn_hotformore.setOnClickListener(jump);
    }

    private void setList() {
        for (int i = 1; i <= 8; i++) {
            list.add(setEventSummary(i));
        }
    }

    private EventSummary setEventSummary(int i) {  //将图片RID填入对应list
        EventSummary eventsummary = new EventSummary();
        eventsummary.setResID(datachange(i));
        return eventsummary;
    }

    private Integer datachange(int i) {
        Integer resID = 0;
        switch (i) {
            case 1:
                resID = R.mipmap.event1;
                break;
            case 2:
                resID = R.mipmap.event2;
                break;
            case 3:
                resID = R.mipmap.event3;
                break;
            case 4:
                resID = R.mipmap.event4;
                break;
            case 5:
                resID = R.mipmap.event5;
                break;
            case 6:
                resID = R.mipmap.event6;
                break;
            case 7:
                resID = R.mipmap.event7;
                break;
            case 8:
                resID = R.mipmap.event8;
                break;
        }
        return resID;
    }

    //菜系按钮点击事件
    View.OnClickListener caixi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_chuan:
                    newIntent("chuan");
                    break;
                case R.id.button_lu:
                    newIntent("lu");
                    break;
                case R.id.button_yue:
                    newIntent("yue");
                    break;
                case R.id.button_su:
                    newIntent("su");
                    break;
                case R.id.button_zhe:
                    newIntent("zhe");
                    break;
                case R.id.button_min:
                    newIntent("min");
                    break;
                case R.id.button_xiang:
                    newIntent("xiang");
                    break;
                case R.id.button_hui:
                    newIntent("hui");
                    break;
            }
        }
    };
    //更多信息跳转事件
    View.OnClickListener jump = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mainpage_home_moreevent:
                    Intent intent = new Intent(homepage, EventForMoreActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mainpage_home_morerecipe:
                    break;
            }
        }
    };

    //菜系跳转事件
    private void newIntent(String s) {
        Intent intent = new Intent(homepage, DemoMenuActivity.class);
        intent.putExtra("caixi", s);
        startActivity(intent);
    }

    //标题的gallery控制radiobutton的选择
    AdapterView.OnItemSelectedListener titleselect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            RadioButton button = (RadioButton) group_title.getChildAt(i);
            button.setChecked(true);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    //标题的radiogroup控制gallery滑动
    RadioGroup.OnCheckedChangeListener titlegroupchoose = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.mainpage_home_choose1:
                    gallery_title.setSelection(0);
                    break;
                case R.id.mainpage_home_choose2:
                    gallery_title.setSelection(1);
                    break;
                case R.id.mainpage_home_choose3:
                    gallery_title.setSelection(2);
                    break;
                case R.id.mainpage_home_choose4:
                    gallery_title.setSelection(3);
                    break;
            }
        }
    };

}
