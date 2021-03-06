package com.example.listify;



public class MoviesModel {
    private int id=-1;
    private String judul="";
    private int bintang =0;  //0 - 5
    private int status=0; // 0 =sedang , 1= drop , 2 =sudah
    private String synopsis="";
    private String comment="";
    private String foto;

    public MoviesModel(String judul,String synopsis,String foto){
        this.judul=judul;
        this.synopsis=synopsis;
        this.foto=foto;
    }

    public MoviesModel(int id,String judul, int bintang, int status,String synopsis,String comment, String foto){
        this.id=id;
        this.judul=judul;
        this.bintang=bintang;
        this.status=status;
        this.synopsis=synopsis;
        this.comment=comment;
        this.foto=foto;
    }



    public String getJudul(){
        return this.judul;
    }



    public int getBintang(){
        return this.bintang;
    }

    public String getStatus(){
        int status=this.status;
        switch (status){
            case 1:
                return "Dropped";
            case 2:
                return "Selesai ditonton";
            default:
                return "Sedang ditonton";
        }
    }

    public int getStatusData(){
        return this.status;
    }
    public String getSynopsis(){
        return this.synopsis;
    }
    public int getId(){
        return this.id;
    }
    public String getComment(){
        return this.comment;
    }

    public String getFoto(){
        return this.foto;
    }












}
