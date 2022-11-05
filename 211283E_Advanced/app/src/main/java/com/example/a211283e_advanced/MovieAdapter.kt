package com.example.a211283e_advanced

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    context: Context,
    private val resource : Int = R.layout.card_items_movie,
    objects: MutableList<MovieModel>
) : ArrayAdapter<MovieModel>(context, resource, objects){
    private val mContext: Context

    init {
        mContext = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val viewinflater: LayoutInflater = LayoutInflater.from(mContext)
            view = viewinflater.inflate(resource, null)
        }

        val movie: MovieModel? = getItem(position)

        if (movie != null) {
            val name: TextView = view!!.findViewById(R.id.name)
            name.text = movie.name
        }
        return view!!
    }

}