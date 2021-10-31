package com.example.listify;

import android.content.ContentValues;

import java.util.ArrayList;

public class MoviesPresenter {
    private ArrayList<MoviesModel> movies;
    private IMainActivity ui;
    private dataChange dc;

    public MoviesPresenter(IMainActivity ui,dataChange dc){
        this.ui=ui;
        this.movies=ui.fetchData("");
        this.dc=dc;
    }

    public ArrayList<MoviesModel> getData(){
        return this.movies;
    }

    public MoviesModel getMovie(int pos){
        return movies.get(pos);
    }

    public void openMovie(int posision){ //index di db
        MoviesModel x=getMovie(posision);
        ui.openDetail("Movies",posision, x,this);
    }

    public ArrayList<MoviesModel> filter(boolean drop,boolean finish,boolean going,int bintang){
        this.movies= ui.fetchData(drop,finish,going,bintang);
        return this.movies;
    }

    public ArrayList<MoviesModel> sorting(String kolom,Boolean desc){
        this.movies= ui.fetchData(kolom,desc);
        return this.movies;
    }

    public void updateRate(int Id,int newrate){
        ContentValues cv=new ContentValues();
        cv.put("BINTANG",newrate);
        this.ui.update("Movies",Id,cv);
        this.movies=ui.fetchData(""); //fetch dari db karena dihapus berdasarkan id(PK dari table) sehingga perlu fetch ulang
        this.dc.changeData("Movies");
    }

    public ArrayList<MoviesModel> search(String key){
        this.movies=this.ui.fetchData(key);

        return this.movies;
    }

    public void deleteMovie(int id){
        this.ui.deleteData("Movies",id);
        this.movies= ui.fetchData(""); //fetch dari db karena dihapus berdasarkan id(PK dari table) sehingga perlu fetch ulang
    }

    public void reviewMovie(int id, String review, String title, String Sinopsis,int status,String foto){
        ContentValues cv=new ContentValues();
        cv.put("COMMENT",review);
        cv.put("JUDUL",title);
        cv.put("SYNOPSIS",Sinopsis);
        cv.put("STATUS",status);
        cv.put("FOTO",foto);
        this.ui.update("Movies",id,cv);
        this.movies=ui.fetchData(""); //fetch dari db karena dihapus berdasarkan id(PK dari table) sehingga perlu fetch ulang
    }

    public void addData(MoviesModel a){
        this.movies.add(a);
    }




}
