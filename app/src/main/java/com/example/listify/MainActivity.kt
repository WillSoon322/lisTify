package com.example.listify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.listify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private lateinit var x:H

    private lateinit var  binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setSupportActionBar(binding.toolbar)


        var ab :ActionBarDrawerToggle
        ab= ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.Open,R.string.Close)
        binding.drawerLayout.addDrawerListener(ab)
        ab.syncState()




   // x.angka

    }





}