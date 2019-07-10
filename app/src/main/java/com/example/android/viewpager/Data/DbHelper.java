package com.example.android.viewpager.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sDatabase";
    private static int DATABASE_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTable = "CREATE TABLE " + Contract.Entry.TABLE_NAME + " ( " +
                Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Entry.COLUMN_ARTICLE + " TEXT NOT NULL, " +
                Contract.Entry.COLUMN_TIME + " TEXT NOT NULL, " +
                Contract.Entry.COLUMN_PAPER + " TEXT NOT NULL, " +
                Contract.Entry.COLUMN_TYPE + " TEXT , " +
                Contract.Entry.COLUMN_LINK + " TEXT NOT NULL);";


        db.execSQL(sqlTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Entry.TABLE_NAME);
        onCreate(db);

    }
}
