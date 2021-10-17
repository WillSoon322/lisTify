package com.example.listify;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainAdapter extends BaseAdapter {
    private Activity activity;
    private MoviesPresenter presenter;
    public MainAdapter(Activity activity,MoviesPresenter presenter){
     this.activity=activity;
     this.presenter=presenter;
    }



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
