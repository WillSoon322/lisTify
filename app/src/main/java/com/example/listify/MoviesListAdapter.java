package com.example.listify;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.listify.databinding.MoviesListBinding;

import java.util.ArrayList;

public class MoviesListAdapter extends BaseAdapter{
        private Activity activity;
        private MoviesPresenter mp;
        private ArrayList<MoviesModel> film;

    public MoviesListAdapter(Activity activity, MoviesPresenter mp){
        this.activity=activity;
        this.mp=mp;
        updateFoods(mp.getData());
    }

    public void updateFoods(ArrayList<MoviesModel> arr){
        this.film=arr;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return this.film.size();
    }

    @Override
    public MoviesModel getItem(int position) {
        return this.film.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MoviesListBinding bindingMovies;
        ViewHolder vh;
        Log.d("test", "getView: ");
        if(convertView==null){
            bindingMovies=MoviesListBinding.inflate(this.activity.getLayoutInflater());
            convertView=bindingMovies.getRoot();
            vh=new ViewHolder(bindingMovies,position,getItem(position));
            convertView.setTag(bindingMovies);
            Log.d("true", "getView: ");
        }else{
            Log.d("false", "getView: ");
            bindingMovies=(MoviesListBinding) convertView.getTag();
        }
        bindingMovies.MoviesJudul.setText("coba");
        return bindingMovies.getRoot();
    }
}


class ViewHolder{
    private MoviesListBinding binding;
    private int index;
    private MoviesModel moviesModelEntitiy;

    public ViewHolder(MoviesListBinding binding, int index, MoviesModel entitas){
        this.binding=binding;
        this.index=index;
        this.moviesModelEntitiy=entitas;
        setText();
    }

    public void setText(){
        Log.d(moviesModelEntitiy.getJudul(), "setText: ");
        binding.MoviesJudul.setText("coba");
        binding.MoviesStatus.setText(moviesModelEntitiy.getStatus());
        binding.MoviesDeskripsi.setText(moviesModelEntitiy.getDeskripsi());
        ImageView arr[]= {binding.star, binding.star1, binding.star2, binding.star3, binding.star4};
        int skor=moviesModelEntitiy.getBintang();

        for(int i=0;i<skor;i++){
            arr[i].setImageResource(android.R.drawable.star_on);
        }




    }





}


