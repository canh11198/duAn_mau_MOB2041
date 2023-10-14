package canhptph44323.fpoly.alo.duan_mau_mob2041.Daos;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Database.DbHelper;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.ThuThu;

public class ThuThuDao {
    DbHelper dbhelper;
    SharedPreferences sharedPreferences;

    public ThuThuDao(Context context) {
        dbhelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
    }

    //đăng nhập
    public boolean checkDangNhap(String matt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT = ? AND matKhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("maTT", cursor.getString(0));
            editor.putString("loaitaikhoan",cursor.getString(3));
            editor.putString("hoTen",cursor.getString(1));
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    public int capNhatMK(String username, String oldPass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT = ? AND matKhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matKhau", newPass);
            long check = sqLiteDatabase.update("ThuThu", contentValues, "maTT =?", new String[]{username});
            if (check == -1) {
                return -1;
            } else {
                return 1;
            }

        }
        return 0;
    }

    public boolean insert(ThuThu thuThu) {
//        String sqlSearch = "SELECT * FROM ThuThu WHERE hoten = '" + userName + "'";
//        Cursor cursor = dbhelper.getData(sqlSearch);
//        if (cursor.getCount() == 0) {
//            String sql = "INSERT INTO ThuThu(hoten, matkhau, loaitaikhoan) VALUES('" + userName + "', '" + password + "', 0)";
//            dbhelper.queryData(sql);
//            return 1;
//        } else {
//            return 0;
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",thuThu.getHoTen());
        contentValues.put("matkhau",thuThu.getMatKhau());
        contentValues.put("maTT",thuThu.getMaTT());
        contentValues.put("loaitaikhoan",thuThu.getLoaitaikhoan());

        long check = sqLiteDatabase.insert("ThuThu",null,contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }


        }

}
