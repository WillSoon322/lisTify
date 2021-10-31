package com.example.listify;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.listify.databinding.FragmentMoviePageBinding;

public class MoviePage extends Fragment implements View.OnClickListener, FragmentResultListener {
    private int index;
    private MoviesModel movie;
    private FragmentMoviePageBinding binding;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private  Rate_modal rt;
    private MoviesPresenter mp;
    private ImageView bintang[];
    private edit_movies em;
    public MoviePage(int index, Object movie,Object mp){
        this.index=index;
        this.movie=(MoviesModel) movie;
        this.mp=(MoviesPresenter) mp;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding=FragmentMoviePageBinding.inflate(getLayoutInflater());


        binding.MovieTitle.setText(this.movie.getJudul());
        binding.desc.setText(this.movie.getStatus());
        binding.synopsis.setText(this.movie.getSynopsis());
        binding.comment.setText(this.movie.getComment());
        String path=movie.getFoto();
        try {
            binding.moviePoster.setImageURI(Uri.parse(path));
        } catch (Exception e){
            binding.moviePoster.setImageResource(R.drawable.poster);
        }

        ImageView bintang[]={binding.star1, binding.star2, binding.star3, binding.star4, binding.star5};
        this.bintang=bintang;
        int rating=this.movie.getBintang();
        setStar(rating);


        this.rt=new Rate_modal("Movie");



        binding.RateBtn.setOnClickListener(this);
        binding.editMovie.setOnClickListener(this);
        this.fm=getParentFragmentManager();
        this.ft=fm.beginTransaction();
        this.fm.setFragmentResultListener("closeRating",this,this);
        this.fm.setFragmentResultListener("updateBintang",this,this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        int clicked=v.getId();
        if(clicked==binding.RateBtn.getId()){

            this.rt.show(getParentFragmentManager(),"null");

        }else if(clicked==binding.editMovie.getId()){
            Bundle res=new Bundle();
            res.putString("Table","Movie");
            res.putInt("id",movie.getId());
            res.putString("judul",movie.getJudul());
            res.putString("synopsis",movie.getSynopsis());
            res.putInt("status",movie.getStatusData());
            res.putString("comment",movie.getComment());
            res.putString("foto",movie.getFoto());
            getParentFragmentManager().setFragmentResult("editMovie",res);

        }



    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if(requestKey.equals("closeRating")){
            this.rt.dismiss();
        }if(requestKey.equals("updateBintang")){
            String own=result.getString("tipe");
            if(own.equals("Movie")) {
                int newRating = result.getInt("bintang");
                this.mp.updateRate(movie.getId(), newRating);
                this.rt.dismiss();
                setStar(newRating);
            }

        }
    }
    public void setStar(int rating){
        for(int i=0;i<5;i++){
            bintang[i].setImageResource(R.drawable.ic_star_empty);
        }
        for(int i=0;i<rating;i++){
            bintang[i].setImageResource(R.drawable.ic_star_full);
        }
    }










}
