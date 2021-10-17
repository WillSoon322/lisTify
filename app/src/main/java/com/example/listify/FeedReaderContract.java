package com.example.listify;

import android.provider.BaseColumns;
import android.database.sqlite.*;

public final class FeedReaderContract {


    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Movies";
        public static final String kolom_judul = "title";
        public static final String kolom_deskripsi = "deskripsi";
        public static final String kolom_bintang = "bintang";
        public static final String kolom_status = "status";
        //public static final String


        public static final String SQL_CREATE_ENTRIES_Movies =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FeedEntry.kolom_judul + " TEXT," +
                        FeedEntry.kolom_deskripsi + " TEXT," +
                        FeedEntry.kolom_bintang + " TEXT," +
                        FeedEntry.kolom_status + " TEXT )";

        public static final String SQL_DELETE =
                "DROP TABLE IF EXIST" + FeedEntry.TABLE_NAME;

    }


}
