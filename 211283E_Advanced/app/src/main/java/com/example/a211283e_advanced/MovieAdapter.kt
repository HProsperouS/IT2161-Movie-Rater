package com.example.a211283e_advanced

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    private var movieList:ArrayList<MovieModel> = ArrayList()

    fun addItems(items:ArrayList<MovieModel>){
        this.movieList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_movie,parent,false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bindView(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieViewHolder(var view:View):RecyclerView.ViewHolder(view){
        private var name = view.findViewById<TextView>(R.id.name)
        fun bindView(model: MovieModel){
            name.text = model.name
        }
    }


}