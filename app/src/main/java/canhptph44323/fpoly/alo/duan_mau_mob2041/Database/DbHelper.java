package canhptph44323.fpoly.alo.duan_mau_mob2041.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "thu_vien";
    private static final int DB_VERSION = 3;

    static final String CREATE_TABLE_THU_THU =
            "CREATE TABLE ThuThu(maTT TEXT PRIMARY KEY," +
                    "hoten TEXT NOT NULL," +
                    "matkhau TEXT NOT NULL," +
                    "loaitaikhoan TEXT)";


    static final String CREATE_TABLE_THANH_VIEN =
            "CREATE TABLE ThanhVien (" +
                    "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "hoTen TEXT NOT NULL," +
                    "namSinh TEXT NOT NULL)";

    static final String CREATE_TABLE_LOAI_SACH =
            "create table LoaiSach (" +
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLoai TEXT NOT NULL)";
    static final String CREATE_TABLE_SACH =
            "create table Sach (" +
                    "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSach TEXT NOT NULL, " +
                    "giaThue INTEGER NOT NULL, " +
                    "maLoai INTEGER REFERENCES LoaiSach (maLoai))";

    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon (" +
                    "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "maTT TEXT REFERENCES ThuThu(maTT), " +
                    "maTV INTEGER REFERENCES ThanhVien (maTV), " +
                    "maSach INTEGER REFERENCES Sach (maSach)," +
                    "tienThue INTEGER NOT NULL," +
                    "ngay TEXT NOT NULL," +
                    "traSach INTEGER NOT NULL )";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TAO BANG THU THƯ
        db.execSQL(CREATE_TABLE_THU_THU);
        //TAO BANG THANH VIEN
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        //TAO BANG LOAI SACH
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        //TAO BANG SACH
        db.execSQL(CREATE_TABLE_SACH);
        //TAO BANG PHIẾU MƯỢN
        db.execSQL(CREATE_TABLE_PHIEU_MUON);

        db.execSQL("INSERT INTO LoaiSach VALUES (1,'Android'),(2,'JAVAt'),(3,'PHP')");
        db.execSQL("INSERT INTO Sach VALUES (1,'JAVASCRIP 1',1000,1),(2,'JAVA',5000,1),(3,'PHP cơ bản',2000,1)");
        db.execSQL("INSERT INTO ThanhVien VALUES (1,'Phạm Tân Cảnh','1999'),(2,'Trần ngọc hải','2004'),(3,'Ngô xuân bắc','2004')");
        db.execSQL("INSERT INTO ThuThu VALUES ('admin','ADMIN Phạm Tân Cảnh','admin','ADMIN'),('thuthu_123','Nguyễn văn nhật','12345','ThuThu')");
        db.execSQL("INSERT INTO PhieuMuon VALUES (1,'thuthu_123',1,1,2500,'15/10/2023', 1),(2,'thuthu2',2,2,3000,'29/10/2023',0),(3,'thuthu3',3,7,2000,'22/09/2023',1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db. execSQL (dropTableLoaiThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db. execSQL (dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db. execSQL(dropTableLoaiSach) ;
        String dropTableSach = "drop table if exists Sach";
        db. execSQL (dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db. execSQL (dropTablePhieuMuon) ;

        onCreate (db);
    }
}
