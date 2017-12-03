package com.example.ahame_000.seg2105.databasing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "appDatabase.db";
    public static final String DB_TABLE_ACCOUNTS = "CREATE TABLE IF NOT EXISTS Accounts(email TEXT, password TEXT)";
    public static final String DB_TABLE_PROFILES = "CREATE TABLE IF NOT EXISTS Profiles(kind TEXT, name TEXT, password TEXT, points INTEGER, accEmail TEXT)";
    public static final String DB_TABLE_CHORES = "CREATE TABLE IF NOT EXISTS Chores(id TEXT, name TEXT, desc TEXT, state TEXT, completedDate TEXT, deadline TEXT, creator TEXT, assignedTo TEXT, reward INTEGER, penalty INTEGER, accEmail TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 16);
    }

    /**
     * On creation of an instance, create a database if need be
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_TABLE_ACCOUNTS);
        sqLiteDatabase.execSQL(DB_TABLE_PROFILES);
        sqLiteDatabase.execSQL(DB_TABLE_CHORES);
    }

    /**
     * On database update, drop the current database and create a new one
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Accounts");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Profiles");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Chores");
        this.onCreate(sqLiteDatabase);
    }
}
