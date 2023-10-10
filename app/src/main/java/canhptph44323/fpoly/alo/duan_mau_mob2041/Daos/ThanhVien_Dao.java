package canhptph44323.fpoly.alo.duan_mau_mob2041.Daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Database.DbHelper;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.ThanhVien;

public class ThanhVien_Dao {
    DbHelper dbhelper;
    public ThanhVien_Dao(Context context){
        dbhelper = new DbHelper(context);
    }

    public ArrayList<ThanhVien> getDSthanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThanhVien",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themThanhVien(String hoten, String namsinh){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",hoten);
        contentValues.put("namSinh",namsinh);

        long check = sqLiteDatabase.insert("ThanhVien",null,contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinTV( int matv,String hoten, String namsinh){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",hoten);
        contentValues.put("namSinh",namsinh);

        long check = sqLiteDatabase.update("ThanhVien",contentValues,"maTV = ?",new String[]{String.valueOf(matv)});

        if (check == -1)
            return false;
        return true;
    }

    public int xoaThongTinThanhVien(int matv){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon WHERE maTV = ?", new String[]{String.valueOf(matv)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("ThanhVien","maTV = ?",new String[]{String.valueOf(matv)});

        if (check == -1)
            return 0;
        return 1;
    }
}
