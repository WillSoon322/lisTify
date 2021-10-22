package com.example.listify;

import android.graphics.Movie;

public class MoviesModel {
    private int id=-1;
    private String judul="";
    private String deskripsi="";
    private int bintang =0;  //0 - 5
    private int status=-1; // 0 =blom ditonton , 1= sedang , 2 =sudah
    private String synopsis="";
    private String comment="";

    public MoviesModel(String judul,String synopsis){
        this.judul=judul;
        this.synopsis=synopsis;
    }

    public MoviesModel(int id,String judul, String deskripsi, int bintang, int status,String synopsis,String comment){
        this.id=id;
        this.judul=judul;
        this.deskripsi=deskripsi;
        this.bintang=bintang;
        this.status=status;
        this.synopsis=synopsis;
        this.comment=comment;
    }



    public String getJudul(){
        return this.judul;
    }

    public String getDeskripsi(){
        return this.deskripsi;
    }

    public int getBintang(){
        return this.bintang;
    }

    public String getStatus(){
        int status=this.status;
        switch (status){
            case 1:
                return "Sedang ditonton";
            case 2:
                return "Selesai ditonton";
            default:
                return "Belum ditonton";
        }
    }

    public int getStatusData(){
        return this.status;
    }
    public String getSynopsis(){
        return this.synopsis;
    }

    public String getComment(){
        return this.comment;
    }











}
