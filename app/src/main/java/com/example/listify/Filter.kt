package com.example.listify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.listify.databinding.FragmentFilterBinding


class Filter : DialogFragment(), View.OnClickListener {
    private lateinit var binding: FragmentFilterBinding
    private lateinit var star:Array<ImageButton>
    private var rating =0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterBinding.inflate(layoutInflater)
        binding.btnFilter.setOnClickListener(this)
        var temp=arrayOf(binding.ratingStar1,binding.ratingStar2,binding.ratingStar3,binding.ratingStar4,binding.ratingStar5)
        star=temp;

        for (x in star){
            x.setOnClickListener(this)
        }

        return binding.root
    }

    override fun onClick(p0: View?) {
        if(p0 == binding.btnFilter){
            var bundle : Bundle = bundleOf()
            if(binding.statusDrop.isChecked){
                bundle.putBoolean("drop",true)
            }else{
                bundle.putBoolean("drop",false)
            }

            if(binding.statusFinish.isChecked){
                bundle.putBoolean("finish",true)
            }else{
                bundle.putBoolean("finish",false)
            }

            if(binding.statusOnGoing.isChecked){
                bundle.putBoolean("going",true)
            }else{
                bundle.putBoolean("going",false)
            }
            bundle.putInt("bintang",rating)
            parentFragmentManager.setFragmentResult("filterProcess",bundle)
        }else {
            var tempRating=0;
            var i=0;
            for( x in star){
                i++;
                if(p0==x){
                    tempRating=i;
                }
            }

                if(tempRating!=rating) {
                    this.rating = tempRating;
                }else{
                    this.rating=0;
                }


                //offin semua
                for( x in star){
                    x.setImageResource(R.drawable.ic_star_empty)
                }

                i=0;

                for(x in star){
                    if(i< rating){
                        x.setImageResource(R.drawable.ic_star_full)
                        i++;
                    }
                }









        }
    }


}