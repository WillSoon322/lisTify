package com.example.listify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.listify.databinding.FragmentEditMoviesBinding;

public class edit_movies extends Fragment implements View.OnClickListener {
    private String tipe;
    private int id;
    private String judul;
    private String sinopsis;
    private int status;
    private String comment;
    private FragmentEditMoviesBinding binding;
    private RadioButton btn[];
    private MainActivity activity;
    private listEpisode List;
    private String poster="";
    private ActivityResultLauncher<Intent> intentLauncher;

    public edit_movies(MainActivity activity,String tipe,int id,String judul,String sinopsis,int status,String comment,String path) {
        this.activity=activity;
        this.tipe=tipe;
        this.id=id;
        this.judul=judul;
        this.sinopsis=sinopsis;
        this.status=status;
        this.comment=comment;
        this.poster=path;
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentEditMoviesBinding.inflate(getLayoutInflater());
        binding.judul.setText(this.judul);
        binding.sinopsis.setText(this.sinopsis);
        binding.review.setText(this.comment);
        binding.review.setText(this.comment);
        try {
            binding.add.setImageURI(Uri.parse(this.poster));
        }catch (Exception e){
            binding.add.setImageResource(R.drawable.poster);
        }
        RadioButton arr[]={binding.ongoing,binding.droped,binding.statusFinish};
        this.btn=arr;
        this.btn[this.status].setChecked(true);


        this.binding.ratingButton.setOnClickListener(this); //submit
        this.binding.delete.setOnClickListener(this);
        this.binding.openEpisode.setOnClickListener(this);
        this.binding.add.setOnClickListener(this);

        if(tipe.equals("Movie")){
            binding.openEpisode.setVisibility(View.GONE);

        }

        this.intentLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent hasil=result.getData();
                        String data=hasil.getDataString();

                        Uri x=Uri.parse(data);

                        String realPath=getPathFromUri(x);
                        binding.add.setImageURI(Uri.parse(realPath));
                        poster=realPath;
                    }
                });




        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        int clicked=v.getId();
            Bundle res=new Bundle();
        res.putString("tipe",tipe);
        res.putInt("id",id);

        if(clicked==binding.delete.getId()) {
            String confirm=getResources().getString(R.string.confirm);
            String tidak=getResources().getString(R.string.Tidak);
            String ya=getResources().getString(R.string.Ya);
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

            builder.setMessage(confirm+" "+tipe+" ini ?").
                    setTitle(confirm+" "+tipe)
                    .setNegativeButton(tidak, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setPositiveButton(ya,new DialogInterface.OnClickListener(){
                public void onClick (DialogInterface d,int id){
                    getParentFragmentManager().setFragmentResult("deleteItem",res);
                    getParentFragmentManager().setFragmentResult("balik",res);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else if(clicked==binding.ratingButton.getId()){
            String review=binding.review.getText().toString();
            String judul=binding.judul.getText().toString();
            String sinopsis=binding.sinopsis.getText().toString();
            res.putString("review",review);
            res.putString("title",judul);
            res.putString("sinopsis",sinopsis);


            if(binding.statusFinish.isChecked()){
                res.putInt("status",2);
            }else if(binding.ongoing.isChecked()){
                res.putInt("status",0);
            }else if(binding.droped.isChecked()){
                res.putInt("status",1);
            }
            res.putString("foto",poster);


            getParentFragmentManager().setFragmentResult("updateReview",res);
        }else if(clicked==binding.openEpisode.getId()){
           this.List=new  listEpisode(activity,id);
           FragmentManager fm=getParentFragmentManager();
           FragmentTransaction ft=fm.beginTransaction();
           ft.hide(this);
           ft.add(R.id.fragment,this.List);
           ft.addToBackStack(null);
           ft.commit();
        }else if(clicked==binding.add.getId()){
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