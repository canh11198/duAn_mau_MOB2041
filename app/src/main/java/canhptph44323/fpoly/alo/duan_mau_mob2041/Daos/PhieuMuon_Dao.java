package canhptph44323.fpoly.alo.duan_mau_mob2041.Daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Database.DbHelper;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.phieuMuon;

public class PhieuMuon_Dao {
    DbHelper dbhelper;

    public PhieuMuon_Dao(Context context) {
        dbhelper = new DbHelper(context);
    }

    public ArrayList<phieuMuon> getDsPhieuMuon() {
        ArrayList<phieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        //int maPM, int maTV, String maTT, String maSach, String tenSach, int ngay, String traSach, String tienThue, int tenTV, int tenTT
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.maPM, pm.maTV, tv.hoTen, pm.maTT," +
                " tt.hoTen, pm.maSach, sc.tenSach, pm.ngay, pm.traSach, " +
                "pm.tienThue FROM PhieuMuon pm, ThanhVien tv, ThuThu tt, " +
                "Sach sc WHERE pm.maTV = tv.maTV AND pm.maTT = tt.maTT AND pm.maSach = sc.maSach", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new phieuMuon(cursor.getInt(0),
                        cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getInt(5), cursor.getString(6),
                        cursor.getString(7), cursor.getInt(8),
                        cursor.getInt(9)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean ThayDoiTrangThai(int mapm) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("traSach", 1);
        long check = sqLiteDatabase.update("PhieuMuon", contentValues, "maPM = ?", new String[]{String.valueOf(mapm)});
        if (check == -1)
            return false;
        return  true;
    }

    public boolean themPm(phieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("maPM",phieuMuon.getMaPM());
        contentValues.put("maTV",phieuMuon.getMaTV());
        contentValues.put("maTT",phieuMuon.getMaTT());
        contentValues.put("maSach",phieuMuon.getMaSach());
        contentValues.put("ngay",phieuMuon.getNgay());
        contentValues.put("traSach",phieuMuon.getTraSach());
        contentValues.put("tienThue",phieuMuon.getTienThue());

        long check = sqLiteDatabase.insert("PhieuMuon",null,contentValues);
        if (check == -1){
            return false;
        }
        else {
            return true;
        }
    }
}
