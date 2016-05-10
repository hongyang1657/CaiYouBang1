package com.bhz.android.caiyoubang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhz.android.caiyoubang.R;

/**
 * Created by Administrator on 2016/5/9.
 */
public class MenuClassifyContentAdapter extends BaseAdapter{
    LayoutInflater inflater;
    Context context;
    String[] MenuNameList;
    String[] MenuIdList;//菜谱id列表

    public MenuClassifyContentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        //this.MenuNameList = menuNameList;
        //this.MenuIdList = menuIdList;
    }

    @Override
    public int getCount() {
        return MenuNameList==null?10:MenuNameList.length;
    }

    @Override
    public Object getItem(int position) {
        return MenuNameList==null?"2":MenuNameList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.classify_adapter_content_layout,null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_content);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_content);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        if (MenuNameList==null){
            holder.textView.setText("000");
        }else {
            Log.i("result", "getView: ----------"+MenuNameList[position]);
            holder.textView.setText(MenuNameList[position]);
        }
        return convertView;
    }

    public void dataChange(String[] menuNameList,String[] menuIdList,int listLength){
        if (listLength==0){
            MenuNameList = new String[10];
            MenuIdList = new String[10];
        }else{
            MenuNameList = new String[listLength];
            MenuIdList = new String[listLength];
        }
        MenuNameList = menuNameList;
        MenuIdList = menuIdList;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
