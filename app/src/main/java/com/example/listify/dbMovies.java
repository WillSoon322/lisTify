package com.example.listify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.core.view.OneShotPreDrawListener;

import java.util.ArrayList;


public class dbMovies extends SQLiteOpenHelper {
    private static int DB_version=3;
    private static String DB_name="lisTify.db";
    private static  final String CREATE_TABLE_MOVIES= "CREATE TABLE Movies (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "JUDUL TEXT , " +
            "DESKRIPSI TEXT , " +
            "BINTANG INTEGER , " +
            "STATUS INTEGER , " +
            "SYNOPSIS TEXT , " +
            "COMMENT TEXT )" ;

    private String SELECT_ALL_QUERY="SELECT * FROM Movies";


    public dbMovies(Context context){
        super(context,DB_name,null,DB_version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS Movies";
        db.execSQL( query);
    }

    public void insertDataMovies(MoviesModel movie){
        String judul=movie.getJudul();
        String deskripsi=movie.getDeskripsi();
        int bintang =movie.getBintang();
        int status=movie.getStatusData();
        String synopsis= movie.getSynopsis();
        String comment=movie.getComment();

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newData=new ContentValues();
        newData.put("JUDUL",judul);
        newData.put("DESKRIPSI",deskripsi);
        newData.put("BINTANG",bintang);
        newData.put("STATUS",status);
        newData.put("SYNOPSIS",synopsis);
        newData.put("COMMENT",comment);

        db.insert("Movies",null,newData);

    }

    public ArrayList <MoviesModel> converttoArrayMovies(Cursor c){
        ArrayList <MoviesModel> arr=new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                int id = Integer.parseInt(c.getString(0));
                String judul = c.getString(1);
                String deskripsi = c.getString(2);
                int bintang = Integer.parseInt(c.getString(3));
                int status = Integer.parseInt(c.getString(4));
                String synopsis = c.getString(5);
                String comments = c.getString(6);

                MoviesModel x = new MoviesModel(id, judul, deskripsi, bintang, status, synopsis, comments);
                arr.add(x);
            } while (c.moveToNext());
        }
        return arr;
    }

    public ArrayList<MoviesModel> getAllDataSorted(String kolom ,Boolean Descending){
        String query=this.SELECT_ALL_QUERY;
        if(!kolom.equals("")){
            query+=" ORDER BY "+kolom;
        }

        if(Descending){
            query+=" DESC";
        }else{
            query+=" ASC";
        }
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c =db.rawQuery(query,null);
        ArrayList <MoviesModel> arr=converttoArrayMovies(c);
        return arr;
    }

    public ArrayList<MoviesModel> getAllData(){
        return getAllDataSorted("",false);
    }














    public int getSize(){
        int ukuran=getAllData().size();
        return ukuran;
    }








}
