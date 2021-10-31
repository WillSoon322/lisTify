package com.example.listify;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.listify.databinding.FragmentListBinding;
import java.util.ArrayList;

public class list extends Fragment implements View.OnClickListener, FragmentResultListener, TextWatcher, dataChange { //list movie
    private MainActivity activity;
    private MoviesPresenter moviesP;
    private MoviesListAdapter moviesA;
    private FragmentListBinding binding;
    private Sort sort;
    private Filter filter;
    private FragmentManager FM;
    private FragmentTransaction FT;
    private SeriesListAdapter seriesA;
    private SeriesPresenter seriesP;
    private Boolean isMovie=true;// default movie

    public list(MainActivity a) {
        this.activity=a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = FragmentListBinding.inflate(getLayoutInflater());
        
        //adapter yg movie;
        this.moviesP= new MoviesPresenter(activity,this);
        this.moviesA= new MoviesListAdapter(this,this.moviesP);

        //adapter yg series
        this.seriesP=new SeriesPresenter(activity,this);
        this.seriesA=new SeriesListAdapter(this,this.seriesP);

        binding.daftarMovies.setAdapter(moviesA);


        sort = new Sort();
        filter = new Filter();
        binding.filterbtn.setOnClickListener(this);
        binding.sortBtn.setOnClickListener(this);

        binding.moviesBtn.setOnClickListener(this);
        binding.seriesBtn.setOnClickListener(this);

        binding.searchBar.addTextChangedListener(this);

        this.FM=getParentFragmentManager();
        this.FT=FM.beginTransaction();
        this.FM.setFragmentResultListener("filterProcess",this,this);
        this.FM.setFragmentResultListener("sorting",this,this);
        this.FM.setFragmentResultListener("deleteItem",this,this);
        this.FM.setFragmentResultListener("updateReview",this,this);
        return binding.getRoot();
    }

    public void setAdapterMovie(){
        binding.daftarMovies.setAdapter(this.moviesA);
        this.moviesA.updateMovie(moviesP.getData());
    }

    public void setAdapterSeries(){
        binding.daftarMovies.setAdapter(this.seriesA);
        this.seriesA.updateMovie(seriesP.getData());
    }

    public void addData(MoviesModel a){
        this.moviesP.addData(a);
    }

    public void addData(SeriesModel a){
        this.seriesP.add(a);
    }
    @Override
    public void onClick(View view) {
        int clicked=view.getId();

            this.FM=getParentFragmentManager();
            this.FT=FM.beginTransaction();

        if(clicked==binding.filterbtn.getId()){

            if(this.sort.isAdded()){
                this.FT.hide(this.sort);
            }
            if(this.filter.isAdded()){
                if(this.filter.isVisible()){
                    this.FT.hide(this.filter);
                }else{
                    this.FT.show(this.filter);
                }
            }else{
                this.FT.add(R.id.fragment_list_container,this.filter)
                .addToBackStack(null);
            }
            this.FT.commit();
        }else if(clicked==binding.sortBtn.getId()){
            if(this.filter.isAdded()){
                this.FT.hide(this.filter);
            }
            if(this.sort.isAdded()){
                if(this.sort.isVisible()){
                    this.FT.hide(this.sort);
                }else{
                    this.FT.show(this.sort);
                }
            }else{
                FT.add(R.id.fragment_list_container,sort)
                        .addToBackStack(null);
            }
            this.moviesA.notifyDataSetChanged();
            this.FT.commit();
        }else if(clicked==binding.moviesBtn.getId()){
            this.isMovie=true;
            changeData("Movies");
        }else if(clicked==binding.seriesBtn.getId()){
            this.isMovie=false;
            changeData("Series");
        }
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if(requestKey.equals("filterProcess")) {
            if(isMovie) {
                ArrayList<MoviesModel> ALM = this.moviesP.filter(result.getBoolean("drop"), result.getBoolean("finish"), result.getBoolean("going"), result.getInt("bintang"));
                this.moviesA.updateMovie(ALM);
                setAdapterMovie();
            }else{
                ArrayList<SeriesModel> ALM = this.seriesP.filter(result.getBoolean("drop"), result.getBoolean("finish"), result.getBoolean("going"), result.getInt("bintang"));
                this.seriesA.updateMovie(ALM);
                setAdapterSeries();
            }
        }else if(requestKey.equals("sorting")){
            if (isMovie) {
                ArrayList<MoviesModel> ALM = this.moviesP.sorting(result.getString("kolom"), result.getBoolean("order"));
                this.moviesA.updateMovie(ALM);
                setAdapterMovie();
            }else{
                ArrayList<SeriesModel> ALM = this.seriesP.sorting(result.getString("kolom"), result.getBoolean("order"));
                this.seriesA.updateMovie(ALM);
                setAdapterSeries();
            }
        }else if(requestKey.equals("deleteItem")){
            String tipe=result.getString("tipe");
            int id=result.getInt("id");

            if(tipe.equals("Movie")){
                this.moviesP.deleteMovie( id);
  //              setAdapterMovie();
                changeData("Movies");
            }else{
                this.seriesP.deleteSeries(id);
  //              setAdapterSeries();
                changeData("Series");
            }
        }else if(requestKey.equals("updateReview")){
            String tipe=result.getString("tipe");
            int id=result.getInt("id");
            String review=result.getString("review");
            String title=result.getString("title");
            String sinopsis=result.getString("sinopsis");
            int status=result.getInt("status");
            String foto=result.getString("foto");
            if(tipe.equals("Movie")){
                this.moviesP.reviewMovie(id,review,title,sinopsis,status,foto);
//                setAdapterMovie();
                changeData("Movies");
            }else{
                this.seriesP.reviewSeries(id,review,title,sinopsis,status,foto);
 //               setAdapterSeries();
                changeData("Series");
            }
            getParentFragmentManager().setFragmentResult("balik",new Bundle());
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String keyword=s.toString();
        if(isMovie){
            ArrayList<MoviesModel> arr=this.moviesP.search(keyword);
            this.moviesA.updateMovie(arr);
            setAdapterMovie();
        }else{
            ArrayList<SeriesModel> arr=this.seriesP.search(keyword);
            this.seriesA.updateMovie(arr);
            setAdapterSeries();
        }
    }
    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void changeData(String tipe) {
        if(tipe.equals("Movies")){

            setAdapterMovie();
        }else{
//            this.seriesP=new SeriesPresenter(activity,this);
//            this.seriesA=new SeriesListAdapter(this,this.seriesP);
            setAdapterSeries();
        }
    }
}