package com.example.listify;

import android.content.ContentValues;

import java.util.ArrayList;

public class EpisodePresenter {
    private ArrayList<EpisodeModel> arr;
    private IMainActivity ui;
    private int fk;
    public EpisodePresenter(MainActivity ui,int fk){
        this.ui=ui;
        this.fk=fk;
        arr= ui.fetchDataEpisode(fk);
    }

    public ArrayList<EpisodeModel> getAllData(){
        return this.arr;
    }

    public EpisodeModel getData(int pos){
        return this.arr.get(pos);
    }

    public void updateStar(int id, int star){
        ContentValues cv=new ContentValues();
        cv.put("BINTANG",star);
        ui.update("Episode",id,cv);
        this.arr= ui.fetchDataEpisode(fk);
    }

    public void  updateEpisode(int id,String newJudul,int code){
        ContentValues cv=new ContentValues();
        cv.put("JUDUL",newJudul);
        cv.put("STATUS",code);
        this.ui.update("Episode",id,cv);
        this.arr=ui.fetchDataEpisode(fk);
    }



}
