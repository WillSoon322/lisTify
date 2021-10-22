package com.example.listify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.example.listify.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity(), FragmentResultListener, View.OnClickListener,IMainActivity {

    private lateinit var  binding : ActivityMainBinding
    private lateinit var fm:FragmentManager
    private lateinit var mainFragment: main
    private lateinit var ft:FragmentTransaction;
    private lateinit var fragmentList:list
    private lateinit var addF :addFragment
    private lateinit var db: dbMovies




    override fun onCreate(savedInstanceState: Bundle ?) {
        super.onCreate(savedInstanceState)


        this.binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // binding.toolbar.setOnClickListener(this)
       // var ab :ActionBarDrawerToggle
       // ab= ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.Open,R.string.Close)
      //  binding.drawerLayout.addDrawerListener(ab)
       // ab.syncState()


        // inisialisasi fragment-fragment
        this.mainFragment=main.newInstance("satu") //home
        this.fragmentList=list (this);
        this.addF=addFragment();


        //inisialisasi DB
        this.db= dbMovies(this);




        this.fm=getSupportFragmentManager()
        this.fm.beginTransaction()
            .add(R.id.fragment,this.mainFragment)
            .addToBackStack(null)
            .commit()

        this.fm.setFragmentResultListener("changePage",this,this)
        this.fm.setFragmentResultListener("insertData",this,this)


    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        if(requestKey.equals("insertData")){
                var judul=result.getString("Title")
                var Synopsis=result.getString("Synopsis")
            var newMovie= MoviesModel(judul,Synopsis)
            this.db.insertDataMovies(newMovie) // push ke db
            onClickNavigation(binding.HomeBtn) //balikin ke main page
        }
    }




    fun onClickNavigation(clicked: View){
            this.fm=supportFragmentManager;
            this.ft=fm.beginTransaction();
        //AddBtn,HomeBtn,ListBtn
        hideall(this.fm, this.ft)
        if (clicked.id==R.id.ListBtn){
            if(this.fragmentList.isAdded){
                ft.show(this.fragmentList)
                    .addToBackStack(null)
            }else{
                ft.add(R.id.fragment,this.fragmentList)
                    .addToBackStack(null)
            }
        }else if(clicked.id==R.id.HomeBtn) {
            if (this.mainFragment.isAdded) {
                ft.show(this.mainFragment)
                    .addToBackStack(null)
            } else {
                ft.add(R.id.fragment, this.mainFragment)
                    .addToBackStack(null)
            }
        }else if(clicked.id==R.id.AddBtn){
            if(this.addF.isAdded){
                ft.show(this.addF)
                    .addToBackStack(null)
            }else{
                ft.add(R.id.fragment, this.addF)
                    .addToBackStack(null)
            }
        }
        ft.commit();
    }

    fun hideall(fm: FragmentManager, ft:FragmentTransaction){
        for(frag in fm.fragments){
            if(frag !is LeftDrawer) {
                ft.hide(frag)
            }
        }
    }



    override fun onClick(v: View?) {
        if(v==binding.toolbar){


        }

    }

    override fun dataChanged(change: Any?) {
    }



    override fun openDetail(pos: Int, x: Any?) {
        this.fm=supportFragmentManager;
        this.ft=fm.beginTransaction();
        hideall(this.fm, this.ft)
        var x = MoviePage(pos, x )
        ft.add(R.id.fragment, x)
            .addToBackStack(null)
        this.ft.commit()
    }

    override fun fetchData(): ArrayList<MoviesModel> {
        return this.db.allData;
    }


}