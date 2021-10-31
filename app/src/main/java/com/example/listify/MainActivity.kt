package com.example.listify

import android.Manifest
import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.example.listify.databinding.ActivityMainBinding
import java.util.ArrayList


class MainActivity : AppCompatActivity(), FragmentResultListener, View.OnClickListener,IMainActivity {

    private lateinit var  binding : ActivityMainBinding
    private lateinit var fm:FragmentManager
    private lateinit var mainFragment: main
    private lateinit var ft:FragmentTransaction
    private lateinit var fragmentList:list
    private lateinit var addF :addFragment
    private lateinit var db: dbMovies
    private lateinit var movie:MoviePage
    private lateinit var em: edit_movies
    private lateinit var series:SeriesPage
    private lateinit var logv:log
    private lateinit var models: Models
    private var doubleTapOut=false;
    private var fullScreen=false;


    override fun onCreate(savedInstanceState: Bundle ?) {
        super.onCreate(savedInstanceState)

        //request access
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)

        }

        //binding
        this.binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // inisialisasi fragment-fragment
        this.mainFragment=main.newInstance("satu") //home
        this.fragmentList=list (this)
        this.addF=addFragment()
        this.movie= MoviePage(0,null,null)
        this.series= SeriesPage(0,null,null)
        this.em=edit_movies(this,"null",0,null,null,0,null,null)
        this.logv=log(this);
        this.models= Models()


        //inisialisasi DB
        this.db= dbMovies(this)


        //onclickListener
        binding.toolbar.setOnClickListener(this)
        binding.setting.setOnClickListener(this)


            //per fragment an
        this.fm=getSupportFragmentManager()
        this.fm.beginTransaction()
            .add(R.id.fragment,this.mainFragment)
            .addToBackStack(null)
            .commit()

        this.fm.setFragmentResultListener("changePage",this,this)
        this.fm.setFragmentResultListener("insertData",this,this)
        this.fm.setFragmentResultListener("editMovie",this,this)
        this.fm.setFragmentResultListener("balik",this,this)
        this.fm.setFragmentResultListener("openLog",this,this)
        this.fm.setFragmentResultListener("homeBalik",this,this)
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        if(requestKey.equals("insertData")){
            var judul = result.getString("Title")
            var Synopsis = result.getString("Synopsis")
            var tipe=result.getString("tipe")
            var poster=result.getString("foto")
                if(tipe.equals("Movies")) {
                    var newMovie = MoviesModel(judul, Synopsis,poster)
                    this.db.insertDataMovies(newMovie) // push ke db
                    if(fragmentList.isAdded) {
                        fragmentList.addData(newMovie)
                        fragmentList.setAdapterMovie()
                    }
                }else if(tipe.equals("series")){
                    var season=result.getInt("season")
                    var newSeries= SeriesModel(judul,Synopsis,season,poster)
                    this.db.insertDataSeries(newSeries)
                    if(fragmentList.isAdded) {
                        fragmentList.addData(newSeries)
                        fragmentList.setAdapterSeries()
                    }
                }

           //balikin ke main page
            onClickNavigation(binding.ListBtn)
        }else if(requestKey.equals("editMovie")){
            var tipe=result.getString("Table")
            var id=result.getInt("id")
            var judul=result.getString("judul")
            var synopsis=result.getString("synopsis")
            var status=result.getInt("status")
            var comment=result.getString("comment")
            var foto=result.getString("foto")
            this.fm=supportFragmentManager
            this.ft=fm.beginTransaction()
            hideall(fm,ft)
            this.em=edit_movies(this,tipe,id,judul,synopsis,status,comment,foto)
            ft.add(R.id.fragment,em).commit()
        }else if(requestKey.equals("balik")){
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
            onClickNavigation(binding.ListBtn)
        }else if(requestKey.equals("openLog")){
            this.fm=supportFragmentManager
            this.ft=fm.beginTransaction()
            hideall(fm,ft)
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
            if(logv.isAdded){
                this.ft.show(logv)
            } else{
                this.ft.add(R.id.fragment, logv)
                    .addToBackStack(null)
            }
            ft.commit();
            doubleTapOut=false;
        }else if(requestKey.equals("homeBalik")){
            onClickNavigation(binding.HomeBtn)
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
        }
    }

    fun onClickNavigation(clicked: View){
            this.fm=supportFragmentManager
            this.ft=fm.beginTransaction()
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
                  //  .addToBackStack(null)
            }else{
                ft.add(R.id.fragment, this.addF)
                  //  .addToBackStack(null)
            }
        }
        ft.commit()
        doubleTapOut=false;
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
                binding.drawerLayout.openDrawer(Gravity.LEFT)
        }else if(v==binding.setting){
            if(this.fullScreen) {
                this.fullScreen=false;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }else{
                this.fullScreen=true;
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
    }

    override fun openDetail(table: String?, pos: Int, x: Any?, y: Any?) {
        this.fm=getSupportFragmentManager()
        this.ft=fm.beginTransaction()
        hideall(fm, ft)
        if(table.equals("Movies")) {
            this.movie = MoviePage(pos, x, y)
            ft.add(R.id.fragment, movie)
        }else{
            this.series= SeriesPage(pos,x,y)
            ft.add(R.id.fragment,series)
        }
        this.ft.commit()
    }

    override fun fetchData(): ArrayList<MoviesModel> {
        return this.db.getDataMovies("")
    }

    override fun fetchData(command: String?): ArrayList<MoviesModel> {
        var query=this.models.search(command);
        return this.db.getDataMovies(query)
    }

    override fun fetchData(kata: String?, desc: Boolean): ArrayList<MoviesModel> {
        var query=this.models.sort(kata,desc)
        return this.db.getDataMovies(query)
    }

    override fun fetchData(drop: Boolean, finish: Boolean, going: Boolean, bintang: Int): ArrayList<MoviesModel> {
        var query=this.models.filter(drop,finish,going,bintang)
        return this.db.getDataMovies(query)
    }

    override fun update(table: String?, id: Int, cv: ContentValues?) {
        this.db.update(table,id,cv)
    }

    override fun deleteData(table: String?, id: Int) {
        this.db.delete(table,id)
    }

    override fun fetchDataSeries(drop: Boolean, finish: Boolean, going: Boolean, bintang: Int): ArrayList<SeriesModel>? {
        var query=this.models.filter(drop,finish,going,bintang)
       return this.db.getDataSerires(query)
    }

    override fun fetchDataSeries(kata: String?,desc : Boolean): ArrayList<SeriesModel> {
        var query=this.models.sort(kata,desc)
        return this.db.getDataSerires(query)
    }

    override fun fetchDataSeries(): ArrayList<SeriesModel> {
        return this.db.getDataSerires("")
    }

    override fun fetchDataSeries(kata: String?): ArrayList<SeriesModel> {
        var query=this.models.search(kata);
        return this.db.getDataSerires(query)
    }

    override fun fetchDataEpisode(fk: Int): ArrayList<EpisodeModel> {
        return this.db.getEpisodeData(fk)
    }

    override fun fetchDataLog(): ArrayList<logModel> {
        return this.db.dataLog;
    }


    override fun onBackPressed() {
        if (this.movie.isVisible) {
            this.fm = supportFragmentManager
            this.ft = fm.beginTransaction()
            ft.hide(this.movie)
            ft.show(this.fragmentList)
            ft.commit()
            doubleTapOut=false;
        } else if (this.fragmentList.isVisible) {
            this.fm = supportFragmentManager
            this.ft = fm.beginTransaction()
            ft.hide(this.movie)
            ft.commit()

            super.onBackPressed()
            doubleTapOut=false;
        } else if (this.em.isVisible) {
            this.fm = supportFragmentManager
            this.ft = fm.beginTransaction()
            ft.hide(em)
            ft.hide(fragmentList)
            var temp: Fragment
            temp = fm.fragments[0]
            for (frag in fm.fragments) {
                if (frag is MoviePage) {
                    temp = frag
                }
            }
            ft.show(temp)
            ft.commit()
            doubleTapOut=false;
        } else if (this.series.isVisible) {
            this.fm = supportFragmentManager
            this.ft = fm.beginTransaction()
            ft.hide(this.series)
            ft.show(this.fragmentList)
            ft.commit()
            doubleTapOut=false;
        } else if (this.addF.isVisible) {
            this.fm = supportFragmentManager
            this.ft = fm.beginTransaction()
            ft.hide(this.addF)
            ft.show(mainFragment)
            ft.commit()
            doubleTapOut=false;
        }  else if (this.mainFragment.isVisible){
            if(this.doubleTapOut){
                finish();
                System.exit(0);
            }else{
                var toast= Toast.makeText(this,"DOUBLE TAP TO EXIT",Toast.LENGTH_SHORT)
                toast.show()
                doubleTapOut=true;
            }
        } else{

            super.onBackPressed()
        }
    }

}