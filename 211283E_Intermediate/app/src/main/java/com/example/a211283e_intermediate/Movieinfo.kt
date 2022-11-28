package com.example.a211283e_intermediate

//class Movieinfo {
//    var title = "Venom"
//    var overview = "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life"
//    var language = "English"
//    var date = "03-10-2018"
//    var suitable = "Yes"
//}

class Movieinfo(name: String,desc:String,language:String,release:String,notsuitable:Boolean,notsuitableviolent:Boolean,notsuitableexplang:Boolean) {
    var name: String
    var desc: String
    var language: String
    var release: String
    var notsuitable: Boolean
    var notsuitableviolent: Boolean
    var notsuitablelanguage: Boolean
    init {
        this.name = name
        this.desc = desc
        this.language = language
        this.release = release
        this.notsuitable = notsuitable
        this.notsuitableviolent = notsuitableviolent
        this.notsuitablelanguage = notsuitableexplang
    }
}

data class Movie(var name: String, var desc: String, var language: String, var release: String, var notsuitable: Boolean, var notsuitableexplang: Boolean)