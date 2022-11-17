package com.example.a211283e_advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
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
        retrieveMovie()
        binding.apply {
            cbsuitable.setOnClickListener {
                setvisibility()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmoviedetail,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.Save ->{
                updateMovie()
            }
            R.id.Cancel ->{
                val intent = Intent(this@EditMovieDetail, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
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
                nameET.setText(m.name)
                desET.setText(m.overview)
                when(m.language){
                    "English" -> rbtnEng.isChecked = true
                    "Chinese" -> rbtnChn.isChecked = true
                    "Malay" -> rbtnMal.isChecked = true
                    "Tamil" -> rbtnTam.isChecked =true
                }
                dateET.setText(m.releaseDate)
                cbsuitable.isChecked = m.isSuitable
                cbviolence.isChecked = m.isViolence
                cblanguage.isChecked = m.isLanguageUsed
                val bothcb : LinearLayout = findViewById(R.id.twocb)
                if (cbsuitable.isChecked){
                    bothcb.visibility = View.VISIBLE
                }else{
                    bothcb.visibility = View.INVISIBLE
                }
            }else{
                nameET.setText("NULL")
            }

        }
    }

    // Form Validation, if invalid, display the error and unable to display toast
    private fun validation(): Boolean {
        var error = false
        binding.apply {
            if (nameET.text.isEmpty()){
                nameET.error = "Name cannot be empty"
                error = true
            }
            if (desET.text.isEmpty()){
                desET.error = "Description cannot be empty"
                error = true
            }
            if (dateET.text.isEmpty()){
                dateET.error = "Date cannot be empty"
                error = true
            }

            return error
        }
    }

    private fun displayToast(message:String){
        Toast.makeText( applicationContext,message, Toast.LENGTH_LONG).show()
    }

    private fun updateMovie(){
        binding.apply {
            val haserror = validation()
            if (haserror == false){
                val title = nameET.text.toString()
                val overview = desET.text.toString()
                // Doing android.widget.RadioButton instead of checkedID and compared in IF ELSE Statement,shorten the code
                val languageRDBT: RadioButton = findViewById(radiogroup.checkedRadioButtonId)
                val language = languageRDBT.text.toString()
                val date = dateET.text.toString()
                val notsuitable = cbsuitable.isChecked
                val isviolence = cbviolence.isChecked
                val islanguageused = cblanguage.isChecked


                val message: ArrayList<String> = arrayListOf(
                    "Title: $title",
                    "Overview: $overview",
                    "Release Date: $date",
                    "Language: $language",
                    "Not suitable for all ages: $notsuitable"
                )

                // If it is not suitable for all audience, must give a reason
                if (notsuitable){
                    // if not give a reason, return back to onclicklistener
                    if(!isviolence && !islanguageused){
                        displayToast("Please give a reason why it is not suitable for all ages")
                        return
                    }
                    else{
                        message.add("Reason: ")
                        if(cbviolence.isChecked){
                            message.add("Violence")
                        }
                        if (cblanguage.isChecked){
                            message.add("Language used")
                        }
                    }
                }
                val id: Int = intent.getIntExtra("MovieId", -1)
                if (id == -1) {
                    return
                }

                val movie = MovieModel(
                    id = id,
                    name = title,
                    overview = overview,
                    language = language,
                    releaseDate = date,
                    isSuitable = notsuitable,
                    isViolence = isviolence,
                    isLanguageUsed = islanguageused,
                    rating = null,
                    review = null
                )
                sqliteHelper.updateMovie(movie)
                val intent = Intent(this@EditMovieDetail,MovieDetail::class.java)
                intent.putExtra("MovieId", movie.id)
                startActivity(intent)
        }

    }
    }
    // Both check boxes have to set to visible if "Not suitable for all audience"
    private fun setvisibility(){
        val bothcb : LinearLayout = findViewById(R.id.twocb)
        val notsuitable : CheckBox = findViewById(R.id.cbsuitable)
        if (notsuitable.isChecked){
            bothcb.visibility = View.VISIBLE
        } else{
            bothcb.visibility = View.INVISIBLE
        }
    }
}

private operator fun Int.invoke(suitable: Boolean) {

}
