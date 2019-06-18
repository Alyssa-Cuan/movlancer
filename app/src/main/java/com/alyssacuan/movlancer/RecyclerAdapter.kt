package com.alyssacuan.movlancer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alyssacuan.movlancer.models.Movie
import com.squareup.picasso.Picasso

class RecyclerAdapter(val list : List<Movie>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var movieList : List<Movie> = emptyList()

    private val imageURL : String = "https://image.tmdb.org/t/p/w500/"

    init{
        this.movieList = list
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = movieList.get(i).title
        val posterPath = movieList.get(i).posterPath
        Picasso.get().load(imageURL+posterPath).into(viewHolder.itemImage)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        //var itemRating: TextView

        init {
            itemImage = itemView.findViewById(R.id.movie_image)
            itemTitle = itemView.findViewById(R.id.movie_title)
            //itemRating = itemView.findViewById(R.id.item_rating)
        }
    }
}
