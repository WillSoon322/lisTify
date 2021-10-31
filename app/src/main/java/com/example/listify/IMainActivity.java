package com.example.listify;

import android.content.ContentValues;

import java.util.ArrayList;

public interface IMainActivity {

    //punya movies
    public ArrayList<MoviesModel> fetchData(boolean drop,boolean finish,boolean going,int bintang);
    public ArrayList<MoviesModel> fetchData(String kata,boolean desc);
    public ArrayList<MoviesModel> fetchData(String command);
    public ArrayList<MoviesModel> fetchData();

    //general
    public void openDetail(String table,int pos, Object x, Object y);
    public void update(String table,int id, ContentValues cv);
    public void deleteData(String table,int id);

    //punya Series
    public ArrayList<SeriesModel> fetchDataSeries(boolean drop,boolean finish,boolean going,int bintang);
    public ArrayList<SeriesModel> fetchDataSeries(String kata,boolean desc);
    public ArrayList<SeriesModel> fetchDataSeries(String kata);
    public ArrayList<SeriesModel> fetchDataSeries();


    //punya episode
    public ArrayList<EpisodeModel> fetchDataEpisode(int fk);

    //punya log
    public ArrayList<logModel> fetchDataLog();


}
