package com.example.listify;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

public class SeriesPage extends Fragment implements View.OnClickListener, FragmentResultListener {

    private int index;
    private SeriesModel series;
    private FragmentMoviePageBinding binding;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Rate_modal rt;
    private SeriesPresenter sp;
    private ImageView bintang[];
    private edit_movies em;

    public SeriesPage(int index, Object movie, Object mp) {
        this.index = index;
        this.series = (SeriesModel) movie;
        this.sp = (SeriesPresenter) mp;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding = FragmentMoviePageBinding.inflate(getLayoutInflater());


        binding.MovieTitle.setText(this.series.getJudul()+" - "+this.series.getNumber_episode()+" Episode");
        binding.desc.setText(this.series.getStatus());

        binding.synopsis.setText(this.series.getSynopsis());
        binding.comment.setText(this.series.getComment());
        ImageView bintang[] = {binding.star1, binding.star2, binding.star3, binding.star4, binding.star5};
        this.bintang = bintang;
        int rating = this.series.getBintang();
        setStar(rating);
        String path=this.series.getFoto();
        try {
            binding.moviePoster.setImageURI(Uri.parse(path));
        }catch (Exception e){
            binding.moviePoster.setImageResource(R.drawable.poster);
        }

        this.rt = new Rate_modal("Series");

        binding.RateBtn.setOnClickListener(this);
        binding.editMovie.setOnClickListener(this);
        this.fm = getParentFragmentManager();
        this.ft = fm.beginTransaction();
        this.fm.setFragmentResultListener("closeRating", this, this);
        this.fm.setFragmentResultListener("updateBintang", this, this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        int clicked = v.getId();
        if (clicked == binding.RateBtn.getId()) {

            this.rt.show(getParentFragmentManager(), "null");
        } else if (clicked == binding.editMovie.getId()) {
            Bundle res = new Bundle();
            res.putString("Table","Series");
            res.putInt("id", series.getId());
            res.putString("judul", series.getJudul());
            res.putString("synopsis", series.getSynopsis());
            res.putInt("status", series.getStatusData());
            res.putString("comment", series.getComment());
            res.putString("foto",series.getFoto());
            getParentFragmentManager().setFragmentResult("editMovie", res);

        }
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if (requestKey.equals("closeRating")) {
            if(this.rt.isVisible()) {
                this.rt.dismiss();
            }
        }
        if (requestKey.equals("updateBintang")) {
            String own=result.getString("tipe");
            if(own.equals("Series")) {
                int newRating = result.getInt("bintang");
                this.sp.updateRate(series.getId(), newRating);
                this.rt.dismiss();
                setStar(newRating);
            }
        }
    }

    public void setStar(int rating) {
        for(int i=0;i<5;i++){
            bintang[i].setImageResource(R.drawable.ic_star_empty);
        }
        for (int i = 0; i < rating; i++) {
            bintang[i].setImageResource(R.drawable.ic_star_full);
        }
    }
}