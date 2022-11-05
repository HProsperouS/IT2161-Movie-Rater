package com.example.a211283e_advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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
    }

}