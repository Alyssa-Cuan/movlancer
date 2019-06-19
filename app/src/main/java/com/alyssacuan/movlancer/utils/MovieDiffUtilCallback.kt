package com.alyssacuan.movlancer.utils

import androidx.recyclerview.widget.DiffUtil
import com.alyssacuan.movlancer.models.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem?.title == newItem?.title
                && oldItem?.posterPath == newItem?.posterPath
                && oldItem?.voteAverage == newItem?.voteAverage
    }

}