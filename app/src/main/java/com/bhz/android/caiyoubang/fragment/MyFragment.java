package com.bhz.android.caiyoubang.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.adapter.MyRecipeAdapter;
import com.bhz.android.caiyoubang.data.MyRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class MyFragment extends Fragment {
    EditText et_epsode;
    RadioGroup group_mychoice;
    ListView list_my;
    TextView tv_tip;
    List<MyRecipe> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_fragment_my, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        et_epsode = (EditText) view.findViewById(R.id.mainpage_my_epsode);
        group_mychoice = (RadioGroup) view.findViewById(R.id.mainpage_my_option);
        list_my = (ListView) view.findViewById(R.id.mainpage_my_optionlist);
        tv_tip= (TextView) view.findViewById(R.id.mainpage_my_tip);
        group_mychoice.setOnCheckedChangeListener(mychoice);
        list=new ArrayList<>();
        setlist();
        MyRecipeAdapter adapter=new MyRecipeAdapter(getActivity(),list);
        list_my.setAdapter(adapter);
    }

    private void setlist() {
        for(int i=0;i<10;i++){
            MyRecipe recipe=new MyRecipe();
            recipe.setName(i+"");
            recipe.setTime(System.currentTimeMillis()+"");
            recipe.setContent("fuck you"+i);
            list.add(recipe);
        }
    }

    RadioGroup.OnCheckedChangeListener mychoice = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.mainpage_my_recipe:
                if (list==null){
                    tv_tip.setVisibility(View.VISIBLE);
                }
                break;
/*                        *//*case R.id.mainpage_my_memo:*//*
                break;*/
            case R.id.mainpage_my_favourite:
                break;
        }
        }
    };

    public void test(){

    }
}
