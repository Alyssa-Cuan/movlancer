package com.alyssacuan.movlancer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.alyssacuan.movlancer.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class GridAdapter : BaseAdapter {
    var movieList : List<Movie> = emptyList()
    var context: Context? = null
    private val imageURL : String = "https://image.tmdb.org/t/p/w200/"

    constructor(context: Context, movieList: List<Movie>) : super() {
        this.context = context
        this.movieList = movieList
    }

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = this.movieList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var movieView = inflator.inflate(R.layout.movie_item_layout, null)
        val posterPath = movieList.get(position).posterPath
        Picasso.get().load(imageURL+posterPath).resize(300,300).centerCrop().into(movieView.movie_image)
        movieView.movie_title.text = movie.title

        return movieView
    }
}
