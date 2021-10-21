package com.example.listify;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.listify.databinding.FragmentListBinding;

public class list extends Fragment {
    private MainActivity activity;
    private MoviesPresenter moviesP;
    private MoviesListAdapter moviesA;


    public list(MainActivity a) {
        this.activity=a;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_list, container, false);
        this.moviesP= new MoviesPresenter(activity);
        this.moviesA= new MoviesListAdapter(activity,this.moviesP);
        ListView list=v.findViewById(R.id.daftar_movies);
        list.setAdapter(this.moviesA);



        return v;
    }
}