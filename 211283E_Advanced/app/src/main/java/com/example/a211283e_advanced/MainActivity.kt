package com.example.a211283e_advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView



class MainActivity : AppCompatActivity() {
    private var arrayAdapter:MovieAdapter? = null
    private lateinit var sqliteHelper:SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sqliteHelper = SQLiteHelper(this)
        getMovies()
        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"
    }



    override fun onResume() {
        getMovies()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.Add) {
            val AddMovie = Intent(this,AddMovie::class.java)
            startActivity(AddMovie)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getMovies(){
        val movielist = sqliteHelper.getAllMovie()
        arrayAdapter = MovieAdapter(this,R.layout.card_items_movie, movielist)
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = arrayAdapter
        registerForContextMenu(listView)
        listView.setOnItemClickListener { adapterView, view, i, l ->
        var movie: MovieModel = adapterView.getItemAtPosition(i) as MovieModel
            GoMovieDetail(movie)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.editmain,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.miEdit->{
                val position = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val movieSelected = arrayAdapter?.getItem(position.position)!!
                GoEditMovie(movieSelected)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun GoEditMovie(movie:MovieModel){
        val intent = Intent(this@MainActivity,EditMovieDetail::class.java)
        intent.putExtra("MovieId", movie.id)
        startActivity(intent)
    }

    private fun GoMovieDetail(movie: MovieModel){

        val intent = Intent(this@MainActivity,MovieDetail::class.java)
        intent.putExtra("MovieId", movie.id)
        startActivity(intent)
    }


}