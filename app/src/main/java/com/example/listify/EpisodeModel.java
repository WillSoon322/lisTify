package com.example.listify;

public class EpisodeModel {
    private int id;
    private String judul;
    private int bintang=0;
    private int fk;
    private int status=0; //0=belum ditonton 1=sudah ditonton

    public EpisodeModel(int id,String judul,int bintang,int fk){
        this(id,judul,bintang,fk,0);
    }

    public EpisodeModel(int id,String judul,int bintang,int fk, int status){
        this.id=id;
        this.judul=judul;
        this.bintang=bintang;
        this.fk=fk;
        this.status=status;
    }

    public int getBintang() {
        return bintang;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public int getFk() {
        return fk;
    }

    public int getStatus(){
        return this.status;
    }


}
