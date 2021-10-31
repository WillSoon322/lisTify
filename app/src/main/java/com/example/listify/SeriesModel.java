package com.example.listify;



public class SeriesModel {
    private int id=-1;
    private String judul="";
    private int bintang =0;  //0 - 5
    private int status=0; // 0 =sedang , 2= drop , 1 =sudah
    private String synopsis="";
    private String comment="";
    private int number_episode=0;
    private String foto;

    public SeriesModel(String judul,String synopsis,int number_episode,String foto){
        this.number_episode=number_episode;
        this.judul=judul;
        this.synopsis=synopsis;
        this.foto=foto;
    }

    public SeriesModel(int id,String judul, int bintang, int status,String synopsis,String comment,int Number_episode, String foto){
        this.id=id;
        this.judul=judul;
        this.bintang=bintang;
        this.status=status;
        this.synopsis=synopsis;
        this.comment=comment;
        this.number_episode=Number_episode;
        this.foto=foto;
    }

    public int getId(){
        return this.id;
    }

    public String getJudul(){
        return this.judul;
    }

    public int getNumber_episode(){
        return this.number_episode;
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

    public String getComment(){
        return this.comment;
    }

    public String getFoto(){
        return this.foto;
    }











}
