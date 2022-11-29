package com.example.a211283e_intermediate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.Toast
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout

import com.example.a211283e_intermediate.databinding.ActivityAddMovieBinding

class AddMovie : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Movie Rater"
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            cbsuitable.setOnClickListener {
                setvisibility()
            }
        }
    }

    // Back to home
    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@AddMovie, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.addmovie,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Clear -> {
                binding.apply {
                    nameET.setText("")
                    desET.setText("")
                    dateET.setText("")
                    rbtnEng.setChecked(true)
                    cbsuitable.setChecked(false)
                    cbviolence.setChecked(false)
                    cblanguage.setChecked(false)
                    twocb.visibility = View.INVISIBLE
                }
            }
            R.id.Add -> {
                binding.apply {
                    // When Checkbox Suitable is clicked, set visibility of the other checkboxes

                    // Initialised the language button as english
                    // Call the validation() function to validates the form
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
                                return false
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

                        val MovieInfoAdded = Intent(this@AddMovie, MovieDetail::class.java)
                        MovieInfoAdded.putExtra("name", title)
                        MovieInfoAdded.putExtra("desc",overview)
                        // Have already converted to string above
                        MovieInfoAdded.putExtra("language",language)
                        MovieInfoAdded.putExtra("date",date)

                        MovieInfoAdded.putExtra("notsuitable", notsuitable)
                        MovieInfoAdded.putExtra("cbviolent", isviolence)
                        MovieInfoAdded.putExtra("cblanguage", islanguageused)

                        startActivity(MovieInfoAdded)

                        displayToast(message.joinToString("\n"))

                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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

    // If programme reruns, the checkbox remain the same
    override fun onResume() {
        super.onResume()
        setvisibility() // check visibility
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
}