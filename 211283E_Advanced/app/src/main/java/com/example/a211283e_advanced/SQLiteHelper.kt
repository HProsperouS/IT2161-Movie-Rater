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
        private const val RELEASE_DATE = "release_date"
        private const val IS_SUITABLE = "is_suitable"
        private const val IS_VIOLENCE = "is_violence"
        private const val IS_LANGUAGE_USED = "is_language_used"
        private const val RATING = "rating"
        private const val REVIEW = "review"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblMovie = ("CREATE TABLE " + TBL_MOVIE + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + OVERVIEW + " TEXT," + LANGUAGE + " TEXT,"
                + RELEASE_DATE + " TEXT," + IS_SUITABLE + " INTEGER,"
                + IS_VIOLENCE + " INTEGER," + IS_LANGUAGE_USED + " INTEGER,"
                + RATING + " INTEGER," + REVIEW + " TEXT"
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
        contentValues.put(RELEASE_DATE,model.releaseDate)
        contentValues.put(IS_SUITABLE,model.isSuitable)
        contentValues.put(IS_VIOLENCE,model.isViolence)
        contentValues.put(IS_LANGUAGE_USED,model.isLanguageUsed)
        contentValues.put(RATING,model.rating)
        contentValues.put(REVIEW,model.review)

        val success = db.insert(TBL_MOVIE,null,contentValues)
        db.close()
        return success
    }


    @SuppressLint("Range", "Recycle")
    fun getAllMovie():ArrayList<MovieModel>{
        val movieList:ArrayList<MovieModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_MOVIE"
        
        println(movieList)

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
        var name: String
        var overview: String
        var language: String
        var releaseDate: String
        var isSuitable:Boolean
        var isViolence:Boolean
        var isLanguageUsed:Boolean
        // Allow null
        var rating:Int?
        var review:String?

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                overview = cursor.getString(cursor.getColumnIndex("overview"))
                language = cursor.getString(cursor.getColumnIndex("language"))
                releaseDate = cursor.getString(cursor.getColumnIndex("release_date"))
                isSuitable = cursor.getInt(cursor.getColumnIndex("is_suitable")) > 0
                isViolence = cursor.getInt(cursor.getColumnIndex("is_violence")) > 0
                isLanguageUsed = cursor.getInt(cursor.getColumnIndex("is_language_used")) > 0
                rating = cursor.getInt(cursor.getColumnIndex("rating"))
                review = cursor.getString(cursor.getColumnIndex("review"))


                val movie = MovieModel(id, name, overview, language, releaseDate, isSuitable, isViolence, isLanguageUsed, rating, review)
                movieList.add(movie)
                println(movie)
            }while (cursor.moveToNext())
        }
        return movieList
    }

    fun updateMovie(model:MovieModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, model.id)
        contentValues.put(NAME,model.name)
        contentValues.put(OVERVIEW,model.overview)
        contentValues.put(LANGUAGE,model.language)

        val success = db.update(TBL_MOVIE, contentValues,"id=" + model.id, null)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun retrieveMovieById(id:Int): MovieModel? {
        val selectQuery = "SELECT * FROM $TBL_MOVIE WHERE $ID = '$id'"
        val db = this.readableDatabase
        val cursor: Cursor

        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            return null
        }

        var movie:MovieModel? = null
        val name:String
        val overview:String
        val language:String
        var releaseDate: String
        var isSuitable:Boolean
        var isViolence:Boolean
        var isLanguageUsed:Boolean
        // Allow null
        var rating:Int?
        var review:String?


        if (cursor.moveToFirst()){
            name = cursor.getString(cursor.getColumnIndex("name"))
            overview = cursor.getString(cursor.getColumnIndex("overview"))
            language = cursor.getString(cursor.getColumnIndex("language"))
            releaseDate = cursor.getString(cursor.getColumnIndex("release_date"))
            isSuitable = cursor.getInt(cursor.getColumnIndex("is_suitable")) > 0
            isViolence = cursor.getInt(cursor.getColumnIndex("is_violence")) > 0
            isLanguageUsed = cursor.getInt(cursor.getColumnIndex("is_language_used")) > 0
            rating = cursor.getInt(cursor.getColumnIndex("rating"))
            review = cursor.getString(cursor.getColumnIndex("review"))

            movie = MovieModel(id, name, overview, language, releaseDate, isSuitable, isViolence, isLanguageUsed, rating, review)

        }
        db.close()
        return movie

    }

}