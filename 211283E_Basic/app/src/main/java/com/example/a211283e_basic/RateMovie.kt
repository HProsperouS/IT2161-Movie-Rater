package com.example.a211283e_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a211283e_basic.databinding.ActivityRateMovieBinding
import com.example.a211283e_basic.Movieinfo

class RateMovie : AppCompatActivity() {
    private lateinit var binding: ActivityRateMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val m = Movieinfo()
            reviewTV.setText(reviewTV.text.toString() + m.title)
        }
    }
}