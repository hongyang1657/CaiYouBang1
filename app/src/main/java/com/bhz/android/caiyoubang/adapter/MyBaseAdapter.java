package com.bhz.android.caiyoubang.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bhz.android.caiyoubang.R;
import com.bhz.android.caiyoubang.activity.CreateMenuActivity;
import com.bhz.android.caiyoubang.info.CreateMenuStepInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.app.ActivityCompat.startActivityForResult;


/**
 * Created by Administrator on 2016/4/13.
 */
public class MyBaseAdapter extends BaseAdapter{
    int i;//上传菜谱步骤数
    Context context;
    List<CreateMenuStepInfo> list;
    LayoutInflater inflater;
    CreateMenuStepInfo stepInfo;
    ViewHolder holder = null;
    Uri imageUri;


    public MyBaseAdapter(Context context,List<CreateMenuStepInfo> list) {
        this.context = context;
        //this.menuInfoList = menuInfoList;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //stepInfo = (MenuStepInfo) getItem(position);
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.step_layout,null);
            holder.tvStepNumber = (TextView) convertView.findViewById(R.id.tv_step_number);
            holder.imageStep = (ImageView) convertView.findViewById(R.id.image_step_image);
            holder.etStepContent = (EditText) convertView.findViewById(R.id.et_step_content);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        i = position+1;
        holder.tvStepNumber.setText(""+i);//设置步骤数目
        holder.imageStep.setOnClickListener(listener);
        String stepContent = holder.etStepContent.getText().toString();
        return convertView;
    }

    public void addInfo(CreateMenuStepInfo stepInfo){
         if(list==null){
             list = new ArrayList<>();
         }
         list.add(stepInfo);
        notifyDataSetChanged();
    }
    public final class ViewHolder{
        public TextView tvStepNumber;
        public ImageView imageStep;
        public EditText etStepContent;
        public Button btSaveAndSend;//点击获取每个edittext中的字符
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "--"+i, Toast.LENGTH_SHORT).show();
            savePhotoAndIntent();//跳转相册，储存照片
        }
    };

    public void savePhotoAndIntent(){
        File userMenuImage = new File(Environment.getExternalStorageDirectory(),"user_menu_image.jpg");
        if (userMenuImage.exists()){
            userMenuImage.delete();
        }
        try {
            userMenuImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(userMenuImage);
        Intent intent = new Intent("android.intent.action. GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivity(null,intent,null);
    }
}
