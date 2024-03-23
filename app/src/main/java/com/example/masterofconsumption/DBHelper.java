package com.example.masterofconsumption;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public  static final int DATABASE_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context, "upload_db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "create table uploaditem(item text, name text primary key, price text)";
        try
        {
            db.execSQL(createSQL);
        }
        catch (Exception e)
        {
            Log.e("onCreate","테이블 생성 중 오류 발생");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropSQL = "drop table item ";
        db.execSQL(dropSQL);
        onCreate(db);
    }
}
