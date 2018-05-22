package com.suda.zph.passwordmanager.database.databaseImp;

import android.content.Context;
import android.database.Cursor;
import com.suda.zph.passwordmanager.database.PassWordOpenHelper;
import com.suda.zph.passwordmanager.entity.PassWord;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class IPassWord {
    private   PassWordOpenHelper helper;

    public Boolean addPassWord(Context context, PassWord passWord){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sql = "INSERT INTO account (source, user_name, user_password, create_time, mod_time, delete_flag, remarks) " +
                "VALUES (?,?,?,?,?,?,?)";
        helper = new PassWordOpenHelper(context,"account.db3",1);

        helper.getWritableDatabase().execSQL(sql,
                new String[]{
                        passWord.getSource(),
                        passWord.getUser_name(),
                        passWord.getUser_password(),
                        df.format(System.currentTimeMillis()),
                        df.format(System.currentTimeMillis()),
                        "0",
                        passWord.getRemarks()}
                );

        return true;
    }

    public ArrayList<PassWord> findAll(Context context){
        helper = new PassWordOpenHelper(context,"account.db3",1);
        String sql = "select * from account";
        Cursor cursor = helper.getReadableDatabase().rawQuery(sql,new String[]{});
        ArrayList lists = new ArrayList<PassWord>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String  source = cursor.getString(1);
            String user_name = cursor.getString(2);
            String user_password = cursor.getString(3);
            String create_time = cursor.getString(4);
            String mod_time = cursor.getString(5);
            int delete_flag = cursor.getInt(6);
            String remarks = cursor.getString(7);
            lists.add(new PassWord(id,source,user_name,user_password,create_time,mod_time,delete_flag,remarks));
        }
        return lists;
    }
}