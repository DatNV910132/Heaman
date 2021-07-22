package com.example.dom.heaman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "HeaMan";


    // Tên bảng: Note.
    private static final String TABLE_User = "User";
    private static final String TABLE_SanPham = "SanPham";
    private static final String TABLE_Status = "Status";

    private static final String COLUMN_User_ID = "User_Id";
    private static final String COLUMN_User_Name = "User_Name";
    private static final String COLUMN_User_Gen = "User_Gen";
    private static final String COLUMN_User_Age = "User_Age";
    private static final String COLUMN_User_Chieucao = "User_Chieucao";
    private static final String COLUMN_User_Cannang = "User_Cannang";
    private static final String COLUMN_User_Dienthoai = "User_Dienthoai";
    private static final String COLUMN_User_Calorie = "User_Calorie";

    private static final String COLUMN_SanPham_Code = "SanPham_Code";
    private static final String COLUMN_SanPham_Name = "SanPham_Name";
    private static final String COLUMN_SanPham_Hangsx = "SanPham_Hangsx";
    private static final String COLUMN_SanPham_Loai = "SanPham_Loai";
    private static final String COLUMN_SanPham_Calorie = "SanPham_Calorie";

    private static final String COLUMN_Status_UID = "Status_UId";
    private static final String COLUMN_Status_DATE = "Status_Date";
    private static final String COLUMN_Status_TIME = "Status_Time";
    private static final String COLUMN_Status_SID = "Status_SId";
    private static final String COLUMN_Status_NameSP = "Status_NameSP";
    private static final String COLUMN_Status_Calorie = "Status_Calorie";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script1 = "CREATE TABLE " + TABLE_User + "("
                + COLUMN_User_ID + " INTEGER PRIMARY KEY," + COLUMN_User_Name + " TEXT," + COLUMN_User_Gen + " TEXT,"
                + COLUMN_User_Age + " INTEGER," + COLUMN_User_Chieucao + " INTEGER," + COLUMN_User_Cannang + " INTEGER,"
                + COLUMN_User_Dienthoai + " TEXT," + COLUMN_User_Calorie + " INTEGER" + " )";
        String script2 = "CREATE TABLE " + TABLE_SanPham + "("
                + COLUMN_SanPham_Code + " TEXT PRIMARY KEY," + COLUMN_SanPham_Name + " TEXT,"
                + COLUMN_SanPham_Hangsx + " TEXT," + COLUMN_SanPham_Loai + " TEXT," + COLUMN_SanPham_Calorie + " INTEGER" + ")";
        String script3 = "CREATE TABLE " + TABLE_Status + "("
                + COLUMN_Status_UID + " TEXT," + COLUMN_Status_DATE + " TEXT," + COLUMN_Status_TIME + " TEXT,"
                + COLUMN_Status_SID + " TEXT," + COLUMN_Status_NameSP + " TEXT," + COLUMN_Status_Calorie + " INTEGER" + ")";
        // Chạy lệnh tạo bảng.
        db.execSQL(script1);
        db.execSQL(script2);
        db.execSQL(script3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_User);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SanPham);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Status);


        // Và tạo lại.
        onCreate(db);
    }

    public void addUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.adduser ... " + user.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_User_ID, user.getId());
        values.put(COLUMN_User_Name, user.getName());
        values.put(COLUMN_User_Gen, user.getGen());
        values.put(COLUMN_User_Age, user.getAge());
        values.put(COLUMN_User_Chieucao, user.getChieucao());
        values.put(COLUMN_User_Cannang, user.getCannang());
        values.put(COLUMN_User_Dienthoai, user.getPhone());
        values.put(COLUMN_User_Calorie, user.getCalorie());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_User, null, values);


        // Đóng kết nối database.
        db.close();
    }

    public void addSanpham(SanPham sanpham) {
        Log.i(TAG, "MyDatabaseHelper.addSP ... " + sanpham.getCode());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SanPham_Code, sanpham.getCode());
        values.put(COLUMN_SanPham_Name, sanpham.getTenSP());
        values.put(COLUMN_SanPham_Hangsx, sanpham.getHangsx());
        values.put(COLUMN_SanPham_Loai, sanpham.getLoai());
        values.put(COLUMN_SanPham_Calorie, sanpham.getCalorie());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_SanPham, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public void addStatus(Status status) {
        Log.i(TAG, "MyDatabaseHelper.add status ... " + status);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Status_UID, status.getUID());
        values.put(COLUMN_Status_DATE, status.getDate());
        values.put(COLUMN_Status_TIME, status.getTime());
        values.put(COLUMN_Status_SID, status.getSID());
        values.put(COLUMN_Status_NameSP, status.getNamesp());
        values.put(COLUMN_Status_Calorie, status.getCalorie());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_Status, null, values);


        // Đóng kết nối database.
        db.close();
    }


    public SanPham getSanpham(String code) {
        Log.i(TAG, "MyDatabaseHelper.getSP ... " + code);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_SanPham, new String[]{COLUMN_SanPham_Code,
                        COLUMN_SanPham_Name, COLUMN_SanPham_Hangsx, COLUMN_SanPham_Loai,
                        COLUMN_SanPham_Calorie}, COLUMN_SanPham_Code + "=?",
                new String[]{code}, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        SanPham sp = new SanPham(c.getString(0), c.getString(1),
                c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)));
        // return sanpham
        return sp;
    }

    public Cursor getSanphamGroupbyName() {
        Log.i(TAG, "MyDatabaseHelper.getSP ... Group by name");

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_SanPham, new String[]{COLUMN_SanPham_Code,
                        COLUMN_SanPham_Name, COLUMN_SanPham_Hangsx, COLUMN_SanPham_Loai,
                        COLUMN_SanPham_Calorie}, null,
                null, null, null, COLUMN_SanPham_Name);
        return c;
    }

    public Cursor getSanphamGroupbyLoai() {
        Log.i(TAG, "MyDatabaseHelper.getSP ... Group by name");

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_SanPham, new String[]{COLUMN_SanPham_Code,
                        COLUMN_SanPham_Name, COLUMN_SanPham_Hangsx, COLUMN_SanPham_Loai,
                        COLUMN_SanPham_Calorie}, null,
                null,null , null, COLUMN_SanPham_Loai);
        return c;
    }

    public Cursor getSanphamGroupbyHang() {
        Log.i(TAG, "MyDatabaseHelper.getSP ... Group by name");

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_SanPham, new String[]{COLUMN_SanPham_Code,
                        COLUMN_SanPham_Name, COLUMN_SanPham_Hangsx, COLUMN_SanPham_Loai,
                        COLUMN_SanPham_Calorie}, null,
                null,null , null, COLUMN_SanPham_Hangsx);
        return c;
    }

    public Cursor getSanphamGroupbyCalotc() {
        Log.i(TAG, "MyDatabaseHelper.getSP ... Group by name");

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_SanPham, new String[]{COLUMN_SanPham_Code,
                        COLUMN_SanPham_Name, COLUMN_SanPham_Hangsx, COLUMN_SanPham_Loai,
                        COLUMN_SanPham_Calorie}, null,
                null, null, null, COLUMN_SanPham_Calorie + " ASC");
        return c;
    }

    public Cursor getSanphamGroupbyCaloct() {
        Log.i(TAG, "MyDatabaseHelper.getSP ... Group by name");

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_SanPham, new String[]{COLUMN_SanPham_Code,
                        COLUMN_SanPham_Name, COLUMN_SanPham_Hangsx, COLUMN_SanPham_Loai,
                        COLUMN_SanPham_Calorie}, null,
                null, null, null, COLUMN_SanPham_Calorie + " DESC");
        return c;
    }

    public ArrayList<SanPham> getAllSP() {
        Log.i(TAG, "MyDatabaseHelper.getAllSanPham ... ");

        ArrayList<SanPham> spList = new ArrayList<SanPham>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SanPham;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                SanPham sp = new SanPham();
                sp.setCode(cursor.getString(0));
                sp.setTenSP(cursor.getString(1));
                sp.setHangsx(cursor.getString(2));
                sp.setLoai(cursor.getString(3));
                sp.setCalorie(Integer.parseInt(cursor.getString(4)));

                // Thêm vào danh sách.
                spList.add(sp);
            } while (cursor.moveToNext());
        }

        // return note list
        return spList;
    }

    public Cursor getUser() {
        Log.i(TAG, "MyDatabaseHelper.getAlluser ... ");

        String selectQuery = "SELECT  * FROM " + TABLE_User;
        User user = new User();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
//                user.setId(cursor.getInt(0));
//                user.setName(cursor.getString(1));
//                user.setGen(cursor.getString(2));
//                user.setAge(cursor.getInt(3));
//                user.setChieucao(cursor.getInt(4));
//                user.setCannang(cursor.getInt(5));
//                user.setPhone(cursor.getString(6));
//                user.setCalorie(cursor.getInt(7));
        return cursor;
    }
    public User getdataUser() {
        Log.i(TAG, "MyDatabaseHelper.getAlluser ... ");

        String selectQuery = "SELECT  * FROM " + TABLE_User;
        User user = new User();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
   //      Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setGen(cursor.getString(2));
                user.setAge(cursor.getInt(3));
                user.setChieucao(cursor.getInt(4));
                user.setCannang(cursor.getInt(5));
                user.setPhone(cursor.getString(6));
                user.setCalorie(cursor.getInt(7));
            } while (cursor.moveToNext());
        }

        return user;
    }

    public Cursor getAllSanPham() {
        Log.i(TAG, "MyDatabaseHelper.getAllSanPham ... ");

        ArrayList<SanPham> spList = new ArrayList<SanPham>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SanPham;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public Cursor SearchSanPham(String s) {
        Log.i(TAG, "MyDatabaseHelper.getAllSanPham ... ");

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SanPham + " WHERE " + COLUMN_SanPham_Name + " LIKE '%"+s+"%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public Cursor getAllStatus() {
        Log.i(TAG, "MyDatabaseHelper.getAllSanPham ... ");

        ArrayList<Status> spList = new ArrayList<Status>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Status;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // Duyệt trên con trỏ, và thêm vào danh sách.
//        if (cursor.moveToFirst()) {
//            do {
//                Status sp = new Status();
//                sp.setUID(cursor.getString(2));
//                sp.setDate(cursor.getString(1));
//                sp.setSID(cursor.getString(0));
//                sp.setCalorie(cursor.getInt(3));
//
//                // Thêm vào danh sách.
//                spList.add(sp);
//            } while (cursor.moveToNext());
//        }
        return cursor;
    }
    public Cursor getItemStatus() {
        Log.i(TAG, "MyDatabaseHelper.getItemStatus ... ");
        String selectQuery = "SELECT "+COLUMN_Status_DATE +", sum("+COLUMN_Status_Calorie+")"+" FROM " + TABLE_Status+ " GROUP BY "+COLUMN_Status_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
    public Cursor getAllItemStatus() {
        Log.i(TAG, "MyDatabaseHelper.getSP ... " );

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_Status, new String[]{COLUMN_Status_DATE,COLUMN_Status_Calorie}, null,
                null, null, null, null, null);
        if (c != null)
            c.moveToFirst();

//        Status st = new Status(c.getString(0), c.getString(1),
//                c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)));
        // return Status
        return c;
    }
    public Cursor getStatus(String date) {
        Log.i(TAG, "MyDatabaseHelper.getSP ... " + date);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_Status, new String[]{COLUMN_Status_UID,
                        COLUMN_Status_DATE, COLUMN_Status_TIME, COLUMN_Status_SID, COLUMN_Status_NameSP,
                        COLUMN_Status_Calorie}, COLUMN_Status_DATE + "=?",
                new String[]{date}, null, null, null, null);
        if (c != null)
            c.moveToFirst();

//        Status st = new Status(c.getString(0), c.getString(1),
//                c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)));
        // return Status
        return c;
    }

    public int getSPCount() {
        Log.i(TAG, "MyDatabaseHelper.getSPCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_SanPham;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.update user ... " + user.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_User_ID, user.getId());
        values.put(COLUMN_User_Name, user.getName());
        values.put(COLUMN_User_Gen, user.getGen());
        values.put(COLUMN_User_Age, user.getAge());
        values.put(COLUMN_User_Chieucao, user.getChieucao());
        values.put(COLUMN_User_Cannang, user.getCannang());
        values.put(COLUMN_User_Dienthoai, user.getPhone());
        values.put(COLUMN_User_Calorie, user.getCalorie());


        // updating row
        return db.update(TABLE_User, values, COLUMN_User_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    public int updateSP(SanPham sanpham) {
        Log.i(TAG, "MyDatabaseHelper.upadate sp ... " + sanpham.getCode());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SanPham_Code, sanpham.getCode());
        values.put(COLUMN_SanPham_Name, sanpham.getTenSP());
        values.put(COLUMN_SanPham_Hangsx, sanpham.getHangsx());
        values.put(COLUMN_SanPham_Loai, sanpham.getLoai());
        values.put(COLUMN_SanPham_Calorie, sanpham.getCalorie());

        // updating row
        return db.update(TABLE_User, values, COLUMN_User_ID + " = ?",
                new String[]{String.valueOf(sanpham.getCode())});
    }

    public void deleteSP(SanPham sp) {
        Log.i(TAG, "MyDatabaseHelper.Deletesp ... " + sp.getTenSP());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SanPham, COLUMN_SanPham_Code + " = ?",
                new String[]{String.valueOf(sp.getTenSP())});
        db.close();
    }
}
