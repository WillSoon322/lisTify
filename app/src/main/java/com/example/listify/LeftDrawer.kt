package com.example.listify

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.listify.databinding.FragmentLeftDrawerBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LeftDrawer.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeftDrawer : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLeftDrawerBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.binding= FragmentLeftDrawerBinding.inflate(layoutInflater)
        binding.HistoryDrawer.setOnClickListener(this)
        binding.ExitDrawer.setOnClickListener(this)
        binding.DaftarDrawer.setOnClickListener(this)
        binding.HomeDrawer.setOnClickListener(this)



        return binding.root;
    }

    override fun onClick(v: View?) {
        if(v==binding.HistoryDrawer){
            parentFragmentManager.setFragmentResult("openLog",Bundle());
        }else if(v==binding.ExitDrawer){
            activity?.finish();
            System.exit(0);
        }else if(v==binding.DaftarDrawer){
            parentFragmentManager.setFragmentResult("balik",Bundle())
        }else if(v==binding.HomeDrawer){
            parentFragmentManager.setFragmentResult("homeBalik",Bundle())
        }


    }


}