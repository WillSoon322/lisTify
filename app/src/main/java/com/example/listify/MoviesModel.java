package com.example.listify;

import android.graphics.Movie;

public class MoviesModel {
    private String judul=null;
    private String deskripsi=null;
    private int bintang =-1;  //0 - 5
    private int status=-1; // 0 =blom ditonton , 1= sedang , 2 =sudah

    public MoviesModel(String judul, String deskripsi, int bintang, int status){
        this.judul=judul;
        this.deskripsi=deskripsi;
        this.bintang=bintang;
        this.status=status;
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









}
