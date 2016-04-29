package com.bhz.android.caiyoubang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.info.MenuStepInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class MenuBaseAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    List<MenuStepInfo> stepInfos;


    public MenuBaseAdapter(Context context,List<MenuStepInfo> stepInfos) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.stepInfos = stepInfos;
    }

    @Override
    public int getCount() {
        return stepInfos==null?0:stepInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return stepInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.method_layout,null);
            holder = new ViewHolder();
            holder.stepNumber = (TextView) convertView.findViewById(R.id.tv_step_number1);
            holder.stepImage = (ImageView) convertView.findViewById(R.id.image_step_image1);
            holder.stepText = (TextView) convertView.findViewById(R.id.et_step_content1);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        MenuStepInfo info = stepInfos.get(position);
        int number = position+1;
        holder.stepNumber.setText(""+number);
        holder.stepText.setText(info.getStepText());
        Picasso.with(context).load(info.getStepImage()).into(holder.stepImage);
        return convertView;
    }

    public void addStepInfos(List<MenuStepInfo> stepInfos){
        if (this.stepInfos==null){
            this.stepInfos = new ArrayList<MenuStepInfo>();
        }
        this.stepInfos = stepInfos;
        notifyDataSetChanged();
    }

    class ViewHolder{
        private TextView stepNumber;
        private ImageView stepImage;
        private TextView stepText;
    }

}
