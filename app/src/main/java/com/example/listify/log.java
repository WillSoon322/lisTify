package com.example.listify;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.listify.databinding.FragmentLogBinding;


public class log extends Fragment {
    private FragmentLogBinding binding;
    private logPresenter lp;
    private logAdapter la;
    private MainActivity activity;



    public log(MainActivity activity) {
        this.activity=activity;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLogBinding.inflate(getLayoutInflater());
        this.lp=new logPresenter(activity);
        this.la=new logAdapter(this,this.lp);
        binding.lvLog.setAdapter(this.la);
        return binding.getRoot();
    }
}