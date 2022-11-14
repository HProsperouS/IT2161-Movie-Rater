package com.example.a211283e_advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast

import com.example.a211283e_advanced.databinding.ActivityEditMovieDetailBinding

// Copied from AddMovie
class EditMovieDetail : AppCompatActivity() {
    private var arrayAdapter:MovieAdapter? = null
    private lateinit var sqliteHelper:SQLiteHelper
    private lateinit var binding: ActivityEditMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"

        sqliteHelper = SQLiteHelper(this)

        val m = getMovie()

        binding.apply{
            if (m != null) {
                nameET.setText(m.name)
                desET.setText(m.overview)
                when(m.language){
                    "English" -> rbtnEng.isChecked = true
                    "Chinese" -> rbtnChn.isChecked = true
                    "Malay" -> rbtnMal.isChecked = true
                    "Tamil" -> rbtnTam.isChecked =true
                }
            }else{
                nameET.setText("NULL")
            }

//            dateET.setText(m.release)
//
//            if (m.notsuitable == true){
//                cbsuitable.setChecked(true)
//                cbviolence.visibility = View.VISIBLE
//                cblanguage.visibility = View.VISIBLE
//            }
//            if (m.notsuitableviolent == true){
//                cbviolence.setChecked(true)
//            }
//            if (m.notsuitablelanguage == true){
//                cblanguage.setChecked(true)
//            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmoviedetail,menu)
        return super.onCreateOptionsMenu(menu)
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
