package com.example.listify;

import android.graphics.Movie;

public class MoviesModel {
    private String judul=null;
    private String deskripsi=null;
    private int bintang =-1;  //0 - 5
    private byte status=-1; // 0 =blom ditonton , 1= sedang , 2 =sudah

    public MoviesModel(String judul, String deskripsi, int bintang, byte status){
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

    public byte getStatus(){
        return this.status;
    }









}
