package com.example.a211283e_intermediate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.a211283e_intermediate.databinding.ActivityMovieDetailBinding
import com.example.a211283e_intermediate.Movieinfo

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

            val name = intent.getStringExtra("name")
            val desc = intent.getStringExtra("desc")
            val language = intent.getStringExtra("language")
            val date = intent.getStringExtra("date")
            val notsuitbale = intent.getBooleanExtra("notsuitable", false)
            val cbviolent = intent.getBooleanExtra("cbviolent",false)
            val cblanguage = intent.getBooleanExtra("cblanguage",false)
            val rating = intent.getFloatExtra("rating",0F)
            val review = intent.getStringExtra("review")

            textoftitle.text = name
            textofoverview.text = desc
            textoflanguage.text = language
            textofdate.text = date
            if (rating != 0F){
                rate.rating = rating
                rate.visibility = View.VISIBLE
            }

            if (review != null) {
                textofreview.text = review
            }else if (rating != 0F){
                textofreview.text = ""
            }else{
                textofreview.text = "No Reviews yet. \nLong press here to add your review"
            }

            if (notsuitbale == true){
                if (cbviolent == true){
                    textofsuitable.text = "No(Violence)"
                }
                if (cblanguage == true){
                    textofsuitable.text = "No(Language Used)"
                }
                if (cbviolent == true && cblanguage == true){
                    textofsuitable.text = "No(Violence And Language Used)"
                }
            }else{
                textofsuitable.text = "Yes"
            }

            if (review == null || rating == 0F) {
                registerForContextMenu(textofreview)
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.moviedetail,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.review ->{

                val name = intent.getStringExtra("name")
                val desc = intent.getStringExtra("desc")
                val language = intent.getStringExtra("language")
                val date = intent.getStringExtra("date")
                val notsuitbale = intent.getBooleanExtra("notsuitable", false)
                val cbviolent = intent.getBooleanExtra("cbviolent",false)
                val cblanguage = intent.getBooleanExtra("cblanguage",false)

                val intent = Intent(this@MovieDetail,RateMovie::class.java)
                intent.putExtra("name", name)
                intent.putExtra("desc",desc)
                intent.putExtra("language",language)
                intent.putExtra("date",date)
                intent.putExtra("notsuitable", notsuitbale)
                intent.putExtra("cbviolent", cbviolent)
                intent.putExtra("cblanguage", cblanguage)
                startActivity(intent)
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@MovieDetail, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Redirect to Edit Movie info
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Edit) {
            val intent = Intent(this,EditMovieDetail::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}