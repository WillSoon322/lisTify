package com.example.listify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


import com.example.listify.databinding.FragmentRateModalBinding;

public class Rate_modal extends DialogFragment implements View.OnClickListener {
    private FragmentRateModalBinding binding;
    private ImageButton bintang[];
    private int rating=0;
    private String tipe;

    public  Rate_modal(String data){
        this.tipe=data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentRateModalBinding.inflate(getLayoutInflater());
        ImageButton temp[]={binding.ratingStar1,binding.ratingStar2,binding.ratingStar3,binding.ratingStar4,binding.ratingStar5};
        this.bintang=temp;

        for(ImageButton x: bintang){
            x.setOnClickListener(this);
        }
        binding.close.setOnClickListener(this);
        binding.submit.setOnClickListener(this);
        return this.binding.getRoot();
    }


    @Override
    public void onClick(View v) {
        int clicked=v.getId();
        FragmentManager fm=getParentFragmentManager();
        Bundle result=new Bundle();

        if(clicked==binding.close.getId()){
            fm.setFragmentResult("closeRating",null);
        }else if(clicked==binding.submit.getId()){
            result.putString("tipe",tipe);
            result.putInt("bintang",this.rating);
            fm.setFragmentResult("updateBintang",result);
        }else {

            for (int i = 0; i < bintang.length; i++) {
                if (clicked == bintang[i].getId()) {
                    this.rating = i + 1;
                    break;
                }
            }

            for (ImageButton x : bintang) {
                x.setImageResource(R.drawable.ic_star_empty);
            }
            for (int i = 0; i < rating; i++) {
                bintang[i].setImageResource(R.drawable.ic_star_full);
            }
        }


    }
}
