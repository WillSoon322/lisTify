package com.example.listify;

import java.util.ArrayList;

public class TEMPstorage { //storage sementara
    private  ArrayList <MoviesModel> arr= new ArrayList<>();

    public TEMPstorage(){
        this.arr.add(new MoviesModel("film 0","desc0",0,0));
        this.arr.add(new MoviesModel("film 1","desc1",1,0));
        this.arr.add(new MoviesModel("film 2","desc2",2,1));
        this.arr.add(new MoviesModel("film 3","desc3",3,2));
        this.arr.add(new MoviesModel("film 4","desc4",4,0));
        this.arr.add(new MoviesModel("film 5","desc5",5,0));
    }

    public ArrayList<MoviesModel> getArr(){
        return this.arr;
    }




}
