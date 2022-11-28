package com.example.a211283e_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a211283e_basic.databinding.ActivityMovieDetailBinding
import com.example.a211283e_basic.MovieInfo

class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
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

            textoftitle.text = m.name
            textofoverview.text = m.desc
            textoflanguage.text = m.language
            textofdate.text = m.release
            var notSuitable:String

            if (m.notsuitable == true){
                 notSuitable = "YES"
            }
            else{
                notSuitable = "No"
            }
            textofsuitable.text = notSuitable

        }
    }

//    private fun Movie(): MovieInfo {
//        var movie = MovieInfo(
//            name = "Venom",
//
//        )
//        return movie
//    }
}