package com.example.listify;



import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.listify.databinding.EpisodelistBinding;
import java.util.ArrayList;

public class episodeAdapter extends BaseAdapter {
    private ArrayList<EpisodeModel> arr;
    private EpisodePresenter ep;
    private listEpisode le;


    public episodeAdapter(listEpisode LE,EpisodePresenter ep){
        this.ep=ep;
        this.le=LE;
        dataChange();
    }

    public void dataChange(){
        arr=ep.getAllData();
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public EpisodeModel getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EpisodelistBinding binding;
        ViewHolderEpisode vhe;
            binding=EpisodelistBinding.inflate(le.getLayoutInflater());
            vhe=new ViewHolderEpisode(getItem(position),le,binding);
        return binding.getRoot();
    }




}

class ViewHolderEpisode implements View.OnClickListener {

    private EpisodelistBinding binding;
    private ImageView star[];
    private EpisodeModel episode;
    private listEpisode le;

    public ViewHolderEpisode(EpisodeModel episode,listEpisode le,EpisodelistBinding binding){

        this.binding=binding;
        ImageView star[]={
                this.binding.star,
                this.binding.star1,
                this.binding.star2,
                this.binding.star3,
                this.binding.star4
        };
        this.star=star;
        for(ImageView satu : star){
            satu.setOnClickListener(this);
        }
        binding.openButton.setOnClickListener(this);
        this.le=le;
        this.episode=episode;
        setText();


    }

    public void setText(){
        binding.judulEpido.setText(episode.getJudul());
        int rate=episode.getBintang();

        for(ImageView x:this.star){
            x.setImageResource(R.drawable.ic_star_empty);
        }

        for(int i=0;i<rate;i++){
            this.star[i].setImageResource(R.drawable.ic_star_full);
        }


    }



    @Override
    public void onClick(View v) {
        int clicked=v.getId();
        for (ImageView x: this.star){
            if(x.getId()==clicked){
                this.le.updateBintang(episode.getId());
            }
        }

        if(clicked==binding.openButton.getId()){
            this.le.updateEpisode(this.episode);
        }




    }
}


