package com.example.listify;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.listify.databinding.FragmentListEpisodeBinding;


public class listEpisode extends Fragment implements FragmentResultListener {
    private FragmentListEpisodeBinding binding;
    private MainActivity activity;
    private EpisodePresenter ep;
    private int fk;
    private episodeAdapter ea;
    private Rate_modal rm;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private int idForUpdating;
    private ImageView bintang[];
    private EditEpisodeModal eem;

    public listEpisode(MainActivity a,int fk) {
        this.activity=a;
        this.fk=fk;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentListEpisodeBinding.inflate(getLayoutInflater());
        this.ep=new EpisodePresenter(this.activity,fk);
        this.ea=new episodeAdapter(this,this.ep);
        this.binding.listEpisode.setAdapter(ea);
        this.rm=new Rate_modal("Episode");
        this.fm=getParentFragmentManager();
        this.ft=fm.beginTransaction();
        this.fm.setFragmentResultListener("updateBintang",this,this);
        this.fm.setFragmentResultListener("updateEpisodeData",this,this);
        this.fm.setFragmentResultListener("closeRating",this,this);
        return binding.getRoot();
    }

    public void updateBintang(int id){
        this.rm.show(getParentFragmentManager(),null);
        this.idForUpdating=id;
    }



    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if(requestKey.equals("updateBintang")){
            String own=result.getString("tipe");
            if(own.equals("Episode")) {
                int newRating = result.getInt("bintang");
                this.rm.dismiss();
                this.ep.updateStar(this.idForUpdating,newRating);
                this.ea=new episodeAdapter(this,this.ep);
                this.binding.listEpisode.setAdapter(this.ea);
            }
        }else if(requestKey.equals("updateEpisodeData")){
            int id=result.getInt("id");
            String judul=result.getString("newJudul");
            int tipe=result.getInt("tipe");
            this.ep.updateEpisode(id,judul,tipe);
            this.eem.dismiss();
            this.ea=new episodeAdapter(this,this.ep);
            this.binding.listEpisode.setAdapter(this.ea);
        }else if (requestKey.equals("closeRating")) {
            if(this.rm.isVisible()) {
                this.rm.dismiss();
            }
        }
    }

    public void  updateEpisode(EpisodeModel episode){
        this.eem=new EditEpisodeModal();
        this.eem.setData(episode);
        this.eem.show(getParentFragmentManager(),null);

    }




}