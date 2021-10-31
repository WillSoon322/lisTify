package com.example.listify;

import android.content.ContentValues;

import java.util.ArrayList;

public class SeriesPresenter {
    private ArrayList<SeriesModel> series;
    private IMainActivity ui;
    private dataChange dc;

    public SeriesPresenter(IMainActivity ui ,dataChange dc){
        this.ui=ui;
        this.series=ui.fetchDataSeries();
        this.dc=dc;
    }

    public SeriesModel getSeries(int pos){
        return series.get(pos);
    }
    public ArrayList<SeriesModel> getData(){
        return series;
    }

    public void openSeries(int posisi){
        SeriesModel x=getSeries(posisi);
        ui.openDetail("Series",posisi,x,this);
    }

    public ArrayList<SeriesModel> search(String key){

        this.series=this.ui.fetchDataSeries(key);
        return this.series;
    }

    public ArrayList<SeriesModel> filter(boolean drop,boolean finish,boolean going,int bintang){
        this.series= ui.fetchDataSeries(drop, finish, going, bintang);
        return this.series;
    }

    public ArrayList<SeriesModel> sorting(String kolom,Boolean desc){
        this.series= ui.fetchDataSeries(kolom,desc);
        return this.series;
    }

    public void updateRate(int Id,int newrate){
        ContentValues cv=new ContentValues();
        cv.put("BINTANG",newrate);
        this.ui.update("Series",Id,cv);
        this.series=ui.fetchDataSeries(); //fetch dari db karena dihapus berdasarkan id(PK dari table) sehingga perlu fetch ulang
        this.dc.changeData("Series");
    }

    public void deleteSeries(int id){
        this.ui.deleteData("Series",id);
        this.series= ui.fetchDataSeries(); //fetch dari db karena dihapus berdasarkan id(PK dari table) sehingga perlu fetch ulang
    }

    public void reviewSeries(int id, String review, String title, String Sinopsis,int status,String foto){
        ContentValues cv=new ContentValues();
        cv.put("COMMENT",review);
        cv.put("JUDUL",title);
        cv.put("SYNOPSIS",Sinopsis);
        cv.put("STATUS",status);
        cv.put("FOTO",foto);
        this.ui.update("Series",id,cv);
        this.series=ui.fetchDataSeries();//fetch dari db karena dihapus berdasarkan id(PK dari table) sehingga perlu fetch ulang
    }

    public void add(SeriesModel a){
        this.series.add(a);
    }


}
