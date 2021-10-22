package com.example.listify;

import java.util.ArrayList;

public class MoviesPresenter {
    private ArrayList<MoviesModel> movies;
    private IMainActivity ui;

    public MoviesPresenter(IMainActivity ui){
        this.ui=ui;
        this.movies=ui.fetchData();
    }

    public ArrayList<MoviesModel> getData(){
        return this.movies;
    }

    public MoviesModel getMovie(int pos){
        return movies.get(pos);
    }

    public void openMovie(int posision){
        MoviesModel x=getMovie(posision);
        ui.openDetail(posision, x);
    }






}
