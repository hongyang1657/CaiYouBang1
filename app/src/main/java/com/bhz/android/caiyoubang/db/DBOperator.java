package com.bhz.android.caiyoubang.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.bhz.android.caiyoubang.domian.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class DBOperator {
    MyDbHelper helper;
    SQLiteDatabase db;

    public DBOperator(Context context) {
      //  helper = new MyDbHelper(context);
        db = helper.getReadableDatabase();
    }

    // 查询库中内容
    public List<UserData> queryALLDATA() {
        List<UserData> list = new ArrayList<UserData>();
        Cursor cursor = db.query(DBConfig.DB_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            UserData data = buildUserdataFromCursor(cursor);
            list.add(data);
        }
        cursor.close();
        return list;
    }

    private UserData buildUserdataFromCursor(Cursor cursor) {
        UserData data = new UserData();
        data.setUserId(cursor.getString(cursor.getColumnIndex(DBConfig.USER_ID)));
        data.setUserPassword(cursor.getString(cursor.getColumnIndex(DBConfig.USER_PASSWORD)));
        data.setUserName(cursor.getString(cursor.getColumnIndex(DBConfig.USER_NAME)));
        data.setUserQMD(cursor.getString(cursor.getColumnIndex(DBConfig.USER_QMD)));
        return data;
    }

    // 修改库中内容
    public void AddOrUpdateNote(UserData data) {
        if (data == null) {
            return;
        }
        if (data.getId() > 0) {
            db.update(DBConfig.DB_NAME, buildUserdataContentValue(data), DBConfig.DB_ID + "=?", new String[]{data.getId() + ""});
        } else {
            db.insert(DBConfig.DB_NAME, null, buildUserdataContentValue(data));
        }
    }

    private ContentValues buildUserdataContentValue(UserData data) {
        ContentValues values = new ContentValues();
        values.put(DBConfig.USER_ID, data.getUserId());
        values.put(DBConfig.USER_PASSWORD, data.getUserPassword());
        values.put(DBConfig.USER_NAME, data.getUserName());
        values.put(DBConfig.USER_QMD, data.getUserQMD());
        return values;
    }

    // 删除库中内容
    public void deleteData(int id){
        db.delete(DBConfig.DB_NAME, DBConfig.USER_ID+"="+id,null);
    }
}
