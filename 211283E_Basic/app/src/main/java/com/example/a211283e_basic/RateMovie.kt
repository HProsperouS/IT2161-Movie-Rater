package com.example.a211283e_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a211283e_basic.databinding.ActivityRateMovieBinding
import com.example.a211283e_basic.MovieInfo

class RateMovie : AppCompatActivity() {
    private lateinit var binding: ActivityRateMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            val m = MovieInfo(
                name = "Venom",
                desc = "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life",
                language = "English",
                release = "03-10-2018",
                notsuitable = true
            )
            reviewTV.setText(reviewTV.text.toString() + m.name)
        }
    }
}