package com.example.listify;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;



import com.example.listify.databinding.MoviesListBinding;

import java.util.ArrayList;

public class SeriesListAdapter extends BaseAdapter {
    private ArrayList<SeriesModel> arr;
    private list list;
    private SeriesPresenter sp;
    public SeriesListAdapter(list list,SeriesPresenter sp){
        this.list=list;
        this.sp=sp;
        arr=sp.getData();
    }

    public void updateMovie(ArrayList<SeriesModel>arr){
        this.arr=null;
        this.arr=arr;
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public SeriesModel getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MoviesListBinding binding;
        ViewHolderSeries vh;
        if(convertView==null){
            binding=MoviesListBinding.inflate(this.list.getLayoutInflater());
            convertView=binding.getRoot();
            vh=new ViewHolderSeries(binding,getItem(position),position,this.sp);
            convertView.setTag(binding);
        }else{
            binding=(MoviesListBinding) convertView.getTag();
        }
        return binding.getRoot();
    }

}

class ViewHolderSeries implements View.OnClickListener {
    private MoviesListBinding binding;
    private int index;
    private ImageView arr[];
    private SeriesModel series;
    private SeriesPresenter sp;
    public ViewHolderSeries(MoviesListBinding binding,SeriesModel series,int index, SeriesPresenter sp){
        this.binding=binding;
        this.series=series;
        this.index=index;
        this.sp=sp;
        ImageView star[]={binding.star, binding.star1, binding.star2, binding.star3, binding.star4};
        this.arr=star;
        setText();
        binding.boxMovies.setOnClickListener(this);
    }

    public void setText(){
        binding.MoviesJudul.setText(series.getJudul());
        binding.MoviesStatus.setText(series.getStatus());

        int skor=series.getBintang();
        String path=series.getFoto();
        try {
            binding.gambarMovie.setImageURI(Uri.parse(path));
        }catch (Exception e){
            binding.gambarMovie.setImageResource(R.drawable.poster);
        }
        for(int i=0;i<skor;i++){
            this.arr[i].setImageResource(R.drawable.ic_star_full);
        }
    }


    @Override
    public void onClick(View v) {
        int clicked=v.getId();
        if(clicked==binding.boxMovies.getId()){
            sp.openSeries(index);
        }
    }
}
