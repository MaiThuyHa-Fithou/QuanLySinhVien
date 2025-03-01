package vn.edu.hou.mttha.quanlysinhvien.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LopDAO {
    //khai bao 1 doi tuong SQLiteDatabase
    private SQLiteDatabase db;
    //phuong thuc khoi tao cho lop LopDAO
    public LopDAO(Context context){
        //khoi tao va mo csdl qlsv.db
        DbHelper dbHelper = new DbHelper(context);
        //lay ra doi tuong SQLiteDatabase de gan vao thuoc tinh db cua lopDAO
        db = dbHelper.getWritableDatabase();
    }
    //thuc hien cac truy van toi bang tbl_Lop
    public long themLop(String tenLop){
        //khai bao 1 doi tuong ContentValues de chua du lieu moi can them tbl_Lop
        ContentValues contentValues =new ContentValues();
        contentValues.put("tenLop", tenLop);
        //thuc hien goi phuong thuc insert de them moi du lieu bang tbl_Lop
        return db.insert("tbl_Lop",null,contentValues);
    }
    //thuc hien truy van hien thi danh sach cac lop co trong db
    public ArrayList<String> getAllLop(){
        ArrayList<String> dsLop = new ArrayList<>();
        //thuc hien cau lenh truy van select de lay ra danh sach lop
       // Cursor cursor = db.query("tbl_Lop",null,null,null,null,null);
        Cursor cursor = db.rawQuery("Select * from tbl_Lop", null);
        while (cursor.moveToNext()){
            dsLop.add(cursor.getString(1));
        }
        cursor.close();//dong ket noi
        return dsLop;
    }
}
