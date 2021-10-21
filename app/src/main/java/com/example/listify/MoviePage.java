package com.example.listify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MoviePage extends Fragment {
    private int index;
    private MoviesModel movie;
    public MoviePage(int index, Object movie){
        this.index=index;
        this.movie=(MoviesModel) movie;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_movie_page, container, false);
        TextView judul =v.findViewById(R.id.Movie_title);
        judul.setText(this.movie.getJudul());
        TextView desc=v.findViewById(R.id.desc);
        desc.setText(this.movie.getDeskripsi());


        return v;
    }

}
