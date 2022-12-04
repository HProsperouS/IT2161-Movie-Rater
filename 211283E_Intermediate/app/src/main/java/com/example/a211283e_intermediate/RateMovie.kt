package com.example.a211283e_intermediate

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        // Getting Movie Name
        val name = intent.getStringExtra("name")

        binding.apply {
            reviewTV.setText(reviewTV.text.toString() + name)
        }
    }
    // Back to home
    override fun onSupportNavigateUp(): Boolean {
        binding.apply {
            // Getting Movie Info from intent
            val name = intent.getStringExtra("name")
            val desc = intent.getStringExtra("desc")
            val language = intent.getStringExtra("language")
            val date = intent.getStringExtra("date")
            val notsuitbale = intent.getBooleanExtra("notsuitable", false)
            val cbviolent = intent.getBooleanExtra("cbviolent",false)
            val cblanguage = intent.getBooleanExtra("cblanguage",false)

            val intent = Intent(this@RateMovie, MovieDetail::class.java)
            intent.putExtra("name", name)
            intent.putExtra("desc",desc)
            intent.putExtra("language",language)
            intent.putExtra("date",date)
            intent.putExtra("notsuitable", notsuitbale)
            intent.putExtra("cbviolent", cbviolent)
            intent.putExtra("cblanguage", cblanguage)
            startActivity(intent)
            return super.onSupportNavigateUp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ratemovie,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.submit ->{
                binding.apply {
                    val rating = rate.rating.toFloat()
                    val review = reviewET.text.toString()
                    val intent = Intent()
                    intent.putExtra("rating",rating)
                    intent.putExtra("review",review)
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}