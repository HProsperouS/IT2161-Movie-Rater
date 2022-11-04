package com.example.a211283e_advanced


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
