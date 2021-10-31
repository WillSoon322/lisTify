package com.example.listify;



import java.util.ArrayList;

public class logPresenter {
    private ArrayList<logModel> arr;
    private IMainActivity ui;
    public logPresenter(IMainActivity ui){
        this.ui=ui;
        this.arr=ui.fetchDataLog();
    }

    public ArrayList<logModel> getArr(){
        return this.arr;
    }





}
