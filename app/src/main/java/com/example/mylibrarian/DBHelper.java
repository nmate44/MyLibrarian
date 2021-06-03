package com.example.mylibrarian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "myLibrarian.db";

    public static final String TABLE_USER = "user";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";

    public static final String TABLE_BOOK = "userBooks";
    public static final String BOOK_ID = "id";
    public static final String BOOK_PAGESREAD = "pagesRead";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_USER +
                        "(" +
                        USER_ID + " INTEGER PRIMARY KEY, " +
                        USER_NAME + " TEXT" +
                        ")"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_BOOK +
                        "(" +
                        BOOK_ID + " INTEGER PRIMARY KEY, " +
                        BOOK_PAGESREAD + "INTEGER" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }

    public boolean setUser(int id, String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, id);
        contentValues.put(USER_NAME, userName);
        db.insert(TABLE_USER, null, contentValues);
        return true;
    }

    public int dropUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(
                TABLE_USER, USER_ID + " = ?", new String[] {Integer.toString(id)}
        );
    }

    public boolean insertUserBook(int id, int pagesRead) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID, id);
        contentValues.put(BOOK_PAGESREAD, pagesRead);
        db.insert(TABLE_BOOK, null, contentValues);
        return true;
    }

    public Cursor findUserBookById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_BOOK + " WHERE id=" + id, null);
    }

    public boolean updateUserBook(int id, int pagesRead) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_PAGESREAD, pagesRead);
        db.update(
                TABLE_BOOK, contentValues, BOOK_ID + " = ?", new String[] {Integer.toString(id)}
                );
        return true;
    }

    public int removeUserBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(
                TABLE_BOOK, BOOK_ID + " = ?", new String[] {Integer.toString(id)}
        );
    }
}
