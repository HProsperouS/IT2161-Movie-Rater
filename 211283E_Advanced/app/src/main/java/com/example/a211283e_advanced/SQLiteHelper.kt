package com.example.a211283e_advanced

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import org.intellij.lang.annotations.Language
import java.lang.Exception

class SQLiteHelper(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "movie.db"
        private const val TBL_MOVIE = "tbl_movie"
        private const val ID = "id"
        private const val NAME = "name"
//        private const val Title = "title"
        private const val OVERVIEW = "overview"
        private const val LANGUAGE = "language"
//        private const val Not_Suitable = false

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblMovie = ("CREATE TABLE " + TBL_MOVIE + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + OVERVIEW + " TEXT," + LANGUAGE + " TEXT"
                + ")"
                )
        db?.execSQL(createTblMovie)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_MOVIE")
        onCreate(db)
    }

    fun insertMovie(model: MovieModel):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,model.id)
        contentValues.put(NAME,model.name)
        contentValues.put(OVERVIEW,model.overview)
        contentValues.put(LANGUAGE,model.language)

        val success = db.insert(TBL_MOVIE,null,contentValues)
        db.close()
        return success
    }



    @SuppressLint("Range", "Recycle")
    fun getAllMovie():ArrayList<MovieModel>{
        val movielist:ArrayList<MovieModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_MOVIE"
        
        println(movielist)

        val db = this.readableDatabase

        val cursor:Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id:Int
        var name:String
        var overview:String
        var language:String

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                overview = cursor.getString(cursor.getColumnIndex("overview"))
                language = cursor.getString(cursor.getColumnIndex("language"))

                val movie = MovieModel(id = id, name=name,overview=overview, language=language)
                movielist.add(movie)
                println(movie)
            }while (cursor.moveToNext())
        }

        return movielist
    }

}