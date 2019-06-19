package com.alyssacuan.movlancer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alyssacuan.movlancer.helpers.MovieDiffUtilCallback
import com.alyssacuan.movlancer.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffUtilCallback()) {

    private val imageURL : String = "https://image.tmdb.org/t/p/w200/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        val resources = holder.itemView.context.resources
        //val scoreString = resources.getString(R.string.score, item?.score)
        //val commentCountString = resources.getString(R.string.comments, item?.commentCount)
        holder.itemView.movie_title.text = item?.title
        val posterPath = item?.posterPath
        Picasso.get().load(imageURL+posterPath).resize(300,300).centerCrop().into(holder.itemView.movie_image)

    }

    inner class MovieViewHolder(movieView : View) : RecyclerView.ViewHolder(movieView)
}