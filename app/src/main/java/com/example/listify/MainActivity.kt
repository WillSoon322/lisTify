package com.example.listify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import com.example.listify.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity(), FragmentResultListener {

    private lateinit var  binding : ActivityMainBinding
    private lateinit var fm:FragmentManager
    private lateinit var mainFragment: main

    override fun onCreate(savedInstanceState: Bundle ?) {
        super.onCreate(savedInstanceState)


        this.binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setSupportActionBar(binding.toolbar)


        var ab :ActionBarDrawerToggle
        ab= ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.Open,R.string.Close)
        binding.drawerLayout.addDrawerListener(ab)
        ab.syncState()

        this.mainFragment=main.newInstance("main")


        this.fm=getSupportFragmentManager()
        this.fm.beginTransaction()
            .add(R.id.fragment_container,this.mainFragment)
            .commit()

        this.fm.setFragmentResultListener("routeListener",this,this)
        binding.fragmentContainer.adapter(this)




    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        TODO("Not yet implemented")
    }


    fun onClickNavigation(clicked: View){
        if (clicked.id==R.id.movies_Button){
            Log.d("true", "haha: ")
        }else{
            Log.d("false", "haha: ")
        }




}