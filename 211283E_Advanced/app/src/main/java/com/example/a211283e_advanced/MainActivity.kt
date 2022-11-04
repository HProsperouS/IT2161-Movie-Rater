package com.example.a211283e_advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var adapter:MovieAdapter? = null

    private lateinit var sqliteHelper:SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRecyclerView()
        sqliteHelper = SQLiteHelper(this)
        getMovies()
        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"
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

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        recyclerView.adapter = adapter
    }
    private fun initView(){
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun getMovies(){
        val movieList = sqliteHelper.getAllMovie()
        Log.e("pppp","${movieList.size}")

        adapter?.addItems(movieList)
    }

}