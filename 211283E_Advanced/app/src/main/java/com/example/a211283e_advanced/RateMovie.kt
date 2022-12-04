package com.example.a211283e_advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.a211283e_advanced.databinding.ActivityRateMovieBinding

class RateMovie : AppCompatActivity() {
    private lateinit var binding: ActivityRateMovieBinding
    private lateinit var sqliteHelper:SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"
        actionbar.setDisplayHomeAsUpEnabled(true)
        sqliteHelper = SQLiteHelper(this)
        val m = getMovie()
        binding.apply {
            reviewTV.setText(reviewTV.text.toString() + m?.name)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ratemovie,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addRating) {
            println("kk")
            rateMovie1()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun rateMovie1(){

        val id: Int = intent.getIntExtra("MovieId", -1)
        println(id)
        if (id == -1) {
            return
        }
        binding.apply {
            val rating = rate.rating.toFloat()
            val review = reviewET.text.toString()
            val movie = MovieModel(
                id = id,
                name = "name",
                overview = "overview",
                language = "language",
                releaseDate = "date",
                isSuitable = true,
                isViolence = true,
                isLanguageUsed = true,
                rating = rating,
                review = review
            )
            sqliteHelper.rateMovie(movie)
        }
        val intent = Intent(this@RateMovie,MovieDetail::class.java)
        intent.putExtra("MovieId", id)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val id: Int = intent.getIntExtra("MovieId", -1)
        val intent = Intent(this@RateMovie, MovieDetail::class.java)
        intent.putExtra("MovieId", id)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }

    private fun getMovie(): MovieModel? {
        val id: Int = intent.getIntExtra("MovieId", -1)
        if (id == -1) {
            return null
        }
        val movie = sqliteHelper.retrieveMovieById(id)
        if (movie != null) {
            println("Hello")
            println(movie.id)
        }
        return movie
    }
}

