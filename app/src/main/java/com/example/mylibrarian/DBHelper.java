package com.example.mylibrarian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "mylibrarian.db";

    public static final String TABLE_BOOK = "userBooks";
    public static final String BOOK_ID = "id";
    public static final String BOOK_PAGESREAD = "pagesRead";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_BOOK +
                        "(" +
                        BOOK_ID + " TEXT PRIMARY KEY, " +
                        BOOK_PAGESREAD + " INTEGER" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }

    public boolean insertUserBook(String id, int pagesRead) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID, id);
        contentValues.put(BOOK_PAGESREAD, pagesRead);
        db.insert(TABLE_BOOK, null, contentValues);
        System.out.println("DB Record inserted");
        return true;
    }

    public Cursor getAllUserBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_BOOK, null);
    }

    public Cursor getUserBookById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_BOOK + " WHERE id=" + id, null);
    }

    public boolean updateUserBook(String id, int pagesRead) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_PAGESREAD, pagesRead);
        db.update(
                TABLE_BOOK, contentValues, BOOK_ID + " = ?", new String[] {id}
                );
        return true;
    }

    public int removeUserBook(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(
                TABLE_BOOK, BOOK_ID + " = ?", new String[] {id}
        );
    }
}
