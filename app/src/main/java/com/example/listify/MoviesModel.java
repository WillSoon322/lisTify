package com.example.listify;

public class MoviesModel {
    private String judul;
    private String deskripsi;
    private int bintang;  //0 - 5
    private byte status; // 0 =blom ditonton , 1= sedang , 2 =sudah

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
