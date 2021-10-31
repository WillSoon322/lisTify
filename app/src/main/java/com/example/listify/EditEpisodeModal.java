package com.example.listify;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listify.databinding.FragmentEditEpisodeModalBinding;


public class EditEpisodeModal extends DialogFragment implements View.OnClickListener {
    private EpisodeModel episode;
    private FragmentEditEpisodeModalBinding binding;

    public EditEpisodeModal() {

    }

    public void setData(EpisodeModel x){
        this.episode=x;
        if(binding!=null){
            binding.judulEpisode.setText(episode.getJudul());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentEditEpisodeModalBinding.inflate(getLayoutInflater());
        binding.judulEpisode.setText(episode.getJudul());
        int tipe=episode.getStatus();
        if(tipe==0){
            binding.belumditonton.setChecked(true);
        }else{
            binding.sudahditonton.setChecked(true);
        }

        binding.submitbtn.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        int clicked=v.getId();
        if(clicked==binding.submitbtn.getId()){
            String newJudul=binding.judulEpisode.getText().toString();
            int tipe=-1;
            if(binding.belumditonton.isChecked()){
                tipe=0;
            }else if(binding.sudahditonton.isChecked()){
                tipe=1;
            }
            Bundle bundle=new Bundle();
            bundle.putInt("id", episode.getId());
            bundle.putString("newJudul",newJudul);
            bundle.putInt("tipe",tipe);
            getParentFragmentManager().setFragmentResult("updateEpisodeData",bundle);



        }
    }
}