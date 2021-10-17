package com.example.listify;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class dbMovies extends SQLiteOpenHelper {
    private static int DB_version=1;
    private static String DB_name="lisTify";
    public dbMovies(Context context){
        super(context,DB_name,null,DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedReaderContract.FeedEntry.SQL_CREATE_ENTRIES_Movies);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
