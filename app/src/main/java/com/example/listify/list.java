package com.example.listify;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listify.databinding.FragmentListBinding;

public class list extends Fragment {
    private MainActivity activity;
    private MoviesPresenter moviesP;
    private MoviesListAdapter moviesA;
    private FragmentListBinding binding;


    public list(MainActivity a) {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentListBinding.inflate(getLayoutInflater());
        this.moviesP= new MoviesPresenter(activity);
        this.moviesA= new MoviesListAdapter(activity,this.moviesP);
        this.binding.listView.setAdapter(moviesA);



        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}