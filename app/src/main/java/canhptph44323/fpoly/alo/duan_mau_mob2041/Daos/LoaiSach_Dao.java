package canhptph44323.fpoly.alo.duan_mau_mob2041.Daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Database.DbHelper;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.loaiSach;

public class LoaiSach_Dao {
    DbHelper dbhelper;

    public LoaiSach_Dao(Context context) {
        dbhelper = new DbHelper(context);
    }

    //lấy danh sách loại sách
    public ArrayList<loaiSach> getDSloaiSach() {
        ArrayList<loaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSach", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new loaiSach(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //Thêm loại sách
    public boolean themLoaiSach(String tenLoai) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoai);

        long check = sqLiteDatabase.insert("LoaiSach", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }


    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach WHERE maLoai = ?",new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LoaiSach","maLoai =?",new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }

    public boolean suaLoaiSach(loaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",loaiSach.getTenLoai());

        long check = sqLiteDatabase.update("LoaiSach",contentValues,"maLoai =?",new String[]{String.valueOf(loaiSach.getMaLoai())});
        if (check == -1)
            return false;
        return true;

    }
}
