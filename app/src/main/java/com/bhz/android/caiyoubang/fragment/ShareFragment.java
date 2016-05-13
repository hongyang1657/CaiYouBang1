package com.bhz.android.caiyoubang.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhz.android.caiyoubang.R;


/**
 * Created by Administrator on 2016/4/19.
 */
public class ShareFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_fragment_share,container,false);
        return view;
    }
}
