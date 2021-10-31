package com.example.listify;

public class logModel {
    private int aksi=0; //0=insert 2=delete 1=update
    private int table=0;  // 0=movies 1=series
    private String tanggal="";
    private String judul="";

    public logModel(int aksi,String judul,int table,String tanggal){
        this.aksi=aksi;
        this.judul=judul;
        this.table=table;
        this.tanggal=tanggal;
    }

    public String getJudul(){
        return this.judul;
    }

    public String getAksi() {
        switch (this.aksi){
            case 0:
                return "Insert";
            case 1:
                return "Update";
            default:
                return "Delete";

        }
    }

    public String getTanggal() {
        return this.tanggal;
    }

    public String getTable() {
        switch (this.table){
            case 0:
                return "Movies";
            default:
                return "Series";
        }
    }
}
