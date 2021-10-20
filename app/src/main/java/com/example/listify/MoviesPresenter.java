package com.example.listify;

import java.util.ArrayList;

public class MoviesPresenter {
    private ArrayList<MoviesModel> movies;
    private IMainActivity ui;

    public MoviesPresenter(IMainActivity ui){
        this.ui=ui;
        TEMPstorage db=new TEMPstorage();
        this.movies=db.getArr();
    }

    public ArrayList<MoviesModel> getData(){
        return this.movies;
    }






}
