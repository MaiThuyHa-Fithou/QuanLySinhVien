package vn.edu.hou.mttha.quanlysinhvien.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //khai bao cac thuoc tinh cua database
    private static final String DB_NAME="qlsv.db";
    private static final int DB_VERSION=1;
    //dinh nghia cac cau lenh tao bang trong database: tbl_Lop, tbl_SinhVien
    private static final String CREATE_TABLE_LOP="" +
            "CREATE TABLE tbl_Lop("+
            "maLop INTEGER PRIMARY KEY AUTOINCREMENT,"+
            " tenLop TEXT NOT NULL);";
    private static final String CREATE_TABLE_SINHVIEN =""
            +"CREATE TABLE tbl_SinhVien ("+
            "maSV INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "hoTen TEXT NOT NULL, "+
            "maLop INTEGER, " +
            "FOREIGN KEY (maLop) REFERENCES tbl_Lop (maLop)) ;" ;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //thuc hien cac cau lenh tao bang cho db
        db.execSQL(CREATE_TABLE_LOP);
        db.execSQL(CREATE_TABLE_SINHVIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xoa cac bang trong db neu co
        db.execSQL("DROP TABLE IF EXISTS tbl_SinhVien ");
        db.execSQL("DROP TABLE IF EXISTS tbl_Lop ");
        //goi lai phuong tao bang moi cho db
        onCreate(db);
    }
}
