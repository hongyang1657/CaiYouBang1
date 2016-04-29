package com.bhz.android.caiyoubang.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/18.
 */
public class MyDbHelper extends SQLiteOpenHelper{
    Context mContext;

    public static final int MENU_DB_VERSION = 1;
    private static final String SQL_CREAT_MENU = "create table if not exists menu(id integer primary key autoincrement,content text,image blob)";

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAT_MENU);
        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
