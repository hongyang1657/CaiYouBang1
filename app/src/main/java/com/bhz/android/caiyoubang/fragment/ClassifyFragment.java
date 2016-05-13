package com.bhz.android.caiyoubang.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.activity.ClassifyActivity;
import com.bhz.android.caiyoubang.adapter.MenuClassifyContentAdapter;

/**
 * Created by Administrator on 2016/5/12.
 */
public class ClassifyFragment extends Fragment{
    ListView listView;
    MenuClassifyContentAdapter adapter;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.classify_right_fragment_layout,container,false);
        listView = (ListView) view.findViewById(R.id.lv_fragment_right);
        adapter = new MenuClassifyContentAdapter(context);
        listView.setAdapter(adapter);
        return view;
    }
}
