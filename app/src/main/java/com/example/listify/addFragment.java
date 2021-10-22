package com.example.listify;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.listify.databinding.FragmentAddBinding;

public class addFragment extends Fragment implements View.OnClickListener {
    private FragmentAddBinding binding;


    public addFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentAddBinding.inflate(getLayoutInflater());
        this.binding.AddBtn.setOnClickListener(this);



        return binding.getRoot();

    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.LTGRAY);
    }


    @Override
    public void onClick(View v) {
        int clicked=v.getId();

        if(clicked==binding.AddBtn.getId()) {
            if (binding.radioMovie.isChecked()) {
                String judul = binding.EditJudul.getText().toString();
                String Synopsis = binding.EditSynopsis.getText().toString();
                Bundle result = new Bundle();
                result.putString("Title", judul);
                result.putString("Synopsis", Synopsis);
                getParentFragmentManager().setFragmentResult("insertData",result);
            }
        }
    }
}