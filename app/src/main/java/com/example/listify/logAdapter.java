package com.example.listify;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.listify.databinding.LogItemBinding;

import java.util.ArrayList;

public class logAdapter extends BaseAdapter {
    private ArrayList<logModel> arr;
    private log log;

    public logAdapter(log log,logPresenter lp){
        this.arr=lp.getArr();
        this.log=log;

    }


    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public logModel getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogItemBinding binding=LogItemBinding.inflate(log.getLayoutInflater());
        logModel log=getItem(position);

        binding.aksi.setText(log.getAksi());
        binding.judul.setText(log.getJudul());
        binding.table.setText(log.getTable());
        binding.date.setText(log.getTanggal());


        return binding.getRoot();
    }
}
