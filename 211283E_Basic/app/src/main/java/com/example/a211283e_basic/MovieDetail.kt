package com.example.a211283e_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a211283e_basic.databinding.ActivityMovieDetailBinding
import com.example.a211283e_basic.Movieinfo

class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            val m = Movieinfo()

            textoftitle.text = m.title
            textofoverview.text = m.overview
            textoflanguage.text = m.language
            textofdate.text = m.date
            textofsuitable.text = m.suitable

        }
    }
}