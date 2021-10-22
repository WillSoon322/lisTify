package com.example.listify;

import java.util.ArrayList;

public interface IMainActivity {


    public void dataChanged(Object change);
    public void openDetail(int pos, Object x);
    public ArrayList<MoviesModel> fetchData();


}
