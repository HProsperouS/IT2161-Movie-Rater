package com.example.a211283e_advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.example.a211283e_advanced.databinding.ActivityMovieDetailBinding


class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var sqliteHelper:SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sqliteHelper = SQLiteHelper(this)

        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"
        actionbar.setDisplayHomeAsUpEnabled(true)
        retrieveMovie()
        binding.apply {
            registerForContextMenu(textofreview)
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
                val id: Int = intent.getIntExtra("MovieId", -1)
                val intent = Intent(this@MovieDetail,RateMovie::class.java)
                intent.putExtra("MovieId", id)
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

    private fun retrieveMovie(){
        val m = getMovie()

        binding.apply{
            if (m != null) {
                textoftitle.setText(m.name)
                textofoverview.setText(m.overview)
                textoflanguage.setText(m.language)
                textofdate.setText(m.releaseDate)
                val isSuitbale = m.isSuitable
                val isViolence = m.isViolence
                val isLanguageUsed = m.isLanguageUsed
                if (m.review != null){
                    rate.rating = m.rating!!
                    textofreview.setText(m.review)
                }else{
                    textofreview.text = "No Reviews yet. \nLong press here to add your review"
                }

                if (isSuitbale == true){
                    if (isViolence == true){
                        textofsuitable.text = "No(Violence)"
                    }
                    else if (isLanguageUsed == true){
                        textofsuitable.text = "No(Language Used)"
                    }
                    else{
                        textofsuitable.text = "No(Violence And Language Used)"
                    }
                }else{
                    textofsuitable.text = "Yes"
                }
                registerForContextMenu(textofreview)
            }else{
                textoftitle.setText("NULL")
            }

        }
    }

//    private fun reviewornot(){
//        binding.apply {
//            val m = getMovie()
//            if (m != null) {
//                if (m.review != null){
//                    return
//                }else{
//                    registerForContextMenu(textofreview)
//                }
//            }
//        }
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.edit,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    // Redirect to Edit Movie info
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item?.itemId == R.id.Edit) {
//            val intent = Intent(this,EditMovieDetail::class.java)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }

}