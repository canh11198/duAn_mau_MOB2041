package canhptph44323.fpoly.alo.duan_mau_mob2041.Daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Database.DbHelper;

public class Demo {
    private SQLiteDatabase db;
    public Demo(Context context){
        DbHelper dbhelper = new DbHelper(context);
        db = dbhelper.getWritableDatabase();

    }
}
