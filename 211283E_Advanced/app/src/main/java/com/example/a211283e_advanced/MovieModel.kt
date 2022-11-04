package com.example.a211283e_advanced
import org.intellij.lang.annotations.Language
import java.util.*

data class MovieModel (
    var id:Int =getAutoId(),
    var name: String = "",
    var overview: String = "",
    var language: String = ""

    ){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}