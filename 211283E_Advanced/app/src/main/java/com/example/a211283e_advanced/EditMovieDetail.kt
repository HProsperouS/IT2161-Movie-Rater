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
    private lateinit var binding: ActivityEditMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"

        val m = Movieinfo("Venom", "Overview","English","19-10-2018",true,true,false)

        binding.apply{
            nameET.setText(m.name)
            desET.setText(m.desc)

            when(m.language){
                "English" -> rbtnEng.isChecked = true
                "Chinese" -> rbtnChn.isChecked = true
                "Malay" -> rbtnMal.isChecked = true
                "Tamil" -> rbtnTam.isChecked =true
            }
            dateET.setText(m.release)

            if (m.notsuitable == true){
                cbsuitable.setChecked(true)
                cbviolence.visibility = View.VISIBLE
                cblanguage.visibility = View.VISIBLE
            }
            if (m.notsuitableviolent == true){
                cbviolence.setChecked(true)
            }
            if (m.notsuitablelanguage == true){
                cblanguage.setChecked(true)
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmoviedetail,menu)
        return super.onCreateOptionsMenu(menu)
    }

}
