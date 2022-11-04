package com.example.a211283e_intermediate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a211283e_intermediate.databinding.ActivityRateMovieBinding
import com.example.a211283e_intermediate.Movieinfo

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
//            val m = Movieinfo()
//            reviewTV.setText(reviewTV.text.toString() + m.title)

        }

    }
    // Back to home
    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@RateMovie, MovieDetail::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}