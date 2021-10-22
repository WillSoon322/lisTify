package com.example.listify;

import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.listify.databinding.FragmentMoviePageBinding;

public class MoviePage extends Fragment {
    private int index;
    private MoviesModel movie;
    private FragmentMoviePageBinding binding;
    public MoviePage(int index, Object movie){
        this.index=index;
        this.movie=(MoviesModel) movie;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding=FragmentMoviePageBinding.inflate(getLayoutInflater());


        binding.MovieTitle.setText(this.movie.getJudul());
        binding.desc.setText(this.movie.getDeskripsi());
        ImageView bintang[]={binding.star1, binding.star2, binding.star3, binding.star4, binding.star5};
        int rating=this.movie.getBintang();
        for(int i=0;i<rating;i++){
            bintang[i].setImageResource(R.drawable.ic_star_full);
        }

        binding.comment.setText(this.movie.getComment());
        binding.synopsis.setText(this.movie.getSynopsis());


        return binding.getRoot();
    }

}
