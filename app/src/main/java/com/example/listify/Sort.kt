package com.example.listify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.listify.databinding.FragmentSortBinding


class Sort : DialogFragment(), View.OnClickListener {
    private lateinit var binding: FragmentSortBinding;
    var alphabetical=false //untuk descending
    var ratingOrder=false
    var statusOrder=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSortBinding.inflate(layoutInflater);
        binding.alphabetical.setOnClickListener(this)
        binding.rating.setOnClickListener(this)
        binding.status.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {
        var bundle : Bundle = bundleOf()
        if(v==binding.alphabetical){
            bundle.putString("kolom","Alphabet");
            bundle.putBoolean("order",alphabetical)
            if(alphabetical){
                binding.alphabetical.text="A-Z"
                alphabetical=false
            }else{
                alphabetical=true
                binding.alphabetical.text="Z-A"
            }
        }else if(v==binding.rating){
            bundle.putString("kolom","Rating");
            bundle.putBoolean("order",ratingOrder)
            if(ratingOrder){
                binding.rating.text="RATING DOWN"
                ratingOrder=false;
            }else{
                binding.rating.text="RATING UP"
                ratingOrder=true;
            }
        }else if(v==binding.status){
            bundle.putString("kolom","status");
            bundle.putBoolean("order",statusOrder)
            statusOrder =!statusOrder
        }

        parentFragmentManager.setFragmentResult("sorting",bundle)


    }


}