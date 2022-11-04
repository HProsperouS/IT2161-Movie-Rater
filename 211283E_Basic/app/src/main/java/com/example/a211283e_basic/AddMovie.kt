package com.example.a211283e_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout

import com.example.a211283e_basic.databinding.ActivityAddMovieBinding

class AddMovie : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding

//    override fun onResume() {
//        super.onResume()
//        setvisibility() // check visibility
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // When Checkbox Suitable is clicked, set visibility of the other checkboxes
            cbsuitable.setOnClickListener {
                setvisibility()
            }
            // When Add Movie Button is clicked,
            // Do --> Validates the information entered
            // --> if is valid, pass on to show the Toast
            AddButton.setOnClickListener {
                // Initialised the language button as english
                // Call the validation() function to validates the form
                validation()
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
                            // So would not show two toast
                            return@setOnClickListener
                        }
                        message.add("Reason: ")
                        if(cbviolence.isChecked){
                            message.add("Violence")
                        }
                        if (cblanguage.isChecked){
                            message.add("Language used")
                        }
                    }
                    displayToast(message.joinToString("\n"))
                }
            }
        }

    }

    // Both check boxes have to set to visible if "Not suitable for all audience"
    private fun setvisibility(){
        val bothcb : LinearLayout = findViewById(R.id.twocb)

        if(bothcb.visibility == View.INVISIBLE){
            bothcb.visibility = View.VISIBLE
        } else{
            bothcb.visibility = View.INVISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        setvisibility() // check visibility
    }

    // Form Validation, if invalid, display the error and unable to display toast
    private fun validation(): Boolean {
        var haserror = false
        binding.apply {

            if (nameET.text.isEmpty()){
                nameET.error = "Name cannot be empty"
                haserror = true
            }
            if (desET.text.isEmpty()){
                desET.error = "Description cannot be empty"
                haserror = true
            }
            if (dateET.text.isEmpty()){
                dateET.error = "Date cannot be empty"
                haserror = true
            }
            return haserror
            // Radio button error prevention --> Set "English" as default, so it will not be empty
            // Date format validation to ensure it is formatted correctly
        }
    }

    private fun displayToast(message:String){
        Toast.makeText( applicationContext,message, Toast.LENGTH_LONG).show()
    }

}