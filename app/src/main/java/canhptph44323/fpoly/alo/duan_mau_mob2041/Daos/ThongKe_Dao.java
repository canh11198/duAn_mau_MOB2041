package canhptph44323.fpoly.alo.duan_mau_mob2041.Daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Database.DbHelper;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.Sach;

public class ThongKe_Dao {
    DbHelper dbhelper;
    public ThongKe_Dao(Context context){
        dbhelper = new DbHelper(context);
    }
    public ArrayList<Sach> getTop10(){
        ArrayList<Sach>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.maSach, sc. tenSach, COUNT (pm.maSach) FROM PhieuMuon pm, Sach sc WHERE pm.maSach = sc.maSach GROUP BY pm.maSach, sc.tenSach ORDER BY COUNT (pm.maSach) DESC LIMIT 10",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM (tienthue) FROM PHIEUMUON WHERE substr (ngay, 7) || substr (ngay, 4,2) || substr (ngay, 1,2) BETWEEN ? AND ?",new String[]{ngaybatdau,ngayketthuc});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;

    }
}
