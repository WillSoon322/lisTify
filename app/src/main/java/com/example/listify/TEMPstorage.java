package com.example.listify;

import java.util.ArrayList;

public class TEMPstorage { //storage sementara
    private  ArrayList <MoviesModel> arr= new ArrayList<>();

    public TEMPstorage(){
        this.arr.add(new MoviesModel("film 1","desc1",1,0));
        this.arr.add(new MoviesModel("film 2","desc2",2,0));
        this.arr.add(new MoviesModel("film 3","desc3",3,0));
        this.arr.add(new MoviesModel("film 4","desc4",4,0));
    }

    public ArrayList<MoviesModel> getArr(){
        return this.arr;
    }




}
