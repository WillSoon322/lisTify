package com.example.listify;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.listify.databinding.FragmentAddBinding;



public class addFragment extends Fragment implements View.OnClickListener {
    private FragmentAddBinding binding;
    private String poster;
    private ActivityResultLauncher<Intent> intentLauncher;

    public addFragment( ){
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentAddBinding.inflate(getLayoutInflater());
        this.binding.AddBtn.setOnClickListener(this);
        this.binding.radioMovie.setOnClickListener(this);
        this.binding.radioSeries.setOnClickListener(this);
        this.binding.uploadPoster.setOnClickListener(this);
        disableEditText(binding.seasonCount);




        this.intentLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent hasil=result.getData();
                        String data=hasil.getDataString();

                        Uri x=Uri.parse(data);

                        String realPath=getPathFromUri(x);
                        binding.uploadPoster.setImageURI(Uri.parse(realPath));
                        poster=realPath;
                    }
                });

        return binding.getRoot();

    }

    private void disableEditText(EditText editText) {
        editText.setVisibility(View.GONE);
    }

    private void enableEditText(EditText editText) {
        editText.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        int clicked=v.getId();
        Bundle result = new Bundle();
        if(clicked==binding.AddBtn.getId()) {

            String judul = binding.EditJudul.getText().toString();
            String Synopsis = binding.EditSynopsis.getText().toString();
            result.putString("Title", judul);
            result.putString("Synopsis", Synopsis);
            String tipe="";
            if (binding.radioMovie.isChecked()) {
                result.putString("tipe","Movies");
                tipe="Movies";
            }else if(binding.radioSeries.isChecked()){
                int num_season=0;
                try {
                    num_season = Integer.parseInt(binding.seasonCount.getText().toString());
                }catch (Exception e){
                    num_season=0;
                }
                result.putString("tipe","series");
                result.putInt("season",num_season);
                tipe="Series";

            }

            binding.EditJudul.setText("");
            binding.uploadPoster.setImageResource(R.drawable.ic_add);
            binding.EditSynopsis.setText("");
            binding.seasonCount.setText("");
            binding.radioMovie.setChecked(true);
            binding.radioSeries.setChecked(false);


            result.putString("foto",this.poster);
            getParentFragmentManager().setFragmentResult("insertData",result);
        }else if(clicked==binding.radioSeries.getId()){
            binding.EditJudul.setHint("Tv Series Title");
            enableEditText(binding.seasonCount);
        }else if(clicked==binding.radioMovie.getId()){
            binding.EditJudul.setHint("Movie Title");
            disableEditText(binding.seasonCount);
        }else if(clicked==binding.uploadPoster.getId()){
            Intent getImage= new Intent(Intent.ACTION_PICK);
            getImage.setType("image/*");
            this.intentLauncher.launch(getImage);
        }
    }




    public String getPathFromUri (Uri content){
        String path=null;
        String proj[]={MediaStore.MediaColumns.DATA};
        Cursor c=getContext().getContentResolver().query(content,proj,null,null,null);
        c.moveToFirst();
        int columnIndex=c.getColumnIndex(proj[0]);
        path=c.getString(columnIndex);
        c.close();
        return path;
    }








}